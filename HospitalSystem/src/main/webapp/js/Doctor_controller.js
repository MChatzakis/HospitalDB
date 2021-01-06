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
    var personalDataTable[ data.name,data.surname,data.address,data.phone,data.at,data.type,data.username,data.email];
    for(var i=0; i<7; i++){
        var cell = row.insertCell(i);
        cell.innerHTML = personalDataTable[i];
    }
    

   
}
