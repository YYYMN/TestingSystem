<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>CreateQuestion</title>
        <link rel="stylesheet" href="http://localhost:8080/css/CreateQuestionPageStyle.css">
    </head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="CreateQuestionPage" >
            <form method="post" action="">
                <input list="questions" placeholder = "Введите новый вопрос" id="question" name="question" autocomplete="off">
                    <datalist id="questions" >
                        <c:forEach var="question" items="${questions}">
                            <option>${question.description}</option>
                        </c:forEach>
                    </datalist>
                <br>
                <div id="dynamic_field"></div>
                <button type="button" name="add" id="add" class="button">Добавить ответ</button>
                <button id="updateQuestion" name="save" value="save" type="submit" class="button" >Сохранить</button>
            </form>
        </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    </body>
</html>

<script>
    $(document).ready(function(){
        var i = 1;
        $(document).on('input','#question', function (ev) {
            if( $(ev.target).val() == "") {$('#dynamic_field').empty()}
            $.ajax({
                type : "GET",
                url : '/DisplayQuestionsFromDb',
                data : {
                    targetQuestion : $(ev.target).val()
                },
                success : function (data) {
                    for (var j = 0; j < data.length ; j++) {
                        $('#dynamic_field').append('<div class="div'+i+'">' +
                            '<input type="text" name="answer[]" id="answer' + i + '" value="'+data[j].description+'">' +
                            '<input type="hidden" name="checkbox_option" class="checkbox'+i+'" value="false"">');
                        if (data[j].correct == 1){
                            // var checked = "checked";
                            $('.div'+i+'').append('<input type="checkbox" class="checkbox'+i+'" data-correct="0" name="checkbox" value="false" checked>');
                        } else if (data[j].correct == 0){
                            $('.div'+i+'').append('<input type="checkbox" class="checkbox'+i+'" data-correct="0" name="checkbox" value="false">');
                        }
                        $('.div'+i+'').append('<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
                        i++;
                    }
                }
            })
        });

        $(document).on('click','#add',function(){
            $('#dynamic_field').append('<div class="div'+i+'">' +
                                       '<input type="text" name="answer[]" id="answer' + i + '" placeholder ="Ответ">' +
                                       '<input type="hidden" name="checkbox_option" class="checkbox'+i+'" value="false">'+
                                       '<input type="checkbox" class="checkbox'+i+'" data-correct="0" name="checkbox" value="false">'+
                                       '<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
            i++;
        });

        $(document).on('click','#remove', function (){
            var div_id = $(this).attr('name');
            $.ajax({
                type : "POST",
                url : '/DeleteAnswerFromDb',
                data : {
                    targetAnswer: $('#answer' + div_id + '').attr('value')
                }

            });
            $('.div'+div_id+'').remove();

        });

        $(document).on('click','[class^="checkbox"]', function () {
            var d = document,
                inp = d.getElementsByName('checkbox_option'),
                mas = [];
            function save() {
                for (var i = 0; i < inp.length; i++) {
                    mas[i] = inp[i];
                }
                console.log(mas);
            }
            save();

            if ($(this).data('correct') == 0) {
                $(this).data('correct', "1");
                $(this).prop("value", "true");

                for (var i = 0; i < mas.length; i++) {
                    if ($(this).attr('class') == mas[i].className) {
                         mas[i].value = 'true';
                    }
                }

            } else{ if ($(this).data('correct') == 1) {
                    $(this).data('correct', 0);
                    $(this).prop("value", "false");
                    for (i = 0; i < mas.length; i++) {
                        if ($(this).attr('class') == mas[i].className) {
                            mas[i].value = 'false';
                        }
                    }

                    }
            }
        });

});
</script>
