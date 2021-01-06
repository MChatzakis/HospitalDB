var GET_PERSONAL_AND_DRUGS = 1;
var GET_DUTIES = 2;
var GET_MEDICALS = 3;
var GET_EXAMINATIONS = 4;
var GET_RE_EXAMINATIONS = 5;
var GET_PATIENTS = 6;

var currentDutyTime = 0;
var url = "http://localhost:8080/HospitalSystem/DoctorServlet";

$(document).ready(function () {
    console.log('Document ready -- Getting the initial information');
    formData = "requestID=" + GET_PERSONAL_AND_DRUGS;
    sendXmlForm(url, formData, GET_PERSONAL_AND_DRUGS);

});

function showPersonal() {
    console.log('Inside show personal');
    var d = document.getElementById('personalButton');
    var e = document.getElementById('personalTable');
    var f = document.getElementById('personalDuties');

    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        f.style.display = 'block';
        d.innerHTML = 'Hide Personal Info';
    } else {
        e.style.display = 'none';
        f.style.display = 'none';
        d.innerHTML = 'Show Personal Info';
    }
}

function showDrugs() {
    var d = document.getElementById('drugButton');
    var e = document.getElementById('drugTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Drugs and Illnesses';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Drugs and Illnesses';
    }
}

function showPatients() {
    var d = document.getElementById('patientsButton');
    var e = document.getElementById('patientsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Current Patients';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Patients';
    }
}

function sendXmlForm(url, formData, req_id) {
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (req_id === GET_PERSONAL_AND_DRUGS) {
                callBackFillPersonalData(request);
                callBackFillDuties(request);
                callBackFillDrugsAndIllnesses(request);
            }
        }
    }
    ;
    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);
}

function callBackFillDuties(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('personalDuties');
    var dt = "duty";
    var total = data.dutytimes;
    console.log("Times: " + total);
    for (var i = 0; i < total; i++) {
        var row = table.insertRow((i + 1));
        var cell = row.insertCell(0);
        cell.innerHTML = data[dt + "" + i];
    }
}

function callBackFillPersonalData(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('personalTable');
    var row = table.insertRow(1);
    var dataTable = [data.name, data.surname, data.address, data.phone, data.at, data.type, data.username, data.email];
    for (var i = 0; i < 8; i++) {
        var cell = row.insertCell(i);
        cell.innerHTML = dataTable[i];
    }
}

function callBackFillDrugsAndIllnesses(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('drugTable');
    var dataTable = ["drug_id", "drug_name", "drug_type", "dosage", "illness_id", "illness_name"];
    var counter = 0;
    for (var j = 1; j <= 5; j++) {
        row = table.insertRow(j);
        for (var i = 0; i < 6; i++) {
            var cell = row.insertCell(i);
            cell.innerHTML = data[dataTable[i] + "" + counter];
        }
        counter++;
    }
}
