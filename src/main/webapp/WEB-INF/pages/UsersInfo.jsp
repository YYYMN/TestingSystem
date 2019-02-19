<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Таблица с информацией о пользователях</h1>
    <table border="1">
        <th>№</th>
        <th>Фамилия и имя</th>
        <th>Название теста</th>
        <th>Пройдено всего раз</th>
        <th>Процент правильных ответов</th>

        <c:forEach var="user" items="${list}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.userNameAndSurname}</td>
                <td>${user.testName}</td>
                <td>${user.numberOfTimes}</td>
                <td>${user.percent}%</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
