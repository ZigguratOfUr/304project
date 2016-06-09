package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIMain extends JPanel implements ActionListener 
{
	private static DatabaseConnecter dc;
	private static JFrame frame;
	
    public GUIMain()
    {
    	JButton b1 = new JButton("Test table creation");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("create");
        b1.addActionListener(this);

        add(b1);
        
    }
 
    public void actionPerformed(ActionEvent evt)
    {
    	if ("create".equals(evt.getActionCommand()))
    	{
    		String[] columnNames = {"id",
                    "pname",
                    "phone",
                    "hireDate",
                    "airlineId"};

    		Object[][] data = dc.getPersonnelTable();
    		
    		JTable table = new JTable(data, columnNames);
    		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    		table.setFillsViewportHeight(true);
    		
    		//Create the scroll pane and add the table to it.
    		JScrollPane scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		add(scrollPane);
    		frame.pack();
    	}
    }
    
	public static void createAndShowGUI(DatabaseConnecter dbc)
	{
		dc = dbc;
		//Create and set up the window.
        frame = new JFrame("GUIMain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new GUIMain());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
		
	}
	
}
