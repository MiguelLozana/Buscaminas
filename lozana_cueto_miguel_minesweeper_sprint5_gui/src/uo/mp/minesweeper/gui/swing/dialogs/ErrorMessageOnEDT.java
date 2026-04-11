package uo.mp.minesweeper.gui.swing.dialogs;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ErrorMessageOnEDT {

	private JFrame frame;
	private String title;
	private String message;

	public ErrorMessageOnEDT(JFrame window, String title, String message) {
		this.frame = window;
		this.title = title;
		this.message = message;
	}

	public ErrorMessageOnEDT(JFrame window,  String message) {
		this(window, "Error message", message);
	}

	public ErrorMessageOnEDT(String message) {
		this(null, message);
	}

	public void invokeAndWait() {
		try {
			SwingUtilities.invokeAndWait( () -> 
					JOptionPane.showMessageDialog(
								frame, message, title,
							    JOptionPane.ERROR_MESSAGE
						 )
			);
		} catch (InterruptedException | InvocationTargetException e) {
			Thread.currentThread().interrupt();
		}
	}

}
