package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlanesPage extends Page implements ActionListener {

	JButton b1;
	
	public PlanesPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPage() {
		// TODO Auto-generated method stub
		b1 = createButton(b1, "Back", "gotoAdminPage");
	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(b1);
	}

}
