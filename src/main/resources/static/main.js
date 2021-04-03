function testButton(){
    console.log("button test");
}

// -----SUDENT-----
function jQueryTest (){
    $("#one").hide();
}

function getStudentsList() {
    $.ajax({
        url: "/getStudents",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
};

function addStudents(){
    $.ajax({
        url: "/getStudents",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
}
