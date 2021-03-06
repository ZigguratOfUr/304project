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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPage extends Page implements ActionListener
{
	JPanel createAccountPage;

	JPasswordField p1;
	JTextField t0, t1;
	JLabel w1;
	
	public CreateAccountPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		createAccountPage = new JPanel();
		createAccountPage.setLayout(null);
		createAccountPage.setPreferredSize(new Dimension(800,800));
	}

	@Override
	public void createPage()
	{
		JButton b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoCustomerLoginPage");
        b1.addActionListener(mainComponent);
        b1.setBounds(20, 20, 80, 25);
        createAccountPage.add(b1);
        
        JLabel m1 = new JLabel();
        m1.setText("CREATE ACCOUNT");
        m1.setFont(new Font(m1.getFont().getFontName(), 0, 32));
        m1.setForeground(Color.BLUE);
        m1.setBounds(225, 125, 400, 60);
        createAccountPage.add(m1);
        
        JLabel l0 = new JLabel();
        l0.setText("Username:");
        l0.setFont(new Font(l0.getFont().getFontName(), 0, 20));
        l0.setBounds(250, 200, 200, 60);
        createAccountPage.add(l0);
        
        t0 = new JTextField(10);
        t0.setBounds(250, 250, 240, 30);
        createAccountPage.add(t0);
        
        w1 = new JLabel();
        w1.setForeground(Color.RED);
        w1.setFont(new Font(w1.getFont().getFontName(), 0, 10));
        w1.setBounds(250, 270, 200, 30);
        createAccountPage.add(w1);
        
        JLabel l1 = new JLabel();
        l1.setText("Name:");
        l1.setFont(new Font(l1.getFont().getFontName(), 0, 20));
        l1.setBounds(250, 290, 200, 60);
        createAccountPage.add(l1);
        
        t1 = new JTextField(10);
        t1.setBounds(250, 340, 240, 30);
        createAccountPage.add(t1);
        
        JLabel l2 = new JLabel();
        l2.setText("Password:");
        l2.setFont(new Font(l2.getFont().getFontName(), 0, 20));
        l2.setBounds(250, 380, 200, 60);
        createAccountPage.add(l2);
        
        p1 = new JPasswordField(10);
        p1.setActionCommand("createAccount");
        p1.addActionListener(this);
        p1.setBounds(250, 430, 240, 30);
        createAccountPage.add(p1);
        
        JButton b2 = new JButton("Create");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("createAccount");
        b2.addActionListener(this);
        b2.setBounds(250, 470, 120, 40);
        createAccountPage.add(b2);
        
        mainComponent.add(createAccountPage);

	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(createAccountPage);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if ("createAccount".equals(evt.getActionCommand()))
    	{
			try
			{
				int id = dc.createAccount(t0.getText(), t1.getText(), new String(p1.getPassword()));
				
				if (id == -1)
				{
					t0.setText("");
					w1.setText("Username in use.");
				}
				else
				{
					JOptionPane.showMessageDialog(GUIMain.frame, "Account created.");
					mainComponent.actionPerformed(new ActionEvent(this, 0, "gotoCustomerLoginPage"));
				}
				

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
    	}
	}

}
