/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities.users;

import database.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author Manos Chatzakis
 */
public class User {

    public static int id_num = 1;
    private String table_name = "users";

    public void addUser(String username, String password, String user_type) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO " + table_name + " VALUES( "
                + (id_num++) + "," + "\'" + username + "\'" + "," + "\'" + password + "\'" + ", " + "\'" + user_type + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name + "("
                + " user_id int NOT NULL,"
                + " username varchar(255) NOT NULL,"
                + " password varchar(255) NOT NULL,"
                + " user_type varchar(255) NOT NULL,"
                + " PRIMARY KEY(user_id),"
                + " UNIQUE(password),"
                + " UNIQUE(username));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table_name + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
