package space.fugit.jfx;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class FPasswordField extends PasswordField {
	public FPasswordField() {
		super();
		this.setPrefColumnCount(20);
		this.setPadding(new Insets(2));
		this.setMaxHeight(Double.MAX_VALUE);
	}
	
	/**
	 * This methods creates a label with given text  
	 * 
	 * @param labelText the text for the label
	 * @return VBox. the label and field bound together vertically
	 */
	public VBox vLabel(String labelText) {
		VBox vbox = new VBox();
		Label label = new Label(labelText);
		vbox.getChildren().addAll(label, this);
		return vbox;
	}
	
	/**
	 * This methods creates a label with given text  
	 * 
	 * @param labelText the text for the label
	 * @return VBox. the label and field bound together vertically
	 */
	public HBox hLabel(String labelText) {
		HBox hbox = new HBox();
		Label label = new Label(labelText);
		hbox.getChildren().addAll(label, this);
		return hbox;
	}
	
	/**
	 * checks if the field is empty
	 * you can call this on a field without calling getText first
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		if( this.getText().length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
