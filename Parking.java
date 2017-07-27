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
	public void printReorderMoves(List<Integer> initialOrder, List<Integer> targetOrder) {
		List<Move> moves = getReorderMoves(initialOrder, targetOrder);
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
	public List<Move> getReorderMoves(List<Integer> initialOrder, List<Integer> targetOrder) {
		// Mapping element -> initial position
		Map<Integer, Integer> misplacedElementIndexMap = getMisplacedElementIndexMap(initialOrder, targetOrder);

		List<Move> moves = new ArrayList<>();
		while (misplacedElementIndexMap.size() > 1) {
			int emptySpotPos = misplacedElementIndexMap.get(EMPTY_SPOT);
			int targetCar = targetOrder.get(emptySpotPos);
			int currentPos = misplacedElementIndexMap.get(targetCar);
			misplacedElementIndexMap.remove(EMPTY_SPOT);
			if (targetCar == EMPTY_SPOT) {
				currentPos = misplacedElementIndexMap.get(misplacedElementIndexMap.keySet().iterator().next());
				targetCar = initialOrder.get(currentPos);
				misplacedElementIndexMap.put(targetCar, emptySpotPos);
			} else {
				misplacedElementIndexMap.remove(targetCar);
			}
			misplacedElementIndexMap.put(EMPTY_SPOT, currentPos);
			moves.add(new Move(currentPos, emptySpotPos));
		}
		return moves;
	}

	/**
	 * Creates element -> index mapping between the misplaced
	 * elements. The mapping contains also element->index mapping 
	 * for the empty spot, regardless of the fact if it is on it's correct position 
	 * or not. 
	 * 
	 * @param array
	 *            - the array to convert in element -> index style
	 * @return - element -> index array
	 */
	private Map<Integer, Integer> getMisplacedElementIndexMap(List<Integer> initialOrder, List<Integer> targetOrder) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < initialOrder.size(); i++) {
			if (initialOrder.get(i) != targetOrder.get(i) || initialOrder.get(i) == EMPTY_SPOT) {
				map.put(initialOrder.get(i), i);
			}
		}
		return map;
	}
}
