/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;

/**
 *
 * @author manos
 */
public class Login {
    public void createTable() throws SQLException{
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS login("
                + " user_id int NOT NULL,"
                + " username varchar(255) NOT NULL,"
                + " password varchar(255) NOT NULL,"
                + " PRIMARY KEY(user_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }
    
    public void dropTable() throws SQLException{
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS login;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
