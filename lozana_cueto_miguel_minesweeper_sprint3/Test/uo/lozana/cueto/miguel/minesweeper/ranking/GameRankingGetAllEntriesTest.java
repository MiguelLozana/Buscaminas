package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;

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
		GameRanking gr = new GameRanking();
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
		GameRanking gr = new GameRanking();
		
		GameRankingEntry g1=new GameRankingEntry("user", GameLevel.FACIL, 33, false);
		GameRankingEntry g2=new GameRankingEntry("user", GameLevel.MEDIANO, 33, false);
		GameRankingEntry g3=new GameRankingEntry("user", GameLevel.DIFICIL, 33, false);
		GameRankingEntry g4=new GameRankingEntry("OtherUser", GameLevel.FACIL, 33, false);
		GameRankingEntry g5=new GameRankingEntry("OtherUser", GameLevel.MEDIANO, 33, false);
		GameRankingEntry g6=new GameRankingEntry("OtherUser", GameLevel.DIFICIL, 33, false);
		
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
		
		assertEquals(expected, result);
		expected.add(g6);
		assertNotEquals(expected, result);
		
	}
}
	


