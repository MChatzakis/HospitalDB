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
public class Visit
{

    //public static int id_num = 1;
    public int addVisit(String date, String duty_time_id, String patient_id) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("visit_id", "visit") + 1;
        String insert = "INSERT INTO visit VALUES( "
                + (id_num) + "," + "\'" + date + "\'" + "," + duty_time_id + "," + patient_id + " );";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS visit("
                + " visit_id int NOT NULL,"
                + " date date NOT NULL ,"
                + " dutytime_id int NOT NULL ,"
                + " patient_id int NOT NULL , "
                + " FOREIGN KEY (dutytime_id) REFERENCES dutytime(dutytime_id),"
                + " FOREIGN KEY (patient_id) REFERENCES patients(patient_id),"
                + " PRIMARY KEY(visit_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS visit";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public void addSymptom(String visit_id, String symptom) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO visit_symptoms VALUES( "
                + visit_id + "," + "\'" + symptom + "\'" + " );";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTableSymptoms() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS visit_symptoms("
                + " visit_id int NOT NULL,"
                + " symptom varchar(255) NOT NULL ,"
                + " FOREIGN KEY (visit_id) REFERENCES visit(visit_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTableSymptoms() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS visit_symptoms";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
