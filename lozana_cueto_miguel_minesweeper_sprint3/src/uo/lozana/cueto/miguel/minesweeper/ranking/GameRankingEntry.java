package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.util.check.ArgumentChecks;

public class GameRankingEntry implements Serializable {

	private String userName;
	private GameLevel level;
	private long duration;
	private boolean hasWon;
	private LocalDateTime completeDate;
	/*==================
	 * CONSRTRUCTOR
	 ==================*/
	/**
	 * Instances the object with all game data
	 * @param userName the name of the user who played the game
	 * @param completeDate the day and hour when were played the game
	 * @param level the level played
	 * @param duration the duration playing
	 * @param hasWon true if has won, false otherwise
	 */
	public GameRankingEntry(String userName,LocalDateTime completeDate, GameLevel level, long duration, boolean hasWon) {
		super();
		ArgumentChecks.isNotBlank(userName, "UserName cannot be blank");
	    ArgumentChecks.isNotNull(completeDate, "Date cannot be null");
	    ArgumentChecks.isNotNull(level, "Level cannot be null");
	    ArgumentChecks.isTrue(duration > 0, "Duration must be higher than 0 ");
		
		this.userName = userName;
		this.level = level;
		this.duration = duration;
		this.hasWon = hasWon;
		this.completeDate = completeDate;
	}

	/*==================
	 * GETTERS
	 ==================*/		  
			
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
	/*==================
	 * PUBLIC METHODS
	 ==================*/		
	public String toString() {
		return String.format("User: %s -> Level: %s  Duration: %s s  Date: %s  %s", 
				getUserName(),getLevel(),getDurationString(),getDate().toString(),getResult());		
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

	/**
	 * @return username;date;time;level;result;duration
	 */
	public String serialize() {
		return String.format("%s;%s;%s;%s;%s;%s ", 
				getUserName(),getDate(),getTime(),getLevel(),getResult(),getDurationString());
	}
	
	
	
	/*==================
	 * PRIVATE HELPING METHODS
	 ==================*/
	
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


	

}
