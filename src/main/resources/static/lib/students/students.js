function getStudentsList() {
    console.log("getStudentsList()")
    $.ajax({
        url: "/rest/students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            populateStudentsList(result);
            console.log(result);
        }
    })
}

function add5Students() {
    console.log("add5Students(): ");
    setTimeout(function(){ getStudentsList(); }, 200);
    $.ajax({
        url: "/rest/add5Students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
}

function addGradeRest() {
    console.log("addGradeRest(): ");
    $.ajax({
        url: "/rest/addGradeRest",
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}

function addSecondGradeRest() {
    console.log("addSecondGradeRest(): ");
    $.ajax({
        url: "/rest/addSecondGradeRest",
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}

function addGradeToStudentById() {
    let element = $("#id_studenta").val();
    // /addGrade/{student}"
    console.log("addGradeToStudentById(): " + element);
    let url = "/rest/addGrade/" + element;
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        method: "POST",
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
                student_id: element.id,
                firstName: element.firstName,
                lastName: element.lastName,
                birthDate: element.birthDate,
                gradeList: parseGradeListintoString(element.gradeList),
            });
        }
        $('#studentListResultWrapper').css("display", "block");
        $('#studentListResult').bootstrapTable('removeAll');
        $('#studentListResult').bootstrapTable('load', jsonArr);
    } else {
        $('#studentListResultWrapper').css("display", "none");
    }
}

function parseGradeListintoString(gradeList) {
    let resultString = 'grade list: ';
    gradeList.forEach(grade => resultString += ("\t \n [" + grade.gradeValue + "], "))
    return resultString;
}

function idSorter(a, b) {
    return a - b
}

function nameSorter(a, b) {
    return a.localeCompare(b);
}

function detailFormatter(index, row) {
    let html = []
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':  </b>' + value + '</p>')
    })
    let deleteButton = "<button type ='button' onClick='deleteStudentById("+ row.student_id + ")' className='custom-button delete-button'>delete student</button>"
    return "<div class='student-table-details'><h5>Details:</h5></br><div class='student-table-details-row'>" + html.join('') + deleteButton + "</div>";
}

/**
 * TODO
 * usunać time out, przerobic to tak zeby po odpowiedzi serwera odswiezalo tabele
 */
function deleteStudentById(student_id) {
    console.log("deleteButton(element):  " + student_id);
    let url = "/rest/student/" + student_id;
    setTimeout(function(){ getStudentsList(); }, 50);
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        method: "DELETE",
        success: function (result) {
            console.log(result);
        }
    })
}

//----Add user ----

function validationInput() {
    let result = true;
    const $firstName = $('#first-name');
    const $lastName = $('#last-name');
    const $email = $('#email');
    const $birthDate = $('#birth-date');
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
    if (($email.val().length < 1) || !validateEmail($email.val())) {
        $email.addClass('is-invalid');
        result = false;
        console.log("result $email: " + result);
    } else {
        $email.addClass('is-valid');
    }
    if (!validateDate($birthDate.val())) {
        $birthDate.addClass('is-invalid');
        console.log("result $birthDate: " + result);
        result = false;
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
    let student = {
        firstName: $firstName.val(),
        lastName: $lastName.val(),
        email: $email.val(),
        birthDate: $birthDate.val()
    };
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

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validateDate(dateString) {
    if (dateString.length !== 10) {
        return false;
    }
    // Parse the date parts to integers
    const parts = dateString.split("-");
    const year = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10);
    const day = parseInt(parts[2], 10);
    // Check the ranges of month and year
    if (year < 1960 || year > 2005 || month === 0 || month > 12)
        return false;
    const monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    // Adjust for leap years
    if (year % 400 === 0 || (year % 100 !== 0 && year % 4 === 0))
        monthLength[1] = 29;
    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
}