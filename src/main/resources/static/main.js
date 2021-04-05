function testMainJS(){
    console.log(" testMainJS()")
}

function addGrade(){
    console.log("addGrade()");
    let grade = {
        professor: "1",
        student: "1",
        subject: "subject - Matematyka",
        gradeValue: "50"
    };
    let student = {
        firstName: "Grade test",
        lastName: "Grade test",
        email: "Grade test @ mail",
        birthDate: "1990-05-05",
        gradeList: []
    };
    student.gradeList.push(grade);

    const stringStudent = JSON.stringify(student);

    console.log("student: " + stringStudent);
    // console.log("grade: " + stringGrade);
    $.ajax({
        url: "/addStudent",
        contentType: "application/json",
        dataType: "json",
        data: stringStudent,
        // data: grade,
        method: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}
testMainJS();


