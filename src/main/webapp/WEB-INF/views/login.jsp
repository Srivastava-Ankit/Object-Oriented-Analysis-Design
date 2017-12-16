<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Login"/>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/main.css" />
    <link href="https://fonts.googleapis.com/css?family=Vollkorn+SC" rel="stylesheet">
</head>
<body class="app-color no-margin">
<div class="text-center image-logo"><img src="/images/logo.png" alt="Smiley face" ></div>
<h1 class="heading-color text-center app-font" > Welcome to Prorg</h1>
<div class="flex-center">

    <div class="login-box inline-flex">

        <form class="width-full" action="/login" method="post">
           <div class="text-box app-font"><input placeholder="Email Address / Username" type="text" name="email" id="email" /></div>
            <div class="text-box app-font"><input type="password" placeholder="Password" name="password" id="password" /></div>
            <div class="text-box app-font login-button"><input type="submit" value="Login" /></div>
                <p class="sign-notamember text-center app-font">Not a member? <a href="/register" class="">Sign up now</a></p>
        </form>
    </div>
</div>
</body>
</html>
