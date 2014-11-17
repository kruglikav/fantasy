<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value='/js/games.js'/>">

</script>

<div id="gameRoom">
    Wait opponents...<span id="currentNumber">${game.currentNumberPlayers}</span>/${game.numberPlayers}
</div>

<script>
    $(document).ready(function(){
        var gameId = ${game.id};
        updateGame(gameId);
    });

</script>
