package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class BoardUncoverWelcomeAreaTest {
	/**
	 * Sceanrios
	 * 1- There is only a mines. Opens all execept the mine
	 * 2- There is more than a mine, only mines are not opened
	 */
	
	/**
	 * Given: a board with only one mine
	 * When: uncoverWelcomeArea
	 * Then: only mines remains closed
	 */
	@Test
	void oneMine() {
		Square[][] exBoard = new Square[3][3];
		
		exBoard[0][0]= new Square(-1, new NullAction());
			
		Board board = new Board(1, exBoard);
		board.uncoverWelcomeArea();
		
			assertEquals(SquareState.CLOSED,board.getSquares()[0][0].getState());
		
		for (int row = 0; row < exBoard.length; row++) {
			for (int col = 0; col< exBoard[row].length; col++) {
				if (! (row == 0 && col==0)) {
					assertEquals(SquareState.OPENED,board.getSquares()[row][col].getState());
					}
				}
			}
		
	}
}