function updateGames(){
    $.ajax({
        type: 'GET',
        url: '/fantasy/games/update',
        dataType: 'json',
        success: function(data){
            console.log('updateGames success');
            console.log(data);
            $('#openGames ul').empty();
            for(var key in data){
                current = data[key]['currentNumberPlayers'];
                all = data[key]['numberPlayers'];
                if (current==all){
                    console.log('continue');
                    continue;
                }
                href = '<a href="/fantasy/games/play?id='+key+'">Join to game</a>';
                li = '<li>Game-'+data[key]['id']+':'+current+'/'+all+href+'</li>';
                $('#openGames ul').append(li);
            }
            updateGames();
        },
        error: function(){
            console.log('updateGames error');
            updateGames();
        }
    });
}
function updateGame(id){
    $.ajax({
        type: 'GET',
        url: '/fantasy/games/update?id='+id,
        dataType: 'json',
        success: function(data){
            console.log('updateGame success');
            console.log(data);
            current = data['currentNumberPlayers'];
            all = data['numberPlayers'];
            console.log(current+'/'+all);
            if(all==current){
                $('#gameRoom').html("Начинаем игру!");
                return;
            }
            $('#currentNumber').html(data['currentNumberPlayers']);
            updateGame(id);
        },
        error: function(){
            console.log('updateGame error');
            updateGame(id);
        }
    });
}