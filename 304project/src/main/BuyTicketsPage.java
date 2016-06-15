package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BuyTicketsPage extends Page implements ActionListener, ListSelectionListener {

	JButton b1, b2;
	JScrollPane scrollPane, myTicketsPane;
	JLabel myTickets;

	Object[][] data = dc.getScheduledFlightTable();
	JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);

	int rowSelection;
	// Object selectedPurchase;
	int sPurchase;

	public BuyTicketsPage(GUIMain mainComponent, DatabaseConnecter dc) {
		super(mainComponent, dc);
	}

	@Override
	public void createPage() {

		b1 = new JButton("Back");
		b1.setVerticalTextPosition(AbstractButton.BOTTOM);
		b1.setHorizontalTextPosition(AbstractButton.CENTER);
		b1.setActionCommand("gotoViewFlightsPage");
		b1.addActionListener(mainComponent);

		mainComponent.add(b1);

		b2 = new JButton("Purchase Selected");
		b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		b2.setHorizontalTextPosition(AbstractButton.CENTER);
		b2.setActionCommand("gotoMadePurchase");
		b2.addActionListener(this);

		mainComponent.add(b2);

		// Object[][] data = dc.getScheduledFlightTable();

		// Create a new table instance
		// JTable table = new JTable(data,
		// DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(this);

		table.setPreferredScrollableViewportSize(new Dimension(700, 200));
		table.setFillsViewportHeight(true);

		if (scrollPane != null) {
			mainComponent.remove(scrollPane);
		}

		scrollPane = new JScrollPane(table);
		mainComponent.add(scrollPane);

		getMyTickets();
		mainComponent.revalidate();
		mainComponent.repaint();
	}

	public void getMyTickets() {
		if (myTicketsPane != null) {
			mainComponent.remove(myTicketsPane);
			mainComponent.remove(myTickets);
		}

		myTickets = new JLabel();
		myTickets.setText("My tickets");
		myTickets.setFont(new Font(myTickets.getFont().getFontName(), 0, 16));
		mainComponent.add(myTickets);

		Object[][] data = dc.getTicketsTable(mainComponent.loginId);

		JTable table = new JTable(data, DatabaseConnecter.MY_TICKETS_TABLE_COLUMN_NAMES);
		table.setPreferredScrollableViewportSize(new Dimension(700, 120));
		table.setFillsViewportHeight(true);

		if (myTicketsPane != null) {
			mainComponent.remove(myTicketsPane);
		}
		// Create the scroll pane and add the table to it.
		myTicketsPane = new JScrollPane(table);
		// Add the scroll pane to this panel.

		mainComponent.add(myTicketsPane);

		mainComponent.revalidate();
		mainComponent.repaint();
	}

	public void valueChanged(ListSelectionEvent event) {

		if (event.getSource() == table.getSelectionModel() && event.getFirstIndex() >= 0) {
			TableModel model = (TableModel) table.getModel();
			// int model = table.

			rowSelection = table.getSelectedRow();

			int rowCount = table.getRowCount();
			int columnCount = table.getColumnCount();

			// the flight id is here
			// selectedPurchase = model.getValueAt(rowSelection, 0);

			sPurchase = Integer.parseInt(model.getValueAt(rowSelection, 0).toString());

			System.out.println("Value selected = " + rowSelection);
			System.out.println(sPurchase);
		}
	}

	@Override
	public void cleanPage() {
		mainComponent.remove(b1);
		mainComponent.remove(b2);

		if (scrollPane != null) {
			mainComponent.remove(scrollPane);
		}

		if (myTicketsPane != null) {
			mainComponent.remove(myTicketsPane);
			mainComponent.remove(myTickets);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if ("gotoMadePurchase".equals(evt.getActionCommand())) {
			if (table.getSelectedRow() > -1) {
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to purchase flight:" + " " + sPurchase, "Confirmation",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					String[] options = { "economy", "business", "first" };
					String[] seats = { "window", "normal" };

					int choice = JOptionPane.showOptionDialog(null, "Choose your desired seating class",
							"Class Seating", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null,
							options, options[2]);

					// the resulting seating class choice
					String option = options[choice];

					// String seatType = seats[choice];

					int seatChoice = JOptionPane.showOptionDialog(null, "Choose your desired seat type", "Seat type",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, seats, seats[1]);
					// the resulting seat choice
					String seat = seats[seatChoice];

					// the ticket ID
					int tID;

					try {
						tID = dc.getTicketID();
						dc.buyTicket(tID, option, seat, mainComponent.loginId, sPurchase);
					} catch (SQLException s) {
						System.out.println("Got an SQL error");
						s.printStackTrace();
					}
				}

				JOptionPane.showMessageDialog(null,
						"The purchase of ticket for flight" + " " + ":" + " " + sPurchase + " has been successful",
						"Confirmation", JOptionPane.INFORMATION_MESSAGE);
				getMyTickets();
			} else {
				JOptionPane.showMessageDialog(null, "No flight selected yet", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
