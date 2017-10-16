package space.fugit.jfx;

public class FEventHandler {
	
	//a required field can't be left empty
	public static void setRequired(FTextField field) {
		if( field.isEmpty()) {
			field.setText("This field can not be left empty!");
		}
	}
}
