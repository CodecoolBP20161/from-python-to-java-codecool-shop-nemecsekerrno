/**
 * Created by berloc on 2017.01.24..
 */

$(document).ready(function() {
    $('.youtube-icon').on('click', function() {
        sendData(this);
    })
});

var sendData = function(button) {
    console.log("sdéjklfélkasd");
    var prodId = $(button).data("prod-id");
    console.log(prodId);
    $.ajax({
        url: '/getvideo/' + prodId,
        method: 'POST'
        // success:function(response){
    })

};



