package database.entities;

import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author manos
 */
@Data
public class DutyTime {

    public static int id_num = 1;

    public void addDutyTime(String id, String date, String coordinator_id) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO dutytime VALUES( "
                + id + "," + "\'" + date + "\'" + "," + coordinator_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }
    
    public void addDutyTimeByID(String date, String coordinator_id) throws SQLException {
        DBConnection conn = new DBConnection();
        String insert = "INSERT INTO dutytime VALUES( "
                +(id_num++) + "," + "\'" + date + "\'" + "," + coordinator_id + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
    }

    public void createTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS dutytime("
                + " dutytime_id int NOT NULL,"
                + " date varchar(255) ,"
                + " coordinator_id int,"
                + "PRIMARY KEY(dutytime_id) ,"
                + "FOREIGN KEY (coordinator_id) REFERENCES coordinators(coordinator_id)); ";

        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS dutytime";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
