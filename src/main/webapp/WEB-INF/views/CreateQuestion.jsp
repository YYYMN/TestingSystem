<%--
  Created by IntelliJ IDEA.
  User: Sitx
  Date: 01.03.2019
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateQuestion</title>
</head>
<body>
<div class="picture">
    <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="CreateQuestionPage" align="center">
    <form method="post" action="">
        <input type="text" name="question" placeholder = "Введите новый вопрос"><br>
        <input type="text" placeholder = "1. Ответ"><br>
        <input type="text" placeholder = "2. Ответ"><br>
        <input type="text" placeholder = "3. Ответ"><br>
        <input type="text" placeholder = "4. Ответ"><br>
        <button id="updateQuestion" name="btn" value="save" type="submit" >Сохранить</button>
    </form>
</div>
</body>
</html>
