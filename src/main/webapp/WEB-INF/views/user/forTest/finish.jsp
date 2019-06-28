<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finish Test</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/scripts/plugin.js"></script>
    <script type="text/javascript" src="http://localhost:8080/scripts/userScript.js"></script>
</head>
<body>
<div align="center">
    <h1>Ваш результат</h1>
    <table border="1" class="info">
        <th>Колличество правильных ответов</th>
        <th>Колличество неправильных ответов</th>
        <tr>
            <td id="right">${rightAnswers}</td>
            <td id="wrong">${wrongAnswers}</td>
        </tr>
    </table>
    <%--<p><a href="/user/forTest/literature" class="button" title="что почитать">Что почитать</a></p>--%>
    <%--<p><a href="/user/forTest/choose-topic" class="button" title="Вернуться на страицу назад">Назад</a></p>--%>
</div>
<br><br><br>
<br><br><br>
<div class = "progress-bar">
</div>
<div align="center">
    <c:if test="${not empty literatureList}">

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
    </c:if>
    <p><a href="/user/forTest/choose-topic" class="button" title="Вернуться на страицу назад">Назад</a></p>
</div>
</body>
</html>


<%--<c:forEach var="correctAnswer" items="${corrects}" varStatus="status">--%>
<%--    <tr>--%>
<%--        <td>${status.index + 1}</td>--%>
<%--        <td>${correctAnswer}</td>--%>
<%--        <td>--%>
<%--            <a style="color: #20ef20" href="/user/forTest/start-test?testId=${test.testId}">Выбрать</a>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</c:forEach>--%>