/**
 * Created by hamargyuri on 2017. 01. 25..
 */
$(document).ready(function(){
   $("#calculate-shipping").click(function() {
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
            console.log(data);
            console.log(data.expressCourier);
            console.log(data.truck);
            console.log(data.truckViaHighway);
            console.log(data.timeMachine);
            console.log(data.timeMachine.details)
        }
        // error: function(request, errorType, errorMessage) {
        //     alert('Error: ' + errorMessage);
        // }
    })
};