package uo.mp.minesweeper.gui.session;

import static javax.swing.SwingUtilities.invokeLater;
import static uo.mp.minesweeper.gui.swing.builders.BorderedPanelBuilder.borderedPanel;
import static uo.mp.minesweeper.gui.swing.builders.ButtonBuilder.button;
import static uo.mp.minesweeper.gui.swing.builders.CardsPanelBuilder.cardsPanel;
import static uo.mp.minesweeper.gui.swing.builders.FrameBuilder.frame;
import static uo.mp.minesweeper.gui.swing.builders.GridPanelBuilder.gridPanel;
import static uo.mp.minesweeper.gui.swing.builders.LabelBuilder.label;
import static uo.mp.minesweeper.gui.swing.builders.TableBuilder.table;
import static uo.mp.minesweeper.gui.swing.builders.TextFieldBuilder.textField;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uo.mp.minesweeper.gui.swing.OnSwingThread;
import uo.mp.minesweeper.gui.swing.builders.ButtonBuilder;

/**
 * Provides the common GUI components for the SessionView, this class is not
 * intended to be used directly, but to be extended by the SessionView
 * 
 * The SessionView is the main screen of the application, and provides the 
 * event handling. This class only provides the layout of the screen. 
 */
/*package*/ abstract class SessionScreen {

	protected JFrame window;
	protected JPanel cardsPanel;
	protected JTextField tfUserName;

	public SessionScreen() {
		this.tfUserName = createUserNameTextField();
		this.cardsPanel = createCardsPanel();
		this.window = createWindow();
	}
	
	private JTextField createUserNameTextField() {
		return textField()
				.hint("Type your user name")
				.alignLeft()
				.notEmpty()
				.build();
	}

	private JFrame createWindow() {
		return frame()
				.title("Minesweeper session")
				.position(100, 100)
				.size( 250, 300 )
				.exitOnClose()
				.content(
					borderedPanel()
						.atNorth( borderedPanel()
							.atWest( label().text("User: ").alignRight() )
							.atCenter( tfUserName )
						)
						.atCenter( cardsPanel )
				)
				.build();
	}

	private JPanel createCardsPanel() {
		Action userNameReadyAction = createUserNameReadyListener();
		Action backToOptionsPanelAction = createBackToOptionsPanelListener();
		return cardsPanel()
				.add( 
					borderedPanel()
						.atNorth( 
							button().text("Ready").onAction( userNameReadyAction )
						) 
				)
				.add( 
					gridPanel(5, 1)
						.add( createButtons() ) 
				)
				.add(
					borderedPanel()
						.atNorth( label().text("My scores").alignLeft() )
						.atCenter(
							table().columns("Username", "Level", "Date", "Time")
						)
						.atSouth( button().text("Back").onAction( backToOptionsPanelAction ) )
				)
				.build();
	}

	protected abstract Action createBackToOptionsPanelListener();
	protected abstract Action createUserNameReadyListener();

	private List<ButtonBuilder> createButtons() {
		Action reaction = createOptionButtonActionListener();
		return List.of(
				button().text("Play").command("1").onAction( reaction ),
				button().text("Show my scores").command("2").onAction( reaction ),
				button().text("Show ranking" ).command("3").onAction( reaction ),
				button().text("Export" ).command("4").onAction( reaction ),
				button().text("Import" ).command("5").onAction( reaction )
		);
	}

	protected abstract Action createOptionButtonActionListener();

	@OnSwingThread
	protected void showPanel(int index) {
		invokeLater( () -> {
			CardLayout layout = (CardLayout) cardsPanel.getLayout();
			layout.show(cardsPanel, String.valueOf( index ));
		});
	}

}
