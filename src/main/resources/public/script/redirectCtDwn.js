$(document).ready(function(){
    var count = 10;
    setInterval(function(){
        $("#count").html(count--);
    }, 1000)
});