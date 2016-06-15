package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage extends Page implements ActionListener
{

	JPanel startPage;

	public StartPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		startPage = new JPanel();
		startPage.setLayout(null);
		startPage.setPreferredSize(new Dimension(800,800));
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
        b1.setBounds(150, 400, 200, 70);

        startPage.add(b1);
        
        JButton b2 = new JButton("Customer Login");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoCustomerLoginPage");
        b2.addActionListener(mainComponent);
        b2.setPreferredSize(new Dimension(200,70));
        b2.setBounds(450, 400, 200, 70);

        startPage.add(b2);
        
		JLabel l1 = new JLabel(new ImageIcon("./images/LAAGlogo.png"));
		l1.setBounds(60,0, 680,289);
		startPage.add(l1);
		
		JLabel l2 = new JLabel(new ImageIcon("./images/LAAGbunny.png"));
		l2.setBounds(188,160, 414,538);
		startPage.add(l2);
		
        mainComponent.add(startPage);
        

        mainComponent.revalidate();
        mainComponent.repaint();
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
