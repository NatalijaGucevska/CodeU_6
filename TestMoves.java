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
        
        List<Integer> initialOrderList = new ArrayList<Integer>();
        List<Integer> finalOrderList = new ArrayList<Integer>();
        for(int i=0; i<initialOrder.length; i++){
            initialOrderList.add(initialOrder[i]);
            finalOrderList.add(finalOrder[i]);
        }
        
        assertTrue(isValidSolution(initialOrderList, finalOrderList, moves));
        assertEquals(3, moves.size());
    }
    
    @Test
    public void testMinimalMovesWithSameEmptySpot() {
        int[] initialOrder = { 1, 0, 2 };
        int[] finalOrder = { 2, 0, 1 };

        List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);
        
        List<Integer> initialOrderList = new ArrayList<Integer>();
        List<Integer> finalOrderList = new ArrayList<Integer>();
        for(int i=0; i<initialOrder.length; i++){
            initialOrderList.add(initialOrder[i]);
            finalOrderList.add(finalOrder[i]);
        }
        
        assertTrue(isValidSolution(initialOrderList, finalOrderList, moves));
        assertEquals(3, moves.size());
    }
    
    @Test
    public void testMinimalMovesWithTwoSwaps() {
        int[] initialOrder = { 1, 2, 0, 3, 4 };
        int[] finalOrder = { 2, 1, 0, 4, 3 };

        List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);
        
        List<Integer> initialOrderList = new ArrayList<Integer>();
        List<Integer> finalOrderList = new ArrayList<Integer>();
        for(int i=0; i<initialOrder.length; i++){
            initialOrderList.add(initialOrder[i]);
            finalOrderList.add(finalOrder[i]);
        }
        
        assertTrue(isValidSolution(initialOrderList, finalOrderList, moves));
        assertEquals(6, moves.size());
    }

    @Test
    public void testRearrangementMoves() {
        List<Integer> initialOrderList = new ArrayList<>(1000);
        List<Integer> finalOrderList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            initialOrderList.add(i);
            finalOrderList.add(i);
        }

        Collections.shuffle(initialOrderList);
        Collections.shuffle(finalOrderList);

        int[] initialOrder = new int[initialOrderList.size()];
        int[] finalOrder = new int[finalOrderList.size()];
        for(int i=0; i<initialOrderList.size(); i++){
            initialOrder[i] = initialOrderList.get(i);
            finalOrder[i] = finalOrderList.get(i);
        }
        
        List<Move> moves = parking.getReorderMoves(initialOrder, finalOrder);

        assertTrue(isValidSolution(initialOrderList, finalOrderList, moves));
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
