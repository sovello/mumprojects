package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import space.fugit.jfx.FButton;
import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public class BookStatusForm implements Form{
	private FTextField isbn;
	private FButton searchbutton;
	
	public BookStatusForm() {
		this.isbn = new FTextField();
		this.searchbutton = new FButton("Search");
	}
	
	public FTextField[] getRequiredFields() {
		FTextField[] arr = { isbn };
		return arr; 
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(isbn.vLabel("ISBN"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(searchbutton);
		p.getChildren().add(box);
		return p;
	}
}
