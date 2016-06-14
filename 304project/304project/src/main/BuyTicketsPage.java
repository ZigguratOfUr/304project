package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class BuyTicketsPage extends Page implements ActionListener, ListSelectionListener {
	
	JButton b1, b2, b3;
	JScrollPane scrollPane;
	
	Object[][] data = dc.getScheduledFlightTable();
	JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
	
	int rowSelection;
//	Object selectedPurchase;
	int sPurchase;
	
	
	


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
//			int model = table.
			
			
			 rowSelection = table.getSelectedRow();
			 
			 int rowCount = table.getRowCount();
			 int columnCount = table.getColumnCount();
			 
			 //the flight id is here
//			 selectedPurchase = model.getValueAt(rowSelection, 0);
			 
			 
			 
			sPurchase = Integer.parseInt(model.getValueAt(rowSelection,0).toString());
			
			System.out.println("Value selected = " + rowSelection );
			System.out.println(sPurchase);
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
										  "Are you sure you want to purchase flight:" + " " + sPurchase,
										  "Confirmation",
										  JOptionPane.YES_NO_OPTION,
										  JOptionPane.QUESTION_MESSAGE);
			if( result == JOptionPane.YES_OPTION ){
				String [] options = { "economy", "business", "first", "super-first"};
				String [] seats = {"window", "normal", "pilot's seat"};
				
				//the ticket ID
				int tID = 0;
				
				try{
				tID = dc.getTicketID();
				}
				catch(SQLException s){
					System.out.println("Got an SQL error");
					s.printStackTrace();
				}
				
				int choice = JOptionPane.showOptionDialog(null,
														  "Choose your desired seating class",
														  "Class Seating",
														  JOptionPane.YES_NO_CANCEL_OPTION,
														  JOptionPane.DEFAULT_OPTION,
														  null,
														  options,
														  options[3]);
				
				//the resulting seating class choice
				String option = options[choice];
				
				
//				String seatType = seats[choice];
				
				int seatChoice = JOptionPane.showOptionDialog(null,
															 "Choose your desired seat type",
															 "Seat type",
															 JOptionPane.YES_NO_CANCEL_OPTION,
															 JOptionPane.DEFAULT_OPTION,
															 null,
															 seats,
															 seats[2]);
				// the resulting seat choice
				String seat = seats[seatChoice];
				
				
				String input = JOptionPane.showInputDialog(null,
														   "Enter passenger ID:",
														   "Passenger ID",
														   JOptionPane.DEFAULT_OPTION);
				
				// the temporary passenger input id
				int numInput;
				boolean testInput = false; 
				numInput = Integer.valueOf(input);				
			
				
				try{
				testInput = dc.checkPassengerID(numInput);
				}
				catch(SQLException s){
					System.out.println("Got an SQL error");
					s.printStackTrace();
				}
				System.out.println(testInput);
				
				if(testInput == true){
					
					try {
				dc.buyTicket(tID,option,seat,numInput,sPurchase);
					} catch(SQLException s) {
						System.out.println("Got an SQL error");
						s.printStackTrace();
						
					}

				JOptionPane.showMessageDialog(null,
											   "The purchase of ticket for flight" + " " + ":" + " " + sPurchase + " has been successful",
											   "Confirmation",
											   JOptionPane.INFORMATION_MESSAGE);
					
				}
				if(testInput == false){
					JOptionPane.showMessageDialog(null,
											   	  "ERROR, That Passenger ID does not exist",
											   	  "Whoops!",
											   	  JOptionPane.ERROR_MESSAGE);
											
				}
				testInput = false;
				
				

				
														  
														  
														  
//				try {
//					// put the flight ID into this
//			dc.buyTicket(100,option,seat,numInput,sPurchase);
//				} catch(SQLException s) {
//					System.out.println("Got an SQL error");
//					s.printStackTrace();
//					
//				}
////				System.out.println(option);
////				System.out.println(seat);
////				System.out.println(input);
//			JOptionPane.showMessageDialog(null,
//										   "The purchase of ticket for flight" + " " + ":" + " " + sPurchase + " has been successful",
//										   "Confirmation",
//										   JOptionPane.INFORMATION_MESSAGE);
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