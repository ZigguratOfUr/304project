package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class DatabaseConnecter
{
	public static final String[] PERSONNEL_TABLE_COLUMN_NAMES = {	"id",
								            						"pname",
								            						"phone",
													        		"hireDate",
													        		"airlineId"};

	public static final String[] FLIGHT_TABLE_COLUMN_NAMES = { "flight id",
																"departure time",
																"arrival time",
																"scheduled departure time",
																"scheduled arrival time",
																"origin",
																"destination",
																"status",
																"planeid",
																"airline id"};
	public static final String[] SCHEDULED_FLIGHT_TABLE_COLUMN_NAMES = { "flight id",
																		"scheduled departure time",
																		"scheduled arrival time",
																		"origin",
																		"destination",
																		"status",
																		"planeid",
																		"airline id"};
																
																																
	public static final String [] PURCHASEDTICKET_TABLE_COLUMN_NAMES = {"ticket id",
																  "flight id",
																  "class",
																  "seat",
																  "price"};
	public static final String [] EVERYAIRLINE_TABLE_COLUMN_NAMES = {"passenger id"};
	
	public static final String[] COUNTTICKET_TABLE_COLUMN_NAMES = {"airline",
																	"number of Tickets sold"
																	};
	public static final String[] COUNTFLIGHTS_TABLE_COLUMN_NAMES = { "airline", "model", "flights"};
	

	public static final String[] VIEW_FLIGHT_TABLE_COLUMN_NAMES =  { "Flight Id",
																	 "Departure Time",
																	 "Arrival Time",
																	 "Scheduled Departure",
																	 "Scheduled Arrival",
																	 "Origin",
																	 "Destination",
																	 "Status",
																	 "PlaneID",
																	"AirlineID"
																	};

	
	Connection con;
	
	public DatabaseConnecter() throws SQLException
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",  "ora_z5r8", "a34072124"); //ssh r0e9@remote.ugrad.cs.ubc.ca -L 1522:dbhost.ugrad.cs.ubc.ca:1522

	}
	// getViewFlights for flightID
	public ResultSet getViewFlights() throws SQLException
	{
//		PreparedStatement stmt = con.prepareStatement("SELECT * FROM flight WHERE status <> 'Landed' AND status <> 'Lost' AND Flight Id = ?");
//		String flightString = Integer.toString(flightId);
//		stmt.setString(1, flightString );
//		ResultSet rs = stmt.executeQuery();
//		
//		if(rs.next())
//		{
//			if(rs.)
//		}
		
		
		
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM flight WHERE status <> 'Landed' AND status <> 'Lost' ");
	}
	
	public Object[][] getViewFlightTable()
	{
		ResultSet rs;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			rs = getViewFlights();
			
			while(rs.next())
			{
				Object[] row = {rs.getLong(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)};
				data.add(row);
			}
			rs.close();
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
	    
		return data.toArray(table);
		
	}
	
	public ResultSet getScheduledFlights() throws SQLException
	{
		
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM flight WHERE status = 'Scheduled'");
	}
	
	public Object[][] getScheduledFlightTable()
	{
		ResultSet rs;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			rs = getScheduledFlights();
			
			while(rs.next())
			{
				Object[] row = {rs.getLong(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)};
				data.add(row);
			}
			rs.close();
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
	    
		return data.toArray(table);
		
	}
	
	public ResultSet getPersonnel() throws SQLException
	{
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM personnel");
	}
	
	/*
	 * 0 means no such user
	 * 1 means incorrect password
	 * 2 means everything ok
	 */
	public int passengerExists(String username, String password) throws SQLException
	{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM passenger WHERE username = ?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next())
		{
			if (password.equals(rs.getString(4)))
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			return 0;
		}
	}
	
	public int createAccount(String username, String name, String password) throws SQLException
	{
		PreparedStatement stmt = con.prepareStatement("INSERT INTO passenger VALUES(?, ?, ?, ?, 0)");
		int id = (int) (Math.random() * Integer.MAX_VALUE);
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, username);
		stmt.setString(4, password);
		
		try 
		{
			stmt.executeQuery();
		}
		catch(SQLException e)
		{
			//e.printStackTrace();

			return -1;
		}

		return id;
	}
	
	public Object[][] getPersonnelTable()
	{
		ResultSet rs;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			rs = getPersonnel();

			while(rs.next())
			{
				Object[] row = {rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getLong(5)};
				data.add(row);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
	}
	

// get available flights
	public ResultSet getAvailableFlights() throws SQLException
	{
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM Flight WHERE status = 'Scheduled'");	
	}
	
	public Object [][] getAvailableFlightsTable()
	{
		ResultSet flights;
		List<Object[]> data = new LinkedList<Object[]>();
		try
		{
			flights = getAvailableFlights();

			while(flights.next())
			{
				Object[] row = {flights.getLong(1), flights.getString(2), flights.getString(3),flights.getString(4), flights.getString(5),
						flights.getString(6), flights.getString(7),flights.getString(8), flights.getLong(9),flights.getLong(10)};
				data.add(row);
			}
			flights.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
	}

// Query to see all flights 
	public ResultSet getallFlights () throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM Flight");
	}
	public Object [][] getallFlightsTable()
	{
		ResultSet flights;
		List<Object[]> data = new LinkedList<Object[]>();
		try
		{
			flights = getallFlights();

			while(flights.next())
			{
				Object[] row = {flights.getLong(1), flights.getString(2), flights.getString(3),flights.getString(4), flights.getString(5),
						flights.getString(6), flights.getString(7),flights.getString(8), flights.getLong(9),flights.getLong(10)};
				data.add(row);
			}
			flights.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
	}
	
// Query to purchase tickets
	public ResultSet getpurchaseTickets () throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT  t.id, t.flightid, t.class, t.seat, p.price FROM Ticket t, TicketPrice p WHERE t.class = p.class AND t.seat = p.seat");
	}
	
	public Object[][] getpurchaseTicketTable(){
		ResultSet boughtTicket;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			boughtTicket = getpurchaseTickets();
			while(boughtTicket.next())
			{
				Object[] row = {boughtTicket.getLong(1), boughtTicket.getLong(2), boughtTicket.getString(3), boughtTicket.getString(4), boughtTicket.getInt(5)	
				};
				data.add(row);
			}
			boughtTicket.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
		
	}
	
// query to find customer who has been on every airline : result should be Dick VanDyke
	 
	public ResultSet geteveryAirlineTicket() throws SQLException{
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT distinct t.passengerid "
								+ "FROM Ticket t, Flight f "
								+ "WHERE t.flightid = f.id AND NOT EXISTS"
								+ "((select id "
								+ "from Airline)"
								+ "except"
								+ "(select airlineid"
								+ "from Flight"
								+ "where t.flightid = f.id))");
	}

	public Object[][] geteveryairlineTicketTable(){
		ResultSet eaticket;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			eaticket = geteveryAirlineTicket();
			while(eaticket.next())
			{
				Object[] row = {eaticket.getLong(1)	
				};
				data.add(row);
			}
			
			eaticket.close();	
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
		
	}

// Query to see how many tickets has each airline sold
	
	public ResultSet getCountTicket() throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT a.airlineName, count(t.id) as ticketsSold FROM Airline a , Ticket t, Flight f WHERE a.id = f.airlineID AND f.id = t.flightid GROUP BY a.airlineName");
	}
	public Object[][] getCountTicketTable(){
		ResultSet numTickets;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			numTickets = getCountTicket();
			while(numTickets.next())
			{
				Object[] row = {numTickets.getString(1),numTickets.getInt(2)	
				};
				data.add(row);
			}
			numTickets.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
		
	}

// Query to see how many flights each plane within each airline has flown

 public ResultSet getCountFlights() throws SQLException{
	 Statement stmt = con.createStatement();
	 return stmt.executeQuery("SELECT a.airlineName as airline, p.model as model, count(*) as numflights FROM Airline a, plane p, flight f "
	 		+ "WHERE a.id = p.airlineid AND f.planeid = p.id GROUP BY a.airlineName, p.model");
 }
 public Object[][] getCountFlightsTable(){
		ResultSet countFlights;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try
		{
			countFlights = getCountFlights();
			while(countFlights.next())
			{
				Object[] row = {countFlights.getString(1),countFlights.getString(2), countFlights.getInt(3)	
				};
				data.add(row);
			}
			countFlights.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
		
	}
 
 public void ScheduleFlight (int flightId, String departureTime, String arrivalTime, String scheduledDepartureTime, String scheduledArrivalTime, String origin, String destination, String status, int planeId, int airlineId){
	 PreparedStatement stmt;
	 System.out.println("add flight1");
	 try {
		stmt= con.prepareStatement("INSERT INTO Flight(flightId, departureTime, arrivalTime, scheduledDepartureTime, scheduledArrivalTime, origin, destination, status, planeId, airlineId) VALUES (?,?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1,flightId);
		stmt.setString(2,departureTime);
		stmt.setString(3,arrivalTime);
		stmt.setString(4,scheduledDepartureTime);
		stmt.setString(5 ,scheduledArrivalTime);
		stmt.setString(6 , origin);
		stmt.setString(7, destination);
		stmt.setString(8, status);
		stmt.setInt(9, planeId);
		stmt.setInt(10, airlineId);
		
		stmt.executeUpdate();
		System.out.println("add flight2");
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }

 public void deleteFlight (int flightID){
	
		PreparedStatement stmt;
		try {

			System.out.println("The input flight id is: 1");
			stmt = con.prepareStatement("DELETE FROM Flight WHERE id = ?");
			stmt.setInt(1, flightID );
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
	

	// check if passenger is already on the table?
//	public void buyTicket(int id, String classP, String seat, int pID, int fID) throws SQLException {
//		PreparedStatement stmt = con.prepareStatement("insert into Ticket value(?, ?, ?, ?, ?)");
//		stmt.setInt(1, id);
//		stmt.setString(2, classP);
//		stmt.setString(3, seat);
//		stmt.setInt(4, pID);
//		stmt.setInt(5, fID);
//		
//		stmt.executeUpdate();
//		
//		
//	}
	
	public int getTicketID() throws SQLException
	{
		Statement stmt = con.createStatement();
		int id = -1;
	    stmt.executeQuery("SELECT max(id) FROM Ticket");
	    ResultSet rs;
	    
	    rs = stmt.getResultSet();
////	    id = (int) rs.getLong(1) + 1;
	    id = rs.getInt(1) + 1;
	    return id;
	}

	public void firePersonnel(GUIMain component, long id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("delete from personnel where id = ?");
		stmt.setLong(1, id);
		int rs_del = stmt.executeUpdate();
		if (rs_del != 0){
			// Found a personnel match, delete was successful
			JOptionPane.showMessageDialog(component, "Fired " + rs_del + " personnel with id: " + id, "DELETE SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);
		} else {
			// Did not find a match, delete yielded no results
			JOptionPane.showMessageDialog(component, "No personnel with id: " + id + " exists.", "DELETE ABORTED", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void hirePersonnel(GUIMain component, long id, String name, long phone, Date hireDate, long airline) throws SQLException{
		PreparedStatement check = con.prepareStatement("select count(*) from personnel where id = ?");
		check.setLong(1, id);
		ResultSet rs = check.executeQuery();
		rs.next();
		int id_exists = rs.getInt(1);
		System.out.println("id_exists is: " + id_exists);
		if (id_exists == 0) {  // The input id does not exist in the DB, insert new id
			
		// order of columns in db: id, pname, phonenumber, hiredate, airlineid
		PreparedStatement stmt = con.prepareStatement("insert into personnel values (?, ?, ?, ?, ?)");
		stmt.setLong(1, id);
		stmt.setString(2, name);
		stmt.setLong(3, phone);
		stmt.setDate(4, hireDate);
		stmt.setLong(5, airline);
		stmt.executeUpdate();
		JOptionPane.showMessageDialog(component, "Personnel successfully created.",
				"HIRE SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(component, "Personnel with id already exists. Please re-enter another id.",
											"HIRE UNSUCCESSFUL", JOptionPane.ERROR_MESSAGE);
		}
	}

}
