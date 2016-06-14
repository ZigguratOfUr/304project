package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FiringPage extends Page implements ActionListener {

	private JButton b1, b2;
	private int pid;
	private JLabel JL_input;
	private JTextField JT_input;
	private JScrollPane scrollPane;
	
	public FiringPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("firePersonnel".equals(e.getActionCommand()))
    	{
			String i = JT_input.getText();
			if("".equals(i)) {
				JOptionPane.showMessageDialog(mainComponent, "Input is empty!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			} else { 
			
			try { 
				pid = Integer.parseInt(JT_input.getText());
				dc.firePersonnel(mainComponent, pid);
				drawPersonnelTable();
			} catch (NumberFormatException ext) {
				JOptionPane.showMessageDialog(mainComponent, "Input is not a number!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException s) {
				JOptionPane.showMessageDialog(mainComponent, "SQL error", "Encountered an SQL error while trying to delete", JOptionPane.ERROR_MESSAGE);
				}
			} 
    	}
	}

	@Override
	public void createPage() {
		// Create buttons, textfields, labels
		b1 = createButton(b1, "Back", "gotoPersonnelPage");
		
		JL_input = new JLabel("Input id: ");
		JL_input.setBounds(20, 40, 60, 20);
		JT_input = new JTextField(20);
		
		
		// Fire personnel
    	b2 = new JButton("Fire Personnel");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("firePersonnel");
        b2.addActionListener(this);

        mainComponent.add(b1);
        mainComponent.add(b2);
        mainComponent.add(JL_input);
        mainComponent.add(JT_input);
 
        // Add personnel table for user reference
        drawPersonnelTable();
		
	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(b1);
		mainComponent.remove(b2);
        mainComponent.remove(JL_input);
        mainComponent.remove(JT_input);
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}
	}
	
	private void drawPersonnelTable() {
	        Object [][] personnel_data = dc.getPersonnelTable();
			JTable table = new JTable(personnel_data, DatabaseConnecter.PERSONNEL_TABLE_COLUMN_NAMES);
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
