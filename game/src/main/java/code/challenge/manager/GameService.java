package code.challenge.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import code.challenge.entities.Player;
import code.challenge.entities.ResultsData;
import code.challenge.entities.RoundInfo;
import code.challenge.entities.Shape;

@Service
public class GameService {

	private static Player player = new Player();

	private static List<RoundInfo> roundsInfo = new ArrayList<>();

	public ResultsData playRound() {
		// Increase all users rounds played
		RoundsPlayedCounter.addRound();

		ResultsData resultsData = new ResultsData();
		Shape randomShape = Shape.getRandomShape();

		player.setShape(randomShape);
		player.setRounds(player.getRounds() + 1);

		roundsInfo.add(buildRoundInfo(randomShape));

		resultsData.setPlayer(player);
		resultsData.setRoundsInfo(roundsInfo);
		return resultsData;
	}

	/**
	 * Builds RoundInfo object depending on the shape provided
	 * 
	 * @param randomShape
	 *            player shape
	 * @return RoundInfo object with round data
	 */
	private RoundInfo buildRoundInfo(Shape randomShape) {
		RoundInfo roundInfo = new RoundInfo();
		roundInfo.setPlayerOneShape(randomShape.name());
		String result;

		if (Shape.ROCK.equals(randomShape)) {
			result = "Draw";
			increaseDraws();
		} else if (Shape.PAPER.equals(randomShape)) {
			result = "Player 1 Wins!";
			increaseWins();
		} else {
			result = "Player 2 Wins!";
			increaseDefeats();
		}

		roundInfo.setResult(result);
		return roundInfo;
	}

	/**
	 * Resets all the data of the player rounds
	 * 
	 * @return ResultData object with empty roundsInfo and player counters
	 *         initialized to 0 and null shape;
	 */
	public ResultsData resetGame() {
		resetPlayer();
		roundsInfo.clear();

		ResultsData resultsData = new ResultsData();
		resultsData.setPlayer(player);
		resultsData.setRoundsInfo(roundsInfo);

		return resultsData;
	}

	/**
	 * Reset player counters and shape
	 */
	private void resetPlayer() {
		player.setWins(0);
		player.setDefeats(0);
		player.setDraws(0);
		player.setRounds(0);
		player.setShape(null);
	}

	/**
	 * Increases in one unit player wins count
	 */
	private void increaseWins() {
		player.setWins(player.getWins() + 1);
	}

	/**
	 * Increases in one unit player defeats count
	 */
	private void increaseDefeats() {
		player.setDefeats(player.getDefeats() + 1);
	}

	/**
	 * Increases in one unit player draws count
	 */
	private void increaseDraws() {
		player.setDraws(player.getDraws() + 1);
	}

}
