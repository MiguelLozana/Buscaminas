package uo.lozana.cueto.miguel.minesweeper.session;


import uo.mp.minesweeper.util.log.SimpleLogger;


import java.time.LocalDateTime;

import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.mp.util.check.ArgumentChecks;

public class GameSession {
	private static final String INVALID_OPTION_MESSAGE = "Invalid option";
	
	private GameInteractor gameInteractor;
	private SessionInteractor sesion;
	private SimpleLogger logger;
	private GameRanking ranking;
	private String name; 
	
	
	/**
	 * Constructor with all parameters
	 * @param gameInteractor 
	 * @param sesion
	 * @param logger 
	 * @param ranking
	 */
	public GameSession(GameInteractor gameInteractor, SessionInteractor sesion, SimpleLogger logger, GameRanking ranking) {
		ArgumentChecks.isNotNull(gameInteractor);
		ArgumentChecks.isNotNull(sesion);
		ArgumentChecks.isNotNull(logger);
		ArgumentChecks.isNotNull(ranking);
		
		setGameInteractor(gameInteractor);
		setSessionInteractor(sesion);
		setLogger(logger);
		setGameRanking(ranking);
	}
	public GameSession() {
		// empty by desing
	}
	
	/*===========================================
	 * 				RUN
	 * ========================================
	 */
	

	
	/**
	 * Runs the session 
	 */
	public void run() {
	    	this.name = sesion.askUserName(); 
            mainLoop();
            sesion.showGoodBye();
	   
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
	
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.isNotNull(logger,"The Sesion logger mus not be null");
		this.logger = logger;
	}
	public void setGameRanking(GameRanking ranking) {
		ArgumentChecks.isNotNull(ranking,"The Sesion logger mus not be null");
		this.ranking = ranking;
		if(ranking.getLogger() == null) {
			ranking.setLogger(this.logger);
		}
	}

		



	/*===========================================
	 * 				PRIVATE HELPING 
	 * ========================================
	 */
	
	
	/*
	 * Controls the main loop and exceptions 
	 */
	private void mainLoop() {
		int option;
	    while ((option = sesion.askNextOption()) != 0) { 
	    	try {
				handleOption(option);
			} catch (GameException e) {
				sesion.showErrorMessage(e.getMessage());
				logger.log(e);
			} catch (Exception e) {
				sesion.showFatalErrorMessage(INVALID_OPTION_MESSAGE);
				logger.log(e);
				
			} 
	    
	    }
	}
	
	/*
	 * Handles the option choosen by the user
	 */
	private void handleOption(int option) throws GameException{
		switch (option) { 
		case 1 -> playGame();
		case 2 -> sesion.showRanking(ranking.getAllEntries());
		case 3 -> sesion.showPersonalRanking(ranking.getEntriesForUsername(this.name));
		case 4 -> ranking.exportRanking(sesion.askFileName());
		case 5 -> ranking.importRanking(sesion.askFileName());
		default -> throw new GameException(INVALID_OPTION_MESSAGE);
		};
	}
	/*
	 * Handles the minesweeper game
	 */
	private void playGame() {
		
		GameLevel  level = sesion.askGameLevel();
		Board board = switch (level) {
		    case  FACIL -> new Board(9,9,12);
		    case MEDIANO-> new Board(16,16,15);
		    case DIFICIL-> new Board(30,16,20);
		    default -> throw new RuntimeException("Nivel no reconocido");
			}; 
		Game game = new Game(board);
		game.setInteractor(gameInteractor);
		game.play();
		if(sesion.doYouWantToRegisterYourScore() && game.hasWon()) {
			long duration = game.getDuration();
			LocalDateTime date = LocalDateTime.now();
			ranking.append(new GameRankingEntry(this.name,date, level, duration, game.hasWon()));
			
		}
	}

	
	
	
	
}