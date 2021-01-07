/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Manos Chatzakis
 */
public class JavaDate {

    public static String getDefaultDate(){
        return java.time.LocalDate.now().toString();
    }
    
    public static void main(String[] args) {
        System.out.println(getDefaultDate());
    }
}
