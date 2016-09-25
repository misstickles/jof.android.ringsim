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
public class RingingError extends ArrayList<String> {
    
    private static ArrayList<String> errs;
    
    private RingingError() {}
    
    public static void error(String err) {
        if (errs == null) {
            errs = new ArrayList<String>();
        }
        System.out.println(err);
        errs.add(err);
    }
    
public static void error(String err, Exception ex) {
    if (errs == null) {
        errs = new ArrayList<String>();
    }
    
    System.out.println("** " + err);
    System.out.println("** " + ex.getMessage());
    errs.add(err);
    
}
    public static int numberErrors() {
        return errs.size();
    }
}
