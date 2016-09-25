/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.enums;

/**
 *
 * @author gibbs
 */
public enum MethodClass {
    ALL         ("Any", ""),
    ALLIANCE    ("Alliance", "A"),
    BOB         ("Bob", "B"),
    DELIGHT     ("Delight", "D"),
    DIFFERENTIAL("Differential", "I"),
    HYBRID      ("Hybrid", "H"),
    PLACE       ("Place", ""),
    PLAIN       ("Plain", "P"),
    PRINCIPLE   ("Principle", "O"),
    SLOWCOURSE  ("Slow Course", "SC"),
    SURPRISE    ("Surprise", "S"),
    TREBLE_BOB  ("Treble Bob", "T"),
    TREBLE_PLACE("Treble Place", "TP");
    
    private final String className;
    private final String classAbbreviation;
    
    MethodClass(String name, String abbr) {
        this.className = name;
        this.classAbbreviation = abbr;
    }
    
    public String getClassName() {
        return className;
    }
    public String getClassAbbreviation() {
        return classAbbreviation;
    }
    
    public static String getClassName(String abbr) {
    	String className = "";
        for (MethodClass m : MethodClass.values()) {
            if (m.getClassAbbreviation().equals("")) continue;
            if (abbr.equals(m.getClassAbbreviation())) {
                className = m.getClassName();
            }
        }
        return className;
    }
    
    @Override
    public String toString() {
        return className;
    }
}
