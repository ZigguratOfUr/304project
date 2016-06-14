package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class ViewFlightsPage extends Page implements ActionListener

{
	JButton b1, b2, b3;
	JScrollPane scrollPane;
	String actionCommand;
	JLabel searchInput;
	JTextField textInput;
	String dest;
	int fid;
    Object[][] data = dc.getViewFlightTable(); 
    JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
   

	
	
	public ViewFlightsPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);

	}
	
	@Override
	public void createPage()
	{

		
        
        b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoExamplePage");
        b1.addActionListener(mainComponent);
        b1.setBounds(50,20,80,25);
        
        mainComponent.add(b1);
        
        b2 = new JButton("Buy ticket for scheduled flights");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoBuyTicketsPage");
        b2.addActionListener(mainComponent);
        b2.setBounds(60, 20, 80, 25);

        mainComponent.add(b2);
        
        
     
        
        
        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        table.setFillsViewportHeight(true);
        TableModel model = (TableModel)table.getModel();
//        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
//        table.setRowSorter(rowSorter);
       

        	
        
        
        
        if (scrollPane!=null)
        {
        	mainComponent.remove(scrollPane);
        }
        
        scrollPane = new JScrollPane(table);
        
        mainComponent.add(scrollPane);
        mainComponent.revalidate();
        mainComponent.repaint();
        
 
        
        JRadioButton radio1 = new JRadioButton("DESTINATION");
        radio1.setBounds(7, 40, 60, 20);
        radio1.setActionCommand("Destination");
        radio1.addActionListener(this);
        radio1.setSelected(true);
        mainComponent.add(radio1);
        
        JRadioButton radio2 = new JRadioButton("FLIGHT ID");
        radio2.setBounds(10,40,60,20);
        radio2.setActionCommand("Flight Id");
        radio2.addActionListener(this);
        mainComponent.add(radio2);
        
        
        ButtonGroup group = new ButtonGroup();
        
        
        group.add(radio1);
        group.add(radio2);
        
        
        searchInput = new JLabel("Search by selected type: ");
        searchInput.setBounds(40, 40, 60, 20);
        textInput = new JTextField(20);
        
        mainComponent.add(searchInput);
        mainComponent.add(textInput);
  
        
        b3 = new JButton("Search");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setBounds(100, 40,60,20);
        b3.setActionCommand("searchTable");
        b3.addActionListener(this);

        mainComponent.add(b3);
        
       

        

	}
        
        // search bar
        
//        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());

        
//        JTextField searchFilter = new JTextField();     
//        table.setRowSorter(rowSorter);
//        
//        JPanel searchPanel = new JPanel(new BorderLayout());
//        searchPanel.setLayout(new GridLayout(1,2,1,1));
//        
//        
//        JRadioButton radio1 = new JRadioButton("Destination");
//        radio1.setActionCommand("Destination");
//        radio1.addActionListener(this);
//        radio1.setSelected(true);
//        
//        JRadioButton radio2 = new JRadioButton("Flight Id");
//        radio2.setActionCommand("Flight Id");
//        radio2.addActionListener(this);
//        
//        ButtonGroup group = new ButtonGroup();
//        
//        
//        group.add(radio1);
//        group.add(radio2);
//        
//        searchPanel.add(radio1);
//        searchPanel.add(radio2);
//        
//        
//        
//       searchPanel.add(new JLabel("Search by selected type:"),BorderLayout.WEST);
//       searchPanel.add(searchFilter,BorderLayout.CENTER);
//       
//       table.setLayout(new BorderLayout());
//       table.add(searchPanel, BorderLayout.SOUTH);
       
       
       

//       //text filtering in search bar
//       searchFilter.getDocument().addDocumentListener(new DocumentListener(){
//
//		@Override
//		public void changedUpdate(DocumentEvent de) {
//			String currentText = searchFilter.getText();
//			
//			if(currentText.trim().length() == 0){
//				rowSorter.setRowFilter(null);
//			}
//			else{
//				TableModel model = (TableModel)table.getModel();
//				int rowSelection = table.getSelectedRow();
//				int rowCount = table.getRowCount();
//				int columnCount = table.getColumnCount();
//				
//				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
//				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
//					
//
//				if(actionCommand == "Destination"){
//					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
//				}
//				
//				if(actionCommand == "Flight Id"){
//					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
//				}
//
//				
//			}			
//		}
//		@Override
//		public void insertUpdate(DocumentEvent de) {
//			String currentText = searchFilter.getText();
//			
//			if(currentText.trim().length() == 0){
//				rowSorter.setRowFilter(null);
//			}
//			else{
//				
//				TableModel model = (TableModel)table.getModel();
//				int rowSelection = table.getSelectedRow();
//				int rowCount = table.getRowCount();
//				int columnCount = table.getColumnCount();
//				
//				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
//				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
//					
//
//				if(actionCommand == "Destination"){
//					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
//				}
//				
//				if(actionCommand == "Flight Id"){
//					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
//				}
//				
////				for(int i = 0; i < rowCount; i++){
////					if(model.getValueAt(i, 0) == currentText) {
////						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
////					}
////				}
////				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
//			}			
//		}
//		@Override
//		public void removeUpdate(DocumentEvent de) {
//			String currentText = searchFilter.getText();
//			
//			if(currentText.trim().length() == 0){
//				rowSorter.setRowFilter(null);
//			}
//			else{
//				
//				TableModel model = (TableModel)table.getModel();
//				int rowSelection = table.getSelectedRow();
//				int rowCount = table.getRowCount();
//				int columnCount = table.getColumnCount();
//				
//				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
//				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
//					
//
//				if(actionCommand == "Destination"){
//					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
//				}
//				
//				if(actionCommand == "Flight Id"){
//					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
//				}
//				
////				for(int i = 0; i < rowCount; i++){
////					if(model.getValueAt(i, 0) ==) {
////						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
////					}
////				}
////				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
//			}			
//		}    	   
//       });
//	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(searchInput);
		mainComponent.remove(textInput);
		
		// cant remove the radio buttons???
//		mainComponent.remove(radio1);
//		mainComponent.remove(radio2);
		

		
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}

	}


	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if("Destination".equals(evt.getActionCommand())){
			actionCommand = evt.getActionCommand();
//		    System.out.println(actionCommand);
		}
    	else if("Flight Id".equals(evt.getActionCommand())){
    		actionCommand = evt.getActionCommand();
//		       System.out.println(actionCommand);
		}
    	else if("viewAll".equals(evt.getActionCommand())){
    		actionCommand = evt.getActionCommand();
//		       System.out.println(actionCommand);
		}
		if("searchTable".equals(evt.getActionCommand())){
			if( actionCommand == "Destination"){
			String text = textInput.getText();
			
			if("".equals(text)) {
				JOptionPane.showMessageDialog(mainComponent,  "Please type in an input", "Incorrect input", JOptionPane.ERROR_MESSAGE);
				
			}
			else {
				try {
					
					dest = textInput.getText();
//					ResultSet rs = dc.getDestSearch(dest);
					dc.getDestSearch(dest);
					System.out.println(text);
					
					
//					destFlightTable(dest);
					
					
					
//					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//					tableModel.fireTableDataChanged();
					


				}
				catch(SQLException s){
					JOptionPane.showMessageDialog(mainComponent, "Not Found", "Incorrect input", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
			else if(actionCommand == "Flight Id"){
				String text = textInput.getText();
				
				if("".equals(text)){
					JOptionPane.showMessageDialog(mainComponent,  "Please type in an input", "Incorrect input", JOptionPane.ERROR_MESSAGE);
				}
				else{
					try{
						fid = Integer.parseInt(textInput.getText());
						dc.getFidSearch(mainComponent,fid);
						System.out.println(text);
					}
					catch(NumberFormatException ext){
						JOptionPane.showMessageDialog(mainComponent,  "Please input numbers for flight id", "Incorrect input", JOptionPane.ERROR_MESSAGE);
					}
					catch(SQLException s){
						JOptionPane.showMessageDialog(mainComponent,  "Not Found", "Incorrect input", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
}
}


	
//	private void destFlightTable(String dest){
//	    Object[][] data = dc.getdestTable(dest); 
//	    JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
//	    
//        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
//        table.setFillsViewportHeight(true);
//        
//        if (scrollPane!=null)
//        {
//        	mainComponent.remove(scrollPane);
//        }
//        
//        scrollPane = new JScrollPane(table);
//        
//        mainComponent.add(scrollPane);
//        mainComponent.revalidate();
//        mainComponent.repaint();
//	}
}
