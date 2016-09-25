/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.pdf;

//import com.itextpdf.text.*;

import java.util.ArrayList;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.itextpdf.text.BaseColor;
import java.awt.Desktop;
import java.util.Random;
import uk.co.jofaircloth.ringsim.Method;
import uk.co.jofaircloth.ringsim.RingingError;
import uk.co.jofaircloth.ringsim.Row;
import uk.co.jofaircloth.ringsim.settings.Settings;

/**
 * A4 - 11.7 x 8.3 / 297 x 210
 *      1" = 72
 *      842.4 x 597.6
 * 
 * @author jof
 */
public class WritePdf {
    
    // store the index location on each row, so we can attempt to draw a line...
    private ArrayList<PdfPosition> arrIndexTreble;
    private ArrayList<PdfPosition> arrIndexHighlight1;
    
    private Method method;
    private ArrayList<String> notation;
    String title;
    
    public WritePdf(Method m, ArrayList<String> n, String t) {
    	method = m;
    	notation = n;
    	title = t;
    }
    
    public String createPdf() {
        float colWidth = 90f;
        float colHeight = 678.4f;
        float colGutter = 31.5f;
        float marginLeft = 72f;
        float marginBottom = 72f;
        float charSpacing = 5.5f;
        float leadingSpacing = 10.3f;
        
        float yTop = marginBottom + colHeight;
        float yBottom = marginBottom;
        
        float[][] COLUMNS = { /* lower left x/y, upper right x/y */
                  { marginLeft, yBottom, marginLeft + colWidth, yTop }
                , { marginLeft + colWidth + colGutter, yBottom, marginLeft + (2 * colWidth) + colGutter, yTop }
                , { marginLeft + (2 * colWidth) + (2 * colGutter), yBottom, marginLeft + (3 * colWidth) + (2 * colGutter), yTop }
                , { marginLeft + (3 * colWidth) + (3 * colGutter), yBottom, marginLeft + (4 * colWidth) + (3 * colGutter), yTop }
        };
        // 77.4 wide 36 margin

        this.arrIndexHighlight1 = new ArrayList<PdfPosition>(method.size());
        this.arrIndexTreble = new ArrayList<PdfPosition>(method.size());

        Random rand = new Random();
        String fileName;
        if (Settings.getIsTitleFileName()) {
        	fileName = Settings.getPdfFileLocation() + title + ".pdf";
        } else {
        	fileName = Settings.getPdfFileLocation() + "Test_" + rand.nextInt() + ".pdf";
        }
        System.out.println("Printing PDF file..." + fileName);
        Document doc;
        OutputStream file;
    
        try {
            doc = new Document(PageSize.A4);
            file = new FileOutputStream(new File(fileName));
            PdfWriter w = PdfWriter.getInstance(doc, file);
            doc.open();

            PdfContentByte cb = w.getDirectContent();
            ColumnText ct = new ColumnText(cb);
            int column = 0;

            ct.setSimpleColumn(COLUMNS[column][0]
                    , COLUMNS[column][1]
                    , COLUMNS[column][2]
                    , COLUMNS[column][3]);
            
            int status = ColumnText.START_COLUMN;
            float y;
            
            Chunk c1;
//            Phrase p1, p2;
//            doc.add(Chunk.NEWLINE);

            Paragraph p = null;
            Row r;

            //for (String s : method) {
            for (int n = 0; n < method.size(); n++) {
                //addRow(s);
                int i = 1;
                y = ct.getYLine();
                
                if ((y >= COLUMNS[column][3] - leadingSpacing)
                        && (y <= COLUMNS[column][3]) ) {
                    if (n > 1) n--;
                }
                r = method.get(n);
                
                p = new Paragraph();

                for (Character c : r) {
                    c1 = new Chunk(c.toString());
                    if (c.equals('1')) {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(255, 0, 0)));
                        this.arrIndexTreble.add(new PdfPosition(i, y, column));
                    } else if (c.equals('4')) {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 255)));
                        this.arrIndexHighlight1.add(new PdfPosition(i, y, column));
                    } else {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0)));
                    }                    
                    c1.setCharacterSpacing(charSpacing);
                    //p1.setLeading(10f);
                    //p1.add(c1);
                    if (!c.equals(' '))
                        i++;
                    p.add(c1);
                }
                
                p.setAlignment(Element.ALIGN_CENTER);
                //p.setSpacingBefore(10.6f);
                //p.setSpacingAfter(0f);
                p.setLeading(leadingSpacing);
                ct.addElement(p);

                status = ct.go(true);
                if (ColumnText.hasMoreText(status)) {
                    column = (column + 1) % 4;
                    
                    if (column == 0) {
                        doc.newPage();
                    }
                    ct.setSimpleColumn(COLUMNS[column][0]
                            , COLUMNS[column][1]
                            , COLUMNS[column][2]
                            , COLUMNS[column][3]);
                    y = COLUMNS[column][3]; 
                }
                
                ct.setYLine(y);
                ct.setText(null);
                
                p = new Paragraph();

                for (Character c : r) {
                    c1 = new Chunk(c.toString());
                    if (c.equals('1')) {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(255, 0, 0)));
                        //this.arrIndexTreble.add(new PdfPosition(marginLeft + (leadingSpacing * i), y));
                    } else if (c.equals('4')) {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 255)));
                        //this.arrIndexHighlight1.add(new PdfPosition(marginLeft + (leadingSpacing * i), y));
                    } else {
                        c1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0)));
                    }                    
                    c1.setCharacterSpacing(charSpacing);

                    if ((n + 1) % 4 == 0) {
                        c1.setUnderline(new BaseColor(0, 0, 0), 0.1f, 0f, -2f, 0f, PdfContentByte.LINE_CAP_PROJECTING_SQUARE);
                    }
                    
                    //p1.setLeading(10f);
                    //p1.add(c1);
                    i++;
                    p.add(c1);
                }
                
                p.setAlignment(Element.ALIGN_CENTER);
                //p.setSpacingBefore(10.6f);
                //p.setSpacingAfter(0f);
                p.setLeading(leadingSpacing);
                ct.addElement(p);

                status = ct.go();
            }
            
            drawBlueLines(cb);
            doc.close();
            
            openPdf(fileName);
            
        }
        catch (DocumentException dex) {
            System.out.println(dex.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        finally {
        }
    
        return fileName;
    }
    
    private void addRow(String s) {
        
    }
    
    private void drawBlueLines(PdfContentByte cb) {
        // set up the start location
        // the line needs to follow x/y progress - y depending on 1 line down for each itm, and x depending on value of index
        PdfPosition pCurrent;
        PdfPosition pNext;
        
        float offset;
        
        cb.moveTo(69f, 75f);
 
        for (int i = 0; i < this.arrIndexTreble.size(); i++) {
            pCurrent = this.arrIndexTreble.get(i);
            offset = 69f + (pCurrent.getColumn() * (89f + 31f));
            cb.setColorStroke(new BaseColor(255, 0, 0));
            cb.moveTo(pCurrent.getxPos() * 12f + offset, pCurrent.getyPos());
            if (i < this.arrIndexTreble.size() - 1) {
                pNext = this.arrIndexTreble.get(i + 1);
                cb.lineTo(pNext.getxPos() * 12f + offset, pNext.getyPos());
            }
            
            cb.stroke();
            System.out.println("(" + pCurrent.getxPos() + ", " + pCurrent.getyPos() + ")");
        }
        cb.moveTo(69f, 75f);
        for (int i = 0; i < this.arrIndexHighlight1.size(); i++) {
            pCurrent = this.arrIndexHighlight1.get(i);
            offset = 69f + (pCurrent.getColumn() * (89f + 31f));
            cb.setColorStroke(new BaseColor(0, 0, 255));
            cb.moveTo(pCurrent.getxPos() * 12.0f + offset, pCurrent.getyPos());
            if (i < this.arrIndexHighlight1.size() - 1) {
                pNext = this.arrIndexHighlight1.get(i + 1);
                cb.lineTo(pNext.getxPos() * 12.0f + offset, pNext.getyPos());
            }
            
            cb.stroke();
            //System.out.println("(" + pCurrent.getxPos() + ", " + pCurrent.getyPos() + ")");
        }
    }
    
    public void openPdf(String fileName) {
       try {
           File pdf = new File("C:/Users/gibbs/Documents/Me/Code/Ringsim/" + fileName);
           if (pdf.exists()) {
               if (Desktop.isDesktopSupported()) {
                   Desktop.getDesktop().open(pdf);
               } else {
                   RingingError.error("AWT desktop is not supportted");
               }
           } else {
               RingingError.error("File does not exist");
           }
       } catch (Exception ex) {
           RingingError.error("openFile", ex);
       }
    }
    
    private void workingPreCB(ArrayList<String> method, ArrayList<String> notation, String title) {
        Random rand = new Random();
        String fileName;
        if (Settings.getIsTitleFileName()) {
        	fileName = title + ".pdf";
        } else {
        	fileName = "Test_" + rand.nextInt() + ".pdf";
        }
        System.out.println("Printing PDF file..." + fileName);
        Document doc;
        OutputStream file;
        try {
            doc = new Document(PageSize.A4);
            file = new FileOutputStream(new File(fileName));
            PdfWriter.getInstance(doc, file);
            doc.open();            
            doc.add(new Paragraph(title));

            Chunk c1;
            Phrase p1, p2;
            doc.add(Chunk.NEWLINE);
            
            this.arrIndexHighlight1 = new ArrayList<PdfPosition>(method.size());
            this.arrIndexTreble = new ArrayList<PdfPosition>(method.size());
            
            int j = 0;
            // set the first leading to be 1.5x line, so it's offset
            for (String s : method) {
                c1 = new Chunk();
                p1 = new Phrase();
                int i = 0;
                
                if (j < notation.size()) {
                    p2 = new Phrase();
                    c1 = new Chunk(notation.get(j), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0)));
                
                    if (j == 0) 
                        p2.setLeading(15f);
                    else
                        p2.setLeading(10f);
                    p2.add(c1);
                    doc.add(p2);
                    j++;
                }
                
                for (Character c : s.toCharArray()) {
                    if (c.equals('1')) {
                        c1 = new Chunk(c.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(255, 0, 0)));
                        //this.arrIndexTreble.add(new PdfPosition(72f + c1.getWidthPoint(), y));
                    } else if (c.equals('4')) {
                        c1 = new Chunk(c.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 255)));
                        //this.arrIndexHighlight1.add(new PdfPosition(72f + c1.getWidthPoint(), y));
                    } else {
                        c1 = new Chunk(c.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0)));
                    }                    
                    c1.setCharacterSpacing(2f);
                    p1.setLeading(10f);
                    p1.add(c1);
                    i++;
                }
                doc.add(p1);
                doc.add(Chunk.NEWLINE);
            }
            
            doc.addAuthor("Jo Faircloth");
            doc.addCreationDate();
            doc.addCreator("Ringsim / Jo Faircloth");
            doc.addTitle("A Method");

            //drawBlueLines(doc);
            
            doc.close();
            file.close();
        }
        catch (DocumentException dex) {
            System.out.println(dex.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        finally {
        }

    }
}
