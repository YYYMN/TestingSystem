$('document').ready(function () {
    var a, b, c, per;
    a = jQuery('#right').html();
    b = jQuery('#wrong').html();
    a = parseInt(a);
    b = parseInt(b);
    c = a + b;
    per = (a * 100) / c;

    $('.progress-bar').circularProgress({
        color: "red",
        height: "120px",
        weight: "120px",
        percent: 0
    }).circularProgress('animate', per, 1500);
});


