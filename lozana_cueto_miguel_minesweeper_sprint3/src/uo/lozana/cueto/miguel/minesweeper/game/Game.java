package uo.lozana.cueto.miguel.minesweeper.game;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.mp.util.check.ArgumentChecks;



public class Game {
	private Board board;
    private GameInteractor interactor;
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
	public void setInteractor(GameInteractor consoleGameInteractor) {
		ArgumentChecks.isNotNull(consoleGameInteractor, "The interactor cannot be null");
		this.interactor = consoleGameInteractor;
	}
	
	/**
	 * Starts the game
	 */
	public void play() {
		interactor.showReadyToStart();
        board.uncoverWelcomeArea();
        long startTime = System.currentTimeMillis();
        
        while (!board.hasExploded() && !board.isGameWon()) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            
            interactor.showGame(elapsedTime, board);
       
            GameMove move = interactor.askMove(board.getNumberOfRows(), board.getNumberOfColumns());
            executeMove(move);
            
        }
        
        long finalTime = (System.currentTimeMillis() - startTime) / 1000;
        interactor.showGame(finalTime, board); 

        if (board.isGameWon()) {
            interactor.showCongratulations(finalTime);
        } else {
            interactor.showBooommm();
        }
        interactor.showGameFinished();
    }
        
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
}
