<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Answer</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div align="center">
    <h1>Таблица вопросов</h1>
    <table border="1" class="info">
        <th>Название вопроса</th>
        <th>Выберете ответ</th>

        <%--        <c:forEach var="question" items="${question}" varStatus="status">--%>
        <tr>
            <td> ${answer}</td>
            <td>

                <p><a style="color: #e61e2c" href="/user/forTest/start-test?testId=${testId}">Дальше</a></p>
            </td>
        </tr>
        <%--        </c:forEach>--%>
    </table>
    <p><a href="/user/forTest/choose-topic" class="button" title="Вернуться на страицу назад">Назад</a></p>
</div>
</body>
</html>