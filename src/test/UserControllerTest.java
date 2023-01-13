package test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import bulletin.User;
import bulletin.data.UserDao;
import bulletin.web.UserController;

public class UserControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		UserDao userDao = mock(UserDao.class);
		UserController controller = new UserController(userDao);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc
			.perform(get("/user/register"))
			.andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		UserDao userDao = mock(UserDao.class);
		User unsaved = new User("jim", "jim", "jim", "tseng", "jim@spring.test");
		User saved = new User(24L, "jim", "jim", "jim", "tseng", "jim@spring.test");
		when(userDao.save(unsaved)).thenReturn(saved);

		UserController controller = new UserController(userDao);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/user/register")
				.param("username", "jim")
				.param("password", "jim")
				.param("firstName", "jim")
				.param("lastName", "tseng")
				.param("email", "jim@spring.test"))
				.andExpect(redirectedUrl("/user/jim"));

		verify(userDao, atLeastOnce()).save(unsaved);
	}

	@Test
	public void shouldFailValidationWithNoData() throws Exception {
		UserDao userDao = mock(UserDao.class);
		UserController controller = new UserController(userDao);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/user/register")).andExpect(status().isOk()).andExpect(view().name("registerForm"))
				.andExpect(model().errorCount(5)).andExpect(model().attributeHasFieldErrors("user", "firstName",
						"lastName", "username", "password", "email"));
	}

}
