package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.util.check.ArgumentChecks;

public class GameRankingEntry implements Serializable {
	
	
	/**
	 * 
	 */
	
	private String userName;
	private GameLevel level;
	private long duration;
	private boolean hasWon;
	private LocalDateTime completeDate;
	
public GameRankingEntry(String userName,LocalDateTime completeDate, GameLevel level, long duration, boolean hasWon) {
		super();
		
		ArgumentChecks.isNotBlank(userName);
		ArgumentChecks.isNotNull(level);
		ArgumentChecks.isTrue(duration>0);
		
		
		this.userName = userName;
		this.level = level;
		this.duration = duration;
		this.hasWon = hasWon;
		this.completeDate = completeDate;
	}

			  
			
	public String getUserName() {
		return this.userName;
	}
	public long getDuration() {
		return this.duration;
	}
	public boolean hasWon() {
		return this.hasWon;
	}
	public LocalDate getDate() {
		return this.completeDate.toLocalDate();
	}
	public LocalTime getTime() {
		return this.completeDate.toLocalTime();
	}
			
	public GameLevel getLevel() {
		return this.level;
				
	}
			
	public String toString() {
		return String.format("User: %s -> Level: %s  Duration: %s s  Date: %s  %s", 
				getUserName(),getLevel(),getDurationString(),getDate().toString(),getResult());
				
	}
	
	private String getDurationString() {
		long min = duration / 60;
		long seg = duration % 60;
		
		return String.format("%dmin %ds", min,seg);
		}
	private String getResult() {
		if(hasWon) {
			return "WIN";
		}
		return "LOOSE";
	}


	@Override
	public int hashCode() {
		return Objects.hash(level, userName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameRankingEntry other = (GameRankingEntry) obj;
		return level == other.level && Objects.equals(userName, other.userName);
	}


	public String serialize() {
		return String.format("%s;%s;%s;%s;%s;%s ", 
				getUserName(),getDate(),getTime(),getLevel(),getResult(),getDuration());
	}
	


	
	
	
	
			

}
