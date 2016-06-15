package main;

import java.awt.event.*;

import javax.swing.*;

public class GUIMain extends JPanel implements ActionListener 
{
	private static DatabaseConnecter dc;
	static JFrame frame;
	Page currentPage;
	int loginId;
	
    public GUIMain()
    {
    	loginId = -1;
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
    	/*
    	else if ("gotoPersonnelPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new PersonnelPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoFlightslPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new FlightsPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoPlanesPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new PlanesPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	*/
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
    	else if ("gotoCustomerMainPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new CustomerMainPage(this, dc);
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
    	else if ("gotoFlightsPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new FlightsPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoAdminPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new AdminPage(this, dc);
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
    	else if ("gotoPersonnelPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new PersonnelPage(this, dc);
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

    	else if ("gotoPlanesPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new PlanesPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	/*
    	else if ("gotoUpdatePersonnelPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new UpdatePersonnelPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}*/
    	else if ("gotoAssignCrewPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new AssignCrewPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	
    	else if ("gotoHiringPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new HiringPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoFiringPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new FiringPage(this, dc);

    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoCreateAccountPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new CreateAccountPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	else if ("gotoStatPage".equals(evt.getActionCommand()))
    	{
    		currentPage.cleanPage();
    		currentPage = new StatPage(this, dc);
    		currentPage.createPage();
    		revalidate();
    		repaint();
    	}
    	
    }

    
	public static void createAndShowGUI(DatabaseConnecter dbc)
	{
		dc = dbc;
		//Create and set up the window.
        frame = new JFrame("Airport Group Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add contents to the window.
        frame.add(new GUIMain());
 
        //Display the window.
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setVisible(true);
		
	}
	
}
