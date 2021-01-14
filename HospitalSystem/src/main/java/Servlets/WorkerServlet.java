/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import commons.JavaDate;
import commons.QueryParser;
import commons.RandomGenerator;
import commons.StringParser;
import database.DBConnection;
import database.entities.DutyTime;
import database.entities.Visit;
import database.entities.users.Coordinator;
import database.entities.users.Doctor;
import database.entities.users.Nurse;
import database.entities.users.Patient;
import database.relations.OnDutyDoctors;
import database.relations.OnDutyNurses;
import database.relations.OnDutyWorkers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class WorkerServlet extends HttpServlet
{

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        JSONObject obj = new JSONObject();

        String username = "";
        String password = "";
        String name = "";
        String surname = "";
        String address = "";
        String email = "";
        String phone = "";
        String at = "";
        String type = "";
        String bd = "";
        String amka = "";
        String insurance = "";
        String cd = "";

        String query = "";

        int currentDutyTime = 20;

        int workerID = (Integer) request.getSession(false).getAttribute("user_id");
        int request_id = Integer.parseInt(request.getParameter("requestID"));

        System.out.println("request id is  : " + request_id);

        try
        {

            PrintWriter out = response.getWriter();
            currentDutyTime = new DutyTime().getDutyIDFromDate(JavaDate.getDefaultDate());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            switch (request_id)
            {
                case 1:
                    String from = request.getParameter("from");
                    String to = request.getParameter("to");
                    obj = getPersonalAndDrugInfo(workerID, currentDutyTime, from, to);
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 2:
                    obj = getCurrentPatientsInfo(currentDutyTime);
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 3:
                    obj = getCurrentDutyStuff(currentDutyTime);
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 4:
                    obj = getDoctorsNursesWorkers();
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 5:
                    obj = getAllPatients();
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 6:
                    obj = getAllExams();
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 7:
                    obj = getAllVisits();
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 8:
                    obj = getAllDuties();
                    out.print(obj);
                    out.flush();
                    System.out.println(obj.toString(0));
                    break;
                case 9:
                    username = request.getParameter("p_username");
                    password = request.getParameter("p_password");
                    email = request.getParameter("p_email");
                    name = request.getParameter("p_name");
                    surname = request.getParameter("p_surname");
                    bd = request.getParameter("p_bd");
                    address = request.getParameter("p_address");
                    phone = request.getParameter("p_phone");
                    amka = request.getParameter("p_amka");
                    at = request.getParameter("p_at");
                    cd = request.getParameter("p_cd");
                    insurance = request.getParameter("p_insurance");

                    addPatient(username, password, email, name, surname, bd, address, amka, at, phone, insurance, cd);
                    break;
                case 10:
                    String v_patientID = request.getParameter("v_patientID");
                    String v_date = request.getParameter("v_date");
                    String v_s = request.getParameter("v_s");
                    String v_duty = currentDutyTime + "";

                    addVisit(v_duty, v_patientID, v_date, v_s);
                    break;
                case 11:
                    String d_coordinatorID = workerID + "";
                    String d_doctorID = request.getParameter("d_doctorID");
                    String d_nurseID = request.getParameter("d_nurseID");
                    String d_workerID = request.getParameter("d_workerID");
                    String d_date = request.getParameter("d_date");

                    addDutyTime(d_coordinatorID, d_doctorID, d_nurseID, d_workerID, d_date);
                    break;
                case 12:
                    username = request.getParameter("dd_username");
                    password = request.getParameter("dd_password");
                    name = request.getParameter("dd_name");
                    surname = request.getParameter("dd_surname");
                    address = request.getParameter("dd_address");
                    phone = request.getParameter("dd_phone");
                    email = request.getParameter("dd_email");
                    type = request.getParameter("dd_type");
                    at = request.getParameter("dd_at");

                    new Doctor().addDoctor(username, password, name, surname, address, email, phone, type, at);
                    break;
                case 13:
                    username = request.getParameter("nn_username");
                    password = request.getParameter("nn_password");
                    name = request.getParameter("nn_name");
                    surname = request.getParameter("nn_surname");
                    address = request.getParameter("nn_address");
                    phone = request.getParameter("nn_phone");
                    email = request.getParameter("nn_email");
                    at = request.getParameter("nn_at");

                    new Nurse().addNurse(username, password, name, surname, address, email, phone, at);
                    break;
                case 14:
                    username = request.getParameter("ww_username");
                    password = request.getParameter("ww_password");
                    name = request.getParameter("ww_name");
                    surname = request.getParameter("ww_surname");
                    address = request.getParameter("ww_address");
                    phone = request.getParameter("ww_phone");
                    email = request.getParameter("ww_email");
                    at = request.getParameter("ww_at");

                    new Coordinator().addCoordinator(username, password, name, surname, address, email, phone, at);
                    break;
                case 15:
                    query = request.getParameter("queryS");
                    obj = QueryParser.parseQuery(query);
                    out.print(obj);
                    System.out.println(obj.toString(0));
                    out.flush();
                    break;
                case 16:
                    query = request.getParameter("queryU");
                    QueryParser.executeRandomQuery(query);
                    break;
                case 17:
                    String wID = workerID + "";
                    String date = request.getParameter("d_date");
                    setRandomDuty(date, wID);
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addDutyTime(String cID, String doctorIDs, String nurseIDs, String workerIDs, String date) throws SQLException, ClassNotFoundException
    {
        DutyTime duty = new DutyTime();

        OnDutyDoctors duty_doc = new OnDutyDoctors();
        OnDutyNurses duty_nur = new OnDutyNurses();
        OnDutyWorkers duty_wor = new OnDutyWorkers();

        String dutyTimeID = duty.addDutyTime(date, cID) + "";

        String[] doctorID = StringParser.parseString(doctorIDs, ",");
        for (int i = 0; i < doctorID.length; i++)
        {
            duty_doc.addDoctorDutyTime(doctorID[i], dutyTimeID);
        }

        String[] nurseID = StringParser.parseString(nurseIDs, ",");
        for (int i = 0; i < nurseID.length; i++)
        {
            duty_nur.addNurseDutyTime(nurseID[i], dutyTimeID);
        }

        String[] workerID = StringParser.parseString(workerIDs, ",");
        for (int i = 0; i < workerID.length; i++)
        {
            duty_wor.addWorkerDutyTime(workerID[i], dutyTimeID);
        }

    }

    public void addVisit(String dutyID, String patientID, String date, String symptoms) throws SQLException, ClassNotFoundException
    {
        Visit vis = new Visit();
        String visitID = vis.addVisit(date, dutyID, patientID) + "";
        String[] symptom = StringParser.parseString(symptoms, ",");
        for (int i = 0; i < symptom.length; i++)
        {
            vis.addSymptom(visitID, symptom[i]);
        }

    }

    public void addPatient(String username, String password, String email, String name, String surname, String birthDate, String address, String amka, String at, String phone, String insurance, String chronicDis) throws SQLException, ClassNotFoundException
    {
        Patient pat = new Patient();
        String[] chronics = StringParser.parseString(chronicDis, ",");
        String pat_id = pat.addPatient(username, password, name, surname, address, email, phone, birthDate, amka, at, insurance) + "";
        for (int i = 0; i < chronics.length; i++)
        {
            pat.addChronicDisease(pat_id, chronics[i]);
        }
    }

    public JSONObject getPersonalAndDrugInfo(int user_id, int currentDutyTime, String from, String to) throws SQLException, ClassNotFoundException
    {
        JSONObject obj = new JSONObject();
        DBConnection conn = new DBConnection();
        int counter = 0;
        String dutyTimeQuery;
        ResultSet res = null;
        String infoQuery = getWorkerInfoByID(user_id);
        String drugsQuery = "SELECT drugs.drug_id, drugs.name AS drug_name, drugs.type AS drug_type, drugs.dosage, drugs.illness_id, illnesses.name AS illness_name\n"
                + "FROM drugs\n"
                + "INNER JOIN illnesses ON drugs.illness_id = illnesses.illness_id;";
        if (from != null && to != null)
        {
            dutyTimeQuery = "SELECT dutytime.date\n"
                    + "FROM dutytime\n"
                    + "INNER JOIN coordinator_duties ON dutytime.dutytime_id = coordinator_duties.dutytime_id\n"
                    + "WHERE coordinator_duties.coordinator_id = " + user_id + " AND dutytime.date BETWEEN ("
                    + "\'" + from + "\'" + ") AND (" + "\'" + to + "\'" + ");";
        }
        else
        {
            dutyTimeQuery = "SELECT dutytime.date\n"
                    + "FROM dutytime\n"
                    + "INNER JOIN coordinator_duties ON dutytime.dutytime_id = coordinator_duties.dutytime_id\n"
                    + "WHERE coordinator_duties.coordinator_id = " + user_id + ";";

        }

        res = conn.executeQuery(infoQuery);

        if (res == null)
        {
            System.err.println("something went wrong!");
            return null;
        }

        while (res != null && res.next())
        {
            obj.put("w_name", res.getString("name"));
            obj.put("w_surname", res.getString("surname"));
            obj.put("w_address", res.getString("address"));
            obj.put("w_phone", res.getString("phone"));
            obj.put("w_at", res.getString("at"));
            obj.put("w_username", res.getString("username"));
            obj.put("w_email", res.getString("email"));
        }

        res = conn.executeQuery(drugsQuery);

        while (res != null && res.next())
        {
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

        while (res != null && res.next())
        {
            obj.put("duty" + counter, res.getString("date"));
            counter++;
        }

        obj.put("dutytimes", counter);

        conn.closeDBConnection();
        return obj;
    }

    public static String getWorkerInfoByID(int user_id)
    {
        String q = "SELECT * \n"
                + "FROM coordinators\n"
                + "INNER JOIN users ON users.user_id = coordinators.coordinator_id\n"
                + "WHERE coordinators.coordinator_id = " + user_id + ";";
        return q;
    }

    public JSONObject getCurrentDutyStuff(int currentDutyTime) throws SQLException, ClassNotFoundException
    {
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
        while (res != null && res.next())
        {
            staff.put("d_doctor_id" + counter, res.getString("doctor_id"));
            staff.put("d_name" + counter, res.getString("name"));
            staff.put("d_surname" + counter, res.getString("surname"));
            staff.put("d_type" + counter, res.getString("type"));
            counter++;
        }

        staff.put("doctorsNumber", counter);

        counter = 0;
        res = conn.executeQuery(getNurses);
        while (res != null && res.next())
        {
            staff.put("n_nurse_id" + counter, res.getString("nurse_id"));
            staff.put("n_name" + counter, res.getString("name"));
            staff.put("n_surname" + counter, res.getString("surname"));
            staff.put("n_nurse" + counter, "nurse");
            counter++;
        }
        staff.put("nursesNumber", counter);

        counter = 0;
        res = conn.executeQuery(getWorkers);
        while (res != null && res.next())
        {
            staff.put("w_worker_id" + counter, res.getString("coordinator_id"));
            staff.put("w_name" + counter, res.getString("name"));
            staff.put("w_surname" + counter, res.getString("surname"));
            staff.put("w_worker" + counter, "worker");
            counter++;
        }
        staff.put("workersNumber", counter);

        return staff;
    }

    public JSONObject getCurrentPatientsInfo(int currentDutyTime) throws SQLException, ClassNotFoundException
    {
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

        while (res != null && res.next())
        {

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
            while (res2 != null && res2.next())
            {
                diseases.put(res2.getString("disease"));
                diseases_counter++;
            }
            patients.put("diseases_counter" + patients_counter, diseases_counter);
            patients.put("diseases_array" + patients_counter, diseases);

            res3 = conn.executeQuery(getCurrentPatientSymptoms(Integer.parseInt(res.getString("patient_id")), currentDutyTime));
            JSONArray symptoms = new JSONArray();
            while (res3 != null && res3.next())
            {
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

    public JSONObject getDoctorsNursesWorkers() throws SQLException, ClassNotFoundException
    {
        JSONObject stuff = new JSONObject();
        DBConnection conn = new DBConnection();
        ResultSet res = null;
        int counter = 0;
        String doctors = "SELECT *\n"
                + "FROM doctors\n"
                + "INNER JOIN users ON doctors.doctor_id = users.user_id;";
        String nurses = "SELECT *\n"
                + "FROM nurses\n"
                + "INNER JOIN users ON users.user_id = nurses.nurse_id;";
        String workers = "SELECT *\n"
                + "FROM coordinators\n"
                + "INNER JOIN users ON users.user_id = coordinators.coordinator_id;";

        res = conn.executeQuery(doctors);
        while (res != null && res.next())
        {
            stuff.put("d_doctor_id" + counter, res.getString("doctor_id"));
            stuff.put("d_name" + counter, res.getString("name"));
            stuff.put("d_surname" + counter, res.getString("surname"));
            stuff.put("d_address" + counter, res.getString("address"));
            stuff.put("d_phone" + counter, res.getString("phone"));
            stuff.put("d_type" + counter, res.getString("type"));
            stuff.put("d_at" + counter, res.getString("at"));
            stuff.put("d_username" + counter, res.getString("username"));
            stuff.put("d_email" + counter, res.getString("email"));
            counter++;
        }
        stuff.put("doctorsNumber", counter);

        counter = 0;
        res = conn.executeQuery(nurses);
        while (res != null && res.next())
        {
            stuff.put("n_nurse_id" + counter, res.getString("nurse_id"));
            stuff.put("n_name" + counter, res.getString("name"));
            stuff.put("n_surname" + counter, res.getString("surname"));
            stuff.put("n_address" + counter, res.getString("address"));
            stuff.put("n_phone" + counter, res.getString("phone"));
            stuff.put("n_at" + counter, res.getString("at"));
            stuff.put("n_username" + counter, res.getString("username"));
            stuff.put("n_email" + counter, res.getString("email"));
            counter++;
        }
        stuff.put("nursesNumber", counter);

        counter = 0;
        res = conn.executeQuery(workers);
        while (res != null && res.next())
        {
            stuff.put("w_worker_id" + counter, res.getString("coordinator_id"));
            stuff.put("w_name" + counter, res.getString("name"));
            stuff.put("w_surname" + counter, res.getString("surname"));
            stuff.put("w_address" + counter, res.getString("address"));
            stuff.put("w_phone" + counter, res.getString("phone"));
            stuff.put("w_at" + counter, res.getString("at"));
            stuff.put("w_username" + counter, res.getString("username"));
            stuff.put("w_email" + counter, res.getString("email"));
            counter++;
        }
        stuff.put("workersNumber", counter);

        return stuff;
    }

    public JSONObject getAllPatients() throws SQLException, ClassNotFoundException
    {
        JSONObject patients = new JSONObject();

        String patientsQuery = "SELECT *\n"
                + "FROM patients\n"
                + "INNER JOIN users ON users.user_id = patients.patient_id;";

        DBConnection conn = new DBConnection();

        ResultSet res = null;
        ResultSet res2 = null;

        int patients_counter = 0;
        int diseases_counter = 0;

        res = conn.executeQuery(patientsQuery);

        while (res != null && res.next())
        {

            diseases_counter = 0;

            patients.put("patient_id" + patients_counter, res.getString("patient_id"));
            patients.put("name" + patients_counter, res.getString("name"));
            patients.put("surname" + patients_counter, res.getString("surname"));
            patients.put("address" + patients_counter, res.getString("address"));
            patients.put("phone" + patients_counter, res.getString("phone"));
            patients.put("birth_date" + patients_counter, res.getString("birth_date"));
            patients.put("amka" + patients_counter, res.getString("amka"));
            patients.put("at" + patients_counter, res.getString("at"));
            patients.put("insurance" + patients_counter, res.getString("insurance"));
            patients.put("username" + patients_counter, res.getString("username"));
            patients.put("email" + patients_counter, res.getString("email"));

            res2 = conn.executeQuery(getChronicDisOfPatient(Integer.parseInt(res.getString("patient_id"))));
            JSONArray diseases = new JSONArray();
            while (res2 != null && res2.next())
            {
                diseases.put(res2.getString("disease"));
                diseases_counter++;
            }
            patients.put("diseases_counter" + patients_counter, diseases_counter);
            patients.put("diseases_array" + patients_counter, diseases);

            patients_counter++;
        }

        patients.put("patientsNumber", patients_counter);
        return patients;
    }

    public JSONObject getAllExams() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONObject obj = new JSONObject();

        int counter = 0;

        ResultSet res = null;

        String tripleJoin = "SELECT examinations.exam_id, examinations.patient_id, examinations.doctor_id, examinations.drug_id, examinations.illness_id, examinations.visit_id, examinations.date, medicals.medical_id, medicals.nurse_id, medicals.doctor_id AS order_doctor_id, medicals.type, examinations_retaken.re_exam_id, examinations_retaken.doctor_id AS re_doctor_id, examinations_retaken.hospitalization\n"
                + "FROM examinations\n"
                + "LEFT JOIN medicals ON examinations.exam_id = medicals.medical_id\n"
                + "LEFT JOIN examinations_retaken ON medicals.medical_id = examinations_retaken.re_exam_id";
        res = conn.executeQuery(tripleJoin);
        counter = 0;

        while (res != null && res.next())
        {
            obj.put("exam_id" + counter, res.getString("exam_id"));
            obj.put("patient_id" + counter, res.getString("patient_id"));
            obj.put("doctor_id" + counter, res.getString("doctor_id"));
            obj.put("drug_id" + counter, res.getString("drug_id"));
            obj.put("illness_id" + counter, res.getString("illness_id"));
            obj.put("visit_id" + counter, res.getString("visit_id"));
            obj.put("date" + counter, res.getString("date"));
            obj.put("medical_id" + counter, res.getString("medical_id"));
            obj.put("nurse_id" + counter, res.getString("nurse_id"));
            obj.put("type" + counter, res.getString("type"));
            obj.put("re_exam_id" + counter, res.getString("re_exam_id"));
            obj.put("re_doctor_id" + counter, res.getString("re_doctor_id"));
            obj.put("hospi" + counter, res.getString("hospitalization"));

            counter++;
        }

        obj.put("examsNumber", counter);

        conn.closeDBConnection();
        return obj;
    }

    public JSONObject getAllVisits() throws SQLException, ClassNotFoundException
    {
        String visits = "SELECT visit.visit_id, visit.date, visit.dutytime_id, visit.patient_id\n"
                + "FROM visit";
        DBConnection conn = new DBConnection();
        JSONObject obj = new JSONObject();

        int counter = 0;
        int symptomCounter = 0;

        ResultSet res = null;
        ResultSet res2 = null;

        res = conn.executeQuery(visits);
        while (res != null && res.next())
        {
            obj.put("visit_id" + counter, res.getString("visit_id"));
            obj.put("date" + counter, res.getString("date"));
            obj.put("dutytime_id" + counter, res.getString("dutytime_id"));
            obj.put("patient_id" + counter, res.getString("patient_id"));

            symptomCounter = 0;
            JSONArray s = new JSONArray();
            res2 = conn.executeQuery(getVisitSymptioms(Integer.parseInt(res.getString("visit_id"))));
            while (res2 != null && res2.next())
            {
                s.put(res2.getString("symptom"));
                symptomCounter++;
            }

            obj.put("symptomArray" + counter, s);
            obj.put("symptomNumber" + counter, symptomCounter);
            counter++;
        }
        obj.put("visitsNumber", counter);
        return obj;
    }

    public JSONObject getAllDuties() throws SQLException, ClassNotFoundException
    {
        String duties = "SELECT * \n"
                + "FROM dutytime";
        DBConnection conn = new DBConnection();
        JSONObject obj = new JSONObject();

        int counter = 0;
        int staffCounter = 0;

        ResultSet res = null;
        ResultSet res2 = null;

        res = conn.executeQuery(duties);
        while (res != null && res.next())
        {
            obj.put("dutytime_id" + counter, res.getString("dutytime_id"));
            obj.put("date" + counter, res.getString("date"));
            obj.put("coordinator_id" + counter, res.getString("coordinator_id"));

            JSONArray s = new JSONArray();
            res2 = conn.executeQuery(getDoctorsOfDutyTime(Integer.parseInt(res.getString("dutytime_id"))));
            while (res2 != null && res2.next())
            {
                s.put(res2.getString("doctor_id"));
                staffCounter++;
            }
            obj.put("doctorArray" + counter, s);
            obj.put("doctorNumber" + counter, staffCounter);

            s = new JSONArray();
            staffCounter = 0;
            res2 = conn.executeQuery(getNursesOfDutyTime(Integer.parseInt(res.getString("dutytime_id"))));
            while (res2 != null && res2.next())
            {
                s.put(res2.getString("nurse_id"));
                staffCounter++;
            }
            obj.put("nurseArray" + counter, s);
            obj.put("nurseNumber" + counter, staffCounter);

            s = new JSONArray();
            staffCounter = 0;
            res2 = conn.executeQuery(getWorkersOfDutyTime(Integer.parseInt(res.getString("dutytime_id"))));
            while (res2 != null && res2.next())
            {
                s.put(res2.getString("coordinator_id"));
                staffCounter++;
            }
            obj.put("workerArray" + counter, s);
            obj.put("workerNumber" + counter, staffCounter);

            counter++;
        }
        obj.put("dutyNumber", counter);
        return obj;
    }

    public String getChronicDisOfPatient(int patientID)
    {
        String query = "SELECT patients_chronic_diseases.disease\n"
                + "FROM patients_chronic_diseases\n"
                + "WHERE patients_chronic_diseases.patient_id = " + patientID + ";";
        return query;
    }

    public String getCurrentPatientSymptoms(int patientID, int dutyTimeID)
    {
        String query = "SELECT visit_symptoms.symptom\n"
                + "FROM visit_symptoms\n"
                + "INNER JOIN visit ON visit_symptoms.visit_id = visit.visit_id\n"
                + "WHERE visit.dutytime_id = " + dutyTimeID + " AND visit.patient_id = " + patientID + ";";
        return query;
    }

    public String getVisitSymptioms(int visitID)
    {
        String query = "SELECT visit_symptoms.symptom\n"
                + "FROM visit_symptoms\n"
                + "WHERE visit_id =" + visitID + ";";
        return query;
    }

    public String getDoctorsOfDutyTime(int dutyTimeID)
    {
        String query = "SELECT doctor_duties.doctor_id\n"
                + "FROM doctor_duties\n"
                + "WHERE doctor_duties.dutytime_id = " + dutyTimeID + ";";
        return query;
    }

    public String getNursesOfDutyTime(int dutyTimeID)
    {
        String query = "SELECT nurse_duties.nurse_id\n"
                + "FROM nurse_duties\n"
                + "WHERE nurse_duties.dutytime_id = " + dutyTimeID + ";";
        return query;
    }

    public String getWorkersOfDutyTime(int dutyTimeID)
    {
        String query = "SELECT coordinator_duties.coordinator_id\n"
                + "FROM coordinator_duties\n"
                + "WHERE coordinator_duties.dutytime_id = " + dutyTimeID + ";";
        return query;
    }

    public int setRandomDuty(String date, String coordinatorID) throws SQLException, ClassNotFoundException
    {

        Doctor doc = new Doctor();

        ArrayList<String> cardIDs = doc.getIDsOfDoctors("cardiologist");
        Collections.shuffle(cardIDs);

        ArrayList<String> endIDs = doc.getIDsOfDoctors("pathologist");
        Collections.shuffle(endIDs);

        ArrayList<String> orthIDs = doc.getIDsOfDoctors("endocrinologist");
        Collections.shuffle(orthIDs);

        ArrayList<String> pathIDs = doc.getIDsOfDoctors("orthopedic");
        Collections.shuffle(pathIDs);

        ArrayList<String> endoIDs = doc.getIDsOfDoctors("pulmonologist");;
        Collections.shuffle(endoIDs);

        ArrayList<String> nurseIDs = new Nurse().getIDsOfNurses();
        ArrayList<String> workerIDs = new Coordinator().getIDsOfWorker();

        String cardID = cardIDs.get(RandomGenerator.getRandomNumber(0, cardIDs.size() - 1));
        String endID = endIDs.get(RandomGenerator.getRandomNumber(0, endIDs.size() - 1));;
        String orthID = orthIDs.get(RandomGenerator.getRandomNumber(0, orthIDs.size() - 1));;
        String pathID = pathIDs.get(RandomGenerator.getRandomNumber(0, pathIDs.size() - 1));;
        String endoID = endoIDs.get(RandomGenerator.getRandomNumber(0, endoIDs.size() - 1));;

        String nurseID = "";
        String workerID = "";

        DutyTime dt = new DutyTime();
        OnDutyDoctors dd = new OnDutyDoctors();
        OnDutyNurses dn = new OnDutyNurses();
        OnDutyWorkers dw = new OnDutyWorkers();

        int dutyTimeID = dt.addDutyTime(date, coordinatorID);
        String strDutyID = dutyTimeID + "";

        dd.addDoctorDutyTime(cardID, strDutyID);
        dd.addDoctorDutyTime(endID, strDutyID);
        dd.addDoctorDutyTime(orthID, strDutyID);
        dd.addDoctorDutyTime(pathID, strDutyID);
        dd.addDoctorDutyTime(endoID, strDutyID);

        Collections.shuffle(nurseIDs);
        for (int j = 0; j < 3; j++)
        {
            dn.addNurseDutyTime(nurseIDs.get(j), strDutyID);
        }

        Collections.shuffle(workerIDs);
        for (int j = 0; j < 2; j++)
        {
            dw.addWorkerDutyTime(workerIDs.get(j), strDutyID);
        }

        return dutyTimeID;
    }

}
