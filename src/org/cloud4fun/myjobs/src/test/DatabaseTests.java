package org.cloud4fun.myjobs.src.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloud4fun.myjobs.server.MyJobsServiceImpl;
import org.cloud4fun.myjobs.server.hibernate.Task;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;
import org.junit.Test;

public class DatabaseTests {

	@Test
	public void TaskTest()
	{
		MyJobsServiceImpl impl = new MyJobsServiceImpl();
		List<Task> tasks = impl.getDBTasks();
		
		assertNotNull(tasks);
	}
	
	@Test
	public void TaskText2()
	{
		MyJobsServiceImpl impl = new MyJobsServiceImpl();
		List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
		
		ProjectDTO project1 = new ProjectDTO(6, "Health");
		projects.add(project1);
		ProjectDTO project2 = new ProjectDTO(7, "Supervision");
		projects.add(project2);
		
		List<TaskDTO> tasks = impl.getTasks(projects);
		
		assertNotNull (tasks);
		assertEquals(tasks.size(), 6);
	}
	
	@Test 
	public void WorkUnitTest()
	{
		TaskDTO t = new TaskDTO();
		t.setId(22);
		
		MyJobsServiceImpl impl = new MyJobsServiceImpl();
		impl.addWorkUnit(t);
	}
	
	@Test
	public void dailySummary()
	{
		MyJobsServiceImpl impl = new MyJobsServiceImpl();
		impl.dailySummaryDB();
	}
	
	
}
