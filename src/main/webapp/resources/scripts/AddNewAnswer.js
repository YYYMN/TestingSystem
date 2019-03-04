$(document).ready(function(){
    var i=2;
    $('#add').click(function(){
        $('#dynamic_field').append('<input type="text" id="question' + i + ' " placeholder = " ' + i + '. Ответ"><br>');
        i++;
    });
});

