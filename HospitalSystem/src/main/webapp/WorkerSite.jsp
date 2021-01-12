<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/Worker.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

        <script type="text/javascript" src="js/Worker_controller.js"></script>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <% //allow access only if session exists 
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Worker")))
            {
                response.sendRedirect("http://localhost:8080/HospitalSystem/");
            }
            else
            {
                user = (String) session.getAttribute("username");
            }
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
            {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("user"))
                    {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID"))
                    {
                        sessionID = cookie.getValue();
                    }
                }
            }%>

        <title>Worker <%= user%></title>
    </head>

    <body>

        <div class="sidenav">
            <a href="#personalH">Personal Information</a>
            <a href="#currentH">Current Duty</a>
            <a href="#infoH">Hospital Information</a>
            <a href="#staffH">Staff</a>
            <a href="#queryH">Queries</a>
        </div>

        <div class="container text-center ">
            <div class="row justify-content-center ">
                <div class="jumbotron col-8 "style="background-color:rgb(20, 75, 165);opacity:0.7;  border-radius: 0px 0px 20px 20px;">
                    <h1>Hello <%= user%> !</h1>
                    <form method="post" action="http://localhost:8080/HospitalSystem/LogoutServlet">
                        <input type="submit" class="btn btn-primary btn-md float-right" style="background-color:#007bff;" id="button-login" value="Logout">
                    </form>
                    <form method="GET" action="http://localhost:8080/HospitalSystem/Statistics">
                        <input type="submit" class="btn btn-primary btn-md float-left" style="background-color:#007bff;" id="statistics-but" value="Statistics">
                    </form>
                </div>
            </div>

            <div id = "personalH"><h4>Personal Information</h4></div>
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

                <input type="text"id="daterange"class="col-3"style="text-align: center;display: none"name="daterange" value=" " />
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalDuties" class="table-sm  table-dark table-bordered" >
                    <tr>            
                        <th>Dates of Duty Time</th>
                    </tr>
                </table>        
            </div>

            <div id = "currentH"><h4>Current Dutytime</h4></div>

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
                        <th>Type</th> 
                    </tr>
                </table>           
            </div>


            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addPatientButton" class="btn btn-primary btn-md" onclick="showAddPatientForm();">Add Patient</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addPatientForm" class="row  col-8 justify-content-center mt-10 table-dark ">
                    <div class="row">
                        <div class="form-group col-3 " class="col-xs-2">
                            <label for="p_username">Username</label>
                            <input type="text" name="p_username" value="username" size="50" class="form-control" id="p_username" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="p_password">Password</label>
                            <input type="text" name="p_password" value="password" size="30" class="form-control" id="p_password" placeholder="">
                        </div>
                        <br><br>
                        <div class="form-group  col-3 ">
                            <label for="p_email">Email</label>
                            <input type="text" name="p_email" value="example@dom.com" size="30" class="form-control" id="p_email" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="p_name">Name</label>
                            <input type="text" name="p_name" value="Name" size="30" class="form-control" id="p_name" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_surname">Surname</label>
                            <input type="text" name="p_surname" value="Surname" size="30" class="form-control" id="p_surname" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_bd">Birth Date</label>
                            <input type="date" name="p_bd" value="yyyy-mm-dd" size="30" class="form-control" id="p_bd" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_phone">Phone</label>
                            <input type="text" name="p_phone" value="+3069...." size="30" class="form-control" id="p_phone" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_address">Address</label>
                            <input type="text" name="p_address" value="City" size="30" class="form-control" id="p_address" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_amka">AMKA</label>
                            <input type="text" name="p_amka" value="000000000000" size="30" class="form-control" id="p_amka" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_at">AT</label>
                            <input type="text" name="p_at" value="AT 0000 0000" size="30" class="form-control" id="p_at" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_insurance">Insurance</label>
                            <input type="text" name="p_insurance" value="IKA,IKY" size="30" class="form-control" id="p_insurance" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="p_cd">Chronic Diseases</label>
                            <input type="text" name="p_cd" value="Asthma, Obesity..." size="30" class="form-control" id="p_cd" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="submitButton"  onClick="sendAddPatientForm()">
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
                            <label for="v_patientID">Patient ID</label>
                            <input type="text" name="v_patientID" value="" class="form-control" id="v_patientID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="v_date">Date</label>
                            <input type="date" name="v_date" value="" class="form-control" id="v_date" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="v_s">Symptoms</label>
                            <input type="text" name="v_s" value="" class="form-control" id="v_s" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="sendAddVisitForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="setDutyTimeButton" class="btn btn-primary btn-md" onclick="showSetDutyTime();">Set Duty Time</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="setDutyTimeForm" class="row col-12  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col-3 ">
                            <label for="d_doctorID">Doctors IDs</label>
                            <input type="text" name="d_doctorID" value="" class="form-control" id="d_doctorID" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="d_nurseID">Nurse IDs</label>
                            <input type="text" name="d_nurseID" value="" class="form-control" id="d_nurseID" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="d_workerID">Worker IDs</label>
                            <input type="text" name="d_workerID" value="" class="form-control" id="d_workerID" placeholder="">
                        </div>
                        <div class="form-group col-3 ">
                            <label for="d_date">Duty Time Date</label>
                            <input type="date" data-date="" data-date-format="DD MMMM YYYY" name="d_date" value="" class="form-control" id="d_date" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input style="margin:5px" type="submit" value="Add" class="btn btn-primary" id="addDut"  onClick="sendAddDutyTimeForm()">
                    </div>
                    <div>
                        <input style="margin:5px" type="submit" value="Default" class="btn btn-primary" id="addDefDuty"  onClick="sendDefaultDutyTimeForm()">
                    </div>
                </form>
            </div>



            <div id="infoH"><h4>Hospital Information</h4></div>


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
                        <th>Doctor ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Address</th>
                        <th>Phone</th> 
                        <th>Type</th> 
                        <th>AT</th> 
                        <th>Username</th> 
                        <th>Email</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="nursesButton" class="btn btn-primary btn-md" onclick="showNurses();">Nurses</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="nursesTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Nurse ID</th>
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
                <button  type="button" id="workersButton" class="btn btn-primary btn-md" onclick="showWorkers();">Workers</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="workersTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Worker ID</th>
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
                <button  type="button" id="patientsButton" class="btn btn-primary btn-md" onclick="showPatients();">Patients</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="patientsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Patient ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Address</th>
                        <th>Phone</th> 
                        <th>Birth Date</th> 
                        <th>AMKA</th> 
                        <th>AT</th> 
                        <th>Insurance</th> 
                        <th>Username</th> 
                        <th>Email</th> 
                        <th>Chronic Diseases</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="examinationsButton" class="btn btn-primary btn-md" onclick="showExaminations();">Examinations</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="examinationsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Exam ID</th>
                        <th>Patient ID</th>
                        <th>Doctor ID</th>
                        <th>Drug ID</th>
                        <th>Illness ID</th> 
                        <th>Visit ID</th> 
                        <th>Date</th> 
                        <th>Medical ID</th> 
                        <th>Nurse ID</th> 
                        <th>Medical Type</th> 
                        <th>Re Examination ID</th> 
                        <th>Re Examination Doctor ID</th> 
                        <th>Hospitalization</th> 
                    </tr>
                </table>      
            </div>


            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="dutiesButton" class="btn btn-primary btn-md" onclick="showDuties();">Duties</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="dutiesTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Dutytime ID</th>
                        <th>Date</th>
                        <th>Coordinator ID</th>
                        <th>Doctor IDs</th>
                        <th>Nurse IDs</th> 
                        <th>Worker IDs</th> 
                    </tr>
                </table>      
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="visitsButton" class="btn btn-primary btn-md" onclick="showVisits();">Visits</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="visitsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Visit ID</th>
                        <th>Date</th>
                        <th>Dutytime ID</th>
                        <th>Patient ID</th>
                        <th>Symptoms</th> 
                    </tr>
                </table>      
            </div>


            <div id="staffH" ><h4>Staff Modification</h4></div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addDoctorButton" class="btn btn-primary btn-md" onclick="showAddDoctorForm();">Add Doctor</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addDoctorForm" class="row col-12  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="dd_username">Username</label>
                            <input type="text" name="dd_username" value="" size="30" class="form-control" id="dd_username" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_password">Password</label>
                            <input type="text" name="dd_password" value="" class="form-control" id="dd_password" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_name">Name</label>
                            <input type="text" name="dd_name" value="" class="form-control" id="dd_name" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_surname">Surname</label>
                            <input type="text" name="dd_surname" value="" class="form-control" id="dd_surname" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_address">Address</label>
                            <input type="text" name="dd_address" value="" class="form-control" id="dd_address" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_phone">Phone</label>
                            <input type="text" name="dd_phone" value="" class="form-control" id="dd_phone" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_email">Email</label>
                            <input type="text" name="dd_email" value="" class="form-control" id="dd_email" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_type">Type</label>
                            <input type="text" name="dd_type" value="" class="form-control" id="dd_type" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="dd_at">AT</label>
                            <input type="text" name="dd_at" value="" class="form-control" id="dd_at" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addDocButton"  onClick="sendAddDoctorForm()">
                    </div>

                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addNurseButton" class="btn btn-primary btn-md" onclick="showAddNurseForm();">Add Nurse</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addNurseForm" class="row col-8  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col-3 ">
                            <label for="nn_username">Username</label>
                            <input type="text" name="nn_username" value="" size="30" class="form-control" id="nn_username" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_password">Password</label>
                            <input type="text" name="nn_password" value="" class="form-control" id="nn_password" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_name">Name</label>
                            <input type="text" name="nn_name" value="" class="form-control" id="nn_name" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_surname">Surname</label>
                            <input type="text" name="nn_surname" value="" class="form-control" id="nn_surname" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_address">Address</label>
                            <input type="text" name="nn_address" value="" class="form-control" id="nn_address" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_phone">Phone</label>
                            <input type="text" name="nn_phone" value="" class="form-control" id="nn_phone" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_email">Email</label>
                            <input type="text" name="nn_email" value="" class="form-control" id="nn_email" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="nn_at">AT</label>
                            <input type="text" name="nn_at" value="" class="form-control" id="nn_at" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addNurButton"  onClick="sendAddNurseForm()">
                    </div>

                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addWorkerButton" class="btn btn-primary btn-md" onclick="showAddWorkerForm();">Add Worker</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="addWorkerForm" class="row col-8  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group  col-3 ">
                            <label for="ww_username">Username</label>
                            <input type="text" name="ww_username" value="" size="30" class="form-control" id="ww_username" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_password">Password</label>
                            <input type="text" name="ww_password" value="" class="form-control" id="ww_password" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_name">Name</label>
                            <input type="text" name="ww_name" value="" class="form-control" id="ww_name" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_surname">Surname</label>
                            <input type="text" name="ww_surname" value="" class="form-control" id="ww_surname" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_address">Address</label>
                            <input type="text" name="ww_address" value="" class="form-control" id="ww_address" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_phone">Phone</label>
                            <input type="text" name="ww_phone" value="" class="form-control" id="ww_phone" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_email">Email</label>
                            <input type="text" name="ww_email" value="" class="form-control" id="ww_email" placeholder="">
                        </div>
                        <div class="form-group  col-3 ">
                            <label for="ww_at">AT</label>
                            <input type="text" name="ww_at" value="" class="form-control" id="ww_at" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addWorButton"  onClick="sendAddWorkerForm()">
                    </div>

                </form>
            </div>

            <div id="queryH" ><h4>Queries</h4></div>


            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="queryButton" class="btn btn-primary btn-md" onclick="showQueryForm();">Create DataBase Query</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="queryForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="selectQ">Selection Query</label>
                            <input type="text" name="selectQ" value="" class="form-control" id="selectQ" placeholder="">
                            <button  style="margin:10px" type="button" id="sendQ" class="btn btn-primary btn-md" onclick="sendSelectQuery();">Show!</button>
                        </div>
                        <div class="form-group col ">
                            <label for="updateQ">Modification Query</label>
                            <input type="text" name="updateQ" value="" class="form-control" id="updateQ" placeholder="">
                            <button  style="margin:10px" type="button" id="sendU" class="btn btn-primary btn-md" onclick="sendUpdateQuery();">Execute!</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="emptyTable" class="table-sm  table-dark table-bordered" >
                    <tr>


                    </tr>
                </table>      
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <button style="display: none" type="button" id="emptyTableButton" class="btn btn-primary btn-md" onclick="hideEmptyTable();">Minimize Result</button>
            </div>
    </body>


    <!--
        <footer>
            <div class="footer" style="background-color:rgb(20, 75, 165);opacity:0.7;  border-radius: 0px 0px 20px 20px;">
                <p>2021, Manos Chatzakis, George Kokolakis, All Rights Reserved</p>
                <p>Computer Science Department, University of Crete</p>
            </div>
        </footer>-->

</html>
