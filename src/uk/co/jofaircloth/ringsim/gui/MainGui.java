/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jofaircloth.ringsim.gui;

import com.itextpdf.text.BaseColor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import uk.co.jofaircloth.ringsim.Method;
import uk.co.jofaircloth.ringsim.Notation;
import uk.co.jofaircloth.ringsim.Row;
import uk.co.jofaircloth.ringsim.SearchResults;
import uk.co.jofaircloth.ringsim.enums.MethodStage;
import uk.co.jofaircloth.ringsim.enums.*;
import uk.co.jofaircloth.ringsim.libraries.ReadMSLIB;

/**
 *
 * @author gibbs
 */
public class MainGui extends Gui {
    
    private static final int FRAME_WIDTH = 780;
    private static final int FRAME_HEIGHT = 360;
    private SearchResults res;
    
    /* popup thingies */
    private JDialog aboutBox;
    
    /* panels */
    private JPanel pnlLeft;
    private JPanel pnlCentre;
    private JPanel pnlRight;
    
    /* left panel */
    private JComboBox cmbStage;
    private JComboBox cmbClass;
    private JList lstMethodResults;
    private JLabel lblCounter;
    private JScrollPane scrMethodResults;
    
    /* right panel */
    private JScrollPane scrBlueLine;
    private JTable tblBlueLine;
    private BlueLineModel tblModel;
    private JLabel lblMethodBlueLine;

    /* toolbar */
    private JToolBar tb;
    
    /* menu */
    private JMenuItem miExit;
    private JMenuItem miAbout;
    
    public MainGui(String title) {
        super();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupLookAndFeel();
        setupMenu();
        setupToolBar();     
        setupPanels();
    }
        
    private void setupMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenu mnuEdit = new JMenu("Edit");
        JMenu mnuView = new JMenu("View");
        JMenu mnuOptions = new JMenu("Options");
        JMenu mnuRinging = new JMenu("Ringing");
        JMenu mnuHelp = new JMenu("Help");

        miExit = new JMenuItem("Exit");
        mnuFile.add(miExit);
        miExit.addActionListener(new ButtonWatcher());
        
        miAbout = new JMenuItem("About");
        mnuHelp.add(miAbout);
        miAbout.addActionListener(new ButtonWatcher());

        mb.add(mnuFile);
        mb.add(mnuEdit);
        mb.add(mnuView);
        mb.add(mnuOptions);
        mb.add(mnuRinging);
        mb.add(mnuHelp);
        this.setJMenuBar(mb);        
    }
    
    private void setupToolBar() {
        tb = new JToolBar();
        tb.add(new JButton("test"));
        this.add(tb, BorderLayout.PAGE_START);
    }
    
    private void setupPanels() {
        pnlLeft = new JPanel(new BorderLayout());
        pnlCentre = new JPanel();
        pnlRight = new JPanel(new BorderLayout());
        
        JPanel pnlSelectMethod = new JPanel(new BorderLayout());
        JPanel pnlSelectedMethod = new JPanel(new BorderLayout());

        lblMethodBlueLine = new JLabel();
        
        lstMethodResults = new JList();
        cmbClass = new JComboBox(MethodClass.values());
        cmbClass.setSelectedIndex(7);
        cmbClass.addItemListener(new ItemWatcher());
        cmbStage = new JComboBox(MethodStage.values());
        cmbStage.setSelectedIndex(2);
        cmbStage.addItemListener(new ItemWatcher());

        lblCounter = new JLabel();
        lstMethodResults.setListData(getMethodResults().toArray());
        lstMethodResults.addListSelectionListener(new ListSelectionWatcher());
        lstMethodResults.setSelectedIndex(3);
        
        pnlSelectMethod.add(cmbClass, BorderLayout.NORTH);
        pnlSelectMethod.add(cmbStage, BorderLayout.CENTER);
        scrMethodResults = new JScrollPane(lstMethodResults);
        pnlSelectedMethod.add(scrMethodResults, BorderLayout.CENTER);
        pnlSelectedMethod.add(lblCounter, BorderLayout.SOUTH);
        pnlLeft.add(pnlSelectMethod, BorderLayout.NORTH);
        pnlLeft.add(pnlSelectedMethod, BorderLayout.CENTER);        

        /* right panel */
        setupBlueLinePanel();
        
        scrBlueLine = new JScrollPane(tblBlueLine);
        pnlRight.add(lblMethodBlueLine, BorderLayout.NORTH);
        pnlRight.add(scrBlueLine, BorderLayout.CENTER);

        getContentPane().add(pnlLeft, BorderLayout.WEST);
        getContentPane().add(pnlCentre, BorderLayout.CENTER);
        getContentPane().add(pnlRight, BorderLayout.EAST);        
    }
    
    private void getBlueLine() {
        if (res != null) {
            Notation n = new Notation(res);
            Method m = new Method();
            ArrayList<Row> blue = m.getBlueline(n.format(Calls.PLAIN), MethodStage.MAJOR);

            JLabel lbl;
            int i = 0, j = 0;
            for (Row r : blue) {                
                for (Character c : r) {
                    lbl = new JLabel(c.toString());
                    if (c.equals('1')) {
                        lbl.setForeground(Color.RED);
                    } else if (c.equals('4')) {
                        lbl.setForeground(Color.BLUE);
                    }
                    tblModel.setValueAt(c, i, j);
                    tblBlueLine.add(lbl);
                    
                    j++;
                }
                
                i++;
            }
            
////            tblBlueLine.tableChanged(new TableModelEvent(tblBlueLine.getModel()));
////            SwingUtilities.updateComponentTreeUI(scr);
////            tblModel = (DefaultTableModel) tblBlueLine.getModel();
//            tblModel.fireTableChanged(new TableModelEvent(res)));
////            tblBlueLine.getModel().addTableModelListener(new BlueLineTable() TableDataChangedWatcher());
//            
//            tblBlueLine.setPreferredScrollableViewportSize(new Dimension(500, 70));
//            tblBlueLine.setFillsViewportHeight(true);
            lblMethodBlueLine.setText(res.toString());
        }
    }
    
    private List<SearchResults> getMethodResults() {
        ReadMSLIB rMslib = new ReadMSLIB((MethodClass)cmbClass.getSelectedItem(), (MethodStage)cmbStage.getSelectedItem());
        List<SearchResults> results = rMslib.searchFiles();
        lblCounter.setText("Methods found: " + results.size());
        return results;
    }
    
    private class MouseWatcher implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
    private class ItemWatcher implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object src = e.getSource();
            if (src.equals(cmbStage) || src.equals(cmbClass)) {
                lstMethodResults.setListData(getMethodResults().toArray());
            }
        }
        
    }
    
    private class ListSelectionWatcher implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            Object src = e.getSource();
            if (src.equals(lstMethodResults)) {
                setupBlueLinePanel();
//                tblModel = new BlueLineModel((SearchResults)lstMethodResults.getSelectedValue(), (MethodStage)cmbStage.getSelectedItem());
////                tblBlueLine = new JTable(tblModel);
////                tblBlueLine.setDefaultRenderer(Object.class, new BlueLineCellRenderer());
//                tblModel.addTableModelListener(new TableModelWatcher());
//                tblModel.fireTableStructureChanged();
            }
        }        
    }
    
    private class ButtonWatcher implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src.equals(miExit)) {
                System.out.println("Exit pressed");
                System.exit(0);
            } else if (src.equals(miAbout)) {
        if (aboutBox == null) {
            aboutBox = new AboutDialog(getFrame(), true);
            aboutBox.setLocationRelativeTo(getFrame());
        }
        aboutBox.show();
            }
        }        
    }
    private JFrame getFrame() {
        return this;
    }
    
    private class BlueLineModel extends AbstractTableModel {

        private SearchResults res;
        private Notation n;
        private Method m;
        private ArrayList<Row> blueLine;
        
        public BlueLineModel(SearchResults data, MethodStage ms) {
            if (data != null) {
                res = data;
                n = new Notation(res);
                m = new Method();
                blueLine = m.getBlueline(n.format(Calls.PLAIN), ms);
                lblMethodBlueLine.setText(res.toString());
            }
        }

        @Override
        public int getRowCount() {
            if (blueLine != null) {
                return blueLine.size();
            }
            return 0;
        }

        @Override
        public int getColumnCount() {
            if (blueLine != null) {
                return blueLine.get(0).length();
            }
            return 0;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (blueLine != null) {
                return blueLine.get(rowIndex).charAt(columnIndex);
            }
            return "-";
        }            
        
    }

    private class TableModelWatcher implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {            
            setupBlueLinePanel();
        }
        
    }
    
    public class BlueLineCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            
            if (value.toString().equals("1")) {
                c.setForeground(Color.RED);
            } else if (value.toString().equals("4")) {
                c.setForeground(Color.BLUE);
            } else {
                c.setForeground(Color.BLACK);
            }
            
            this.setHorizontalAlignment(JLabel.CENTER);
            
            return c;
        }
    }
    
    private void setupBlueLinePanel() {
        tblModel = new BlueLineModel((SearchResults)this.lstMethodResults.getSelectedValue(), (MethodStage)cmbStage.getSelectedItem());
        tblModel.addTableModelListener(new TableModelWatcher());
        tblBlueLine = new JTable(tblModel);
        tblBlueLine.setDefaultRenderer(Object.class, new BlueLineCellRenderer());
        tblBlueLine.setFillsViewportHeight(true);
        tblBlueLine.setTableHeader(null);
        tblBlueLine.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col;
        DefaultTableColumnModel colModel = (DefaultTableColumnModel)tblBlueLine.getColumnModel();
        for (int i = 0; i < tblModel.getColumnCount(); i++) {
            col = colModel.getColumn(i);
            col.setPreferredWidth(17);
        }
        
        pnlRight.removeAll();
        scrBlueLine = new JScrollPane(tblBlueLine);
        pnlRight.add(lblMethodBlueLine, BorderLayout.NORTH);
        pnlRight.add(scrBlueLine, BorderLayout.CENTER);

    }
}
