package uo.lozana.cueto.miguel.minesweeper.ranking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.lozana.cueto.miguel.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.log.BasicSimpleLogger;
import uo.mp.minesweeper.util.log.SimpleLogger;

public class RankingParserParserTest {
	/*
	 * Scenarios:
	 * 1 - Null list of lines provided
	 * 2 - Empty list of lines privided
	 * 3 - Null line
	 * 4 - Blank lines provided
	 * 5 - Incorrect line format (incorrect number of fields) 
	 * 6 - Unable to get username
	 * 7 - unable to get date
	 * 8 - Unable to get level
	 * 9 - Unable to get boolean
	 * 10 - Unable to get duration
	 * 11- Able to parse correctly the object
	 * 12 - Mixed correct and incorrect lines
	 */
	private SimpleLogger logger  = new BasicSimpleLogger();
	/**
	 * 1
	 * GIVEN: null list of lines
	 * WHEN: parse null
	 * THEN: IAE 
	 */
	
	@Test
	void NullListLines() {
		assertThrows(IllegalArgumentException.class, ()-> new RankingParser(logger).parse(null));
	}
	/**
	 * 2
	 * GIVEN: empty list of lines
	 * WHEN: parse empty list
	 * THEN: no object created
	 */
	@Test
	void EmptyLines() {
		List<String> lines = new ArrayList<String>();
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 3
	 * GIVEN: list with null line
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void NullSingleLine() {
		List<String> lines = new ArrayList<String>();
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 4
	 * GIVEN: list with blank line
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void BlankSingleLine() {
		List<String> lines = new ArrayList<String>();
		lines.add("");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 5
	 * GIVEN: list with invalid line
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidSingleLine() {
		List<String> lines = new ArrayList<String>();
		lines.add("THERE IS NO: ENOUGH FILELDS");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 6
	 * GIVEN: list with invalid line
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidName() {
		List<String> lines = new ArrayList<String>();
		lines.add(";08/02/2020;21:02:34;FACIL;lost;4");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 7
	 * GIVEN: list with invalid date
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidDate() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;080220;21:02:34;FACIL;lost;4");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 8
	 * GIVEN: list with invalid level
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidLevel() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;08/02/2020;21:02:34;INVALIDO;lost;4");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 9
	 * GIVEN: list with invalid result
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidResult() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;INVALIDO;4");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 10
	 * GIVEN: list with invalid result
	 * WHEN: parse list
	 * THEN: no object created
	 */
	@Test
	void InvalidDuration() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;lost;-4");
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(0, parsedList.size());
	}
	/**
	 * 11
	 * GIVEN: list with valid fields
	 * WHEN: parse list
	 * THEN: object created
	 */
	@Test
	void ValidFields() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;loose;4");
		
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		
		GameRankingEntry parsedEntry = parsedList.getFirst();
		
		
		assertEquals(1, parsedList.size());
		
		assertEquals("Pepe",parsedEntry.getUserName());
		assertEquals(LocalDateTime.of(2020, 2, 8, 21, 2, 34),parsedEntry.getDate());
		assertEquals(GameLevel.FACIL,parsedEntry.getLevel());
		assertEquals(false, parsedEntry.hasWon());
		assertEquals(4,parsedEntry.getDuration());
		
	}
	/**
	 * 12
	 * GIVEN: list with valid fields
	 * WHEN: parse list
	 * THEN: object created
	 */
	@Test
	void MixedInvalidValid() {
		List<String> lines = new ArrayList<String>();
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;lost;-4");
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;lost;-4");
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;lost;-4"); // 3 invalid
		
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;loose;4");
		lines.add("Pepe;08/02/2020;21:02:34;FACIL;loose;4");// 2 valid
		
		List<GameRankingEntry> parsedList = new RankingParser(logger).parse(lines);
		assertEquals(2, parsedList.size());
	}
}
