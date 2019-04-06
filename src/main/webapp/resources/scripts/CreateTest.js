$(document).ready(function(){

    var testsData;
    var i = 0;

    //Добавляем новое поле при нажатии кнопки "Добавить вопрос"
    $(document).on('click','#add',function(){

        $('.innerQuestionsDiv').append('<div class="question'+i+'">' +
            '<input type="text" required list="questions" id="question" placeholder="Выберите вопрос" name="questions[]" autocomplete="off">' +
            '<datalist id="questions" class="questionsDatalist">'+questionsToSelect()+'</datalist>'+
            '<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
        i++;
    });

    //Удаляем вопрос при нажатии на кнопку "Удалить"
    $(document).on('click','#remove', function (){
        var question_id = $(this).attr('name');
        $('.question'+question_id+'').remove();

    });

    // Подтягиваем из БД существующие темы
    $(document).on('input','#topic', function (ev) {
        //Если поле пустое, удаляем все, что находится ниже него
        if ($(ev.target).val() == "") {
            $('.innerTestsDiv').remove();
            $('.innerQuestionsDiv').remove();
        }
        $.ajax({
            type : "GET",
            url : "/GetTestsByTopic",
            data : {
                targetTopic : $(ev.target).val()
            },
            success : function (data) {
                testsData = data;
                console.log(data);
                $('.tests').append('<div class="innerTestsDiv"><p><a>Выберите тест</a></p><input type="text" required list="tests" placeholder="Введите новый или выберите тест" id="test" name="test" autocomplete="off">' +
                    '<input hidden id="testId" name="testId" value="">'+
                    '<datalist id="tests" class="testsDatalist">');

                data.forEach(function(test) {
                    $('.testsDatalist').append('<option>'+test.description+'</option>');
                });

                $('.tests').append('</datalist></div>');
            }
        })
    });

    // Подтягиваем из БД существующие тесты
    $(document).on('input','#test', function (ev) {

        if ($('.innerQuestionsDiv')[0]){
        }else $('.questions').append('<div class="innerQuestionsDiv"><p><a>Выберите вопросы для теста</a></p></div>');



        if ($(ev.target).val() == "") {
            $('.innerQuestionsDiv').remove();
        }

        testsData.forEach(function(test) {
            if ($(ev.target).val() == test.name) {
                $('#testId').attr('value', test.testId)
            }
        });

        $.ajax({
            type : "GET",
            url : "/GetQuestionsByTest",
            data : {
                targetTest : $(ev.target).val()
            },
            success : function (data) {
                data.forEach(function(question) {
                    $('.innerQuestionsDiv').append('<div class="question'+i+'">' +
                        '<input readonly value="'+question.description+'" name="questions[]" autocomplete="off">' +
                        '<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
                    i++;
                });
            }
        })
    });
});