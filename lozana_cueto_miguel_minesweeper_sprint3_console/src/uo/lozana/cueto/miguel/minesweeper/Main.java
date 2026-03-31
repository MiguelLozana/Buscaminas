package uo.lozana.cueto.miguel.minesweeper;

import uo.lozana.cueto.miguel.minesweeper.console.ConsoleGameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;


public class Main {

	private Game game;

	public static void main(String[] args) {
		new Main()
			.configure()
			.run();
	}

	private Main configure() {
		Board board = new Board(
				9 /*cols*/, 
				9 /*rows*/, 
				12.0 /*% of mines*/
			);
		game = new Game( board );
		game.setInteractor( new ConsoleGameInteractor() );
		return this;
	}

	private void run() {
		game.play();
	}

}
