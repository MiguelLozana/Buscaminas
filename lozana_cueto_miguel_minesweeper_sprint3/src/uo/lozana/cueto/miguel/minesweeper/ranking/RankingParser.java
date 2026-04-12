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
				} catch (Exception e) {
					logger.log(e);
				}
			
		}
		return entries;
	}
	
	private GameRankingEntry parseLine(String line) {
		ArgumentChecks.isNotBlank(line);
		String[] content = line.split(";");
		
		String userName = content[0].trim();
		LocalDateTime date = getDate(content[1].trim(),content[2].trim());
	    GameLevel level = getLevel(content[3].trim());
	    Boolean result = getResult(content[4].trim());
	    Long duration = getDuration(content[5].trim());
	    
		return new GameRankingEntry(userName,date, level,  duration,  result);
		
	}

	private LocalDateTime getDate(String strDate,String strTime) {
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate date = LocalDate.parse(strDate, fd); 
		DateTimeFormatter fh = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		LocalTime time = LocalTime.parse(strTime, fh); 
		LocalDateTime datetime = LocalDateTime.of(date, time);
		return datetime;
	}
	private GameLevel getLevel(String strLevel) {
		return GameLevel.valueOf(strLevel.toUpperCase());
	}
	private Boolean getResult(String strResult) {
		ArgumentChecks.isNotBlank(strResult);
		return switch (strResult.toUpperCase()) {
		case "WON" -> true;
		case "LOST" -> true;
		default ->
			throw new IllegalArgumentException("Unexpected value: " + strResult);
		};
	}
	private long getDuration(String strDuration) {
		return Long.valueOf(strDuration);
	}
	
}
