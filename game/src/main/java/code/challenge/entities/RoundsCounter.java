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

	public RoundsCounter() {
		// Initialize users map and counters
		totalWinsPlayerOne = 0;
		totalWinsPlayerTwo = 0;
		totalDraws = 0;
	}

	public int getTotalWinsPlayerOne() {
		return totalWinsPlayerOne;
	}

	public void setTotalWinsPlayerOne(int totalWinsPlayerOne) {
		this.totalWinsPlayerOne = totalWinsPlayerOne;
	}

	public void setTotalWinsPlayerTwo(int totalWinsPlayerTwo) {
		this.totalWinsPlayerTwo = totalWinsPlayerTwo;
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
