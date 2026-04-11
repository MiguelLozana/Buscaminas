package uo.mp.minesweeper.gui.swing.builders;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogBuilder implements ContainerBuilder {
	private String title = "";
	private int x = 100;
	private int y = 100;
	private int width = 460;
	private int height = 350;
	
	private int onCloseOperation = JDialog.DISPOSE_ON_CLOSE;
	private ContainerBuilder contentPaneBuilder;
	private boolean modal = true;

	public static DialogBuilder dialog() {
		return new DialogBuilder();
	}

	public DialogBuilder modal() {
		this.modal = true;
		return this;
	}

	public DialogBuilder modeless() {
		this.modal = false;
		return this;
	}

	public DialogBuilder title(String title) {
		this.title = title;
		return this;
	}

	public DialogBuilder position(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public DialogBuilder size(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public DialogBuilder exitOnClose() {
		this.onCloseOperation = JFrame.EXIT_ON_CLOSE;
		return this;
	}

	public DialogBuilder hideOnClose() {
		this.onCloseOperation = JFrame.HIDE_ON_CLOSE;
		return this;
	}

	public DialogBuilder doNothingOnClose() {
		this.onCloseOperation = JFrame.DO_NOTHING_ON_CLOSE;
		return this;
	}

	public DialogBuilder content(ContainerBuilder builder) {
		this.contentPaneBuilder = builder;
		return this;
	}

	@Override
	public JDialog build() {
		JDialog dlg = new JDialog();
		dlg.setModal( modal );
		dlg.setTitle( title );
		dlg.setDefaultCloseOperation( onCloseOperation );
		dlg.setBounds(x, y, width, height);
		dlg.setContentPane( contentPaneBuilder.build() );
		return dlg;
	}

}
