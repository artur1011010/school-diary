// TODO get student details
function getStudentDetails(){
    let id_student = $('#id_student-details').val();
    if(!id_student){
        let profile_id = $('#profile_id').val();
        console.log("id_student: " + profile_id );
        id_student = profile_id;
    }
    console.log("getStudentDetails()");
    $.ajax({
        url: "/rest/student/" + id_student,
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            populateProfile(result);
        }
    })
}

function populateProfile(data) {
    console.log(data);
    populateGrades(data.gradeList);
    let $studentId = $('#id-details');
    let $firstName = $('#first-name-details');
    let $lastName = $('#last-name-details');
    let $email = $('#email-details');
    let $birthDate = $('#birth-date-details');
    $studentId.val(data.id);
    $firstName.val(data.firstName);
    $lastName.val(data.lastName);
    $email.val(data.email);
    $birthDate.val(data.birthDate);
}

function populateGrades(grades){
    let result = '';
    grades.forEach(grade=> result += parseGrade(grade));
    $('#grade-list').html(result);
}

function parseGrade(grade){
    let result = ''
    if(grade.gradeValue < 30){
        result =  '<span class="badge badge-danger">' + grade.subject + ':  ' +  grade.gradeValue + '</span></br>';
    }else if(grade.gradeValue >=30 && grade.gradeValue < 50 ){
        result =  '<span class="badge badge-warning">' + grade.subject + ':  ' +  grade.gradeValue + '</span></br>';
    }else if(grade.gradeValue >= 50 && grade.gradeValue < 70 ){
        result =  '<span class="badge badge-secondary">' + grade.subject + ':  ' +  grade.gradeValue + '</span></br>';
    }else if(grade.gradeValue >= 70){
        result =  '<span class="badge badge-success">Subject: ' + grade.subject + ':  ' +  grade.gradeValue + '</span></br>';
    }
    return result;
}
function addGradeProfile() {
    let id = $("#id_student-details").val();
    let subjectName = $("#add-grade-subject1").val();
    let gradeValue = $("#add-grade-value").val();
    let gradeDTO = {
        gradeValue: gradeValue,
        subjectName: subjectName
    }
    console.log("addGradeProfile(): " + id);
    console.log("grade " + gradeDTO);
    console.log("subject " + subjectName);
    const stringGrade = JSON.stringify(gradeDTO);
    let url = "/rest/grade/" + id;
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: stringGrade,
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
    setTimeout(function(){ getStudentDetails();}, 200);
}