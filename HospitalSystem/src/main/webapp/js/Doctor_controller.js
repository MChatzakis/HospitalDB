// @author Manos Chatzakis (chatzakis@ics.forth.gr)


var GET_PERSONAL_AND_DRUGS = 1;
var GET_EXAMS_AND_MEDICALS = 2;
var GET_PATIENTS = 3;
var ADD_EXAMINATION = 4;
var ADD_RE_EXAMINATION = 5;
var MODIFY_EXAMINATION = 6;

var url = "http://localhost:8080/HospitalSystem/DoctorServlet";
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

    formData = "requestID=" + GET_EXAMS_AND_MEDICALS;
    sendXmlForm(url, GET_EXAMS_AND_MEDICALS, formData);


    formData = "requestID=" + GET_PATIENTS;

    sendXmlForm(url, GET_PATIENTS, formData);
});

function showPersonal() {
    console.log('Inside show personal');
    var d = document.getElementById('personalButton');
    var e = document.getElementById('personalTable');
    var f = document.getElementById('personalDuties');
    var g = document.getElementById('daterange');


    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        f.style.display = 'block';
        g.style.display = 'block';

        d.innerHTML = 'Hide Personal Info';
    } else {
        e.style.display = 'none';
        f.style.display = 'none';
        g.style.display = 'none';

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

function showModifyExaminationForm() {
    var d = document.getElementById('modifyExaminationButton');
    var e = document.getElementById('modifyExamForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Modify Examination';
    }
}

function showExaminations() {
    var d = document.getElementById('examinationsButton');
    var e = document.getElementById('examinationsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Current Examinations';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Examinations';
    }
}

function showMedicals() {
    var d = document.getElementById('medicalsButton');
    var e = document.getElementById('medicalsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Current Medicals';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Medicals';
    }
}

function showReExaminations() {
    var d = document.getElementById('reExaminationsButton');
    var e = document.getElementById('reExaminationsTable');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Current Re - Examinations';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Re - Examinations';
    }
}

function showExaminationForm() {
    var d = document.getElementById('addExaminationButton');
    var e = document.getElementById('examForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Examination Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Examination Form';
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
                callBackFillDuties(request);
            } else if (reqID === GET_EXAMS_AND_MEDICALS) {
                console.log("Filling examinationn and medical information");
                callBackFillExams(request);
                callBackFillMedicals(request);
                callBackFillReExams(request);
            } else if (reqID === GET_PATIENTS) {
                console.log("Filling patient information");
                callBackFillPatients(request);
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

function callBackFillExams(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('examinationsTable');
    var dataTable = ["exam_id", "patient_id", "drug_id", "illness_id", "doctor_id"];
    var total = data.examsNumber;
    var counter = 0;

    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 5; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillMedicals(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('medicalsTable');
    var dataTable = ["m_medical_id", "m_exam_id", "m_patient_id", "m_nurse_id", "m_doctor_id", "m_type"];
    var total = data.medicalsNumber;
    var counter = 0;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 6; j++) {
            var cell = row.insertCell(j);
            console.log(data[dataTable[j] + "" + i]);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillReExams(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('reExaminationsTable');
    var dataTable = ["r_re_exam_id", "r_patient_id", "r_visit_id", "r_hospitalization"];
    var total = data.reExamsNumber;
    var counter = 0;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 4; j++) {
            var cell = row.insertCell(j);
            console.log(data[dataTable[j] + "" + i]);
            cell.innerHTML = data[dataTable[j] + "" + i];
        }
    }
}

function callBackFillPatients(request) {
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('patientsTable');
    var dataTable = ["visit_id", "date", "patient_id", "name", "surname", "birth_date", "amka", "diseases_array", "symptoms_array"];
    var total = data.patientsNumber;
    var counter = 0;
    for (var i = 0; i < total; i++) {
        row = table.insertRow(i + 1);
        for (var j = 0; j < 9; j++) {
            var cell = row.insertCell(j);
            if (j === 7 && data.diseases_counter > 0) {
                var text = "";
                for (var k = 0; k < data.diseases_counter; k++) {
                    text += data[dataTable[j] + "" + i]["disease"] + " ";
                }
            } else if (j === 8 && data.symptoms_counter > 0) {
                var text = "";
                for (var k = 0; k < data.symptoms_counter; k++) {
                    text += data[dataTable[j] + "" + i]["symptom"] + " ";
                }
            } else {
                cell.innerHTML = data[dataTable[j] + "" + i];
            }
        }
    }
}

function showReExaminationForm() {
    var d = document.getElementById('reExaminationButton');
    var e = document.getElementById('reExaminationForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Re - Examination Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Re - Examination Form';
    }
}

function sendExaminationForm() {
    var jsonForm = {
        'requestID': ADD_EXAMINATION,
        'patientID': $('input[name=patientID]').val(),
        'visitID': $('input[name=visitID]').val(),
        'drugID': $('input[name=drugID]').val(),
        'illnessID': $('input[name=illnessID]').val(),
        'date': $('input[name=date]').val()
    };

    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#examForm");
}

function sendReExaminationForm() {
    var jsonForm = {
        'requestID': ADD_RE_EXAMINATION,
        'patientID': $('input[name=r_patientID]').val(),
        'medicalID': $('input[name=r_medicalID]').val(),
        'hosp': $('input[name=r_hosp]').val(),
        'visitID': $('input[name=r_visitID]').val(),
        'date': $('input[name=r_date]').val()
    };

    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#reExaminationForm");
}

function sendModifyExaminationForm() {
    var jsonForm = {
        'requestID': MODIFY_EXAMINATION,
        'mm_examID': $('input[name=mm_examID]').val(),
        'mm_drugID': $('input[name=mm_drugID]').val(),
        'mm_illnessID': $('input[name=mm_illnessID]').val(),
    };

    console.log('Form: ' + jsonForm);
    sendForm(jsonForm, "#modifyExamForm");
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
