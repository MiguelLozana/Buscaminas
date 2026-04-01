package uo.lozana.cueto.miguel.minesweeper.console;

import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.GameMove;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.game.board.Square;
import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.lozana.cueto.miguel.minesweeper.session.SessionInteractor;
import uo.mp.util.console.Console;



public class ConsoleGameInteractor implements GameInteractor, SessionInteractor {
	private final static char[] MOVEMENTS = {'f','F','u','U','s','S'};
	public ConsoleGameInteractor() {
		
	}
	/*
	 * =================================
	 * OUTPUT
	 * ================================
	 */
	/**
	 * Shows all the information of the board
	 * @param elapsedTime the time spent to show
	 * @param board the board to show
	 */
	@Override
	public void showGame(long elapsedTime, Board board) {
		System.out.println("Time: " + elapsedTime + " seconds");
        System.out.println("Flags remaining : " + board.getNumberOfFlagsLeft());
        showBoard(board);
	}
	/**
	 * Shows a message when the game has ended
	 */
	@Override
	public void showGameFinished() {
		System.out.println("The game has ended");
		
	}
	/**
	 * Shows a message the player has won
	 */
	@Override
	public void showCongratulations(long elapsedTime) {
		System.out.println(Ansi.green("Congratulations! You are the winner of the game in " + elapsedTime + " seconds!"));
		
	}
	
	/**
	 * Shows a message when the the player has stepped into a mine
	 */
	@Override
	public void showBooommm() {
		System.out.println(Ansi.red("BOOOOOOM!!! Yur have stepped into a mine :("));
		
	}
	/**
	 * Shows a initial message 
	 */
	@Override
	public void showReadyToStart() {
		System.out.println("Are you ready? This is going to start!");
		
	}
	
	/*=============================
	 *  INPUT
	 *============================
	 */
	@Override
	/**
	 * Ask for a valid move
	 * @param rows number of rows of the board 
	 * @param rows number of columns of the board 
	 */
	public GameMove askMove(int rows, int columns) {
		
		int y = Console.readInt("Select row");
        y = getValidCoordante(y, rows);
        
        
        int x = Console.readInt("Select column");
        x = getValidCoordante(x, columns);
        
        char move = getMovement();
        
        return new GameMove(move , y, x);
    }
	
	/*===============================
	 * PRIVATE HELPER METHODS
	 * ===============================
	 */
	
	
	/*
	 * Ask for coordinate until one valid is provided
	 */
	private int getValidCoordante(int currentCoord , int maxCoord) {
		while(currentCoord<0 || currentCoord >=maxCoord) {
			
			currentCoord = Console.readInt(String.format(
					"Incorrect coord, try again. The value must be between 0 and %s, both icluded.", maxCoord -1));
        }
		return currentCoord;
	}
	
	
	/*
	 * Ask for movement until one valid is provided
	 */
	private char getMovement() {
		
		char move = Console.readChar("Select movement: flag, step op or unflag (F|S|U) ");
        while(!validMove(MOVEMENTS,move)) {
        	move = Console.readChar("Incorrect movement, try again. The movement must be flag, step op or unflag (F|S|U) ");
        }
        return move;
	}
	
	/*
	 * Checks if the move provided is valid (is among valid movements)
	 */
	private boolean validMove(char[] movements,char move) {
		for (char ch: movements) {
			if (ch == move) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Prints all the board, with row/column headers and square separators
	 */
	private void showBoard(Board board) {
		int numberOfRows = board.getNumberOfRows();
	    int numberOfCols = board.getNumberOfColumns();
	    
		// prints the columns numbers
	    System.out.print("      "); 
	    for (int c = 0; c < numberOfCols; c++) {
	        System.out.print(c + "   "); 
	    }
	    System.out.println(); // going to next line
	    
	    //Shows separator and game element
	    for (int row = 0; row < numberOfRows; row++) {
	        printSeparatorLine(numberOfRows);
	        System.out.print(row + "  ");
	        for (int col = 0; col < numberOfCols; col++) {
	        	
	            char symbol = board.getState()[row][col];
	            
	            //Use a color depending on the symbol
	            switch(symbol) {
	            
	            	case Square.OPEN_MINE -> System.out.print(Ansi.grey("| ") +Ansi.red(symbol)+ " ");
	            	case Square.FLAG -> System.out.print(Ansi.grey("| ")+ Ansi.green(symbol) + " ");
	            	case Square.CLOSED-> System.out.print(Ansi.grey("| ")+ Ansi.blue(symbol) + " ");
	            	
	            	default -> System.out.print(Ansi.grey("| ") + (symbol) + " ");
	            }
	        }
	        System.out.println(Ansi.grey("|")); 
	    }
	    printSeparatorLine(numberOfCols);
	}

	private void printSeparatorLine(int cols) {
	    System.out.print("   "); 
	    for (int i = 0; i < cols; i++) {
	        System.out.print(Ansi.grey("+---"));
	    }
	    System.out.println(Ansi.grey("+"));
	}
	
	
	
	@Override
	public GameLevel askForDifficulty() {
		boolean validLevel = false;
		
		while(!validLevel) {
			String level = Console.readString("Type your the level : ");
			try {
				return switch (level) {
			    case "FACIL" -> GameLevel.FACIL;
			    case "MEDIANO" -> GameLevel.MEDIANO;
			    case "DIFICIL" -> GameLevel.DIFICIL;
			    default -> throw new RuntimeException("Nivel no reconocido");
				};
				
			}catch (IllegalArgumentException e) {
				System.out.println("Incorrect name, try again");
			}
		}
		return null;	
	}
	@Override
	public void showDifficulties() {
			System.out.println(" .   .   .  DIFFICULTY  .   .   . ");
			System.out.println("FACIL (9x9 12%)");
			System.out.println("MEDIANO (16x16 15%)");
			System.out.println("DIFICIL (30x16 20%)");
			System.out.println(" .   .   .   .   .   .   .   .   . ");		
	}
		
	
	@Override
	public void showMenu() {
		System.out.println("==================== MENU ===================");
		System.out.println(" 1 - Play a game");
		System.out.println(" 2 - Check all players scores");
		System.out.println(" 3 - Chech my scores ");
		System.out.println(" 4 - Exit");
		System.out.println(" --------------------------------------------");
		
	}
	@Override
	public void AskForName() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int askForOption() {
		int option = -1;
	    while (option < 1 || option > 4) {
	        try {
	             option = Console.readInt("Select your option");
	        }
	        catch (Exception e) {
	            System.out.println("Incorrect option, type only a number (1 to 4)");
	        }
	    }
	    return option;
	}
	
	@Override
	public boolean saveScore(){
		return Console.readBoolean("Do you want to save your score? ");
	}
}


