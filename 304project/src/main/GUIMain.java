package main;

import java.awt.event.*;

import javax.swing.*;

public class GUIMain extends JPanel implements ActionListener 
{
	private static DatabaseConnecter dc;
	static JFrame frame;
	Page currentPage;
	
    public GUIMain()
    {
    	currentPage = new StartPage(this, dc);
    	currentPage.createPage();
    }
 
    public void actionPerformed(ActionEvent evt)
    {
    	if ("gotoStartPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new StartPage(this, dc);
    		currentPage.createPage();
            revalidate();
    		repaint();
    	}
    	else if ("gotoAdminLoginPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new AdminLoginPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoCustomerLoginPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new CustomerLoginPage(this, dc);
    		currentPage.createPage();   		
    		revalidate();
    		repaint();
    	}
    	else if ("gotoExamplePage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new ExamplePage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoViewFlightsPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new ViewFlightsPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoBuyTicketsPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new BuyTicketsPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
//    	else if("Destination".equals(evt.getActionCommand())){
//		    System.out.println(evt.getActionCommand());
//		}
//    	else if("Flight Id".equals(evt.getActionCommand())){
//		       System.out.println(evt.getActionCommand());
//		}

    }

    
	public static void createAndShowGUI(DatabaseConnecter dbc)
	{
		dc = dbc;
		//Create and set up the window.
        frame = new JFrame("GUIMain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add contents to the window.
        frame.add(new GUIMain());
 
        //Display the window.
        frame.setSize(800,800);
        frame.setVisible(true);
		
	}
	
}
