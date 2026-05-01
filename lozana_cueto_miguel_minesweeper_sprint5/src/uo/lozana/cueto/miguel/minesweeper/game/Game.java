package uo.lozana.cueto.miguel.minesweeper.game;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.mp.util.check.ArgumentChecks;


public class Game {
	private Board board;
    private GameInteractor interactor;
    private long startTime;
    private long endTime;
    
	/*============================================
	 *        CONSTRUCTORS
	 * ===========================================
	 */
    /**
     * Creates the game given the board
     * @param board a board
     */
	public Game(Board board) {
		ArgumentChecks.isNotNull(board);
		this.board = board;
	}
	/**
	 * The board is created randomly
	 */
	public Game() {
		this.board = new Board();
	}
	/*============================================
	 *        SETTERS AND GETTERS
	 * ===========================================
	 */
	public void setInteractor(GameInteractor consoleGameInteractor) {
		ArgumentChecks.isNotNull(consoleGameInteractor, "The interactor cannot be null");
		this.interactor = consoleGameInteractor;
	}
	public boolean hasWon() {
		return this.board.isGameWon();
	}
	
	public long getDuration() {
	    return (endTime - startTime) / 1000;
	}
	
	/*============================================
	 *       PLAY
	 * ===========================================
	 */
	/**
	 * Starts the game
	 */
	public void play() {
		// Staring game configuration 
		this.startTime = System.currentTimeMillis();
		interactor.showReadyToStart();
        board.uncoverWelcomeArea();
       
        // manages the turns of the game until is ended
        while (!board.hasExploded() && !board.isGameWon()) {
        	playTurn();
            
        }
        // stops recording time
        this.endTime = System.currentTimeMillis();
        long finalTime = (endTime - startTime) / 1000;
        // update the board and final message
        interactor.showGame(finalTime, board); 
        endMessage(finalTime);
        
    }
	
	
	
	/*============================================
	 *        PRIVATE HELPING METHODS
	 * ===========================================
	 */
	private void executeMove(GameMove move) {
        char op = move.getOperation();
        int row = move.getRow();
        int col = move.getColumn();

        switch (Character.toLowerCase(op)) {
            case 's' -> board.stepOn(row, col);
            case 'f' -> board.flag(row, col);
            case 'u' -> board.unflag(row, col);
        }
    }
	/*
	 * Execute a turn in the game
	 */
	private void playTurn() {
		long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        
        interactor.showGame(elapsedTime, board);
   
        GameMove move = interactor.askMove(board.getNumberOfRows(), board.getNumberOfColumns());
        executeMove(move);
	}
	/*
	 * Select the message to show in the end of the game
	 */
	private void endMessage(long finalTime) {
		if (board.isGameWon()) {
            interactor.showCongratulations(finalTime);
        } else {
            interactor.showBooommm();
        }
        interactor.showGameFinished();
	}
}
