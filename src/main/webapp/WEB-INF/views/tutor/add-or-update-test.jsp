<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>CreateTest</title>
        <link rel="stylesheet" href="http://localhost:8080/css/CreateTestPageStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="http://localhost:8080/scripts/CreateTest.js"></script>
    </head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="CreateTestPage" align="center">
            <form method="post" action="">
                <p><a>Выберите тему:</a></p>
                <input type="text"  required list="topics" placeholder="Выберите тему" id="topic" name="topic" autocomplete="off">
                    <datalist id="topics" >
                        <c:forEach var="topic" items="${topics}">
                            <option>${topic.description}</option>
                        </c:forEach>
                    </datalist>
                <div id="savebtn"><button class="button" id="save" value="save" type="submit" >Сохранить тест</button></div>
                <br>
                <div class="tests"></div>
                <div class="questions"></div>
                <button class="button" id="add" value="add" type="button">Добавить вопрос</button>
                <br>
                <p><a href="/tutor/tutor-main-page" class="button" id="back" title="Вернуться на главную страицу">Назад</a></p>
            </form>

        </div>
    </body>
</html>
<script>
    function questionsToSelect(){
        var questionsArray = ${JSONQuestions};
        var questionDescription = '';
        for (var j = 0; j < questionsArray.length; j++) {
            questionDescription += '<option>'+questionsArray[j].description+'</option>';
        }
        return questionDescription;
    }
</script>
