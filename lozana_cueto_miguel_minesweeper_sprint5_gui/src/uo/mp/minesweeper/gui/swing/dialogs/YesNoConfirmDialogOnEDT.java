package uo.mp.minesweeper.gui.swing.dialogs;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import uo.mp.minesweeper.gui.swing.OnSwingThread;

/**
 * Shows an confirm JDialog on the Swing EDT by using invokeAndWait()
 * @author alb
 */
@OnSwingThread
public class YesNoConfirmDialogOnEDT {

	private String message;
	private String title;
	private int res;

	public YesNoConfirmDialogOnEDT(String title, String message) {
		this.message = message;
		this.title = title;
	}

	public int invokeAndWait() {
		try {
			SwingUtilities.invokeAndWait( () -> {
				res = JOptionPane.showConfirmDialog(null, 
						message, title, 
						JOptionPane.YES_NO_OPTION);
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException("Concurrency issues on Swing EDT");
		}
		return res;
	}

}
