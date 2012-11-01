package org.cloud4fun.myjobs.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.ReportDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;



public class ReportMenu extends VerticalPanel 
{
	public ReportMenu()
	{
		this.setWidth("20em");
		
		// add radio buttons for the reports
		dailySummary_rb = new RadioButton("reportGroup", "Daily Summary");
		dailySummary_rb.addClickHandler(new ReportClickHandler());
		add(dailySummary_rb);
		
		weeklySummary_rb = new RadioButton("reportGroup", "Weekly Summary");
		weeklySummary_rb.addClickHandler(new ReportClickHandler());
		add(weeklySummary_rb);
		
		periodSummary_rb = new RadioButton("reportGroup", "Period Summary");
		periodSummary_rb.addClickHandler(new ReportClickHandler());
		add(periodSummary_rb);
	}
	
	private void createReport(ReportDTO.ReportType rt)
	{
		
		
		 if (service == null)
			 service = GWT.create(MyJobsService.class);
		 
		 AsyncCallback<ReportDTO> callback = new AsyncCallback<ReportDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ReportDTO result) 
			{
				ReportPanel panel = new ReportPanel(result);
				MainDockPanel.getInstance().addReportPanel(panel);
			}
			 		
		};
		
		service.getReport(rt, callback);
	}
	
	
	
	public class ReportClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {

			if (event.getSource() == dailySummary_rb)
			{
				createReport(ReportDTO.ReportType.DAILY);
			}
			
			if (event.getSource() == weeklySummary_rb)
			{
				createReport(ReportDTO.ReportType.WEEKLY);
			}
			
			if (event.getSource() == periodSummary_rb)
			{
				PeriodReportPanel panel = new PeriodReportPanel();
				MainDockPanel.getInstance().addPanel(panel);
			}
			
			
		}
		
	}
	
	
	MyJobsServiceAsync service = null;
	RadioButton dailySummary_rb;
	RadioButton weeklySummary_rb;
	RadioButton periodSummary_rb;
	
	
}
