<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="/css/main.css">
    <title>Storyboards</title>
</head>
<body class="no-margin">
<%@ include file="header.jsp" %>
<div class="storyboards-container">
<h3>All your storyboards</h3>
<div class="width-full">
<c:forEach var="storyboard" items="${storyboards}" varStatus="row">
    <div class="storyboard-tag"> <a href="${pageContext.request.contextPath}/storyboards/${storyboard.id}">${storyboard.title}</a></div>
</c:forEach>
</div>


<h3>Create a storyboard</h3>
<form action="/storyboards" method="post">
    <div class=" app-font">
        <input placeholder="Title" type="text" id="title" name="title"/>
    </div>
    <div class="text-box app-font">
        <textarea id="description" type="text" name="description" rows="10" cols="70" placeholder="Please add storyBoard description here."></textarea>
    </div>
    <div class="text-box app-font login-button">
        <input type="submit" value="Create"/>
    </div>
</form>
</div>
</body>
</html>
