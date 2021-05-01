function getStudentDetails() {
    let id_student = $('#id_student-details').val();
    if (!id_student) {
        let profile_id = $('#profile_id').html();
        id_student = profile_id;
    }
    $.ajax({
        url: "/rest/student/" + id_student,
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            // console.log(JSON.stringify(result));
            populateProfile(result);
        }
    })
}

function populateProfile(data) {
    let $studentId = $('#id-details');
    let $firstName = $('#first-name-details');
    let $lastName = $('#last-name-details');
    let $email = $('#email-details');
    let $birthDate = $('#birth-date-details');
    // todo - prevent undefined error
    populateGrades(data.gradeList);
    $studentId.val(data.id);
    $firstName.val(data.firstName);
    $lastName.val(data.lastName);
    $email.val(data.email);
    $birthDate.val(data.birthDate);
}

function populateGrades(grades) {
    const $gradeListEmpty = $('#grade-list-empty');
    const $gradeList = $('#grade-list');
    if (grades === undefined || grades.length === 0) {
        $gradeListEmpty.css("display", "block");
    } else {
        $gradeListEmpty.css("display", "none");
        $gradeList.html('');
        $gradeList.html(parseGradeToTable(grades));
    }
}

function parseGradeToTable(grades) {
    let result = '';
    const tableHeader = '<table class="table"><thead><tr><th scope="col" class="col-3">Subject:</th><th scope="col" class="col-9">Grades:</th></tr></thead><tbody>';
    const tableEnding = ' </tbody></table>';
    let rows = [], subjects = [];

    for (let i = 0; i < grades.length; i++) {
        const tempSubject = grades[i].subject;
        const index = subjects.indexOf(tempSubject);
        if (index === -1) {
            subjects.push(tempSubject);
            // console.log(subjects.indexOf(tempSubject));
            rows[subjects.indexOf(tempSubject)] += ' <tr><th scope="row">' + tempSubject + '</th><td>' + parseGrade(grades[i]);
        } else {
            rows[index] += parseGrade(grades[i]);
        }
    }
    result += tableHeader;
    rows.forEach(row => result += (row + '</td></tr>'));
    result += tableEnding;
    // console.log(result);
    return result;
}

function parseGrade(grade) {
    let result = ''
    if (grade.gradeValue < 30) {
        result = '<span class="badge badge-danger badge-grade">' + grade.gradeValue + '</span>';
    } else if (grade.gradeValue >= 30 && grade.gradeValue < 50) {
        result = '<span class="badge badge-warning badge-grade">' + grade.gradeValue + '</span>';
    } else if (grade.gradeValue >= 50 && grade.gradeValue < 70) {
        result = '<span class="badge badge-secondary badge-grade">' + grade.gradeValue + '</span>';
    } else if (grade.gradeValue >= 70) {
        result = '<span class="badge badge-success badge-grade">' + grade.gradeValue + '</span>';
    }
    return result;
}

function addGradeProfile() {
    let id = $("#id_student-details").val();
    if (!id) {
        id = $("#profile_id").html();
    }
    let subjectName = $("#add-grade-subject1").val();
    let gradeValue = $("#add-grade-value").val();
    let gradeDTO = {
        gradeValue: gradeValue,
        subjectName: subjectName
    }
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
    setTimeout(function () {
        getStudentDetails();
    }, 200);
}
