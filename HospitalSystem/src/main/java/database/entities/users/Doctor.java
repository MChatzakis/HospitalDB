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
public class Doctor
{

    private String table_name = "doctors";

    public int addDoctor(String username, String password, String name, String surname, String address, String email, String phone, String type, String at) throws SQLException, ClassNotFoundException
    {
        int id = 0;
        DBConnection conn = new DBConnection();
        User user = new User();

        id = user.addUser(username, password, "Doctor", email);

        String insert = "INSERT INTO doctors VALUES( "
                + (id) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + phone + "\'" + ",\'" + type + "\'" + ",\'" + at + "\'" + ");";

        conn.updateQuery(insert);
        conn.closeDBConnection();

        return id;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name + "("
                + " doctor_id int NOT NULL,"
                + " name varchar(100) NOT NULL,"
                + " surname varchar(100) NOT NULL,"
                + " address varchar(100) NOT NULL,"
                + " phone varchar(100) NOT NULL,"
                + " type varchar(100) NOT NULL,"
                + " at varchar(100) NOT NULL,"
                + "PRIMARY KEY(doctor_id),"
                + "UNIQUE(at),"
                + "UNIQUE(phone),"
                + "FOREIGN KEY(doctor_id) REFERENCES users(user_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void createTable(String type) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + type + "("
                + " doctor_id int NOT NULL,"
                + "PRIMARY KEY(doctor_id),"
                + "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table_name + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public ArrayList<String> getIDsOfDoctors(String type) throws SQLException, ClassNotFoundException
    {
        ArrayList<String> IDs = new ArrayList<String>();
        String query = "SELECT doctors.doctor_id\n"
                + "FROM doctors\n"
                + "WHERE doctors.type = \"" + type + "\"";
        DBConnection conn = new DBConnection();
        ResultSet res = null;
        res = conn.executeQuery(query);
        while (res != null && res.next())
        {
            IDs.add(res.getString("doctor_id"));
        }

        conn.closeDBConnection();
        return IDs;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        System.out.println((new Doctor().getIDsOfDoctors("cardiologist").toString()));
    }

}
