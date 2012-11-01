package org.cloud4fun.myjobs.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;


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
	private Panel centerPanel = null;
	
	private MainDockPanel()
	{
		thePanel = new DockLayoutPanel(Unit.EM);
		thePanel.addWest(mainMenu.getInstance(), 20);
		
		this.initWidget(thePanel);
	}
	
	public void addTaskPanel(TaskPanel tp)
	{
		if (centerPanel != null)
			thePanel.remove(centerPanel);
		
		centerPanel = tp;
		thePanel.add(centerPanel);
	}
	
	public void addReportPanel(ReportPanel rp)
	{
		if (centerPanel != null)
			thePanel.remove(centerPanel);
		
		centerPanel = rp;
		thePanel.add(centerPanel);
	}
	
	public void addPanel(Panel p)
	{
		if (centerPanel != null)
			thePanel.remove(centerPanel);
		
		centerPanel = p;
		thePanel.add(centerPanel);
	}
	

}
