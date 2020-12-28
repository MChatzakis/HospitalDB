/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class Coordinator {
   
    public void addCoordinator(String id, String name, String surname, String address, String email,String phone) throws SQLException{
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO coordinators VALUES( "
                + id+","+"\'"+name+"\'"+","+"\'"+surname+"\'"+", "+"\'"+address+"\'"+","+"\'"+email+"\'" +"," +"\'"+ phone+"\'"+");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }
    
    public void createTable() throws SQLException{
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS coordinators("
                + " coordinator_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " email varchar(255),"
                + " phone varchar(255),"
                + " PRIMARY KEY(coordinator_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }
    
    public void dropTable() throws SQLException{
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS coordinators";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
