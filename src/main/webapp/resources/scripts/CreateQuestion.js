$(document).ready(function(){
    var i = 1;

    // Если ни один чекбокс не выбран, кнопка "Сохранить" не активна
    $(document).on('click','input[type="checkbox"]', function () {
        if ($('input[type="checkbox"]:checkbox:checked').length == 0) {
            $('#save').attr('disabled', true);
        }else $('#save').removeAttr('disabled');
    });



    // Потягивание ответов на вопрос, который уже есть в БД
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
                        '<input type="text" required name="answer[]" id="answer' + i + '" value="'+data[j].description+'" autocomplete="off">' +
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

    //Добавление поля, для создания нового ответа
    $(document).on('click','#add',function(){
        $('#dynamic_field').append('<div class="div'+i+'">' +
            '<input type="text" required name="answer[]" id="answer' + i + '" placeholder ="Ответ" autocomplete="off">' +
            '<input type="hidden" name="checkbox_option" class="checkbox'+i+'" value="false">'+
            '<input type="checkbox" class="checkbox'+i+'" data-correct="0" name="checkbox" value="false">'+
            '<button type="button" name="'+i+'" id="remove" class="remove">Удалить</button><br></div>');
        i++;
    });

    /*Удаление поля для ответа
    если пустое - просто удаляется div, в котором оно находится
    если подтянутое из БД - удаляется сразу из БД(не круто, могут быть траблы)
    */
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

    // Костыль для возможности создать массив из значений чекбокса, ибо JS сосет
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