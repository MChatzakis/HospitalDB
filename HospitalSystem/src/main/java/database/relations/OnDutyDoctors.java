package database.relations;

import database.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author manos
 */
public class OnDutyDoctors {

    public void addDoctorDutyTime(String doctor_id, String duty_id) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO doctor_duties VALUES( "
                + doctor_id + "," + duty_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable(String type) throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS "+ type + "_duties("
                + " doctor_id int NOT NULL,"
                + " dutytime_id int NOT NULL,"
               + " FOREIGN KEY(dutytime_id) REFERENCES dutytime(dutytime_id),"
              + " FOREIGN KEY(doctor_id) REFERENCES  "+type+"(doctor_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS doctor_duties";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
