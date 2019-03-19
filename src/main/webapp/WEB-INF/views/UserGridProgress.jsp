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
    <h1>Прогресс для пользователя: ${userLastAndFirstName}</h1>
    <table border="1" class="info">
        <th>№</th>
        <th>Название теста</th>
        <th>Усреднённый результат прохождения теста по дням в процентах</th>

        <c:forEach var="userGrid" items="${progressGridList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${userGrid.testName}</td>
                <td>${userGrid.percentList}</td>

            </tr>
        </c:forEach>
    </table>
    <p><a href="http://localhost:8080/TableOfUsersForWatchingGrid" class="button" title="Вернуться на страицу выбора статистики">Назад</a></p>
</div>
</body>
</html>
