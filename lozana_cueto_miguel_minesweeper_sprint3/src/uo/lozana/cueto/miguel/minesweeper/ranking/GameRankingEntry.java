package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.time.LocalDateTime;
import java.util.Objects;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;

public class GameRankingEntry {
	private String userName;
	private GameLevel level;
	private long duration;
	private boolean hasWon;
	
public GameRankingEntry(String userName, GameLevel level, long duration, boolean hasWon){
		
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
	public LocalDateTime getDate() {
		LocalDateTime date = LocalDateTime.now();	
		return date;
	}
			
	public GameLevel getLevel() {
		return this.level;
				
	}
			
	public String toString() {
		return String.format("User: -> Level: %s  Duration: %s s  Date: %s  %s", 
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
	
	
			

}
