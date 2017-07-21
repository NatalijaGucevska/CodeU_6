import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestMoves {

	@Before
	public void setup() {
		Move.setParkingSize(25);
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
		for(int i=0; i<Move.PARKING_SIZE; i++) {
			for(int j=0; j<Move.PARKING_SIZE; j++) {
				move = new Move(i, j); 
				assertEquals(i, move.getInitialPosition()); 
				assertEquals(j, move.getMovePosition()); 
			}
		}
	}
}
