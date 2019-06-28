<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserMainPage</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
</head>
<body>
<div class="picture">
    <p><img src="http://localhost:8080/images/picture.png" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="refPage" align="center">
    <p style="font-size: 110%">Добро пожаловать, <span style="color: black;">${username}!</span></p>
    <p><a href="/user/forTest/choose-topic" class="text">Выбрать тест</a></p>

    <p><a href="/user/statistics" class="text">Статистика</a></p>
    <p></p>
    <form method="post" action="/user/user-main-page">
        <button name="name" value="misha">Enter Please</button>
    </form>

    <form action="/logout" method="post" >
        <input type="submit" class="logout" value="Выйти" />
    </form>
</div>
</body>
</html>
