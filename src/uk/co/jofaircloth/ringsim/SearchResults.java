/*
 * SearchResults
 * A class to hold data found from a search of any of the libraries
 * 
 */
package uk.co.jofaircloth.ringsim;

import uk.co.jofaircloth.ringsim.enums.*;
import uk.co.jofaircloth.ringsim.utils.Utils;

/**
 *
 * @author gibbs
 */
public class SearchResults implements Comparable {
    
    private String fileName;
    private String methodClass;
    private String methodName;
    private String leadHeadGroup;
    private String notation;
    private int methodStage;
    private LeadHeads leadEnds;
    
    public SearchResults(String file, String method, String lh, String not) {
        this.fileName = file;
        this.methodClass = Utils.getCharsFromString(file);
        this.methodName = method;
        this.leadHeadGroup = lh;
        this.notation = not;
        this.methodStage = Utils.getNumbersFromString(file);
	        // set and get the lead end notation
    	if (!file.equals("")) {
			leadEnds = new LeadHeads(leadHeadGroup, methodStage, notation);
    	} else {
    		leadEnds = new LeadHeads("z", -1, "");
    	}
    }
    public SearchResults(String method, String lh, String not, String methodClass, String methodStage) {
        this.methodClass = methodClass;
        this.methodName = method;
        this.leadHeadGroup = lh;
        this.notation = not;
        this.methodStage = Integer.parseInt(methodStage);
        // set and get the lead end notation
		leadEnds = new LeadHeads(this.leadHeadGroup, this.methodStage, this.notation);
    }
        
    @Override
    public String toString() {
        String s = "";
        //s += this.getMethodName() + " " + this.getMethodClassStage() + ": " + this.getNotation();
        s += this.methodName + " " 
                + MethodClass.getClassName(this.methodClass) + " "
                + MethodStage.getStageName(this.methodStage) 
                + " (" + leadHeadGroup + ")";
        return s;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof SearchResults) {
            SearchResults sr = (SearchResults) o;
            int lastCmp = methodName.compareTo(sr.methodName);
            return (lastCmp != 0 ? lastCmp : this.methodClass.compareTo(sr.methodClass));
        }
        return 0;
    }
    
    public String getNotation() {
    	return notation;
    }
    
    public String getPlainLeadEndNotation() {
    	return leadEnds.getPlainEnd();
    }
    public String getBobLeadEndNotation() {
    	return leadEnds.getBobEnd();
    }
    public String getSingleLeadEndNotation() {
    	return leadEnds.getSingleEnd();
    }
    public String getLeadEndGroup() {
    	return leadHeadGroup;
    }
    public String getMethodName() {
    	return methodName;
    }
    
    /**
     * Retrns the number of bells in selected method
     * @return
     */
    public int getMethodStage() {
    	return methodStage;
    }
    
    public String getMethodClass() {
    	return methodClass;
    }

}
