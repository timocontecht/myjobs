package org.cloud4fun.myjobs.src.test;

import static org.junit.Assert.*;

import java.util.List;

import org.cloud4fun.myjobs.server.MyJobsServiceImpl;
import org.cloud4fun.myjobs.server.hibernate.Task;
import org.junit.Test;

public class DatabaseTests {

	@Test
	public void TaskTest()
	{
		MyJobsServiceImpl impl = new MyJobsServiceImpl();
		List<Task> tasks = impl.getTasks();
		
		assertNotNull(tasks);
	}
}
