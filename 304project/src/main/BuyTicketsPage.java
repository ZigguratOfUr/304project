package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.TableModel;
import javax.swing.JOptionPane;


public class BuyTicketsPage extends Page implements ActionListener, ListSelectionListener {
	
	JButton b1, b2;
	JScrollPane scrollPane;
	
	Object[][] data = dc.getScheduledFlightTable();
	JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
	
	int rowSelection;
	Object selectedPurchase;


	public BuyTicketsPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}
	
	@Override
	public void createPage()
	{
        
        b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoViewFlightsPage");
        b1.addActionListener(mainComponent);

        mainComponent.add(b1);
        
        b2 = new JButton("Purchase Selected");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoMadePurchase");
        b2.addActionListener(this);
        
        mainComponent.add(b2);
        
        
        
        
//        Object[][] data = dc.getScheduledFlightTable();
        
        // Create a new table instance
//        JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        
        
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
	}
	
	public void valueChanged( ListSelectionEvent event){
		
		if(event.getSource() == table.getSelectionModel() && event.getFirstIndex() >= 0){
			TableModel model = (TableModel)table.getModel();
			
			 rowSelection = table.getSelectedRow();
			 
			 int rowCount = table.getRowCount();
			 int columnCount = table.getColumnCount();
			 
			 selectedPurchase = model.getValueAt(rowSelection, 0);
			
//			System.out.println("Value selected = " + rowSelection );
//			System.out.println(selectedPurchase);
		}
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
		if("gotoMadePurchase".equals(evt.getActionCommand())){
			if(table.getSelectedRow() > -1){
			int result = JOptionPane.showConfirmDialog(null,
										  "Are you sure you want to purchase flight:" + " " + selectedPurchase,
										  "Confirmation",
										  JOptionPane.YES_NO_OPTION,
										  JOptionPane.QUESTION_MESSAGE);
			if( result == JOptionPane.YES_OPTION ){
			JOptionPane.showMessageDialog(null,
										   "The purchase of ticket for flight" + " " + ":" + " " + selectedPurchase + " has been successful",
										   "Confirmation",
										   JOptionPane.INFORMATION_MESSAGE);
			}
			}
		else{
			JOptionPane.showMessageDialog(null,
										  "No flight selected yet",
										  "Error",
										  JOptionPane.ERROR_MESSAGE);
			}
		}		       

	    	
	}

}
