/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author George
 */
public class Statistics extends HttpServlet
{

    int MONTHLY_DUTY_STATS = 1;
    int DAILY_DUTY_STATS = 2;
    int COVID_19_REPORT = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("Statistics.jsp").forward(request, response);

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
        System.out.println("statistics table");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int request_id = Integer.parseInt(request.getParameter("requestID"));
        if (request_id == MONTHLY_DUTY_STATS)
        {
            System.out.println("MONTHLY_DUTY_STATS");

            try
            {
                String  month = request.getParameter("month");
                JSONArray dutyArrray = CreateDutyMonthlyStatistics(month, month);
                out.print(dutyArrray);
                out.flush();
                System.out.println(dutyArrray.toString(0));
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (request_id == DAILY_DUTY_STATS)
        {
            System.out.println("Daily_duty_stats");

            try
            {
                JSONArray dutyArrray = CreateDutyDailyStatistics("2021-01-06", "2021-01-06");
                out.print(dutyArrray);
                out.flush();
                System.out.println(dutyArrray.toString(0));
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (request_id == COVID_19_REPORT)
        {
            JSONObject obj = new JSONObject();
            try
            {
                PrintWriter out2 = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                obj = CreateCovidReport();
                out2.print(obj);
                out2.flush();
                System.out.println(obj.toString());
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public JSONObject CreateCovidReport() throws SQLException, ClassNotFoundException
    {
        DBConnection conn;

        conn = new DBConnection();

        JSONObject obj = new JSONObject();
        ResultSet res = null;
        ResultSet res2 = null;

        String covidPersonalInformationQuery = "SELECT visit.visit_id ,  patients.name , patients.surname ,patients.address , patients.phone, patients.birth_date \n"
                + "FROM patients\n"
                + "INNER JOIN examinations ON examinations.patient_id= patients.patient_id  AND examinations.illness_id=" + 1 + " \n"
                + "INNER JOIN visit ON visit.visit_id = examinations.visit_id\n";

        String chronicDiseasesQuery = "SELECT visit.visit_id,patients_chronic_diseases.disease \n"
                + "FROM patients\n"
                + "INNER JOIN examinations ON examinations.patient_id= patients.patient_id  AND examinations.illness_id=" + 1 + " \n"
                + "INNER JOIN visit ON visit.visit_id = examinations.visit_id\n"
                + "INNER JOIN patients_chronic_diseases ON patients_chronic_diseases.patient_id= patients.patient_id";

        res = conn.executeQuery(covidPersonalInformationQuery);
        int counter = 0;
        while (res != null && res.next())
        {
            int vis_id = res.getInt("visit_id");
            obj.put("visit" + counter, vis_id);
            obj.put("name" + counter, res.getString("name"));
            obj.put("surname" + counter, res.getString("surname"));
            obj.put("address" + counter, res.getString("address"));
            obj.put("phone" + counter, res.getString("phone"));
            obj.put("birth_date" + counter, res.getString("birth_date"));

            res2 = conn.executeQuery(chronicDiseasesQuery);
            String chronicDieseases = "";
            while (res2 != null && res2.next())
            {
                if (vis_id == res2.getInt("visit_id"))
                {
                    chronicDieseases += res2.getString("disease");
                    chronicDieseases += ",";
                }

            }
            if (!chronicDieseases.equals(""))
            {
                chronicDieseases = chronicDieseases.substring(0, chronicDieseases.length() - 1);
                obj.put("diseases" + counter, chronicDieseases);

            }

            counter++;
        }
        obj.put("len", counter);

        System.out.println("return from covid");
        return obj;
    }

    public JSONArray CreateDutyDailyStatistics(String from, String to) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;

        dutyArrray.put(from);

        String drugsQuery = "SELECT COUNT(examinations.drug_id)  AS drugs_amount FROM examinations\n"
                + "WHERE  (examinations.date) BETWEEN ("
                + "\'" + from + "\'" + ")AND (" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(drugsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("drugs_amount"));
        }
        String incidentsQuery = "SELECT COUNT(examinations.visit_id) AS incidents_amount FROM examinations\n"
                + "WHERE  (examinations.date) BETWEEN ("
                + "\'" + from + "\'" + ")AND (" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(incidentsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("incidents_amount"));
        }
        String examinationsQuery = "SELECT COUNT(examinations.exam_id)  AS examinations_amount FROM examinations\n"
                + "WHERE  (examinations.date) BETWEEN ("
                + "\'" + from + "\'" + ")AND (" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(examinationsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("examinations_amount"));
        }
        String illnessQuery = "SELECT COUNT(examinations.illness_id)  AS illnesses_amount FROM examinations\n"
                + "WHERE  (examinations.date) BETWEEN ("
                + "\'" + from + "\'" + ")AND (" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(illnessQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("illnesses_amount"));
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

    public JSONArray CreateDutyMonthlyStatistics(String from, String to) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;

        dutyArrray.put(from);

        String drugsQuery = "SELECT COUNT(examinations.drug_id)  AS drugs_amount FROM examinations\n"
                + "WHERE  MONTH(examinations.date) BETWEEN MONTH("
                + "\'" + from + "\'" + ")AND MONTH(" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(drugsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("drugs_amount"));
        }
        String incidentsQuery = "SELECT COUNT(examinations.visit_id) AS incidents_amount FROM examinations\n"
                + "WHERE  MONTH(examinations.date) BETWEEN MONTH("
                + "\'" + from + "\'" + ")AND MONTH(" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(incidentsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("incidents_amount"));
        }
        String examinationsQuery = "SELECT COUNT(examinations.exam_id)  AS examinations_amount FROM examinations\n"
                + "WHERE  MONTH(examinations.date) BETWEEN MONTH("
                + "\'" + from + "\'" + ")AND MONTH(" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(examinationsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("examinations_amount"));
        }
        String illnessQuery = "SELECT COUNT(examinations.illness_id)  AS illnesses_amount FROM examinations\n"
                + "WHERE  MONTH(examinations.date) BETWEEN MONTH("
                + "\'" + from + "\'" + ")AND MONTH(" + "\'" + to + "\'" + ");";

        res = conn.executeQuery(illnessQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("illnesses_amount"));
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

}
