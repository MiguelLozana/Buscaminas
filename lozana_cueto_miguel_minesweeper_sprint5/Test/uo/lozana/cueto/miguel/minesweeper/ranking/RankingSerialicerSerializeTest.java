package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;

class RankingSerialicerSerializeTest {
	/*
	 * Scenarios:
	 * 1 - No ranking entries in list
	 * 2 - Varied entry list
	 */
	@Test
	void noRankingEntries() {
		List<GameRankingEntry> toSerialize = new ArrayList<>();
		List<String> serialiced = new RankingSerialiacer().serialize(toSerialize);
		
		assertEquals(0, serialiced.size());
	}
	@Test
	void VariedRankingEntries() {
		List<GameRankingEntry> toSerialize = new ArrayList<>();
		toSerialize.add(new GameRankingEntry("NotComonChar%^$#@!<>'a", LocalDateTime.of(2025, 5, 20, 15, 30, 0), GameLevel.FACIL, 1111111111, false));
		toSerialize.add(new GameRankingEntry("NotComonChar--++==", LocalDateTime.of(2020, 5, 20, 15, 30, 0), GameLevel.MEDIANO, 1111111111, true));
		toSerialize.add(new GameRankingEntry("VeryLong--------------------------------------------------------------------------------------------------------------",
				LocalDateTime.of(2024, 5, 20, 15, 30, 0), GameLevel.DIFICIL, 1111111111, false));
		List<String> serialized = new RankingSerialiacer().serialize(toSerialize);
		
		assertEquals(3, serialized.size());
		assertEquals("NotComonChar%^$#@!<>'a;20/05/2025;15:30:00;FACIL;LOOSE;1111111111 ", serialized.get(0));
		assertEquals("NotComonChar--++==;20/05/2020;15:30:00;MEDIANO;WIN;1111111111 ", serialized.get(1));
		assertEquals("VeryLong--------------------------------------------------------------------------------------------------------------;" +
				 "20/05/2024;15:30:00;DIFICIL;LOOSE;1111111111 ", serialized.get(2));
	}

}
