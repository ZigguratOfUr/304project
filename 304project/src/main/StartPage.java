package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPage extends Page implements ActionListener
{

	JPanel startPage;

	public StartPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		startPage = new JPanel();
		startPage.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 350));
	}

	@Override
	public void createPage()
	{
		
    	JButton b1 = new JButton("Admin Login");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoAdminLoginPage");
        b1.addActionListener(mainComponent);
        b1.setPreferredSize(new Dimension(200,70));

        startPage.add(b1, BorderLayout.WEST);
        
        JButton b2 = new JButton("Customer Login");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoCustomerLoginPage");
        b2.addActionListener(mainComponent);
        b2.setPreferredSize(new Dimension(200,70));

        startPage.add(b2, BorderLayout.EAST);
        
        mainComponent.add(startPage);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(startPage);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
	}

}
