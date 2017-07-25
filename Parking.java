import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Parking {

	/**
	 * Given the initial order of cars in the parking and a target order, it it
	 * prints the Moves to be done in order to arrive from the initial to the
	 * target ordering of the cars.
	 * 
	 * @param initialOrder
	 *            - The initial order of the cars in the parking
	 * @param targetOrder
	 *            - The target order of the cars in the parking
	 */
	public void printReorderMoves(int[] initialOrder, int[] targetOrder) {
		List<Move> moves = reorderMoves(initialOrder, targetOrder);
		for (Move move : moves) {
			System.out.println(move.toString());
		}
	}

	/**
	 * Given the initial order of cars in the parking and a target order, it
	 * generates a list of Moves to be done in order to arrive from the initial
	 * to the target ordering of the cars.
	 * 
	 * @param initialOrder
	 *            - The initial order of the cars in the parking
	 * @param targetOrder
	 *            - The target order of the cars in the parking
	 * @return - The moves that have to be done in order to reach the target
	 *         parking order
	 */
	public List<Move> reorderMoves(int[] initialOrder, int[] targetOrder) {
		// Make copy of the initial order array
		int[] initialOrderCopy = Arrays.copyOf(initialOrder, initialOrder.length);
		// Mapping element -> initial position
		int[] initial = elementMapPosition(initialOrderCopy);

		List<Move> moves = new ArrayList<>();
		for (int i = 0; i < initialOrderCopy.length; i++) {
			int targetCar = targetOrder[i];
			int targetCarCurrPos = initial[targetCar];
			int currentlyOnTargetPos = initialOrderCopy[i];
			int emptySpot = initial[0];
			if (i != targetCarCurrPos && targetCar != 0) {
				moves.addAll(generateMoves(targetCarCurrPos, i, emptySpot));
				// Put the car from the target position on empty spot
				initialOrderCopy[emptySpot] = currentlyOnTargetPos;
				initial[currentlyOnTargetPos] = emptySpot;
				// Put the target car on the target position
				initialOrderCopy[i] = targetCar;
				initial[targetCar] = i;
				// The current position of the target car remains empty
				initialOrderCopy[targetCarCurrPos] = 0;
				initial[0] = targetCarCurrPos;

			}
		}
		return moves;
	}

	/**
	 * Given it's current position, it's target position and an empty spot, this
	 * method generates a minimal number of moves needed for a car to be placed
	 * on it's target position.
	 * 
	 * @param from
	 *            - The current position of the car
	 * @param to
	 *            - The target position of the car
	 * @param emptySpot
	 *            - The free slot to be used for transition
	 * @return - List of at most 2 moves to be done in order to move the car to
	 *         it's target position
	 */
	public List<Move> generateMoves(int from, int to, int emptySpot) {
		List<Move> moves = new ArrayList<Move>();

		if (to != emptySpot) {
			moves.add(new Move(to, emptySpot));
		}
		moves.add(new Move(from, to));

		return moves;
	}

	/**
	 * 
	 * @param array
	 *            - the array to convert in element -> index style
	 * @return - element -> index array
	 */
	public int[] elementMapPosition(int[] array) {
		int[] map = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			map[array[i]] = i;
		}

		return map;
	}
}
