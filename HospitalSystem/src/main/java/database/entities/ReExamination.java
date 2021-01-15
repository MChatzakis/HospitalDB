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
public class ReExamination
{

    //public static int id_num = 1;
    public int addReExamination(String patient_id, String doctor_id, String visit_id, String date, String medical_id, boolean hospi) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("re_exam_id", "examinations_retaken") + 1;
        String hosp = "false";
        if (hospi)
        {
            hosp = "true";
        }
        String insert = "INSERT INTO examinations_retaken VALUES( "
                + (id_num) + "," + patient_id + "," + doctor_id + "," + visit_id + "," + "\'" + date + "\'" + "," + hosp + "," + medical_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations_retaken("
                + " re_exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int NOT NULL,"
                + " visit_id int NOT NULL,"
                + " date date NOT NULL,"
                + " hospitalization boolean NOT NULL,"
                + " medical_id int NOT NULL,"
                + " PRIMARY KEY(re_exam_id),"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(medical_id) REFERENCES medicals(medical_id),"
                + " FOREIGN KEY(visit_id) REFERENCES visit(visit_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }
}
