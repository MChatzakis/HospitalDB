<%-- Document : DoctorSite Created on : Dec 28, 2020, 5:24:22 PM Author : George
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/Doctor_controller.js"></script>
        
        
        <link rel="stylesheet" href="css/Doctor.css">
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
                        <input type="submit" class="btn btn-primary btn-md float-bottom" style="background-color:#007bff;" id="button-login" value="Logout">
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
                        <th>Email</th>
                        <th>Address</th>
                        <th>Phone</th> 
                    </tr>
                </table>           
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="information-but" class="btn btn-primary btn-md" onclick="ShowInformation();">Show Drugs and Illnesses</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="patientsButton" class="btn btn-primary btn-md" onclick="showPatients();">Current Patients</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="patientsTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>ok</th>
                        <th>ok</th>
                        <th>suckit</th> 
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="information-but" class="btn btn-primary btn-md" onclick="ShowInformation();">New Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Phone</th> 
                    </tr>
                </table>           
            </div>

            <div class="row  justify-content-center " style="margin:5px";>
                <button  type="button" id="information-but" class="btn btn-primary btn-md" onclick="ShowInformation();">New Re - Examination</button>
            </div>
            <div class="row  justify-content-center " style="margin:5px";>
                <table style="display: none" id="personalTable" class="table-sm  table-dark table-bordered" >
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Phone</th> 
                    </tr>
                </table>           
            </div>
        </div> 

    </body>

    <footer>
        <div class="footer" style="background-color:rgb(20, 75, 165);opacity:0.7;  border-radius: 0px 0px 20px 20px;">
            <p>2021, Manos Chatzakis, George Kokolakis, All Rights Reserved</p>
            <p>Computer Science Department, University of Crete</p>
        </div>
    </footer>

</html>
