function getStudentsList() {
    console.log("getStudentsList()")
    $.ajax({
        url: "/rest/students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
            populateStudentsList(result);
            console.log(result);
        }
    })
}

function add5Students() {
    console.log("add5Students(): ");
    setTimeout(function () {
        getStudentsList();
    }, 300);
    $.ajax({
        url: "/rest/add5Students",
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
    const deleteButton = "<button type ='button' onClick='deleteStudentById(" + row.student_id + ")' class='btn btn-warning mr-2'>delete student</button>";
    const goToProfileButton = "<a type ='button' href='/studentProfile/" + row.student_id + "' " +
    "class='btn btn-primary'>student profile</a>";
    return "<div class='student-table-details'><h5>Details:</h5></br><div class='student-table-details-row'>" + html.join('') + deleteButton + " " + goToProfileButton + "</div>";
}

/**
 * TODO
 * usunać time out, przerobic to tak zeby po odpowiedzi serwera odswiezalo tabele
 */
function deleteStudentById(student_id) {
    console.log("deleteButton(element):  " + student_id);
    let url = "/rest/student/" + student_id;
    setTimeout(function () {
        getStudentsList();
    }, 50);
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