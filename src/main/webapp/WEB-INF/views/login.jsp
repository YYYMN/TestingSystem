<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div class="picture">
    <p><img src="<c:url value="http://localhost:8080/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
</div>

<div class="LoginPage" align="center">
    <form action="/login" method="post">
        <table>
            <tr><td><a>Войти в систему</a></td></tr>

            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                <tr><td><a style="color: red; font-size: 85%">Ой, не получилось: неверный логин или пароль</a></td></tr>
            </c:if>

            <tr><td><input type="text" name="username" placeholder="Логин" required="true"></td></tr>
            <tr><td><input type="password" name="password" placeholder="Пароль" required="true"></td></tr>
            <tr><td><input type="submit" class="sing_in" value="Войти"></td></tr>
        </table>
    </form>
</div>
</body>
</html>



