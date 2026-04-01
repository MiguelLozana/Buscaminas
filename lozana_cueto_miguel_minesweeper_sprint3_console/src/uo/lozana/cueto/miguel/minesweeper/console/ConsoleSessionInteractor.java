package uo.lozana.cueto.miguel.minesweeper.console;

import java.util.List;


import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.lozana.cueto.miguel.minesweeper.session.SessionInteractor;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

public class ConsoleSessionInteractor implements  SessionInteractor{

	@Override
	public GameLevel askGameLevel() {
		showDifficulties();
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
			}
		}
		return null;	
	}
	@Override
	public String askUserName() {
		String name ="";
		boolean validName = false;
		do {
			try {
				name = Console.readString();
				validName = IsValidName(name);
			}catch(Exception e) {
				System.out.print("invalid name, try again, only lowercase ");
			}
		}while( !validName);
		return name;
	}

	@Override
	public int askNextOption() {
		showMenu();
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
	

	@Override
	public boolean doYouWantToRegisterYourScore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showGoodBye() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFatalErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean IsValidName(String name) {
		ArgumentChecks.isNotBlank(name);
		for (char c : name.toCharArray()) {
		    if (c < 'a' || c > 'z') {
		        throw new RuntimeException("InvalidName");
		    }
		}
		return false;
	}
	private void showMenu() {
	    System.out.println("\n==================== MENU ===================");
	    System.out.println(" 1 - Play a game");
	    System.out.println(" 2 - Check all players scores");
	    System.out.println(" 3 - Check my scores ");
	    System.out.println(" 0 - Exit"); // Cambiado a 0 según el PDF
	    System.out.println(" --------------------------------------------");
	}

	private void showDifficulties() {
	    System.out.println("\n .   .   .  DIFFICULTY  .   .   . ");
	    System.out.println("1. FACIL (9x9 12%)");
	    System.out.println("2. MEDIANO (16x16 15%)");
	    System.out.println("3. DIFICIL (30x16 20%)");
	    System.out.println(" .   .   .   .   .   .   .   .   . ");		
	}
}
