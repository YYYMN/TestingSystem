<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TableOfUsersForEditing</title>
    <link rel="stylesheet" href="http://localhost:8080/css/stylesForTables.css">
    <link rel="stylesheet" href="http://localhost:8080/css/a_buttons.css">
    <link rel="stylesheet" href="http://localhost:8080/css/UserProgressGridStyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
    <body>
        <div align="center">
            <h1>Прогресс для пользователя: ${userLastAndFirstName}</h1>
            <table border="1" class="info">
                <th>№</th>
                <th>Название теста</th>
                <th>Усреднённый результат прохождения теста по дням в процентах</th>
                <%----%>
            </table>
            <p><a href="/admin/table-of-users-for-watching-grid" class="button" title="Вернуться на страицу выбора статистики">Назад</a></p>
        </div>
    </body>
</html>

<script>
    var numb = 1;
    var progressGridList = ${progressGridList};

    for (var j = 0; j < progressGridList.length; j++) {
        $('.info').append('<tr><td>'+numb+'</td><td>'+progressGridList[j].testName+'</td><td>'+getPercents()+'</td></tr>');

        function getPercents() {
            var percents = '';
            for (var k = 0; k < progressGridList[j].percentList.length; k++){

                var ourPercent = progressGridList[j].percentList[k];
                var color = '', src = '';

                if (ourPercent <= 39) color = 'red';
                if (ourPercent >= 40 && ourPercent <= 69 ) color = 'yellow';
                if (ourPercent >= 70) color = 'green';

                if (k == 0){
                    // src = "http://localhost:8080/images/line.png";
                    percents += '<a class="'+color+'">'+ourPercent+'</a><a> </a>';
                } else {
                    if (progressGridList[j].percentList[k] < progressGridList[j].percentList[k-1]) src = "http://localhost:8080/images/downarrow.png";
                    if (progressGridList[j].percentList[k] > progressGridList[j].percentList[k-1]) src = "http://localhost:8080/images/uparrow.png";
                    if (progressGridList[j].percentList[k] == progressGridList[j].percentList[k-1]) src = "http://localhost:8080/images/line.png";
                    percents += '<a class="'+color+'">'+ourPercent+'</a><img width="19" src="'+src+'" alt="">';
                }

            }
            return percents;
        }
        numb++;
    }
</script>
