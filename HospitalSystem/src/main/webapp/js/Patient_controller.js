/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//defines

var FILL_HISTORY_ID = 1
var UPDATE_FORM_ID = 2;
var FILL_DISEASES_ID = 3;
var FILL_INFORMATION_ID = 5


//var url = "https://webhook.site/93afe500-82d2-464c-a89e-2d1b318b0140";





$(document).ready(function () {

    FillForm();
    FillDisease();
    FillHistory();

});


function FillDisease()
{
    formData = "requestID=" + FILL_DISEASES_ID;
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"
    SendXmlForm(url, formData, FILL_DISEASES_ID);

}
function FillHistory() {
    //formData = "requestID=" + FILL_HISTORY_ID;
    formData = "requestID=" + FILL_HISTORY_ID;


// HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, FILL_HISTORY_ID);

}


function FillForm() {

    formData = "requestID=" + FILL_INFORMATION_ID;
    // HTML file input, chosen by user
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    SendXmlForm(url, formData, FILL_INFORMATION_ID);

}
function CallBackFillDiseases(data)
{
    var data = JSON.parse(data.responseText);
    var length = Object.keys(data).length;
    console.log("length : " + length);
    var table = document.getElementById("diseases-table");
    var i = 0;
    for (var x in data)
    {
        var row = table.insertRow(i+1);

        var cell = row.insertCell(0);
        cell.innerHTML = data[x];
        i++;
    }


}
function CallBackFillForm(data)
{
    var data = JSON.parse(data.responseText);
    $('input[name=fname]').attr('value', data.name);
    $('input[name=surname]').attr('value', data.surname);
    $('input[name=username]').attr('value', data.username);
    $('input[name=address]').attr('value', data.address);
    $('input[name=email]').attr('value', data.email);
    $('input[name=phone]').attr('value', data.phone);
    $('input[name=birth_day]').attr('value', data.birth_day);
    $('input[name=amka]').attr('value', data.amka);
    $('input[name=at]').attr('value', data.at);
    $('input[name=insurance]').attr('value', data.insurance);
}

function CallBackFillHistory(data)
{
    var obj_symptoms;
    var symptoms = "";
    var data = JSON.parse(data.responseText);
    for (var i = 0; i < data[0].length; i++)
    {
        var obj = data[0][i];
        console.log(obj)
        for (var j = 0; j < data[1].length; j++)
        {
            //console.log("j : " + j)
            obj_symptoms = data[1][j];
            // console.log(obj_symptoms.visit);

            if (obj.visit === obj_symptoms.visit)
            {
                console.log(obj_symptoms.symptom);
                if (symptoms === "")
                {
                    symptoms += obj_symptoms.symptom

                } else {
                    symptoms += ", " + obj_symptoms.symptom

                }
                console.log(symptoms);
            }
        }
        AddRow("history-table", 6, i + 1, obj, symptoms);
        symptoms = "";
        //console.log(obj.visit);
        /*
         console.log(obj.date);
         console.log(obj.illness);
         console.log(obj.drug);
         console.log(obj.medical);
         console.log(obj.hospitalization);*/


    }
    //console.log(data);

}

function AddRow(id, cells, row, obj, symptoms)
{
    console.log("i add row" + row)
    var table = document.getElementById(id);
    var row = table.insertRow(row);
    var i = 0;
    for (var x in obj)
    {

        if (x !== "visit") {
            var cell = row.insertCell(i);
            cell.innerHTML = obj[x];
            i++;

        }


    }
    var cell = row.insertCell(i);
    cell.innerHTML = symptoms;
}

function SendXmlForm(url, formData, req_id)
{

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status == 200)
        {
            // Typical action to be performed when the document is ready:
            // console.log("lalal : " +request.responseText) ;
            //if formdata == FILL_INFORMATION_ID
            if (req_id === FILL_INFORMATION_ID)
            {
                CallBackFillForm(request);

            } else if (req_id === FILL_HISTORY_ID) {
                CallBackFillHistory(request);

            } else if (req_id === FILL_DISEASES_ID)
            {
                CallBackFillDiseases(request);

            }


        }
    };
    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);

}


function SaveForm() {
    var formData = {
        'requestID': UPDATE_FORM_ID,
        'fname': $('input[name=fname]').val(),
        'surname': $('input[name=surname]').val(),

        'username': $('input[name=username]').val(),
        'address': $('input[name=address]').val(),
        'email': $('input[name=email]').val(),
        'phone': $('input[name=phone]').val(),
        'birth_day': $('input[name=birth_day]').val(),
        'amka': $('input[name=amka]').val(),
        'at': $('input[name=at]').val(),
        'insurance': $('input[name=insurance]').val(),

    };
    var url = "http://localhost:8080/HospitalSystem/PatientServlet"

    console.log(formData);
    SendForm(url, formData, "#form-id")
}

function SendForm(url, formData, id) {

    console.log("senddd")
    // process the form
    $(id).submit(function (event) {
        //event.preventDefault();
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        /* var formData = $("form").formSerialize();*/



        // process the form
        $.ajax({
            method: "post",
            type: "post", // define the type of HTTP verb we want to use (POST for our form)
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




function ShowVisits() {
    //console.log("lalala");

    if ($('#history-table').hasClass('d-none')) {
        $("#history-table").removeClass('d-none');

    } else {
        $("#history-table").addClass('d-none');

    }

}



function ShowDiseases()
{
    //console.log("lalala");

    if ($('#diseases-table').hasClass('d-none')) {
        $("#diseases-table").removeClass('d-none');

    } else {
        $("#diseases-table").addClass('d-none');

    }

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






