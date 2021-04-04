// -----SUDENT-----
function jQueryTest() {
    $("#one").hide();
}

function getStudentsList() {
    console.log("getStudentsList()")
    $.ajax({
        url: "/getStudents",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
};

var person = {firstName: "John", lastName: "nazwisko", birthDate: ""};

function addStudent() {
    console.log("addStudent()");
    console.log("student: " + person);
    $.ajax({
        url: "/addStudent",
        contentType: "application/json",
        dataType: "json",
        data: person,
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}

function add5Students() {
    console.log("add5Students(): ");
    $.ajax({
        url: "/add5Students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
}

function postForm() {
    console.log("postForm()")
    const valid = validationInput();
    console.log("valid: " + valid);
}

function setDatePicker() {
    // $('.datepicker').datepicker();
}

/**
 * TODO naprawic validacje, w tej chwili nie czyszcza sie klasy przy ponowanym sprawdzeniu, moze dojsc do sytuacji ze input jest OK i NOK
 *
 */
function validationInput() {
    let result = true;
    let $firstName = $('#first-name');
    let $lastName = $('#last-name');
    let $email = $('#email');
    if ($firstName.val().length < 1) {
        $firstName.addClass('is-invalid');
        result = false;
    } else {
        $firstName.removeClass('is-invalid');
        $firstName.addClass('is-valid');
    }
    if ($lastName.val().length < 1) {
        $lastName.addClass('is-invalid');
        result = false;
    } else {
        $lastName.removeClass('is-invalid');
        $lastName.addClass('is-valid');
    }
    if ($email.val().length < 1) {
        $email.addClass('is-invalid');
        result = false;
    } else {
        $email.removeClass('is-invalid');
        $email.addClass('is-valid');
    }
    return result;
}