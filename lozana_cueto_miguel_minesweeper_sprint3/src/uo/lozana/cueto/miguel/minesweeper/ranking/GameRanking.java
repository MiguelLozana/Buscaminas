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
	 * CONSTRUCTOR
	 * ======================
	 */
	
	
	/*========================
	 * 
	 * LIST METHODS
	 * 
	 * ======================
	 */
	public void append(GameRankingEntry gameRankingEntry) {
		int index = ranking.indexOf(gameRankingEntry);
		if (index != -1) {
			ranking.remove(index);
		}
		ranking.add(gameRankingEntry);
	}
	
	
	
	public List<GameRankingEntry> getAllEntries(){
		return new ArrayList<>(ranking);
	}

	public List<GameRankingEntry> getEntriesForUsername(String userName){
		ArgumentChecks.isNotBlank(userName);
		List<GameRankingEntry> userRanking = new ArrayList<GameRankingEntry>();
		for(GameRankingEntry entry : ranking) {
			if (entry.getUserName().equals(userName)) {
				userRanking.add(entry);
			}
		}
		return userRanking;
	}
	
	
	
	/*========================
	 * 
	 * OUTPUT - IMPUT
	 * 
	 * ======================
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
