package uo.mp.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;


import uo.mp.util.check.ArgumentChecks;

/**
 * A utility class to read/write text lines from/to a text file
 */public class FileUtil {

	    /**
	     * Fake method to read lines from a file
	     * 
	     * Read all lines from a file. A line is considered to be terminated by a
	     * line feed ('\n').
	     * Each item in the list (line) will contain the contents of a line, not
	     * including any line-termination characters.
	     * 
	     * @param  pathToTheFile path to a plain text file
	     * @return               the lines from the file as a List
	     * @throws FileNotFoundException 
	     * @throws IOException
	     *                       TODO
	     */
	    public List<String> readLines(String pathToTheFile) throws FileNotFoundException  {
	    	
	    	ArgumentChecks.isNotBlank(pathToTheFile);
	        List<String> lines = new LinkedList<>();
	        
	        BufferedReader in = new BufferedReader(new FileReader(pathToTheFile));
	        
	        String line;
	        try {
				try {
					while ((line = in.readLine()) != null) {
						lines.add(line);
					} 
				} finally {
					in.close();
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}
			return lines;
	    }
	    /**
	     * 
	     * 
	     * Write to a plain text file all strings in the list. Lines will be
	     * separated by a line feed ('\n').
	     * Each item in the list (line) contains the contents for one line, not
	     * including any
	     * line-termination characters.
	     * 
	     * @param  pathToTheFile path to a plain text file
	     * @param  lines         the List of Strings to be writen to the file
	     * @param append         if you want to append and do not lose previous data
	     * @throws IOException
	     *                       TODO
	     */
	    
	    
	    public void WriteLines(String outFileName, List<String> lines, boolean append ) {
	    	
	    	ArgumentChecks.isNotBlank(outFileName);
	    	ArgumentChecks.isNotNull(lines);
	    	ArgumentChecks.isNotNull(append);
	    	
	    	 try {
					BufferedWriter out = new BufferedWriter(new FileWriter(outFileName,append));
					
					try {
						for (String line : lines) {
							out.write(line);
							out.newLine();
						} 
					} finally {
						out.close();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		    }
	    	
	    
	    
	    /**
	     * Writes the object in the file whose name was provided as parameter
	     * @param filename where save the object
	     * @param obj obect to save
	     */
	    public void writeObject(String filename, Object obj) {
	    	try {
				ObjectOutputStream out = new ObjectOutputStream(
											new BufferedOutputStream(
											new FileOutputStream(filename)));
				try {
					out.writeObject(obj);
				}finally {
					out.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	    	
	    }
		public Object readObjects(String fileName) {
			try {
				
				ObjectInputStream in = new ObjectInputStream(
										new BufferedInputStream(
										new FileInputStream(fileName)));
				try {
					return in.readObject();
				}finally {
					in.close();
				}
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

	}
