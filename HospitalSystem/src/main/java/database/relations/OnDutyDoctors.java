package database.relations;

import database.DBConnection;
import java.sql.SQLException;

/**
 * Class representation of "onDutyDoctors" relationship.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class OnDutyDoctors
{

    public void addDoctorDutyTime(String doctor_id, String duty_id) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO doctor_duties VALUES("
                + doctor_id + "," + duty_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS  doctor_duties("
                + " doctor_id int NOT NULL,"
                + " dutytime_id int NOT NULL,"
                + " FOREIGN KEY(dutytime_id) REFERENCES dutytime(dutytime_id),"
                + " FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS doctor_duties;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
