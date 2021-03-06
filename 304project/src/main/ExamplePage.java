package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExamplePage extends Page implements ActionListener
{
	JButton b1, b2, b3;
	JScrollPane scrollPane;
	
	public ExamplePage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}

	@Override
	public void createPage()
	{
    	b1 = new JButton("Test table creation");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("createPersonnelTable");
        b1.addActionListener(this);

        mainComponent.add(b1);
        
        b2 = new JButton("Back");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoCustomerLoginPage");
        b2.addActionListener(mainComponent);

        mainComponent.add(b2);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(b3);
		
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if ("createPersonnelTable".equals(evt.getActionCommand()))
    	{
    		Object[][] data = dc.getAvailableFlightsTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.FLIGHT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(500, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(scrollPane);
    		mainComponent.revalidate();
    		mainComponent.repaint();
    	}
		
		
		
	}

}
