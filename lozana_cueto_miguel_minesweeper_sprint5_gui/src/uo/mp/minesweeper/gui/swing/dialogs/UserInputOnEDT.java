package uo.mp.minesweeper.gui.swing.dialogs;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import uo.mp.minesweeper.gui.swing.OnSwingThread;

@OnSwingThread
public class UserInputOnEDT {

	private String title;
	private String message;
	private String res;

	public UserInputOnEDT(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public String invokeLater() {
		try {
			SwingUtilities.invokeAndWait( () -> {
				res = (String) JOptionPane.showInputDialog(null, message, title,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, // possible icon
					        null, 
					        "type file name"
					   );
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException("Concurrency issues on Swing EDT");
		}
		return res;
	}

}
