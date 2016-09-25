/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gibbs
 */
public class Utils {
    
    private Utils() {}
    
    /**
     * Get the integer values from a string
     * @param str
     * @return 
     */
    public static int getNumbersFromString(String str) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        if (m.find())
            return Integer.parseInt(m.group());
        else
            return -1;
    }
    
    public static String getCharsFromString(String str) {
        Pattern p = Pattern.compile("[A-Z]+");
        Matcher m = p.matcher(str);
        if (m.find() == true)
            return m.group();
        else
            return str;
    }
    
    public static boolean isNumber(String str) {
        return str.matches("\\d+");
    }
}
