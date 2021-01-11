/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities.users;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class Coordinator {

    public int addCoordinator(String username, String password, String name, String surname, String address, String email, String phone, String at) throws SQLException, ClassNotFoundException {
        int id = 0;
        
        DBConnection conn = new DBConnection();
        User user = new User();

        id = user.addUser(username, password, "Worker", email);

        String insert = "INSERT INTO coordinators VALUES( "
                + (id) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + phone + "\'" + "," + "\'" + at + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        
        return id;
    }

    public void createTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS coordinators("
                + " coordinator_id int NOT NULL,"
                + " name varchar(100) NOT NULL,"
                + " surname varchar(100) NOT NULL,"
                + " address varchar(100) ,"
                + " phone varchar(100),"
                + " at varchar(100),"
                + " PRIMARY KEY(coordinator_id),"
                + " UNIQUE(phone),"
                + " UNIQUE(at),"
                + " FOREIGN KEY(coordinator_id) REFERENCES users(user_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS coordinators";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public ArrayList<String> getIDsOfWorker() throws SQLException, ClassNotFoundException {
        ArrayList<String> IDs = new ArrayList<String>();
        String query = "SELECT coordinators.coordinator_id\n"
                + "FROM coordinators";
        DBConnection conn = new DBConnection();
        ResultSet res = null;
        res = conn.executeQuery(query);
        while (res != null && res.next()) {
            IDs.add(res.getString("coordinator_id"));
        }
        conn.closeDBConnection();
        return IDs;
    }
}
