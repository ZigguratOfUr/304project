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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HiringPage extends Page implements ActionListener {

	private JButton btn_back, btn_submit;
	private JLabel JL_id, JL_name, JL_phone, JL_airline;
	private JTextField JT_id, JT_name, JT_phone, JT_airline;
	private JComboBox JDD_airline;
	
	public HiringPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
		mainComponent.setLayout(new GridLayout(5,2));
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if("hirePersonnel".equals(evt.getActionCommand())) {
			// Hire frame logic, makes call to dc.hirePersonnel()
			String id = JT_id.getText();
			String name = JT_name.getText();
			String phone = JT_phone.getText();
			long airline = Long.parseLong(JDD_airline.getSelectedItem().toString());
			Calendar currentTime = Calendar.getInstance();
			Date hireDate = new Date((currentTime.getTime()).getTime());
			
			/* Debugging prints //////////////////////////
			System.out.println("The converted date is: " + hireDate);
			System.out.println("id is: " + id);
			System.out.println("name is: " + name);
			System.out.println("phone is: " + phone);
			System.out.println("airline is: " + airline);
			*/
			
			if("".equals(id) || "".equals(name) || "".equals(phone)) {
				JOptionPane.showMessageDialog(mainComponent, "Blank input(s) detected.", "FIELDS MISSING", JOptionPane.ERROR_MESSAGE);
			} else { 
				try { 
					dc.hirePersonnel(mainComponent, Long.parseLong(id), name,
									Long.parseLong(phone), hireDate, airline);
				} catch (NumberFormatException ext) {
					String newLine = System.getProperty("line.separator");
					JOptionPane.showMessageDialog(mainComponent, "Error: All inputs must obey the following formats:" + newLine + newLine +
							"Id, Phone must be numbers;" + newLine + "Name must be alphabetical;", "INVALID INPUT", JOptionPane.ERROR_MESSAGE);
					//ext.printStackTrace();
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(mainComponent, "SQL error", "Encountered an SQL error while trying to delete", JOptionPane.ERROR_MESSAGE);
					s.printStackTrace();
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
    	JL_airline = new JLabel("Airline Id: ");
    	
    	// Make Textfields
    	JT_id = new JTextField(20);
    	JT_name = new JTextField(20);
    	JT_phone = new JTextField(20);
    	
    	// Make Drop down Box
		String [] airlineIDs = {"1", "2", "3", "4", "5"};
		JDD_airline = new JComboBox<String>(airlineIDs);
    	
    	// Add page elements
      	mainComponent.add(btn_submit);
      	mainComponent.add(JL_id);
      	mainComponent.add(JT_id);
      	mainComponent.add(JL_name);
      	mainComponent.add(JT_name);
      	mainComponent.add(JL_phone);
      	mainComponent.add(JT_phone);
      	mainComponent.add(JL_airline);
      	mainComponent.add(JDD_airline);

	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(btn_back);
		mainComponent.remove(btn_submit);
     	mainComponent.remove(JL_id);
      	mainComponent.remove(JL_name);
      	mainComponent.remove(JL_phone);
      	mainComponent.remove(JL_airline);
      	mainComponent.remove(JT_id);
      	mainComponent.remove(JT_name);
      	mainComponent.remove(JT_phone);
      	mainComponent.remove(JDD_airline);
      	mainComponent.setLayout(new FlowLayout());
	}

}
