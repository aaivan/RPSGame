package code.challenge.entities;


/**
 * Class to store all the rounds data
 *
 */
public class ResultsData {
	
	private Player player;
	
	private RoundsCounter roundsCounter;

	public RoundsCounter getRoundsCounter() {
		return roundsCounter;
	}

	public void setRoundsCounter(RoundsCounter roundsCounter) {
		this.roundsCounter = roundsCounter;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player playerOne) {
		this.player = playerOne;
	}

}
