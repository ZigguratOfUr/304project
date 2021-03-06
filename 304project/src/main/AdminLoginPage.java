package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class AdminLoginPage extends Page implements ActionListener
{
	JPanel adminLoginPage;
	JPasswordField p1;
	JLabel l2;
	private static final String PASSWORD = "Lagomorpha";
	
	public AdminLoginPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		adminLoginPage = new JPanel();
		adminLoginPage.setLayout(new BorderLayout(20, 20));
		adminLoginPage.setPreferredSize(new Dimension(800,800));
	}

	@Override
	public void createPage()
	{
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
    	JButton b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoStartPage");
        b1.addActionListener(mainComponent);

        topLeft.add(b1);
        
        adminLoginPage.add(topLeft, BorderLayout.NORTH);


        JPanel centre = new JPanel();
        centre.setLayout(null);
		
        JLabel m1 = new JLabel();
        m1.setText("LOGIN");
        m1.setFont(new Font(m1.getFont().getFontName(), 0, 32));
        m1.setForeground(Color.BLUE);
        m1.setBounds(300, 75, 200, 60);
        centre.add(m1);
        
        JLabel l1 = new JLabel();
        l1.setText("Enter Password:");
        l1.setFont(new Font(l1.getFont().getFontName(), 0, 20));
        l1.setBounds(250, 200, 200, 60);
        centre.add(l1);
        
        p1 = new JPasswordField(10);
        p1.setActionCommand("tryLogin");
        p1.addActionListener(this);
        p1.setBounds(250, 300, 240, 30);

        centre.add(p1);

        l2 = new JLabel();
        l2.setForeground(Color.RED);
        l2.setFont(new Font(l1.getFont().getFontName(), 0, 10));
        l2.setBounds(250, 320, 120, 30);
        
        centre.add(l2);
        
        JButton b3 = new JButton("Login");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("tryLogin");
        b3.addActionListener(this);
        b3.setBounds(250, 400, 120, 40);

        centre.add(b3);

        adminLoginPage.add(centre, BorderLayout.CENTER);
        
		mainComponent.add(adminLoginPage);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(adminLoginPage);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if ("tryLogin".equals(evt.getActionCommand()))
    	{
			if(PASSWORD.equals(new String(p1.getPassword())))
			{
				mainComponent.actionPerformed(new ActionEvent(this, 0, "gotoAdminPage"));
				
			}
			else
			{
				l2.setText("Incorrect Password.");
				p1.setText("");
			}
    	}
	}
	

}
