package org.cloud4fun.myjobs.client;



import java.util.List;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.ReportDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;


public class ReportPanel extends ScrollPanel {
	
	public ReportPanel()
	{
		
	}
	
	public ReportPanel(ReportDTO reportList)
	{
		this.reportList = reportList;
		buildGrid();
	}
	
	private void buildGrid()
	{
		
		int row_size = reportList.getProjects().size() + 1;
		this.add(theGrid);
		theGrid.clear();
		theGrid.resize(row_size, 2	);
		
		
		// add header row
		theGrid.setText(0, 0,  "Project");
		theGrid.setText(0, 1, "Worked Units");
		
		
		// add task rows
		for(int i=0; i<reportList.getProjects().size(); i++)
		{
			theGrid.setText(i+1, 0, reportList.getProjects().get(i));
			theGrid.setText(i+1, 1, reportList.getUnits().get(i).toString());
		}
	}

	
	ReportDTO reportList; 
	private static Grid theGrid = new Grid();
}
