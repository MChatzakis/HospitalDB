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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/*
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class LoginServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<String> ret = new ArrayList<String>();
        String type = null;
        int userID = -1;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try
        {
            ret = Validate(username, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (ret == null)
        {
            System.out.println("wrong credentials");
            response.sendRedirect("http://localhost:8080/HospitalSystem/");
        }
        else
        {
            type = ret.get(0);
            userID = Integer.parseInt(ret.get(1));

            System.out.println("Success: Type user is " + type);
            System.out.println("Success: User ID is " + userID);
            System.out.println("Success: Username is " + username);
            System.out.println("Success: Password is " + password);

            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("type", type); //setting session attribute
            session.setAttribute("username", username); //setting session attribute
            session.setAttribute("user_id",userID );//String.valueOf(userID)
          

            //Cookie userName = new Cookie("user", username);
            response.sendRedirect(request.getContextPath() + "/" + type + "Servlet");
        }

    }

    ArrayList<String> Validate(String username, String password) throws SQLException, ClassNotFoundException
    {
        String query;
        ArrayList<String> values = new ArrayList<String>();
        String userPassword = null, userType = null;
        int userId = -1;
        ResultSet res = null;

        query = "SELECT password , user_id ,user_type FROM users WHERE username=" + "\'" + username + "\'";
        DBConnection conn = new DBConnection();
        res = conn.executeQuery(query);
        if (res == null)
        {
            System.err.println("wrong query ");
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
            System.out.println("logged in with username : " + username);
            values.add(userType);
            values.add(String.valueOf(userId));
            return values;
        }
        else
        {
            System.err.println(" wrong credentials");
        }
        conn.closeDBConnection();
        return null;
    }
}
