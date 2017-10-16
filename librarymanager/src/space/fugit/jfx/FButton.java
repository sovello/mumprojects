package space.fugit.jfx;

import javafx.scene.control.Button;

public class FButton extends Button {
	public FButton(String text) {
		super(text);
		this.setMaxWidth(Double.MAX_VALUE);
	}
}
