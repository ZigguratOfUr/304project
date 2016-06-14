package main;

import java.sql.SQLException;

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

	}

}
