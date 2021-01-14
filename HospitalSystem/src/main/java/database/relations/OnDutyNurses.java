package database.relations;

import database.DBConnection;
import java.sql.SQLException;

/**
 * Class representation of "onDutyNurses" relationship.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class OnDutyNurses
{

    public void addNurseDutyTime(String nurse_id, String duty_id) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO nurse_duties VALUES( "
                + nurse_id + "," + duty_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS nurse_duties("
                + " nurse_id int NOT NULL,"
                + " dutytime_id int NOT NULL,"
                + " FOREIGN KEY(nurse_id) REFERENCES nurses(nurse_id),"
                + " FOREIGN KEY(dutytime_id) REFERENCES dutytime(dutytime_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS nurse_duties";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }

}
