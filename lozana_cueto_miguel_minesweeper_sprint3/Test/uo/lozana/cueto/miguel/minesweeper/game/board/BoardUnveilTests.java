package uo.lozana.cueto.miguel.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.NullAction;

class BoardUnveilTests {
	/*
	 * Scenarios
	 *1- Board competely closed
	 *2- Board with some flags
	 *3- Board with some squares already opened
	 */
	
	
	/**
	 * GIVEN: All board closed
	 * WHEN: unveil
	 * THEN: All board is opened
	 */
	@Test
	void AllBoardClosed() {
		Board board = new Board();
			board.unveil();
			Square[][] squares = board.getSquares();
			for (int row = 0; row < squares.length; row++) {
				for (int column = 0; column < squares[row].length; column++) {
					assertEquals(SquareState.OPENED, squares[row][column].getState());
				}
			}
	}
	/**
	 * GIVEN: Board with some flags
	 * WHEN: unveil
	 * THEN: All board is opened
	 */
	
	@Test
	public void someFlag() {
		Square[][] exBoard = new Square[9][9];
		for (int i = 0; i < exBoard.length; i++) {
			for (int j = 0; j < exBoard[i].length; j++) {
				Square sq = new Square(2,new NullAction());
				exBoard[i][j] = sq;
				if(i==j) {
					sq.flag();
				}
			}
		}
		
		Board board = new Board(10,exBoard);
		board.unveil();
		Square[][] squares = board.getSquares();
		for (int row = 0; row < squares.length; row++) {
			for (int column = 0; column < squares[row].length; column++) {
				assertEquals(SquareState.OPENED, squares[row][column].getState());
			}
		}
	}
	/**
	 * GIVEN: Board with some squares alredy opened
	 * WHEN: unveil
	 * THEN: All board is opened
	 */
	
	@Test
	public void someOpen() {
		Square[][] exBoard = new Square[9][9];
		for (int i = 0; i < exBoard.length; i++) {
			for (int j = 0; j < exBoard[i].length; j++) {
				Square sq = new Square(2,new NullAction());
				exBoard[i][j] = sq;
				if(i==j) {
					sq.open();
				}
			}
		}
		
		Board board = new Board(10,exBoard);
		board.unveil();
		Square[][] squares = board.getSquares();
		for (int row = 0; row < squares.length; row++) {
			for (int column = 0; column < squares[row].length; column++) {
				assertEquals(SquareState.OPENED, squares[row][column].getState());
			}
		}
	}

}