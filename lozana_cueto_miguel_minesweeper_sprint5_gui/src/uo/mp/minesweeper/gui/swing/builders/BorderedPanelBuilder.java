package uo.mp.minesweeper.gui.swing.builders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BorderedPanelBuilder implements ContainerBuilder, ComponentBuilder {
	private Map<String, ComponentBuilder> builders = new HashMap<>();
	private Map<String, Component> components = new HashMap<>();

	public static BorderedPanelBuilder borderedPanel() {
		return new BorderedPanelBuilder();
	}

	public BorderedPanelBuilder atNorth(ComponentBuilder builder) {
		builders.put(BorderLayout.NORTH, builder);
		return this;
	}
	
	public BorderedPanelBuilder atCenter(ComponentBuilder builder) {
		builders.put(BorderLayout.CENTER, builder);
		return this;
	}
	
	public BorderedPanelBuilder atSouth(ComponentBuilder builder) {
		builders.put(BorderLayout.SOUTH, builder);
		return this;
	}

	public BorderedPanelBuilder atEast(ComponentBuilder builder) {
		builders.put(BorderLayout.EAST, builder);
		return this;
	}

	public BorderedPanelBuilder atWest(ComponentBuilder builder) {
		builders.put(BorderLayout.WEST, builder);
		return this;
	}

	public BorderedPanelBuilder atNorth(Component component) {
		components.put(BorderLayout.NORTH, component);
		return this;
	}
	
	public BorderedPanelBuilder atCenter(Component component) {
		components.put(BorderLayout.CENTER, component);
		return this;
	}
	
	public BorderedPanelBuilder atSouth(Component component) {
		components.put(BorderLayout.SOUTH, component);
		return this;
	}

	public BorderedPanelBuilder atEast(Component component) {
		components.put(BorderLayout.EAST, component);
		return this;
	}

	public BorderedPanelBuilder atWest(Component component) {
		components.put(BorderLayout.WEST, component);
		return this;
	}

	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));

		for (String orientation: components.keySet()) {
			Component component = components.get(orientation);
			panel.add( component, orientation );
		}

		for (String orientation: builders.keySet()) {
			ComponentBuilder builder = builders.get(orientation);
			panel.add( builder.build(), orientation );
		}

		return panel;
	}

}
