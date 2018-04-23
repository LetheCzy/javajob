package controller;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config.xml", "classpath:spring-mvc.xml" })

@WebAppConfiguration
public class JobControllerTest {

	protected MockMvc mockMvc;

	@Autowired 
	protected WebApplicationContext wac;

	@Before //这个方法在每个方法执行之前都会执行一遍
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
	}

	@Test
	public void testGetjoblistpage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetjobdetailStringModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testCron() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetjoblist() throws UnsupportedEncodingException, Exception {
		String responseString = mockMvc.perform
		           (
		        		   MockMvcRequestBuilders.get("/job/getjoblist")          //请求的url,请求的方法是get
		               //get("/user/showUser2")          //请求的url,请求的方法是get
		               .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
		               //.param("id","1")   //添加参数(可以添加多个)
		               //.param("id","3")   //添加参数(可以添加多个)
		           )
		           .andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200
		           //.andDo(MockMvcResultMatchers)         //打印出请求和相应的内容
		           .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
		       System.out.println("-----返回的json = " + responseString);
		       
//		       mockMvc.perform(
//		                (post("/email/send")
//		                        .contentType(MediaType.APPLICATION_JSON).content(json)))
//		                .andExpect(status().isOk()).andDo(print());
		       
		fail("Not yet implemented");
	}

	@Test
	public void testGetjobdetailString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavejobdata() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeJobStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskeFireTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExsitJob() {
		fail("Not yet implemented");
	}

}
