package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import librarymanager.controllers.Controller;
import librarymanager.models.BookCopy;
import librarymanager.models.CheckoutRecord;
import librarymanager.models.LibraryMember;
import space.fugit.jfx.FButton;
import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public class CheckOutForm implements Form{
	private FTextField memberid, isbn;
	private FButton searchbutton;
	public CheckOutForm() {
		this.memberid = new FTextField();
		this.isbn = new FTextField();
		this.searchbutton = new FButton("Search");
		searchbutton.setOnAction((event)->Controller.setFormData(this));
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(memberid.vLabel("Member ID"));
		p.getChildren().add(isbn.vLabel("ISBN"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(searchbutton);
		p.getChildren().add(box);
		return p;
	}
	
	public FTextField[] getRequiredFields() {
		FTextField[] arr = { memberid, isbn };
		return arr; 
	}
	
	public HBox checkout(BookCopy copy, LibraryMember member) {
		HBox box = new HBox();
		FButton confirm = new FButton("Confirm Checkout");
		confirm.setOnAction((event)->CheckoutRecord.confirmCheckout(copy, member));
		FButton cancel = new FButton("Cancel");
		cancel.setOnAction((event)->Controller.cancel());
		box.getChildren().addAll(confirm, cancel);
		return box;
	}
	
	
	public String getMemberid() {
		return memberid.getText();
	}

	public String getIsbn() {
		return isbn.getText();
	}
	
}
