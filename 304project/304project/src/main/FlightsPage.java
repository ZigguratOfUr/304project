// View all flights scheduled is a list from list select flight 
package main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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
	JButton  b1,b2,b3,b4, b5, ok_button;
	JScrollPane scrollPane;
	
	// From CancelFlight
	JLabel deleteflight;
	JTextField deleteflighttext = new JTextField(20);
	
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
		
		//mainComponent.setLayout(null);
	}

	@Override
	public void createPage()
	{

        
        b1 = new JButton("Cancel Flight");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("CancelFlight");
        b1.addActionListener(this);
        b1.setBounds(50, 50, 40, 30);;

        mainComponent.add(b1);
        
        b2 = new JButton("View all flights");
        b2.setVerticalTextPosition(AbstractButton.TOP);
        b2.setHorizontalTextPosition(AbstractButton.LEFT);
        b2.setActionCommand("createallFlightsTable");
        b2.addActionListener(this);

        mainComponent.add(b2);
        
        b3 = new JButton("Schedule Flight");
        b3.setVerticalTextPosition(AbstractButton.TOP);
        b3.setHorizontalTextPosition(AbstractButton.LEFT);
        b3.setActionCommand("ScheduleFlights");
        b3.addActionListener(this);

        mainComponent.add(b3);
        
        b4 = new JButton("Back");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        b4.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setActionCommand("gotoAdminPage");
        b4.addActionListener(mainComponent);

        mainComponent.add(b4);
        
       
	}

	

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		
		if ("createallFlightsTable".equals(evt.getActionCommand()))
		{
			Object[][] data = dc.getallFlightsTable();
    		
    		JTable table = new JTable(data, DatabaseConnecter.FLIGHT_TABLE_COLUMN_NAMES);
    		table.setPreferredScrollableViewportSize(new Dimension(500, 120));
    		table.setFillsViewportHeight(true);
    		
    		if (scrollPane!= null)
    		{
    			mainComponent.remove(scrollPane);
    		}
    		//Create the scroll pane and add the table to it.
    		scrollPane = new JScrollPane(table);
    		//Add the scroll pane to this panel.
            
    		mainComponent.add(scrollPane);
    		mainComponent.revalidate();
    		mainComponent.repaint();

		}
		
		else if ("CancelFlight".equals(evt.getActionCommand()))
		{
			drawFlightTable();
			JLabel deleteflight = new JLabel("Flight ID");
			JButton ok_button = new JButton("OK");
			ok_button.addActionListener(this);
			mainComponent.add(deleteflight);
			mainComponent.add(deleteflighttext);
			mainComponent.add(ok_button);
		}
		else if ("OK".equals(evt.getActionCommand())){
			int flightID = Integer.parseInt(deleteflighttext.getText());
			dc.deleteFlight(flightID);
			drawFlightTable();
		}
		else if ("ScheduleFlights".equals(evt.getActionCommand()))
		{
			System.out.println("Check1");
			drawFlightTable();
    		
			JTextField flightID = new JTextField(50);
			flightID.setText("");
			JLabel flight = new JLabel("Flight ID");
			mainComponent.add(flight);
			mainComponent.add(flightID);
			
			// String variables for drop down menu
			String [] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			String [] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
							"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
							"21", "22", "23", "24", "25", "26" , "27" , "28", "29", "30", "31"};
			String [] years = {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"};
			String [] hours = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
					"21", "22", "23", "00"};
			String [] minutes = {"00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16" , "17" , "18", "19", "20",
					"21", "22", "23", "24", "25", "26" , "27" , "28", "29", "30",
					"31", "32", "33", "34", "35", "36" , "37" , "38", "39", "40",
					"41", "42", "43", "44", "45", "46" , "47" , "48", "49", "50",
					"51", "52", "53", "54", "55", "56" , "57" , "58", "59"};
			
			//Drop downs for Scheduled Arrival;
			JComboBox yearD = new JComboBox(years);
			JLabel dep = new JLabel("Scheduled Departure: ");
			JLabel dep_year = new JLabel("Year");
			mainComponent.add(dep);
			mainComponent.add(dep_year);
			mainComponent.add(yearD);
			
			JComboBox monthD = new JComboBox(months);
			JLabel dep_month = new JLabel("Month");
			mainComponent.add(dep_month);
			mainComponent.add(monthD);
			
			JComboBox dayD = new JComboBox(days);
			JLabel dep_day = new JLabel("Date");
			mainComponent.add(dep_day);
			mainComponent.add(dayD);
			
			JComboBox HourD = new JComboBox(hours);
			JLabel dep_hr = new JLabel("Hour");
			mainComponent.add(dep_hr);
			mainComponent.add(HourD);
			
			JComboBox MinuteD = new JComboBox(minutes);
			JLabel dep_min = new JLabel("minute");
			mainComponent.add(dep_min);
			mainComponent.add(MinuteD);
			
			JComboBox yearA = new JComboBox(years);
			JLabel arr = new JLabel("Scheduled Arrival: ");
			JLabel arr_year = new JLabel("Year");
			mainComponent.add(arr);
			mainComponent.add(arr_year);
			mainComponent.add(yearA);
			
			JComboBox monthA = new JComboBox(months);
			JLabel arr_month = new JLabel("Month");
			mainComponent.add(arr_month);
			mainComponent.add(monthA);
			
			JComboBox dayA = new JComboBox(days);
			JLabel arr_day = new JLabel("Date");
			mainComponent.add(arr_day);
			mainComponent.add(dayA);
			
			JComboBox HourA = new JComboBox(hours);
			JLabel arr_hr = new JLabel("Hour");
			mainComponent.add(arr_hr);
			mainComponent.add(HourA);
			
			JComboBox MinuteA = new JComboBox(minutes);
			JLabel arr_min = new JLabel("minute");
			mainComponent.add(arr_min);
			mainComponent.add(MinuteA);
			
			String scheduledDepartureTime = yearD.getSelectedItem().toString() + "/" + monthD.getSelectedItem().toString() + "/"+ dayD.getSelectedItem().toString()
					+ " " + HourD.getSelectedItem().toString()+ ":" + MinuteD.getSelectedItem().toString()+ ":00";
			
			String scheduledArrivalTime = yearA.getSelectedItem().toString() + "/" + monthA.getSelectedItem().toString() + "/"+ dayA.getSelectedItem().toString()
					+ " " + HourA.getSelectedItem().toString()+ ":" + MinuteA.getSelectedItem().toString()+ ":00";
			System.out.println("Schedule Dep: " + scheduledDepartureTime);
			
			Date s_arrival = new Date(scheduledArrivalTime);
			Date s_departure = new Date(scheduledDepartureTime);
			
			if(s_arrival.before(s_departure)){
				final JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Invalid Entry of Dates", "Error", JOptionPane.ERROR_MESSAGE);		
			}
			
			JTextField originF = new JTextField(50);
			originF.setText("");
			JLabel originL = new JLabel("From: ");
			mainComponent.add(originL);
			mainComponent.add(originF);
			
			JTextField destinationF = new JTextField(50);
			destinationF.setText("");
			JLabel destinationL = new JLabel("To: ");
			mainComponent.add(destinationL);
			mainComponent.add(destinationF);
			
			String [] airlineIDs = {"1", "2", "3", "4", "5"};
			JComboBox airlineID = new JComboBox(airlineIDs);
			JLabel airline = new JLabel("Airline ID");
			mainComponent.add(airline);
			mainComponent.add(airlineID);
			
			String [] planeID_air1 = {"101", "102", "103"};
			String [] planeID_air2 = {"201", "202", "203"};
			String [] planeID_air3 = {"301", "302", "303"};
			String [] planeID_air4 = {"401", "402", "403"};
			String [] planeID_air5 = {"501", "502", "503"};
			
			if (airlineID.getSelectedItem().toString() == "1"){
				JComboBox planeID = new JComboBox(planeID_air1);
				JLabel planeModel = new JLabel("Plane Model");
				mainComponent.add(planeModel);
				mainComponent.add(planeID);
			}
			else if (airlineID.getSelectedItem().toString() == "2"){
				JComboBox planeID = new JComboBox(planeID_air2);
				JLabel planeModel = new JLabel("Plane Model");
				mainComponent.add(planeModel);
				mainComponent.add(planeID);
			}
			else if (airlineID.getSelectedItem().toString() == "3"){
				JComboBox planeID = new JComboBox(planeID_air3);
				JLabel planeModel = new JLabel("Plane Model");
				mainComponent.add(planeModel);
				mainComponent.add(planeID);
			}
			else if (airlineID.getSelectedItem().toString() == "4"){
				JComboBox planeID = new JComboBox(planeID_air4);
				JLabel planeModel = new JLabel("Plane Model");
				mainComponent.add(planeModel);
				mainComponent.add(planeID);
			}
			else if (airlineID.getSelectedItem().toString() == "5"){
				JComboBox planeID = new JComboBox(planeID_air5);
				JLabel planeModel = new JLabel("Plane Model");
				mainComponent.add(planeModel);
				mainComponent.add(planeID);
			}
			
			JButton ok_schedule = new JButton("ScheduleOK");
			ok_schedule.addActionListener(this);
			mainComponent.add(ok_schedule);
			
		}
		
		else if ("ScheduleOK".equals(evt.getActionCommand())){
			
			int flightId = Integer.parseInt(flightID.getText());
			String departureTime = null;
			String arrivalTime = null;
			String status = "Scheduled";	
			int planeId = Integer.parseInt(planeID.getSelectedItem().toString());
			int airlineId = Integer.parseInt(airlineID.getSelectedItem().toString());	
			String origin = originF.getText();
			String destination = destinationF.getText();
			
			dc.ScheduleFlight(flightId , departureTime , arrivalTime, scheduledDepartureTime , scheduledArrivalTime, origin , destination , status , planeId , airlineId);
			System.out.println("Flight: ");
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
			mainComponent.remove(scrollPane);
		}
		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		//Add the scroll pane to this panel.
        
		mainComponent.add(scrollPane);
		mainComponent.revalidate();
		mainComponent.repaint();
	}
	
	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
		mainComponent.remove(b3);
		mainComponent.remove(b4);
		
		//Remove things from Delete
		mainComponent.remove(deleteflight);
		mainComponent.remove(deleteflighttext);
		mainComponent.remove(ok_button);
		
		//Remove things from Schedule
		mainComponent.remove(dep);
		mainComponent.remove(dep_year);
		mainComponent.remove(yearD);
		mainComponent.remove(dep_month);
		mainComponent.remove(monthD);
		mainComponent.remove(dep_day);
		mainComponent.remove(dayD);
		mainComponent.remove(dep_hr);
		mainComponent.remove(HourD);
		mainComponent.remove(dep_min);
		mainComponent.remove(MinuteD);
		
		mainComponent.remove(arr_min);
		mainComponent.remove(MinuteA);
		mainComponent.remove(arr);
		mainComponent.remove(arr_year);
		mainComponent.remove(yearA);
		mainComponent.remove(arr_month);
		mainComponent.remove(monthA);
		mainComponent.remove(arr_day);
		mainComponent.remove(dayA);
		mainComponent.remove(arr_hr);
		mainComponent.remove(HourA);
		mainComponent.remove(originL);
		mainComponent.remove(originF);
		mainComponent.remove(destinationL);
		mainComponent.remove(destinationF);
		
		mainComponent.remove(airline);
		mainComponent.remove(airlineID);
		mainComponent.remove(airline);
		mainComponent.remove(planeID);
		
		mainComponent.remove(ok_schedule);
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}
	}

}