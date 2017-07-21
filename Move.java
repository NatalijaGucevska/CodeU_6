public class Move {
	public static int PARKING_SIZE = -1;

	private int from;
	private int to;

	public Move(int from, int to) {
		if (PARKING_SIZE < 0) {
			throw new IllegalStateException("You can't set a Move without defining the parking size.");
		}
		if (from < 0 || to < 0 || from >= PARKING_SIZE || to >= PARKING_SIZE) {
			throw new IllegalArgumentException("The bounderies of the parking are not respected");
		}
		this.from = from;
		this.to = to;
	}

	public int getInitialPosition() {
		return from;
	}

	public int getMovePosition() {
		return to;
	}

	public static void setParkingSize(int parkingSize) {
		PARKING_SIZE = parkingSize;
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof Move)) {
			return false;
		}
		return this.getInitialPosition() == ((Move) that).getInitialPosition()
				&& this.getMovePosition() == ((Move) that).getMovePosition();
	}

	@Override
	public String toString() {
		return String.format("Moved from %d to %d", getInitialPosition(), getMovePosition());
	}
}
