package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomerMainPage extends Page implements ActionListener
{
	public CustomerMainPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
		// TODO Auto-generated constructor stub
	}

	JButton b1, b2, b3;
	JScrollPane scrollPane;


	@Override
	public void createPage()
	{
		b1 = new JButton("View Flights");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoViewFlightsPage");
        b1.addActionListener(mainComponent);

        mainComponent.add(b1);
        
        b2 = new JButton("Buy Tickets");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoBuyTicketsPage");
        b2.addActionListener(mainComponent);
        
        mainComponent.add(b2);
        
        b3 = new JButton("Back");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("gotoCustomerLoginPage");
        b3.addActionListener(mainComponent);

        mainComponent.add(b3);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(b3);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}

}
