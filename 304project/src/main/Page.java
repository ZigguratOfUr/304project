package main;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public abstract class Page
{
	GUIMain mainComponent;
	DatabaseConnecter dc;
	
	public Page(GUIMain mainComponent, DatabaseConnecter dc)
	{
		this.mainComponent = mainComponent;
		this.dc = dc;
	}

	public abstract void createPage();
	
	public abstract void cleanPage();

	public JButton createButton(JButton btn, String btn_name, String actionCommand) {
    	btn = new JButton(btn_name);
        btn.setVerticalTextPosition(AbstractButton.BOTTOM);
        btn.setHorizontalTextPosition(AbstractButton.CENTER);
        btn.setActionCommand(actionCommand);
        btn.addActionListener(mainComponent);
        mainComponent.add(btn);
        return btn;
	}

	
}
