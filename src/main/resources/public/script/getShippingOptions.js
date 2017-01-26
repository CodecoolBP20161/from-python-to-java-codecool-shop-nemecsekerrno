/**
 * Created by hamargyuri on 2017. 01. 25..
 */
$(document).ready(function(){
   $("#calculate-shipping").click(function() {
       sendToServer();
        // sendToServer($('#user-address').value)
   });
});

var sendToServer = function() {
    var address = $("#user-address").val();
    console.log(address);
    $.ajax({
        url: '/getshippingcost?address=' + address,
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