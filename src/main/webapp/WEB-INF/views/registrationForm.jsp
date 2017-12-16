<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Register"/>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link href="https://fonts.googleapis.com/css?family=Vollkorn+SC" rel="stylesheet">
</head>
<body class="app-color">
<div class="text-center image-logo"><img src="images/logo.png" alt="Smiley face" ></div>
<h1 class="heading-color text-center app-font "> Sign up now</h1>
<div class="flex-center">

    <div class="login-box inline-flex">
        <form  class="width-full" action="${pageContext.request.contextPath}/register" method="post">
            <div class="text-box app-font"><input placeholder="First Name" type="text" id="first-name" name="firstName" /></div>
            <div class="text-box app-font"><input placeholder="Last Name" type="text" id="last-name" name="lastName" /></div>
            <div class="text-box app-font"><input placeholder="Email Address" type="text" name="email" id="email" /></div>
            <div class="text-box app-font"><input type="password" placeholder="Password" name="password" id="password" /></div>
            <div class="text-box app-font"><input type="password" placeholder="Confirm Password" id="confirm-password" name="confirmPassword" /></div>
            <div class="text-box app-font login-button"><input type="submit" value="Register" /></div>
        </form>
    </div>
</div>
</body>
</html>
