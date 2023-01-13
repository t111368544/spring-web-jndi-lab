package test;

import org.junit.Assert;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import bulletin.web.HomeController;

public class HomeControllerTest {
	
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		Assert.assertEquals("home", controller.home());
	}
	
	@Test
	public void testHomePage2() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc = 
				MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.view().name("home"));
	}
}
