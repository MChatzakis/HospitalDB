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
 * @author manos
 * @author george
 */
@Data
public class Doctor {

    /*Attributes*/
    String name;
    String surname;
    String email;
    String address;
    String phone;
    String id;

    public static int id_num = 1;

    public void addDoctor(String type, String id, String name, String surname, String address, String email, String phone) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO doctors VALUES( "
                + id + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + "\'" + type + "\'" + ");";
        /*String insert_d = "INSERT INTO " + type+ " VALUES( "
                + id + ");";*/
        conn.updateQuery(insert);
         //conn.updateQuery(insert_d);
        conn.closeDBConnection();
    }

    public void addDoctorByID(String type, String name, String surname, String address, String email, String phone) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO doctors VALUES( "
                + (id_num) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + ",\'" + type + "\'" + ");";
       /*String insert_d = "INSERT INTO " + type + " VALUES( "
                + (id_num) + ");";*/
        conn.updateQuery(insert);
        //conn.updateQuery(insert_d);
        id_num++;
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS doctors("
                + " doctor_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " email varchar(255),"
                + " phone varchar(255),"
                + " type varchar(255) NOT NULL,"
                + "PRIMARY KEY(doctor_id));";
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

    public void dropTable(String type) throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + type + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
