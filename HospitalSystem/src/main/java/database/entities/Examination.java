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
public class Examination
{

    public int addExamination(String patient_id, String doctor_id, String drug_id, String illness_id, String visit_id, String date) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("exam_id", "examinations") + 1;
        String insert = "INSERT INTO examinations VALUES( "
                + (id_num) + "," + patient_id + "," + doctor_id + ", " + drug_id + "," + illness_id + "," + visit_id + "," + "\'" + date + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void modifyExamination(String exam_id, String drug_id, String illness_id) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        if (drug_id.equals(""))
        {
            drug_id = "null";
        }
        if (illness_id.equals(""))
        {
            illness_id = "null";
        }

        String query = "UPDATE examinations\n"
                + "SET drug_id= " + drug_id + ", illness_id=" + illness_id + "\n"
                + "WHERE exam_id = " + exam_id + ";";
        conn.updateQuery(query);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS examinations("
                + " exam_id int NOT NULL,"
                + " patient_id int NOT NULL,"
                + " doctor_id int NOT NULL,"
                + " drug_id int  ,"
                + " illness_id int ,"
                + " visit_id int NOT NULL,"
                + " date date  NOT NULL,"
                + " PRIMARY KEY(exam_id),"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
                + " FOREIGN KEY(drug_id) REFERENCES drugs(drug_id),"
                + " FOREIGN KEY(illness_id) REFERENCES illnesses(illness_id),"
                + " FOREIGN KEY(visit_id) REFERENCES visit(visit_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS examinations;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        new Examination().modifyExamination("4", "", "");
    }
}
