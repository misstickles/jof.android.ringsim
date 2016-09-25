/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.utils;

/**
 *
 * @author gibbs
 */
public class MethodTitle {
    
    private String methodClass;
    private String methodStage;
    
    public MethodTitle(String mClass, String mStage) {
        methodClass = mClass;
        methodStage = mStage;
    }
    
    @Override
    public String toString() {
        return methodClass + " " + methodStage;
    }
}
