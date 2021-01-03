<%-- 
    Document   : DoctorSite
    Created on : Dec 28, 2020, 5:24:22 PM
    Author     : George
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <script type="text/javascript" src="js/Doctor_controller.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%
//allow access only if session exists
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Doctor")))

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
        <title>Doctor <%= userName %></title>

    </head>

    <body>
        <div class="container text-center">
            <h1>Hello Doctor!</h1>
            <form method="post" action="http://localhost:8080/HospitalSystem/LogoutServlet">

                <input type="submit" class="btn btn-primary btn-md mt-4 "  
                       id="button-login" value="Logout">
            </form>

            <button type="button" id="doctor_button" class="btn btn-primary" onclick="ShowPatients();"ondblclick="HidePatients()">Patients</button>
            <table class="table table-sm table-dark d-none" id="table-id">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">disease</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>george</td>
                        <td>Kokolakis</td>
                        <td>omorfos</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>eua</td>
                        <td>xamhlakh</td>
                        <td>xazoula</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>manos</td>
                        <td>xatzakhs</td>
                        <td>asxhmos</td>
                    </tr>
                </tbody>
            </table>
        </div>

    </body>

</html>
