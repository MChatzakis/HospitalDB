/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import commons.Queries;
import database.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class Medical
{

    //public static int id_num = 1;
    public int addMedical(String type, String exam_id, String patient_id, String doctor_id, String nurse_id, String date) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("medical_id", "medicals") + 1;
        String insert = "INSERT INTO medicals VALUES( "
                + (id_num) + "," + exam_id + "," + patient_id + ", " + doctor_id + "," + nurse_id + "," + "\'" + date + "\'" + ",\'" + type + "\'" + ");";

        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS medicals("
                + " medical_id int NOT NULL,"
                + " exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int  NOT NULL,"
                + " nurse_id int  NOT NULL,"
                + " date date NOT NULL,"
                + " type varchar(100) NOT NULL,"
                + " PRIMARY KEY(medical_id),"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
                + " FOREIGN KEY(nurse_id) REFERENCES nurses(nurse_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(exam_id) REFERENCES examinations(exam_id));"; //examinations_inittial(exam_id)

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable(String type) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS medicals;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
