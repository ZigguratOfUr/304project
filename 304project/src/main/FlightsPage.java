// View all flights scheduled is a list from list select flight 
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.print.attribute.DateTimeSyntax;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FlightsPage extends Page implements ActionListener
{
	// String variables for drop down menu
	private final String [] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private final String [] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
					"21", "22", "23", "24", "25", "26" , "27" , "28", "29", "30", "31"};
	private final String [] years = {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"};
	private final String [] hours = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
			"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
			"21", "22", "23", "00"};
	private final String [] minutes = {"00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
			"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
			"21", "22", "23", "24", "25", "26" , "27" , "28", "29", "30",
			"31", "32", "33", "34", "35", "36" , "37" , "38", "39", "40",
			"41", "42", "43", "44", "45", "46" , "47" , "48", "49", "50",
			"51", "52", "53", "54", "55", "56" , "57" , "58", "59"};
	
	JPanel flightsPage, deletePane, createPane;
	
	JButton ok_button;
	JScrollPane scrollPane;
	
	// From CancelFlight
	JLabel deleteflight, deleteFlightWarningText, createFlightWarningText;
	JTextField deleteflighttext;
	
	//From ScheduleFlight
	JTextField flightID;
	JLabel flight;
	
	JComboBox yearD;
	JLabel dep;
	JLabel dep_year;
	JComboBox monthD;
	JLabel dep_month;
	JComboBox dayD;
	JLabel dep_day;
	JComboBox HourD;
	JLabel dep_hr;
	JComboBox MinuteD;
	JLabel dep_min;
	String scheduledDepartureTime;
	
	JComboBox yearA;
	JLabel arr;
	JLabel arr_year;
	JComboBox monthA;
	JLabel arr_month;
	JComboBox dayA;
	JLabel arr_day;
	JComboBox HourA;
	JLabel arr_hr;
	JComboBox MinuteA;
	JLabel arr_min;
	String scheduledArrivalTime;

	JTextField originF;
	JLabel originL;
	JTextField destinationF;
	JLabel destinationL;
	
	JComboBox airlineID ;
	JLabel airline;
	
	JComboBox planeID;
	JLabel planeModel;
	
	JButton ok_schedule;
	

	public FlightsPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
		flightsPage = new JPanel();
		flightsPage.setLayout(null);
		flightsPage.setPreferredSize(new Dimension(800,800));
		//mainComponent.setLayout(null);
		deleteflighttext = new JTextField(20);
	}

	@Override
	public void createPage()
	{
		
		
        JButton b1 = new JButton("Cancel Flights");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("CancelFlight");
        b1.addActionListener(this);
        b1.setBounds(150, 50, 160, 40);
        b1.setForeground(Color.RED);

        flightsPage.add(b1);
        
        JButton b2 = new JButton("View All Flights");
        b2.setVerticalTextPosition(AbstractButton.TOP);
        b2.setHorizontalTextPosition(AbstractButton.LEFT);
        b2.setActionCommand("generateFlightTable");
        b2.addActionListener(this);
        b2.setBounds(330, 50, 160, 40);
        b2.setForeground(Color.BLUE);

        flightsPage.add(b2);
        
        JButton b3 = new JButton("Schedule Flights");
        b3.setVerticalTextPosition(AbstractButton.TOP);
        b3.setHorizontalTextPosition(AbstractButton.LEFT);
        b3.setActionCommand("ScheduleFlights");
        b3.addActionListener(this);
        b3.setBounds(510, 50, 160, 40);
        b3.setForeground(new Color(79, 121, 66));

        flightsPage.add(b3);
        
        JButton b4 = new JButton("Back");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("gotoAdminPage");
        b4.addActionListener(mainComponent);
        b4.setBounds(20, 20, 80, 25);

        flightsPage.add(b4);

        this.actionPerformed( new ActionEvent(this, 0, "generateFlightTable"));
        
        mainComponent.add(flightsPage);
	}

	

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (!("Delete".equals(evt.getActionCommand())))
		{
			if (deletePane!= null)
			{
				flightsPage.remove(deletePane);

				flightsPage.revalidate();
				flightsPage.repaint();
			}
		}
		
		if (!("ScheduleFlight".equals(evt.getActionCommand())))
		{
			if (createPane!= null)
			{
				flightsPage.remove(createPane);

				flightsPage.revalidate();
				flightsPage.repaint();
			}
		}
		
		if ("generateFlightTable".equals(evt.getActionCommand()))
		{
			
			Object[][] data = dc.getallFlightsTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.FLIGHT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(700, 160));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			flightsPage.remove(scrollPane);
    		}
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		scrollPane.setBounds(50, 100, 700, 400);
    		//Add the scroll pane to this panel.
            
    		flightsPage.add(scrollPane);
		}
		
		else if ("CancelFlight".equals(evt.getActionCommand()))
		{
			drawFlightTable();
			
			deletePane = new JPanel();
			deletePane.setLayout(null);
			deletePane.setPreferredSize(new Dimension(800,800));
			deletePane.setBounds(0, 0, 800, 800);
			
			JLabel deleteflight = new JLabel("Flight ID:");
			deleteflight.setBounds(250, 520, 100, 25);
			JButton deleteButton = new JButton("Cancel Flight");
			deleteButton.setBounds(490, 520, 160, 25);
			deleteButton.setActionCommand("Delete");
			deleteButton.addActionListener(this);
			deletePane.add(deleteflight);
			deletePane.add(deleteflighttext);
			deleteflighttext.setBounds(320, 520, 160, 25);
			deletePane.add(deleteButton);
			
			deleteFlightWarningText = new JLabel();
			deleteFlightWarningText.setBounds(320, 550, 160, 20);
			deleteFlightWarningText.setFont(new Font(deleteFlightWarningText.getFont().getFontName(), 0, 10));
			deletePane.add(deleteFlightWarningText);
			
			flightsPage.add(deletePane);
			flightsPage.revalidate();
			flightsPage.repaint();
		}
		else if ("Delete".equals(evt.getActionCommand()))
		{
			try
			{
				int flightID = Integer.parseInt(deleteflighttext.getText());
				int result = dc.deleteFlight(flightID);
				deleteflighttext.setText("");
				if (result == -1)
				{
					deleteFlightWarningText.setForeground(Color.RED);
					deleteFlightWarningText.setText("SQL Exception");
				}
				else if (result == 0)
				{
					deleteFlightWarningText.setForeground(Color.RED);
					deleteFlightWarningText.setText("No such flight");
				}
				else if (result == 1)
				{
					drawFlightTable();
					deleteFlightWarningText.setForeground(Color.BLACK);
					deleteFlightWarningText.setText("Flight cancelled");
				}
			}
			catch (NumberFormatException e)
			{
				deleteflighttext.setText("");
				deleteFlightWarningText.setForeground(Color.RED);
				deleteFlightWarningText.setText("Invalid input.");
			}
		}
		else if ("ScheduleFlights".equals(evt.getActionCommand()))
		{
			drawFlightTable();
    		
			createPane = new JPanel();
			//createPane.setLayout(null);
			createPane.setPreferredSize(new Dimension(800,800));
			createPane.setBounds(0, 500, 800, 300);
			
			JPanel row1 = new JPanel();
			flightID = new JTextField(50);
			JLabel flight = new JLabel("Flight ID");
			row1.add(flight);
			row1.add(flightID);
			
			JPanel row2 = new JPanel();
			//Drop downs for Scheduled Arrival;
			yearD = new JComboBox(years);
			JLabel dep = new JLabel("Scheduled Departure: ");
			JLabel dep_year = new JLabel("Year");
			row2.add(dep);
			row2.add(dep_year);
			row2.add(yearD);
			
			monthD = new JComboBox(months);
			JLabel dep_month = new JLabel("Month");
			row2.add(dep_month);
			row2.add(monthD);
			
			dayD = new JComboBox(days);
			JLabel dep_day = new JLabel("Day");
			row2.add(dep_day);
			row2.add(dayD);
			
			HourD = new JComboBox(hours);
			JLabel dep_hr = new JLabel("Hour");
			row2.add(dep_hr);
			row2.add(HourD);
			
			MinuteD = new JComboBox(minutes);
			JLabel dep_min = new JLabel("Minute");
			row2.add(dep_min);
			row2.add(MinuteD);
			
			JPanel row3 = new JPanel();
			
			yearA = new JComboBox(years);
			JLabel arr = new JLabel("Scheduled Arrival: ");
			JLabel arr_year = new JLabel("Year");
			row3.add(arr);
			row3.add(arr_year);
			row3.add(yearA);
			
			monthA = new JComboBox(months);
			JLabel arr_month = new JLabel("Month");
			row3.add(arr_month);
			row3.add(monthA);
			
			dayA = new JComboBox(days);
			JLabel arr_day = new JLabel("Day");
			row3.add(arr_day);
			row3.add(dayA);
			
			HourA = new JComboBox(hours);
			JLabel arr_hr = new JLabel("Hour");
			row3.add(arr_hr);
			row3.add(HourA);
			
			MinuteA = new JComboBox(minutes);
			JLabel arr_min = new JLabel("Minute");
			row3.add(arr_min);
			row3.add(MinuteA);
			
			createPane.add(row1);
			createPane.add(row2);
			createPane.add(row3);
			
			JPanel row4 = new JPanel();

			originF = new JTextField(50);
			originF.setText("");
			JLabel originL = new JLabel("From: ");
			row4.add(originL);
			row4.add(originF);
			
			JPanel row5 = new JPanel();

			destinationF = new JTextField(50);
			destinationF.setText("");
			JLabel destinationL = new JLabel("To: ");
			row5.add(destinationL);
			row5.add(destinationF);
			

			createPane.add(row4);
			createPane.add(row5);
			
			JPanel row6 = new JPanel();

			airlineID = new JComboBox(dc.getAirlineIds());
			JLabel airline = new JLabel("Airline ID");
			row6.add(airline);
			row6.add(airlineID);
			

			planeID = new JComboBox(dc.getPlaneIds());
			JLabel planeModel = new JLabel("Plane Model");
			row6.add(planeModel);
			row6.add(planeID);
			
			JButton ok_schedule = new JButton("Schedule Flight");
			ok_schedule.setActionCommand("ScheduleFlight");
			ok_schedule.addActionListener(this);
			row6.add(ok_schedule);
			
			createPane.add(row6);
			
			flightsPage.add(createPane);
			flightsPage.revalidate();
			flightsPage.repaint();
		}
		else if ("ScheduleFlight".equals(evt.getActionCommand()))
		{
			if(createFlightWarningText !=null)
			{
				createPane.remove(createFlightWarningText);
			}
			if(!pageIsValid())
			{
				createFlightWarningText = new JLabel("Invalid request.");
				createFlightWarningText.setFont(new Font(createFlightWarningText.getFont().getFontName(), 0, 10));
				createFlightWarningText.setForeground(Color.RED);
				createPane.add(createFlightWarningText);
				createPane.revalidate();
				createPane.repaint();
				
				return;
			}
			
			int flightId = Integer.parseInt(flightID.getText());
			String departureTime = null;
			String arrivalTime = null;
			String status = "Scheduled";	
			int planeId = Integer.parseInt(planeID.getSelectedItem().toString());
			int airlineId = Integer.parseInt(airlineID.getSelectedItem().toString());	
			String origin = originF.getText();
			String destination = destinationF.getText();
			String scheduledDepartureTime = yearD.getSelectedItem().toString() + "-" + monthD.getSelectedItem().toString() + "-"+ dayD.getSelectedItem().toString()
					+ " " + HourD.getSelectedItem().toString()+ ":" + MinuteD.getSelectedItem().toString()+ ":00.0000";
			
			String scheduledArrivalTime = yearA.getSelectedItem().toString() + "-" + monthA.getSelectedItem().toString() + "-"+ dayA.getSelectedItem().toString()
					+ " " + HourA.getSelectedItem().toString()+ ":" + MinuteA.getSelectedItem().toString()+ ":00.0000";

			dc.ScheduleFlight(flightId , departureTime , arrivalTime, scheduledDepartureTime , scheduledArrivalTime, origin , destination , status , planeId , airlineId);
			drawFlightTable();
		}
	}
	
	private void drawFlightTable (){
		Object[][] data = dc.getAvailableFlightsTable();
		JTable table = new JTable(data, DatabaseConnecter.FLIGHT_TABLE_COLUMN_NAMES);
		table.setPreferredScrollableViewportSize(new Dimension(800, 120));
		table.setFillsViewportHeight(true);
		
		if (scrollPane!= null)
		{
			flightsPage.remove(scrollPane);
		}
		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 100, 700, 400);
		//Add the scroll pane to this panel.
        
		flightsPage.add(scrollPane);
	}
	
	private boolean pageIsValid()
	{
		try
		{
			Integer.parseInt(flightID.getText());
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		

		String scheduledDepartureTime = yearD.getSelectedItem().toString() + "-" + monthD.getSelectedItem().toString() + "-"+ dayD.getSelectedItem().toString()
				+ " " + HourD.getSelectedItem().toString()+ ":" + MinuteD.getSelectedItem().toString()+ ":00.0000";
		
		String scheduledArrivalTime = yearA.getSelectedItem().toString() + "-" + monthA.getSelectedItem().toString() + "-"+ dayA.getSelectedItem().toString()
				+ " " + HourA.getSelectedItem().toString()+ ":" + MinuteA.getSelectedItem().toString()+ ":00.0000";
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSSS");
		try {
			Date departureDate = formatter.parse(scheduledDepartureTime);
			Date arrivalDate = formatter.parse(scheduledArrivalTime);
			
			if (arrivalDate.after(departureDate))
			{
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	@Override
	public void cleanPage()
    {
		mainComponent.remove(flightsPage);
	}

}
