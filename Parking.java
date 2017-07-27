import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parking {

	private final static int EMPTY_SPOT = 0;

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
		List<Move> moves = getReorderMoves(initialOrder, targetOrder);
		for (Move move : moves) {
			System.out.println(move);
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
	public List<Move> getReorderMoves(int[] initialOrder, int[] targetOrder) {
		Map<Integer, Integer> misplacedCarPositionMap = getMisplacedCarPositionMap(initialOrder, targetOrder);
		List<Move> moves = new ArrayList<>();

		while (misplacedCarPositionMap.size() > 1) {
			int emptySpotPos = misplacedCarPositionMap.get(EMPTY_SPOT);
			int targetCar = targetOrder[emptySpotPos];
			int currentPos = misplacedCarPositionMap.get(targetCar);
			misplacedCarPositionMap.remove(EMPTY_SPOT);

			if (targetCar == EMPTY_SPOT) {
				targetCar = misplacedCarPositionMap.keySet().iterator().next();
				currentPos = misplacedCarPositionMap.get(targetCar);
				misplacedCarPositionMap.put(targetCar, emptySpotPos);
			} else {
				misplacedCarPositionMap.remove(targetCar);
			}

			misplacedCarPositionMap.put(EMPTY_SPOT, currentPos);
			moves.add(new Move(currentPos, emptySpotPos));
		}
		return moves;
	}

	/**
	 * Creates car -> position mapping between the misplaced elements and their
	 * position. The mapping contains also element -> position mapping for the
	 * empty spot, regardless of the fact if it is on it's correct position or
	 * not.
	 * 
	 * @param initialOrder
	 *            - The initial order of the cars in the parking
	 * @param targetOrder
	 *            - The target order of the cars in the parking
	 * @return - car -> position map
	 */
	private Map<Integer, Integer> getMisplacedCarPositionMap(int[] initialOrder, int[] targetOrder) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < initialOrder.length; i++) {
			if (initialOrder[i] != targetOrder[i] || initialOrder[i] == EMPTY_SPOT) {
				map.put(initialOrder[i], i);
			}
		}
		return map;
	}
}
