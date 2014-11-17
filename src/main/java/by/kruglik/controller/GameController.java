package by.kruglik.controller;

import by.kruglik.bean.Game;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by kruglik on 17.11.2014.
 */
@Controller
@RequestMapping("/games")
public class GameController {
    private static Logger log = Logger.getLogger(GameController.class);
    private Thread connectionManager = null;
    private final Object lock = new Object();

    @PostConstruct
    public void initConnectionManager() {
        connectionManager = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                     log.debug("ConnectionManager run");
                    if (updated) {
                        log.debug("something updated in games");
                        Thread commonManager = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (DeferredResult<Map<Integer, Game>> conn : commonConnections) {
                                    log.debug("set result for common connection");
                                    conn.setResult(games);
                                }
                            }
                        });
                        Thread gameManager = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (Map.Entry<DeferredResult<Game>,Integer> entry : gameConnections.entrySet()){
                                    Integer gameId = entry.getValue();
                                    Game game = games.get(gameId);
                                    entry.getKey().setResult(game);
                                    log.debug(String.format("set result for game %n connection ",gameId));
                                }
                            }
                        });
                        commonManager.start();
                        gameManager.start();

                        try {
                            gameManager.join();
                            commonManager.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        synchronized (updated) {
                            updated = false;
                        }
                    }
                    synchronized (lock){
                        try {
                            log.debug("ConnectionManager wait");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        connectionManager.start();
    }

    @PreDestroy
    public void destroyConnectionManager() {
        connectionManager.interrupt();
    }

    private List<DeferredResult<Map<Integer, Game>>> commonConnections =
            new CopyOnWriteArrayList<DeferredResult<Map<Integer, Game>>>();
    private Map<DeferredResult<Game>,Integer> gameConnections =
            new ConcurrentHashMap<DeferredResult<Game>,Integer>();
    private Map<Integer, Game> games = new ConcurrentHashMap<Integer, Game>();
    private volatile Boolean updated = false;
    private Integer idGen = 0;

    @RequestMapping(method = RequestMethod.GET)
    public String viewGames(Model model) {
        model.addAttribute("gamesMap", games);
        return "games";
    }

    @RequestMapping("/create")
    public String createGame(Model model) {
        Game game = new Game();
        game.setId(++idGen);
        game.setNumberPlayers(3);
        game.setCurrentNumberPlayers(1);
        games.put(game.getId(), game);
        synchronized (updated) {
            updated = true;
        }
        synchronized (lock){
            lock.notify();
            log.debug("ConnectionManager notify");
        }


        model.addAttribute("game", game);
        return "game";
    }

    @RequestMapping(value = "/play", params ="id")
    public String joinToGame(@RequestParam int id, Model model) {
        Game game = games.get(id);
        game.setCurrentNumberPlayers(game.getCurrentNumberPlayers() + 1);
        synchronized (updated) {
            updated = true;
        }
        synchronized (lock){
            lock.notify();
            log.debug("ConnectionManager notify");
        }
        model.addAttribute("game", game);
        return "game";
    }

    @RequestMapping("/update")
    @ResponseBody
    public DeferredResult<Map<Integer, Game>> updateGames() {
        log.debug("add conn to common list");
        final DeferredResult<Map<Integer, Game>> df = new DeferredResult<Map<Integer, Game>>();
        commonConnections.add(df);
        df.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.debug("onCompletion remove conn from common list");
                commonConnections.remove(df);
            }
        });
        return df;
    }

    @RequestMapping(value = "/update",params = "id")
    @ResponseBody
    public DeferredResult<Game> updateGame(@RequestParam int id) {
        log.debug(String.format("add conn to game %n",id));
        final DeferredResult<Game> df = new DeferredResult<Game>();
        gameConnections.put(df,id);
        df.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.debug("onCompletion remove conn from game list");
                gameConnections.remove(df);
            }
        });
        return df;
    }

}
