/**
 * Created by berloc on 2017.01.24..
 */

$(document).ready(function() {
    $('#youtube-icon').click(sendData)
    });

var sendData = function() {
    console.log("sdéjklfélkasd");
    var prodId = $('#youtube-icon').data("prod-id");
    console.log(prodId);
    $.ajax({
    url: '/getvideo/' + prodId,
    method: 'POST'
    // success:function(response){
    })

};



