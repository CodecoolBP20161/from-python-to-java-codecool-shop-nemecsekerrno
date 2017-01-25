/**
 * Created by hamargyuri on 2017. 01. 25..
 */
$(document).ready(function(){
   $("#calculate-shipping").click(function() {
       var userInput = $("#user-address").val();
       console.log(userInput);
        // sendToServer($('#user-address').value)
   });
});

var sendToServer = function(address) {

}