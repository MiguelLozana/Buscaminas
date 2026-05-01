package uo.lozana.cueto.miguel.minesweeper.game.board;


import uo.lozana.cueto.miguel.minesweeper.game.board.actions.Action;
import uo.mp.util.check.ArgumentChecks;

public class Square {
	public static final char FLAG = (char) 182;
	public static final char OPEN_MINE = '@';
	public static final char CLOSED = '#';
	public static final char EMPTY = ' ';
	
	
	private Action action;
	private SquareState state;
	private int number;
	
	
	/* ======================================
	 *      CONSTRUCTOR
	 * =====================================
	 */
	/**
	 * Creates the square with the number of mines that have arround
	 * @param value number of mines
	 */
	public Square(int value,Action action) {
		ArgumentChecks.isTrue(value >= -1 && value <= 8);
		ArgumentChecks.isNotNull(action);
		setState(SquareState.CLOSED);
		this.number = value;
		setAction(action);
		
		
	}	
	/* ======================================
	 *      SET AND GET		 
	 * =====================================
	 */	
		
	private void setState(SquareState state) {
		this.state =state;
	}
	
	public void setAction(Action action) {
		ArgumentChecks.isNotNull(action);
		this.action = action;
	} 
	protected void setValue(int actualValue) {
		this.number = actualValue;	
	}
	
	public SquareState getState() {
		return this.state;
	}
	
	public int getValue() {
		return this.number;
	}
	
	
	/* ======================================
	 *      SET STATES : OPENED,CLOSED,FLAGGED		 
	 * =====================================
	 */	
	/**
	 * If the square is closed, it is set as opened and executed the action
	 * @return True if was able to stepOn, false otherwise
	 */
	public boolean stepOn() {
		
		if (state == SquareState.CLOSED) {
			setState(SquareState.OPENED);
			action.execute();
			return true;
		}
		return false;
		
	}
	/**
	 * If the square is closed, it is set as flagged
	 * @return true if was able to flag the square
	 */
	public boolean flag() {
		if (state == SquareState.CLOSED) {
			setState(SquareState.FLAGGED);
			return true;
		}
		return false;
	}
	/**
	 * If the square is flagged, it is set as closed 
	 * @return true if was able to unflag the square
	 */
	public boolean unflag() {
		if (state == SquareState.FLAGGED) {
			setState(SquareState.CLOSED);
			return true;
		}
		return false;
	}
	/**
	 * Force the square as open
	 */
	public void open() {
		setState(SquareState.OPENED);
	}
	/* ======================================
	 *      CHECK STATES	 
	 * =====================================
	 */	
	/**
	 * Checks if the square has a mine
	 */
	public boolean hasMine() {
		return this.number == -1;
	}
	/**
	 * Checks if the square is flagged
	 */
	public boolean isFlagged() {
		return this.state == SquareState.FLAGGED;
	}
	/**
	 * Checks if the square is opened
	 */
	public boolean isOpened() {
		return this.state == SquareState.OPENED;
	}
	/**
	 * @return true if the action of the square is CLearAction, false otherwise
	 */
	public boolean isClear() {
		return this.action.isClearAction();
	}
	public boolean isBlowUpAction() {
		return this.action.isBlowUpAction();
	}
	public boolean isNullAction() {
		return this.action.isNullAction();
	}
	/* ======================================
	 *      OTHERS	 
	 * =====================================
	 */
	public String toString() {
		if(this.state == SquareState.FLAGGED) {
			return "" + FLAG;
		}
		if(this.state == SquareState.OPENED) {
			return numberToString();
		}
		return "#";

	}
	
	
	private String numberToString() {
		if(this.number == 0) {
			return  "" + EMPTY;
		}
		else if(hasMine()) {
			return "" + OPEN_MINE;
		}
		else{
			return ""+ number;
		}
	}


}
	
