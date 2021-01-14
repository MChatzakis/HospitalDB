package database.entities.medics;

import commons.Queries;
import database.DBConnection;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class Illness
{

    //public static int id_num = 1;
    public int addIllness(String name) throws SQLException, ClassNotFoundException
    {
        int id_num = 0;
        DBConnection conn = new DBConnection();
        id_num = Queries.getMaxTableKey("illness_id", "illnesses") + 1;
        String insert = "INSERT INTO illnesses VALUES( "
                + (id_num) + "," + "\'" + name + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS illnesses("
                + " illness_id int NOT NULL,"
                + " name varchar(100) NOT NULL ,"
                + "PRIMARY KEY(illness_id) ) ;";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS illnesses";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
