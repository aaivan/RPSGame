package code.challenge.manager;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import code.challenge.common.ApplicationConstants;
import code.challenge.entities.ResultsData;
import code.challenge.entities.Shape;

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
				.andExpect(jsonPath("$.player.wins", is(3)))
				.andExpect(jsonPath("$.player.defeats", is(2)))
				.andExpect(jsonPath("$.player.draws", is(1)))
				.andExpect(jsonPath("$.player.rounds", is(6)))
				.andExpect(jsonPath("$.player.roundsInfo.length()", is(1)))
				.andExpect(
						jsonPath("$.player.roundsInfo[0].playerOneShape",
								is(Shape.PAPER.name())))
				.andExpect(
						jsonPath("$.player.roundsInfo[0].playerTwoShape",
								is(Shape.ROCK.name())))
				.andExpect(
						jsonPath("$.player.roundsInfo[0].result",
								is(ApplicationConstants.PLAYER_ONE_WINS_RESULT)))
				.andExpect(
						jsonPath("$.roundsCounter.totalWinsPlayerOne", is(5)))
				.andExpect(
						jsonPath("$.roundsCounter.totalWinsPlayerTwo", is(3)))
				.andExpect(jsonPath("$.roundsCounter.totalDraws", is(1)))
				.andExpect(jsonPath("$.roundsCounter.totalRounds", is(9)));

	}

	@Test
	public void testPlayRoundFailure() throws Exception {

		mvc.perform(get("/play").with(request -> {
			request.setRemoteAddr(StringUtils.EMPTY);
			return request;
		}).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isUnauthorized());

	}

	@Test
	public void testResetSuccess() throws Exception {

		ResultsData resultsData = TestUtils.buildResultsData(true);

		when(service.playRound(any(String.class))).thenReturn(resultsData);

		mvc.perform(get("/play").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.player.wins", is(0)))
				.andExpect(jsonPath("$.player.defeats", is(0)))
				.andExpect(jsonPath("$.player.draws", is(0)))
				.andExpect(jsonPath("$.player.rounds", is(0)))
				.andExpect(jsonPath("$.player.roundsInfo.length()", is(0)))
				.andExpect(
						jsonPath("$.roundsCounter.totalWinsPlayerOne", is(5)))
				.andExpect(
						jsonPath("$.roundsCounter.totalWinsPlayerTwo", is(3)))
				.andExpect(jsonPath("$.roundsCounter.totalDraws", is(1)))
				.andExpect(jsonPath("$.roundsCounter.totalRounds", is(9)));

	}

	@Test
	public void testResetFailure() throws Exception {

		mvc.perform(get("/reset").with(request -> {
			request.setRemoteAddr(StringUtils.EMPTY);
			return request;
		}).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isUnauthorized());

	}

}
