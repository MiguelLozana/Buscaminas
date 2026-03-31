package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class BoardGetStateTests {
	/*
	 * 1- New board.
	 * 2- All board opened.
	 * 3- Intermediate game state: flag, empty, and number.
	 */
	
	/**
	 * Helper method to create a board with all squares closed
	 */
	private Square[][] createClossedEmptyBoard() {
		Square[][] exBoard = new Square[9][9];
		for (int i = 0; i < exBoard.length; i++) {
			for (int j = 0; j < exBoard[i].length; j++) {
				exBoard[i][j]= new Square(2, new NullAction());
				
			}
		}
		return exBoard;
	}

	/**
	 * GIVEN: A new board
	 * WHEN: getState()
	 * THEN: All squares are closed
	 */
	@Test
	public void initialBoardState() {
		Board board = new Board();
		char[][] state = board.getState();
		for (int row = 0; row < state.length; row++) {
			for (int column = 0; column < state[row].length; column++) {
				assertEquals(Square.CLOSED,state[row][column]);
			}
			
		}
	}

	/**
	 * GIVEN: All board opened
	 * WHEN: getState()
	 * THEN: All squares are NOT closed 
	 */
	@Test
	public void OpenBoardState() {
		Board board = new Board();
		board.unveil();
		char[][] state = board.getState();
		for (int row = 0; row < state.length; row++) {
			for (int column = 0; column < state[row].length; column++) {
				assertNotEquals(Square.CLOSED,state[row][column]);
			}
			
		}
	}
	
	/**
	 * GIVEN: Intermediate game state
	 * WHEN: getState()
	 * THEN: Returns correct characters 
	 */
	@Test
	public void MiddleGameState() {
	    // We keep your objects
	    Square mine = new Square(-1, new NullAction());
	    Square empty = new Square(0, new NullAction());
	    Square number = new Square(2, new NullAction());
	    Square flagged = new Square(5, new NullAction());
	    
	    Square[][] exBoard = createClossedEmptyBoard();
	    exBoard[0][0] = mine;
	    exBoard[0][1] = number;
	    exBoard[0][2] = empty;
	    exBoard[0][3] = flagged;
	    
	    empty.open();
	    number.open();
	    flagged.flag();
	    mine.open();
	    
	    Board board = new Board(1, exBoard);
	    
	    char[][] state = board.getState();
	    
	    assertEquals(Square.OPEN_MINE, state[0][0]);
	    assertEquals(Square.EMPTY, state[0][2]);
	    assertEquals('2', state[0][1]);
	    assertEquals(Square.FLAG, state[0][3]);
	    
	    for (int row = 0; row < state.length; row++) {
	        for (int col = 0; col < state[row].length; col++) {
	            if (row == 0 && col <= 3) {
	                continue;
	            }
	            assertEquals(Square.CLOSED, state[row][col]);
	        }
	    }
	}
}