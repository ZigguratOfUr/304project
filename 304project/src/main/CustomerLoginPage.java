package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CustomerLoginPage extends Page implements ActionListener
{
	JScrollPane scrollPane;
	JPanel customerLoginPage;
	JPasswordField p1;
	JTextField t1;
	JLabel w1, w2;
	
	public CustomerLoginPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		customerLoginPage = new JPanel();
		customerLoginPage.setLayout(null);
		customerLoginPage.setPreferredSize(new Dimension(800,800));

	}

	@Override
	public void createPage()
	{
		JButton b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoStartPage");
        b1.addActionListener(mainComponent);
        b1.setBounds(20, 20, 80, 25);

        customerLoginPage.add(b1);
        
//        JButton b2 = new JButton("Go to example page");
//        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
//        b2.setHorizontalTextPosition(AbstractButton.CENTER);
//        b2.setActionCommand("gotoExamplePage");
//        b2.addActionListener(mainComponent);
//        b2.setBounds(550, 20, 200, 25);
//
//        customerLoginPage.add(b2);
        
        JLabel m1 = new JLabel();
        m1.setText("LOGIN");
        m1.setFont(new Font(m1.getFont().getFontName(), 0, 32));
        m1.setForeground(Color.BLUE);
        m1.setBounds(300, 125, 200, 60);
        customerLoginPage.add(m1);
        
        JLabel l1 = new JLabel();
        l1.setText("Enter username:");
        l1.setFont(new Font(l1.getFont().getFontName(), 0, 20));
        l1.setBounds(250, 200, 200, 60);
        customerLoginPage.add(l1);
        
        t1 = new JTextField(10);
        t1.setBounds(250, 250, 240, 30);
        customerLoginPage.add(t1);
        
        w1 = new JLabel();
        w1.setForeground(Color.RED);
        w1.setFont(new Font(l1.getFont().getFontName(), 0, 10));
        w1.setBounds(250, 270, 160, 30);
        customerLoginPage.add(w1);
        
        JLabel l2 = new JLabel();
        l2.setText("Enter password:");
        l2.setFont(new Font(l1.getFont().getFontName(), 0, 20));
        l2.setBounds(250, 300, 200, 60);
        customerLoginPage.add(l2);
        
        p1 = new JPasswordField(10);
        p1.setActionCommand("tryLogin");
        p1.addActionListener(this);
        p1.setBounds(250, 350, 240, 30);
        customerLoginPage.add(p1);

        w2 = new JLabel();
        w2.setForeground(Color.RED);
        w2.setFont(new Font(l1.getFont().getFontName(), 0, 10));
        w2.setBounds(250, 370, 160, 30);
        customerLoginPage.add(w2);
        
        JButton b4 = new JButton("Login");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("tryLogin");
        b4.addActionListener(this);
        b4.setBounds(250, 430, 120, 40);
        customerLoginPage.add(b4);

        JLabel l3 = new JLabel();
        l3.setText("No account? Register here.");
        l3.setFont(new Font(l1.getFont().getFontName(), 0, 10));
        l3.setForeground(Color.DARK_GRAY);
        l3.setBounds(600, 600, 200, 60);
        customerLoginPage.add(l3);
        
        JButton b5 = new JButton("Create Account");
        b5.setVerticalTextPosition(AbstractButton.BOTTOM);
        b5.setHorizontalTextPosition(AbstractButton.CENTER);
        b5.setActionCommand("gotoCreateAccountPage");
        b5.addActionListener(mainComponent);
        b5.setBounds(600, 650, 160, 40);
        customerLoginPage.add(b5);
        
        mainComponent.add(customerLoginPage);
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(customerLoginPage);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if ("tryLogin".equals(evt.getActionCommand()))
    	{
			int result = -1;
			
			try
			{
				result = dc.passengerExists(t1.getText(), new String(p1.getPassword()));
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			if(result != -2)
			{
				mainComponent.loginId = result;
				mainComponent.actionPerformed(new ActionEvent(this, 0, "gotoCustomerMainPage"));
			}
			else
			{
				t1.setText("");
				p1.setText("");
				
				if (result == -1)
				{
					w2.setText("");
					w1.setText("No such user.");
				}
				else if (result == -2)
				{
					w1.setText("");
					w2.setText("Incorrect password.");
				}
			}
    	}
		
	}

}
