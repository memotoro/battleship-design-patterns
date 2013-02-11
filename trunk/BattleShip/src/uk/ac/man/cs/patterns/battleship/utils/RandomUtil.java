package uk.ac.man.cs.patterns.battleship.utils;

import java.util.Random;

/**
 * Class useful to create random numbers based on specific parameters.
 * @author Guillermo Antonio Toro Bayona
 */
public class RandomUtil {

    /**
     * Random class from java.
     */
    private static Random randomGenerator;

    /**
     * Static method to generate a random number from 0 (inclusive) to the given max value (exclusive)
     * @param maxValue Integer with the max value allowed.
     * @return Integer random number
     */
    public static Integer generateRandom(Integer maxValue) {
        randomGenerator = new Random();
        return randomGenerator.nextInt(maxValue);
    }
}
