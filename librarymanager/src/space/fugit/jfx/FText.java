package space.fugit.jfx;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FText extends Text {
	public FText(String text) {
		super(text);
		this.setFont(new Font("Times New Roman", 20));
	}
	
	public FText() {
		super();
	}

}
