package uo.lozana.cueto.miguel.minesweeper.console;

/**
 * Utility class to add ANSI color codes to strings for console output.
 */
public class Ansi {

	public static String green(String string) {
		return "\u001B[32m" + string + "\u001B[0m";
	}

	public static String green(char c) {
		return green(String.valueOf(c));
	}

	public static String black(String string) {
		return "\u001B[30m" + string + "\u001B[0m";
	}

	public static String black(char c) {
		return black(String.valueOf(c));
	}

	public static String grey(String string) {
		return "\u001B[90m" + string + "\u001B[0m";
	}

	public static String grey(char c) {
		return grey(String.valueOf(c));
	}

	public static String blue(String string) {
		return "\u001B[34m" + string + "\u001B[0m";
	}

	public static String blue(char c) {
		return blue(String.valueOf(c));
	}

	public static String red(String string) {
		return "\u001B[31m" + string + "\u001B[0m";
	}

	public static String red(char c) {
		return red( String.valueOf(c) );
	}

	public static String lightGrey(String string) {
		return "\u001B[37m" + string + "\u001B[0m";
	}

	public static String lightGrey(char c) {
		return lightGrey( String.valueOf(c) );
	}

	private Ansi() {
		// This is an utilities class and cannot be instatiated
	}
}