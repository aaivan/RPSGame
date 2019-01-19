package code.challenge.entities;

import java.util.List;

import code.challenge.manager.RoundsPlayedCounter;

/**
 * Class to store all the rounds data
 *
 */
public class ResultsData {
	
	private Player player;
	
	private List<RoundInfo> roundsInfo;
	
	private int allUsersRoudsPlayed = RoundsPlayedCounter.getTotalRounds();
	
	public int getAllUsersRoudsPlayed() {
		return allUsersRoudsPlayed;
	}

	public void setAllUsersRoudsPlayed(int allUsersRoudsPlayed) {
		this.allUsersRoudsPlayed = allUsersRoudsPlayed;
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
