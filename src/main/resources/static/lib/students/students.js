// -----student list-----
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
            populateStudentsList(result);
            console.log(result);
        }
    })
};

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

function populateStudentsList(input) {
    if (input.length > 0) {
        let jsonArr = [];
        for (let element of input) {
            jsonArr.push({
                student_id: element.student_id,
                firstName: element.firstName,
                lastName: element.lastName,
                birthDate: element.birthDate,
            });
        }
        $('#studentListResultWrapper').css("display", "block");
        $('#studentListResult').bootstrapTable('removeAll');
        $('#studentListResult').bootstrapTable('load', jsonArr);
    }
}

function idSorter(a, b) {
    return a - b
}

function nameSorter(a, b) {
    return a.localeCompare(b);
}


//----Add user ----

/**
 * TODO naprawic validacje, w tej chwili nie czyszcza sie klasy przy ponowanym sprawdzeniu, moze dojsc do sytuacji ze input jest OK i NOK
 *
 */
function validationInput() {
    let result = true;
    let $firstName = $('#first-name');
    let $lastName = $('#last-name');
    let $email = $('#email');
    let $birthDate = $('#birth-date');
//TODO - sprobowac to zrobic krocej
    $firstName.removeClass('is-invalid');
    $firstName.removeClass('is-valid');
    $lastName.removeClass('is-invalid');
    $lastName.removeClass('is-valid');
    $email.removeClass('is-invalid');
    $email.removeClass('is-valid');
    $birthDate.removeClass('is-invalid');
    $birthDate.removeClass('is-valid');
    //
    if ($firstName.val().length < 1) {
        $firstName.addClass('is-invalid');
        result = false;
        console.log("result $firstName: " + result);
    } else {
        $firstName.addClass('is-valid');
    }
    if ($lastName.val().length < 1) {
        $lastName.addClass('is-invalid');
        result = false;
        console.log("result $lastName: " + result);
    } else {
        $lastName.addClass('is-valid');
    }
    if ($email.val().length < 1) {
        $email.addClass('is-invalid');
        result = false;
        console.log("result $email: " + result);
    } else {
        $email.addClass('is-valid');
    }
    if ($birthDate.val().length !== 10) {
        $birthDate.addClass('is-invalid');
        result = false;
        console.log("result $birthDate: " + result);
    } else {
        $birthDate.addClass('is-valid');
    }
    return result;
}

function addStudent() {

    if (!validationInput()) {
        console.log("validationInput: " + validationInput());
        return;
    }
    let $firstName = $('#first-name');
    let $lastName = $('#last-name');
    let $email = $('#email');
    let $birthDate = $('#birth-date');
    let student = {firstName: $firstName.val(), lastName:$lastName.val(), email: $email.val(), birthDate: $birthDate.val()};
    const stringStudent = JSON.stringify(student);
    console.log("addStudent()");
    console.log("student: " + stringStudent);
    $.ajax({
        url: "/addStudent",
        contentType: "application/json",
        dataType: "json",
        data: stringStudent,
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}


function testDatePicker() {
    let value = $("#birth-date").val();
    console.log("testDatePicker(), value: " + value);
}

function setDatePicker() {
    // $('.datepicker').datepicker();
}