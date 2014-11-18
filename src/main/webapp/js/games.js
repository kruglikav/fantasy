function updateGames(){
    $.ajax({
        type: 'GET',
        url: '/fantasy/games/update',
        success: function(data){
            $('#openGames ul').empty();
            for(var key in data){
                current = data[key]['currentNumberPlayers'];
                all = data[key]['numberPlayers'];
                if (current==all){
                    continue;
                }
                href = '<a href="/fantasy/games/play?id='+key+'">Join to game</a>';
                li = '<li>Game-'+data[key]['id']+':'+current+'/'+all+href+'</li>';
                $('#openGames ul').append(li);
            }
            updateGames();
        },
        error: function(){
            updateGames();
        }
    });
}
function updateGame(id){
    $.ajax({
        type: 'GET',
        url: '/fantasy/games/update?id='+id,
        success: function(data){
            current = data['currentNumberPlayers'];
            all = data['numberPlayers'];
            if(all==current){
                $('#gameRoom').html("Начинаем игру!");
                return;
            }
            $('#currentNumber').html(data['currentNumberPlayers']);
            updateGame(id);
        },
        error: function(){
            updateGame(id);
        }
    });
}