/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.util.ArrayList;

import uk.co.jofaircloth.ringsim.utils.LeadHeadOrders;

/**
 *
 * @author gibbs
 */
public class LeadHeads extends LeadHeadOrders {
    
	private static ArrayList<Row> leadHeads;
	private static int noHuntBells;
	private String plainEnd = "", bobEnd = "", singleEnd = ""; 
	
	private LeadHeads() {
	}
	
	public LeadHeads(String leadHeadGroup, int noBells, String notation) {
		setLeadEndNotation(leadHeadGroup, noBells, notation);
	}

	public static LeadHeadOrders getLeadHeadOrder(String leadHead, int noBells, String notation) {
		LeadHeadOrders order = new LeadHeadOrders();
		
		//order.leadHead = new Row("1234");
		
		return order;
	}	
	
    /**
     * Returns the lead end lead, based on the character of its group
     * lead end depends on:
     * 		no hunt bells in method
     * 		odd or even bells
     * a - twin hunt, symmetric like grandsire
     * b - twin hunt, symmetric like plain bob
     * c - twin hunt, asymmetric
     * palindromic single hunt and twin hunt (b) methods will start with & (ie, only first 1/2)
     * twin hunt (a) methods is up to where hunt bells cross at back.....
     * non-palindromic and twin hunt (c) methods - entire notation is given 
     * methods with plain bob leads are given in the tables
     * 
     * surprise major goes a-m with some 2z and 8z
     * 
     * Comments: for non-reversible notation (starting +),
     * setSingleEnd and setBobEnd must have 2 places, as 2 are removed
     * @param lh
     */
	public void setLeadEndNotation(String le, int noBells, String notation) {
		getNumberHuntBells();
		
    	switch(le.toLowerCase().toCharArray()[0]) {
    	case 'a':
        	if (notation.startsWith("&")) {
				if (noBells % 2 == 0) {
					setPlainEnd("2");
					setBobEnd("4");
					setSingleEnd("23");
				} else {
					setPlainEnd("1");
				}
        	} else {
        		setPlainEnd("");
        		setBobEnd("3.1");
        		setSingleEnd("3.23");
        	}
    		break;
    	case 'b':
    	case 'c':
    		if (noBells % 2 == 0) {
    			setPlainEnd("2");
    			setBobEnd("4");
    			setSingleEnd("234");
    		} else {
    			setPlainEnd("3");
    			setBobEnd("3");
    			setSingleEnd("3");
    		}
    		break;
    	case 'd':
    	case 'e':
	    case 'f':
    		setPlainEnd("2");
    		setBobEnd("4");
    		setSingleEnd("234");
    		break;
	    case 'g':
	    case 'h':
	    case 'i':
	    case 'j':
	    case 'k':
	    case 'l':
	    case 'm':
    		setPlainEnd(Integer.toString(noBells));
    		setBobEnd(Integer.toString(noBells - 2));
    		break;
	    case 'p':
        	if (notation.startsWith("&")) {
		    	setPlainEnd("2");
		    	setBobEnd("4");
		    	setSingleEnd("234");
        	} else {
        		setPlainEnd("");
        		setBobEnd("3.1");
        		setSingleEnd("3.23");
        	}
	    	break;
	    case 'q':	// TODO: q is wrong...
	    	setPlainEnd("2");
	    	break;
	    case 'r':
	    	setPlainEnd("1");
	    	break;
	    case 's':
	    	setPlainEnd("1");
	    	break;
	    default:
	    	if (le.length() > 1) {
	    		if (le.contains("z")) {
	    			// plain lead pn is the number of 2z or 25z or 345z or ...
	    			setPlainEnd(Integer.toString(uk.co.jofaircloth.ringsim.utils.Utils.getNumbersFromString(le)));
	    			setBobEnd("5.1.3.1");
	    			setSingleEnd("567.1.3.1");
	    		}
	    	} else {
	    		setPlainEnd("");	// for doubles 'z' (can't convert null to string if no numerics!)
	    	}
	    	break;	    
    	}
    }
	
	public static int getNumberHuntBells() {
		leadHeads = new ArrayList<Row>();
		for (Row r : leadHeads) {
			
		}
		return 0;
	}

	/**
	 * @param plainEnd the plainEnd to set
	 */
	public void setPlainEnd(String plainEnd) {
		this.plainEnd = plainEnd;
	}

	/**
	 * @return the plainEnd
	 */
	public String getPlainEnd() {
		return plainEnd;
	}

	/**
	 * @param bobEnd the bobEnd to set
	 */
	public void setBobEnd(String bobEnd) {
		this.bobEnd = bobEnd;
	}

	/**
	 * @return the bobEnd
	 */
	public String getBobEnd() {
		return bobEnd;
	}

	/**
	 * @param singleEnd the singleEnd to set
	 */
	public void setSingleEnd(String singleEnd) {
		this.singleEnd = singleEnd;
	}

	/**
	 * @return the singleEnd
	 */
	public String getSingleEnd() {
		return singleEnd;
	}
	

	
}
