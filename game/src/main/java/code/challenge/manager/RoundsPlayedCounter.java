package code.challenge.manager;


/**
 * Class to store and handle the count of rounds played by all users
 *
 */
public final class RoundsPlayedCounter {

	/**
	 * Number of rounds played by all users
	 */
	private static int totalRounds;

	private static RoundsPlayedCounter instance = new RoundsPlayedCounter();

	private RoundsPlayedCounter() {
		// Initialize total rounds played
		totalRounds = 0;
	}

	/**
	 * 
	 * @return total rounds played
	 */
	public static int getTotalRounds() {
		return totalRounds;
	}

	/**
	 * Every time this method is called an user is playing a round so we
	 * increase totalRounds count
	 * 
	 * @return the only instance of RoundsPlayedCounter
	 */
	public static RoundsPlayedCounter getInstance() {
		return instance;
	}
	
	/**
	 * Increases in one unit the rounds played counter
	 */
	public static void addRound() {
		totalRounds++;
	}

}
