package script;

import database.DBDataGenerator;
import database.DBInitializer;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates and initializes the hospital database.
 * You may run this script on first execution of the project.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class Main {

    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        try {

            DBInitializer init = new DBInitializer();
            DBDataGenerator dataGenerator = new DBDataGenerator();

            init.dropDB();
            init.buildDB();

            dataGenerator.insertData();

        } catch (SQLException ex) {
            Logger.getLogger(DBInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
