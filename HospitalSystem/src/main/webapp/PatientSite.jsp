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
        <title>Patient Page </title>
        <% //In case, if Admin session is not set, redirect to Login page
            if (((String) request.getSession(false).getAttribute("type") == "patient"))

            {
        %>
        <jsp:forward page="/PatientSite.jsp"></jsp:forward>
        <%}%>
    </head>
    <body>
        <h1>Hello Patient!</h1>
    </body>
</html>
