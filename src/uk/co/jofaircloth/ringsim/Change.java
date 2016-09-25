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
public class Change {
    
    private Row row;
    private String notation;
    
    public Change(Row r, String notation) {
        this.row = r;
        this.notation = notation;
    }
    public Change(Row r) {
        this.row = r;
        this.notation = Bell.getSymbols();
    }
    
    public Row swap() {
        // store the row, so we can change the original...
        Row origRow = (Row)row.clone();
        
        // for each place in the notation, store its index value (eg 1 = 0, 0 = 9, T = 11, D = 15)
        // if it's an x, or -, it won't exist, and index will be -1
        ArrayList<Integer> places = new ArrayList<Integer>(this.row.size());
        for (Character c : this.notation.toUpperCase().toCharArray()) {
            places.add(Bell.getIndex(c));
        }

        // loop the row, check if its index is contained in the 'places' array
        // if it is, leave it, else swap it ([i] = [i+1] and [i+1] = [i])
        for (int i = 0; i < row.size(); i++) {
            if (places.contains(i)) {
                row.set(i, origRow.get(i));
            } else {
                if (i+1 < row.size()) {
                    row.set(i+1, origRow.get(i));
                    row.set(i, origRow.get(i+1));
                    i++;
                }
            }
        }
        return row;
    }
        
    @Override
    public String toString() {
        String s = "change: ";
        for (Character c : this.row) {
            s += " " + c.toString();
        }
        return s;
    }
    
}
