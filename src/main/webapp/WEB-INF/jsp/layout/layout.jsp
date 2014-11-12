<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="<c:url value='/css/layout.css'/>" rel="stylesheet">
<script src="<c:url value='/js/jquery.js'/>"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div id="full-content">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
</div>


</body>
</html>