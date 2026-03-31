package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




class BoardConstructorTests {

	/*
	*1- A square board has the correct dimensions and the expected number of mines
	*2- A rectangular board has the correct dimensions and the expected number of mines
	*3- Mines percentage = 0
	*4- Mines percentage = 100
	*5- Mines percentage >100
	*/
	
	/**
	 * GIVEN:A square board size and a mine percentage
	 * WHEN: The sqaure board is created
	 * THEN: he board has the correct dimensions and number of mines
	 */
	@Test
	void CorrectSquareBoard() {
		Board board = new Board (9,9,10);
		assertEquals(9, board.getNumberOfMinesLeft());
		Square[][] sq = board.getSquares();
		
		assertEquals(9, sq.length);
		for (int row = 0; row < sq.length; row++) { 
			assertEquals(9, sq[row].length);
		}
	}
	/**
	 * GIVEN:A rectangular board size and a mine percentage
	 * WHEN: The square board is created
	 * THEN: The board has the correct dimensions and number of mines
	 */
	@Test
	void CorrectNotSquareBoard() {
		Board board = new Board (3,20,12);
		
		assertEquals(8, board.getNumberOfMinesLeft());
		Square[][] sq = board.getSquares();
		
		assertEquals(3, sq.length);
		for (int row = 0; row < sq.length; row++) {
			assertEquals(20, sq[row].length);
		}
	}
	/**
	 * GIVEN: 0 percent of mines
	 * WHEN: The board is created
	 * THEN: IllegalArgumentException 
	 */
	@Test
	void zeroPercentMines() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(9, 9, 0);
			});
	}

	/**
	 * GIVEN: 100 percent of mines
	 * WHEN: The board is created
	 * THEN: All squares are mines
	 */
	@Test
	void hundredPercentMines() {
		Board board = new Board(9, 9, 100);
		assertEquals(81, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: more than 100 percent of mines
	 * WHEN: The board is created
	 * THEN: IllegalArgumentException 
	 */
	@Test
	void moreThanHundredPercentMines() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Board(9, 9, 101);
			});
	}

}
