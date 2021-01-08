<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/Worker.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/Worker_controller.js"></script>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <% //allow access only if session exists 
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Worker"))) {
                response.sendRedirect("http://localhost:8080/HospitalSystem/");
            } else {
                user = (String) session.getAttribute("username");
            }
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                }
            }%>

        <title>Worker <%= user%></title>
    </head>

    <body>
        <div class="container text-center ">
            <div class="row justify-content-center ">
                <div class="jumbotron col-8 "style="background-color:rgb(20, 75, 165);opacity:0.7;  border-radius: 0px 0px 20px 20px;">
                    <h1>Hello <%= user%> !</h1>
                    <form method="post" action="http://localhost:8080/HospitalSystem/LogoutServlet">
                        <input type="submit" class="btn btn-primary btn-md float-right" style="background-color:#007bff;" id="button-login" value="Logout">
                    </form>
                    <form method="post" action="http://localhost:8080/HospitalSystem/DoctorServlet">
                        <input type="submit" class="btn btn-primary btn-md float-left" style="background-color:#007bff;" id="button-refresh" value="Refresh">
                    </form>
                </div>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="personalButton" class="btn btn-primary btn-md" onclick="showPersonal();">Show Personal Information</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Address</th>
                        <th>Phone</th> 
                        <th>AT</th> 
                        <th>Username</th>
                        <th>Email</th>
                    </tr>
                </table>        
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalDuties" class="table-sm  table-dark table-bordered" >
                    <tr>            
                        <th>Dates of Duty Time</th>
                    </tr>
                </table>        
            </div>


            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="currPatientsButton" class="btn btn-primary btn-md" onclick="showCurrentPatients();">Current Patients</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="currPatientsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Visit ID</th>
                        <th>Date</th>
                        <th>Patient ID</th>
                        <th>Name</th>
                        <th>Surname</th> 
                        <th>Birth Date</th>
                        <th>AMKA</th>
                        <th>Chronic Diseases</th>
                        <th>Current Symptoms</th>
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="activeStaffButton" class="btn btn-primary btn-md" onclick="showActiveStaff();">Active Staff</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="activeStaffTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Illness ID</th>
                        <th>Doctor ID</th> 
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addPatientButton" class="btn btn-primary btn-md" onclick="showAddPatientForm();">Add Patient</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addPatientForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="patientID">Username</label>
                            <input type="text" name="patientID" value="" class="form-control" id="patientID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">Password</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="drugID">Email</label>
                            <input type="text" name="drugID" value="" class="form-control" id="drugID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="illnessID">Name</label>
                            <input type="text" name="illnessID" value="" class="form-control" id="illnessID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Surname</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Birth Date</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Phone</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Address</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">AMKA</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">AT</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Insurance</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Chronic Diseases (with comma)</label>
                            <input type="text" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="submitButton"  onClick="sendExaminationForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addVisitButton" class="btn btn-primary btn-md" onclick="showAddVisitForm();">Add Visit</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addVisitForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="r_patientID">Patient ID</label>
                            <input type="text" name="r_patientID" value="" class="form-control" id="r_patientID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Date</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Symptoms (with comma)</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="sendReExaminationForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="setDutyTimeButton" class="btn btn-primary btn-md" onclick="showSetDutyTime();">Set Duty Time</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="setDutyTimeForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="r_patientID">Doctors IDs (with commas)</label>
                            <input type="text" name="r_patientID" value="" class="form-control" id="r_patientID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Nurse IDs (with commas)</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Worker IDs (with comma)</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Duty Time Date</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="sendReExaminationForm()">
                    </div>
                    <div>
                        <input type="submit" value="Default Duty Set" class="btn btn-primary" id="addExamButton"  onClick="sendReExaminationForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="drugButton" class="btn btn-primary btn-md" onclick="showDrugs();">Show Drugs and Illnesses</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="drugTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="doctorsButton" class="btn btn-primary btn-md" onclick="showDoctors();">Doctors</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="doctorsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>
                    
            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="nursesButton" class="btn btn-primary btn-md" onclick="showNurses();">Nurses</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="nursesTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>
                    
            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="workersButton" class="btn btn-primary btn-md" onclick="showWorkers();">Workers</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="workersTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>
                    
            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="patientsButton" class="btn btn-primary btn-md" onclick="showPatients();">Patients</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="patientsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="examinationsButton" class="btn btn-primary btn-md" onclick="showExaminations();">Examinations</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="examinationsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="medicalsButton" class="btn btn-primary btn-md" onclick="showMedicals();">Medicals</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="medicalsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="reExaminationsButton" class="btn btn-primary btn-md" onclick="showReExaminations();">Re - Examinations</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="reExaminationsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="dutiesButton" class="btn btn-primary btn-md" onclick="showDuties();">Duties</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="dutiesTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="visitsButton" class="btn btn-primary btn-md" onclick="showVisits();">Visits</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="visitsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Drug ID</th>
                        <th>Drug Name</th>
                        <th>Drug Type</th>
                        <th>Dosage (mg)</th>
                        <th>Illness ID</th> 
                        <th>Illness Name</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="queryButton" class="btn btn-primary btn-md" onclick="showQueryForm();">Create DataBase Query</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="queryForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="r_patientID">Selection Query</label>
                            <input type="text" name="r_patientID" value="" class="form-control" id="r_patientID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_visitID">Modification Query</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="sendReExaminationForm()">
                    </div>
                </form>
            </div>
    </body>



    <footer>
        <div class="footer" style="background-color:rgb(20, 75, 165);opacity:0.7;  border-radius: 0px 0px 20px 20px;">
            <p>2021, Manos Chatzakis, George Kokolakis, All Rights Reserved</p>
            <p>Computer Science Department, University of Crete</p>
        </div>
    </footer>

</html>
