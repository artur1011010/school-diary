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