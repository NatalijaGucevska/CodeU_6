import static org.junit.Assert.*;

import java.util.ArrayList;
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
	public void testMinimalMovesWithGivenExample() {
		int[] initialOrder = { 1, 2, 0, 3 };
		int[] finalOrder = { 3, 1, 2, 0 };

		List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);

		assertTrue(isValidSolution(initialOrder, finalOrder, moves));
		assertEquals(3, moves.size());
	}

	@Test
	public void testMinimalMovesWithSameEmptySpot() {
		int[] initialOrder = { 1, 0, 2 };
		int[] finalOrder = { 2, 0, 1 };

		List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);

		assertTrue(isValidSolution(initialOrder, finalOrder, moves));
		assertEquals(3, moves.size());
	}

	@Test
	public void testMinimalMovesWithTwoSwaps() {
		int[] initialOrder = { 1, 2, 0, 3, 4 };
		int[] finalOrder = { 2, 1, 0, 4, 3 };

		List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);

		assertTrue(isValidSolution(initialOrder, finalOrder, moves));
		assertEquals(6, moves.size());
	}

	@Test
	public void testRearrangementMoves() {
		int[] initialOrder = createListWithValuesFromRange(0, 1000);
		int[] finalOrder = createListWithValuesFromRange(0, 1000);

		List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);

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
	private boolean isValidSolution(int[] initialOrder, int[] finalOrder, List<Move> moves) {
		List<Integer> initialOrderList = arrayToList(initialOrder);
		List<Integer> finalOrderList = arrayToList(finalOrder);

		for (Move move : moves) {
			// Make sure that there is no move from and to the same position
			if (move.getInitialPosition() == move.getFinalPosition()) {
				return false;
			}
			// Moving "nothing" is invalid move
			if (initialOrderList.get(move.getInitialPosition()) == 0) {
				return false;
			}
			// Moving to taken position is invalid move
			if (initialOrderList.get(move.getFinalPosition()) != 0) {
				return false;
			}
			Collections.swap(initialOrderList, move.getInitialPosition(), move.getFinalPosition());
		}
		return initialOrderList.equals(finalOrderList);
	}

	/**
	 * Creates randomly ordered array of values in a given range.
	 * 
	 * @param start
	 *            - starting value
	 * @param finish
	 *            - ending value
	 * @return - array of randomly ordered values
	 */
	private int[] createListWithValuesFromRange(int start, int finish) {
		List<Integer> list = new ArrayList<>();
		for (int i = start; i < finish; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return listToArray(list);
	}

	/**
	 * Converts list to array preserving the order of elements
	 */
	private int[] listToArray(List<Integer> list) {
		int[] array = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * Converts array to list preserving the order of elements
	 */
	private List<Integer> arrayToList(int[] array) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
}
