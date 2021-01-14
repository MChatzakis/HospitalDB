package database.entities.users;

import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 * Class representation of "patient" entity.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class Patient
{

    public int addPatient(String username, String password, String name, String surname, String address, String email, String phone, String birth_date, String amka, String at, String insurance) throws SQLException, ClassNotFoundException
    {
        int id = 0;
        DBConnection conn = new DBConnection();
        User user = new User();

        id = user.addUser(username, password, "Patient", email);

        String insert = "INSERT INTO patients  VALUES( "
                + (id) + "," + "\'" + name + "\'" + "," + "\'" + surname + "\'" + ", " + "\'" + address + "\'" + "," + "\'" + phone + "\'" + "," + "\'" + birth_date + "\'" + "," + "\'" + amka + "\'" + "," + "\'" + at + "\'" + "," + "\'" + insurance + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();

        return id;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients ("
                + " patient_id int NOT NULL,"
                + " name varchar(100) NOT NULL,"
                + " surname varchar(100) NOT NULL,"
                + " address varchar(100) NOT NULL,"
                + " phone varchar(100) NOT NULL,"
                + " birth_date date NOT NULL,"
                + " amka varchar(100) NOT NULL,"
                + " at varchar(100) NOT NULL,"
                + " insurance varchar(100) NOT NULL,"
                + " PRIMARY KEY(patient_id),"
                + " FOREIGN KEY(patient_id) REFERENCES users(user_id),"
                + " UNIQUE(amka),"
                + " UNIQUE(phone),"
                + " UNIQUE(at));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public void addChronicDisease(String patient_id, String disease) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO patients_chronic_diseases  VALUES( "
                + patient_id + "," + "\'" + disease + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTableChronicDiseases() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS patients_chronic_diseases ("
                + " patient_id int NOT NULL,"
                + " disease varchar(100) NOT NULL,"
                + " FOREIGN KEY(patient_id) REFERENCES patients(patient_id));";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTableChronicDiseases() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS patients_chronic_diseases;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
