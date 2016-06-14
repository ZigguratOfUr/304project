package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class AdminLoginPage extends Page implements ActionListener
{
	JButton b1, b2;
	public AdminLoginPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}

	@Override
	public void createPage()
	{
    	b1 = createButton(b1, "Back", "gotoStartPage");
    	b2 = createButton(b2, "Admin Page", "gotoAdminPage");
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);;
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}
	

}
