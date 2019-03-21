<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TableOfUsersForEditing</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div align="center">
    <h1>Выберите пользователя для просмотра его прогресса</h1>
    <table border="1" class="info">
        <th>№</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Логин</th>
        <th>email</th>
        <th>Действие</th>

        <c:forEach var="user" items="${usersList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.lastName}</td>
                <td>${user.firstName}</td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>
                    <a class="showProgress" href="http://localhost:8080/UserForWatchingGrid?userId=${user.userId}&userLastName=${user.lastName}&userFirstName=${user.firstName}">Посмотреть прогресс</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="http://localhost:8080/html/Statistics.html" class="button" title="Вернуться на страицу выбора статистики">Назад</a></p>
</div>
</body>
</html>

