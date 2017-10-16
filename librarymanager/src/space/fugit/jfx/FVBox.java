package space.fugit.jfx;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class FVBox extends VBox {
	public FVBox() {
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(20);
	}
}
