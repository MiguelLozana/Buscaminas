package uo.lozana.cueto.miguel.minesweeper.game;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;

public interface GameInteractor {
	/**
	 * Ask for a valid move
	 * @param rows number of rows of the board 
	 * @param rows number of columns of the board 
	 */
	GameMove askMove(int rows, int columns);
	/**
	 * Shows all the information of the board
	 * @param elapsedTime the time spent to show
	 * @param board the board to show
	 */
    void showGame(long elapsedTime, Board board);
    /**
	 * Shows a message when the game has ended
	 */
    void showGameFinished();
    /**
	 * Shows a message the player has won
	 */
    void showCongratulations(long elapsedTime);
    /**
	 * Shows a message when the the player has stepped into a mine
	 */
    void showBooommm();
    /**
     * Shoes an initial  message
     */
    void showReadyToStart();
    
    
    


}
