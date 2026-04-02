package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;

/**
 * Scenarios:
 * 1 - Already exist Entry (same user and difficulty)
 * 2 - Do not exist Entry
 */
public class GameRankingAppendTest {
	/**
	 * 
	 */
	@Test
	public void exitingEntry() {
		GameRanking gr = new GameRanking();
		GameRankingEntry g1=new GameRankingEntry("user1", GameLevel.FACIL, 33, false);
		GameRankingEntry g2=new GameRankingEntry("user1", GameLevel.FACIL, 3333, true);
		gr.append(g1);
		
		
		int prevSize = gr.getAllEntries().size();
		long time1 = gr.getAllEntries().get(0).getDuration();
		
		gr.append(g2);
		
		long time2 = gr.getAllEntries().get(0).getDuration();
		int finalSize = gr.getAllEntries().size();
		
		

		assertNotEquals(time1,time2);
		assertEquals(prevSize,finalSize);
	}
	/**
	 * 
	 */
	@Test
	public void noExitingEntry() {
		GameRanking gr = new GameRanking();
		GameRankingEntry g1=new GameRankingEntry("user1", GameLevel.FACIL, 33, false);
		
		assertEquals(0,gr.getAllEntries().size());
		
		gr.append(g1);
		
		assertTrue(gr.getAllEntries().contains(g1));
		assertEquals(1,gr.getAllEntries().size());
	}
}
