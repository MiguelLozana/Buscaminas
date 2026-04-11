package uo.mp.minesweeper.gui.session;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.util.check.ArgumentChecks;

/**
 * The SessionPresenter is the bridge between the SessionView and the SessionInteractor.
 * It provides the abstract presentation logic, and
 */
/*package*/ class SessionPresenter { 
	
	/**
	 * Any view must implement this interface, this avoids dependency of the
	 * presenter on the view 
	 */
	interface View {
		void showFatalMessage(String message);
		void showMessage(String message);
		String askFileName();
		int askGameLevel();
		boolean askToStoreScore();
		void clearUserName();
		void showRanking(String string, List<GameRankingEntry> ranking);

		void close();
		void open();
	}
	
	/*
	 * The SessionView and the Session are executed on different threads.
	 * This BlockingQueue implements the producer-consumer pattern.
	 * 	- Producer: the (swing) session view, produces options (Integer)
	 *  - Consumer: the GuiInteractor consumes them when asked by the Session
	 */
	private BlockingQueue<Integer> optionsQueue = new LinkedBlockingQueue<>();
	
	/**
	 * A latch to block the session thread until the SessionView (the user) 
	 * had provided the userName  
	 */
	private CountDownLatch userNameReady;

	private View view;
	private String userName;
	
	public void setView(View view) {
		ArgumentChecks.isTrue( view != null );
		this.view = view;
	}

	public void show() {
		view.open();
	}

	/**
	 * Will block the thread until the user had provided this value
	 * @return
	 */
	public String getUsername() {
		view.clearUserName();
		userNameReady = new CountDownLatch( 1 );
		try {
			userNameReady.await();
		} catch (InterruptedException e) {
			throw new RuntimeException("Concurrency error");
		}
		return userName;
	}
	
	/**
	 * This is called by the SessionView when the user provides the userName, it will unblock
	 * Called from the Swing thread (EDT)
	 * @param userName
	 */
	/*package*/ void setUserName(String userName) {
		this.userName = userName;
		userNameReady.countDown();
	}

	public GameLevel getGameLavel() {
		int index = view.askGameLevel();
		return GameLevel.values()[ index ];
	}

	public boolean askToStoreScore() {
		return view.askToStoreScore();
	}

	public String getFileName() {
		return view.askFileName();
	}

	public int getNextOption() {
		try {
			return optionsQueue.take();
		} catch (InterruptedException ex) {
			return -1; // Non existent option, means do nothing
		}
	}

	public void optionSelected(int opt) {
		try {
			optionsQueue.put( opt );
		} catch (InterruptedException ignored) {
			// intentionally ignored
		}
	}

	public void showGlobalRanking(List<GameRankingEntry> ranking) {
		view.showRanking("Global", ranking);
	}

	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		view.showRanking("Personal", ranking);
	}

	public void showMessage(String message) {
		view.showMessage( message );
	}

	public void showFatalMessage(String message) {
		view.showFatalMessage( message );
		view.close();
	}

}
