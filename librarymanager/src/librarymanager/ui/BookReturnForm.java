package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import librarymanager.controllers.Controller;
import space.fugit.jfx.FButton;
import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public class BookReturnForm implements Form{
	private FTextField memberid, isbn, copyNumber;
	private FButton searchbutton;
	public BookReturnForm() {
		this.memberid = new FTextField();
		this.isbn = new FTextField();
		this.copyNumber = new FTextField();
		this.searchbutton = new FButton("Search");
		searchbutton.setOnAction((event)->Controller.setFormData(this));
	}

	public FTextField[] getRequiredFields() {
		FTextField[] arr = { memberid, isbn, copyNumber };
		return arr; 
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(memberid.vLabel("Member ID"));
		p.getChildren().add(isbn.vLabel("ISBN"));
		p.getChildren().add(copyNumber.vLabel("Book Copy Number"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(searchbutton);
		p.getChildren().add(box);
		return p;
	}
	
	public String getMemberid() {
		return memberid.getText();
	}

	public String getIsbn() {
		return isbn.getText();
	}

	public String getCopyNumber() {
		return copyNumber.getText();
	}
}
