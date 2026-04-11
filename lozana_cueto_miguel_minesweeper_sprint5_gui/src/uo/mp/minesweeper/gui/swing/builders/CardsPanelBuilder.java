package uo.mp.minesweeper.gui.swing.builders;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CardsPanelBuilder implements ContainerBuilder, ComponentBuilder {
	private List<ComponentBuilder> builders = new LinkedList<>();
	private List<Component> components = new LinkedList<>();

	public static CardsPanelBuilder cardsPanel() {
		return new CardsPanelBuilder();
	}

	public CardsPanelBuilder add(ComponentBuilder builder) {
		builders.add( builder );
		return this;
	}
	
	public CardsPanelBuilder atNorth(Component component) {
		components.add( component );
		return this;
	}
	
	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new CardLayout(0, 0));

		int index = 0;
		for (Component component: components) {
			panel.add( component, String.valueOf( index++ ) );
		}

		for (ComponentBuilder builder: builders ) {
			panel.add( builder.build(), String.valueOf( index++ ) );
		}

		return panel;
	}

}
