let btn = $('#button-top');

$(window).scroll(function() {
    if ($(window).scrollTop() > 300) {
        btn.addClass('show');
    } else {
        btn.removeClass('show');
    }
});

btn.on('click', function(e) {
    e.preventDefault();
    $('html, body').animate({scrollTop:0}, '300');
});

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