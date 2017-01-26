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
            console.log("kiskutyafasza");
            console.log(data);
            console.log(JSON.stringify(data));
        },
        // error: function(request, errorType, errorMessage) {
        //     alert('Error: ' + errorMessage);
        // }
    })

};