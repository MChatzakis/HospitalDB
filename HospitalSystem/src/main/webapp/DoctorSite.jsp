<%-- 
    Document   : DoctorSite
    Created on : Dec 28, 2020, 5:24:22 PM
    Author     : George
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Page</title>
        <% //In case, if Admin session is not set, redirect to Login page
            if ((request.getSession(false).getAttribute("Doctor") == null))
            {
        %>
        <jsp:forward page="/DoctorSite.jsp"></jsp:forward>
        <%}%>
    </head>
    <body>
        <h1>Hello Doctor!</h1>
    </body>
</html>
