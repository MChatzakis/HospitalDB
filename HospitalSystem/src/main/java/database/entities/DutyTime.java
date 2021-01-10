package database.entities;

import commons.JavaDate;
import commons.Queries;
import database.DBConnection;
import database.entities.users.Coordinator;
import database.entities.users.Doctor;
import database.entities.users.Nurse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author Manos Chatzakis
 */
@Data
public class DutyTime {

    public static int id_num = 1;

    public int addDutyTime(String date, String coordinator_id) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("dutytime_id", "dutytime") + 1;
        String insert = "INSERT INTO dutytime VALUES( "
                + (id_num++) + "," + "\'" + date + "\'" + "," + coordinator_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num - 1;
    }

    public void createTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS dutytime("
                + " dutytime_id int NOT NULL,"
                + " date date NOT NULL,"
                + " coordinator_id int,"
                + "PRIMARY KEY(dutytime_id) ,"
                + "FOREIGN KEY (coordinator_id) REFERENCES coordinators(coordinator_id)); ";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS dutytime;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

    public int getDutyIDFromDate(String date) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        int id = 0;
        String query = "SELECT dutytime.dutytime_id\n"
                + "FROM dutytime\n"
                + "WHERE DATE(date) = " + "\'" + date + "\'" + ";";
        ResultSet res = null;

        res = conn.executeQuery(query);

        while (res != null && res.next()) {
            id = Integer.parseInt(res.getString("dutytime_id"));
        }

        return id;
    }

    public int getRandomDuty(String date) throws SQLException, ClassNotFoundException {
        int dutyTimeID = getDutyIDFromDate(date);
        Doctor doc = new Doctor();
        ArrayList<String>cardIDs = doc.getIDsOfDoctors("cardiologist");
        ArrayList<String>endIDs = doc.getIDsOfDoctors("pathologist");
        ArrayList<String>orthIDs = doc.getIDsOfDoctors("endocrinologist");
        ArrayList<String>pathIDs = doc.getIDsOfDoctors("orthopedic");
        ArrayList<String>endoIDs = doc.getIDsOfDoctors("pulmonologist");;
        ArrayList<String>nurseIDs = new Nurse().getIDsOfNurses();
        ArrayList<String>workerIDs = new Coordinator().getIDsOfWorker();
        
        return dutyTimeID;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(new DutyTime().getDutyIDFromDate(JavaDate.getDefaultDate()));
    }

}
