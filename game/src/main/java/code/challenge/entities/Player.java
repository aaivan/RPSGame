package code.challenge.entities;

/**
 * Class to represent the player that always choose randomly
 *
 */
public final class Player{
	
	private int roundsPlayed;
	
	private Shape shape;
	
	private int wins;
	
	private int defeats;
	
	private int draws;

	public int getRounds() {
		return roundsPlayed;
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
		this.roundsPlayed = rounds;
	}
	
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
