package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.UIManager;
//import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class ViewFlightsPage extends Page implements ActionListener

{
	JButton b1, b2;
//	JButton[] buttons = new JButton[10000];
	JScrollPane scrollPane;
	
	public ViewFlightsPage(GUIMain mainComponent, DatabaseConnecter dc)
	{
		super(mainComponent, dc);
	}
	
	@Override
	public void createPage()
	{
//    	b1 = new JButton("Test table creation");
//        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
//        b1.setHorizontalTextPosition(AbstractButton.CENTER);
//        b1.setActionCommand("createPersonnelTable");
//        b1.addActionListener(this);
//
//        mainComponent.add(b1);
		
        
        b1 = new JButton("Back");
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("gotoExamplePage");
        b1.addActionListener(mainComponent);
        
        mainComponent.add(b1);
        
        b2 = new JButton("Buy ticket for scheduled flights");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("gotoBuyTicketsPage");
        b2.addActionListener(mainComponent);

        mainComponent.add(b2);
        
//        b3 = new JButton("Test View Flights");
//        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
//        b3.setHorizontalTextPosition(AbstractButton.CENTER);
//        b3.setActionCommand("createViewFlightsTable");
//        b3.addActionListener(this);
//        
//        mainComponent.add(b3);
        
        Object[][] data = dc.getViewFlightTable();
        
        JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
        
        // creating button column
//        TableColumn col = new TableColumn();
//        col.setHeaderValue("");
        
//       TableCellRenderer buttonRenderer = new ButtonRenderer();
//       table.getColumn("Buy").setCellRenderer(buttonRenderer);
//       table.getColumn("Buy").setCellRenderer(buttonRenderer);
//       table.addMouseListener(new MouseListener(table));
        
        
//        table.addColumn(col);
        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        table.setFillsViewportHeight(true);
        
        if (scrollPane!=null)
        {
        	mainComponent.remove(scrollPane);
        }
        
        scrollPane = new JScrollPane(table);
        
        mainComponent.add(scrollPane);
        mainComponent.revalidate();
        mainComponent.repaint();
//		}
	}

	@Override
	public void cleanPage()
	{
		mainComponent.remove(b1);
		mainComponent.remove(b2);
//		mainComponent.remove(b3);
		
		if (scrollPane!= null)
		{
			mainComponent.remove(scrollPane);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
//		if("createViewFlightsTable".equals(evt.getActionCommand()))
//		{
//        Object[][] data = dc.getViewFlightTable();
//        
//        JTable table = new JTable(data, DatabaseConnecter.VIEW_FLIGHT_TABLE_COLUMN_NAMES);
//        table.setPreferredScrollableViewportSize(new Dimension(600, 150));
//        table.setFillsViewportHeight(true);
//        
//        if (scrollPane!=null)
//        {
//        	mainComponent.remove(scrollPane);
//        }
//        
//        scrollPane = new JScrollPane(table);
//        
//        mainComponent.add(scrollPane);
//        mainComponent.revalidate();
//        mainComponent.repaint();
//		}

	}
//	private static class ButtonRenderer implements TableCellRenderer {
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
//			JButton button = (JButton)value;
//			if (selected) {
//				button.setForeground(table.getForeground());
//				button.setBackground(table.getSelectionBackground());
//			}
//			else{
//				button.setForeground(table.getForeground());
//				button.setBackground(UIManager.getColor("Button.background"));
//			}
//			return button;
//		}
//	}
	
//	private static class MouseListener extends MouseAdapter{
//		private final JTable table;
//		
//		public MouseListener(JTable table){
//			this.table = table;
//		}
//		
//		public void Click(MouseEvent e){
//			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
//			int row = e.getY()/table.getRowHeight();
//			
//			if(row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
//				Object value = table.getValueAt(row, column);
//				if(value instanceof JButton){
//					((JButton)value).doClick();
//				}
//			}
//		}
//	}
	
}
