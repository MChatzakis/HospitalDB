/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import database.DBConnection;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String type = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try
        {
            type = Validate(username, password);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (type == null)
        {
            System.out.println("wrong credentials");
            response.sendRedirect("http://localhost:8080/HospitalSystem/");
        }

        else
        {
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute(type, username); //setting session attribute
            response.sendRedirect(request.getContextPath() + "/" + type + "Servlet");
        }

    }

    String Validate(String username, String password) throws SQLException, ClassNotFoundException
    {
        String query;
        String userPassword = null, userType = null;
        int userId;
        ResultSet res = null;

        query = "SELECT password , user_id ,user_type FROM users WHERE username=" + "\'" + username + "\'";
        DBConnection conn = new DBConnection();
        res = conn.executeQuery(query);
        if (res == null)
        {
            System.err.println("wrong query idiot");
            return null;
        }

        while (res != null && res.next())
        {
            userPassword = res.getString("password");
            userId = res.getInt("user_id");
            userType = res.getString("user_type");

        }
        if (password.equals(userPassword))
        {

            System.out.println("Correct credentials");
            System.out.println("logged in with username : "+username);

            return userType;
        }
        else
        {

            System.err.println("idiot wrong credentials");
        }
        conn.closeDBConnection();
        return null;
    }
}
