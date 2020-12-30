package test;

import database.DBConnection;
import database.DBDataGenerator;
import database.DBInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testing class
 *
 * @author Manos Chatzakis
 */
public class Main
{

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        try
        {

            DBInitializer init = new DBInitializer();
            DBDataGenerator dataGenerator = new DBDataGenerator();

            init.dropDB();
            init.buildDB();

            dataGenerator.insertData();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    public void old() throws SQLException, ClassNotFoundException
    {
        String query = "SELECT * FROM people;";
        String ins_query = "INSERT INTO people VALUES (3, \"Nikos\"  ,21);";
        String ins_query2 = "INSERT INTO people VALUES (10, \"Vlakas\"  ,25);";

        ResultSet res = null;
        DBConnection conn = new DBConnection();

        conn.updateQuery(ins_query2);
        res = conn.executeQuery(query);

        while (res != null && res.next())
        {
            System.out.println(res.getString("id") + " " + res.getString("name") + " " + res.getString("age"));
        }

        conn.closeDBConnection();
    }
}
