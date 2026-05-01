package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.game.board.Square;
import uo.lozana.cueto.miguel.minesweeper.game.board.SquareState;

class ClearActionExecuteTest {
	/*
	 * Scenarios:
	 * 1- Clears a zone
	 * 2- Not possible to widespread (all neigbours are next to mines)
	 * 3- Flagged square, does noting
	 * 4- Already open square, does nothing
	 */
	/*
	 * @return board full of clossed no mines square
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
	/**1
	 * GIVEN: a board with a big area of sauqres to clear
	 * WHEN: step on on whichever mine
	 * THEN: all was set as opened
	 */
	@Test
	void clearZone() {
		Square[][] exBoard = new Square[10][10];
		exBoard[4][4] = new Square(-1,new NullAction());
		
		Board board = new Board(1, exBoard);
		int closed = 0;
		int opened = 0;
		board.stepOn(0, 0);
		
		Square[][] sq = board.getSquares();
		
		
		for (int i = 0; i < sq.length; i++) {
			for (int j = 0; j < sq[i].length; j++) {
				if(SquareState.CLOSED ==  sq[i][j].getState()) {
					closed++;
				}else if (SquareState.OPENED==  sq[i][j].getState()) {
					opened++;
				}
			}
		}
		assertEquals(99,opened);
		assertEquals(1,closed);
	}
	/**2
	 * GIVEN: a isolated 0 square
	 * WHEN: step on the isolated
	 * THEN: only opened the isolated an his neigbours 
	 *     0  1  2  3  4  5  6  7 
     *   -------------------------
  	 * 0 | #  @  #  #  @  #  #  #
  	 * 1 | #  2  1  2  2  #  #  #
  	 * 2 | @  1  -  -  1  @  #  #
  	 * 3 | #  2  1  1  2  #  #  #
  	 * 4 | #  @  #  #  @  #  #  #
  	 * 5 | #  #  #  #  #  #  #  #
  	 * 6 | #  #  #  #  #  #  #  #
  	 * 7 | #  #  #  #  #  #  #  #
	 */
	@Test
	void isolated() {
		Square[][] sq = new Square[8][8];
	
		sq[0][1] = new Square(-1, new NullAction());
		sq[0][4] = new Square(-1, new NullAction());
		
		sq[2][0] = new Square(-1, new NullAction());
		sq[2][5] = new Square(-1, new NullAction());
		
		sq[4][1] = new Square(-1, new NullAction());
		sq[4][4] = new Square(-1, new NullAction());
	    
		
		Board board = new Board(6,sq); 
		
		board.stepOn(2, 2);

		for (int i = 0; i < sq.length; i++) {
			for (int j = 0; j < sq[i].length; j++) {
				if(i >=1 && i<= 3 &&j >=1 && j<= 4 ) {
					assertEquals(SquareState.OPENED, sq[i][j].getState());
				}else {
					assertEquals(SquareState.CLOSED, sq[i][j].getState());
				}
			}
		}
	}
	/**
	 * GIVEN: a flagged square
	 * WHEN: step on the flagged square
	 * THEN: does nothing (stays closed and flagged)
	 */
	@Test
	void flaggedSquare() {
		Square[][] sq = createClossedEmptyBoard();
		
		Board board = new Board(1, sq);
		Square mine = new Square(-1, new BlowUpAction(board));
		sq[1][1] = mine;
		mine.flag();
		assertFalse(board.hasExploded());
		
		board.stepOn(1, 1);
		
		
		assertFalse(board.hasExploded(), "The board must NOT explode if there is a flag");
		for (int i = 0; i < sq.length; i++) {
			for (int j = 0; j < sq[i].length; j++) {
				if(i ==1 && j== 1) {
					assertEquals(SquareState.FLAGGED, sq[i][j].getState());
				}else {
					assertEquals(SquareState.CLOSED, sq[i][j].getState());
				}
				
			}
		}
	}
	/**
	 * GIVEN: an already opened square
	 * WHEN: stepOn() is called again
	 * THEN: does nothing 
	 */
	@Test
	void alreadyOpenSquare() {
		Square[][] sq = createClossedEmptyBoard();
		Board board = new Board(1, sq);
		
		sq[1][1] = new Square(1, new NullAction());
		board.stepOn(1, 1); 
		
		assertEquals(SquareState.OPENED, sq[1][1].getState());
		
		
		board.stepOn(1, 1);
		
		
		assertEquals(SquareState.OPENED, sq[1][1].getState());
		
		for (int i = 0; i < sq.length; i++) {
			for (int j = 0; j < sq[i].length; j++) {
				if(i == 1 && j == 1) {
					assertEquals(SquareState.OPENED, sq[i][j].getState());
				} else {
					assertEquals(SquareState.CLOSED, sq[i][j].getState());
				}
			}
		}
	}
}

