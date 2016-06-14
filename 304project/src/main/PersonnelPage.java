package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class PersonnelPage extends Page implements ActionListener {

	JButton b1, b2, b3, b4, b5;
	
	public PersonnelPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {}

	@Override
	public void createPage() {
		// Redirects
    	b1 = createButton(b1, "Back", "gotoAdminPage");
    	b2 = createButton(b2, "Update Personnel", "gotoUpdatePersonnelPage");
    	b3 = createButton(b3, "Assign Crew", "gotoAssignCrewPage");
    	b4 = createButton(b4, "Hire", "gotoHiringPage");
    	b5 = createButton(b5, "Fire", "gotoFiringPage");
    	
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

}
