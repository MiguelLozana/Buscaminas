package uo.mp.minesweeper.gui.swing.dialogs;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import uo.mp.minesweeper.gui.swing.OnSwingThread;

@OnSwingThread
public class MessageDialogOnEDT {

	private Component frame;
	private String message;

	public MessageDialogOnEDT(Component window, String message) {
		this.frame = window;
		this.message = message;
	}

	public MessageDialogOnEDT(String message) {
		this(null, message);
	}

	public void invokeAndWait() {
		SwingUtilities.invokeLater( () -> 
				JOptionPane.showMessageDialog(frame, message)
			);
	}

}
