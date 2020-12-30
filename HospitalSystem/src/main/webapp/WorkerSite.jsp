<%-- 
    Document   : WorkerSite
    Created on : Dec 28, 2020, 6:05:32 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Worker Page</title>
        <% //In case, if Admin session is not set, redirect to Login page
            if (((String) request.getSession(false).getAttribute("type") == "Worker"))

            {
        %>
        <jsp:forward page="/WorkerSite.jsp"></jsp:forward>
        <%}%>
    </head>
    <body>
        <h1>Hello Worker!</h1>
    </body>
</html>
