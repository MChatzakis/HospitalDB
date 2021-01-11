package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Data;

/**
 * Class for MySQL Database connection and query execution methods.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
@Data
public class DBConnection {

    private Connection conn;

    private String dbConnectionURL = "jdbc:mysql://localhost/hospital";
    private String dbConnectionUsername = "root";
    private String dbConnectionPassword = ""; //I dont know why this does work

    public DBConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(dbConnectionURL, dbConnectionUsername, dbConnectionPassword);
    }

    public DBConnection(String URL, String user, String pass) throws SQLException {
        dbConnectionURL = URL;
        dbConnectionUsername = user;
        dbConnectionPassword = pass;
        conn = DriverManager.getConnection(URL, user, pass);
    }

    public void setDefaultDBConnection() throws SQLException {
        setDBConnection(dbConnectionURL, dbConnectionUsername, dbConnectionPassword);
    }

    public void closeDBConnection() throws SQLException {
        conn.close();
    }

    public void setDBConnection(String dbConnectionURL, String dbConnectionUsername, String dbConnectionPassword) throws SQLException {
        conn = DriverManager.getConnection(dbConnectionURL, dbConnectionUsername, dbConnectionPassword);
    }

    public ResultSet executeQuery(String query) {
        PreparedStatement prepStm = null;
        ResultSet resultSet = null;
        Statement stm = null;

        try {
            if (conn == null) {
                setDefaultDBConnection();
            }

            stm = conn.createStatement();

            prepStm = conn.prepareStatement(query);
            resultSet = prepStm.executeQuery();

            stm.close();
            System.out.println(query);
            //closeDBConnection();
        } catch (SQLException ex) {
            System.out.println("Attempt to run query: " + query + "failed.");
            ex.printStackTrace();
        }

        return resultSet;
    }

    public int updateQuery(String query) {

        PreparedStatement prepStm = null;
        ResultSet resultSet = null;
        Statement stm = null;
        int ret;

        try {
            if (conn == null) {
                setDefaultDBConnection();
            }

            stm = conn.createStatement();

            prepStm = conn.prepareStatement(query);
            ret = prepStm.executeUpdate(query);

            stm.close();
            System.out.println(query);
            //closeDBConnection();
        } catch (SQLException ex) {
            System.out.println("Attempt to run query: " + query + "failed.");
            ex.printStackTrace();
            ret = -1;
        }

        return ret;
    }

}
