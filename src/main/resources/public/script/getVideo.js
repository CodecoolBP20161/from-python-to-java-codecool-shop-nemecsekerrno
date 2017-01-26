/**
 * Created by berloc on 2017.01.24..
 */

$(document).ready(function() {
    $('.youtube-icon').on('click', function () {
        sendData(this);
    });
});

    var sendData = function (button) {
        $('.loading').show();
        var prodId = $(button).data("prod-id");
        var prodName = $(button).data("prod-name");
        $('.modal-header').html("Review of " + prodName);
        $.ajax({
            url: '/getvideo?id=' + prodId,
            method: 'GET',
            dataType: "text",
            success: function (data) {
                $('.loading').hide();
                $('.video-body').html(data);
            },
            error: function (request, errorType, errorMessage) {
                $('.loading').hide();
                $('.video-body').html(errorMessage);
            },
            complete: function () {
                $('.modal-close').on('click', function () {
                    $('.video-body').empty();
                });
            }
        });
    };

