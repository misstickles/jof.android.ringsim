/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.pdf;

/**
 *
 * @author gibbs
 */
public class PdfPosition {
    private float xPos;
    private float yPos;
    private int column;
    
    public PdfPosition(float x, float y, int col) {
        this.xPos = x;
        this.yPos = y;
        this.column = col;
    }

    /**
     * @return the xPos
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
}
