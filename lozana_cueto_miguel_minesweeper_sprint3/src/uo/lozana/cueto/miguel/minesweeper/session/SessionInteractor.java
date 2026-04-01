package uo.lozana.cueto.miguel.minesweeper.session;

import java.util.List;

import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;

public interface SessionInteractor {
	
	GameLevel askGameLevel();
	
    String askUserName();
	int askNextOption();
	boolean doYouWantToRegisterYourScore();
	void showRanking(List<GameRankingEntry> ranking);
	void showPersonalRanking(List<GameRankingEntry> ranking);
	void showGoodBye();
	void showErrorMessage(String message);
	void showFatalErrorMessage(String message);

}
