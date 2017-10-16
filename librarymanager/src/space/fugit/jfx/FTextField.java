package space.fugit.jfx;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class FTextField extends TextField {
	private boolean required = false;
	private Text message;
	//default behavior the field is not required
	public FTextField() {
		
		super();
		this.setPrefColumnCount(20);
		this.setPadding(new Insets(2));
		this.setMaxHeight(Double.MAX_VALUE);
		this.required  = false;
		this.message = new Text();
		this.message.setStyle("-fx-font-size: 14px; -fx-fill: #cc0000;");
	}
	
	//required field, you can optionally set if the field is required
	public FTextField(boolean required) {
		this();
		this.required = required;
	}
	
	public void setTooltip(String tip) {
		final Tooltip tooltip = new Tooltip();
		tooltip.setText(tip);
		this.setTooltip(tooltip);
	}
	
	/**
	 * Checks if a given field is required or not
	 * @return true if required, false if not
	 */
	public boolean isRequired() {
		return required;
	}
	
	public void setMessage(String errorMessage) {
		this.message.setText(errorMessage);
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
		vbox.getChildren().addAll(label, this, message);
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
