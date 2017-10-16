package librarymanager.ui;

import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public interface Form {
	public FVBox getForm();
	public FTextField[] getRequiredFields();
	
	public static boolean isValid(Form form) {
		boolean isvalid = true;
		for( FTextField f: form.getRequiredFields()) {
			f.setMessage(""); //clear if there was any message
			if( f.isEmpty()) {
				f.setMessage("This field can't be left empty!");
				isvalid = false;
			}
		}
		return isvalid;
	}
}
