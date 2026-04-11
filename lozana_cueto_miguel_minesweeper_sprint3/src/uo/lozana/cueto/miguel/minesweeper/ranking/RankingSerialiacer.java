package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.util.LinkedList;
import java.util.List;

public class RankingSerialiacer {
	public RankingSerialiacer() {
		
	}
	
	
	public List<String> serialize(List<GameRankingEntry> ranking) {
		List<String> lines = new LinkedList<>();
        for(GameRankingEntry entry: ranking) {
        	lines.add(entry.serialize());
        }
        return lines;
	}
}
