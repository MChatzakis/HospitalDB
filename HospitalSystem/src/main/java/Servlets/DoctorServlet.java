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
import org.json.JSONArray;
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
        int currentDutyTime = 2;
        int request_id = Integer.parseInt(request.getParameter("requestID"));
        System.out.println("request id is  : " + request_id);
        try {

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            switch (request_id) {
            case 1:
                obj = getPersonalAndDrugInfo((Integer) request.getSession(false).getAttribute("user_id"),currentDutyTime);
                break;
            case 2:
                obj = getMedicalAndExaminationInfo((Integer) request.getSession(false).getAttribute("user_id"),currentDutyTime);
                break;
            case 3:
                obj = getCurrentPatientsInfo((Integer) request.getSession(false).getAttribute("user_id"),currentDutyTime);
                break;
            }
            out.print(obj);
            out.flush();
            System.out.println(obj.toString(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getMedicalAndExaminationInfo(int user_id, int currentDutyTime) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        JSONObject obj = new JSONObject();

        int counter = 0;

        ResultSet res = null;

        String examinationsQuery = "SELECT examinations.exam_id, examinations.patient_id, examinations.drug_id, examinations.illness_id ,examinations.doctor_id\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
                + "WHERE visit.dutytime_id = " + currentDutyTime + ";";

        String medicalsQuery = "SELECT medicals.medical_id, medicals.exam_id, medicals.patient_id, medicals.nurse_id, medicals.doctor_id,medicals.type\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
                + "INNER JOIN medicals ON medicals.exam_id = examinations.exam_id\n"
                + "WHERE visit.dutytime_id =" + currentDutyTime + ";";

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

    public JSONObject getPersonalAndDrugInfo(int user_id, int currentDutyTime) throws SQLException, ClassNotFoundException {
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

    public JSONObject getCurrentPatientsInfo(int user_id, int currentDutyTime) throws SQLException, ClassNotFoundException {
        JSONObject patients = new JSONObject();

        String patientsQuery = "SELECT visit.visit_id, visit.date, patients.patient_id, patients.name, patients.surname, patients.birth_date, patients.amka\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "WHERE visit.dutytime_id = " + currentDutyTime + ";";

        DBConnection conn = new DBConnection();
        ResultSet res = null;
        ResultSet res2 = null;
        ResultSet res3 = null;
        int patients_counter = 0;
        int diseases_counter = 0;
        int symptoms_counter = 0;

        res = conn.executeQuery(patientsQuery);

        while (res != null && res.next()) {

            diseases_counter = 0;
            symptoms_counter = 0;

            patients.put("visit_id" + patients_counter, res.getString("visit_id"));
            patients.put("date" + patients_counter, res.getString("date"));
            patients.put("patient_id" + patients_counter, res.getString("patient_id"));
            patients.put("name" + patients_counter, res.getString("name"));
            patients.put("surname" + patients_counter, res.getString("surname"));
            patients.put("birth_date" + patients_counter, res.getString("birth_date"));
            patients.put("amka" + patients_counter, res.getString("amka"));

            res2 = conn.executeQuery(getChronicDisOfPatient(Integer.parseInt(res.getString("patient_id"))));
            JSONArray diseases = new JSONArray();
            while (res2 != null && res2.next()) {
                diseases.put(res2.getString("disease"));
                diseases_counter++;
            }
            patients.put("diseases_counter" + patients_counter, diseases_counter);
            patients.put("diseases_array" + patients_counter, diseases);
            
            res3 = conn.executeQuery(getCurrentPatientSymptoms(Integer.parseInt(res.getString("patient_id")), currentDutyTime));
            JSONArray symptoms = new JSONArray();
            while (res3 != null && res3.next()) {
                symptoms.put(res3.getString("symptom"));
                symptoms_counter++;
            }
            patients.put("symptoms_counter" + patients_counter, symptoms_counter);
            patients.put("symptoms_array" + patients_counter, symptoms);
            
            patients_counter++;
        }

        patients.put("patientsNumber", patients_counter);

        return patients;
    }

    public String getChronicDisOfPatient(int patientID) {
        String query = "SELECT patients_chronic_diseases.disease\n"
                + "FROM patients_chronic_diseases\n"
                + "WHERE patients_chronic_diseases.patient_id = " + patientID + ";";
        return query;
    }

    public String getCurrentPatientSymptoms(int patientID, int dutyTimeID) {
        String query = "SELECT visit_symptoms.symptom\n"
                + "FROM visit_symptoms\n"
                + "INNER JOIN visit ON visit_symptoms.visit_id = visit.visit_id\n"
                + "WHERE visit.dutytime_id = " + dutyTimeID + " AND visit.patient_id = " + patientID + ";";
        return query;
    }
}
