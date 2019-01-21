package code.challenge.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import code.challenge.common.ApplicationConstants;
import code.challenge.entities.Player;
import code.challenge.entities.ResultsData;
import code.challenge.entities.RoundInfo;
import code.challenge.entities.RoundsCounter;
import code.challenge.entities.Shape;

/**
 * Test class for GameService
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = GameApplication.class)
public class GameServiceTest {

	 @Autowired
	 GameService service;

	@Test
	public void shouldReturnFilledResultsDataWithNewUser() {
		
		List<Player> players = initData();
		
		ResultsData resultsData = service
				.playRound(ApplicationConstants.USER_IP_2);
		
		Player player = resultsData.getPlayer();
		assertEquals(1, player.getRounds());
		assertTrue(players.contains(player));
		
		List<RoundInfo> roundsInfo = player.getRoundsInfo();
		assertEquals(1, roundsInfo.size());
		
		RoundInfo roundInfo = roundsInfo.get(0);
		assertEquals(Shape.ROCK.name(), roundInfo.getPlayerTwoShape());
		
		RoundsCounter roundsCounter = resultsData.getRoundsCounter();
		assertEquals(10, roundsCounter.getTotalRounds());
		
		testPlayerAndRoundInfoData(player, roundInfo, roundsCounter, 1, 1, 1);
	}

	@Test
	public void shouldReturnUpdatedResultsDataWithOldUser() {
		
		List<Player> players = initData();
		
		ResultsData resultsData = service
				.playRound(ApplicationConstants.USER_IP_1);
		
		Player player = resultsData.getPlayer();
		assertEquals(7, player.getRounds());
		assertTrue(players.contains(player));
		
		List<RoundInfo> roundsInfo = player.getRoundsInfo();
		assertEquals(2, roundsInfo.size());
		
		RoundInfo roundInfo = roundsInfo.get(1);
		assertEquals(Shape.ROCK.name(), roundInfo.getPlayerTwoShape());
		
		RoundsCounter roundsCounter = resultsData.getRoundsCounter();
		assertEquals(10, roundsCounter.getTotalRounds());
		
		testPlayerAndRoundInfoData(player, roundInfo, roundsCounter, 4, 3, 2);
		
	}
	
	@Test
	public void shouldResetResultsDataWithNewUser() {
		
		initData();
		
		ResultsData resultsData = service
				.resetGame(ApplicationConstants.USER_IP_2);
		
		Player player = resultsData.getPlayer();
		assertEquals(0, player.getRounds());
		assertEquals(0, player.getWins());
		assertEquals(0, player.getDefeats());
		assertEquals(0, player.getDraws());
		
		List<RoundInfo> roundsInfo = player.getRoundsInfo();
		assertEquals(0, roundsInfo.size());
		
		RoundsCounter roundsCounter = resultsData.getRoundsCounter();
		assertEquals(9, roundsCounter.getTotalRounds());
		assertEquals(5, roundsCounter.getTotalWinsPlayerOne());
		assertEquals(3, roundsCounter.getTotalWinsPlayerTwo());
		assertEquals(1, roundsCounter.getTotalDraws());
		
	}
	
	@Test
	public void shouldResetResultsDataWithOldUser() {
		
		initData();
		
		ResultsData resultsData = service
				.resetGame(ApplicationConstants.USER_IP_1);
		
		Player player = resultsData.getPlayer();
		assertEquals(0, player.getRounds());
		assertEquals(0, player.getWins());
		assertEquals(0, player.getDefeats());
		assertEquals(0, player.getDraws());
		
		List<RoundInfo> roundsInfo = player.getRoundsInfo();
		assertEquals(0, roundsInfo.size());
		
		RoundsCounter roundsCounter = resultsData.getRoundsCounter();
		assertEquals(9, roundsCounter.getTotalRounds());
		assertEquals(5, roundsCounter.getTotalWinsPlayerOne());
		assertEquals(3, roundsCounter.getTotalWinsPlayerTwo());
		assertEquals(1, roundsCounter.getTotalDraws());
		
	}
	
	private List<Player> initData() {
		Player storedPlayer = TestUtils.buildPlayer(
				ApplicationConstants.USER_IP_1, false);
		List<Player> players = new ArrayList<>();
		players.add(storedPlayer);
		
		GameService.setPlayers(players);
		GameService.setRoundsCounter(TestUtils.buildRoundsCounter());
		return players;
	}

	private void testPlayerAndRoundInfoData(Player player, RoundInfo roundInfo,
			RoundsCounter roundsCounter, int expectedWins, int expectedDefeats, int expectedDraws) {
		String playerShape = roundInfo.getPlayerOneShape();
		if(Shape.PAPER.name().equals(playerShape)) {
			assertEquals(ApplicationConstants.PLAYER_ONE_WINS_RESULT, roundInfo.getResult());
			assertEquals(expectedWins, player.getWins());
			assertEquals(6, roundsCounter.getTotalWinsPlayerOne());
		} else if(Shape.SCISSORS.name().equals(playerShape)) {
			assertEquals(ApplicationConstants.PLAYER_TWO_WINS_RESULT, roundInfo.getResult());
			assertEquals(expectedDefeats, player.getDefeats());
			assertEquals(4, roundsCounter.getTotalWinsPlayerTwo());
		} else {
			assertEquals(ApplicationConstants.DRAW_RESULT, roundInfo.getResult());
			assertEquals(expectedDraws, player.getDraws());
			assertEquals(2, roundsCounter.getTotalDraws());
		}
	}

}
