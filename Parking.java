import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking {

	int parkingPlaces = 0;

	public Parking(int parkingPlaces) {
		if (parkingPlaces <= 0) {
			throw new IllegalArgumentException("The number of parking places must be a positive natural number!");
		}
		this.parkingPlaces = parkingPlaces;
	}

	/**
	 * 
	 * @param initialOrder
	 *            - The initial order of the cars in the parking
	 * @param targetOrder
	 *            - The target order of the cars in the parking
	 * @return - The moves that have to be done in order to reach the tarket
	 *         parking order
	 */
	public List<Move> reorderMoves(int[] initialOrder, int[] targetOrder) {
		// Mapping element -> initial position
		Map<Integer, Integer> initial = elementMapPosition(initialOrder);

		List<Move> moves = new ArrayList<>();
		for (int i = 0; i < initialOrder.length; i++) {
			int shouldBeHere = targetOrder[i];
			int itsCurrentPosition = initial.get(shouldBeHere);
			int currentlyOnThatPosition = initialOrder[i];
			int emptyPlace = initial.get(0);
			if (i != itsCurrentPosition) {
				moves.addAll(move(i, itsCurrentPosition, emptyPlace));
				// Put the car from the target position on empty spot
				initialOrder[emptyPlace] = currentlyOnThatPosition;
				initial.replace(currentlyOnThatPosition, emptyPlace);
				// Put the target car on the target position
				initialOrder[i] = shouldBeHere;
				initial.replace(shouldBeHere, i);
				// The current position of the target car remains empty
				initialOrder[itsCurrentPosition] = 0;
				initial.replace(0, itsCurrentPosition);

			}
		}
		return moves;
	}

	/**
	 * 
	 * @param from
	 *            - The current position of the car
	 * @param to
	 *            - The target position of the car
	 * @param emptyPlace
	 *            - The free slot to be used for temporary transition
	 * @return - List of 2 moves to be done to move the car to it's correct
	 *         position
	 */
	public List<Move> move(int from, int to, int emptyPlace) {
		List<Move> moves = new ArrayList<Move>();

		if (from != to) {
			if (from != emptyPlace) {
				moves.add(new Move(from, emptyPlace));
			}
			if (to != emptyPlace) {
				moves.add(new Move(to, from));
			}
		}
		return moves;
	}

	/**
	 * 
	 * @param array
	 *            - the array to convert in element -> index style
	 * @return - element -> index map
	 */
	public Map<Integer, Integer> elementMapPosition(int[] array) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < array.length; i++) {
			map.put(array[i], i);
		}
		return map;
	}
}
