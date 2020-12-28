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

    public void addReExam(String id, String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String medical_id, String date, String reExam, String hosp) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO nurses VALUES( "
                + id + "," + "\'" + patient_id + "\'" + "," + "\'" + doctor_id + "\'" + ", " + "\'" + drug_id + "\'" + "," + "\'" + illness_id + "\'" + "," + "\'" + visit_id + "\'" + "," + "\'" + medical_id + "\'" + "," + "\'" + date + "\'"+ "," + "\'" + reExam + "\'" + "," + "\'" + hosp + "\'" +");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }
    
    public void addExam(String id, String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String medical_id, String date) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO nurses VALUES( "
                + id + "," + "\'" + patient_id + "\'" + "," + "\'" + doctor_id + "\'" + ", " + "\'" + drug_id + "\'" + "," + "\'" + illness_id + "\'" + "," + "\'" + visit_id + "\'" + "," + "\'" + medical_id + "\'" + "," + "\'" + date + "\'"+ "," + "\'" + "false" + "\'" + "," + "\'" + "false" + "\'" +");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations("
                + " exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int NOT NULL,"
                + " drug_id int  ,"
                + " illness_id int ,"
                + " visit_id int NOT NULL,"
                + " medical_id int,"
                + " date varchar(255),"
                + " is_reExam BOOLEAN,"
                + " hospitalization BOOLEAN,"
                + " PRIMARY KEY(exam_id)"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
            //    + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(drug_id) REFERENCES drugs(drug_id),"
                + " FOREIGN KEY(illness_id) REFERENCES illnesses(illness_id),"
                + " FOREIGN KEY(visit_id) REFERENCES visits(visit_id),"
                + " FOREIGN KEY(medical_id) REFERENCES medicals(medical_id));";
                
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS examinations";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
