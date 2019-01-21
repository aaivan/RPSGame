package code.challenge.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import code.challenge.common.ApplicationConstants;
import code.challenge.entities.Player;
import code.challenge.entities.ResultsData;
import code.challenge.entities.RoundInfo;
import code.challenge.entities.RoundsCounter;
import code.challenge.entities.Shape;

@Service
public class GameService {

	private static List<Player> players = new ArrayList<>();

	private static RoundsCounter roundsCounter = new RoundsCounter();

	/**
	 * Run one round of the game
	 * 
	 * @param playerIP
	 * @return ResultsData object with the results of the round
	 */
	public ResultsData playRound(String playerIP) {

		Player player = getOrCreatePlayer(playerIP);

		ResultsData resultsData = new ResultsData();

		player.getRoundsInfo().add(buildRoundInfo(Shape.getRandomShape(), player));

		resultsData.setPlayer(player);
		resultsData.setRoundsCounter(roundsCounter);
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
				.filter(player -> haveSamePlayerIP(player.getPlayerIP(), playerIP))
				.findFirst();

		if (storedPlayer.isPresent()) {
			return storedPlayer.get();
		}

		Player newPlayer = new Player(playerIP);
		players.add(newPlayer);
		return newPlayer;
	}

	/**
	 * Compare IPs
	 * 
	 * @param storedPlayerIP
	 * @param playerIP
	 * @return true if sotredPalyerIP and playerIP are equals, false otherwise
	 */
	private static boolean haveSamePlayerIP(String storedPlayerIP,
			String playerIP) {
		return storedPlayerIP.equals(playerIP);
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
			resultMessage = ApplicationConstants.DRAW_RESULT;
			increaseDraws(player);
			break;
		case 1:
			resultMessage = ApplicationConstants.PLAYER_ONE_WINS_RESULT;
			increasePlayerOneWins(player);
			break;
		case 2:
			resultMessage = ApplicationConstants.PLAYER_TWO_WINS_RESULT;
			increasePlayerTwoWins(player);
			break;
		default:
			break;
		}

		roundInfo.setResult(resultMessage);
		return roundInfo;
	}

	/**
	 * Clear all the data of the player.
	 * 
	 * @param playerIP
	 * @return ResultData object with empty roundsInfo and player counters
	 *         initialized to 0 and null shape;
	 */
	public ResultsData resetGame(String playerIP) {

		Player player = getOrCreatePlayer(playerIP);

		if (player.getRounds() > 0) {
			resetPlayer(player);
		}

		ResultsData resultsData = new ResultsData();
		resultsData.setPlayer(player);
		resultsData.setRoundsCounter(roundsCounter);

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
		player.getRoundsInfo().clear();
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

	public static List<Player> getPlayers() {
		return players;
	}

	public static void setPlayers(List<Player> players) {
		GameService.players = players;
	}

	public static RoundsCounter getRoundsCounter() {
		return roundsCounter;
	}

	public static void setRoundsCounter(RoundsCounter roundsCounter) {
		GameService.roundsCounter = roundsCounter;
	}

}
