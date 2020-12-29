/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities.medics;

import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author manos
 */
@Data
public class Illness {

    public static int id_num = 1;

    public void addIllness(String name) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO illnesses VALUES( "
                + (id_num++) + "," + "\'" + name + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS illnesses("
                + " illness_id int NOT NULL,"
                + " name varchar(255) NOT NULL ,"
                + "PRIMARY KEY(illness_id) ) ;";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS illnesses";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
