function updateGames(){
    $.ajax({
        type: 'GET',
        url: '/fantasy/games/update',
        success: function(data){
            $('#openGames ul').empty();
            for(var key in data){
                href = '<a href="/fantasy/games/play?id='+key+'">Join to game</a>';
                li = '<li>Game-'+data[key]['id']+':'+data[key]['currentNumberPlayers']+'/'+data[key]['numberPlayers']+href+'</li>';
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
            $('#currentNumber').html(data['currentNumberPlayers']);
            updateGame(id);
        },
        error: function(){
            updateGame(id);
        }
    });
}