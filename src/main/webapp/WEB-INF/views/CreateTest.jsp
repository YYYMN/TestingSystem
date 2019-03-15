<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>CreateTest</title>
    </head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="CreateTestPage" align="center">
            <form method="post" action="">
                <label>
                    <input list="topics" placeholder="Выберите тему" id="topic">
                    <datalist id="topics" >
                        <c:forEach var="topic" items="${topics}">
                            <option>${topic.description}</option>
                        </c:forEach>
                    </datalist>
                </label><br>
                <div class="tests"></div>
                <div class="questions"></div>
                <button id="save" value="save" type="submit" >Сохранить</button>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    </body>
</html>

<script>
    $(document).ready(function(){

        $(document).on('input','#topic', function (ev) {
            if ($(ev.target).val() == "") {
                $('.innerTestsDiv').remove();
            }else {
                $.ajax({
                    type : "GET",
                    url : "/GetTestsByTopic",
                    data : {
                        targetTopic : $(ev.target).val()
                    },
                    success : function (data) {
                        $('.tests').append('<div class="innerTestsDiv"><input list="tests" placeholder="Выберите тест" id="test">' +
                                           '<datalist id="tests" class="testsDatalist">');

                        data.forEach(function(test) {
                            $('.testsDatalist').append('<option>'+test.description+'</option>');
                        });

                        $('.tests').append('</datalist></div>');
                    }
                })
            }

        });

        $(document).on('input','#test', function (ev) {
            if ($(ev.target).val() == "") {
                $('.innerQuestionsDiv').remove();
            }else {
                $.ajax({
                    type : "GET",
                    url : "/GetQuestionsByTest",
                    data : {
                        targetTest : $(ev.target).val()
                    },
                    success : function (data) {
                        $('.questions').append('<div class="innerQuestionsDiv"><input list="questions" id="question" placeholder="Выберите вопрос"><br>' +
                            '<datalist id="questions" class="questionsDatalist">');

                        data.forEach(function(question) {
                            $('.questionsDatalist').append('<option>'+question.description+'</option>');
                        });

                        $('.questions').append('</datalist></div>');
                    }
                })
            }
        });

        $(document).on('input','#question', function (ev) {
            $('.questions').append('<input list="questions" id="question" placeholder="Выберите вопрос"><br>');
        });



    })




</script>