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

function add20Students() {
    console.log("add20Students(): ");
    setTimeout(function () {
        getStudentsList();
    }, 300);
    $.ajax({
        url: "/rest/add20Students",
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
                email: element.email,
                birthDate: element.birthDate,
                gradeList: parseGradeListIntoString(element.gradeList),
            });
        }
        $('#studentListResultWrapper').css("display", "block");
        $('#studentListResult').bootstrapTable('removeAll');
        $('#studentListResult').bootstrapTable('load', jsonArr);
    } else {
        $('#studentListResultWrapper').css("display", "none");
    }
}

function parseGradeListIntoString(gradeList) {
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
    const deleteButton = "<button type ='button' onClick='deleteStudentById(" + row.student_id + ")' data-toggle='modal'  data-target='#deleteModal' class='btn btn-warning mr-2'>delete student</button>";
    const goToProfileButton = "<a type ='button' href='../studentProfile/" + row.student_id + "' " +
        "class='btn btn-primary'>Student profile</a>";

    const modal = '<div class="modal fade" id="deleteModal" tabIndex="-1" role="dialog"><div class="modal-dialog" role="document">' +
        '<div class="modal-content"><div class="modal-header"><h5 class="modal-title" id="deleteModal">Are you sure want to delete user?</h5>' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>' +
        '</button></div><div class="modal-body">modal body</div><div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> ' +
        '<button type="button" class="btn btn-primary" onClick="deleteStudentById(' + row.student_id + ')" >Delete user</button></div></div></div></div>';

    return "<div class='student-table-details'><h5>Details:</h5></br><div class='student-table-details-row'>" + html.join('') + deleteButton + " " +
        goToProfileButton + modal + "</div>";
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
    }, 200);
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