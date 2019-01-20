package code.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to represent the player that always choose randomly
 *
 */
public final class Player{
	
	@JsonIgnore
	private String playerIP;
	
	@JsonIgnore
	private Shape shape;
	
	private int wins;
	
	private int defeats;
	
	private int draws;

	private int playedRounds;

	public Player(String playerIP) {
		this.playerIP = playerIP;
	}

	public int getRounds() {
		return playedRounds;
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

	public void setRounds(int rounds) {
		this.playedRounds = rounds;
	}
	
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public String getPlayerIP() {
		return playerIP;
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

	public void increasePlayedRounds() {
		this.playedRounds++;
	}

}
