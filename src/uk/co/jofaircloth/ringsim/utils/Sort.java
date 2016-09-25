/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.utils;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author gibbs
 */
public class Sort {    

    public Sort() {}
    
    /**
     * A natural sort algorithm, to display alphanumeric strings in 'human' readable form
     * (ie 1, 2, 3, 10, 11, 20, 33 not 1, 10, 11, 2, 20, 3, 33)
     * @param s1
     * @param s2
     * @return 
     */
    public int naturalSort(String s1, String s2) {
        String chars1 = Utils.getCharsFromString(s1);
        String chars2 = Utils.getCharsFromString(s2);
        int val = 0;
        
        // if chars are equal, match on digits
        // else do a normal compareTo
        if (chars1.toLowerCase().equals(chars2.toLowerCase())) {
            int digits1 = Utils.getNumbersFromString(s1);
            int digits2 = Utils.getNumbersFromString(s2);
        
            if (digits1 < digits2) val = -1;
            else if (digits1 == digits2) val = 0;
            else if (digits1 > digits2) val = 1;
        }
        else {
            val = s1.compareToIgnoreCase(s2);
        }
        
        return val;
    }
    
    public static File[] sortFilesByName(File[] files) {
        Arrays.sort(files, new SortFiles());
        return files;
    }
}
