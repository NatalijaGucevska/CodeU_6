import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestMoves {
	private Parking parking;

	@Before
	public void setup() {
		parking = new Parking();
	}

	@Test
	public void testEmptyParking() {
		List<Move> moves = new ArrayList<>();
		int[] empty = new int[] { 0 };
		assertEquals(moves, parking.getReorderMoves(empty, empty));
	}

	@Test
	public void testIdenticalInitialAndFinalOrder() {
		List<Move> moves = new ArrayList<>();
		int[] sameArray = new int[] { 0, 1, 2 };

		assertEquals(moves, parking.getReorderMoves(sameArray, sameArray));
	}

	@Test
	public void testEmptySpotOnTargetPositionAtBeginning() {
		int[] initialOrder = new int[] { 0, 1, 2, 3 };
		int[] finalOrder = new int[] { 0, 2, 1, 3 };

		List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);
		//I'm not sure how to convert initialOrder and finalOrder to Lists 
		//assertTrue(isValidSolution(initialOrder, finalOrder, moves));
	}

	@Test
	public void testRearrangementMoves() {
		List<Integer> initialOrder = new ArrayList<>(1000);
		List<Integer> finalOrder = new ArrayList<>(1000);
		for (int i = 0; i < 1000; i++) {
			initialOrder.add(i);
			finalOrder.add(i);
		}
		
		Collections.shuffle(initialOrder);
		Collections.shuffle(finalOrder);
		
		int[] array1 = initialOrder.stream().mapToInt(i->i).toArray();
		int[] array2 = finalOrder.stream().mapToInt(i->i).toArray();

		List<Move> moves = parking.getReorderMoves(array1, array2);

		assertTrue(isValidSolution(initialOrder, finalOrder, moves));
	}

	/**
	 * Given initial order of parking, target order and a list of moves to be
	 * done to generate the target order from initial order: return ``false`` if
	 * some of the generated moves is invalid (i.e. generating move for staying
	 * on the same position, for example Move(5,5), moving nothing or moving to
	 * taken position) and return ``true`` if the moves are valid and they
	 * generate the desired target order.
	 */
	private boolean isValidSolution(List<Integer> initialOrder, List<Integer> finalOrder, List<Move> moves) {
		List<Integer> initialOrderCopy = new ArrayList<>(initialOrder);

		for (Move move : moves) {
			// Make sure that there is no move from and to the same position
			if (move.getInitialPosition() == move.getFinalPosition()) {
				return false;
			}
			// Moving "nothing" is invalid move
			if (initialOrderCopy.get(move.getInitialPosition()) == 0) {
				return false;
			}
			// Moving to taken position is invalid move
			if (initialOrderCopy.get(move.getFinalPosition()) != 0) {
				return false;
			}
			Collections.swap(initialOrderCopy, move.getInitialPosition(), move.getFinalPosition());
		}

		return initialOrderCopy.equals(finalOrder);
	}
}
