package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StatPage extends Page implements ActionListener
{
	private final String [] OP =  {"MIN", "MAX", "AVG"};
	
	JPanel statPane, airmilePane, planePane, personnelPane, allFlights;
	JComboBox ops;
	JLabel total;
	
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
		statPane = new JPanel();
		statPane.setLayout(null);
		statPane.setPreferredSize(new Dimension(800,800));
	}

	@Override
	public void createPage()
	{
    	JButton b1 = new JButton("Personnel");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("createTables");
        b1.addActionListener(this);
        b1.setBounds(125, 50, 130, 20);
        statPane.add(b1);
        
        JButton b2 = new JButton("AirMiles");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("airMileStats");
        b2.addActionListener(this);
        b2.setBounds(275, 50, 130,20);

        statPane.add(b2);
        
        JButton b3 = new JButton("Planes");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("planeStats");
        b3.addActionListener(this);
        b3.setBounds(425, 50, 130,20);

        statPane.add(b3);
        
        JButton b4 = new JButton("Loyal Passengers");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("AllFlights");
        b4.addActionListener(this);
        b4.setBounds(575, 50, 160,20);

        statPane.add(b4);
        
        JButton b5 = new JButton("Back");
        b5.setVerticalTextPosition(AbstractButton.BOTTOM);
        b5.setHorizontalTextPosition(AbstractButton.CENTER);
        b5.setActionCommand("gotoAdminPage");
        b5.addActionListener(mainComponent);
        b5.setBounds(20, 20, 80, 25);

        statPane.add(b5);
        
        mainComponent.add(statPane);
        
        
	}

	@Override
	public void cleanPage()
	{
		if(airmilePane!= null)
		{
			statPane.remove(airmilePane);
		}
		if(planePane!= null)
		{
			statPane.remove(planePane);
		}
		if(personnelPane!= null)
		{
			statPane.remove(personnelPane);
		}
		if(allFlights!= null)
		{
			statPane.remove(allFlights);
		}
		mainComponent.remove(statPane);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if("UpdateAirMilesTable".equals(evt.getActionCommand()))
		{
			int result = dc.getAirMileStatResult(ops.getSelectedItem().toString());
			total.setText(String.valueOf(result));
			return;
		}
		
		if(airmilePane!= null)
		{
			statPane.remove(airmilePane);
		}
		if(planePane!= null)
		{
			statPane.remove(planePane);
		}
		if(personnelPane!= null)
		{
			statPane.remove(personnelPane);
		}
		if(allFlights!= null)
		{
			statPane.remove(allFlights);
		}
		if ("createTables".equals(evt.getActionCommand()))
    	{
			personnelPane = new JPanel();
			personnelPane.setLayout(null);
			personnelPane.setPreferredSize(new Dimension(800,800));
			personnelPane.setBounds(0, 80, 800, 720);

			// Create Personnel Table
    		Object[][] data = dc.getPersonnelTotalTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table.setFillsViewportHeight(true);
    		
    		JLabel personnel = new JLabel("Total Personnel");
    		personnel.setBounds(340, 20, 120, 40);
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		scrollPane.setBounds(25, 60, 750, 120);
    		//Add the scroll pane to this panel.
            
    		personnelPane.add(personnel);
    		personnelPane.add(scrollPane, BorderLayout.NORTH);
    		
    		// Create Flight Attendant Table
    		Object[][] data2 = dc.getFATotalTable();
    		
    		JTable table2 = new JTable(data2, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table2.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table2.setFillsViewportHeight(true);
    		
    		JLabel fa = new JLabel("Total Flight Attendants");
    		fa.setBounds(320, 200, 200, 40);

    		//Create the scroll pane and add the table to it.
    		scrollPane2 = new JScrollPane(table2);
    		scrollPane2.setBounds(25, 240, 750, 120);

    		//Add the scroll pane to this panel.
            
    		personnelPane.add(fa);
    		personnelPane.add(scrollPane2, BorderLayout.CENTER);
    		
    		
    		// Create Pilot Table
    		Object[][] data3 = dc.getPilotTotalTable();
    		
    		JTable table3 = new JTable(data3, DatabaseConnecter.PERSONNEL_STAT_TABLE_COLUMN_NAMES);
    		table3.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table3.setFillsViewportHeight(true);
    		
    		JLabel pilot = new JLabel("Total Pilots");
    		pilot.setBounds(350, 380, 120, 40);

    		//Create the scroll pane and add the table to it.
    		scrollPane3 = new JScrollPane(table3);
    		//Add the scroll pane to this panel.
    		scrollPane3.setBounds(25, 420, 750, 120);

    		personnelPane.add(pilot);
    		personnelPane.add(scrollPane3, BorderLayout.SOUTH);
    		statPane.add(personnelPane);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		else if ("airMileStats".equals(evt.getActionCommand()))
    	{
			airmilePane = new JPanel();
			airmilePane.setLayout(null);
			airmilePane.setPreferredSize(new Dimension(800,800));
			airmilePane.setBounds(0, 80, 800, 720);
			
			ops = new JComboBox(OP);
			ops.addActionListener(this);
			ops.setSelectedIndex(2);
			ops.setActionCommand("UpdateAirMilesTable");
			ops.setBounds(140, 60, 60, 25);
			int result = dc.getAirMileStatResult(ops.getSelectedItem().toString());
			// Create Personnel Table
//    		Object[][] data = dc.getairMileTable();
//    		
//    		JTable table = new JTable(data, DatabaseConnecter.AIRMILES_STAT_TABLE_COLUMN_NAMES);
//    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
//    		table.setFillsViewportHeight(true);
//    		
//    		if (scrollPane!= null)
//    		{
//    			mainComponent.remove(scrollPane);
//    		}
    		
    		JLabel air = new JLabel("Air Miles per customer =");
    		air.setBounds(200, 60, 240, 25);

    		total = new JLabel(String.valueOf(result));
    		total.setBounds(440, 60, 50, 25);

    		//Create the scroll pane and add the table to it.
//    		scrollPane = new JScrollPane(table);
//    		scrollPane.setBounds(25, 100, 750, 120);

    		//Add the scroll pane to this panel.
            
    		airmilePane.add(air);
    		airmilePane.add(ops);
    		airmilePane.add(total);

//    		airmilePane.add(scrollPane, BorderLayout.NORTH);
    		
    		statPane.add(airmilePane);

    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		else if ("planeStats".equals(evt.getActionCommand()))
    	{
			planePane = new JPanel();
			planePane.setLayout(null);
			planePane.setPreferredSize(new Dimension(800,800));
			planePane.setBounds(0, 80, 800, 720);
			
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
    		air.setBounds(350, 60, 120, 40);

    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		scrollPane.setBounds(25, 100, 750, 120);

    		//Add the scroll pane to this panel.
            
    		planePane.add(air);
    		planePane.add(scrollPane, BorderLayout.NORTH);
    		
    		statPane.add(planePane);
    		
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		else if("AllFlights".equals(evt.getActionCommand())){
			allFlights = new JPanel();
			allFlights.setLayout(null);
			allFlights.setPreferredSize(new Dimension(800,800));
			allFlights.setBounds(0, 80, 800, 720);
			
			// Create Personnel Table
    		Object[][] data = dc.beenOnallFlightsTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.PASSENGER_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(750, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		
    		JLabel air = new JLabel("Passenger That Has Been on Every Flight");
    		air.setBounds(250, 60, 300, 40);

    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		scrollPane.setBounds(25, 100, 750, 120);

    		//Add the scroll pane to this panel.
            
    		allFlights.add(air);
    		allFlights.add(scrollPane, BorderLayout.NORTH);
    		
    		statPane.add(allFlights);
    		
    		mainComponent.revalidate();
    		mainComponent.repaint();
		}
		
		
		
	}

}
