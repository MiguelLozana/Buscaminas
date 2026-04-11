package uo.mp.minesweeper.gui.swing.dialogs;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import uo.mp.minesweeper.gui.swing.OnSwingThread;

/**
 * Shows an input JDialog on the Swing EDT by using invokeAndWait()
 * @author alb
 */
@OnSwingThread
public class OptionsDialogOnEDT {

	private String message;
	private String title;
	private Object[] options;
	private Object res;

	public OptionsDialogOnEDT(String title, String message,
			Object[] options) {
		this.message = message;
		this.title = title;
		this.options = options;
	}

	public Object invokeAndWait() {
		try {
			SwingUtilities.invokeAndWait( () -> {
				res = JOptionPane.showInputDialog(null, message, title,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, // possible icon
					        options, options[0]
					   );
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException("Concurrency issues on Swing EDT");
		}
		return res;
	}

}
