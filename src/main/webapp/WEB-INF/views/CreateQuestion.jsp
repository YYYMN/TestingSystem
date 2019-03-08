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
        <meta charset="utf-8">
        <title>CreateQuestion</title>
    </head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="CreateQuestionPage" align="center">
            <form method="post" action="">
                <label>
                    <select>
                        <c:forEach var="question" items="${questions}">
                            <option>${question.description}</option>
                        </c:forEach>
                    </select>
                </label><br>
                <input type="text" name="question" placeholder = "Введите новый вопрос"><br>
                <div id="dynamic_field"></div>
                <button type="button" name="add" id="add" class="btn btn-success">Добавить ответ</button>
                <button id="updateQuestion" name="btn" value="save" type="submit" >Сохранить</button>
            </form>
        </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    </body>
</html>

<script>
    $(document).ready(function(){
        var i=1;
        $(document).on('click','#add',function(){
            $('#dynamic_field').append('<div class="div' + i + '">' +
                                       '<input type="text" name="answer[]" id="answer' + i + ' " placeholder ="' + i + '. Ответ">' +
                                       '<input type="hidden" name="checkbox_option" class="checkbox'+i+'" value="false">'+
                                       '<input type="checkbox" class="checkbox'+i+'" data-correct="0" name="checkbox" value="false">'+
                                       '<button type="button" name="'+i+'" id="remove" class="btn btn-success">Удалить</button><br></div>');
            i++;
        });

        $(document).on('click','#remove', function (){
            var div_id = $(this).attr("name");
            $('#div'+div_id+'').remove();
        });

        $(document).ready().on('click','[class^="checkbox"]', function () {
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
