package uo.lozana.cueto.miguel.minesweeper.util.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import uo.mp.minesweeper.util.log.SimpleLogger;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.file.FileUtil;

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
		ArgumentChecks.isNotNull(e);
		log(e.getMessage());
	}

		
		
		
	@Override
	public void log(String message) {
		ArgumentChecks.isNotBlank(message);
		message = getFormatedDate() + message +"\n";
		
		List<String> messageList= List.of(message);
		new FileUtil().WriteLines(fileName, messageList, true); // append mode active
	}
	private String getFormatedDate() {
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	    return String.format("[%s] : ", now.format(formatter));
	    
		 
	}
}

