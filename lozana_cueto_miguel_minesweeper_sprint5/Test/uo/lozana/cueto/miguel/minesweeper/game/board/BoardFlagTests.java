package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;



class BoardFlagTests {
	/*
	 * 1- flag in an already flagged square
	 * 2- flag in a closed square
	 * 3- flag in the same square twice.
	 * 4- flag in an already opened square
	 */
	private Square[][] createClossedEmptyBoard() {
		Square[][] exBoard = new Square[9][9];
		for (int i = 0; i < exBoard.length; i++) {
			for (int j = 0; j < exBoard[i].length; j++) {
				exBoard[i][j] = new Square(2, new NullAction());
			}
		}
		return exBoard;
	}
	
	/**
	 * GIVEN: An already flagged square
	 * WHEN: flag()
	 * THEN: It remains flagged and the number of flag do not change
	 */
	@Test
	void AlreadyFlagged() {
		Square[][] squares = createClossedEmptyBoard();
		Square sq = new Square(2, new NullAction());
		sq.flag();
		squares[1][1] = sq;
		Board board = new Board(1, squares);
		int flags = board.getNumberOfFlagsLeft();
		board.flag(1, 1);
		
		assertEquals(flags, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.FLAGGED, board.getSquares()[1][1].getState());
		
		
	}
	/**
	 * GIVEN: A closed square
	 * WHEN: flag()
	 * THEN: The square is flagged and the number of flags -1
	 */
	@Test
	void ClossedSquare() {
		Square[][] squares = new Square[5][5];
		squares[1][1] = new Square(-1,new NullAction());
		
		Board board = new Board(1, squares);
		int flags = board.getNumberOfFlagsLeft();
		board.flag(1, 1);
		
		assertEquals(flags-1, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.FLAGGED, board.getSquares()[1][1].getState());
		
		
	}
	/**
	 * GIVEN: A closed square
	 * WHEN: flag() is called twice
	 * THEN: It remains flagged and the number of flag do not change
	 */
	@Test
	void TwiceFlag() {
		Square[][] squares = new Square[3][3];
		squares[0][0] = new Square(-1, new NullAction());
	    Board board = new Board(1, squares);
		
	    int initialFlags = board.getNumberOfFlagsLeft();
		
	    //First time should work
		board.flag(1, 1);
	    assertEquals(initialFlags - 1, board.getNumberOfFlagsLeft());
	    assertEquals(SquareState.FLAGGED, board.getSquares()[1][1].getState());
		
		//Second rime should not work
	    int flagsAfterFirst = board.getNumberOfFlagsLeft();
	    board.flag(1, 1);
	    assertEquals(flagsAfterFirst, board.getNumberOfFlagsLeft());
	    assertEquals(SquareState.FLAGGED, board.getSquares()[1][1].getState());

	}
	/**
	 * GIVEN: An opened square
	 * WHEN: flag() is called
	 * THEN: The square remains OPENED and flag count do not change
	 */
	@Test
	void flagOnOpenedSquare() {
		Square[][] squares = createClossedEmptyBoard();
		Board board = new Board(1, squares);
		
		board.stepOn(0, 0); 
		assertEquals(SquareState.OPENED, board.getSquares()[0][0].getState());
		
		int flagsBefore = board.getNumberOfFlagsLeft();
		board.flag(0, 0);
		
		assertEquals(flagsBefore, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.OPENED, board.getSquares()[0][0].getState());
	}

}
