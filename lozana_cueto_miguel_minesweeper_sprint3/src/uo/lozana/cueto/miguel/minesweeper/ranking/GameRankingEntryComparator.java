package uo.lozana.cueto.miguel.minesweeper.ranking;

import java.util.Comparator;

public class GameRankingEntryComparator implements Comparator<GameRankingEntry> {
	/**
	 * Compares two GameRankingEntry and returns a number indicating which goes fist (higher)
	 * @return which goes fist (higher), -1 o1 goes first, 1 o2 goes first
	 */
	@Override
	public int compare(GameRankingEntry o1, GameRankingEntry o2) {
		
		// comparing level, More difficult higher, easier lower
		int levelDiff = o2.getLevel().compareTo(o1.getLevel());
	    if (levelDiff != 0) {
	        return levelDiff;
	    }
		
		
		//compare duration, less duration higher
	    long durationDiference = o1.getDuration()-o2.getDuration();
	    if (durationDiference >0) {
	    	return 1;
	    }else if(durationDiference<0){
	    	return -1;
	    }
	    
	  //compare duration, ancient first
	    return o1.getDate().compareTo(o2.getDate());
	}

}
