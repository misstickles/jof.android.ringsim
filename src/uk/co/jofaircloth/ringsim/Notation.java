/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.util.ArrayList;

import uk.co.jofaircloth.ringsim.enums.Calls;
import uk.co.jofaircloth.ringsim.utils.Utils;

/**
 *
 * @author gibbs
 */
public class Notation extends ArrayList<String> {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placeNotation;
    private String plainLeadEnd;
    private String bobLeadEnd;
    private String singleLeadEnd;
    private int methodStage;
    private ArrayList<String> arrNotation;
    private boolean isReversable = false;
    private Calls calls;
    
    private final String DELIMITER = "\\.";

    public Notation(String pn, String plainLeadEnd, int stage) {
        this.placeNotation = pn;
        this.plainLeadEnd = plainLeadEnd;
        //this.bobLeadEnd = res.getBobLeadEndNotation();
        //this.singleLeadEnd = res.getSingleLeadEndNotation();
        this.methodStage = stage;
        if (pn.startsWith("&")) isReversable = true;
        arrNotation = new ArrayList<String>();
    }
    
    public Notation(SearchResults res) {
        if (res != null) {
            this.placeNotation = res.getNotation();
            this.plainLeadEnd = res.getPlainLeadEndNotation();
            this.bobLeadEnd = res.getBobLeadEndNotation();
            this.singleLeadEnd = res.getSingleLeadEndNotation();
            this.methodStage = res.getMethodStage();
            if (res.getNotation().startsWith("&")) isReversable = true;
            arrNotation = new ArrayList<String>();
        }
    }
    
    // '�X', 'x' or '-'  All bells cross, no places made between changes. 
    // '.'           Change separator, e.g. 36.14 describes two changes. This must describe a wrong place method; otherwise every other section would have an ‘x’ or ‘-‘.
    // 36-36        Is equivalent to '3.x.3'. The dots are often omitted adjacent to a '-' or 'x' as there is no ambiguity without them. 
    // &            Do the following place notation forwards and then backwards; do not repeat the final section on reversing. This implies symmetry in the method. 
    // +            Do the following place notation forwards only. Therefore '&-18-14' is equivalent to '+-18-14-18-, but less concise.
    // 1) x38x14x1258x36x14x58x16x78x16x58x14x36x1258x14x38x12
    // 2) & x38x14x1258x36x14x58x16x78,12
    // 3) & x3x4x25x36x4x5x6x7 LH2
    // 4) & -3-4-25-36-4-5-6-7, 2
    public ArrayList<String> format(Calls call) {
    	this.calls = call;
    	
    	this.placeNotation = this.placeNotation.replaceAll("[&\\+ ]+", "");
        this.placeNotation = this.placeNotation.replaceAll("[x-]+", ".x.");
        this.placeNotation = this.placeNotation.replace("..", ".");

        arrNotation = this.splitNotation();
        
        if (isReversable) {
            for (int i = arrNotation.size() - 2; i >= 0; i--) {
                arrNotation.add(arrNotation.get(i));
            }
        }
        
        // add the lead head
        switch (call) {
        case PLAIN:
            if (!this.plainLeadEnd.equals("") && Integer.parseInt(this.plainLeadEnd) > 0) {
            	arrNotation.add(this.plainLeadEnd);
            }
        	break;
        case BOB:
        	/* at a bob, if notation starts with a + (ie FULL notation is given already),
        	 * replace the last notation rows with bob notation (to the number of rows given)...
        	 */
        	if (!this.bobLeadEnd.equals("")) {
        		if (!isReversable) {
        			String[] bob = this.bobLeadEnd.split(DELIMITER);
        			for (int i = 0; i < bob.length; i++) {
            			arrNotation.remove(arrNotation.size() - 1);        				
        			}
        			for (String s : bob) {
        				arrNotation.add(s);
        			}
        		} else {
        			String[] bob = this.bobLeadEnd.split(DELIMITER);
        			for (int i = 0; i < bob.length - 1; i++) {
            			arrNotation.remove(arrNotation.size() - 1);        				
        			}
        			for (String s : bob) {
        				arrNotation.add(s);
        			}
        		}
        	}                
        	break;
        case SINGLE:
        	/* at a single, if notation starts with a + (ie FULL notation is given already),
        	 * replace the last last notation rows with bob notation (to the number of rows given)...
        	 */
        	if (!this.singleLeadEnd.equals("")) {
        		if (!isReversable) {
        			String[] single = this.singleLeadEnd.split(DELIMITER);
        			for (int i = 0; i < single.length; i++) {
        				arrNotation.remove(arrNotation.size() - 1);
        			}
        			for (String s : single) {
        				arrNotation.add(s);
        			}
        		} else {
        			String[] single = this.singleLeadEnd.split(DELIMITER);
        			for (int i = 0; i < single.length - 1; i++) {
        				arrNotation.remove(arrNotation.size() - 1);
        			}
        			for (String s : single) {
        				arrNotation.add(s);
        			}
        		}
        	}                
        	break;
        }

        addExternalPlaces(arrNotation);
        
        return arrNotation;
    }
    
    // if the first place in the row is even, prefix a '1'. 
    // For even bell methods, if the last place in the row is odd, add an nths place to the end.
    // For odd bell methods, if the last place is even, add an nths place to the end.
    private void addExternalPlaces(ArrayList<String> n) {
        String s = "";
        for (int i = 0; i < n.size(); i++) {
            s = n.get(i);
            if (Utils.isNumber(s)) {
                if (s.charAt(0) % 2 == 0) {
                    s = "1" + s;
                    n.set(i, s);
                }
                
                if (this.getMethodStage() % 2 == 0) {  // even method
                    if (s.charAt(s.length() - 1) % 2 == 1) {
                        n.set(i, s + Bell.getSymbol(this.getMethodStage() - 1));
                    }
                } else {    // odd method
                    if (s.charAt(s.length() - 1) % 2 == 0) {
                        n.set(i, s + Bell.getSymbol(this.getMethodStage() - 1));
                    }
                }
            }
        }
        
        arrNotation = n;
    }
    
    @Override
    public String toString() {
        String var = "";
        if (arrNotation == null) return "";
        for (String s : arrNotation) {
            s += s;
        }
        return var;
    }
    
    private ArrayList<String> splitNotation() {
        String[] splt = this.getPlaceNotation().split(DELIMITER);
        ArrayList<String> lst = new ArrayList<String>(splt.length);
        for (String s : splt) {
            if (!s.equals("")) {
                lst.add(s);
            }
        }
        return lst;
    }
    
    /**
     * @return the leadHead
     */
    public String getPlainLeadEnd() {
        return plainLeadEnd;
    }

    /**
     * @param leadHead the leadHead to set
     */
    public void setPlainLeadEnd(String leadHead) {
        this.plainLeadEnd = leadHead;
    }

    /**
     * @return the methodStage
     */
    public int getMethodStage() {
        return methodStage;
    }

    /**
     * @param methodStage the methodStage to set
     */
    public void setMethodStage(int methodStage) {
        this.methodStage = methodStage;
    }

    /**
     * @return the placeNotation
     */
    public String getPlaceNotation() {
        return placeNotation;
    }

    /**
     * @param placeNotation the placeNotation to set
     */
    public void setPlaceNotation(String placeNotation) {
        this.placeNotation = placeNotation;
    }
    
    public Calls getCalls() {
    	return calls;
    }
}
