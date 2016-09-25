/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.enums;

/**
 *
 * @author gibbs
 */
public enum Libraries {
    MSLIB   ("C:\\Users\\gibbs\\Documents\\Me\\Code\\Ringsim\\res\\MSLIBS"),
    CCC		("");
    
    private final String location;
    
    Libraries(String loc) {
        location = loc;
    }
    
    public String toString() {
        return location;
    }
    
}
