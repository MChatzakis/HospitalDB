/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//defines

var INFORMATION_ID = 1
var MEDICAL_ID = 2
var Clinical_ID = 3
var VISIT_ID = 4
var FILL_INFORMATION_ID = 5


//var url = "https://webhook.site/93afe500-82d2-464c-a89e-2d1b318b0140";





$(document).ready(function () {

    FillForm();

});


function FillMedical() {

    formData = "requestID=" + MEDICAL_ID;
    // HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, MEDICAL_ID);

}
function  FillClinical()
{
    formData = "requestID=" + Clinical_ID;
    // HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, Clinical_ID);

}
function FillVisits() {
    formData = "requestID=" + VISIT_ID;
    // HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, VISIT_ID);

}

function FillForm() {

    formData = "requestID=" + FILL_INFORMATION_ID;
    // HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, FILL_INFORMATION_ID);



}
function CallBackFillForm(data)
{    
    var data = JSON.parse(data.responseText);
    $('input[name=fname]').attr('value', data.name)
    $('input[name=surname]').attr('value', data.surname)
    $('input[name=username]').attr('value', data.username)
    $('input[name=adress]').attr('value', data.address)
    $('input[name=email]').attr('value', data.email)
    $('input[name=phone]').attr('value', data.phone)
    $('input[name=birth_day]').attr('value', data.birth_day)
    $('input[name=amka]').attr('value', data.amka)
    $('input[name=at]').attr('value', data.at)
    $('input[name=insurance]').attr('value', data.insurance)
}

function CallBackFillMedical(data) {

}
function CallBackFillClinical(data) {
    

}
function CallBackFillVisits(data) {
    
}












function SendXmlForm(url, formData, req_id)
{

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200)
        {
            // Typical action to be performed when the document is ready:
            // console.log("lalal : " +request.responseText) ;
            //if formdata == FILL_INFORMATION_ID
            if (req_id === FILL_INFORMATION_ID)
            {
                CallBackFillForm(request);

            } else if (req_id === MEDICAL_ID)
            {
                CallBackFillMedical(request);
            } else if (req_id === Clinical_ID) {
                CallBackFillMedical(request)
            } else
            {
                CallBackFillVisits(request);
            }

        }
    };
    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);

}


function SaveForm() {
    var formData = {
        'requestID': INFORMATION_ID,
        'fname': $('input[name=fname]').val(),
        'surname': $('input[name=surname]').val(),

        'username': $('input[name=username]').val(),
        'adress': $('input[name=adress]').val(),
        'email': $('input[name=email]').val(),
        'phone': $('input[name=phone]').val(),
        'birth_day': $('input[name=birth_day]').val(),
        'amka': $('input[name=amka]').val(),
        'at': $('input[name=at]').val(),
        'insurance': $('input[name=insurance]').val(),

    };
    // SendForm(url, formData, "#form-id")
}

function SendForm(url, formData, id) {

    console.log("senddd")
    // process the form
    $(id).submit(function (event) {
        event.preventDefault();
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        /* var formData = $("form").formSerialize();*/



        // process the form
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: url, // the url where we want to POST
            data: formData, // our data object
            dataType: 'json', // what type of data do we expect back from the server
            success: function (results) {
                return results;
            }
        })
                // using the done promise callback
                .done(function (data) {

                    // log data to the console so we can see
                    console.log(data);

                    // here we will handle errors and validation messages
                });


    });
    //window.location.reload();

}







function ShowClinical() {
    console.log("lalala");

    if ($('#clinical').hasClass('d-none')) {
        $("#clinical").removeClass('d-none');
        AddRow("clinical_rows")
    } else {
        $("#clinical").addClass('d-none');

    }

}

function HideClinical() {
    console.log("lalala");
    $("#clinical").addClass('d-none');
}


function ShowVisits() {
    console.log("lalala");

    if ($('#visit').hasClass('d-none')) {
        $("#visit").removeClass('d-none');

    } else {
        $("#visit").addClass('d-none');

    }

}


function HideVisit() {
    console.log("lalala");
    $("#medical").addClass('d-none');
}


function ShowMedical() {
    console.log("lalala");

    if ($('#medical').hasClass('d-none')) {
        $("#medical").removeClass('d-none');

    } else {
        $("#medical").addClass('d-none');

    }

}


function HideMedical() {
    console.log("lalala");
    $("#form").addClass('d-none');
}

function ShowInformation() {
    console.log("lalala");

    if ($('#form').hasClass('d-none')) {
        $("#form").removeClass('d-none');

    } else {
        $("#form").addClass('d-none');

    }

}


function HideInformation() {
    console.log("lalala");
    $("#form").addClass('d-none');
}



function AddRow(id) {
    var table = document.getElementById(id);
    var row = table.insertRow(0);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = "new cell";
    cell2.innerHTML = "new cell";
}


