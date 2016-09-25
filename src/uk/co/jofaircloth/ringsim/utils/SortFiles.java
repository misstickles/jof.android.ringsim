/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.utils;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author gibbs
 */
public class SortFiles extends Sort implements Comparator<File> {

    @Override
    public int compare(File f1, File f2) {
        return naturalSort(f1.getName(), f2.getName());
    }
    
}
