package code.challenge.entities;


/**
 * Class to store and handle total number of rounds played by all users
 *
 */
public final class RoundsCounter {

	/**
	 * Number of rounds won by player one
	 */
	private int totalWinsPlayerOne;

	/**
	 * Number of rounds won by player two
	 */
	private int totalWinsPlayerTwo;

	/**
	 * Number of draws
	 */
	private int totalDraws;

	private static RoundsCounter instance = new RoundsCounter();

	private RoundsCounter() {
		// Initialize users map and counters
		totalWinsPlayerOne = 0;
		totalWinsPlayerTwo = 0;
		totalDraws = 0;
	}

	/**
	 * Every time this method is called an user is playing a round so we
	 * increase totalRounds count
	 * 
	 * @return the only instance of RoundsPlayedCounter
	 */
	public static RoundsCounter getInstance() {
		return instance;
	}

	public int getTotalWinsPlayerOne() {
		return totalWinsPlayerOne;
	}

	public void setTotalWinsPlayerOne(int totalWinsPlayerOne) {
		this.totalWinsPlayerOne = totalWinsPlayerOne;
	}

	/**
	 * 
	 * @return total rounds played
	 */
	public int getTotalRounds() {
		return totalWinsPlayerOne + totalWinsPlayerTwo + totalDraws;
	}

	/**
	 * Increases in one unit the rounds played counter
	 */
	public void addPlayerOneWin() {
		setTotalWinsPlayerOne(getTotalWinsPlayerOne() + 1);
	}

	/**
	 * Increases in one unit the rounds played counter
	 */
	public void addPlayerTwoWin() {
		totalWinsPlayerTwo++;
	}
	
	/**
	 * Increases in one unit the rounds played counter
	 */
	public void addDraw() {
		totalDraws++;
	}

	public int getTotalWinsPlayerTwo() {
		return totalWinsPlayerTwo;
	}

	public int getTotalDraws() {
		return totalDraws;
	}

}
