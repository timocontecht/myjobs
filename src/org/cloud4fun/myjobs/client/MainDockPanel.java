package org.cloud4fun.myjobs.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;


public class MainDockPanel extends Composite {
	
	static private MainDockPanel instance = null;
	
	static public MainDockPanel getInstance()
	{
		if (instance == null)
			instance = new MainDockPanel();
		return instance;
	}
	
	private DockLayoutPanel thePanel;
	private MainMenu mainMenu;
	private TaskPanel taskPanel = null;
	
	private MainDockPanel()
	{
		thePanel = new DockLayoutPanel(Unit.EM);
		thePanel.addWest(mainMenu.getInstance(), 20);
		
		this.initWidget(thePanel);
	}
	
	public void addTaskPanel(TaskPanel tp)
	{
		if (taskPanel != null)
			thePanel.remove(taskPanel);
		
		taskPanel = tp;
		thePanel.add(taskPanel);
	}
	
	
	

}
