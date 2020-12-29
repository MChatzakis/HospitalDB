/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities.users;

import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author manos
 */
@Data
public class Nurse {

    private String table_name;

    public void addNurseByID(String username, String password, String name, String surname, String address, String email, String phone) throws SQLException {
        DBConnection conn = new DBConnection();
        User user = new User();

        user.addUser(username, password, "nurse");

        String insert = "INSERT INTO nurses VALUES( "
                + (User.id_num - 1) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + ");";

        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS nurses("
                + " nurse_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " email varchar(255),"
                + " phone varchar(255),"
                + " PRIMARY KEY(nurse_id),"
                + " FOREIGN KEY(nurse_id) REFERENCES users(user_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS nurses";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
