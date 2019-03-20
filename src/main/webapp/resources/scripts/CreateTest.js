$(document).ready(function(){

    var testsData;
    var i = 0;

    $(document).on('click','#add',function(){

        $('.innerQuestionsDiv').append('<div class="question'+i+'">' +
            '<input type="text" required list="questions" id="question" placeholder="Выберите вопрос" name="questions[]" autocomplete="off">' +
            '<datalist id="questions" class="questionsDatalist">'+questionsToSelect()+'</datalist>'+
            '<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
        i++;
    });

    $(document).on('click','#remove', function (){
        var question_id = $(this).attr('name');
        $('.question'+question_id+'').remove();

    });

    $(document).on('input','#topic', function (ev) {
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
                $('.tests').append('<div class="innerTestsDiv"><input type="text" required list="tests" placeholder="Введите новый или выберите тест" id="test" name="test" autocomplete="off">' +
                    '<input hidden id="testId" name="testId" value="">'+
                    '<datalist id="tests" class="testsDatalist">');

                data.forEach(function(test) {
                    $('.testsDatalist').append('<option>'+test.description+'</option>');
                });

                $('.tests').append('</datalist></div>');
            }
        })
    });

    $(document).on('input','#test', function (ev) {
        $('.questions').append('<div class="innerQuestionsDiv"></div>');

        if ($(ev.target).val() == "") {
            $('.innerQuestionsDiv').remove();
        }

        testsData.forEach(function(test) {
            if ($(ev.target).val() == test.description) {
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