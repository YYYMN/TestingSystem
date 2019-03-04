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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="<c:url value="/resources/scripts/AddNewAnswer.js"/>"></script>
    <title>CreateQuestion</title>
</head>
<body>
<div class="picture">
    <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
</div>
<div class="CreateQuestionPage" align="center">
    <form method="post" action="">
        <input type="text" name="question" placeholder = "Введите новый вопрос"><br>
        <div id="dynamic_field">
            <input type="text" id="question1" placeholder = "1. Ответ"><br>
        </div>
        <input type="text" placeholder = "1. Ответ"><br>
        <button type="button" name="add" id="add" class="btn btn-success">Добавить ответ</button>
        <button id="updateQuestion" name="btn" value="save" type="submit" >Сохранить</button>
    </form>
</div>
</body>
</html>
