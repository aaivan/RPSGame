package code.challenge.entities;

/**
 * Class to represent a row of the rounds info table
 *
 */
public class RoundInfo {
	
	private String playerOneShape;
	
	private final String playerTwoShape = Shape.ROCK.name();
	
	private String result;

	public String getPlayerOneShape() {
		return playerOneShape;
	}

	public void setPlayerOneShape(String playerOneShape) {
		this.playerOneShape = playerOneShape;
	}

	public String getPlayerTwoShape() {
		return playerTwoShape;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
