/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.libraries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uk.co.jofaircloth.ringsim.SearchResults;
import uk.co.jofaircloth.ringsim.enums.*;
import uk.co.jofaircloth.ringsim.settings.Settings;
import uk.co.jofaircloth.ringsim.utils.*;

/**
 *
 * @author gibbs
 */
public class ReadMSLIB extends Read {
    
    private String stageNumber;
    private String classCode;
    private String search;
    private List<SearchResults> searchResults;
 
    public ReadMSLIB() {
    	this("", "", "");
    }
    
    public ReadMSLIB(MethodClass methodClass, MethodStage methodStage) {
    	this(Integer.toString(methodStage.getStageNumber()), methodClass.getClassAbbreviation(), "");
    }
        
    public ReadMSLIB(String stageNumber, String classCode, String srch) {    	
    	if (stageNumber.equals("0"))
    		stageNumber = "";
    	
        this.fileLocation = Settings.getMSLibFileName();
        this.stageNumber = stageNumber;
        this.classCode = classCode;
        this.search = srch.replace(" ", "");
        searchResults = new ArrayList<SearchResults>();
    }
    
    public ReadMSLIB(String srch) {
    	this.fileLocation = Settings.getMSLibFileName();
    	/* manipulate the string we're parsed (eg Amber valley surprise major, becomes
    	 * Ambervalley, with stageNumber = 8 and classCode = "S", and filter the searched
    	 * files; if no class or stage are parsed - just search the srch string)
    	 */
    	
    	String methodClass = "S";
    	String methodStage = "8";
    	String searchName = srch;

    	// TODO: move to a constructor call (requires setting up getters)
        this.fileLocation = Settings.getMSLibFileName();
        this.stageNumber = methodStage;
        this.classCode = methodClass;
        this.search = searchName;
        searchResults = new ArrayList<SearchResults>();
    }
    
    public ArrayList<String> methodTypeNames() {
        File[] files = this.getDirectoryFiles();
        ArrayList<String> methodTypeNames = new ArrayList<String>(files.length);
        
        for (File f : files) {
            methodTypeNames.add(methodTypeName(f.getName()));
        }

        return methodTypeNames;
    }
    
    private String methodTypeName(String fileName) {
        this.setStageName(MethodStage.getStageName(Utils.getNumbersFromString(fileName)));
        this.setClassName(MethodClass.getClassName(Utils.getCharsFromString(fileName)));
        
        return new MethodTitle(this.getClassName(), this.getStageName()).toString();
    }
    
    /**
     * Search for a specified method name
     * @return
     */
    public List<SearchResults> searchMethod() {
    	// TODO: catch if dir does not exist
        File[] files = this.getDirectoryFiles();
        String method;
        SearchResults results;
        
        try {
	        for (File f : files) {
	            if (this.openRead(f.getPath())) {
	                while ((method = bufferedReader.readLine()) != null) {
	                    if (method.toLowerCase().contains(getSearch().toLowerCase())) {
	                        String[] splt = method.split(" ");
	                        results = new SearchResults(f.getName(), splt[0], splt[1], splt[2]);
	                        searchResults.add(results);
	                    }
	                }   
	                try {
	                	if (bufferedReader != null) {
	                		bufferedReader.close();
	                	}
	                } catch (IOException ex) {
	                	ex.printStackTrace();
	                }
	            }
	        }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        
        if (searchResults.size() < 1) {
            searchResults.add(new SearchResults("", "", "", ""));
        }

        return searchResults;
    }

    /**
     * loop through each file in the specified directory, when the filename equals the 
     * selected class and stage, create a SearchResults object for each result
     * @return 
     */
    public List<SearchResults> searchFiles() {
        // TODO: we need to get the user to store some 'proper' names!
        File [] files = this.getDirectoryFiles();
        String method;
        SearchResults results;
        
        try {
	        for (File f : files) {
	        	// TODO: if it's a 'T' code, we'll also get 'TP', if number is "any"
	        	// check the file name stages with the letter (eg, T, or TP)
	            if (isWantedFile(f.getName())) {
	                if (this.openRead(f.getPath())) {
	                    while ((method = bufferedReader.readLine()) != null) {
	                        if (!method.startsWith("*")) {
	                            String[] splt = method.split(" ");
	                            results = new SearchResults(f.getName(), splt[0], splt[1], splt[2]);
	                            searchResults.add(results);
	                        }
	                    } 
	                    try {
	                    	if (bufferedReader != null) {
	                    		bufferedReader.close();
	                    	}
	                    } catch (IOException ex) {
	                    	ex.printStackTrace();
	                    }
	                }
	            }
	        }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        
        Collections.sort(searchResults);
        return searchResults;
    }
    
    /**
     * Take the file name and see if its name contains the required class and stage
     * @param fileName
     * @return result of checking the filename against class and stage
     */
    private boolean isWantedFile(String fileName) {
   		return (classCode.equals("") || Utils.getCharsFromString(fileName).equals(classCode)) 
   			&& (stageNumber.equals("") || Integer.toString(Utils.getNumbersFromString(fileName)).equals(stageNumber));
    }
    
    @Override
    public String toString() {
        return this.classCode + this.stageNumber.toString();
    }

    /**
     * @return the stageName
     */
    public String getStageName() {
        return stageNumber;
    }

    /**
     * @param stageName the stageName to set
     */
    public void setStageName(String stageName) {
        this.stageNumber = stageName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return classCode;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.classCode = className;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
}
