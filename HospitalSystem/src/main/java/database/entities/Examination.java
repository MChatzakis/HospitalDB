/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author manos
 */
public class Examination {

    public static int id_num = 1;

    public void addInitialExam(String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String date) throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        addExamination(patient_id, doctor_id, drug_id, illness_id, visit_id, date);
        String insert = "INSERT INTO examinations_initial VALUES( " + (id_num - 1) + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void addReExam(String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String date, String medical_id, boolean hospi) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String hosp = "false";
        if (hospi) {
            hosp = "true";
        }
        addExamination(patient_id, doctor_id, drug_id, illness_id, visit_id, date);
        String insert = "INSERT INTO examinations_retaken VALUES( " + (id_num - 1) + ","  + hosp  + "," + medical_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void addExamination(String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String date) throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO examinations VALUES( "
                + (id_num++) + "," + patient_id + "," + doctor_id + ", " + drug_id + "," + illness_id + "," + visit_id + "," + "\'" + date + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTables() throws SQLException , ClassNotFoundException{
        createTable();
        createInitExamTable();
        createReExamTable();
    }

    public void createTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations("
                + " exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int NOT NULL,"
                + " drug_id int  ,"
                + " illness_id int ,"
                + " visit_id int NOT NULL,"
                + " date varchar(255),"
                + " PRIMARY KEY(exam_id),"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(drug_id) REFERENCES drugs(drug_id),"
                + " FOREIGN KEY(illness_id) REFERENCES illnesses(illness_id),"
                + " FOREIGN KEY(visit_id) REFERENCES visit(visit_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void createInitExamTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations_initial("
                + " init_exam_id int NOT NULL,"
                + " PRIMARY KEY(init_exam_id),"
                + " FOREIGN KEY(init_exam_id) REFERENCES examinations(exam_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void createReExamTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations_retaken("
                + " re_exam_id int NOT NULL,"
                + " hospitalization BOOLEAN NOT NULL,"
                + " medical_id int NOT NULL,"
                + " PRIMARY KEY(re_exam_id),"
                + " FOREIGN KEY(re_exam_id) REFERENCES examinations(exam_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void alterTableToAddMedical() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String str = "ALTER TABLE examinations_retaken "
                + "ADD FOREIGN KEY (medical_id) REFERENCES medicals(medical_id);";
        conn.updateQuery(str);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS examinations;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
