/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author gibbs
 */
public class Settings {
    
    private Properties p = new Properties();

    public Settings() {        
        try {
            //p.load(new FileInputStream("./ringing.properties"));            
            p.load(getClass().getResourceAsStream("ringing.properties"));
        }
        catch (IOException ioe) {
            RingingError.error("Settings constructor", ioe);
        }
    }
    
    public String getSetting(String name) {
        return p.getProperty(name);
    }
    
}
