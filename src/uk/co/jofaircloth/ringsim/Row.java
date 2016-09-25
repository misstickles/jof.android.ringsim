/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim;

import java.util.Collections;

/**
 *
 * @author gibbs
 */
public class Row extends Bell
{
    private int noBells;
    
    public Row() {}
    
    public Row(int noBells) {
        this.noBells = noBells;
        rounds();
    }
    
    public Row(String row) {
        for (Character c : row.toCharArray()) {
            this.add(c);
        }
    }
    
    public boolean isRounds(Row r) {
        if (this.equals(r)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private Row rounds() {
        if (this.noBells <= Bell.getMaxBells()) {
            for(int i = 0; i < this.noBells; i++) {
                this.add(Bell.getSymbol(i));
            }
        } else {
            RingingError.error("Too many bells");
        }
        
        return this;
    }
    
    public Row reverse() {
        Collections.reverse((Row)this);
        return this;
    }

    
    @Override
    public String toString() {
        String s = "";
        for (Character c : this) {
            s += " " + c.toString();
        }
        return s;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Row) {
            Row r = (Row)o;
            
            if (this.size() != r.size()) return false;
            
            for (int i = 0; i < this.size(); i++) {
                if (r.get(i) != this.get(i)) {
                    return false;
                }                
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    public char charAt(int idx) {
        return this.get(idx);
    }
    
    public int length() {
        return this.size();
    }


}
