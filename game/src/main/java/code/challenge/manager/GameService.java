package code.challenge.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import code.challenge.entities.Player;
import code.challenge.entities.ResultsData;
import code.challenge.entities.RoundInfo;
import code.challenge.entities.RoundsCounter;
import code.challenge.entities.Shape;

@Service
public class GameService {

	private static List<Player> players = new ArrayList<>();

	private static List<RoundInfo> roundsInfo = new ArrayList<>();

	private RoundsCounter roundsCounter = RoundsCounter.getInstance();

	/**
	 * Run one round of the game
	 * 
	 * @param playerIP
	 * @return ResultsData object with the results of the round
	 */
	public ResultsData playRound(String playerIP) {

		Player player = getOrCreatePlayer(playerIP);

		ResultsData resultsData = new ResultsData();
		Shape randomShape = Shape.getRandomShape();

		player.setShape(randomShape);
		player.increasePlayedRounds();

		roundsInfo.add(buildRoundInfo(randomShape, player));

		resultsData.setPlayer(player);
		resultsData.setRoundsInfo(roundsInfo);
		return resultsData;
	}

	/**
	 * This method returns a player from players list if this contains a player
	 * with the provided IP, otherwise it adds a new player to players list and
	 * returns it
	 * 
	 * @param playerIP
	 *            the IP of the player
	 * @return Player
	 */
	private Player getOrCreatePlayer(String playerIP) {
		Optional<Player> storedPlayer = players.stream()
				.filter(p -> p.getPlayerIP().equals(playerIP)).findFirst();

		if (storedPlayer.isPresent()) {
			return storedPlayer.get();
		} else {
			Player newPlayer = new Player(playerIP);
			players.add(newPlayer);
			return newPlayer;
		}
	}

	/**
	 * Builds RoundInfo object depending on the shape provided
	 * 
	 * @param playerShape
	 *            player shape
	 * @param player
	 *            player
	 * @return RoundInfo object with round data
	 */
	private RoundInfo buildRoundInfo(Shape playerShape, Player player) {
		RoundInfo roundInfo = new RoundInfo();
		roundInfo.setPlayerOneShape(playerShape.name());

		int playResult = playerShape.play();
		String resultMessage = null;

		switch (playResult) {
		case 0:
			resultMessage = "Draw!";
			increaseDraws(player);
			break;
		case 1:
			resultMessage = "Player 1 Wins!";
			increasePlayerOneWins(player);
			break;
		case 2:
			resultMessage = "Player 2 Wins!";
			increasePlayerTwoWins(player);
			break;
		default:
			break;
		}

		roundInfo.setResult(resultMessage);
		return roundInfo;
	}

	/**
	 * Clear all the data of the player
	 * 
	 * @param playerIP
	 * @return ResultData object with empty roundsInfo and player counters
	 *         initialized to 0 and null shape;
	 */
	public ResultsData resetGame(String playerIP) {

		Player player = getOrCreatePlayer(playerIP);

		if (player.getRounds() > 0) {
			resetPlayer(player);
			roundsInfo.clear();
		}

		ResultsData resultsData = new ResultsData();
		resultsData.setPlayer(player);
		resultsData.setRoundsInfo(roundsInfo);

		return resultsData;
	}

	/**
	 * Reset player counters and shape
	 * 
	 * @param player
	 */
	private void resetPlayer(Player player) {
		player.setWins(0);
		player.setDefeats(0);
		player.setDraws(0);
		player.setRounds(0);
		player.setShape(null);
	}

	/**
	 * Increases in one unit player one and global player one wins count
	 * 
	 * @param player
	 */
	private void increasePlayerOneWins(Player player) {
		player.increaseWins();
		roundsCounter.addPlayerOneWin();
	}

	/**
	 * Increases in one unit player one defeats and global player two wins count
	 * 
	 * @param player
	 */
	private void increasePlayerTwoWins(Player player) {
		player.increaseDefeats();
		roundsCounter.addPlayerTwoWin();
	}

	/**
	 * Increases in one unit player and global draws count
	 * 
	 * @param player
	 */
	private void increaseDraws(Player player) {
		player.increaseDraws();
		roundsCounter.addDraw();
	}

}
