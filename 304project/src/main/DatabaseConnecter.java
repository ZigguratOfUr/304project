package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DatabaseConnecter
{
	public static final String[] PERSONNEL_TABLE_COLUMN_NAMES = {	"id",
								            						"pname",
								            						"phone",
													        		"hireDate",
													        		"airlineId"};
	
	Connection con;
	
	public DatabaseConnecter() throws SQLException
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",  "ora_z5r8", "a34072124"); //ssh r0e9@remote.ugrad.cs.ubc.ca -L 1522:dbhost.ugrad.cs.ubc.ca:1522

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
}
