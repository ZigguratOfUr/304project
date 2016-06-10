package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class StartPage extends Page implements ActionListener
{
	JButton b1, b2;
	
	public StartPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}

	@Override
	public void createPage()
	{
    	b1 = new JButton("Admin Login");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoAdminLoginPage");
        b1.addActionListener(mainComponent);

        mainComponent.add(b1);
        
        b2 = new JButton("Customer Login");
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
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}

}
