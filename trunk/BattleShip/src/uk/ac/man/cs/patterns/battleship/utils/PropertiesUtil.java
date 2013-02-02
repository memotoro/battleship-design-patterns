/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.patterns.battleship.utils;

import java.util.Properties;

/**
 *
 * @author memotoro
 */
public class PropertiesUtil {
/**
     * Properties
     */
    private Properties properties;

    private static PropertiesUtil propertiesUtil;

    public static PropertiesUtil getInstance(){
        if(propertiesUtil == null){
           propertiesUtil = new PropertiesUtil();
        }
        return propertiesUtil;
    }

    private PropertiesUtil(){
        try {
            // Load the properties
            this.properties = new Properties();
            this.properties.load(PropertiesUtil.class.getResourceAsStream(Constants.GAME_PATH_FILE_MESSAGES));
        } catch (Exception ex) {
            // print the trace
            ex.printStackTrace();
        }
    }

    public String getMessageByCode(String code){
        return this.properties.getProperty(code);
    }
}
