package org.cloud4fun.myjobs.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.StackLayoutPanel;



public class MainMenu extends Composite
{
	static private MainMenu instance = null;
	
	static public MainMenu getInstance()
	{
		if (instance == null)
			instance = new MainMenu();
		return instance;
	}
	
	private StackLayoutPanel panel;
	private MainMenu()
	{
		panel = new StackLayoutPanel(Unit.EM);
		
		panel.setSize("20em", "100%");
		panel.add( new ProjectsPanel(), "Projects", 4);
		panel.add(new HTML("that"), new HTML("[that]"), 4);
		panel.add(new HTML("the other"), new HTML("[the other]"), 4);
		
		initWidget(panel);
	}
	
	
	
}
