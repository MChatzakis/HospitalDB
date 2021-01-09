/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

/**
 *
 * @author Manos Chatzakis
 */
public class StringParser {

    public static String [] parseString(String input, String delimeter) {
        String[] out = input.split(delimeter);
        return out;
    }
}
