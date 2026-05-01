package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uo.mp.minesweeper.util.log.BasicSimpleLogger;

/*
 * Sceanrios:
 * 1 - Crates the object with an empty list
 */
public class GameRankingConstructorTest {

	/**
	 * GIVEN: a new GameRanking with a non existing file
	 * WHEN: constructor
	 * THEN: empty list
	 */
	@Test
	public void emptyList() {
		GameRanking gr = new GameRanking("test_" + System.currentTimeMillis() + ".rnk");
		gr.setLogger(new BasicSimpleLogger());
		assertTrue(gr.getAllEntries().isEmpty());
	}
}