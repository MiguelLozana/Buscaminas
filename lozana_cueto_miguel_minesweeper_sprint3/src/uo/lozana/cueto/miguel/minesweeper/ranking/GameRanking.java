package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

public class GameRanking {
	private List<GameRankingEntry> ranking;
	
	public GameRanking() {
		ranking = new ArrayList<GameRankingEntry>();
	}
	
	public void append(GameRankingEntry gameRankingEntry) {
		int index = ranking.indexOf(gameRankingEntry);
		if (index != -1) {
			ranking.remove(index);
		}
		ranking.add(gameRankingEntry);
	}
	
	
	
	public List<GameRankingEntry> getAllEntries(){
		return new ArrayList<>(ranking);
	}
	
	
	
	
	public List<GameRankingEntry> getEntriesForUsername(String userName){
		ArgumentChecks.isNotBlank(userName);
		List<GameRankingEntry> userRanking = new ArrayList<GameRankingEntry>();
		for(GameRankingEntry entry : ranking) {
			if (entry.getUserName().equals(userName)) {
				userRanking.add(entry);
			}
		}
		return userRanking;
	}
	
	
}
