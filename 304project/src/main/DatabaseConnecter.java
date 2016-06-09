package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DatabaseConnecter
{
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
	
	public Object[][] getPersonnelTable()
	{
		ResultSet rs;
		List<Object[]> data = new LinkedList<Object[]>();
		
		try {
			rs = getPersonnel();

			while(rs.next())
			{
				Object[] row = {rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getLong(5)};
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object[][] table=new Object[data.size()][];
		return data.toArray(table);
	}
}
