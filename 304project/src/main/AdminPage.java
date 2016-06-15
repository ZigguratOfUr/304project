package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AdminPage extends Page implements ActionListener{

	JButton b1, b2, b3, b4,b5;
	
	public AdminPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
	}

	@Override
	public void createPage() {
		// TODO Auto-generated method stub
		b1 = createButton(b1, "Logout", "gotoStartPage");
		b2 = createButton(b2, "Manage Personnel", "gotoPersonnelPage");
		b3 = createButton(b3, "Manage Planes", "gotoPlanesPage");
		b4 = createButton(b4, "Flights", "gotoFlightsPage");
		b5 = createButton(b5, "Stats", "gotoStatPage");
	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(b3);
		mainComponent.remove(b4);
		mainComponent.remove(b5);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
