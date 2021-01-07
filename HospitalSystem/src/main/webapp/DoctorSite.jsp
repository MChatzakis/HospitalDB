<%-- Document : DoctorSite Created on : Dec 28, 2020, 5:24:22 PM Author : George
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/Doctor.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/Doctor_controller.js"></script>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <% //allow access only if session exists 
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Doctor"))) {
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
        <title>Doctor <%= user%></title>
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
                        <!-- <button onClick="window.location.reload();">Refresh Page</button>-->
                    </form>
                </div>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="personalButton" class="btn btn-primary btn-md" onclick="showPersonal();">Personal Information</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Address</th>
                        <th>Phone</th> 
                        <th>AT</th> 
                        <th>Type</th>
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
                <button  type="button" id="patientsButton" class="btn btn-primary btn-md" onclick="showPatients();">Current Patients</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="patientsTable" class="table-sm  table-dark table-bordered" >
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
                <button  type="button" id="examinationsButton" class="btn btn-primary btn-md" onclick="showExaminations();">Show Current Examinations</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="examinationsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Exam ID</th>
                        <th>Patient ID</th>
                        <th>Drug ID</th>
                        <th>Illness ID</th>
                        <th>Doctor ID</th> 
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="medicalsButton" class="btn btn-primary btn-md" onclick="showMedicals();">Show Current Medicals</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="medicalsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Medical ID</th>
                        <th>Exam ID</th>
                        <th>Patient ID</th>
                        <th>Nurse ID</th>
                        <th>Doctor ID</th> 
                        <th>Type</th> 
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addExaminationButton" class="btn btn-primary btn-md" onclick="showExaminationForm();">Add New Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="examForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="visitID">PatientID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">VisitID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">DrugID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">IllnessID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">Date</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="SaveForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="reExaminationButton" class="btn btn-primary btn-md" onclick="showReExaminationForm();">New Re - Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="reExaminationForm" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="visitID">PatientID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">VisitID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">MedicalID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">Hospitalization</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="visitID">Date</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="SaveForm()">
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
