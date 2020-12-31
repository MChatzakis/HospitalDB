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


        <title>Patient Page </title>
        < <%
//allow access only if session exists
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Patient")))

            {
                response.sendRedirect("http://localhost:8080/HospitalSystem/");

            }
            else
            {
                user = (String) session.getAttribute("user");
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
        <div class="container text-center">
            <div class="jumbotron col-12">
                <h1>Hello Patient!</h1>
                <form method="post" action="http://localhost:8080/HospitalSystem/LogoutServlet">
                    <input type="submit" class="btn btn-primary btn-md float-right" id="button-login" value="Logout">
                </form>
            </div>
            <div class="row justify-content-between">

                <button type="button" id="doctor_button" class="btn btn-primary"
                        onclick="ShowInformation();">Information</button>
                <button type="button" id="doctor_button" class="btn btn-primary" onclick="ShowMedical();">Medical</button>
                <button type="button" id="doctor_button" class="btn btn-primary" onclick="ShowClinical();">Clinical
                    Medical</button>
                <button type="button" id="doctor_button" class="btn btn-primary" onclick="ShowVisits();">Visits</button>
            </div>
            <div class=" position-relative">
                <table class="table-sm  table-dark d-none position-absolute mt-2" style="left:55%" id="clinical">
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

                <table class="table-sm  table-dark d-none position-absolute mt-2" style="left:23%" id="medical">

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

                <table class="table-sm  table-dark d-none position-absolute mt-2" style="left:95%" id="visit">
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
                    <div class="Form-container col-4 d-none justify-content-center" id="form" style="right:13%">
                        <div class="row">
                            <div class="form-group col ">
                                <label for="fname">Name</label>
                                <input type="text" name="fname" class="form-control" id="fname" placeholder="Manos">
                            </div>
                            <div class="form-group col">
                                <label for="surname">Surname</label>
                                <input type="text" class="form-control" name="surname" placeholder="Chatzakes">
                            </div>
                        </div>

                        <div class="row">

                            <div class="form-group col">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" name="username" placeholder="Chatzakes">
                            </div>
                            <div class="form-group col ">
                                <label for="address">Address</label>
                                <input type="text" class="form-control" name="adress" placeholder="Chatzakes">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col ">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" name="email" placeholder="Chatzakes">
                            </div>
                            <div class="form-group col ">
                                <label for="phone">Phone</label>
                                <input type="text" class="form-control" name="phone" placeholder="Chatzakes">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col ">
                                <label for="birth_day">Birth_date</label>
                                <input type="text" class="form-control" name="birth_day" placeholder="Chatzakes"
                                       value="laal">
                            </div>
                            <div class="form-group col">
                                <label for="amka">Amka</label>
                                <input type="text" class="form-control" name="amka" placeholder="Chatzakes">
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group  col">
                                <label for="at">At</label>
                                <input type="text" class="form-control" name="at" placeholder="Chatzakes">
                            </div>
                            <div class="form-group col ">
                                <label for="insurance">Insurance</label>
                                <input type="text" class="form-control" name="insurance" placeholder="Chatzakes">
                            </div>
                        </div>
                        <div>
                            <input type="submit" value="Submit" id="submitButton" onClick="SaveForm()">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</body>
</html>
