package uo.lozana.cueto.miguel.minesweeper.ranking;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import uo.lozana.cueto.miguel.minesweeper.session.GameException;
import uo.mp.minesweeper.util.log.SimpleLogger;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.file.FileUtil;

public class GameRanking {
	private List<GameRankingEntry> ranking;
	private String rankigFileName;
	private SimpleLogger logger;

	
	/*========================
	 * CONSTRUCTOR
	 * ======================
	 */
	/** 
	 * Instances the GameRanking the filename
	 */
	public GameRanking(String rankigFileName) {
		ArgumentChecks.isNotBlank(rankigFileName);
		ranking = new ArrayList<GameRankingEntry>();
		
		this.rankigFileName = rankigFileName;
		importRankingObjects(rankigFileName);
		
	}
	
	private void importRankingObjects(String rankigFileName2) {
		try {
			this.ranking = (List<GameRankingEntry>) new FileUtil().readObjects(rankigFileName);
		}catch (Exception e){
			if (logger != null) logger.log(e);
			this.ranking = new ArrayList<GameRankingEntry>();
			saveActualEntrys();
		}
		
	}
	/*========================
	 * GETTERS
	 * ======================
	 */
	public String getRankingFilename() {
		return this.rankigFileName;
	}
	
	public SimpleLogger getLogger() {
		return this.logger;
	}
	
	
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.isNotNull(logger);
		this.logger = logger;
	}
	/*========================
	 * 
	 * LIST METHODS
	 * 
	 * ======================
	 */
	/**
	 * Appends the gameRankingEntry given at the end of the list and writes it in the file,
	 *  if it is already existing, is previously removed
	 * @param gameRankingEntry to append
	 */
	public void append(GameRankingEntry gameRankingEntry) {
		ArgumentChecks.isNotNull(gameRankingEntry);
		if (gameRankingEntry.hasWon()) {
			int index = ranking.indexOf(gameRankingEntry);
			if (index != -1) {
				ranking.remove(index);
			}
			ranking.add(gameRankingEntry);
			saveActualEntrys();
		}
	}
	
	
	/**
	 * Gets a sorted list of the ranking. 
	 * More difficult, with less duration ancient GameRanklingEntries goes first
	 * @return sorted list
	 */
	public List<GameRankingEntry> getAllEntries(){
		
		return sortList();
	}
	
	/**
	 * Gets a sorted list of the ranking o the user. 
	 * More difficult, with less duration ancient GameRanklingEntries goes first
	 * @return sorted user list
	 */
	public List<GameRankingEntry> getEntriesForUsername(String userName){
		ArgumentChecks.isNotBlank(userName);
		
		List<GameRankingEntry> sorted = sortList();
		List<GameRankingEntry> userRanking = new ArrayList<GameRankingEntry>();
		
		for(GameRankingEntry entry : sorted) {
			if (entry.getUserName().equals(userName)) {
				userRanking.add(entry);
			}
		}
		return userRanking;
	}
	//Saves the object GameRankingEntry in the file
	private void saveActualEntrys() {
		new FileUtil().writeObject(rankigFileName, ranking);
		
	}
	
	/*
	 * Creates a sorted list  
	 * More difficult, with less duration ancient GameRanklingEntries goes first
	 */
	private List<GameRankingEntry> sortList() {
		
		List<GameRankingEntry> copy = new ArrayList<>(this.ranking);
		Collections.sort(copy,new GameRankingEntryComparator());
		return new ArrayList<GameRankingEntry>(copy);	
	}
		
	
	/*========================
	 * 
	 * OUTPUT - IMPUT
	 * 
	 * ======================
	 */
	/**
	 * Saves the ranking in a file
	 */
	public void exportRanking(String exportFile){
		ArgumentChecks.isNotBlank(exportFile);
		List<GameRankingEntry> sorted = sortList();
		List<String> serializedRanking = new RankingSerialiacer().serialize(sorted);
		new FileUtil().WriteLines(exportFile, serializedRanking, false);
		
	}
	/**
	 * Loads the ranking form a file, given its name as parameter
	 * @param importFile 
	 * @throws FileNotFoundException 
	 * @throws GameException 
	 */
	public void importRanking(String importFile) throws GameException{
		ArgumentChecks.isNotBlank(importFile);
		 try {
			List<String> lines = new FileUtil().readLines(importFile);
			this.ranking = new RankingParser(logger).parse(lines);
		 } catch (FileNotFoundException e) {
			if (logger != null) logger.log(e);
			throw new GameException("ERROR :File Not Found");
		 }
		 saveActualEntrys();
	}
	
}
