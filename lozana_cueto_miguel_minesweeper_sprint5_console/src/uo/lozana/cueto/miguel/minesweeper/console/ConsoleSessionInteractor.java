package uo.lozana.cueto.miguel.minesweeper.console;

import java.time.format.DateTimeFormatter;
import java.util.List;


import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.lozana.cueto.miguel.minesweeper.session.SessionInteractor;
import uo.lozana.cueto.miguel.minesweeper.session.GameException;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

public class ConsoleSessionInteractor implements  SessionInteractor{
	private String user;
	public ConsoleSessionInteractor() {
	}
	/* ===================================
	 * 
	 *     INTPUT
	 * 
	 ====================================*/
	
	/**
	 * Ask the user the level/difficulty 
	 * @return the response a a GameLevel
	 */
	@Override
	public GameLevel askGameLevel() {
		boolean isValidLevel = false;
		GameLevel selected = null;
		
		
		do {
			showDifficulties();
			String level = Console.readString("Type your the level : ");
			
			
			try {
				 selected = switch (level) {
			    	case "FACIL" -> GameLevel.FACIL;
			    	case "MEDIANO" -> GameLevel.MEDIANO;
			    	case "DIFICIL" -> GameLevel.DIFICIL;
			    	default -> throw new GameException("Wrong Level Selected");
			    };
			    isValidLevel = true;
			}catch(GameException e) {
				Console.println("Invalid option, FACIL / MEDIANO / DIFICIL");
			}
			
			
		}while(!isValidLevel);
			return selected;
	}
	/**
	 * Ask the user the userName. It cannot be blank
	 * @return the valid userName
	 */
	@Override
	public String askUserName() {
		String name ="";
		boolean validName = false;
		
		do {
			try {
				name = Console.readString("Typer your user name");
				
				validName = isValidName(name);
			}catch (GameException e) {
				Console.print("Invalid name, try again, only lowercase");
			}catch(RuntimeException e) {
				Console.print("The name could not be read, Try again.");
			}
		}while( !validName);
		this.user = name;
		return name;
	}
	 /**
     * Ask the user to choose an option in the menu. 0 = exist; >0 valid menu options
     * @return the number which represent the menu option 
     */
	@Override
	public int askNextOption() {
		int option = -1;
		do {
			showMenu();
	        try {
	             option = Console.readInt("Select your option");
	        }
	        catch (RuntimeException e) {
	            Console.println("Incorrect option, type only a number (0 to 5 included)");
	        }
	    }while (option < 0 || option > 3);
	    return option;
	}
	
	/**
	 * Ask the user to decide to register the score
	 * @return true if decide to register, false otherwise
	 */
	@Override
	public boolean doYouWantToRegisterYourScore() {
		boolean isAlreadyanswered = false;
		boolean userAnswer = false;
		do {
			try { 
				userAnswer = Console.readBoolean("Do you want to register your score? (Y/N) " );
				isAlreadyanswered =true;
			}catch(RuntimeException re) {
				Console.printError("Invalid option, type (Y) for yes or (N) for no");
			}
		}while(!isAlreadyanswered);
		return userAnswer;
			
			
		
	}
	/* ===================================
	 * 
	 *     OUTPUT
	 * 
	 ====================================*/
	
	/**
	 * Shows all the elements of the list provided
	 * @param ranking the list to show
	 */
	
	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		String baseFormat = "%-10s %11s %10s %8s %6s %6s\n";
		
		Console.print(Ansi.blue(String.format(baseFormat, "User", "Date", "Hour", "Level", "Res", "Time")));
		
		for (GameRankingEntry entry : ranking) {
			String result = "LOOSE";
			
			String user = entry.getUserName();
			String date = entry.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String hour = entry.getDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			String level= entry.getLevel().toString();
			if (entry.hasWon()) {
				 result = "WIN";
			}
			String time = "" + entry.getDuration();
			
			Console.print(String.format(baseFormat,user,date,hour,level,result,time)); 
		}
		
	}
	/**
	 * Shows only the user ranking form the list provided
	 * @param ranking the list to show
	 */
	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking){
	String baseFormat ="%11s %10s %8s %6s %6s\n";
		
		Console.print(Ansi.blue(String.format(baseFormat, "Date", "Hour", "Level", "Res", "Time")));
		
		for (GameRankingEntry entry : ranking) {
			if( entry.getUserName().equals(user)) {
				String result = "LOOSE";
				
				String date = entry.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String hour = entry.getDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
				String level= entry.getLevel().toString();
				if (entry.hasWon()) {
				result = "WIN";
				}
				String time = "" + entry.getDuration();
			
				Console.print(String.format(baseFormat,date,hour,level,result,time)); 
			}
		}
	}
	/**
	 * Shows the GoodBy message
	 */
	@Override
	public void showGoodBye() {
		Console.println(Ansi.green("Have a nice day!! See you soon :) "));
		
	}
	/**
	 * Shows the error message to the user
	 * @param message to show the user
	 */
	@Override
	public void showErrorMessage(String message) {
		Console.println("Error: " +message);
		
	}
	/**
	 * Shows the fatal error message to the user, the program will not continue
	 * @param message to show the user
	 */
	@Override
	public void showFatalErrorMessage(String message) {
		Console.println(Ansi.red("FATAL Error: " +message));
		Console.println(Ansi.red("Progam is not able to continue"));
	}
	
	@Override
	public String askFileName() {
		
		return Console.readString("Filename: ");
	}
	
	/* ===================================
	 * 
	 *     PRIVATE HELPING METHODS
	 * 
	 ====================================*/
	
	private boolean isValidName(String name) throws GameException {
		ArgumentChecks.isNotBlank(name);  // if its incorrect raises an IllegalArgumentException
		for (char c : name.toCharArray()) {
		    if (c < 'a' || c > 'z') {
		        throw new GameException("InvalidName");
		    }
		}
		return true;
	}
	private void showMenu() {
	   Console.println("\n==================== MENU ===================");
	   Console.println(" 0 - Exit");
	   Console.println(" 1 - Play a game");
	   Console.println(" 2 - Check all players scores");
	   Console.println(" 3 - Check my scores ");
	   Console.println(" 4 - Export results ");
	   Console.println(" 5 - Import results ");
	   Console.println(" --------------------------------------------");
	}

	private void showDifficulties() {
	    Console.println("\n .   .   .  DIFFICULTY  .   .   . ");
	    Console.println("1. FACIL (9x9 12%)");
	    Console.println("2. MEDIANO (16x16 15%)");
	    Console.println("3. DIFICIL (30x16 20%)");
	    Console.println(" .   .   .   .   .   .   .   .   . ");		
	}

	
}
