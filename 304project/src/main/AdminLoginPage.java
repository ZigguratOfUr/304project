package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class AdminLoginPage extends Page implements ActionListener
{
	JButton b1;
	
	public AdminLoginPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}

	@Override
	public void createPage()
	{
    	b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoStartPage");
        b1.addActionListener(mainComponent);

        mainComponent.add(b1);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}

}