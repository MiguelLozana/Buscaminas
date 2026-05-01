package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.mp.util.check.ArgumentChecks;

public class BlowUpAction implements Action{
	
	private Board board;
	
	
	/**
	 * Creates the blowUpAction with a board provided
	 * @param board
	 */
	public BlowUpAction(Board board) {
		ArgumentChecks.isNotNull(board);
		this.board = board;
	}
	/**
	 * Executes the blow up action (the board explodes)
	 */
	@Override
	public void execute() {
		board.markAsExploded();
	}
	/**
	 * Checks if is ClearAction
	 */
	@Override
	public boolean isClearAction() {
		return false;
	}
	@Override
	public boolean isBlowUpAction() {
		return true;
	}
	@Override
	public boolean isNullAction() {
		return false;
	}

}
