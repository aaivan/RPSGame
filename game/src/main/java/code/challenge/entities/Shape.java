package code.challenge.entities;

import java.util.Random;

public enum Shape {

	ROCK, PAPER, SCISSORS;

	public String play() {
		switch (this) {
		case ROCK:
			return "Draw";
		case PAPER:
			return "Player 1 Wins!";
		case SCISSORS:
			return "Player 2 Wins!";
		default:
			return "Draw";
		}
	}

	private static final Shape[] VALUES = values();
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();

	public static Shape getRandomShape() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}

}
