<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>CreateTopic</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div class="picture">
    <p><img src="<c:url value="http://localhost:8080/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="CreatePage" align="center">
    <form:form method="post" action="/admin/create-topic" modelAttribute="topic">

        <table>
            <tr><td> <form:input path="topicName" placeholder="Название темы" size="20" required="true"/></td></tr>
            <tr><td><form:input path="description" placeholder="Описание темы" size="20"  required="true"/></td></tr>
            <tr><td><input type="submit" value="Сохранить тему" /></td></tr>
        </table>
    </form:form>
    <p><a href="/admin/add-or-update-topic" class="button" title="Вернуться на главную страицу">Назад</a></p>
    <p id="success"><a style="color: green; font-size: 120%;" >${success}</a></p>
</div>

<script>setTimeout(function () {document.getElementById("success").style.display = 'none';},4000)</script>

</body>
</html>