// TODO zrobic działajacy przycisk powrotu do gory strony
// $("#upButton").click(function () {
//     $('html, body').animate({
//         scrollTop: $("#top-navbar").offset().top
//     }, 1000);
// });

// console.log("main.js")

function topSC(){
    console.log("topSC()");
    $('html, body').animate({
        scrollTop: $("#top-navbar").offset().top
    }, 1000);
    // document.documentElement.scrollTop = 0;
}
// mybutton = $("#upButton").click(topFunction());
// // When the user scrolls down 20px from the top of the document, show the button
// window.onscroll = function() {scrollFunction()};
//
// function scrollFunction() {
//     if (document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
//         mybutton.style.display = "block";
//     } else {
//         mybutton.style.display = "none";
//     }
// }
//
// // When the user clicks on the button, scroll to the top of the document
// function topFunction() {
//     console.log(" topFunction()")
//     $('#top-navbar').scrollTop = 0;
//     // document.body.scrollTop = 0; // For Safari
//     // document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
// }
// TODO get student details

function getStudentDetails(){
    let id_student = $('#id_student-details').val();
    console.log("sgetStudentDetails()");
    $.ajax({
        url: "/rest/student/" + id_student,
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            setData(result);
        }
    })
}

function setData(data) {
    console.log(data);
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