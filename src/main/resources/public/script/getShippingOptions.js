/**
 * Created by hamargyuri on 2017. 01. 25..
 */
$(document).ready(function(){
   $("#calculate-shipping").click(function() {
       $("label").empty();
       sendToServer();
   });
});

var sendToServer = function() {
    var country = $('#user-country').val();
    var postcode = $('#user-postcode').val();
    var city = $("#user-city").val();
    var address = $("#user-address").val();
    $.ajax({
        url: '/getshippingcost?address=' + postcode +country + city + address,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $("#express").html("<input type=\"radio\" name=\"optradio\">" +
                data.expressCourier.details + " - " +
                data.expressCourier.timeInHours + " hours - " +
                data.expressCourier.cost + " " + data.expressCourier.currency +
                "</input>");
            $("#truck").html("<input type=\"radio\" name=\"optradio\">" +
                data.truck.details + " - " +
                data.truck.timeInHours + " hours - " +
                data.truck.cost + " " + data.truck.currency +
                "</input>");
            $("#truckhighway").html("<input type=\"radio\" name=\"optradio\">" +
                data.truckViaHighway.details + " - " +
                data.truckViaHighway.timeInHours + " hours - " +
                data.truckViaHighway.cost + " " + data.truckViaHighway.currency +
                "</input>");
            $("#timeMachine").html("<input type=\"radio\" name=\"optradio\">" +
                data.timeMachine.details + " - " +
                data.timeMachine.timeInHours + " hours - " +
                data.timeMachine.cost + " " + data.timeMachine.currency +
                "</input>");
        }
    })
};