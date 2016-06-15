package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AssignCrewPage extends Page implements ActionListener {

	// Declare JButtons
	private JButton btn_back, btn_assign;
	
	// Declare JLabels
	private JLabel JL_personnel, JL_flights;
	
	// Declare JDrop downs
	private JComboBox JDD_personnel, JDD_flights;
	private List<Long> pids = new ArrayList<Long>();
	private List<Long> fids = new ArrayList<Long>();
	
	public AssignCrewPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if ("assignCrew".equals(evt.getActionCommand())) {
			// Assign selected personnel to selected flight
			// note: need to check if personnel already assigned to a flight, if
			// yes do not assign
			long pid = Long.parseLong(JDD_personnel.getSelectedItem().toString());
			long fid = Long.parseLong(JDD_flights.getSelectedItem().toString());

			try {
				if (!dc.pAssigned(pid)) {

					if (dc.assignPersonnelToCrew(pid, fid)) {
						JOptionPane.showMessageDialog(mainComponent,
								"Personnel with id= " + pid + " successfully assigned to " + "flight " + fid,
								"CREW ASSIGNMENT SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(mainComponent,
								"Personnel with id= " + pid + " was not successfully assigned to " + "flight " + fid + "due to constraint violation",
								"CREW ASSIGNMENT UNSUCCESSFUL", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					long cFid = dc.getCrewFid(pid);
					JOptionPane.showMessageDialog(mainComponent,
							"Personnel with id = " + pid + " already assigned to " + "flight with id = " + cFid,
							"CREW ASSIGNMENT UNSUCCESSFUL", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	@Override
	public void createPage() {
		// Make buttons
		btn_back = createButton(btn_back, "Back", "gotoPersonnelPage");
    	btn_assign = createButton(btn_assign, "Assign", "assignCrew");
    	btn_assign.setVerticalTextPosition(AbstractButton.BOTTOM);
    	btn_assign.setHorizontalTextPosition(AbstractButton.CENTER);
    	btn_assign.addActionListener(this);
		
    	// Make labels
    	JL_personnel = new JLabel("Select personnel");
    	JL_flights = new JLabel("Select flight");
    	
		// Make drop downs & populate
		JDD_personnel = new JComboBox();
		JDD_flights = new JComboBox();
    	try {
    		ResultSet r1 = dc.getPersonnel();
    		while(r1.next()){
    			pids.add(r1.getLong("id"));
    			JDD_personnel.addItem(r1.getLong("id"));
    		}
    		ResultSet r2 = dc.getallFlights();
    		while(r2.next()){
    			fids.add(r2.getLong("id"));
    			JDD_flights.addItem(r2.getLong("id"));
    		}
    		r1.close();
    		r2.close();
    	} catch (SQLException s) {
    		s.printStackTrace();
    	}
    	
		// JDD_personnel.addItem(pids);
		// JDD_flights.addItem(fids);
		
		mainComponent.add(btn_back);
		mainComponent.add(btn_assign);
		mainComponent.add(JL_personnel);
		mainComponent.add(JDD_personnel);
		mainComponent.add(JL_flights);
		mainComponent.add(JDD_flights);
	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		mainComponent.remove(btn_back);
		mainComponent.remove(btn_assign);
		mainComponent.remove(JDD_personnel);
		mainComponent.remove(JDD_flights);
		mainComponent.remove(JL_personnel);
		mainComponent.remove(JL_flights);
	}

}
