package uo.lozana.cueto.miguel.minesweeper.ranking;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

public class GameRanking {
	
	private List<GameRankingEntry> ranking;
	private String file;
	private RankingParser parser;
	
	/*========================
	 * CONSTRUCTOR
	 * ======================
	 */
	/** 
	 * Instances the GameRanking the filename
	 */
	public GameRanking(String fileName) {
		ranking = new ArrayList<GameRankingEntry>();
		this.file = fileName;
	}
	/*========================
	 * GETTERS
	 * ======================
	 */
	public String getFilename() {
		return this.file;
	}
	
	
	
	/*========================
	 * 
	 * LIST METHODS
	 * 
	 * ======================
	 */
	/**
	 * Appends the gameRankingEntry given at the end of the list,
	 *  if it is already existing, is previously removed
	 * @param gameRankingEntry to append
	 */
	public void append(GameRankingEntry gameRankingEntry) {
		int index = ranking.indexOf(gameRankingEntry);
		if (index != -1) {
			ranking.remove(index);
		}
		ranking.add(gameRankingEntry);
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
		List<GameRankingEntry> sorted = sortList();
		ArgumentChecks.isNotBlank(userName);
		List<GameRankingEntry> userRanking = new ArrayList<GameRankingEntry>();
		for(GameRankingEntry entry : sorted) {
			if (entry.getUserName().equals(userName)) {
				userRanking.add(entry);
			}
		}
		return userRanking;
	}
	
	/*
	 * Creates a sorted list  
	 * More difficult, with less duration ancient GameRanklingEntries goes first
	 */
	private List<GameRankingEntry> sortList() {
		List<GameRankingEntry> sorted = new ArrayList<>();
	    GameRankingEntryComparator comparator = new GameRankingEntryComparator();
	    
	    for (GameRankingEntry toSort : ranking) {
	        boolean insert = false;
	        
	        
	        for (int i = 0; i < sorted.size(); i++) {
	            
	            if (comparator.compare(toSort, sorted.get(i)) < 0) {
	                sorted.add(i, toSort); 
	                insert = true;
	                break; 
	            }
	        }
	        
	        if (!insert) {
	            sorted.add(toSort);
	        }
	    }
	   	return sorted;
	}
		
	
	/*========================
	 * 
	 * OUTPUT - IMPUT
	 * 
	 * ======================
	 */
	/**
	 * Saves the ranking in a file, given its name as parameter
	 * @param filename where save the ranking
	 */
	public void exportRanking(String filename){
		RankingSerialiacer serialicer = new RankingSerialiacer();
		List<String> serializedRanking = serialicer.serialize(ranking);
		
		try { 
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			try {
				for (String line : serializedRanking) {
					out.write(line);
					out.newLine();
				}
			} finally {
				out.close();
			} 
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	/**
	 * Loads the ranking form a file, given its name as parameter
	 * @param filename where save the ranking
	 */
	public void importRanking(){
		List<String> lines = new ArrayList<>();
	    BufferedReader reader = null;
	
		try {
			try {
				reader = new BufferedReader(new FileReader(file));
		        String line;
		        while ((line = reader.readLine()) != null) {
		            lines.add(line);   
		        }
			}catch(FileNotFoundException e){
				this.ranking = new ArrayList<>();
			}finally {
				if (reader != null) { 
	                reader.close();
	            }
			}
		
		}catch(IOException e){
			throw new RuntimeException("Error reading the file", e);
		}
		this.ranking =parser.parse(lines);
	}




	}
