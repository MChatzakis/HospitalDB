/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author George
 */
public class LoginServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        /*response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.print("<html>");
        printWriter.print("<body>");
        printWriter.print("<p> username :: " + username + "</p>");
        printWriter.print("<p> password :: " + password + "<    /p>");
        printWriter.print("</body>");
        printWriter.print("</html>");
        printWriter.close();*/
        //authenticate 
        //  request.getRequestDispatcher("/doctor.jsp").forward(request, response);
        /*
         RequestDispatcher dispatcher = getServletContext()
      .getRequestDispatcher("/DoctorServlet");
    dispatcher.forward(request, response);*/
        
        String Type = Validate(username, password);
        
 /*
        RequestDispatcher rd = request.getRequestDispatcher("doctor.jsp"); 
        rd.forward(request, response);*/
        if (username.equals("giorgos"))
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Doctor", username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/DoctorServlet");

        }
        else  if (username.equals("omorfos") )
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Patient", username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/PatientServlet");
        }

        // Set response content type
    }
    
    String Validate(String username , String password)
    {
        return null;
    }
}
