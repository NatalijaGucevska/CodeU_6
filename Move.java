public class Move {

	private int from;
	private int to;

	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getInitialPosition() {
		return from;
	}

	public int getMovePosition() {
		return to;
	}

	@Override
	public String toString() {
		return String.format("Moved from %d to %d", getInitialPosition(), getMovePosition());
	}
}
