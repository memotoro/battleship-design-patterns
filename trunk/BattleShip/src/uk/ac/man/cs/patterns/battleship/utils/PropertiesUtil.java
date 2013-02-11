package uk.ac.man.cs.patterns.battleship.utils;

import java.util.Properties;

/**
 * Singleton Patterns. PropertiesUtil is a class useful to read information from the files by key.
 * Use singleton pattern in order to avoid more that one instance of the class have access to the file.
 * @author Guillermo Antonio Toro Bayona
 */
public class PropertiesUtil {

    /**
     * Properties to read file by key.
     */
    private Properties properties;
    /**
     * Static variable of the same type of the class. Feature of singleton.
     */
    private static PropertiesUtil propertiesUtil;

    /**
     * Constructor. Call the properties and load the specific file.
     */
    private PropertiesUtil() {
        try {
            // Load the properties
            this.properties = new Properties();
            // Load the file with specific path.
            this.properties.load(PropertiesUtil.class.getResourceAsStream(Constants.GAME_PATH_FILE_MESSAGES));
        } catch (Exception ex) {
            // print the trace
            ex.printStackTrace();
        }
    }

    /**
     * Singleton Pattern. Method that give access to the only instance of the class.
     * Initialise the variable if is not created.
     * @return PropertiesUtil
     */
    public static PropertiesUtil getInstance() {
        // Validation
        if (propertiesUtil == null) {
            // Initialize the variable
            propertiesUtil = new PropertiesUtil();
        }
        // Return the reference
        return propertiesUtil;
    }

    /**
     * Method to get one specific message with a key.
     * @param code String with the key
     * @return String with the description of the key.
     */
    public String getMessageByCode(String code) {
        return this.properties.getProperty(code);
    }
}
