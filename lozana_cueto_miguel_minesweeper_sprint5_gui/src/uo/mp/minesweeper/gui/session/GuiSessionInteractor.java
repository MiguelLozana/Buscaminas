package uo.mp.minesweeper.gui.session;

import java.util.List;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.lozana.cueto.miguel.minesweeper.session.SessionInteractor;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;



public class GuiSessionInteractor implements SessionInteractor {
	
	private SessionPresenter presenter;

	public GuiSessionInteractor() {
		SessionView view = new SessionView();
		presenter = new SessionPresenter();
		presenter.setView( view );
		view.setPresenter( this.presenter );
		presenter.show();
	}

	@Override
	public GameLevel askGameLevel() {
		return presenter.getGameLavel();
	}

	@Override
	public String askUserName() {
		return presenter.getUsername();
	}

	@Override
	public int askNextOption() {
		return presenter.getNextOption();
	}
	
	@Override
	public String askFileName() {
		return presenter.getFileName();
	}

	@Override
	public boolean doYouWantToRegisterYourScore() {
		return presenter.askToStoreScore();
	}

	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		presenter.showGlobalRanking( ranking );
	}

	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		presenter.showPersonalRanking( ranking );
	}

	@Override
	public void showGoodBye() {
		presenter.showMessage( "Thanks for using. Bye, bye!" );
	}

	@Override
	public void showErrorMessage(String message) {
		message = "There are some problem processing your option.\n"
				+ "Please, review and try again.\n"
				+ "Problem: " + message;
		presenter.showMessage( message );
	}

	@Override
	public void showFatalErrorMessage(String message) {
		message = "There are some technical problem processing your option.\n"
				+ "The program should stop.\n"
				+ "Problem: " + message;
		presenter.showFatalMessage( message );
	}

}
