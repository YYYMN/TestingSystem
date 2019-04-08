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
    <h1>Таблица для редактирования тем</h1>
    <table border="1" class="info">
        <th>№</th>
        <th>Название темы</th>
        <th>Описание</th>
        <th>Действие</th>

        <c:forEach var="topic" items="${topicsList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${topic.topicName}</td>
                <td>${topic.description}</td>
                <td>
                    <a class="edit" href="/admin/update-topic?topicId=${topic.topicId}">Редактировать</a>
                    <a class="delete" href="/admin/delete-topic?topicId=${topic.topicId}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/admin/add-or-update-topic" class="button" title="Вернуться на страицу назад">Назад</a></p>
</div>
</body>
</html>
