package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.game.board.Square;
class BlowUpActionTest {
	/*
	 * Scenarios
	 * 1-Only one mine to blow up. No widespread
	 * 2-Several mines to blow up. widespread
	 */
	/*
	 * @return board full of closed no mines square
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
	 * GIVEN: a board with only a mine
	 * WHEN: blows up
	 * THEN: the board is set as exploded
	 */
	@Test
	public void oneMine() {
		Square[][] b = createClossedEmptyBoard();
		Board board = new Board(1,b); 
		b[1][1] = new Square(-1, new BlowUpAction(board));
		
		assertFalse(board.hasExploded());
		board.stepOn(1,1);
		assertTrue(board.hasExploded());
	}
	
	/**
	 * GIVEN: a board with only a mine
	 * WHEN: blows up
	 * THEN: the board is set as exploded
	 */
	@Test
	public void SeveralMine() {
		Square[][] b = createClossedEmptyBoard();
		Board board = new Board(1,b); 
		b[0][0] = new Square(-1, new BlowUpAction(board));
		b[0][8] = new Square(-1, new BlowUpAction(board));
		b[1][1] = new Square(-1, new BlowUpAction(board));
		b[4][4] = new Square(-1, new BlowUpAction(board));
		b[5][2] = new Square(-1, new BlowUpAction(board));
		b[7][6] = new Square(-1, new BlowUpAction(board));
		b[8][0] = new Square(-1, new BlowUpAction(board));
		b[8][8] = new Square(-1, new BlowUpAction(board));
		
		assertFalse(board.hasExploded());
		board.stepOn(1,1);
		assertTrue(board.hasExploded());
		
		
		
	}

}
