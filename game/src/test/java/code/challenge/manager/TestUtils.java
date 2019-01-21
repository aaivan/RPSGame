package code.challenge.manager;

import java.util.ArrayList;
import java.util.List;

import code.challenge.common.ApplicationConstants;
import code.challenge.entities.Player;
import code.challenge.entities.ResultsData;
import code.challenge.entities.RoundInfo;
import code.challenge.entities.RoundsCounter;
import code.challenge.entities.Shape;

/**
 * Class with common methods for test classes
 *
 */
public class TestUtils {

	public static ResultsData buildResultsData(boolean isResetAction) {

		Player player = buildPlayer(ApplicationConstants.USER_IP_1,
				isResetAction);
		RoundsCounter roundsCounter = buildRoundsCounter();

		ResultsData resultsData = new ResultsData();
		resultsData.setPlayer(player);
		resultsData.setRoundsCounter(roundsCounter);

		return resultsData;
	}

	private static List<RoundInfo> buildRoundsInfo() {
		RoundInfo roundInfo = buildRoundInfo();
		List<RoundInfo> roundsInfo = new ArrayList<>();
		roundsInfo.add(roundInfo);
		return roundsInfo;
	}

	// @formatter:off
	/**
	 * Builds Rounds Counters considering the following case for two users
	 * playing:
	 * 
	 * User 1: 3 wins, 2 defeats, 1 draws
	 * User 2: 2 wins, 1 defeats, 0 draws
	 * 
	 * @return RoundsCounter
	 */
	// @formatter:on
	public static RoundsCounter buildRoundsCounter() {
		RoundsCounter roundsCounter = new RoundsCounter();

		roundsCounter.setTotalWinsPlayerOne(5);
		roundsCounter.setTotalWinsPlayerTwo(3);
		roundsCounter.addDraw();
		return roundsCounter;
	}

	public static Player buildPlayer(String playerIP, boolean isResetAction) {
		Player player = new Player(playerIP);

		if (isResetAction) {
			return player;
		}

		player.setRoundsInfo(buildRoundsInfo());
		player.setWins(3);
		player.setDefeats(2);
		player.setDraws(1);
		return player;
	}

	private static RoundInfo buildRoundInfo() {
		RoundInfo roundInfo = new RoundInfo();
		roundInfo.setPlayerOneShape(Shape.PAPER.name());
		roundInfo.setResult(ApplicationConstants.PLAYER_ONE_WINS_RESULT);
		return roundInfo;
	}

}
