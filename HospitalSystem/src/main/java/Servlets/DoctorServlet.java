/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import commons.Queries;
import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author George
 */
public class DoctorServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("hahahahahahaha");
        request.getRequestDispatcher("DoctorSite.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject obj = new JSONObject();
        int request_id = Integer.parseInt(request.getParameter("requestID"));
        System.out.println("request id is  : " + request_id);
        //System.out.println("lalalalalala");
        if (request_id == 1) {
            try {
                System.out.println("Got initial request!");
                obj = getInfo((String) request.getSession(false).getAttribute("username"));
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

    public JSONObject getInfo(String username) throws SQLException, ClassNotFoundException {
        JSONObject obj = new JSONObject();
        DBConnection conn = new DBConnection();

        ResultSet res = null;
        String infoQuery = Queries.getDoctorInfoByUsername(username);
        String drugsQuery = "SELECT drugs.drug_id, drugs.name AS drug_name, drugs.type AS drug_type, drugs.dosage, drugs.illness_id, illnesses.name AS illness_name\n"
                + "FROM drugs\n"
                + "INNER JOIN illnesses ON drugs.illness_id = illnesses.illness_id;";

        res = conn.executeQuery(infoQuery);

        if (res == null) {
            System.err.println("something went wrong!");
            return null;
        }

        while (res != null && res.next()) {
            obj.put("name", res.getString("name"));
            obj.put("surname", res.getString("surname"));
            obj.put("address", res.getString("address"));
            obj.put("phone", res.getString("phone"));
            obj.put("at", res.getString("at"));
            obj.put("type", res.getString("type"));
            obj.put("username", res.getString("username"));
            obj.put("email", res.getString("email"));
        }

        res = conn.executeQuery(drugsQuery);

        while (res != null && res.next()) {
            obj.put("drug_id", res.getString("drug_id"));
            obj.put("drug_name", res.getString("drug_name"));
            obj.put("drug_type", res.getString("drug_type"));
            obj.put("dosage", res.getString("dosage"));
            obj.put("illness_id", res.getString("illness_id"));
            obj.put("illness_name", res.getString("illness_name"));

        }

        conn.closeDBConnection();
        return obj;
    }

}
