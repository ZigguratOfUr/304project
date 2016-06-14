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

import javax.swing.JOptionPane;

public class DatabaseConnecter
{
	public static final String[] PERSONNEL_TABLE_COLUMN_NAMES = {	"id",
								            						"pname",
								            						"phone",
													        		"hireDate",
													        		"airlineId"};
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
		// order of columns in db: id, pname, phonenumber, hiredate, airlineid
		PreparedStatement stmt = con.prepareStatement("insert into personnel values (?, ?, ?, ?, ?)");
		stmt.setLong(1, id);
		stmt.setString(2, name);
		stmt.setLong(3, phone);
		stmt.setDate(4, hireDate);
		stmt.setLong(5, airline);
	}
}
