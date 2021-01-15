/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class offers methods for creating pseudo-random integers withing given
 * range.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class RandomGenerator
{

    public static int getRandomNumber(int min, int max)
    {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

}
