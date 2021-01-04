<%-- 
    Document   : PatientSite
    Created on : Dec 28, 2020, 6:06:01 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Patient.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/Patient_controller.js"></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>


        <title>Patient</title>
        <%
            //allow access only if session exists
            String username = null;
            if (!(request.getSession(false).getAttribute("type").equals("Patient"))) {
                response.sendRedirect("http://localhost:8080/HospitalSystem/");

            } else {
                username = (String) session.getAttribute("username");
            }
            String user = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        user = cookie.getValue();
                    }

                }
            }
        %>


    <body>
        <div class="container text-center ">
            <div class="row justify-content-center ">

                <div class="jumbotron col-8 "style="background-color:rgb(20, 75, 165);opacity:0.4;  border-radius: 0px 0px 20px 20px;">
                    <h1>Hello <%= username%></h1>
                    <form method="post" action="http://localhost:8080/HospitalSystem/LogoutServlet">
                        <input type="submit" class="btn btn-primary btn-md float-right" style="background-color:#007bff;" id="button-login" value="Logout">
                    </form>
                </div>
            </div>
            <div class="row justify-content-between">

                <button type="button" id="information-but" class="btn btn-primary btn-md"
                        onclick="ShowInformation();">Information</button>
                <button type="button" id="medical-but" class="btn btn-primary btn-md"
                        onclick="ShowMedical();">Medical</button>
                <button type="button" id="clinical-but" class="btn btn-primary" onclick="ShowClinical();">Clinical
                    Medical</button>
                <button type="button" id="visit-but" class="btn btn-primary btn-md" onclick="ShowVisits();">Visits</button>
            </div>
            <div class=" position-relative">
                <table class="table-sm  table-dark table-bordered d-none position-absolute mt-2 btn-md" style="left:55% " id="clinical">
                    <tr>
                        <th>Drug</th>
                        <th>Date</th>
                        <th>Doctor</th>
                        <th>Nurse</th>
                        <th>Ilness</th>

                    </tr>
                    <tbody id="clinical_rows">
                        <tr>
                            <td>george</td>
                            <td>Kokolakis</td>
                            <td>omorfos</td>
                        </tr>
                        <tr>
                            <td>eua</td>
                            <td>xamhlakh</td>
                            <td>xazoula</td>
                        </tr>
                        <tr>
                            <td>manos</td>
                            <td>xatzakhs</td>
                            <td>asxhmos</td>
                        </tr>
                    </tbody>
                </table>

                <table class="table-sm  table-dark d-none table-bordered position-absolute mt-2" style="left:23%" id="medical">

                    <tr>
                        <th>Type</th>
                        <th>Date</th>
                        <th>Doctor</th>
                        <th>Nurse</th>
                    </tr>
                    <tbody>
                        <tr>
                            <td>george</td>
                            <td>Kokolakis</td>
                            <td>omorfos</td>
                        </tr>
                        <tr>
                            <td>eua</td>
                            <td>xamhlakh</td>
                            <td>xazoula</td>
                        </tr>
                        <tr>
                            <td>manos</td>
                            <td>xatzakhs</td>
                            <td>asxhmos</td>
                        </tr>
                    </tbody>
                </table>

                <table class="table-sm  table-dark d-none  table-bordered position-absolute mt-2" style="left:95%" id="visit">
                    <tr>
                        <th>Date</th>
                    </tr>
                    <tbody>
                        <tr>
                            <td>16/10/2000</td>
                        </tr>
                        <tr>
                            <td>16/10/2000</td>
                        </tr>
                        <tr>
                            <td>16/10/2000</td>
                        </tr>
                    </tbody>
                </table>


                <form id="form-id">
                    <div class="Form-container col-4 d-none justify-content-center mt-3 table-dark" id="form" style="right:13%">
                        <div class="row">
                            <div class="form-group col ">
                                <label for="fname">Name</label>
                                <input type="text" name="fname" value="" class="form-control" id="fname" placeholder="">
                            </div>
                            <div class="form-group col">
                                <label for="surname">Surname</label>
                                <input type="text" class="form-control" name="surname" placeholder="">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" name="username" placeholder="">
                            </div>
                            <div class="form-group col ">
                                <label for="address">Address</label>
                                <input type="text" class="form-control" name="adress" placeholder="">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col ">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" name="email" placeholder="">
                            </div>
                            <div class="form-group col ">
                                <label for="phone">Phone</label>
                                <input type="text" class="form-control" name="phone" placeholder="">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col ">
                                <label for="birth_day">Birth_date</label>
                                <input type="text" class="form-control" name="birth_day" placeholder="" value="">
                            </div>
                            <div class="form-group col">
                                <label for="amka">Amka</label>
                                <input type="text" class="form-control" name="amka" placeholder="">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group  col">
                                <label for="at">At</label>
                                <input type="text" class="form-control" name="at" placeholder="">
                            </div>
                            <div class="form-group col ">
                                <label for="insurance">Insurance</label>
                                <input type="text" class="form-control" name="insurance" placeholder="">
                            </div>
                        </div>
                        <div>
                            <input type="submit" value="Submit" class="btn btn-primary" id="submitButton"
                                   onClick="SaveForm()">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</body>
</html>
