function validateFirstNameFormat() {
    var $name = $('form input[id="firstname"]');
    var re = /^[a-zA-Z\s]*$/;
    if (!re.test($name.val())) {
        $(".firstname-error-messages").text("Name cannot contain numbers").fadeIn();
        buttonActive(false);
    } else {
        $(".firstname-error-messages").fadeOut();
        buttonActive(true);
    }
}

function validateLastNameFormat() {
    var $name = $('form input[id="lastname"]');
    var re = /^[a-zA-Z\s]*$/;
    if (!re.test($name.val())) {
        $(".lastname-error-messages").text("Name cannot contain numbers").fadeIn();
        buttonActive(false);
    } else {
        $(".lastname-error-messages").fadeOut();
        buttonActive(true);
    }
}

function validateEmailFormat() {
    var $email = $('form input[id="email"]');
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test($email.val())) {
        $(".email-error-messages").text("Incorrect email format").fadeIn();
        buttonActive(false);
    } else {
        $(".email-error-messages").fadeOut();
        buttonActive(true);
    }
}

function validatePasswordFormat() {
    var $password = $('form input[id="password"]');
    var re = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}/g;
    if (!re.test($password.val())) {
        $(".password-error-messages").text("Minimum length is 8 characters. Password must contain at least one uppercase, lowercase and number").fadeIn();
        buttonActive(false);
    } else {
        $(".password-error-messages").fadeOut();
        $("#singlebutton").removeAttr("disabled");
        buttonActive(true);
    }
}

function buttonActive(status) {
    if (status == false) {
        $("#singlebutton").attr("disabled", true);
    } else {
        $("#singlebutton").removeAttr("disabled");
    }
}
