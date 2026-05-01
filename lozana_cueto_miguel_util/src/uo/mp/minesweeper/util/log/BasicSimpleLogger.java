package uo.mp.minesweeper.util.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;


public class BasicSimpleLogger implements SimpleLogger {

	private static PrintStream out = createPrintStream();

	private static PrintStream createPrintStream() {
		try {
			return new PrintStream(new FileOutputStream("log.txt", true /* APPEND*/));
		} catch (FileNotFoundException e) {
			return System.err;
		}
	}
	/**
	 * Sends the full stack trace of the exception received to the log
	 * prefixing it with a timestamp 
	 * @param t, the exception to be logged
	 */
	@Override
	public void log(Exception ex) {
		out.print( new Date()  + " ");
		ex.printStackTrace( out );
	}
	/**
	 * Sends the string received as message to the log, prefixing it with 
	 * a timestamp 
	 * @param message
	 */
	@Override
	public void log(String message) {
		out.print( new Date()  + " ");
		out.println( message );
	}
}

