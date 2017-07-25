import java.util.Arrays;
import java.util.Collections;
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
	public void printReorderMoves(List<Integer> initialOrder, List<Integer> targetOrder) {
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
	public List<Move> reorderMoves(List<Integer> initialOrder, List<Integer> targetOrder) {
		// Make copy of the initial order array
		List<Integer> initialOrderCopy = new ArrayList<>(initialOrder);
		// Mapping element -> initial position
		List<Integer> initial = elementMapPosition(initialOrderCopy);

		List<Move> moves = new ArrayList<>();
		for (int targetPos = 0; targetPos < initialOrderCopy.size(); targetPos++) {
			int targetCar = targetOrder.get(targetPos);
			int targetCarCurrPos = initial.get(targetCar);
			int currentlyOnTargetPos = initialOrderCopy.get(targetPos);
			int emptySpot = initial.get(0);

			if (targetPos != targetCarCurrPos && targetCar != 0) {
				moves.addAll(generateMoves(targetCarCurrPos, targetPos, emptySpot));
				// Put the car from the target position on empty spot
				initialOrderCopy.set(emptySpot, currentlyOnTargetPos);
				initial.set(currentlyOnTargetPos, emptySpot);
				// Put the target car on the target position
				initialOrderCopy.set(targetPos, targetCar);
				initial.set(targetCar, targetPos);
				// The current position of the target car remains empty
				initialOrderCopy.set(targetCarCurrPos, 0);
				initial.set(0, targetCarCurrPos);

			}
		}
		return moves;
	}

	/**
	 * Given it's current position, it's target position and an empty spot, this
	 * method generates a minimal number of moves needed for a car to be placed
	 * on it's target position.
	 * 
	 * @param currentPos
	 *            - The current position of the car
	 * @param targetPos
	 *            - The target position of the car
	 * @param emptySpot
	 *            - The free slot to be used for transition
	 * @return - List of at most 2 moves to be done in order to move the car to
	 *         it's target position. If the target position of the car is an
	 *         empty spot then only one Move is generated. Otherwise two moves
	 *         are generated (one to free the target spot, the other one to
	 *         place the car on the target spot).
	 */
	public List<Move> generateMoves(int currentPos, int targetPos, int emptySpot) {
		List<Move> moves = new ArrayList<Move>();

		if (targetPos != emptySpot) {
			moves.add(new Move(targetPos, emptySpot));
		}
		moves.add(new Move(currentPos, targetPos));

		return moves;
	}

	/**
	 * 
	 * @param array
	 *            - the array to convert in element -> index style
	 * @return - element -> index array
	 */
	public List<Integer> elementMapPosition(List<Integer> array) {
		List<Integer> list = new ArrayList<Integer>(Collections.nCopies(array.size(), 0));

		for (int i = 0; i < array.size(); i++) {
			list.add(array.get(i), i);
		}

		return list;
	}
}
