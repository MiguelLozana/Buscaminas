package uo.mp.minesweeper.gui.swing.builders;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableBuilder implements ComponentBuilder {
	
	private String[] columns;

	public static TableBuilder table() {
		return new TableBuilder();
	}

	public TableBuilder columns(String... columns) {
		this.columns = columns;
		return this;
	}

	@Override
	public JScrollPane build() {
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);
		return new JScrollPane(table);
	}

}
