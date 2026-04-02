package uo.lozana.cueto.miguel.minesweeper.session;

import java.util.List;

import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;

public interface SessionInteractor {
	/* ===================================
	 * 
	 *     INTPUT
	 * 
	 ====================================*/
	
	/**
	 * Ask the user the level/difficulty 
	 * @return the response a a GameLevel
	 */
	GameLevel askGameLevel();
	/**
	 * Ask the user the userName. It cannot be blank
	 * @return the valid userName
	 */
    String askUserName();
    /**
     * Ask the user to choose an option in the menu. 0 = exist; >0 valid menu options
     * @return the number which represent the menu option 
     */
	int askNextOption();
	/**
	 * Ask the user to decide to register the score
	 * @return true if decide to register, false otherwise
	 */
	boolean doYouWantToRegisterYourScore();
	
	
	/* ===================================
	 * 
	 *     OUTPUT
	 * 
	 ====================================*/
	
	
	/**
	 * Shows all the elements of the list provided
	 * @param ranking the list to show
	 */
	void showRanking(List<GameRankingEntry> ranking);
	/**
	 * Shows only the user ranking form the list provided
	 * @param ranking the list to show
	 */
	void showPersonalRanking(List<GameRankingEntry> ranking);
	/**
	 * Shoes the GoodBy message
	 */
	void showGoodBye();
	/**
	 * Shows the error message to the user
	 * @param message to show the user
	 */
	void showErrorMessage(String message);
	/**
	 * Shows the fatal error message to the user, the program will not continue
	 * @param message to show the user
	 */
	void showFatalErrorMessage(String message);

}
