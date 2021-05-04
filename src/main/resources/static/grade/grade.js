function addGradeToStudentById() {
    let element = $("#id_student").val();
    // console.log("addGradeToStudentById(): " + element);
    let url = "/rest/addGrade/" + element;
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (result) {
            console.log(result.status);
        },
        complete: function(xhr, textStatus) {
            if(xhr.status ===202){
                getStudentDetails();
            }
        }
    })
}
// TODO - deprecated
function addGrade() {
    let student_id = $("#id_student").val();
    let gradeValue = $("#id_student").val();
    let grade = {
        firstName: $firstName.val(),
        lastName: $lastName.val(),
        email: $email.val(),
        birthDate: $birthDate.val()
    };
    console.log("++++++++++++++++++++ ADD GRADE ++++++++++++++++++++")
    console.log(grade.firstName)
    console.log(grade.lastName)
    console.log(grade.birthDate)
    console.log(grade.email)
    let url = "/rest/grade/" + element;
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: grade,
        method: "POST",
        success: function (result) {
            console.log(result.status);
        },
        complete: function(xhr, textStatus) {
            if(xhr.status ===202){
            }
        }
    })
}