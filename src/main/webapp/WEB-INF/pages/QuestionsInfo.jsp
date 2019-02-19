<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Info</title>
</head>
<body>
    <div align="center">
        <h1>Таблица с информацией о вопросах</h1>
        <table border="1">
            <th>№</th>
            <th>Название вопроса</th>
            <th>Пройдено всего раз</th>
            <th>Процент правильных ответов</th>

            <c:forEach var="question" items="${list}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${question.questionName}</td>
                    <td>${question.numberOfTimes}</td>
                    <td>${question.percent}%</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
