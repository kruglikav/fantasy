<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/js/games.js'/>">

</script>

<div id="openGames">
    <ul>
        <c:forEach items="${gamesMap}" var="game">
            <spring:url var="joinToGameUrl" value="/games/play?id=${game.key}" />
            <li id="${game.key}">Game ${game.key}:${game.value.currentNumberPlayers}/${game.value.numberPlayers}<a href="${joinToGameUrl}">Join to game</a></li>
        </c:forEach>
    </ul>
</div>
<div id="createGame">
    <spring:url var="createGameUrl" value="/games/create" />
    <form:form action="${createGameUrl}">
        <button id="createGameBtn" type="submit">create game</button>
    </form:form>
</div>
<script>
    $(document).ready(function(){
        updateGames();
    });

</script>
