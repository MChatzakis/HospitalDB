var GET_INITIAL_INFO = 1;
var currentDutyTime = 0;
var url = "http://localhost:8080/HospitalSystem/DoctorServlet";

$(document).ready(function () {
    console.log('Document ready -- Getting the initial information');
    formData = "requestID=" + GET_INITIAL_INFO;
    sendXmlForm(url, formData, GET_INITIAL_INFO);
});

function showPersonal() {
    console.log('Inside show personal');
    var d = document.getElementById('personalButton');
    var e = document.getElementById('personalTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Personal Info';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Personal Info';
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

function fillPersonalTable() {
    console.log('Inside fill personal data');
    var table = document.getElementById('personalTable');
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = "test1";
    cell2.innerHTML = "test2";
}

function sendXmlForm(url, formData, req_id) {
    var request = new XMLHttpRequest();
    console.log('XML');
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log('Inside if');
            if (req_id === GET_INITIAL_INFO) {
                console.log('Sending XML form for initial tables')
                CallBackFillInitialTables(request);
            }
        }
    }
    ;

    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);

}

function CallBackFillInitialTables(request) {
    console.log('Inside call back fill');
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('personalTable');
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = data.name;
    cell2.innerHTML = data.surname;
    //$('input[name=fname]').attr('value', data.name);
    /*$('input[name=surname]').attr('value', data.surname);
     $('input[name=username]').attr('value', data.username);
     $('input[name=adress]').attr('value', data.address);
     $('input[name=email]').attr('value', data.email);
     $('input[name=phone]').attr('value', data.phone);
     $('input[name=birth_day]').attr('value', data.birth_day);
     $('input[name=amka]').attr('value', data.amka);
     $('input[name=at]').attr('value', data.at);
     $('input[name=insurance]').attr('value', data.insurance);*/
}