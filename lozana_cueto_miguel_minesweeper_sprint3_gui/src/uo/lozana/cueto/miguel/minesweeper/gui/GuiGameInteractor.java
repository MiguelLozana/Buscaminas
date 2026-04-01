package uo.lozana.cueto.miguel.minesweeper.gui;

import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.GameMove;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;

public class GuiGameInteractor implements GameInteractor {

	private Presenter presenter;
	/*
	 * =================================
	 * OUTPUT
	 * ================================
	 */


	@Override
	public void showGame(long elapsedTime, Board board) {
		if (presenter == null) {
			createPresenterAndView( board );
		}
		presenter.update(elapsedTime, board.getNumberOfFlagsLeft(),	board.getState());
	}

	@Override
	public void showGameFinished() {
		presenter.setMessage("Game Over");
	}

	@Override
	public void showCongratulations(long elapsedTime) {
		presenter.setMessage("You win!");
	}

	@Override
	public void showBooommm() {
		presenter.setMessage("BOOOMMMM");
	}

	@Override
	public void showReadyToStart() {
		presenter = null;
		// nothing to show for this user interface
	}
	/*=============================
	 * INPUT
	 *============================
	 */
	/**
	 * Ask for a valid move
	 * @param rows number of rows of the board
	 * @param rows number of columns of the board
	 */
	@Override
	public GameMove askMove(int heigth, int width) {
		return presenter.getNextMove();
	}
	/*===============================
	 * PRIVATE HELPER METHODS
	 * ===============================
	 */
	private void createPresenterAndView(Board board) {
		BoardView view = new BoardView(board.getNumberOfRows(), board.getNumberOfColumns());
		presenter = new Presenter();
		presenter.setView( view );		// bidirectional linking
		view.setPresenter( presenter );	// bidirectional linking
		presenter.show();
	}

	


}
