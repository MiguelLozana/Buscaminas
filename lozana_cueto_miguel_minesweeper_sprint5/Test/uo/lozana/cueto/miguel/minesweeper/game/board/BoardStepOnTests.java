package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import uo.lozana.cueto.miguel.minesweeper.game.board.actions.BlowUpAction;
import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class BoardStepOnTests {
	/*
	*1- stepOn on already opened square
	*2- stepOn on flagged square with mine
	*3- stepOn on flagged square without mine
	*4- stepOn on square with mine
	*5- stepOn on square with numeric hint
	*6- stepOn on empty square
	*/
	
	
	private Square[][] createClossedEmptyBoard() {
		Square[][] exBoard = new Square[9][9];
		for (int i = 0; i < exBoard.length; i++) {
			for (int j = 0; j < exBoard[i].length; j++) {
				exBoard[i][j]= new Square(2,new NullAction());
				
			}
		}
		return exBoard; 
	}
	
	/**
	 * GIVEN: An opened square
	 * WHEN: stepOn()
	 * THEN: It stays open
	 */
	@Test
	void AlreadyOpen() {
		Square sq = new Square(1,new NullAction());
		sq.open();
		
		Square[][] squares = createClossedEmptyBoard();
		squares[1][1] = sq;
		
		Board b = new Board(10,squares);
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.OPENED, b.getSquares()[1][1].getState());
		assertFalse(b.hasExploded());
	}
	/**
	 * GIVEN: A flagged square with mine
	 * WHEN: stepOn()
	 * THEN: Remains falgged and no explosion
	 */
	@Test
	void FlaggedMine() {
		Square sq = new Square(-1,new NullAction());
		sq.flag();
		
		Square[][] squares = createClossedEmptyBoard();
		squares[1][1] = sq;
		
		Board b = new Board(10,squares);
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.FLAGGED, b.getSquares()[1][1].getState());
		assertFalse(b.hasExploded());
	}
	/**
	 * GIVEN: A flagged square without mine
	 * WHEN: stepOn()
	 * THEN: Stay flagged and no explosion happens
	 */
	@Test
	void FlaggedWithoutMine() {
		Square sq = new Square(2,new NullAction());
		sq.flag();
		
		Square[][] squares = createClossedEmptyBoard();
		squares[1][1] = sq;
		
		Board b = new Board(10,squares);
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.FLAGGED, b.getSquares()[1][1].getState());
		assertFalse(b.hasExploded());
	}
	/**
	 * GIVEN: A square with a mine
	 * WHEN: stepOn()
	 * THEN: Becomes open and board explode
	 */
	@Test
	void ClosedMine() {
		Square[][] squares = createClossedEmptyBoard();
	    Board b = new Board(1, squares);
	    Square sq = new Square(-1, new BlowUpAction(b));
	    squares[1][1] = sq;
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.OPENED, b.getSquares()[1][1].getState());
		assertTrue(b.hasExploded());
	}
	/**
	 * GIVEN: A square with numeric hint
	 * WHEN: stepOn()
	 * THEN: It is open and no explode
	 */
	@Test
	void NumericHint() {
		Square sq = new Square(2,new NullAction());
		sq.open();
		
		Square[][] squares = createClossedEmptyBoard();
		squares[1][1] = sq;
		
		Board b = new Board(10,squares);
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.OPENED, b.getSquares()[1][1].getState());
		assertFalse(b.hasExploded());
	}
	/**
	 * GIVEN: Empty square
	 * WHEN: stepOn()
	 * THEN: Square is open and not exploded
	 */
	@Test
	void EmptySquare() {
		Square sq = new Square(0,new NullAction());
		sq.open();
		
		Square[][] squares = createClossedEmptyBoard();
		squares[1][1] = sq;
		
		Board b = new Board(10,squares);
		
		b.stepOn(1, 1);
		
		assertEquals(SquareState.OPENED, b.getSquares()[1][1].getState());
		assertFalse(b.hasExploded());
	}
}