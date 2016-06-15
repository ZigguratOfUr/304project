package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StatPage extends Page implements ActionListener
{
	JButton b1, b2, b3, b4;
	JScrollPane scrollPane;
	JLabel personnel;
	
	JLabel fa ;
	JScrollPane scrollPane2;
	
	JLabel pilot;
	JScrollPane scrollPane3;
	
	JLabel air;
    

	public StatPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}

	@Override
	public void createPage()
	{
    	b1 = new JButton("Personnel");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("createTables");
        b1.addActionListener(this);

        mainComponent.add(b1);
        
        b2 = new JButton("AirMiles");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("airMileStats");
        b2.addActionListener(this);
        
        mainComponent.add(b2);
        
        b3 = new JButton("Planes");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("planeStats");
        b3.addActionListener(this);
        
        mainComponent.add(b3);
        
        b4 = new JButton("Back");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("gotoAdminPage");
        b4.addActionListener(mainComponent);

        mainComponent.add(b4);
        
        
        
        
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(b3);
		mainComponent.remove(b4);
		mainComponent.remove(personnel);
		mainComponent.remove(fa);
		mainComponent.remove(pilot);
		mainComponent.remove(air);
		
		
		
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}
		if (scrollPane2!= null)
		{
			mainComponent.remove(scrollPane2);
		}
		if (scrollPane3!= null)
		{
			mainComponent.remove(scrollPane3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if ("createTables".equals(evt.getActionCommand()))
    	{
			// Create Personnel Table
    		Object[][] data = dc.getPersonnelTotalTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		
    		JLabel personnel = new JLabel("             Total Personnel");
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(personnel);
    		mainComponent.add(scrollPane, BorderLayout.NORTH);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    		
    		// Create Flight Attendant Table
    		Object[][] data2 = dc.getFATotalTable();
    		
    		JTable table2 = new JTable(data2, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table2.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table2.setFillsViewportHeight(true);
    		
    		if (scrollPane2!= null)
    		{
    			mainComponent.remove(scrollPane2);
    		}
    		
    		JLabel fa = new JLabel("       Total Flight Attendants");
    		//Create the scroll pane and add the table to it.
    		scrollPane2 = new JScrollPane(table2);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(fa);
    		mainComponent.add(scrollPane2, BorderLayout.CENTER);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    		
    		
    		// Create Pilot Table
    		Object[][] data3 = dc.getPilotTotalTable();
    		
    		JTable table3 = new JTable(data3, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table3.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table3.setFillsViewportHeight(true);
    		
    		if (scrollPane3!= null)
    		{
    			mainComponent.remove(scrollPane3);
    		}
    		
    		JLabel pilot = new JLabel("       Total Pilots");
    		//Create the scroll pane and add the table to it.
    		scrollPane3 = new JScrollPane(table3);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(pilot);
    		mainComponent.add(scrollPane3, BorderLayout.SOUTH);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		else if ("airMileStats".equals(evt.getActionCommand()))
    	{
			// Create Personnel Table
    		Object[][] data = dc.getairMileTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.AIRMILES_STAT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		
    		JLabel air = new JLabel("Airmiles");
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(air);
    		mainComponent.add(scrollPane, BorderLayout.NORTH);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		else if ("planeStats".equals(evt.getActionCommand()))
    	{
			// Create Personnel Table
    		Object[][] data = dc.getCountFlightsTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.COUNTFLIGHTS_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		
    		JLabel air = new JLabel("Flights");
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(air);
    		mainComponent.add(scrollPane, BorderLayout.NORTH);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		
		
	}

}
