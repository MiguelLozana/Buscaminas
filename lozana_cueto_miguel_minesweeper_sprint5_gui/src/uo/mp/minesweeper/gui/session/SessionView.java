package uo.mp.minesweeper.gui.session;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.gui.swing.OnSwingThread;
import uo.mp.minesweeper.gui.swing.dialogs.ErrorMessageOnEDT;
import uo.mp.minesweeper.gui.swing.dialogs.OptionsDialogOnEDT;
import uo.mp.minesweeper.gui.swing.dialogs.UserInputOnEDT;
import uo.mp.minesweeper.gui.swing.dialogs.YesNoConfirmDialogOnEDT;
import uo.mp.util.check.ArgumentChecks;

/**
 * The SessionView is the main screen of the application.
 * Adds the logic to the SessionScreen, it implements the SessionPresenter.View interface, so
 * the SessionPresenter can interact with it without knowing the implementation details of the view
 * 
 */
/*package*/ class SessionView 
		extends SessionScreen 
		implements SessionPresenter.View {
	
	private SessionPresenter presenter;

	public void setPresenter(SessionPresenter presenter) {
		ArgumentChecks.isTrue( presenter != null );
		this.presenter = presenter;
	}

	@Override
	@OnSwingThread
	public void open() {
		onSwingThread( () -> window.setVisible(true) );
	}

	@Override
	@OnSwingThread
	public void close() {
		onSwingThread( () -> {
			window.setVisible( false );
			window.dispose();
		});
	}

	@Override
	@OnSwingThread
	public boolean askToStoreScore() {
		int option = new YesNoConfirmDialogOnEDT(
							"Congratulations! You win",
							"Do you want to store your score?"
						)
						.invokeAndWait();
		
		return option == JOptionPane.YES_OPTION;
	}

	@Override
	@OnSwingThread
	public int askGameLevel() {
		Object[] options = { "Easy", "Medium", "Hard" };
		
		Object selected = new OptionsDialogOnEDT(
					"Level", "Choose one game level", options
				)
				.invokeAndWait();

		for (int i = 0; i < options.length; i++) {
			if ( options[i].equals( selected) ) {
				return i;
			}
		}
		return 0;
	}

	@Override
	@OnSwingThread
	public String askFileName() {
		return new UserInputOnEDT("File name", "Type the name of the file")
				.invokeLater();
	}

	@Override
	@OnSwingThread
	public void showMessage(String message) {
		new ErrorMessageOnEDT(message).invokeAndWait();
	}

	@Override
	@OnSwingThread
	public void showFatalMessage(String message) {
		new ErrorMessageOnEDT(message).invokeAndWait();
	}
	
	@Override
	protected Action createUserNameReadyListener() {
		return new ReadyButtonActionListener();
	}

	@Override
	protected Action createOptionButtonActionListener() {
		return new OptionButtonActionListener();
	}

	@OnSwingThread
	@SuppressWarnings("serial") 
	class OptionButtonActionListener extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			String actionCommand = bt.getActionCommand();
			int opt = Integer.parseInt( actionCommand );
			
			presenter.optionSelected( opt );
		}

	}

	@Override
	public void clearUserName() {
		tfUserName.setText( "" );
		tfUserName.setEnabled( true );
		showPanel( 0 );
	}

	@Override
	public void showRanking(String type, List<GameRankingEntry> ranking) {
		JPanel p = (JPanel) cardsPanel.getComponent( 2 );
		JScrollPane scrollPane = (JScrollPane) p.getComponent( 0 );
		JTable table = (JTable) scrollPane.getViewport().getView();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.setRowCount( 0 ); // Clear table
		for (GameRankingEntry e : ranking) {
			model.addRow( new Object[] {
					e.getUserName(),
					e.getLevel(),
					e.getDate(),
					e.getTime()
			});
		}
		
		table.setVisible( true );
		showPanel( 2 );
	}

	@Override
	protected Action createBackToOptionsPanelListener() {
		return new BackToOptionsPanelActionListener();
	}

	@OnSwingThread
	@SuppressWarnings("serial")
	class BackToOptionsPanelActionListener extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			showPanel( 1 );
		}
	}
	
	@OnSwingThread
	@SuppressWarnings("serial") 
	class ReadyButtonActionListener extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( tfUserName.getText().trim().isEmpty() ) {
				tfUserName.grabFocus();
				return;
			}
			
			tfUserName.setEnabled( false );
			showPanel( 1 );
			
			presenter.setUserName( tfUserName.getText() );
		}
		
	}

	private void onSwingThread(Runnable task) {
		SwingUtilities.invokeLater( task );
	}

}
