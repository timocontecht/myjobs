package org.cloud4fun.myjobs.client;

import org.cloud4fun.myjobs.shared.TaskDTO;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;



public class TimerDlg extends DialogBox 
{
	TaskDTO task;
	Countdown cd;
	Button stop_btn;
	Label time_lbl;
	
	
	public TimerDlg(TaskDTO task)
	{
		this.task = task;
		cd = new Countdown();
		
		VerticalPanel p = new VerticalPanel();
		
		time_lbl = new Label("Time");
	
		
		stop_btn = new Button("Stop Working");
		stop_btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cd.cancel();
				hide();
			}
		});
		
		p.add(time_lbl);
		p.add(stop_btn);
		
		this.add(p);
		this.setText("Working on " + task.getTask());
	}
	
	public void show()
	{
		super.show();	
		super.center();
		cd.scheduleRepeating(1000);
	}
	
	
	public class Countdown extends Timer 
	{
        int count = 60 * 25; //25 minutes 
        MyJobsServiceAsync service = null;
        
        public void run() {
        	
        	String time_str = Integer.toString(count/60) + " : " + Integer.toString(count%60);
        	time_lbl.setText(time_str);
        	count--;
        	
        	
        	if(count==0) {
        		SoundController soundController = new SoundController();
        		Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_OGG_SPEEX,
        		        "37747__quilt__blues-for-the-masses-01.ogg");

        		sound.play();
        		hide();
	            this.cancel(); //cancel the timer -- important!
	            
	            // add worked unit to database
	            if (service == null)
					 service = GWT.create(MyJobsService.class);
				 
				AsyncCallback<String> callback = new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						
						
					}

					@Override
					public void onSuccess(String result) {
					
						Window.alert(result);
					}
					
				};
				
				service.addWorkUnit(task, callback);
        	}
        }
    };
}
