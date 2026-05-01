package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

import java.util.List;

import uo.lozana.cueto.miguel.minesweeper.game.board.Square;
import uo.mp.util.check.ArgumentChecks;

public class ClearAction implements Action {

	private List<Square> neigbours;

	/**
	 * Creates a ClearAction with a list of squares provided
	 * @param neigbours the surrounding squares
	 */
	public ClearAction(List<Square> neigbours) {
		ArgumentChecks.isNotNull(neigbours);
		this.neigbours = neigbours;
	}

	/**
	 * Executes the ClearAction (stepsOn all the neigbours)
	 */
	@Override
	public void execute() {
		for (Square square : neigbours) {
			square.stepOn();
		}
	}
	@Override
	public boolean isClearAction() {
		return true;
	}

	@Override
	public boolean isBlowUpAction() {
		return false;
	}

	@Override
	public boolean isNullAction() {
		return false;
	}

}
