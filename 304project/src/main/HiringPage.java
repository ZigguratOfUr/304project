package main;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HiringPage extends Page implements ActionListener {

	private JButton btn_back, btn_submit;
	private JLabel JL_id, JL_name, JL_phone, JL_hireDate, JL_airline;
	private JTextField JT_id, JT_name, JT_phone, JT_hireDate, JT_airline;
	
	public HiringPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
		mainComponent.setLayout(new GridLayout(6,2));
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if("hirePersonnel".equals(evt.getActionCommand())) {
			// Hire frame logic, makes call to dc.hirePersonnel()
			String id = JT_id.getText();
			String name = JT_name.getText();
			String phone = JT_phone.getText();
			//String hireDate = JT_hireDate.getText();
			String airline = JT_airline.getText();
			Calendar currentTime = Calendar.getInstance();
			Date hireDate = new Date((currentTime.getTime()).getTime());
			
			
			if("".equals(id) || "".equals(name) || "".equals(phone) || "".equals(airline)) {
				JOptionPane.showMessageDialog(mainComponent, "Blank input(s) detected.", "FIELDS MISSING", JOptionPane.ERROR_MESSAGE);
			} else { 
			
				int err = -1;  // Inputs 2,4,5 may throw NumberFormatException need to keep track of culprit
				try { 
					dc.hirePersonnel(mainComponent, Integer.parseInt(JT_id.getText()), JT_name.getText(),
									Integer.parseInt(JT_phone.getText()), hireDate, Integer.parseInt(JT_airline.getText()));
				} catch (NumberFormatException ext) {
					switch(err) {
					case(2) : JOptionPane.showMessageDialog(mainComponent, "Error: Id must be in integer format", "INVALID INPUT", JOptionPane.ERROR_MESSAGE);
					break;
					case(4) : JOptionPane.showMessageDialog(mainComponent, "Error: Phone must be in integer format", "INVALID INPUT", JOptionPane.ERROR_MESSAGE);
					break;
					case(6) : JOptionPane.showMessageDialog(mainComponent, "Error: Airling must be in integer format", "INVALID INPUT", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(mainComponent, "SQL error", "Encountered an SQL error while trying to delete", JOptionPane.ERROR_MESSAGE);
				}
			} 
			
		}

	}

	@Override
	public void createPage() {
		// Make Buttons
		btn_back = createButton(btn_back, "Back", "gotoPersonnelPage");
    	btn_submit = createButton(btn_submit, "Submit", "hirePersonnel");
    	btn_submit.setVerticalTextPosition(AbstractButton.BOTTOM);
    	btn_submit.setHorizontalTextPosition(AbstractButton.CENTER);
    	btn_submit.addActionListener(this);
    	
    	// Make Labels
    	JL_id = new JLabel("Id: ");
    	JL_name = new JLabel("Full Name: ");
    	JL_phone = new JLabel("Phone#: ");
    	JL_hireDate = new JLabel("Today's Date: ");
    	JL_airline = new JLabel("Airline Id: ");
    	
    	// Make Textfields
    	JT_id = new JTextField(20);
    	JT_name = new JTextField(20);
    	JT_phone = new JTextField(20);
    	JT_hireDate = new JTextField(20);
    	JT_airline = new JTextField(20);
    	
    	// Add page elements
      	mainComponent.add(btn_submit);
      	mainComponent.add(JL_id);
      	mainComponent.add(JT_id);
      	mainComponent.add(JL_name);
      	mainComponent.add(JT_name);
      	mainComponent.add(JL_phone);
      	mainComponent.add(JT_phone);
      	mainComponent.add(JL_hireDate);
      	mainComponent.add(JT_hireDate);
      	mainComponent.add(JL_airline);
      	mainComponent.add(JT_airline);

	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(btn_back);
		mainComponent.remove(btn_submit);
     	mainComponent.remove(JL_id);
      	mainComponent.remove(JL_name);
      	mainComponent.remove(JL_phone);
      	mainComponent.remove(JL_hireDate);
      	mainComponent.remove(JL_airline);
      	mainComponent.remove(JT_id);
      	mainComponent.remove(JT_name);
      	mainComponent.remove(JT_phone);
      	mainComponent.remove(JT_hireDate);
      	mainComponent.remove(JT_airline);
      	mainComponent.setLayout(new FlowLayout());
	}

}
