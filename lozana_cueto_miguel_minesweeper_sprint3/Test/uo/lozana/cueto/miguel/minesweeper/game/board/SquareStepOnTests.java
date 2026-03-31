package uo.lozana.cueto.miguel.minesweeper.game.board;



import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.BlowUpAction;
import uo.lozana.cueto.miguel.minesweeper.game.board.actions.ClearAction;
import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;







class SquareStepOnTests {
	/**
	 * Scenarios:
	 * 1− stepOn on opened square
	 * 2− stepOn on CLOSED square null action
	 * 3− stepOn on CLOSED square blow up action
	 * 4− stepOn on CLOSED square clear action
	 * 5− stepOn on FLAGGED square
	 */
	/**
	 * GIVEN: OPENED square
	 * WHEN: stepOn()
	 * THEN: square remains open and return false
	 */
	@Test
	void OpenSquare() {
		
		Square sq = new Square(0,new NullAction());
		sq.open();
		
		boolean result = sq.stepOn();
		
		assertFalse(result);
		assertEquals(SquareState.OPENED, sq.getState()); 
	}
	/**
	 * GIVEN: CLOSED square with NullAction
	 * WHEN: stepOn()
	 * THEN: square set as open and return true, no side effects
	 */
	@Test
	void ClossedSquareNullAction() {
		
		
		Square sq = new Square(2, new NullAction()); 
		
		boolean result = sq.stepOn();
		
		assertTrue(result);
		assertEquals(SquareState.OPENED, sq.getState());
		
	}
	/**
	 * GIVEN: CLOSED square with BlowUpAction
	 * WHEN: stepOn()
	 * THEN: square set as open and return true, the board is exploded
	 */
	@Test
	void ClossedSquareBlowUpAction() {
			Board board = new Board(1, 1, 100);				
			Square mineSquare = new Square(-1, new BlowUpAction(board));
			
			mineSquare.stepOn();
			assertEquals(SquareState.OPENED, mineSquare.getState());
			assertTrue(board.hasExploded());
	}
	/**
	 * GIVEN: CLOSED square
	 * WHEN: stepOn()
	 * THEN: square set as open and return true, the surrounding squares are opened
	 */
	@Test
	void ClossedSquareClearAction() {
		List<Square> squares= new ArrayList<>();
		for (int i= 0; i< 7; i++) {
			squares.add( new Square(0,  new NullAction()));
		}	
		Square sq = new Square(0,new ClearAction(squares));
		boolean result = sq.stepOn();
		
		assertTrue(result);
		assertEquals(SquareState.OPENED, sq.getState());
		
		
		for (Square neignours : squares) {
			assertEquals(SquareState.OPENED, neignours.getState());
		}
	}
	/**
	 * GIVEN: FLAGGED square
	 * WHEN: stepOn()
	 * THEN: square remains flagged and return false
	 */
	@Test
	void FlaggedSquare() {
		
		Square sq = new Square(0,new NullAction());
		sq.flag();
		
		boolean result = sq.stepOn();
		
		assertFalse(result);
		assertEquals(SquareState.FLAGGED, sq.getState());
	}

}