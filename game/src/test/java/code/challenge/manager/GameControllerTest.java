package code.challenge.manager;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import code.challenge.entities.ResultsData;

/**
 * Test class for GameController
 *
 */
@RunWith(SpringRunner.class)
public class GameControllerTest {

	private MockMvc mvc;

	@Mock
	private GameService service;

	@InjectMocks
	private GameController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testPlayRoundSuccess() throws Exception {

		ResultsData resultsData = TestUtils.buildResultsData(false);

		when(service.playRound(any(String.class))).thenReturn(resultsData);

		mvc.perform(get("/play").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(forwardedUrl("index"))
				.andExpect(model().attributeExists("playerRounds"))
				.andExpect(
						model().attribute(
								"playerRounds", is(resultsData)));
		
        verify(service, times(1)).playRound(any(String.class));
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testPlayRoundServiceNotRun() throws Exception {

		mvc.perform(get("/play").with(request -> {
			request.setRemoteAddr(StringUtils.EMPTY);
			return request;
		}).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("index"))
		.andExpect(model().attributeDoesNotExist("playerRounds"));

		verify(service, times(0)).resetGame(any(String.class));
		verifyNoMoreInteractions(service);

	}

	@Test
	public void testResetSuccess() throws Exception {

		ResultsData resultsData = TestUtils.buildResultsData(true);

		when(service.resetGame(any(String.class))).thenReturn(resultsData);
		
		mvc.perform(get("/reset").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("index"))
		.andExpect(model().attributeExists("playerRounds"))
		.andExpect(
				model().attribute(
						"playerRounds", is(resultsData)));

		verify(service, times(1)).resetGame(any(String.class));
		verifyNoMoreInteractions(service);

	}

	@Test
	public void testResetServiceNotRun() throws Exception {

		mvc.perform(get("/reset").with(request -> {
			request.setRemoteAddr(StringUtils.EMPTY);
			return request;
		}).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("index"))
		.andExpect(model().attributeDoesNotExist("playerRounds"));

		verify(service, times(0)).resetGame(any(String.class));
		verifyNoMoreInteractions(service);

	}
	
	@Test
	public void testWrongMethodFailure() throws Exception {
		
		mvc.perform(get("/wrongPath").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}

	
}
