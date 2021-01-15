/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities.medics;

import commons.Queries;
import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class Drug
{

    //public static int id_num = 1;
    public int addDrug(String name, String type, String dosage, String illness_id) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        if (!dosage.equals("NULL"))
        {
            dosage = "\'" + dosage + "\'";
        }
        id_num = Queries.getMaxTableKey("drug_id", "drugs") + 1;
        String insert = "INSERT INTO drugs VALUES( "
                + (id_num) + "," + "\'" + name + "\'" + "," + "\'" + type + "\'" + ", " + dosage + "," + illness_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS drugs("
                + " drug_id int NOT NULL,"
                + " name varchar(100) NOT NULL,"
                + " type varchar(100) NOT NULL,"
                + " dosage varchar(100) ,"
                + " illness_id int NOT NULL,"
                + "PRIMARY KEY(drug_id),"
                + "UNIQUE(name),"
                + "FOREIGN KEY(illness_id) REFERENCES illnesses(illness_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS drugs";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
