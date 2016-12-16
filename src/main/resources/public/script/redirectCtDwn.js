$(document).ready(function(){
    var count = 9;
    setInterval(function(){
        $("#count").html(count--);
    }, 1000)
});