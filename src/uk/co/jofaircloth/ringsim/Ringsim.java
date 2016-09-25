/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * edited
 */
package uk.co.jofaircloth.ringsim;

import uk.co.jofaircloth.ringsim.gui.MainGui;
import uk.co.jofaircloth.ringsim.pdf.WritePdf;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import org.jdesktop.application.Application;
import uk.co.jofaircloth.ringsim.enums.*;
import uk.co.jofaircloth.ringsim.libraries.*;

/**
 *
 * @author gibbs
 */
public class Ringsim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainGui g = new MainGui("Jo's ringer thing");
        g.setVisible(true);
        
//        for (MethodStage m : MethodStage.values()) {
//            System.out.println(m.getStageNumber() + " " + m.getStageName());
//        }
//        
//        Row r = new Row("123456");
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "16").swap();
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "16").swap();
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "16").swap();
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "16").swap();
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "16").swap();
//        System.out.println(r);
//        r = new Change(r, "x").swap();
//        System.out.println(r);
//        r = new Change(r, "12").swap();
//        System.out.println(r);
//
//        
//        System.out.println(MethodStage.getStageName(12));
//        
//        System.out.println(new Row(12));
//        System.out.println(new Row(36));
//        System.out.println(new Row("13572468"));
//        
//        System.out.println();
//        System.out.println("**********");
//        System.out.println();
//        System.out.println("Testing new Change(new Row(6), '16')");
//        System.out.println(new Change(new Row(6), "16").swap());
//        System.out.println("Testing new Change(new Row(10), 'x')");
//        System.out.println(new Change(new Row(10), "x").swap());
//        System.out.println("Testing new Change(new Row(16), '1458ed')");
//        System.out.println(new Change(new Row(16), "1458e").swap());
//        System.out.println("Testing new Change(new Row(12))");
//        System.out.println(new Change(new Row(12)).swap());
//        System.out.println("Testing new Change(new Row(8), '16').swap().reverse()");
//        System.out.println(new Change(new Row(8), "16").swap().reverse());
//        System.out.println("Testing new Row(6).equals(new Row(8))");
//        System.out.println(new Row(6).equals(new Row(8)));
//        System.out.println("Testing new Row('642135').equals(new Row('642135'))");
//        System.out.println(new Row("642135").equals(new Row("642135")));
//               
//     
//
//        Settings s = new Settings();
//        System.out.println("Testing getSetting('title'): " + s.getSetting("title"));
//        
//        ringsim.libraries.ReadMSLIB mslib = new ringsim.libraries.ReadMSLIB();
//        ArrayList<String> methods = mslib.methodTypeNames();                
//        for (String m : methods) {
//            System.out.println(m);
//        }
//        
//        System.out.println("Testing search for 'Bristol'");
//        ReadMSLIB rmslib = new ReadMSLIB("major", "", "Bristol");
//        List<SearchResults> results = rmslib.searchMethod();
//        for (SearchResults res : results) {
//            System.out.println(res.toString());
//        }
//        System.out.println("Testing search for 'Plonka'");
//        rmslib = new ReadMSLIB("major", "", "Plonka");
//        results = rmslib.searchMethod();
//        for (SearchResults res : results) {
//            System.out.println(res.toString());
//        }
//        
//        Notation n = new Notation("  x.38.x.14.x.1258.x.36.x.14.x.58.x.16.x.78.x.16.x.58.x.14.x.36.x.1258.x.14.x.38.x", "2", MethodStage.MAJOR.getStageNumber());
//        System.out.println(n.format());
//        
//        Notation n2 = new Notation("& x.3.x.4.x.25.x.36.x.4.x.5.x.6.x.7", "2", MethodStage.MAJOR.getStageNumber());
//        System.out.println(n2.format());
//        
//        Notation n3 = new Notation("& x3x4x25x36x4x5x6x7", "2", MethodStage.MAJOR.getStageNumber());
//        System.out.println(n3.format());
//        
        Notation n4 = new Notation("& -3-4-25-36-4-5-6-7", "2", MethodStage.MAJOR.getStageNumber());
        System.out.println(n4.format(Calls.PLAIN));
                
        WritePdf pdf1 = new WritePdf(new Method().getBlueline(n4.format(Calls.PLAIN), MethodStage.MAJOR), n4.format(Calls.PLAIN), "Cambridge Surprise Major");
        String pdfFile = pdf1.createPdf();
        pdf1.openPdf(pdfFile);

        SearchResults res = new SearchResults("S8", "Cam Bobs", "b", "& -3-4-25-36-4-5-6-7");
        Notation nBob = new Notation(res);
        System.out.println(nBob.format(Calls.BOB));
                
        WritePdf pdfBob = new WritePdf(new Method().getBlueline(nBob.format(Calls.BOB), MethodStage.MAJOR), nBob.format(Calls.BOB), "Cambridge Surprise Major ");
        String pdfFileBob = pdfBob.createPdf();
        pdfBob.openPdf(pdfFileBob);
        System.out.println("Printed " + pdfFileBob);
        
        WritePdf pdfSingle = new WritePdf(new Method().getBlueline(nBob.format(Calls.SINGLE), MethodStage.MAJOR), nBob.format(Calls.SINGLE), "Cambridge Surprise Major ");
        String pdfFileSingle = pdfSingle.createPdf();
        pdfSingle.openPdf(pdfFileSingle);
        System.out.println("Printed " + pdfFileSingle);

//        
//        Sound snd = new Sound();
//        
//        System.out.println("Number errors: " + RingingError.numberErrors());
    }
    
}
