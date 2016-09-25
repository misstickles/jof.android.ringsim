/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.libraries;

import uk.co.jofaircloth.ringsim.utils.Sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author gibbs
 */
public class Read {
    
	protected BufferedReader bufferedReader;
    protected File folder;
    protected String fileLocation;
   
    public boolean openRead() {
        try {
        	bufferedReader = new BufferedReader(new FileReader(fileLocation));
//            setScanner(new Scanner(new File(fileLocation)).useDelimiter(DELIMITERS));
        }
        catch (FileNotFoundException fnfEx) {
            return false;
        }
        return true;
    }
    
    boolean openRead(String file) {
        fileLocation = file;
        return openRead();
    }

    
    public File[] getDirectoryFiles() {
//        try {
            folder = new File(fileLocation);
//        }
//        catch (FileNotFoundException fnfEx) {
//            
//        }
        File[] listOfFiles = folder.listFiles();
        return Sort.sortFilesByName(listOfFiles);
    }
    
}
