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
 * @author george
 */
@Data
public class Doctor {

    private String table_name = "doctors";

    public void addDoctor(String username, String password, String name, String surname,  String address, String email, String phone, String type, String at) throws SQLException {
        DBConnection conn = new DBConnection();
        User user = new User();

        user.addUser(username, password, "doctor");

        String insert = "INSERT INTO doctors VALUES( "
                + (User.id_num - 1) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + ",\'" + type + "\'" + ",\'" + at + "\'"+");";

        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name + "("
                + " doctor_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " email varchar(255),"
                + " phone varchar(255),"
                + " type varchar(255) NOT NULL,"
                + " at varchar(255),"
                + "PRIMARY KEY(doctor_id),"
                + "FOREIGN KEY(doctor_id) REFERENCES users(user_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void createTable(String type) throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + type + "("
                + " doctor_id int NOT NULL,"
                + "PRIMARY KEY(doctor_id),"
                + "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id));";
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
