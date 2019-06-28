<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Literature</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div align="center">
    <h1>Список литературы</h1>
    <table border="1" class="info">
        <th>Вопрос</th>
        <th>статья в интернете</th>

        <%--        <c:forEach var="question" items="${question}" varStatus="status">--%>
        <tr>
            <td>
                <c:forEach var="literature" items="${literatureList}">
                    <p><a style="color: #1c2de6" >${literature.description}</a></p>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="link" items="${linkList}">
                    <p><a style="color: #e61e2c" href="${link.link}">Перейти</a></p>
                </c:forEach>
            </td>
        </tr>
        <%--        </c:forEach>--%>
    </table>
    <p><a href="/user/forTest/choose-topic" class="button" title="Вернуться на страицу назад">Назад</a></p>
</div>
</body>
</html>
