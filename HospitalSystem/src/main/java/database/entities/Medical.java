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
public class Medical {

    public static int id_num = 1;

    public void addMedical(String type, String id, String patient_id, String doctor_id, String nurse_id, String date) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO medical VALUES( "
                + (id) + "," + "\'" + patient_id + "\'" + ", " + "\'" + doctor_id + "\'" + "," + "\'" + nurse_id + "\'" + "," + "\'" + date + ",\'" + type + "\'" + ");";
        //String insert_m = "INSERT INTO " + type + " VALUES( "
        //      + id + ");";
        conn.updateQuery(insert);
        //conn.updateQuery(insert_m);
        conn.closeDBConnection();
    }

    public void addMedicalByID(String type, String patient_id, String doctor_id, String nurse_id, String date) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO medical VALUES( "
                + (id_num++) + "," + "\'" + patient_id + "\'" + ", " + "\'" + doctor_id + "\'" + "," + "\'" + nurse_id + "\'" + "," + "\'" + date + ",\'" + type + "\'" + ");";

        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS medicals("
                + " medical_id int NOT NULL,"
                + " re_exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int  ,"
                + " nurse_id int ,"
                + " date varchar(255),"
                + " type varchar(255),"
                + " PRIMARY KEY(medical_id),"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
                + " FOREIGN KEY(nurse_id) REFERENCES nurses(nurse_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(re_exam_id) REFERENCES examinations(exam_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void createTable(String type) throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + type + "("
                + " medical_id int NOT NULL,"
                + " PRIMARY KEY(medical_id),"
                + "FOREIGN KEY(medical_id) REFERENCES medicals(medical_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable(String type) throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + type;
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
