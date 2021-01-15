package database.relations;

import database.DBConnection;
import java.sql.SQLException;

/**
 * Class representation of "onDutyWorkers" relationship.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class OnDutyWorkers
{

    public void addWorkerDutyTime(String coordinator_id, String dutytime_id) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO coordinator_duties VALUES( "
                + dutytime_id + "," + coordinator_id + " );";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS coordinator_duties ("
                + " dutytime_id int NOT NULL,"
                + " coordinator_id int NOT NULL,"
                + " FOREIGN KEY (coordinator_id) REFERENCES coordinators(coordinator_id),"
                + " FOREIGN KEY (dutytime_id) REFERENCES dutytime(dutytime_id));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS coordinator_duties;";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
