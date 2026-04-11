package uo.lozana.cueto.miguel.minesweeper.util.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import uo.mp.minesweeper.util.log.SimpleLogger;
import uo.mp.util.check.ArgumentChecks;

public class FileLogger implements SimpleLogger{
	
	//
	// Constants y attributes 
	//
	public static final String FILENAME_DEF = "minesweeper.log";
	public static final String ERROR_LOGING_MENSAGGE = "FATAL ERROR: was not possible to log, "
														+ "the program is not able to continue";
	private String fileName;
	
	
	//
	// COSNTRUCTORES 
	//
	
	public FileLogger(String fileName) {
	    ArgumentChecks.isNotBlank(fileName);
	    this.fileName = fileName;
	}
	public FileLogger() {
	    this(FILENAME_DEF);
	}

	
	//
	// Methdos
	//
	
	@Override
	public void log(Exception e) {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		String date = "[" + currentDate.format(formatter).toString() + "]: "; //there is a space behind
		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			
			writer.write(date + e.getMessage() +"\n");
			
		} catch (IOException exception) {
			throw new RuntimeException(ERROR_LOGING_MENSAGGE,e);
		}
		
	}
}
