package uo.lozana.cueto.miguel.minesweeper.game.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.lozana.cueto.miguel.minesweeper.game.board.actions.*;
import uo.mp.util.check.ArgumentChecks;

/**
 * This class deals with the matrix of the fame and the rules of the board
 */
public class Board {
	public static final int DEF_SIZE = 9;
	public static final int DEF_MINES_PERCENTAGE = 12;
	
	
	private Square[][] board;
	private int mines;
	private int flags;
	private static Random random = new Random();
	private boolean exploded = false;
	
	/*============================================
	 *        CONSTRUCTORS
	 * ===========================================
	 */
	/**
	 * Def constructor, a board 9x9 with 12% of mines (10 mines)
	 */
	public Board() {
		this(DEF_SIZE,DEF_SIZE,DEF_MINES_PERCENTAGE);
	}
	/**
	 * Creates the board with a number of rows and columns an a percentage of mines on it.
	 * @param i number of rows
	 * @param j number of columns
	 * @param d percentage of mines
	 * @throws IAE if a param is invalid
	 */
	public Board(int i, int j, double d) {
		ArgumentChecks.isTrue(i>0,"The rows number must be bigger than 0");
		ArgumentChecks.isTrue(i<=25,"The rows number can´t be bigger than 25");
		
		ArgumentChecks.isTrue(j>0,"The columns number must be bigger than 0");
		ArgumentChecks.isTrue(j<=25,"The columns number can´t be bigger than 25");
		
		ArgumentChecks.isTrue(d>0,"The percentaje must be bigger than 0");
		ArgumentChecks.isTrue(d<=25,"The percentaje can´t be bigger than 25");
		
		
		this.mines = (int) Math.ceil( i*j*d*0.01);
		this.flags = this.mines;
		board = new Square[i][j];
		fillMines(mines);
		setCorrectValue();
		setCorrectActions();
		
	}
	/**
	 * Generates a board with a provided matrix an number of mines adn fixed it if necesary 
	 * @param mines
	 * @param squares
	 */
	
	public Board(int mines, Square[][] squares) {
		ArgumentChecks.isNotNull(squares);
		ArgumentChecks.isTrue(mines > 0);
		
		board = squares;
		
		setCorrectValue();
		setCorrectActions();
		
		this.mines = countMines();
		this.flags = countMines();
		
	}
	
	
	
	/*============================================
	 *        MOVEMENTS
	 * ===========================================
	 */
	/**
	 * Reveals the square given its coordinates
	 * @param row where the square is
	 * @param column where the square is
	 */
	public void stepOn(int row,int column) {
		checkCoordenates(row,column);
		Square sq = board[row][column];
		sq.stepOn();
	}
	/**
	 * Flag a square placed on the row and column given
	 * @param row where the square is
	 * @param column where the square is
	 */
	public void flag(int row, int column) {
		checkCoordenates(row,column);
		if (this.flags>0 && board[row][column].flag()) {
			this.flags--;
		}
	}
	/**
	 * Unflag a square placed on the row and column given
	 * @param row where the square is
	 * @param column where the square is
	 */
	public void unflag(int row, int column) {
		checkCoordenates(row,column); 
		if(board[row][column].unflag()) {
			this.flags++;
		}
		
	}
	/**
	 * Opens all the board
	 */
	public void unveil() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				board[row][column].open();
			}
		}
	}
	/*============================================
	 *        INFORMATION
	 * ===========================================
	 */
	
	/**
	 * @return if the board has exploded
	 */
	public boolean hasExploded() {
		return this.exploded;
	}
	public void markAsExploded() {
		this.exploded=true;
	}
	/**
	 * @return the number of flags remaining
	 */
	public int getNumberOfFlagsLeft() {
		return this.flags;
	}
	
	/**
	 * @return the number of mines remaining
	 */
	public int getNumberOfMinesLeft() {
		int counter = 0;
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				Square square = board[row][column];
				if(!square.isFlagged() && square.hasMine()) {
					counter++;
				}
			}
		}
		return counter;
	}
	public int getNumberOfRows() {
		 return this.board.length;
	 }
	public  int getNumberOfColumns() {
		 return this.board[0].length;
	 }
	
	/*============================================
	 *        COMPLETE BOARD
	 * ===========================================
	 */
	 
	/**
	 * @return a copy of the symbols of the chart
	 */
	public char[][] getState(){
		char[][] copied = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				copied[row][column] = board[row][column].toString().charAt(0);
			}
			
		}
		return copied;
	}
	/**
	 * @return a copy of the board but same squares
	 */
	 public Square[][] getSquares() {
		Square[][] copied = new Square[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				copied[row][column] = board[row][column];
			}
			
		}
		return copied;
	}
	 
	 /**
	  * Looks for a square with clear action not corner and step on it
	  */
	 public void uncoverWelcomeArea() {
		 if (board.length < 3 && board[0].length < 3){ 
			 stepOn(0, 0); //all board is made of corners. 
			 
		 } 
		 else {
		 
			 int row ;
			 int col;
			 do {
			  	row = getRandomNumber(board.length);
			  	col = getRandomNumber(board[row].length);
		 	} while(!board[row][col].isClear() || isCorner(row,col));
		 	stepOn(row, col);
		 }
	 }
	 
	 public boolean isGameWon() {
		    return countNoOpenSquares() == mines;
	}
	
	
	/*========================================
	 * HELPER METHODS (private)
	 * =======================================
	 */
	 
	 private int countNoOpenSquares() {
			int count = 0;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j].getState() != SquareState.OPENED) {
					count++;
					}
				}
			}
			return count;
		}
	 
	/**
	 * Fill the matrix with mines placed randomly 
	 * @param mines number of mines to place
	 */
	private void fillMines(int mines) {
		
		while (mines != 0) {
			int row = getRandomNumber(board.length);
			int column = getRandomNumber(board[row].length);
			if (board[row][column] == null) {
				board[row][column] = new Square(-1, new BlowUpAction(this));
				mines--;
			}
		}
	}
	/**
	 * If the squares is not already set or the value is incorrectly, this method set them correctly
	 */
	private void setCorrectValue() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if(board[row][column] == null) {
					board[row][column] = new Square(getSurroundingMines(row, column),new NullAction());				
				}else if(board[row][column].getValue() > 0 && board[row][column].getValue() != getSurroundingMines(row, column)) {
					board[row][column].setValue(getSurroundingMines(row, column));
				}
			}
		}
	}
	/**
	 * If the action is not already set or set incorrectly, this method set them correctly
	 */
	private void setCorrectActions() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				Square sq = board[row][column];
				if(sq.getValue() == -1 && !sq.isBlowUpAction()) {
					sq.setAction(new BlowUpAction(this));
				} else if(sq.getValue() == 0 && !sq.isClear()) {
					sq.setAction(new ClearAction(getNeigbours(row, column)));
				}else if(sq.getValue() > 0 && !sq.isNullAction()) {
					sq.setAction(new NullAction());
				}
			}
		}	
	}
	
	/*
	 * Gets a list with the surrounding squares
	 */
	private List<Square> getNeigbours(int row, int column) {
		List<Square> neighbors= new ArrayList<>(); 
		
		for (int i = -1; i <= 1; i++) { // I start -1 step form middle (one left one up)
			for (int j = -1; j <= 1; j++) {
				
				int rowToCheck = i+row;
				int columnToChech = j+column;
				
				boolean isValidRow = rowToCheck >= 0  && rowToCheck < board.length ;
				boolean isValidCol = columnToChech >= 0  && columnToChech < board[0].length ;
				
				if (isValidRow && isValidCol && !(rowToCheck==row && columnToChech==column)) {//If is not the middle square and is inside the board
					neighbors.add(board[rowToCheck][columnToChech]);
				}
			}
		}
		return neighbors;
		
					
	}
	
	
	/*
	 *  Count how many mines are arround the one provided as cordenates
	 *  @return the number of mines
	 */
	private int getSurroundingMines(int row, int column) {
		checkCoordenates(row,column);
		
		int counter = 0;
		
		for (int i = -1; i <= 1; i++) { // I start -1 step form middle (one left one up)
			for (int j = -1; j <= 1; j++) {
				
				int rowToCheck = i+row;
				int columnToChech = j+column;
				
				boolean isRowOutOfBOunds = rowToCheck < 0  || rowToCheck >= board.length ;
				boolean isColumnOutOfBOunds = columnToChech < 0 || columnToChech >= board[0].length ;
				
				if (!isRowOutOfBOunds && !isColumnOutOfBOunds && !(rowToCheck==row && columnToChech==column)) { //If is not the middle square and is inside the board
					if (board[rowToCheck][columnToChech] != null && board[rowToCheck][columnToChech].hasMine()) {
						counter++;
					}
				}
			}   
		}
		return counter;
	}
	private int countMines() {
		int count = 0;
		for  (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if(board[row][column].getValue() == -1) {
					count++;
				}
			}
		}
		return count;
	}
	/*
	 * Checks if the coordenates provides are a corner
	 */
	private boolean isCorner(int r, int c) {
	    int lastRow = board.length - 1;
	    int lastCol = board[0].length - 1;
	    
	    return (r == 0 || r == lastRow) && (c == 0 || c == lastCol);
	}
	/**
	 * Generates a number between 0 and the one given (0 included, max not)
	 */
	public static int getRandomNumber(int max) {
		return random.nextInt(max);
		
	}
	/*
	 * Checks if the coordinates provided are in the board
	 */
	private void checkCoordenates(int row, int column) {
		ArgumentChecks.isTrue(row>=0 && row < board.length);
		ArgumentChecks.isTrue(column>=0 && column < board[row].length);
		}
}
