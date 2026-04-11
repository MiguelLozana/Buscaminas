package lozana.cueto.miguel.minesweeper;

import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.lozana.cueto.miguel.minesweeper.session.GameSession;
import uo.mp.minesweeper.gui.game.GuiGameInteractor;
import uo.mp.minesweeper.gui.session.GuiSessionInteractor;
import uo.lozana.cueto.miguel.minesweeper.util.log.FileLogger;



public class Main {
	private static final String RANKING_FILE = "minesweeper.rnk";
	private static final String LOG_FILE = "minesweeper.log";

	private GameSession session;

	public static void main(String[] args) {
		new Main()
			.configure()
			.run();
	}

	private Main configure() {
		session = new GameSession();
		session.setSessionInteractor( new GuiSessionInteractor() );
		session.setGameInteractor( new GuiGameInteractor() );
		session.setLogger(new FileLogger(LOG_FILE));
		
		session.setGameRanking( new GameRanking( RANKING_FILE ) );
		return this;
	}

	private void run() {
		session.run();
	}

}
