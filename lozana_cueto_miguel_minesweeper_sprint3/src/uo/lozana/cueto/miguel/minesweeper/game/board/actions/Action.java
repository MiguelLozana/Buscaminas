package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

public interface Action {
	/**
	 * Exectues the action
	 */
	void execute();
	/**
	 * Cheks the action type
	 */
	boolean isClearAction();
	boolean isBlowUpAction();
	boolean isNullAction();
}
