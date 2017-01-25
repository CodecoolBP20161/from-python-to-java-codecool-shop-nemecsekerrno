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
    $('.loading').show();
    var prodId = $(button).data("prod-id");
    var prodName = $().data("prod-name");
    console.log(prodName);
    $('.modal-header').html("Review of " + prodName);
    $.ajax({
        url: '/getvideo?id=' + prodId,
        method: 'GET',
        dataType: "text",
        success: function (data) {
            $('.loading').hide();
            $('.modal-body').html(data);
            $('.modal-close').on('click', function () {
                stopVideo($('.video'));
            });
        }
    })

};

var stopVideo = function(player) {
    var vidSrc = player.prop('src');
    player.prop('src', ''); // to force it to pause
    player.prop('src', vidSrc);
};



