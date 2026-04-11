package uo.mp.minesweeper.gui.swing.builders;

import javax.swing.JFrame;

public class FrameBuilder implements ContainerBuilder {
	private String title = "";
	private int x = 100;
	private int y = 100;
	private int width = 460;
	private int height = 350;
	
	private int onCloseOperation = JFrame.DISPOSE_ON_CLOSE;
	private ContainerBuilder contentPaneBuilder;

	public static FrameBuilder frame() {
		return new FrameBuilder();
	}

	public FrameBuilder title(String title) {
		this.title = title;
		return this;
	}

	public FrameBuilder position(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public FrameBuilder size(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public FrameBuilder exitOnClose() {
		this.onCloseOperation = JFrame.EXIT_ON_CLOSE;
		return this;
	}

	public FrameBuilder hideOnClose() {
		this.onCloseOperation = JFrame.HIDE_ON_CLOSE;
		return this;
	}

	public FrameBuilder doNothingOnClose() {
		this.onCloseOperation = JFrame.DO_NOTHING_ON_CLOSE;
		return this;
	}

	public FrameBuilder content(ContainerBuilder builder) {
		this.contentPaneBuilder = builder;
		return this;
	}

	@Override
	public JFrame build() {
		JFrame frame = new JFrame();
		frame.setTitle( title );
		frame.setDefaultCloseOperation( onCloseOperation );
		frame.setBounds(x, y, width, height);
		frame.setContentPane( contentPaneBuilder.build() );
		return frame;
	}

}
