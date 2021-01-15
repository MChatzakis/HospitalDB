//  @author Manos Chatzakis (chatzakis@ics.forth.gr)


var url = "http://localhost:8080/HospitalSystem/WorkerServlet";
var GET_PERSONAL_AND_DRUGS = 1;
var GET_CURRENT_PATIENTS = 2;
var GET_CURRENT_STUFF = 3;
var GET_DOCTORS_NURSES_WORKERS = 4;
var GET_ALL_PATIENTS = 5;
var GET_ALL_EXAMS = 6;
var GET_ALL_VISITS = 7;
var GET_ALL_DUTIES = 8;
var ADD_PATIENT = 9;
var ADD_VISIT = 10;
var ADD_DUTY = 11;
var ADD_DOCTOR = 12;
var ADD_NURSE = 13;
var ADD_WORKER = 14;
var SEND_S_QUERY = 15;
var SEND_U_QUERY = 16;
var ADD_DEFAULT_DUTY = 17;

var flag = 0;

$(document).ready(function () {
    $('input[name="daterange"]').daterangepicker({
    }, function (start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        var from = start.format('YYYY-MM-DD');
        var to = end.format('YYYY-MM-DD');
        formData = "requestID=" + GET_PERSONAL_AND_DRUGS;
        formData += "&from=" + from;
        formData += "&to=" + to;
        flag = 1;
        sendXmlForm(url, GET_PERSONAL_AND_DRUGS, formData);

    });
    formData = "requestID=" + GET_PERSONAL_AND_DRUGS;
    sendXmlForm(url, GET_PERSONAL_AND_DRUGS, formData);

    formData = "requestID=" + GET_CURRENT_PATIENTS;
    sendXmlForm(url, GET_CURRENT_PATIENTS, formData);

    formData = "requestID=" + GET_CURRENT_STUFF;

    sendXmlForm(url, GET_CURRENT_STUFF, formData);

    formData = "requestID=" + GET_DOCTORS_NURSES_WORKERS;

    sendXmlForm(url, GET_DOCTORS_NURSES_WORKERS, formData);

    formData = "requestID=" + GET_ALL_PATIENTS;

    sendXmlForm(url, GET_ALL_PATIENTS, formData);

    formData = "requestID=" + GET_ALL_EXAMS;

    sendXmlForm(url, GET_ALL_EXAMS, formData);

    formData = "requestID=" + GET_ALL_VISITS;

    sendXmlForm(url, GET_ALL_VISITS, formData);
    formData = "requestID=" + GET_ALL_DUTIES;

    sendXmlForm(url, GET_ALL_DUTIES, formData);
    
    document.getElementById('v_date').valueAsDate = new Date();

});
function showPersonal() {
    var d = document.getElementById('personalButton');
    var e = document.getElementById('personalTable');
    var f = document.getElementById('personalDuties');
    var g = document.getElementById('daterange');

    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        f.style.display = 'block';
        g.style.display = 'block';

        d.innerHTML = 'Hide Your Personal Info';
    } else {
        e.style.display = 'none';
        f.style.display = 'none';
        g.style.display = 'none';

        d.innerHTML = 'Show Personal Info';
    }
}

function callBackFillPersonalData(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('personalTable');
    var row = table.insertRow(1);
    var dataTable = [data.w_name, data.w_surname, data.w_address, data.w_phone, data.w_at, data.w_username, data.w_email];
    for (var i = 0; i < 7; i++) {
        var cell = row.insertCell(i);
        cell.innerHTML = dataTable[i];
    }
}

function showCurrentPatients() {
    var d = document.getElementById('currPatientsButton');
    var e = document.getElementById('currPatientsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Current Patients';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Patients';
    }
}

function showActiveStaff() {
    var d = document.getElementById('activeStaffButton');
    var e = document.getElementById('activeStaffTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Active Staff';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Active Staff';
    }
}

function showAddPatientForm() {
    var d = document.getElementById('addPatientButton');
    var e = document.getElementById('addPatientForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Patient';
    }
}

function showAddVisitForm() {
    var d = document.getElementById('addVisitButton');
    var e = document.getElementById('addVisitForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Visit';
    }
}

function showAddWorkerForm() {
    var d = document.getElementById('addWorkerButton');
    var e = document.getElementById('addWorkerForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Worker';
    }
}


function showAddDoctorForm() {
    var d = document.getElementById('addDoctorButton');
    var e = document.getElementById('addDoctorForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Doctor';
    }
}



function showAddNurseForm() {
    var d = document.getElementById('addNurseButton');
    var e = document.getElementById('addNurseForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Nurse';
    }
}


function showSetDutyTime() {
    var d = document.getElementById('setDutyTimeButton');
    var e = document.getElementById('setDutyTimeForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Add Duty Time';
    }
}

function showDrugs() {
    var d = document.getElementById('drugButton');
    var e = document.getElementById('drugTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Drugs';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Drugs';
    }
}

function showDoctors() {
    var d = document.getElementById('doctorsButton');
    var e = document.getElementById('doctorsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Doctors';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Doctors';
    }
}

function showNurses() {
    var d = document.getElementById('nursesButton');
    var e = document.getElementById('nursesTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Nurses';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Nurses';
    }
}

function showWorkers() {
    var d = document.getElementById('workersButton');
    var e = document.getElementById('workersTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Workers';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Workers';
    }
}

function showPatients() {
    var d = document.getElementById('patientsButton');
    var e = document.getElementById('patientsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Patients';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Patients';
    }
}

function showExaminations() {
    var d = document.getElementById('examinationsButton');
    var e = document.getElementById('examinationsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Examinations';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Examinations';
    }
}

function showMedicals() {
    var d = document.getElementById('medicalsButton');
    var e = document.getElementById('medicalsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Medicals';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Medicals';
    }
}

function showReExaminations() {
    var d = document.getElementById('reExaminationsButton');
    var e = document.getElementById('reExaminationsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Re-Examinations';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Re-Examination';
    }
}

function showDuties() {
    var d = document.getElementById('dutiesButton');
    var e = document.getElementById('dutiesTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Duties';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Duties';
    }
}

function showVisits() {
    var d = document.getElementById('visitsButton');
    var e = document.getElementById('visitsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Visits';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Visits';
    }
}

function showQueryForm() {
    var d = document.getElementById('queryButton');
    var e = document.getElementById('queryForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Query Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Query Form';
    }
}

function sendXmlForm(url, reqID, formData) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (reqID === GET_PERSONAL_AND_DRUGS) {
                console.log("Filling personal data and medical supplies information");
                if (flag == 0)
                {
                    callBackFillPersonalData(request);
                    callBackFillDrugsAndIllnesses(request);

                }

                callBackFillPersonalDuties(request);
            } else if (reqID === GET_CURRENT_PATIENTS) {
                callBackFillPatients(request);
            } else if (reqID === GET_CURRENT_STUFF) {
                callBackFillCurrentStaff(request);
            } else if (reqID === GET_DOCTORS_NURSES_WORKERS) {
                callBackFillDoctors(request);
                callBackFillNurses(request);
                callBackFillWorkers(request);
            } else if (reqID === GET_ALL_PATIENTS) {
                callBackFillAllPatients(request);
            } else if (reqID === GET_ALL_EXAMS) {
                callBackFillExams(request);
            } else if (reqID === GET_ALL_VISITS) {
                callBackFillVisits(request);
            } else if (reqID === GET_ALL_DUTIES) {
                callBackFillDuties(request);
            } else if (reqID === GET_S_QUERY_ANSWER) {
                callBackFillEmptyTable(request);
            }
        }
    }
    ;
    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);
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

function callBackFillPersonalDuties(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('personalDuties');
    var rowCount = table.rows.length;
    var rowToBeDeleted = rowCount;
    console.log("rows to delete : " + rowToBeDeleted);
    for (var i = 0; i < rowCount - 1; i++)
    {
        table.deleteRow(rowToBeDeleted - 1);
        rowToBeDeleted--;
    }
    var dt = "duty";
    var total = data.dutytimes;
    console.log("Times: " + total);
    for (var i = 0; i < total; i++) {
        var row = table.insertRow((i + 1));
        var cell = row.insertCell(0);
        cell.innerHTML = data[dt + "" + i];
    }
}

function callBackFillPatients(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('currPatientsTable');
    var dataTable = ["visit_id", "date", "patient_id", "name", "surname", "birth_date", "amka", "diseases_array", "symptoms_array"];
    var total = data.patientsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 9; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillCurrentStaff(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('activeStaffTable');
    var dataTable = ["d_doctor_id", "d_name", "d_surname", "d_type"];
    var total = data.doctorsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 4; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + i]
        }
    }

    dataTable = ["n_nurse_id", "n_name", "n_surname", "n_nurse"];
    var total1 = data.nursesNumber;
    for (var i = 0; i < total1; i++) {
        row = table.insertRow(total + i + 1);
        for (var j = 0; j < 4; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + i]
        }
    }

    dataTable = ["w_worker_id", "w_name", "w_surname", "w_worker"];
    var total2 = data.workersNumber;
    for (var i = 0; i < total2; i++) {
        row = table.insertRow(total + total1 + i + 1);
        for (var j = 0; j < 4; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + i]
        }
    }


}

function callBackFillDoctors(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('doctorsTable');
    var dataTable = ["d_doctor_id", "d_name", "d_surname", "d_address", "d_phone", "d_type", "d_at", "d_username", "d_email"];
    total = data.doctorsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 9; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillNurses(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('nursesTable');
    var dataTable = ["n_nurse_id", "n_name", "n_surname", "n_address", "n_phone", "n_at", "n_username", "n_email"];
    total = data.nursesNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 8; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillWorkers(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('workersTable');
    var dataTable = ["w_worker_id", "w_name", "w_surname", "w_address", "w_phone", "w_at", "w_username", "w_email"];
    total = data.workersNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 8; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillAllPatients(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('patientsTable');
    var dataTable = ["patient_id", "name", "surname", "address", "phone", "birth_date", "amka", "at", "insurance", "username", "email", "diseases_array"];
    var total = data.patientsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 12; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillExams(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('examinationsTable');
    var dataTable = ["exam_id", "patient_id", "doctor_id", "drug_id", "illness_id", "visit_id", "date", "medical_id", "nurse_id", "type", "re_exam_id", "re_doctor_id", "hospi"];
    var total = data.examsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 13; j++) {
            var cell = row.insertCell(j);
            if (j === 12) {
                if (data[dataTable[j] + "" + i] === "1") {
                    cell.innerHTML = "Yes";
                } else {
                    cell.innerHTML = "No";
                }
            } else {
                cell.innerHTML = data[dataTable[j] + "" + i];
            }
        }
    }
}

function callBackFillVisits(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('visitsTable');
    var dataTable = ["visit_id", "date", "dutytime_id", "patient_id", "symptomArray"];
    var total = data.visitsNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 5; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillDuties(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('dutiesTable');
    var dataTable = ["dutytime_id", "date", "coordinator_id", "doctorArray", "nurseArray", "workerArray"];
    var total = data.dutyNumber;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 6; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function sendForm(jsonForm, id) {
    $(id).submit(function (event) {
        $.ajax({
            type: 'POST',
            url: url,
            data: jsonForm,
            dataType: 'json',
            success: function (results) {
                return results;
            }}).done(function (data) {
            console.log(data);
        });
    });
}

function sendAddPatientForm() {
    var jsonForm = {
        'requestID': ADD_PATIENT,
        'p_username': $('input[name=p_username]').val(),
        'p_password': $('input[name=p_password]').val(),
        'p_email': $('input[name=p_email]').val(),
        'p_name': $('input[name=p_name]').val(),
        'p_surname': $('input[name=p_surname]').val(), //?????????????
        'p_bd': $('input[name=p_bd]').val(),
        'p_phone': $('input[name=p_phone]').val(),
        'p_address': $('input[name=p_address]').val(),
        'p_amka': $('input[name=p_amka]').val(),
        'p_at': $('input[name=p_at]').val(),
        'p_insurance': $('input[name=p_insurance]').val(),
        'p_cd': $('input[name=p_cd]').val()
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#addPatientForm");
}

function sendAddVisitForm() {
    var jsonForm = {
        'requestID': ADD_VISIT,
        'v_patientID': $('input[name=v_patientID]').val(),
        'v_date': $('input[name=v_date]').val(),
        'v_s': $('input[name=v_s]').val()
    }

    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#addVisitForm");
}

function sendAddDutyTimeForm() {
    var jsonForm = {
        'requestID': ADD_DUTY,
        'd_doctorID': $('input[name=d_doctorID]').val(),
        'd_nurseID': $('input[name=d_nurseID]').val(),
        'd_workerID': $('input[name=d_workerID]').val(),
        'd_date': $('input[name=d_date]').val()
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#setDutyTimeForm");
}

function sendDefaultDutyTimeForm() {
    var jsonForm = {
        'requestID': ADD_DEFAULT_DUTY,
        'd_date': $('input[name=d_date]').val()
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#setDutyTimeForm");
}

function sendSelectQuery() {

    var query = $('input[name=selectQ]').val();
    var request = new XMLHttpRequest();
    formData = "requestID=" + SEND_S_QUERY;
    formData += "&queryS=" + query;
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callBackFillEmptyTable(request);
        }
    }
    ;
    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);
}

function sendUpdateQuery() {
    var query = $('input[name=updateQ]').val();
    var request = new XMLHttpRequest();
    formData = "requestID=" + SEND_U_QUERY;
    formData += "&queryU=" + query;
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //callBackFillEmptyTable(request);
        }
    };

    request.open("POST", url);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    request.send(formData);
}

function callBackFillEmptyTable(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('emptyTable');
    table.innerHTML = "";
    total = data.totalRows + 1;
    console.log("totalll:" + total);
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i);
        console.log("hwoqidhs: " + data.totalCols);
        for (var j = 0; j < data.totalCols; j++) {
            if (i === 0) {
                var cell = row.insertCell(j);
                cell.innerHTML = data["ColumnNames"][j];
            } else {
                var cell = row.insertCell(j);
                cell.innerHTML = data[data["ColumnNames"][j] + (i - 1)];

            }

        }
    }

    console.log(request.responseText);
    var e = document.getElementById('emptyTable');
    var d = document.getElementById('emptyTableButton');

    e.style.display = 'block';
    d.style.display = 'block';
    /*if (e.style.display === 'none' || e.style.display === '') {
     } else {
     e.style.display = 'none';
     }*/
}

function hideEmptyTable() {
    var e = document.getElementById('emptyTable');
    var d = document.getElementById('emptyTableButton');
    e.style.display = 'none';
    d.style.display = 'none';

    /*if (e.style.display === 'none' || e.style.display === '') {
     e.style.display = 'block';
     } else {
     }*/
}

function sendAddDoctorForm() {
    var jsonForm = {
        'requestID': ADD_DOCTOR,
        'dd_username': $('input[name=dd_username]').val(),
        'dd_password': $('input[name=dd_password]').val(),
        'dd_name': $('input[name=dd_name]').val(),
        'dd_surname': $('input[name=dd_surname]').val(),
        'dd_address': $('input[name=dd_address]').val(),
        'dd_phone': $('input[name=dd_phone]').val(),
        'dd_email': $('input[name=dd_email]').val(),
        'dd_type': $('input[name=dd_type]').val(),
        'dd_at': $('input[name=dd_at]').val(),
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#addDoctorForm");
}

function sendAddNurseForm() {
    var jsonForm = {
        'requestID': ADD_NURSE,
        'nn_username': $('input[name=nn_username]').val(),
        'nn_password': $('input[name=nn_password]').val(),
        'nn_name': $('input[name=nn_name]').val(),
        'nn_surname': $('input[name=nn_surname]').val(),
        'nn_address': $('input[name=nn_address]').val(),
        'nn_phone': $('input[name=nn_phone]').val(),
        'nn_email': $('input[name=nn_email]').val(),
        'nn_at': $('input[name=nn_at]').val(),
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#addNurseForm");
}

function sendAddWorkerForm() {
    var jsonForm = {
        'requestID': ADD_WORKER,
        'ww_username': $('input[name=ww_username]').val(),
        'ww_password': $('input[name=ww_password]').val(),
        'ww_name': $('input[name=ww_name]').val(),
        'ww_surname': $('input[name=ww_surname]').val(),
        'ww_address': $('input[name=ww_address]').val(),
        'ww_phone': $('input[name=ww_phone]').val(),
        'ww_email': $('input[name=ww_email]').val(),
        'ww_at': $('input[name=ww_at]').val(),
    };
    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#addWorkerForm");
}







