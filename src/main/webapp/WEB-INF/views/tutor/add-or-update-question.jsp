<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CreateQuestion</title>
        <link rel="stylesheet" href="http://localhost:8080/css/CreateQuestionPageStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="http://localhost:8080/scripts/CreateQuestion.js"></script>
    </head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="CreateQuestionPage" >
            <form method="post" action="">
                <input list="questions" required placeholder = "Введите новый вопрос" id="question" name="question" autocomplete="off">
                    <datalist id="questions" >
                        <c:forEach var="question" items="${questions}">
                            <option>${question.description}</option>
                        </c:forEach>
                    </datalist>
                <br>
                <div id="dynamic_field"></div>
                <button type="button" name="add" id="add" class="button">Добавить ответ</button>
                <p><a href="/tutor/tutor-main-page" class="button" title="Вернуться на главную страницу">Назад</a></p>
                <button id="save" name="save" value="save" type="submit" class="button" disabled >Сохранить</button>
            </form>
        </div>
    </body>
</html>

