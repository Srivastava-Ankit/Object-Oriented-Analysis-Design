<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/main.css" />
    <link href="https://fonts.googleapis.com/css?family=Vollkorn+SC" rel="stylesheet">
</head>
<body class="app-color no-margin home-margin">
<div class="container">
    <header class="inline-flex">
       <div class="width-30"><h1 class="heading-color text-center app-font" > Welcome to Prorg</h1></div>
        <div  class="width-70 flex-center">

            <div class="width-full">

                <form class="width-full inline-flex header-login" action="/login" method="post">
                    <div class="text-box-header app-font"><input placeholder="Email Address" type="text" name="email" /></div>
                    <div class="text-box-header app-font"><input type="password" placeholder="Password" name="password" /></div>
                    <div class="text-box-header app-font login-button-header"><input type="submit" value="Login" /></div>
                </form>
            </div>
        </div>
    </header>
    <aside>

        <c:out value="${message}"/>
        <ul>
            <c:forEach var="user" items="${users}" varStatus="row">
                <li>${user.lastName}, ${user.firstName} </li>
            </c:forEach>
        </ul>
    </aside>
    <main>
        <div class="flex-center">

            <div class="login-box inline-flex">
                <form  class="width-full" action="${pageContext.request.contextPath}/register" method="post">
                    <div class="text-center register-now heading-color"> Register Now</div>
                    <div class="text-box app-font"><input placeholder="First Name" type="text" id="first-name" name="firstName" /></div>
                    <div class="text-box app-font"><input placeholder="Last Name" type="text" id="last-name" name="lastName" /></div>
                    <div class="text-box app-font"><input placeholder="Email Address" type="text" name="email" /></div>
                    <div class="text-box app-font"><input type="password" placeholder="Password" name="password" /></div>
                    <div class="text-box app-font"><input type="password" placeholder="Confirm Password" id="confirm-password" name="confirmPassword" /></div>
                    <div class="text-box app-font login-button"><input type="submit" value="Register" /></div>
                </form>
            </div>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>
