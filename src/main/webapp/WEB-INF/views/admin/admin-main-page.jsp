<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminMainPage</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
</head>
<body>
<div class="picture">
    <p><img src="http://localhost:8080/images/picture.png" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="refPage" align="center">
    <p style="font-size: 110%">Добро пожаловать, <span style="color: green;">${username}!</span></p>
    <p><a href="/admin/add-or-update-topic" class="text">Создать или редактировать тему</a></p>
    <p><a href="/admin/add-or-update-user" class="text">Создать или редактировать пользователя</a></p>
    <p><a href="/admin/statistics" class="text">Статистика</a></p>

    <form action="/logout" method="post" >
        <input type="submit" class="logout" value="Выйти" />
    </form>
</div>
</body>
</html>
