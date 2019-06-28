<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div align="center">
    <h1>Таблица тестов</h1>
    <table border="1" class="info">
        <th>№</th>
        <th>Название темы</th>
        <th>Описание</th>
        <th>Действие</th>

        <c:forEach var="test" items="${allTests}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${test.testName}</td>
                <td>${test.description}</td>
                <td>
                    <a style="color: #20ef20" href="/tutor/add-or-update-question?testId=${test.testId}">Выбрать</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/tutor/tutor-main-page" class="button" title="Вернуться на страицу назад">Назад</a></p>
</div>
</body>
</html>