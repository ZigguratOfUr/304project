package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminMainPage extends Page implements ActionListener
{
	public AdminMainPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
		// TODO Auto-generated constructor stub
	}

	JButton b1, b2, b3 ,b4;
	JScrollPane scrollPane;


	@Override
	public void createPage()
	{
		b1 = new JButton("Personnel");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoPersonnelPage");
        b1.addActionListener(mainComponent);

        mainComponent.add(b1);
        
        b2 = new JButton("Flights");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoFlightsPage");
        b2.addActionListener(mainComponent);
        
        mainComponent.add(b2);
        
        b3 = new JButton("Planes");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("gotoPlanesPage");
        b3.addActionListener(mainComponent);
        
        mainComponent.add(b3);
        
        b4 = new JButton("Back");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("gotoCustomerLoginPage");
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
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}

}
