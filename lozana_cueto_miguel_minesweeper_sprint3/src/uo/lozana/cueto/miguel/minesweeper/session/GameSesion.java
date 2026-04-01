package uo.lozana.cueto.miguel.minesweeper.session;



import org.junit.platform.commons.logging.Logger;

import uo.lozana.cueto.miguel.minesweeper.console.ConsoleGameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.GameMove;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

public class GameSesion {
	private GameInteractor gameInteractor;
	private SessionInteractor sesion;
	private Logger logger;
	private GameRanking ranking;
	
	
	public GameSesion(GameInteractor gameInteractor, SessionInteractor sesion, Logger logger, GameRanking ranking) {
		setGameInteractor(gameInteractor);
		setGameRanking(ranking);
		setLogger(logger);
		setGameRanking(ranking);
		run();
	}
	
	
	/*===========================================
	 * 				SETERS
	 * ========================================
	 */
	public void setGameInteractor(GameInteractor interactor) {
		ArgumentChecks.isNotNull(interactor,"The Game Interactor mus not be null");
		this.gameInteractor = interactor;
	}
	public void setSessionInteractor(SessionInteractor interactor) {
		ArgumentChecks.isNotNull(interactor,"The Sesion Interactor mus not be null");
		this.sesion = interactor;
	}
	
	public void setLogger(Logger logger) {
		ArgumentChecks.isNotNull(logger,"The Sesion logger mus not be null");
		this.logger = logger;
	}
	public void setGameRanking(GameRanking ranking) {
		ArgumentChecks.isNotNull(ranking,"The Sesion logger mus not be null");
		this.ranking = ranking;
	}


	/*===========================================
	 * 				ORHER METOTHS
	 * ========================================
	 */
	public void run() {
		sesion.AskForName();
		while (true) {
			handleMenu();
		}
	}
	
	
	private void handleMenu() {
		sesion.showMenu();
		int option = sesion.askForOption();
		handleOption(option);
	}
	private void handleOption(int option) {
		switch (option) {
		case 1 -> playGame();
		case 2 -> allScores();
		case 3 -> myScores();
		case 4 -> exit();
		default -> throw new RuntimeException();
		}
		
	}
	private void playGame() {
		sesion.showDifficulties();
		GameLevel  level = sesion.askForDifficulty();
	
		Board board = switch (level) {
	    case  FACIL -> new Board(9,9,12);
	    case MEDIANO-> new Board(16,16,15);
	    case DIFICIL-> new Board(30,16,20);
	    default -> throw new RuntimeException("Nivel no reconocido");
		};
		Game game = new Game(board);
		game.setInteractor(gameInteractor);
		game.play();
		if(sesion.saveScore()) {
			
		}
	} 
}
