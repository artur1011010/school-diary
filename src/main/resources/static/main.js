function scriptTest(){
    console.log(" scriptTest()");
}
scriptTest();

function testButton(){
    console.log("button test");
}

function search() {
    $.ajax({
        url: "/getStudents",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result);
        }
    })
};

function jQueryTest (){
    $("#one").hide();
}