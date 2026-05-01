package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class BoardUnflagTest {
	/*
	 *1- Unflag mine square with flag
	 *2- Unflag mine square without flag
	 *3- Unflag open square has no effect
	 *4- Unflag clossed square has no effect
	 *5- Unflag our of bounds exception
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
	/*
	 * GIVEN: Mine square with flag
	 * WHEN: unflag()
	 * THEN: Flag counter increse and square is clossed
	 */
	@Test
	void MineWithFlag() {
		Square[][] exBoard = createClossedEmptyBoard(); 
		Square mine = new Square(-1,new NullAction());
		mine.flag();
		exBoard[1][1] = mine;
		
		Board board = new Board(1,exBoard);
		board.flag(1, 1);
		
		int numberFlags = board.getNumberOfFlagsLeft();
		
		board.unflag(1, 1);
		
		assertEquals(numberFlags+1, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.CLOSED, board.getSquares()[1][1].getState());
	}
	/*
	 * GIVEN: Mine square without flag
	 * WHEN: unflag()
	 * THEN: Flag counter no change and square remains clossed
	 */
	@Test
	void MineWithoutFlag() {
		Square[][] exBoard = createClossedEmptyBoard();
		Square mine = new Square(-1,new NullAction());
		exBoard[1][1] = mine;
		
		Board board = new Board(1,exBoard);
		int numberFlags = board.getNumberOfFlagsLeft();
		
		board.unflag(1, 1);
		
		assertEquals(numberFlags, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.CLOSED, board.getSquares()[1][1].getState());
	}
	/*
	 * GIVEN: Clossed square
	 * WHEN: unflag()
	 * THEN: Flag counter no change and square remains clossed
	 */
	@Test
	void ClossedSquare() {
		Square[][] exBoard = createClossedEmptyBoard();
		exBoard[1][1] = new Square(3,new NullAction());
		
		Board board = new Board(1,exBoard);
		int numberFlags = board.getNumberOfFlagsLeft();
		
		board.unflag(1, 1);
		
		assertEquals(numberFlags, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.CLOSED, board.getSquares()[1][1].getState());
	}
	/*
	 * GIVEN: Opened square
	 * WHEN: unflag()
	 * THEN: Flag counter not changing and square remains opened
	 */
	@Test
	void OpenSquare() {
		Square[][] exBoard = createClossedEmptyBoard();
		exBoard[1][1] = new Square(3,new NullAction());
		exBoard[1][1].open();
		Board board = new Board(1,exBoard);
		int numberFlags = board.getNumberOfFlagsLeft();
		
		board.unflag(1, 1);
		
		assertEquals(numberFlags, board.getNumberOfFlagsLeft());
		assertEquals(SquareState.OPENED, board.getSquares()[1][1].getState());
	}
	/**
	 * GIVEN: Coordinates outside the board
	 * WHEN: unflag() is called
	 * THEN:  IndexOutOfBoundsException 
	 */
	@Test
	void unflagOutsideBounds() {
		Square[][] exBoard = createClossedEmptyBoard();
		Board board = new Board(1, exBoard);
		
		assertThrows(IllegalArgumentException.class, () -> {
			board.unflag(10, 10);
		});
	}

}