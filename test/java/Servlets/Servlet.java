/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import javax.servlet.http.HttpServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 *
 * @author George Kokolakis
 * 
 */
@WebServlet("/login")
public class Servlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Set response content type
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.print("<html>");
        printWriter.print("<body>");
        printWriter.print("<p> username :: " + username + "</p>");
        printWriter.print("<p> password :: " + password + "</p>");
        printWriter.print("</body>");
        printWriter.print("</html>");
        printWriter.close();
        
        
        System.out.println("username :: " + username);
        System.out.println("password :: " + password);

    }
}
