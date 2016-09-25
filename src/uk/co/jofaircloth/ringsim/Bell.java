/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.util.ArrayList;

/**
 *
 * @author gibbs
 */
public class Bell extends ArrayList<Character> {
    
    private static final String SYMBOLS = "1234567890ETABCDFGHJKLMNPQRSUVWYZ";
    
    /**
     * @param index
     * @return the symbol at the given index
     */
    public static Character getSymbol(int index) {
        return SYMBOLS.charAt(index);
    }
    
    /**
     * @param symbol
     * @return the index from SYMBOLS of the given symbol
     */
    public static int getIndex(char symbol) {
        return SYMBOLS.indexOf(symbol);
    }
    
    /**
     * @return the string of SYMBOLS
     */
    public static String getSymbols() {
        return SYMBOLS;
    }
    
    public static int getMaxBells() {
        return SYMBOLS.length();
    }
        
}
