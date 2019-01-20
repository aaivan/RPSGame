package code.challenge.entities;

import java.util.Random;

public enum Shape {

	ROCK, PAPER, SCISSORS;

	private static final Shape[] VALUES = values();

	private static final int SIZE = VALUES.length;

	private static final Random RANDOM = new Random();

	/**
	 * Get a random shape
	 * 
	 * @return ROCK, PAPER or SCISSORS
	 */
	public static Shape getRandomShape() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}

	/**
	 * Simulates game by returning an integer value depending on the shape
	 * chosen by player
	 * 
	 * @return 0 if shape is Rock (draw), 1 if shape is Paper (player 1 wins), 2
	 *         if shape is Scissors (player 2 wins)
	 */
	public int play() {
		switch (this) {
		case ROCK:
			return 0;
		case PAPER:
			return 1;
		case SCISSORS:
			return 2;
		default:
			// Not possible case
			return -1;
		}
	}

}
