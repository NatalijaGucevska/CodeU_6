import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestMoves {
	Parking parking;

	@Before
	public void setup() {
		parking = new Parking();
	}

	@Test
	public void testEmptyParking() {
		List<Move> moves = new ArrayList<>();
		List<Integer> empty = Arrays.asList(new Integer[] { 0 }); 
		assertEquals(moves, parking.getReorderMoves(empty, empty));
	}

	@Test
	public void testIdenticalInitialAndFinalOrder() {
		List<Move> moves = new ArrayList<>();
		List<Integer> sameArray = Arrays.asList(new Integer[] { 0, 1, 2 }); 

		assertEquals(moves, parking.getReorderMoves(sameArray, sameArray));
	}

	@Test
	public void testRearrangementMoves() {
		List<Integer> initialOrder = new ArrayList<>(1000); 
		List<Integer> finalOrder = new ArrayList<>(1000);
		for(int i=0; i<1000; i++) {
			initialOrder.add(i); 
			finalOrder.add(i); 
		}	
		Collections.shuffle(initialOrder);
		Collections.shuffle(finalOrder);
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
	private boolean isValidSolution(List<Integer> initialOrder, List<Integer> finalOrder, List<Move> moves) {
		List<Integer> initialOrderCopy = new ArrayList<>(initialOrder);

		for (Move move : moves) {
			// Make sure that there is no move from and to the same position
			if (move.getInitialPosition() == move.getMovePosition()) {
				return false;
			}
			// Moving "nothing" is invalid move
			if (initialOrderCopy.get(move.getInitialPosition()) == 0) {
				return false;
			}
			// Moving to taken position is invalid move
			if (initialOrderCopy.get(move.getMovePosition()) != 0) {
				return false;
			}
			Collections.swap(initialOrderCopy, move.getInitialPosition(), move.getMovePosition());
		}

		return initialOrderCopy.equals(finalOrder);
	}
}
