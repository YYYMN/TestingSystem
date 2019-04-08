<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>UpdateTopic</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
</head>
<body>
<div class="picture">
    <p><img src="http://localhost:8080/images/picture.png" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="CreateTopic" align="center">
    <form:form method="post" action="/admin/update-topic" modelAttribute="topic">

            <table>
                <tr><td> <form:hidden path="topicId" /></td></tr>
                <tr><td> <form:input path="topicName" placeholder="Название темы" size="20" required="true"/></td></tr>
                <tr><td><form:textarea path="description" placeholder="Описание темы" size="20"  required="true"/></td></tr>
                <tr><td><input type="submit" value="Обновить тему" /></td></tr>
            </table>
        </form:form>
    <p><a href="/admin/table-of-topics-for-editing" class="button" title="Вернуться на таблицу для редактирования пользователей">Назад</a></p>
    <p id="success"><a style="color: green; font-size: 120%;" >${success}</a></p>
</div>

<script>setTimeout(function () { document.getElementById("success").style.display = 'none'; },4000)</script>

</body>
</html>
