<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
java.util.Date currDate = new java.util.Date();
%>
<script src="<c:url value='/js/games.js'><c:param name="v" value="<%=java.lang.Long.toString(currDate.getTime())%>"/></c:url>">

</script>

<div id="gameRoom">
    <c:choose>
        <c:when test="${game.currentNumberPlayers eq game.numberPlayers}">
            Начинаем игру!
        </c:when>
        <c:otherwise>
            Wait opponents...<span id="currentNumber">${game.currentNumberPlayers}</span>/${game.numberPlayers}
        </c:otherwise>
    </c:choose>

</div>

<script>
    $(document).ready(function(){
        var gameId = ${game.id};
        if (!${game.currentNumberPlayers eq game.numberPlayers}){
            updateGame(gameId);
        }

    });

</script>
