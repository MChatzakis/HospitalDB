package database.entities;

import database.DBConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author manos
 */
@Data
public class Patient {

    /*Attributes*/
    String name;
    String surname;
    String email;
    String address;
    String phone;
    String id;
    String age;
    String amka;
    String insurance;
    ArrayList<String> chronicDiseases = new ArrayList<String>();

    public static int id_num = 1;
    
    public void addPatient(String id, String name, String surname, String address, String email, String phone, String age, String amka, String insurance) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO patients  VALUES( "
                + id + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + "," + "\'" + age + "\'" + "," + "\'" + amka + "\'" + "," + "\'" + insurance + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }
    
    public void addPatientByID(String name, String surname, String address, String email, String phone, String age, String amka, String insurance) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO patients  VALUES( "
                + (id_num++)  + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + email + "\'" + "," + "\'" + phone + "\'" + "," + "\'" + age + "\'" + "," + "\'" + amka + "\'" + "," + "\'" + insurance + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients ("
                + " patient_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " email varchar(255),"
                + " phone varchar(255),"
                + " age int NOT NULL,"
                + " amka varchar(255) NOT NULL,"
                + " insurance varchar(255),"
                + " PRIMARY KEY(patient_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public void addChronicDisease(String patient_id, String disease) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO patients_chronic_diseases  VALUES( "
                + patient_id + "," + "\'" + disease + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTableChronicDiseases() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients_chronic_diseases ("
                + " patient_id int NOT NULL,"
                + " disease varchar(255) NOT NULL,"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTableChronicDiseases() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients_chronic_diseases;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
