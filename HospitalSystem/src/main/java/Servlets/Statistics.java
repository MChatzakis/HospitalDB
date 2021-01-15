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

/*
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
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
                JSONArray array = new JSONArray();
                String month = request.getParameter("month");
                array.put(CreateDutyMonthlyStatistics(month));
                array.put(CreateDrugsMonthlyStatistics(month));
                array.put(CreateIllnessMonthlyStatistics(month));

                out.print(array);
                out.flush();
                System.out.println(array.toString(0));
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
                JSONArray array = new JSONArray();

                String date = request.getParameter("date");
                array.put(CreateDutyDailyStatistics(date));
                array.put(getCountOfDrugs(date));

                array.put(getCountOfIllnesses(date));

                out.print(array);
                out.flush();
                System.out.println(array.toString(0));
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

        String covidPersonalInformationQuery = "SELECT visit.visit_id ,  patients.name ,patients.patient_id, patients.surname ,patients.address , patients.phone, patients.birth_date \n"
                + "FROM patients\n"
                + "INNER JOIN examinations ON examinations.patient_id= patients.patient_id  AND examinations.illness_id=" + 1 + " \n"
                + "INNER JOIN visit ON visit.visit_id = examinations.visit_id\n";

        //eisai malakas
        res = conn.executeQuery(covidPersonalInformationQuery);
        int counter = 0;
        while (res != null && res.next())
        {
            int vis_id = res.getInt("visit_id");
            int patientID = res.getInt("patient_id");
            obj.put("visit" + counter, vis_id);
            obj.put("name" + counter, res.getString("name"));
            obj.put("surname" + counter, res.getString("surname"));
            obj.put("address" + counter, res.getString("address"));
            obj.put("phone" + counter, res.getString("phone"));
            obj.put("birth_date" + counter, res.getString("birth_date"));

            String chronicDiseasesQuery = getChronicDisOfPatient(patientID);

            res2 = conn.executeQuery(chronicDiseasesQuery);
            String chronicDieseases = "";
            while (res2 != null && res2.next())
            {

                chronicDieseases += res2.getString("disease");
                chronicDieseases += ",";

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

    public JSONArray CreateDrugsMonthlyStatistics(String month) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;
        String[] onlyMonth = month.split("-");
        dutyArrray.put(onlyMonth[0] + "-" + onlyMonth[1]);
        for (int i = 0; i < 5; i++)
        {

            String query = "SELECT COUNT(examinations.drug_id)\n"
                    + "FROM examinations\n"
                    + "INNER JOIN visit ON examinations.visit_id = visit.visit_id\n"
                    + "INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id\n"
                    + "WHERE year(examinations.date) =year(" + "\'" + month + "\'" + ") \n"
                    + "AND month(examinations.date) =month(" + "\'" + month + "\'" + ")"
                    + "AND examinations.drug_id = " + (i + 1) + ";";

            res = conn.executeQuery(query);
            while (res != null && res.next())
            {
                dutyArrray.put(res.getInt(1));
            }
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

    public JSONArray CreateIllnessMonthlyStatistics(String month) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;
        String[] onlyMonth = month.split("-");
        dutyArrray.put(onlyMonth[0] + "-" + onlyMonth[1]);
        for (int i = 0; i < 5; i++)
        {

            String query = "SELECT COUNT(examinations.illness_id)\n"
                    + "FROM examinations\n"
                    + "INNER JOIN visit ON examinations.visit_id = visit.visit_id\n"
                    + "INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id\n"
                    + "WHERE year(examinations.date) =year(" + "\'" + month + "\'" + ") \n"
                    + "AND month(examinations.date) =month(" + "\'" + month + "\'" + ")"
                    + "AND examinations.illness_id = " + (i + 1) + ";";

            res = conn.executeQuery(query);
            while (res != null && res.next())
            {
                dutyArrray.put(res.getInt(1));
            }
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

    public JSONArray CreateDutyMonthlyStatistics(String month) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;

        String[] onlyMonth = month.split("-");
        dutyArrray.put(onlyMonth[0] + "-" + onlyMonth[1]);

        String incidentsQuery = "SELECT COUNT(examinations.visit_id) AS incidents_amount FROM examinations\n"
                + "WHERE year(examinations.date) =year(" + "\'" + month + "\'" + ") \n"
                + "AND month(examinations.date) =month(" + "\'" + month + "\'" + ")";

        res = conn.executeQuery(incidentsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("incidents_amount"));
        }
        String examinationsQuery = "SELECT COUNT(examinations.exam_id)  AS examinations_amount FROM examinations\n"
                + "WHERE year(examinations.date) =year(" + "\'" + month + "\'" + ") \n"
                + "AND month(examinations.date) =month(" + "\'" + month + "\'" + ")";

        res = conn.executeQuery(examinationsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt("examinations_amount"));
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

    public JSONArray CreateDutyDailyStatistics(String date) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        JSONArray dutyArrray = new JSONArray();
        ResultSet res = null;

        dutyArrray.put(date);

        String incidentsQuery = "SELECT COUNT(examinations.visit_id) AS incidents_amount FROM examinations\n"
                + "WHERE DATE(examinations.date) = \"" + date + "\";";

        res = conn.executeQuery(incidentsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt(1));
        }
        String examinationsQuery = "SELECT COUNT(examinations.exam_id)  AS examinations_amount FROM examinations\n"
                + "WHERE DATE(examinations.date) = \"" + date + "\";";

        res = conn.executeQuery(examinationsQuery);
        while (res != null && res.next())
        {
            dutyArrray.put(res.getInt(1));
        }

        conn.closeDBConnection();

        return dutyArrray;
    }

    public JSONArray getCountOfDrugs(String date) throws SQLException, ClassNotFoundException
    {
        JSONArray dutyArrray = new JSONArray();
        DBConnection conn = new DBConnection();
        ResultSet res = null;
        dutyArrray.put(date);

        for (int i = 0; i < 5; i++)
        {

            String query = "SELECT COUNT(examinations.drug_id)\n"
                    + "FROM examinations\n"
                    + "INNER JOIN visit ON examinations.visit_id = visit.visit_id\n"
                    + "INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id\n"
                    + "WHERE DATE(dutytime.date) = \"" + date + "\" AND examinations.drug_id = " + (i + 1) + "";
            res = conn.executeQuery(query);
            while (res != null && res.next())
            {
                dutyArrray.put(res.getInt(1));
            }
        }
        conn.closeDBConnection();

        return dutyArrray;

    }

    public JSONArray getCountOfIllnesses(String date) throws SQLException, ClassNotFoundException
    {
        JSONArray dutyArrray = new JSONArray();
        DBConnection conn = new DBConnection();
        dutyArrray.put(date);

        for (int i = 0; i < 5; i++)
        {
            String query = "SELECT COUNT(examinations.illness_id)\n"
                    + "FROM examinations\n"
                    + "INNER JOIN visit ON examinations.visit_id = visit.visit_id\n"
                    + "INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id\n"
                    + "WHERE DATE(dutytime.date) = \"" + date + "\" AND examinations.illness_id = " + (i + 1) + ";";

            ResultSet res = null;
            int count = 0;

            res = conn.executeQuery(query);
            while (res != null && res.next())
            {
                dutyArrray.put(res.getInt(1));
            }
        }
        conn.closeDBConnection();
        return dutyArrray;

    }

    public int getCountOfVisits(String date) throws SQLException, ClassNotFoundException
    {
        String query = "SELECT COUNT(visit.visit_id)\n"
                + "FROM visit\n"
                + "INNER JOIN dutytime ON visit.dutytime_id = dutytime.dutytime_id\n"
                + "WHERE DATE(dutytime.date) = \"" + date + "\";";

        DBConnection conn = new DBConnection();
        ResultSet res = null;
        int count = 0;

        res = conn.executeQuery(query);
        while (res != null && res.next())
        {
            count = res.getInt(1);
        }

        return count;
    }

    public String getChronicDisOfPatient(int patientID)
    {
        String query = "SELECT patients_chronic_diseases.disease\n"
                + "FROM patients_chronic_diseases\n"
                + "WHERE patients_chronic_diseases.patient_id = " + patientID + ";";
        return query;
    }

}
