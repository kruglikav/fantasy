<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    java.util.Date currDate = new java.util.Date();
%>
<html>
<head>
<link href="<c:url value='/css/layout.css'><c:param name="v" value="<%=java.lang.Long.toString(currDate.getTime())%>"/></c:url>" rel="stylesheet">
<script src="<c:url value='/js/jquery.js'/>"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div id="full-content">
    <tiles:insertAttribute name="header" />
    <div style="min-height:90%;text-align: justify">
        <tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />
</div>


</body>
</html>