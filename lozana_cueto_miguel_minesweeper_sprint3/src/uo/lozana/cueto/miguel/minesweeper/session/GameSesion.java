package uo.lozana.cueto.miguel.minesweeper.session;


import java.util.logging.Logger;

import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

public class GameSesion {
	private String name;
	private GameInteractor gi;
	private SessionInteractor si;
	private Logger logger;
	private GameRanking ranking;
	
	
	public GameSesion() {
		getName();
		handleMenu();
	}
	
	
	
	
	
	
	





	/*
	 * Ask for a valid name until one valid is provided (not blank)
	 */
	private void getName(){
		
		boolean validName = false;
		
		while(!validName) {
			name = Console.readString("Type your name: ");
			try {
				ArgumentChecks.isNotBlank(name);
			}catch (IllegalArgumentException e) {
				System.out.println("Incorrect name, try again");
				getName();
			}
		}	
	}
	
	
	
	
	private void handleMenu() {
		showMenu();
		int option = askForOption();
		handleOption(option);
	}




	private void showMenu() {
		System.out.println("==================== MENU ===================");
		System.out.println(" 1 - Play a game");
		System.out.println(" 2 - Check all players scores");
		System.out.println(" 3 - Chech my scores ");
		System.out.println(" 4 - Sign out");
		System.out.println(" --------------------------------------------");
		
		
	}
	
	/*
	 * Ask for a valid option(1 to 4) until one valid is provided
	 */
	private int askForOption() {
		
		int option = -1;
		while (option < 1 || option > 4) {
			try {
				 option = Console.readInt("Select your option");
			}
			catch (Exception e) {
				System.out.println("Incorrect option, type only a number (1 to 4)");
			}
		}
		return option;

	}
	
	
	
	private void handleOption(int option) {
		switch (option) {
		case 1 -> playGame();
		case 2 -> playGame();
		case 3 -> playGame();
		case 4 -> playGame();
		default -> throw new RuntimeException();
		}
		
	}












	private Object playGame() {
		showDifficulties();
		askForDifficulty();
		
		Game game = new Game();
		game.play();
		return null;
	}

	
private void showDifficulties() {
	System.out.println(" .   .   .  DIFFICULTY  .   .   . ");
	System.out.println("FACIL (9x9 12%)");
	System.out.println("MEDIANO (16x16 15%)");
	System.out.println("DIFICIL (30x16 20%)");
	System.out.println(" 4 - Sign out");
	System.out.println(" .   .   .   .   .   .   .   .   . ");
	

		
	}












/*
 * Ask for a valid option(1 to 4) until one valid is provided
 */
private GameLevel askForDifficulty() {		
	
		boolean validLevel = false;
		
		while(!validLevel) {
			String level = Console.readString("Type your the level : ");
			try {
				return switch (level) {
			    case "FACIL" -> GameLevel.FACIL;
			    case "MEDIANO" -> GameLevel.MEDIANO;
			    case "DIFICIL" -> GameLevel.DIFICIL;
			    default -> throw new RuntimeException("Nivel no reconocido");
				};
				
			}catch (IllegalArgumentException e) {
				System.out.println("Incorrect name, try again");
				getName();
			}
		}
		return null;	
	}
}