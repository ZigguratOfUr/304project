package main;

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
}
