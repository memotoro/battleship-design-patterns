/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.patterns.battleship.utils;

import java.util.Random;

/**
 *
 * @author memotoro
 */
public class RandomUtil {

    private static Random randomGenerator;

    public static int generateRandom(int maxValue){
        randomGenerator = new Random();
        return randomGenerator.nextInt(maxValue);
    }
}
