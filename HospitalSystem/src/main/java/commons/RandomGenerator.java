/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author manos
 */
public class RandomGenerator {

    public static int getRandomNumber(int min, int max) {

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;

    }

    public static void main(String[] args) {
        System.out.println(getRandomNumber(0,10));
    }
}
