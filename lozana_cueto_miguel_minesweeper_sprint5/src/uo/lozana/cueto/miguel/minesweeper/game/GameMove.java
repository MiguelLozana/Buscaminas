package uo.lozana.cueto.miguel.minesweeper.game;

    
    /**
     * Represents a move made by the user, containing the operation and coordinates.
     */
    public class GameMove {
	    private char operation;
	    private int row;
	    private int column;

	    /**
	     * Contrcutor with the operation, row and column
	     */
	    public GameMove(char operation, int row, int column) {
	        this.operation = operation;
	        this.row = row;
	        this.column = column;
	    }

	    /**
	     * @return the operation character
	     */
	    public char getOperation() {
	        return operation;
	    }
	    public int getRow() {
	        return row;
	    }
	    public int getColumn() {
	        return column;
	    }
	    @Override
	    public String toString() {
	        return "GameMove [operation=" + operation + ", row=" + row + ", column=" + column + "]";
	    }
}