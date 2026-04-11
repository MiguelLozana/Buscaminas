package uo.mp.minesweeper.gui.swing.builders;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ComboBoxBuilder implements ComponentBuilder {
	
	private List<String> items = new ArrayList<>();
	private int defaultOption = 0;

	public static ComboBoxBuilder combo() {
		return new ComboBoxBuilder();
	}

	public ComboBoxBuilder options(String... items) {
		for (String item: items) {
			this.items.add( item );
		}
		return this;
	}

	public ComboBoxBuilder defaultOption(int i) {
		this.defaultOption = i;
		return this;
	}

	@Override
	public JComboBox<String> build() {
		JComboBox<String> tf = new JComboBox<>();
		for (String item : items) {
			tf.addItem(item);
		}
		tf.setSelectedIndex( defaultOption );
		return tf;
	}

}
