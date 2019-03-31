<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TutorMainPage</title>
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
</head>
<body>
<div class="picture">
    <p><img src="http://localhost:8080/images/picture.png" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="refPage" align="center">
    <p style="font-size: 110%">Добро пожаловать, <span style="color: green;">${username}!</span></p>
    <p><a href="/tutor/add-or-update-test" class="text">Создать или редактировать тест</a></p>
    <p><a href="/tutor/add-or-update-question" class="text">Создать или редактировать вопрос</a></p>
    <p><a href="/tutor/statistics" class="text">Статистика</a></p>

    <form action="/logout" method="post" >
        <input type="submit" class="logout" value="Выйти" />
    </form>
</div>
</body>
</html>
