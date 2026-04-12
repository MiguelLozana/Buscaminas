package uo.lozana.cueto.miguel.minesweeper;

import uo.lozana.cueto.miguel.minesweeper.console.ConsoleGameInteractor;
import uo.lozana.cueto.miguel.minesweeper.console.ConsoleSessionInteractor;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.lozana.cueto.miguel.minesweeper.session.GameSession;
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
		session.setSessionInteractor( new ConsoleSessionInteractor() );
		session.setGameInteractor( new ConsoleGameInteractor() );
		session.setLogger( new FileLogger( LOG_FILE ) );
		session.setGameRanking( new GameRanking( RANKING_FILE ) );
		return this;
	} 

	private void run() {
		session.run();
	}
	}