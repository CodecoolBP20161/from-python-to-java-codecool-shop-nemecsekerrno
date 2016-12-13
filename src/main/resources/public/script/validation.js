
function validateEmailFormat() {
    var $email = $('form input[class="email');
    var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
    if ($email.val() == '' || !re.test($email.val())) {
        alert('Please enter a valid email address.');
        return false;
    }
}

function validateNameFormat() {
    var $name = $('form input[id="names');
    var re = /^[a-zA-Z\s]*$/;
    if ($name.val() == '' || !re.test($name.val())) {
        return false;
    }

}

function validatePasswordFormat() {
    var $password = $('form input[class="password"]');
    var re = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}/g;
    if ($password.val() == '' || !re.test($password.val())) {
        return false;
    }
}
