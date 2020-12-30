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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author George
 */
public class LoginServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String type = Validate(username, password);
/*
        if (username.equals("giorgos"))
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Doctor", username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/DoctorServlet");
        }
        else if (username.equals("omorfos"))
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Patient", username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/PatientServlet");
        }
        else
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Nurse", username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/NurseServlet");

        }*/
        if (type == null)
        {
            response.sendRedirect("http://localhost:8080/HospitalSystem/");
        }
        else
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute(type, username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/"+ type +"servlet");
        }
        // Set response content type
    }

    String Validate(String username, String password)
    {
        
        return null;
    }
    
}
