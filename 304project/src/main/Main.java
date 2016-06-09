package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Main
{
	private static DatabaseConnecter dc;
	
	public static void main(String[] args) throws SQLException
	{
		dc = new DatabaseConnecter();
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIMain.createAndShowGUI(dc);
            }
        });
        
//		ResultSet rs = dc.getPersonnelTable();
//		
//		// Initialize variables to store the data from DB
//		long id = 0;
//		String pname = "";
//		long phone = 0;
//		Date hireDate = null;
//		long airlineId = 0;
//		
//		while(rs.next()) {
//			// Get the column values
//			id = rs.getLong(1);
//			pname = rs.getString(2);
//		    phone = rs.getLong(3);
//			hireDate = rs.getDate(4);
//			airlineId = rs.getLong(5);
//			
//			// Print the result to console
//			System.out.println("Personnel id is: " + id);
//			System.out.println("Personnel name is; " + pname);
//			System.out.println("Personnel phone# is: " + phone);
//			System.out.println("Hire date is; " + hireDate);
//			System.out.println("Airline Id is: " + airlineId);
//		}
//		
//		rs.close();
	}

}
