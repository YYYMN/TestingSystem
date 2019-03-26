<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tests Info</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div align="center">
    <h1>Таблица с информацией о тестах</h1>
    <table border="1" class="info">
        <th>№</th>
        <th>Название теста</th>
        <th>Пройдено всего раз</th>
        <th>Процент правильных ответов</th>

        <c:forEach var="test" items="${list}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${test.testName}</td>
                <td>${test.numberOfTimes}</td>
                <td>${test.percent}%</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="http://localhost:8080/html/Statistics.html" class="button" title="Вернуться на страицу выбора статистики">Назад</a></p>
</div>
</body>
</html>
