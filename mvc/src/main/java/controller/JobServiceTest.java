package controller;

import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import czy.site.model.myjob;
import czy.site.service.jobservice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config.xml"})

public class JobServiceTest {
	@Autowired
	private jobservice service;
	
	@Test
	public void testCron() {
		myjob jobObj = new myjob();
		
		List<myjob> t = service.selectByCondition(jobObj);
		
		assertNotNull(t);
	}
}
