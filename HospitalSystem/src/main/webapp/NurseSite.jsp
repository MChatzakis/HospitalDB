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
        <%
//allow access only if session exists
            String user = null;
            if (!(request.getSession(false).getAttribute("type").equals("Nurse")))

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
        <h1>Hello World!</h1>
    </body>
</html>
