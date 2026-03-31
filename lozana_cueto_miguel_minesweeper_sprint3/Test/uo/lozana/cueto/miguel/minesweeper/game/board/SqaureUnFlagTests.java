package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class SqaureUnFlagTests {
/**
 * Scenarios:
 * 1− unflag on OPENED square
 * 2− unflag on CLOSED square 
 * 3− unflag on FLAGGED square 
 * */
	/**
	 * GIVEN: OPENED square
	 * WHEN: unflag()
	 * THEN: square remains open and return false
	 */
	@Test
	void OpenSquare() {
		
		Square sq = new Square(0,new NullAction());
		sq.open();
		assertFalse(sq.unflag());
		assertEquals(SquareState.OPENED, sq.getState()); 
		
	}
	/**
	 * GIVEN: CLOSED square
	 * WHEN: unflag()
	 * THEN: square remains CLOSED and return false
	 */
	@Test
	void ClossedSquare() { 
		Square sq = new Square(0,new NullAction());
		assertFalse(sq.unflag());
		assertEquals(SquareState.CLOSED, sq.getState());
		
	}
	/**
	 * GIVEN: FLAGGED square
	 * WHEN: unflag()
	 * THEN: square remains closed and return true
	 */
	@Test
	void FlaggedSquare() {
		Square sq = new Square(0,new NullAction());
		sq.flag();
		assertTrue(sq.unflag());
		
		assertEquals(SquareState.CLOSED, sq.getState());
		
	}

}