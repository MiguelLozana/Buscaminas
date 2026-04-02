package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameRankingConstructorTest {
	/**
	 * Sceanrios:
	 * 1 - Crates the object with an empty list
	 */
	@Test
	public void emptyList() {
		GameRanking gr = new GameRanking();
		assertTrue(gr.getAllEntries().isEmpty());
	}
}
