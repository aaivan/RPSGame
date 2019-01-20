package code.challenge.entities;

import java.util.List;

/**
 * Class to store all the rounds data
 *
 */
public class ResultsData {
	
	private Player player;
	
	private List<RoundInfo> roundsInfo;
	
	private RoundsCounter roundsCounter;

	public ResultsData() {
		roundsCounter = RoundsCounter.getInstance();
	}

	public RoundsCounter getRoundsPlayedCounter() {
		return roundsCounter;
	}

	public void setRoundsPlayedCounter(RoundsCounter roundsCounter) {
		this.roundsCounter = roundsCounter;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player playerOne) {
		this.player = playerOne;
	}

	public List<RoundInfo> getRoundsInfo() {
		return roundsInfo;
	}

	public void setRoundsInfo(List<RoundInfo> roundsInfo) {
		this.roundsInfo = roundsInfo;
	}

}
