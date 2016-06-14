package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.table.TableCellRenderer;


public class ViewFlightsPage extends Page implements ActionListener

{
	JButton b1, b2;
	JScrollPane scrollPane;
	String actionCommand;
	
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
        b1.setActionCommand("gotoCustomerMainPage");
        b1.addActionListener(mainComponent);
        
        mainComponent.add(b1);
        
        b2 = new JButton("Buy ticket for scheduled flights");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoBuyTicketsPage");
        b2.addActionListener(mainComponent);

        mainComponent.add(b2);
        
        
        Object[][] data = dc.getViewFlightTable();
        
        JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
        
        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        table.setFillsViewportHeight(true);
        
        if (scrollPane!=null)
        {
        	mainComponent.remove(scrollPane);
        }
        
        scrollPane = new JScrollPane(table);
        
        mainComponent.add(scrollPane);
        mainComponent.revalidate();
        mainComponent.repaint();
        
        // search bar
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());

        
        JTextField searchFilter = new JTextField();     
        table.setRowSorter(rowSorter);
        
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setLayout(new GridLayout(1,2,1,1));
        
        
        JRadioButton radio1 = new JRadioButton("Destination");
        radio1.setActionCommand("Destination");
        radio1.addActionListener(this);
        radio1.setSelected(true);
        
        JRadioButton radio2 = new JRadioButton("Flight Id");
        radio2.setActionCommand("Flight Id");
        radio2.addActionListener(this);
        
        ButtonGroup group = new ButtonGroup();
        
        
        group.add(radio1);
        group.add(radio2);
        
        searchPanel.add(radio1);
        searchPanel.add(radio2);
        
        
        
       searchPanel.add(new JLabel("Search by selected type:"),BorderLayout.WEST);
       searchPanel.add(searchFilter,BorderLayout.CENTER);
       
       table.setLayout(new BorderLayout());
       table.add(searchPanel, BorderLayout.SOUTH);
       
       
       

       //text filtering in search bar
       searchFilter.getDocument().addDocumentListener(new DocumentListener(){

		@Override
		public void changedUpdate(DocumentEvent de) {
			String currentText = searchFilter.getText();
			
			if(currentText.trim().length() == 0){
				rowSorter.setRowFilter(null);
			}
			else{
				TableModel model = (TableModel)table.getModel();
				int rowSelection = table.getSelectedRow();
				int rowCount = table.getRowCount();
				int columnCount = table.getColumnCount();
				
				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
					

				if(actionCommand == "Destination"){
					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
				}
				
				if(actionCommand == "Flight Id"){
					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
				}

				
//				for(int i = 0; i < rowCount; i++){
//					if(model.getValueAt(i, 0) == currentText) {
//						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
//					}
//				}
			}			
		}
		@Override
		public void insertUpdate(DocumentEvent de) {
			String currentText = searchFilter.getText();
			
			if(currentText.trim().length() == 0){
				rowSorter.setRowFilter(null);
			}
			else{
				
				TableModel model = (TableModel)table.getModel();
				int rowSelection = table.getSelectedRow();
				int rowCount = table.getRowCount();
				int columnCount = table.getColumnCount();
				
				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
					

				if(actionCommand == "Destination"){
					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
				}
				
				if(actionCommand == "Flight Id"){
					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
				}
				
//				for(int i = 0; i < rowCount; i++){
//					if(model.getValueAt(i, 0) == currentText) {
//						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
//					}
//				}
//				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
			}			
		}
		@Override
		public void removeUpdate(DocumentEvent de) {
			String currentText = searchFilter.getText();
			
			if(currentText.trim().length() == 0){
				rowSorter.setRowFilter(null);
			}
			else{
				
				TableModel model = (TableModel)table.getModel();
				int rowSelection = table.getSelectedRow();
				int rowCount = table.getRowCount();
				int columnCount = table.getColumnCount();
				
				RowFilter rowFilterD = RowFilter.regexFilter(currentText,6);
				RowFilter rowFilterF = RowFilter.regexFilter(currentText,0);
					

				if(actionCommand == "Destination"){
					rowSorter.setRowFilter(rowFilterD.regexFilter("(?i)" + currentText));
				}
				
				if(actionCommand == "Flight Id"){
					rowSorter.setRowFilter(rowFilterF.regexFilter("(?i)" + currentText));
				}
				
//				for(int i = 0; i < rowCount; i++){
//					if(model.getValueAt(i, 0) ==) {
//						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
//					}
//				}
//				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentText));
			}			
		}    	   
       });
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);

		
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
		
			
		

//		if("createViewFlightsTable".equals(evt.getActionCommand()))
//		{
//        Object[][] data = dc.getViewFlightTable();
//        
//        JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
//        table.setPreferredScrollableViewportSize(new Dimension(600, 150));
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
//		}

	}
//	private static class ButtonRenderer implements TableCellRenderer {
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
//			JButton button = (JButton)value;
//			if (selected) {
//				button.setForeground(table.getForeground());
//				button.setBackground(table.getSelectionBackground());
//			}
//			else{
//				button.setForeground(table.getForeground());
//				button.setBackground(UIManager.getColor("Button.background"));
//			}
//			return button;
//		}
//	}
	
//	private static class MouseListener extends MouseAdapter{
//		private final JTable table;
//		
//		public MouseListener(JTable table){
//			this.table = table;
//		}
//		
//		public void Click(MouseEvent e){
//			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
//			int row = e.getY()/table.getRowHeight();
//			
//			if(row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
//				Object value = table.getValueAt(row, column);
//				if(value instanceof JButton){
//					((JButton)value).doClick();
//				}
//			}
//		}
//	}
	
}
	
