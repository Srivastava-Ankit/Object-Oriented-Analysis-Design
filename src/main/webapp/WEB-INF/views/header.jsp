<head>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>
<div class="header-box">
    <div class="header-button1"><a href="/">HOME</a></div>
    <div class="inline-flex">
        <c:if test="${sessionScope.loggedInUser == null}">
            <div class="header-button1 "><a href="/register">Register</a></div>
            <div class="header-button2"><a href="/login">Login</a></div>
        </c:if>
        <c:if test="${sessionScope.loggedInUser != null}">
            <div class="header-button2"><a href="/logout">Logout</a></div>
        </c:if>
    </div>
</div>