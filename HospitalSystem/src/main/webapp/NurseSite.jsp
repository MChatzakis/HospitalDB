<%-- 
    Document   : NurseSite
    Created on : Dec 28, 2020, 6:05:19 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="css/Doctor.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <script type="text/javascript" src="js/Nurse_controller.js"></script>
    <link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
//allow access only if session exists
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Nurse")))
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
            }
        %>
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

                <table style="display: none" id="personalDuties" class="table-sm table-dark table-bordered" >
                    <tr>            
                        <th>Dates of Duty Time</th>
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
                <button  type="button" id="addExaminationButton" class="btn btn-primary btn-md" onclick="showExaminationForm();">Add New Medical</button>
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <form  style="display: none" id="medical-form" class="row col-6  justify-content-center mt-3 table-dark ">
                    <div class="row">
                        <div class="form-group col ">
                            <label for="type">Type</label>
                            <input type="text" name="type" value="" class="form-control" id="type" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="exam-id">ExamID</label>
                            <input type="text" name="exam-id" value="" class="form-control" id="exam-id" placeholder="">
                        </div>
                        <!--<div class="form-group col ">
                            <label for="patient-id">PatientID</label>
                            <input type="text" name="patient-id" value="" class="form-control" id="patient-id" placeholder="">
                        </div>
                        <div class="form-group col ">
                            <label for="doctor-id">DoctorID</label>
                            <input type="text" name="doctor-id" value="" class="form-control" id="doctor-id" placeholder="">
                        </div>-->

                        <div class="form-group col ">
                            <label for="date">Date</label>
                            <input type="date" name="date" value="" class="form-control" id="date" placeholder="">
                        </div>
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary" id="submitButton"  onClick="sendMedicalForm()">
                    </div>
                </form>
            </div>

        </div>
        <!--<div class="footer float-left" Style="color:rgb(0, 0, 0)">All Rights Reserved &copy; George Kokolakis , Manos Chatzakes</div>-->

    </body>


</html>
