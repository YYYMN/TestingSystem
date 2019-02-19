<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 17.02.2019
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Info</title>
</head>
<body>
<div align="center">
    <h1>Таблица с информацией о тестах</h1>
    <table border="1">
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
</div>
</body>
</html>
