<%-- 
    Document   : NurseSite
    Created on : Dec 28, 2020, 6:05:19 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <% //In case, if Admin session is not set, redirect to Login page
            if ((request.getSession(false).getAttribute("Nurse") == null))
            {
        %>
        <jsp:forward page="/NurseSite.jsp"></jsp:forward>
        <%}%>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
