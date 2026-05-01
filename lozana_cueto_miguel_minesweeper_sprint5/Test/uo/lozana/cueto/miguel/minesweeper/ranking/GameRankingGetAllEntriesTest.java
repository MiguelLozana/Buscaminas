package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.log.BasicSimpleLogger;

/*
 * Sceanrios:

 * 1 - Ranking list is empty -> Empty list
 * 2 - Ranking several entries -> Same content, independent list
 * 
 */
public class GameRankingGetAllEntriesTest {
	/**
	 * GIVEN: a empty game ranking 
	 * WHEN: getAllEntries 
	 * THEN: empty list is returned 
	 */
	@Test
	public void emptyList() {
	    GameRanking gr = new GameRanking("test_" + System.currentTimeMillis() + ".rnk");
	    gr.setLogger(new BasicSimpleLogger());
	    assertTrue(gr.getAllEntries().isEmpty());
	}
	/**
	 * GIVEN: game ranking contains only no user entries
	 * WHEN: getAllEntries 
	 * THEN: empty list
	 */
	@Test
	public void independentList() {
		
		List<GameRankingEntry> expected= new ArrayList<>();
		GameRanking gr = new GameRanking("file");
		
		GameRankingEntry g1=new GameRankingEntry("user", LocalDateTime.of(2025, 5, 20, 15, 30, 0),GameLevel.FACIL, 33, true);
		GameRankingEntry g2=new GameRankingEntry("user",LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.MEDIANO, 33, true);
		GameRankingEntry g3=new GameRankingEntry("user",LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.DIFICIL, 33, true);
		GameRankingEntry g4=new GameRankingEntry("OtherUser",LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.FACIL, 33, true);
		GameRankingEntry g5=new GameRankingEntry("OtherUser",LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.MEDIANO, 33, true);
		GameRankingEntry g6=new GameRankingEntry("OtherUser",LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.DIFICIL, 33, true);
		gr.append(g1);
		gr.append(g2);
		gr.append(g3);
		gr.append(g4);
		gr.append(g5);
		

		expected.add(g1);
		expected.add(g2);
		expected.add(g3);
		expected.add(g4);
		expected.add(g5);
		
		
		
		List<GameRankingEntry> result = gr.getAllEntries();
		
		assertEquals(5, result.size());
	    assertTrue(result.containsAll(List.of(g1, g2, g3, g4, g5)));

	    result.add(g6);
	    assertEquals(5, gr.getAllEntries().size());
		
	}
}
	


