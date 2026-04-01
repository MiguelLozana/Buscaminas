package uo.lozana.cueto.miguel.minesweeper;

import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.gui.GuiGameInteractor;

public class MainEasy {

	private Game game;

	public static void main(String[] args) {
		new MainEasy()
			.configure()
			.run();
	}

	private MainEasy configure() {
		Board board = new Board(
				9 /*cols*/,
				9 /*rows*/,
				12.0 /*% of mines*/
			);

		game = new Game( board );
		game.setInteractor( new GuiGameInteractor());
		return this;
	}

	private void run() {
		game.play();
	}

}