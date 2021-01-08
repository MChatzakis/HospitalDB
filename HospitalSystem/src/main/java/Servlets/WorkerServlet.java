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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author George
 */
public class WorkerServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WorkerSite.jsp").forward(request, response);
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
        int workerID = (Integer) request.getSession(false).getAttribute("user_id");
        int request_id = Integer.parseInt(request.getParameter("requestID"));

        System.out.println("request id is  : " + request_id);

        try {

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            switch (request_id) {
            case 1:
                obj = getPersonalAndDrugInfo(workerID, currentDutyTime);
                out.print(obj);
                out.flush();
                System.out.println(obj.toString(0));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getPersonalAndDrugInfo(int user_id, int currentDutyTime) throws SQLException, ClassNotFoundException {
        JSONObject obj = new JSONObject();
        DBConnection conn = new DBConnection();
        int counter = 0;

        ResultSet res = null;
        String infoQuery = getWorkerInfoByID(user_id);
        String drugsQuery = "SELECT drugs.drug_id, drugs.name AS drug_name, drugs.type AS drug_type, drugs.dosage, drugs.illness_id, illnesses.name AS illness_name\n"
                + "FROM drugs\n"
                + "INNER JOIN illnesses ON drugs.illness_id = illnesses.illness_id;";
        String dutyTimeQuery = "SELECT dutytime.date\n"
                + "FROM dutytime\n"
                + "INNER JOIN coordinator_duties ON dutytime.dutytime_id = coordinator_duties.dutytime_id\n"
                + "WHERE coordinator_duties.coordinator_id = " + user_id + ";";

        res = conn.executeQuery(infoQuery);

        if (res == null) {
            System.err.println("something went wrong!");
            return null;
        }

        while (res != null && res.next()) {
            obj.put("w_name", res.getString("name"));
            obj.put("w_surname", res.getString("surname"));
            obj.put("w_address", res.getString("address"));
            obj.put("w_phone", res.getString("phone"));
            obj.put("w_at", res.getString("at"));
            obj.put("w_username", res.getString("username"));
            obj.put("w_email", res.getString("email"));
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

    public static String getWorkerInfoByID(int user_id) {
        String q = "SELECT * \n"
                + "FROM coordinators\n"
                + "INNER JOIN users ON users.user_id = coordinators.coordinator_id\n"
                + "WHERE coordinators.coordinator_id = " + user_id + ";";
        return q;
    }

    public JSONObject getCurrentDutyStuff(int user_id, int currentDutyTime) throws SQLException, ClassNotFoundException {
        JSONObject staff = new JSONObject();
        DBConnection conn = new DBConnection();

        String getDoctors = "SELECT doctor_duties.doctor_id, doctors.name, doctors.surname, doctors.type\n"
                + "FROM doctor_duties\n"
                + "INNER JOIN doctors ON doctor_duties.doctor_id = doctors.doctor_id\n"
                + "WHERE dutytime_id = " + currentDutyTime + ";";
        String getNurses = "SELECT nurse_duties.nurse_id, nurses.name, nurses.surname\n"
                + "FROM nurse_duties\n"
                + "INNER JOIN nurses ON nurses.nurse_id = nurse_duties.nurse_id\n"
                + "WHERE nurse_duties.dutytime_id = " + currentDutyTime + "";
        String getWorkers = "SELECT coordinator_duties.coordinator_id, coordinators.name, coordinators.surname\n"
                + "FROM coordinator_duties\n"
                + "INNER JOIN coordinators ON coordinator_duties.coordinator_id = coordinators.coordinator_id\n"
                + "WHERE coordinator_duties.dutytime_id = " + currentDutyTime + ";";

        ResultSet res = null;

        int counter = 0;

        res = conn.executeQuery(getDoctors);
        while (res != null && res.next()) {
            staff.put("d_doctor_id" + counter, res.getString("doctor_id"));
            staff.put("d_name" + counter, res.getString("name"));
            staff.put("d_surname" + counter, res.getString("surname"));
            staff.put("d_type" + counter, res.getString("type"));
            counter++;
        }

        staff.put("doctorsNumber", counter);

        counter = 0;
        res = conn.executeQuery(getNurses);
        while (res != null && res.next()) {
            staff.put("n_nurse_id" + counter, res.getString("nurse_id"));
            staff.put("n_name" + counter, res.getString("name"));
            staff.put("n_surname" + counter, res.getString("surname"));
            counter++;
        }
        staff.put("nursesNumber", counter);

        counter = 0;
        res = conn.executeQuery(getWorkers);
        while (res != null && res.next()) {
            staff.put("w_worker_id" + counter, res.getString("coordinator_id"));
            staff.put("w_name" + counter, res.getString("name"));
            staff.put("w_surname" + counter, res.getString("surname"));
            counter++;
        }
        staff.put("workersNumber", counter);

        return staff;
    }

    public JSONObject getCurrentPatientsInfo(int currentDutyTime) throws SQLException, ClassNotFoundException {
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

    public JSONObject getAllDoctors(){
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
