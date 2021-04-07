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
    let element = $("#id_student").val();
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

function addGrade() {
    let student_id = $("#id_student").val();
    let gradeValue = $("#id_student").val();
    let grade = {
        firstName: $firstName.val(),
        lastName: $lastName.val(),
        email: $email.val(),
        birthDate: $birthDate.val()
    };

    console.log("addGrade(): " + element);
    let url = "/rest/grade/" + element;
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: grade,
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}