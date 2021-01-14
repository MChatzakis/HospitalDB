/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

/**
 * Class to get the date for SQL table entries. 
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
*/
public class JavaDate {

    public static String getDefaultDate(){
        return java.time.LocalDate.now().toString();
    }
    
}
