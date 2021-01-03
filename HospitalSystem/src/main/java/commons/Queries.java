/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author manos
 */
public class Queries {

    public static int getMaxTableKey(String tableColumn, String tableName) throws SQLException, ClassNotFoundException {
        String query = "SELECT MAX(" + tableColumn + ") FROM " + tableName + ";";
        ResultSet res = null;
        String result = null;
        int key = 0;
        DBConnection conn = new DBConnection();

        res = conn.executeQuery(query);

        while (res != null && res.next()) {
            result = res.getString(1);
        }

        conn.closeDBConnection();

        if(!(result == null)){
            key = Integer.parseInt(result);
        }
        
        return key;
    }

    public static String selectInitialPatientExaminations(int id) {
        String query = "SELECT drugs.name AS drug_name, examinations.date, illnesses.name AS illness_name\n"
                + "FROM examinations\n"
                + "LEFT JOIN drugs ON examinations.drug_id=drugs.drug_id\n"
                + "LEFT JOIN illnesses ON drugs.illness_id=illnesses.illness_id\n"
                + "WHERE examinations.patient_id = " + id + " AND examinations.exam_id IN \n"
                + "(\n"
                + "    SELECT examinations_initial.init_exam_id\n"
                + "    FROM examinations_initial\n"
                + ");";
        return query;
    }

    public static String selectMedicalsAndReExams(int id) {
        String query = "SELECT medicals.date AS medical_date, medicals.type, examinations.date AS re_examination_date, drugs.name AS drug_name, illnesses.name AS illness_name, examinations_retaken.hospitalization\n"
                + "FROM examinations\n"
                + "INNER JOIN examinations_retaken ON examinations_retaken.re_exam_id=examinations.exam_id\n"
                + "INNER JOIN medicals ON examinations_retaken.medical_id = medicals.medical_id\n"
                + "INNER JOIN drugs ON drugs.drug_id = examinations.drug_id\n"
                + "INNER JOIN illnesses On illnesses.illness_id = examinations.illness_id\n"
                + "WHERE examinations.patient_id = " + id + ";";
        return query;
    }

    public static String selectPatientsOfDutyTime(int dutytimeID) {
        String query = "SELECT patients.patient_id, patients.name, patients.surname, patients.birth_date\n"
                + "FROM patients\n"
                + "INNER JOIN visit ON visit.patient_id = patients.patient_id\n"
                + "INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id\n"
                + "WHERE dutytime.dutytime_id = " + dutytimeID + ";";
        return query;
    }

    public static String selectDutiesOfDoctor(int doctorID) {
        String query = "SELECT dutytime.date\n"
                + "FROM dutytime\n"
                + "INNER JOIN doctor_duties ON doctor_duties.dutytime_id = dutytime.dutytime_id\n"
                + "WHERE doctor_duties.doctor_id = " + doctorID + ";";
        return query;
    }

    public static String selectAllDoctors() {
        String query = "SELECT * FROM doctors";
        return query;
    }

    public static String selectAllPatients() {
        String query = "SELECT * FROM patients";
        return query;
    }

    public static String selectAllNurses() {
        String query = "SELECT * FROM nurses";
        return query;
    }

    public static String selectAllWorkers() {
        String query = "SELECT * FROM coordinators";
        return query;
    }

}
