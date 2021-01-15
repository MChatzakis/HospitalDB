<%-- Document : DoctorSite Created on : Dec 28, 2020, 5:24:22 PM Author : George
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/Doctor.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <script type="text/javascript" src="js/Doctor_controller.js"></script>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <% //allow access only if session exists 
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Doctor")))
            {
                response.sendRedirect("http://localhost:8080/HospitalSystem/");
            }
            else
            {
                user = (String) session.getAttribute("username");
            }

        %>
        <title>Doctor <%= user%></title>
    </head>

    <body>
        <div class="container-fluid text-center ">
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
                        <th>Type</th>
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
                <button  type="button" id="reExaminationsButton" class="btn btn-primary btn-md" onclick="showReExaminations();">Show Current Re - Examinations</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="reExaminationsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Re - Exam ID</th>
                        <th>Patient ID</th>
                        <th>Medical ID</th>
                        <th>Hospitalization</th>
                    </tr>
                </table>           
            </div> 

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="addExaminationButton" class="btn btn-primary btn-md" onclick="showExaminationForm();">Add New Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="examForm" class="row col-10  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <!--<div class="form-group col ">
                            <label for="patientID">PatientID</label>
                            <input type="text" name="patientID" value="" class="form-control" id="patientID" placeholder="">
                        </div>-->
                        <div class="form-group col ">
                            <label for="visitID">VisitID</label>
                            <input type="text" name="visitID" value="" class="form-control" id="visitID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="drugID">DrugID</label>
                            <input type="text" name="drugID" value="" class="form-control" id="drugID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="illnessID">IllnessID</label>
                            <input type="text" name="illnessID" value="" class="form-control" id="illnessID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="date">Date</label>
                            <input type="date" data-date="" data-date-format="DD MMMM YYYY" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="submitButton-2"  onClick="sendExaminationForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="modifyExaminationButton" class="btn btn-primary btn-md" onclick="showModifyExaminationForm();">Modify Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="modifyExamForm" class="row col-10  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="mm_examID">ExamID</label>
                            <input type="text" name="mm_examID" value="" class="form-control" id="mm_examID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="mm_drugID">DrugID</label>
                            <input type="text" name="mm_drugID" value="" class="form-control" id="mm_drugID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="mm_illnessID">IllnessID</label>
                            <input type="text" name="mm_illnessID" value="" class="form-control" id="mm_illnessID" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="submitButton"  onClick="sendModifyExaminationForm()">
                    </div>
                </form>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="reExaminationButton" class="btn btn-primary btn-md" onclick="showReExaminationForm();">New Re - Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="reExaminationForm" class="row col-10  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <!--<div class="form-group col ">
                            <label for="r_patientID">PatientID</label>
                            <input type="text" name="r_patientID" value="" class="form-control" id="r_patientID" placeholder="">
                        </div>-->
                        <!--<div class="form-group col ">
                            <label for="r_visitID">VisitID</label>
                            <input type="text" name="r_visitID" value="" class="form-control" id="r_visitID" placeholder="">
                        </div>-->
                        <div class="form-group col ">
                            <label for="r_medicalID">MedicalID</label>
                            <input type="text" name="r_medicalID" value="" class="form-control" id="r_medicalID" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_hosp">Hospitalization</label>
                            <input type="text" name="r_hosp" value="" class="form-control" id="r_hosp" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="r_date">Date</label>
                            <input type="date" data-date="" data-date-format="DD MMMM YYYY" name="r_date" value="" class="form-control" id="r_date" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="addExamButton"  onClick="sendReExaminationForm()">
                    </div>
                </form>
            </div>

            <!--<div class="footer mt-5">All Rights Reserved &copy; George Kokolakis , Manos Chatzakes</div>-->
        </div>
        <!--<div class="footer" Style="color:rgb(0, 0, 0)">All Rights Reserved &copy; George Kokolakis , Manos Chatzakes</div>-->


    </body>

</html>
