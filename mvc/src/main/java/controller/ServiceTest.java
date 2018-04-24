package controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import czy.site.model.myjob;
import czy.site.service.jobservice;

public class ServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String[] configs = new String[] { "classpath:config.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
		jobservice sv = (jobservice) ctx.getBean("jobservice");

		myjob model = new myjob();
		List<myjob> list = sv.selectByCondition(model);
		
		assertNotNull(ctx);
		assertNotNull(sv);
		fail("Not yet implemented");
	}

}
