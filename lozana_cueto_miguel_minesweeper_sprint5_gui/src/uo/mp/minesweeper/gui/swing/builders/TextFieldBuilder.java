package uo.mp.minesweeper.gui.swing.builders;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextFieldBuilder implements ComponentBuilder {

	private String text = "";
	private String hint = "";
	private int aligmentType = SwingConstants.LEFT;
	private float fontSize = 15.0f;
	private InputVerifier inputVerifier = null;

	public static TextFieldBuilder textField() {
		return new TextFieldBuilder();
	}

	public TextFieldBuilder text(String text) {
		this.text = text;
		return this;
	}

	public TextFieldBuilder hint(String string) {
		this.hint = string;
		return this;
	}

	public TextFieldBuilder alignLeft() {
		aligmentType = SwingConstants.LEFT;
		return this;
	}

	public TextFieldBuilder alignRight() {
		aligmentType = SwingConstants.RIGHT;
		return this;
	}

	public TextFieldBuilder alignCenter() {
		aligmentType = SwingConstants.CENTER;
		return this;
	}

	public TextFieldBuilder fontSize(float size) {
		fontSize = size;
		return this;
	}

	public TextFieldBuilder notEmpty() {
		this.inputVerifier = new NotEmptyVerifier();
		return this;
	}

	@Override
	public JTextField build() {
		JTextField tf = new JTextField();
		tf.setText( text );
		tf.setToolTipText( hint );
		tf.setInputVerifier( inputVerifier );
		tf.setHorizontalAlignment( aligmentType );
		tf.setFont( tf.getFont().deriveFont( fontSize ) );
		return tf;
	}

	private static class NotEmptyVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			return tf.getText().trim().isEmpty() == false;
		}
		
	}
}
