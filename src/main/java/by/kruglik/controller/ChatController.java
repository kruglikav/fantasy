package by.kruglik.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.security.Principal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by kruglik on 10.11.2014.
 */
@Controller
public class ChatController {
    private static Logger log = Logger.getLogger(ChatController.class);
    private volatile String msg="";
    private Queue<DeferredResult<String>> requests = new ConcurrentLinkedQueue<DeferredResult<String>>();
    @RequestMapping("/")
    public String chatView(){
        return "chat";
    }
    @RequestMapping("getMsg")
    @ResponseBody
    public DeferredResult<String> getMsg(){
        log.info("start getMsg()");
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        requests.add(deferredResult);

        log.info("finish getMsg()");
        return deferredResult;
    }
    @RequestMapping(value = "/postMsg",method = RequestMethod.POST)
    @ResponseBody
    public void postMsg(@RequestParam String msg,Principal principal) throws InterruptedException {
        log.info("start postMsg()");
        String userName = principal.getName();
        msg = userName + ": " + msg;
        log.info("msg= "+msg);
        this.msg = msg;
        for (DeferredResult<String> i : requests){
            i.setResult(msg);
        }
        requests.clear();
        msg="";
        log.info("finish postMsg()");
    }
}
