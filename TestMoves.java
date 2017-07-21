import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestMoves {
	int validParkingSize = 25;

	@Before
	public void setup() {
		Move.setParkingSize(validParkingSize);
	}

	@Test
	public void testCreateMove() {
		new Move(6, 5);
	}

	/**********
	 * Tests with out of the parking range arguments
	 ********************/
	@Test(expected = IllegalArgumentException.class)
	public void testCreateMoveWithNegativeStartPos() {
		new Move(-6, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMoveWithNegativeTargetPos() {
		new Move(6, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMoveWithBiggerStartPos() {
		new Move(70, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMoveWithBiggerTargetPos() {
		new Move(5, 70);
	}

	/********** Tests with valid arguments ********************/
	@Test
	public void testGetInitialPos() {
		Move move = null;
		for (int i = 0; i < Move.PARKING_SIZE; i++) {
			move = new Move(i, 0);
			assertEquals(i, move.getInitialPosition());

		}
	}

	@Test
	public void testGetMovePos() {
		Move move = null;
		for (int i = 0; i < Move.PARKING_SIZE; i++) {
			move = new Move(0, i);
			assertEquals(i, move.getMovePosition());
		}
	}

	@Test
	public void testGetBothPos() {
		Move move = null;
		for (int i = 0; i < Move.PARKING_SIZE; i++) {
			for (int j = 0; j < Move.PARKING_SIZE; j++) {
				move = new Move(i, j);
				assertEquals(i, move.getInitialPosition());
				assertEquals(j, move.getMovePosition());
			}
		}
	}

	/********** Test create invalid parking ****************/
	@Test(expected = IllegalArgumentException.class)
	public void testCreateParkingWithNegativeNoSpots() {
		new Parking(-5);
	}

	/********** Tests for valid parking , helper methods ********************/
	@Test
	public void testCreateValidParking() {
		new Parking(validParkingSize);
	}

	@Test
	public void testMoveCarFreePlace() {
		Parking parking = new Parking(validParkingSize);
		List<Move> expectedMoves = new ArrayList<Move>();
		expectedMoves.add(new Move(0, 5));
		assertEquals(expectedMoves, parking.move(5, 0, 5));
	}

	@Test
	public void testMoveCarNoNeedToMove() {
		Parking parking = new Parking(validParkingSize);
		List<Move> expectedMoves = new ArrayList<Move>();

		assertEquals(expectedMoves, parking.move(5, 5, 0));
	}

	@Test
	public void testMoveCarWithTwoMoves() {
		Parking parking = new Parking(validParkingSize);
		List<Move> expectedMoves = new ArrayList<Move>();
		expectedMoves.add(new Move(5, 2));
		expectedMoves.add(new Move(3, 5));

		assertEquals(expectedMoves, parking.move(5, 3, 2));
	}

	@Test
	public void testCreateElementMapPosition() {
		Parking parking = new Parking(validParkingSize);
		Map<Integer, Integer> map = new HashMap<>();
		map.put(2, 0);
		map.put(1, 1);
		map.put(3, 2);
		map.put(0, 3);

		assertEquals(map, parking.elementMapPosition(new int[] { 2, 1, 3, 0 }));
	}

	/********** Tests for valid parking ********************/
	// Test from assignment example
	@Test
	public void testFindCorrectMoves() {
		Parking parking = new Parking(4);

		int[] initialOrder = new int[] { 1, 2, 0, 3 };
		int[] targetOrder = new int[] { 3, 1, 2, 0 };

		List<Move> moves = new ArrayList<>();
		moves.add(new Move(0, 2));
		moves.add(new Move(3, 0));
		moves.add(new Move(1, 3));
		moves.add(new Move(2, 1));
		moves.add(new Move(3, 2));

		assertEquals(moves, parking.reorderMoves(initialOrder, targetOrder));

	}

	@Test
	public void testFindOneMove() {
		Parking parking = new Parking(11);

		int[] initialOrder = new int[] { 1, 2, 0, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] targetOrder = new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8, 9, 10 };

		List<Move> moves = new ArrayList<>();
		moves.add(new Move(5, 2));

		assertEquals(moves, parking.reorderMoves(initialOrder, targetOrder));
	}

	@Test
	public void testNoMovesOnEmptyParking() {
		Parking parking = new Parking(11);

		int[] initialOrder = new int[] { 0 };
		int[] targetOrder = new int[] { 0 };

		List<Move> moves = new ArrayList<>();

		assertEquals(moves, parking.reorderMoves(initialOrder, targetOrder));

	}

	@Test
	public void testForInvalidMoves() {
		Parking parking = new Parking(15);

		int[] initialOrder = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		int[] targetOrder = new int[] { 14, 1, 3, 2, 0, 5, 6, 7, 8, 9, 11, 12, 10, 13, 4 };
		List<Move> moves = parking.reorderMoves(initialOrder, targetOrder); 
	
		for(Move m: moves) {
			assertThat(m.getInitialPosition(), not(equalTo(m.getMovePosition()))); 		
		}
	}
}
