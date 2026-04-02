package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;

/*
 * Sceanrios:
 * 1 - Null username -> IAE
 * 2 - Blank username IAR
 * 3 - General Ranking list is empty, user ranking, -> Empty list
 * 4 - General Ranking list has entries by nothing is form user -> Empty list
 * 5 - General ranking list has only user entries -> Same list content
 * 6 - General ranking with several entries, some from user
 */
public class GameRankingGetEntriesForUsernameTest {
	/**
	 * GIVEN: null username
	 * WHEN: getEntriesForUsername with null Username
	 * THEN: IllegalArgumentException
	 */
	@Test
	public void nullUser() {
		GameRanking gr = new GameRanking();
		assertThrows(IllegalArgumentException.class, ()-> gr.getEntriesForUsername(null));
	}
	/**
	 * GIVEN: blank username
	 * WHEN: getEntriesForUsername with blank Username
	 * THEN: IllegalArgumentException
	 */
	@Test
	public void BlankkUser() {
		GameRanking gr = new GameRanking();
		assertThrows(IllegalArgumentException.class, ()-> gr.getEntriesForUsername("   "));
	}
	/**
	 * GIVEN: a empty game ranking 
	 * WHEN: getEntriesForUsername 
	 * THEN: empty list is returned 
	 */
	@Test
	public void emptyList() {
		GameRanking gr = new GameRanking();
		assertTrue(gr.getEntriesForUsername("user").isEmpty());
	}
	/**
	 * GIVEN: game ranking contains only no user entries
	 * WHEN: getEntriesForUsername 
	 * THEN: empty list
	 */
	@Test
	public void onlyNotUserEntries() {
		
		GameRanking gr = new GameRanking();
		
		GameRankingEntry g1=new GameRankingEntry("user1", GameLevel.FACIL, 33, false);
		GameRankingEntry g2=new GameRankingEntry("user1", GameLevel.MEDIANO, 33, false);
		GameRankingEntry g3=new GameRankingEntry("user1", GameLevel.DIFICIL, 33, false);
		
		gr.append(g1);
		gr.append(g2);
		gr.append(g3);

		assertTrue(gr.getEntriesForUsername("user").isEmpty());
	}
	/**
	 * GIVEN: game ranking contains only user entries
	 * WHEN: getEntriesForUsername 
	 * THEN: same content
	 */
	@Test
	public void onlyUserEntries() {
		List<GameRankingEntry> expected= new ArrayList<>();
		GameRanking gr = new GameRanking();
		
		GameRankingEntry g1=new GameRankingEntry("user", GameLevel.FACIL, 33, false);
		GameRankingEntry g2=new GameRankingEntry("user", GameLevel.MEDIANO, 33, false);
		GameRankingEntry g3=new GameRankingEntry("user", GameLevel.DIFICIL, 33, false);
		
		gr.append(g1);
		gr.append(g2);
		gr.append(g3);

		expected.add(g1);
		expected.add(g2);
		expected.add(g3);
		List<GameRankingEntry> result = gr.getEntriesForUsername("user");
		
		assertEquals(expected, result);
	}
	/**
	 * GIVEN: game ranking contains mixed user entries
	 * WHEN: getEntriesForUsername 
	 * THEN: same content
	 */
	@Test
	public void mixedEntries() {
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
		gr.append(g6);

		expected.add(g1);
		expected.add(g2);
		expected.add(g3);
		List<GameRankingEntry> result = gr.getEntriesForUsername("user");
		
		assertEquals(expected, result);
	}
	
}
