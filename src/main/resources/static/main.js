function testButton() {
    console.log("button test");
}

// -----SUDENT-----
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
            console.log(result);
        }
    })
};

const student = {
    "firstName": "Pierwsze imie",
    "lastName": "nazwisko"
}


var person = {firstName:"John", lastName:"nazwisko", birthDate:""};

function addStudent() {
    console.log("addStudent()");
    console.log("student: " + person);
    $.ajax({
        url: "/addStudent",
        contentType: "application/json",
        dataType: "json",
        data: person,
        method : "POST",
        success: function (result) {
            console.log(result);
        }
    })
}


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
