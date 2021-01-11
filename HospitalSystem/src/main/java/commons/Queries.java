package commons;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the most commons queries used for this project (not all
 * of them).
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
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

        if (!(result == null)) {
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

    public static String q = "SELECT visit.visit_id, visit.date, examinations.exam_id, examinations.drug_id, examinations.illness_id, medicals.type ,examinations_retaken.hospitalization\n"
            + "FROM visit\n"
            + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
            + "INNER JOIN medicals ON medicals.exam_id = examinations.exam_id\n"
            + "INNER JOIN examinations_retaken ON medicals.medical_id = examinations_retaken.medical_id";

    public static String getAllPatientExaminationsOfVisits = "SELECT DISTINCT visit.date, illnesses.name, drugs.name, medicals.type , examinations_retaken.hospitalization, examinations.patient_id,patients.name,patients.surname\n"
            + "FROM visit\n"
            + "INNER JOIN patients ON patients.patient_id = visit.patient_id\n"
            + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
            + "INNER JOIN medicals ON medicals.exam_id = examinations.exam_id\n"
            + "INNER JOIN examinations_retaken ON medicals.medical_id = examinations_retaken.medical_id\n"
            + "LEFT JOIN drugs ON examinations.drug_id = drugs.drug_id\n"
            + "LEFT JOIN illnesses ON illnesses.illness_id = drugs.illness_id";

    public static String getPatientInfoByID(int id) {
        String query = "SELECT  name, surname, address, users.email,users.username ,  phone, birth_date, amka, at, insurance \n"
                + "FROM patients\n"
                + "INNER JOIN users ON patients.patient_id = users.user_id\n"
                + "WHERE users.user_id = " + +id + ";";
        return query;
    }

    public static String getDoctorInfoByUsername(String username) {
        String query = "SELECT  *\n"
                + "FROM users\n"
                + "INNER JOIN doctors ON doctors.doctor_id = users.user_id\n"
                + "WHERE users.username = " + " \'" + username + "\' " + ";";
        return query;
    }

    public static String getDoctorInfoByID(int docID) {
        String query = "SELECT  *\n"
                + "FROM users\n"
                + "INNER JOIN doctors ON doctors.doctor_id = users.user_id\n"
                + "WHERE users.user_id = " + docID + ";";
        return query;
    }

    public static String getNurseInfoByID(int id) {
        String query = "SELECT  *\n"
                + "FROM users\n"
                + "INNER JOIN nurses ON nurses.nurse_id = users.user_id\n"
                + "WHERE users.user_id = " + id + ";";
        return query;
    }

    public static String getPatientDiseasesByID(int id) {
        String query = "SELECT patients_chronic_diseases.disease\n"
                + "FROM patients_chronic_diseases\n"
                + "WHERE patients_chronic_diseases.patient_id = " + id + ";";
        return query;
    }

    public static String getAllVisitExaminationInfo(int patientID) {
        String visitQuery = "SELECT DISTINCT visit.date,visit.visit_id, illnesses.name AS illness, drugs.name AS drug, medicals.type AS medical , examinations_retaken.hospitalization\n"
                + "FROM visit\n"
                + "INNER JOIN examinations ON examinations.visit_id = visit.visit_id\n"
                + "INNER JOIN medicals ON medicals.exam_id = examinations.exam_id\n"
                + "INNER JOIN examinations_retaken ON medicals.medical_id = examinations_retaken.medical_id\n"
                + "LEFT JOIN drugs ON examinations.drug_id = drugs.drug_id\n"
                + "LEFT JOIN illnesses ON illnesses.illness_id = drugs.illness_id\n"
                + "WHERE visit.patient_id = " + patientID + ";";
        return visitQuery;

    }

    public static String getAllVisitSymptomsInfo(int patientID) {

        String symptomQuery = "SELECT visit_symptoms.symptom , visit_symptoms.visit_id\n"
                + "FROM visit_symptoms\n"
                + "INNER JOIN visit ON visit_symptoms.visit_id = visit.visit_id\n"
                + "WHERE visit.patient_id = " + patientID + ";";
        return symptomQuery;
    }
}
