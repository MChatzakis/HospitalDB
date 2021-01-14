package commons;

/**
 * Class offering string parsing methods.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class StringParser
{

    public static String[] parseString(String input, String delimeter)
    {
        String[] out = input.split(delimeter);
        return out;
    }
}
