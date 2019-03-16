<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>CreateUser</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
</head>
<body>
<div class="picture">
    <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="CreateUserPage" align="center">
    <form:form method="post" action="/AddUser" modelAttribute="user">

        <table>
            <tr>
                <td>Выберите роли для пользователя:</td><br>
                <td> <form:checkboxes   path="roles" items="${allRoles}"/></td>
            </tr>
            <tr><td> <form:input path="lastName" placeholder="Фамилия" size="20" required="true"/></td></tr>
            <tr><td><form:input path="firstName" placeholder="Имя" size="20"  required="true"/></td></tr>
            <tr><td><form:password path="password" placeholder="Пароль" size="20"  required="true"/></td></tr>
            <tr><td><form:input path="login" placeholder="Логин" size="20"  required="true"/></td></tr>
            <tr><td> <form:input type="email" path="email" placeholder="Почта" size="20"  required="true"/><br></td></tr>

            <tr><td><input type="submit" value="Сохранить пользователя" /></td></tr>

        </table>
    </form:form>
    <p><a href="http://localhost:8080/html/MainPage.html" class="button" title="Вернуться на главную страицу">Назад</a></p>
</div>
<div>${success}</div>
</body>
</html>


<%--
<form:input path="lastName" placeholder="Фамилия" size="20" required="true"/><br>
<form:input path="firstName" placeholder="Имя" size="20"  required="true"/><br>
<form:password path="password" placeholder="Пароль" size="20"  required="true"/><br>
<form:input path="login" placeholder="Логин" size="20"  required="true"/><br>
<form:input type="email" path="email" placeholder="Почта" size="20"  required="true"/><br>--%>
