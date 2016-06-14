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
