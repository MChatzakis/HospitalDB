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
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@WebServlet
@MultipartConfig
public class PatientServlet extends HttpServlet
{

    int HISTORY_ID = 1;
    int UPDATE_FORM_ID = 2;
    int FILL_DISEASES_ID = 3;
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        JSONObject obj = new JSONObject();
        int id;
        System.out.println((Integer) request.getSession(false).getAttribute("user_id"));
        id = (Integer) request.getSession(false).getAttribute("user_id");
        int request_id = Integer.parseInt(request.getParameter("requestID"));
        // System.out.println("request id is  : " + request_id);
        if (request_id == FILL_INFORMATION_ID)
        {

            try
            {
                PrintWriter out = response.getWriter();

                obj = GetInformations(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(obj);
                out.flush();
                System.out.println(obj.toString(0));

            }
            catch (SQLException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if (request_id == HISTORY_ID)
        {
            JSONArray array;
            System.out.println("im in history request ");

            try
            {
                PrintWriter out = response.getWriter();
                array = getMedicalHistory(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(array);
                out.flush();

            }
            catch (SQLException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (request_id == UPDATE_FORM_ID)
        {
            String fname = request.getParameter("fname");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String birth_day = request.getParameter("birth_day");
            String amka = request.getParameter("amka");
            String at = request.getParameter("at");
            String insurance = request.getParameter("insurance");
            System.out.println(fname + " " + surname + " " + username + " " + address + " " + email + " " + phone + " " + birth_day + " "
                    + " " + amka + " " + at + " " + insurance);
            try
            {
                changeInfo(fname, surname, username, address, email, phone, birth_day, amka, at, insurance, id);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (request_id == FILL_DISEASES_ID)
        {
            try
            {
                PrintWriter out = response.getWriter();

                obj = GetDiseases(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(obj);
                out.flush();
                System.out.println(obj.toString(0));

            }
            catch (SQLException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    JSONObject GetDiseases(int id) throws SQLException, ClassNotFoundException
    {
        JSONObject obj = new JSONObject();
        DBConnection conn = new DBConnection();
        

        ResultSet res = null;
        String query = Queries.getPatientDiseasesByID(id);
        res = conn.executeQuery(query);

        if (res == null)
        {
            System.err.println("Wrong getPatientDiseasesByID querry");
            return null;
        }
        int i=0;
        while (res != null && res.next())
        {
            //String visitQuery = "SELECT DISTINCT visit.date, illnesses.name AS illness,
            //drugs.name AS drug, medicals.type AS medical , examinations_retaken.hospitalization\n"
            obj.put("disease"+i, res.getString("disease"));
            i++;
            
        }
        conn.closeDBConnection();
        return obj;

    }

    void changeInfo(String fname, String surname, String username, String address, String email, String phone, String birth_day,
            String amka, String at, String insurance, int id) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String update = "UPDATE  patients "
                + "SET name=" + "\'" + fname + "\'" + "," + "surname=" + "\'" + surname + "\'" + "," + "address=" + "\'" + address + "\'" + "," + "phone="
                + "\'" + phone + "\'" + "," + "birth_date=" + "\'" + birth_day + "\'" + "," + "amka=" + "\'" + amka + "\'" + ","
                + "at=" + "\'" + at + "\'" + "," + "insurance=" + "\'" + insurance + "\'"
                + "WHERE patient_id=" + id + ";";
        conn.updateQuery(update);
        conn.closeDBConnection();
        conn = new DBConnection();
        String updateUsers = "UPDATE  users "
                + "SET username=" + "\'" + username + "\'" + "," + "email=" + "\'" + email + "\'"
                + "WHERE user_id=" + id + ";";
        conn.updateQuery(updateUsers);
        conn.closeDBConnection();

    }

    JSONArray getMedicalHistory(int id) throws SQLException, ClassNotFoundException
    {

        JSONObject obj = new JSONObject();
        JSONObject objSymptoms = new JSONObject();

        JSONArray array = new JSONArray();
        JSONArray array2 = new JSONArray();
        JSONArray wholeArray = new JSONArray();

        DBConnection conn = new DBConnection();

        ResultSet res = null;
        String query = Queries.getAllVisitExaminationInfo(id);
        res = conn.executeQuery(query);

        if (res == null)
        {
            System.err.println("Wrong getAllVisitExaminationInfo querry");
            return null;
        }

        while (res != null && res.next())
        {
            //String visitQuery = "SELECT DISTINCT visit.date, illnesses.name AS illness,
            //drugs.name AS drug, medicals.type AS medical , examinations_retaken.hospitalization\n"
            obj = new JSONObject();
            obj.put("visit", res.getString("visit_id"));
            obj.put("date", res.getString("date"));
            obj.put("illness", res.getString("illness"));
            obj.put("drug", res.getString("drug"));
            obj.put("medical", res.getString("medical"));
            obj.put("hospitalization", res.getString("hospitalization"));
            array.put(obj);
        }
        conn.closeDBConnection();
        res = null;
        conn = new DBConnection();

        query = Queries.getAllVisitSymptomsInfo(id);
        res = conn.executeQuery(query);

        if (res == null)
        {
            System.err.println("Wrong getAllVisitExaminationInfo querry");
            return null;
        }

        while (res != null && res.next())
        {
            //String visitQuery = "SELECT DISTINCT visit.date, illnesses.name AS illness,
            //drugs.name AS drug, medicals.type AS medical , examinations_retaken.hospitalization\n"
            objSymptoms = new JSONObject();
            objSymptoms.put("symptom", res.getString("symptom"));
            objSymptoms.put("visit", res.getString("visit_id"));

            array2.put(objSymptoms);
        }
        conn.closeDBConnection();
        wholeArray.put(array);
        wholeArray.put(array2);

        return wholeArray;

    }

    JSONObject GetInformations(int id) throws SQLException, ClassNotFoundException
    {
        JSONObject obj = new JSONObject();
        ResultSet res = null;

        DBConnection conn = new DBConnection();

        String query = Queries.getPatientInfoByID(id);

        res = conn.executeQuery(query);
        if (res == null)
        {
            System.err.println("wrong query");
            return null;
        }

        while (res != null && res.next())
        {
            obj.put("name", res.getString("name"));
            obj.put("surname", res.getString("surname"));

            obj.put("address", res.getString("address"));
            obj.put("email", res.getString("email"));
            obj.put("phone", res.getString("phone"));
            obj.put("birth_day", res.getString("birth_date"));
            obj.put("amka", res.getString("amka"));
            obj.put("at", res.getString("at"));
            obj.put("insurance", res.getString("insurance"));
            obj.put("username", res.getString("username"));
        }

        conn.closeDBConnection();
        return obj;
    }

}
