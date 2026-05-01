package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class SqaureFlagTests {
	/**
	 * Scenarios: 
	 * 1− flag on OPENED square 
	 * 2− flag on CLOSED square 
	 * 3− flag on FLAGGED square 
	 */
	/**
	 * GIVEN: OPENED square
	 * WHEN: flag()
	 * THEN: Square stays opened and returns false
	 */
	@Test
	void OpenSquare() {
		Square sq = new Square(0,new NullAction()); 
		sq.open();
		
		boolean result = sq.flag();
		
		assertFalse(result);
		assertEquals(SquareState.OPENED, sq.getState());
	}
	/**
	 * GIVEN: CLOSED square
	 * WHEN: flag()
	 * THEN: Square becomes FLAGGED and returns true
	 */
	@Test
	void ClossedSquare() {
		Square sq = new Square(0,new NullAction());
		
		boolean result = sq.flag();
		
		assertTrue(result);
		assertEquals(SquareState.FLAGGED, sq.getState());
	}
	/**
	 * GIVEN: FLAGGED square
	 * WHEN: flag()
	 * THEN: Square stays flagged and returns false
	 */
	@Test
	void FlaggedSquare() {
		Square sq = new Square(0,new NullAction());
		sq.flag();
		
		boolean result = sq.flag();
		
		assertFalse(result);
		assertEquals(SquareState.FLAGGED, sq.getState());
	}

}