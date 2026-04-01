package uo.lozana.cueto.miguel.minesweeper.session;

public interface SessionInteractor {
	
	GameLevel askForDifficulty();
	void showDifficulties();
    void showMenu();
    void AskForName();
}
