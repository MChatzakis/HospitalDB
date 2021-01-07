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

    int DOCTOR_DRUG_INFORMATION = 1;

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
        try {

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            switch (request_id) {
            case 1:
                obj = getPersonalAndDrugInfo((Integer) request.getSession(false).getAttribute("user_id"));
                break;
            case 2:
                obj = getMedicalAndExaminationInfo((Integer) request.getSession(false).getAttribute("user_id"));
                break;
            }
            out.print(obj);
            out.flush();
            System.out.println(obj.toString(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getMedicalAndExaminationInfo(int user_id) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        JSONObject obj = new JSONObject();

        int counter = 0;
        int currentDutyNumber = 1;

        ResultSet res = null;

        String examinationsQuery = "SELECT examinations.exam_id, examinations.patient_id, examinations.drug_id, examinations.illness_id ,examinations.doctor_id\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
                + "WHERE visit.dutytime_id = " + currentDutyNumber + ";";
        String medicalsQuery = "SELECT medicals.medical_id, medicals.exam_id, medicals.patient_id, medicals.nurse_id, medicals.doctor_id,medicals.type\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
                + "INNER JOIN medicals ON medicals.exam_id = examinations.exam_id\n"
                + "WHERE visit.dutytime_id =" + currentDutyNumber + ";";

        res = conn.executeQuery(examinationsQuery);
        counter = 0;

        while (res != null && res.next()) {
            obj.put("exam_id" + counter, res.getString("exam_id"));
            obj.put("patient_id" + counter, res.getString("patient_id"));
            obj.put("drug_id" + counter, res.getString("drug_id"));
            obj.put("illness_id" + counter, res.getString("illness_id"));
            obj.put("doctor_id" + counter, res.getString("doctor_id"));
            counter++;
        }

        obj.put("examsNumber", counter);

        res = conn.executeQuery(medicalsQuery);
        counter = 0;

        while (res != null && res.next()) {
            obj.put("m_medical_id" + counter, res.getString("medical_id"));
            obj.put("m_exam_id" + counter, res.getString("exam_id"));
            obj.put("m_patient_id" + counter, res.getString("patient_id"));
            obj.put("m_nurse_id" + counter, res.getString("nurse_id"));
            obj.put("m_doctor_id" + counter, res.getString("doctor_id"));
            obj.put("m_type" + counter, res.getString("type"));
            counter++;
        }

        obj.put("medicalsNumber", counter);

        conn.closeDBConnection();
        return obj;
    }

    public JSONObject getPersonalAndDrugInfo(int user_id) throws SQLException, ClassNotFoundException {
        JSONObject obj = new JSONObject();
        DBConnection conn = new DBConnection();
        int counter = 0;

        ResultSet res = null;
        String infoQuery = Queries.getDoctorInfoByID(user_id);
        String drugsQuery = "SELECT drugs.drug_id, drugs.name AS drug_name, drugs.type AS drug_type, drugs.dosage, drugs.illness_id, illnesses.name AS illness_name\n"
                + "FROM drugs\n"
                + "INNER JOIN illnesses ON drugs.illness_id = illnesses.illness_id;";
        String dutyTimeQuery = "SELECT dutytime.date\n"
                + "FROM dutytime\n"
                + "INNER JOIN doctor_duties ON dutytime.dutytime_id = doctor_duties.dutytime_id\n"
                + "WHERE doctor_duties.doctor_id = " + user_id + ";";

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
            obj.put("drug_id" + counter, res.getString("drug_id"));
            obj.put("drug_name" + counter, res.getString("drug_name"));
            obj.put("drug_type" + counter, res.getString("drug_type"));
            obj.put("dosage" + counter, res.getString("dosage"));
            obj.put("illness_id" + counter, res.getString("illness_id"));
            obj.put("illness_name" + counter, res.getString("illness_name"));
            counter++;
        }

        res = conn.executeQuery(dutyTimeQuery);
        counter = 0;

        while (res != null && res.next()) {
            obj.put("duty" + counter, res.getString("date"));
            counter++;
        }

        obj.put("dutytimes", counter);

        conn.closeDBConnection();
        return obj;
    }

}
