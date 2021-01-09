package database.entities.users;

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

    public int addPatient(String username, String password, String name, String surname, String address, String email, String phone, String birth_date, String amka, String at, String insurance) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        User user = new User();

        user.addUser(username, password, "Patient",email);

        String insert = "INSERT INTO patients  VALUES( "
                + (User.id_num - 1) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" +  "," + "\'" + phone + "\'" + "," + "\'" + birth_date + "\'" + "," + "\'" + amka + "\'" +  "," + "\'" + at + "\'" + "," + "\'" + insurance + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return User.id_num - 1;
    }

    public void createTable() throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients ("
                + " patient_id int NOT NULL,"
                + " name varchar(255) NOT NULL,"
                + " surname varchar(255) NOT NULL,"
                + " address varchar(255) ,"
                + " phone varchar(255),"
                + " birth_date date NOT NULL,"
                + " amka varchar(255) NOT NULL,"
                + " at varchar(255) NOT NULL,"
                + " insurance varchar(255),"
                + " PRIMARY KEY(patient_id),"
                + " FOREIGN KEY(patient_id) REFERENCES users(user_id),"
                + " UNIQUE(amka),"
                + " UNIQUE(at));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public void addChronicDisease(String patient_id, String disease) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO patients_chronic_diseases  VALUES( "
                + patient_id + "," + "\'" + disease + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTableChronicDiseases() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients_chronic_diseases ("
                + " patient_id int NOT NULL,"
                + " disease varchar(255) NOT NULL,"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTableChronicDiseases() throws SQLException , ClassNotFoundException{
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients_chronic_diseases;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
