/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author George
 */
@WebServlet
@MultipartConfig
public class PatientServlet extends HttpServlet {

    int INFORMATION_ID = 1;
    int MEDICAL_ID = 2;
    int Clinical_ID = 3;
    int VISIT_ID = 4;
    int FILL_INFORMATION_ID = 5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("PatientSite.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject obj = new JSONObject();
        int request_id = Integer.parseInt(request.getParameter("requestID"));
        System.out.println("request id is  : " + request_id);
        if (request_id == FILL_INFORMATION_ID) {
            try {
                obj = GetInformations((String) request.getSession(false).getAttribute("username"));
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(obj);
                out.flush();
                System.out.println(obj.toString(0));

            } catch (SQLException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    JSONObject GetInformations(String username) throws SQLException, ClassNotFoundException {
        JSONObject obj = new JSONObject();
        String query;
        ResultSet res = null;

        query = "SELECT  name, surname, address, users.email, phone, birth_date, amka, at, insurance FROM patients WHERE patient_id IN"
                + "( SELECT user_id FROM users WHERE username=" + "\'" + username + "\'" + " );";
        DBConnection conn = new DBConnection();
        res = conn.executeQuery(query);
        if (res == null) {
            System.err.println("wrong query");
            return null;
        }

        while (res != null && res.next()) {
            obj.put("name", res.getString("name"));
            obj.put("surname", res.getString("surname"));

            obj.put("address", res.getString("address"));
            obj.put("email", res.getString("email"));
            obj.put("phone", res.getString("phone"));
            obj.put("birth_day", res.getString("birth_date"));
            obj.put("amka", res.getString("amka"));
            obj.put("at", res.getString("at"));
            obj.put("insurance", res.getString("insurance"));
        }
        obj.put("username", username);

        conn.closeDBConnection();
        return obj;
    }

}
