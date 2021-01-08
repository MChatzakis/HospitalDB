
var url = "http://localhost:8080/HospitalSystem/WorkerServlet";

$(document).ready(function () {
    console.log('Document ready -- Getting the initial information');
    sendXmlForm(url, GET_PERSONAL_AND_DRUGS);
    sendXmlForm(url, GET_EXAMS_AND_MEDICALS);
    sendXmlForm(url, GET_PATIENTS);
});

function showPersonal() {
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
        d.innerHTML = 'Hide Current Patients';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Current Patients';
    }
}

function showAddPatientForm() {
    var d = document.getElementById('addPatientButton');
    var e = document.getElementById('addPatientForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Add Patients Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Add Patients Form';
    }
}

function showAddVisitForm() {
    var d = document.getElementById('addVisitButton');
    var e = document.getElementById('addVisitForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Add Visit Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Add Visit Form';
    }
}

function showSetDutyTime() {
    var d = document.getElementById('setDutyTimeButton');
    var e = document.getElementById('setDutyTimeForm');
    if (e.style.display === 'none' || e.style.display === '') {
        e.style.display = 'block';
        d.innerHTML = 'Hide Add Duty Time Form';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Add Duty Time Form';
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
        d.innerHTML = 'Show Re-Duties';
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
        d.innerHTML = 'Hide Visits';
    } else {
        e.style.display = 'none';
        d.innerHTML = 'Show Visits';
    }
}

function sendXmlForm(url, reqID) {
    var request = new XMLHttpRequest();
    formData = "requestID=" + reqID;
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (reqID === GET_PERSONAL_AND_DRUGS) {
                console.log("Filling personal data and medical supplies information");
                callBackFillPersonalData(request);
                callBackFillDuties(request);
                callBackFillDrugsAndIllnesses(request);
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


























