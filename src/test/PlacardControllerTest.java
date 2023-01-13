package test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import bulletin.Placard;
import bulletin.data.PlacardDao;
import bulletin.web.PlacardController;

public class PlacardControllerTest {

	@Test
	public void testGetWithoutQueryParameter() throws Exception {
		List<Placard> expectedPlacards = createPlacardList(20);		
		PlacardDao mockDao = mock(PlacardDao.class);
		when(mockDao.findPlacards(PlacardController.DEFAULT_MAX_ID))
		.thenReturn(expectedPlacards);

		PlacardController controller = new PlacardController(mockDao);
		MockMvc mockMvc = 
			standaloneSetup(controller)
			.setSingleView(new InternalResourceView("/WEB-INF/views/placards.jsp"))
			.build();

		mockMvc.perform(get("/placards"))
				.andExpect(view().name("placards"))
				.andExpect(model().attributeExists("placardList"))
				.andExpect(model().attribute("placardList", expectedPlacards));
	}
	
	@Test
	public void testGetWithQueryParameter() throws Exception {
		List<Placard> expectedPlacards = createPlacardList(20);
		PlacardDao mockDao = mock(PlacardDao.class);
		when(mockDao.findPlacards(12345))
		.thenReturn(expectedPlacards);

		PlacardController controller = new PlacardController(mockDao);
		MockMvc mockMvc = 
			standaloneSetup(controller)
			.setSingleView(new InternalResourceView("/WEB-INF/views/placards.jsp"))
			.build();

		mockMvc.perform(get("/placards?max=12345"))
				.andExpect(view().name("placards"))
				.andExpect(model().attributeExists("placardList"))
				.andExpect(model().attribute("placardList", expectedPlacards));
	}



	@Test
	public void testPathVariable() throws Exception {
		Placard expectedPlacard = new Placard("Hello", new Date());
		PlacardDao mockDao = mock(PlacardDao.class);
		when(mockDao.findBy(12345)).thenReturn(expectedPlacard);

		PlacardController controller = new PlacardController(mockDao);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get("/placards/12345"))
				.andExpect(view().name("placard"))
				.andExpect(model().attributeExists("placard"))
				.andExpect(model().attribute("placard", expectedPlacard));
	}

	@Test
	public void savePlacard() throws Exception {
		PlacardDao mockDao = mock(PlacardDao.class);
		PlacardController controller = new PlacardController(mockDao);
		MockMvc mockMvc = standaloneSetup(controller).build();

		// this works, but isn't really testing what really happens
		mockMvc
		.perform(post("/placards").param("message", "Hello World"))
		.andExpect(redirectedUrl("/placards"));

		verify(mockDao, atLeastOnce())
		.save(new Placard(null, "Hello World", new Date()));
	}

	private List<Placard> createPlacardList(int count) {
		List<Placard> placards = new ArrayList<Placard>();
		for (int i = 0; i < count; i++) {
			placards.add(new Placard("Placard " + i, new Date()));
		}
		return placards;
	}
}
