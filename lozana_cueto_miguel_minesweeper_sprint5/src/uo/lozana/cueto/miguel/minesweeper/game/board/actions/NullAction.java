package uo.lozana.cueto.miguel.minesweeper.game.board.actions;

public class NullAction implements Action{
	/** 
	 * Empty method
	 */
	@Override
    public void execute() {
        
    }


	@Override
	public boolean isClearAction() {
		return false;
	}

	@Override
	public boolean isBlowUpAction() {
		return false;
	}

	@Override
	public boolean isNullAction() {
		return true;
	}
}
