package database.entities.users;

import commons.Queries;
import database.DBConnection;
import java.sql.SQLException;

/**
 * Class representation of "user" entity.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class User
{

    private String table_name = "users";

    public int addUser(String username, String password, String user_type, String email) throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        int id_num = 0;
        id_num = Queries.getMaxTableKey("user_id", "users") + 1;
        String insert = "INSERT INTO " + table_name + " VALUES( "
                + (id_num) + "," + "\'" + username + "\'" + "," + "\'" + password + "\'" + ", " + "\'" + user_type + "\'," + "\'" + email + "\'" + ");";
        conn.updateQuery(insert);
        conn.closeDBConnection();
        return id_num;
    }

    public void createTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name + "("
                + " user_id int NOT NULL,"
                + " username varchar(100) NOT NULL,"
                + " password varchar(100) NOT NULL,"
                + " user_type varchar(100) NOT NULL,"
                + " email varchar(100) NOT NULL,"
                + " PRIMARY KEY(user_id),"
                + " UNIQUE(password),"
                + " UNIQUE(email),"
                + " UNIQUE(username));";
        conn.updateQuery(createTable);
        conn.closeDBConnection();
    }

    public void dropTable() throws SQLException, ClassNotFoundException
    {
        DBConnection conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table_name + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
