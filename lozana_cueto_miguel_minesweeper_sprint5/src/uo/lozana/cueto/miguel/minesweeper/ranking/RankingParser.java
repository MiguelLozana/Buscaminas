package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.log.SimpleLogger;
import uo.mp.util.check.ArgumentChecks;

public class RankingParser {
	
	private  SimpleLogger logger;
	
	
	public RankingParser(SimpleLogger logger) {
	    ArgumentChecks.isNotNull(logger);
	    this.logger = logger;
	    
	}
	
	/**
	 * Creates a list of gameRankingEntries form a list of String 
	 * @param list form create a gameRankingEntries list 
	 * @return gameRankingEntries list 
	 */
	public List<GameRankingEntry> parse(List<String> list) {
		ArgumentChecks.isNotNull(list);
		List<GameRankingEntry> entries = new ArrayList<>();
		
		for(String line : list) {
				try {
		            entries.add(parseLine(line));
				} catch (InvalidLineException e) {
					logger.log(e);
				}
			
		}
		return entries;
	}
	
	//Parser ONE LINE
	private GameRankingEntry parseLine(String line) throws InvalidLineException {
		try {
			ArgumentChecks.isNotBlank(line);
		}catch(IllegalArgumentException e) {
			throw new InvalidLineException(e.getMessage());
		}
		String[] content = line.split(";");
		if (content.length != 6) {
	        throw new InvalidLineException("Invalid number of fields: " + line);
	    }
		
		String userName = content[0].trim();
		LocalDateTime date = getDate(content[1].trim(),content[2].trim());
	    GameLevel level = getLevel(content[3].trim());
	    Boolean result = isWon(content[4].trim());
	    Long duration = getDuration(content[5].trim());
	    
		try {
			return new GameRankingEntry(userName, date, level, duration, result);
		} catch (Exception e) {
			throw new InvalidLineException("Error while trying to create the object");
		}
		
	}

	/*============
	 * Conversioon methods
	 * =============
	 */
	 //date is considered as day and time
	private LocalDateTime getDate(String strDate,String strTime) throws InvalidLineException {
		
		try {
			DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate date = LocalDate.parse(strDate, fd); 
			DateTimeFormatter fh = DateTimeFormatter.ofPattern("HH:mm:ss"); 
			LocalTime time = LocalTime.parse(strTime, fh); 
			LocalDateTime datetime = LocalDateTime.of(date, time);
			return datetime;
		}catch(RuntimeException e) {
			throw new InvalidLineException("Error while converting time" + e.getMessage());
		}
	}
	private GameLevel getLevel(String strLevel) throws InvalidLineException {
		try {
			return GameLevel.valueOf(strLevel.toUpperCase());
		}catch(RuntimeException e) {
			throw new InvalidLineException("Error while converting Level" + e.getMessage());
		}
	}
	private Boolean isWon(String strResult) throws InvalidLineException {
		ArgumentChecks.isNotBlank(strResult);
		return switch (strResult.toUpperCase()) {
		case "WIN" -> true;
		case "LOOSE" -> false;
		default ->
			throw new InvalidLineException("Unexpected result value: " + strResult);
		};
	}
	private long getDuration(String strDuration) throws InvalidLineException {
		try {
			return Long.valueOf(strDuration);
		}catch(RuntimeException e) {
			throw new InvalidLineException("Error while converting duration" + e.getMessage());
		}
	}
	
}


@SuppressWarnings("serial")
class InvalidLineException extends Exception {
	public InvalidLineException(String message) {
		super(message);
	}
}
