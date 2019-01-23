package code.challenge.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to represent the player that always choose randomly (the user)
 *
 */
public final class Player{
	
	@JsonIgnore
	private String playerIP;
	
	private int wins;
	
	private int defeats;
	
	private int draws;

	private List<RoundInfo> roundsInfo;

	public Player(String playerIP) {
		this.playerIP = playerIP;
		this.roundsInfo = new ArrayList<>();
		this.wins = 0;
		this.defeats = 0;
		this.draws = 0;
	}

	public int getRounds() {
		return wins + defeats + draws;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDefeats() {
		return defeats;
	}

	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public String getPlayerIP() {
		return playerIP;
	}

	public List<RoundInfo> getRoundsInfo() {
		return roundsInfo;
	}

	public void setRoundsInfo(List<RoundInfo> roundsInfo) {
		this.roundsInfo = roundsInfo;
	}

	public void increaseWins() {
		this.wins++;
	}

	public void increaseDefeats() {
		this.defeats++;
	}

	public void increaseDraws() {
		this.draws++;
	}

}
