package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import librarymanager.controllers.Controller;
import space.fugit.jfx.*;

public class LibraryMemberForm extends PersonForm implements Form{
	//private static FTextField firstname, lastname, phone;
	private FButton memberButton;
	private FTextField memberId;
	public LibraryMemberForm() {
		super();
		memberId = new FTextField();
		memberButton = new FButton("Create Member");
		memberButton.setOnAction((event)->Controller.setFormData(this));
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(super.getFirstName().vLabel("First Name"));
		p.getChildren().add(super.getLastName().vLabel("Last Name"));
		p.getChildren().add(super.getPhone().vLabel("Phone Number"));
		p.getChildren().add(super.getAddress().getStreet().vLabel("Street"));
		p.getChildren().add(super.getAddress().getCity().vLabel("City"));
		p.getChildren().add(super.getAddress().getState().vLabel("State"));
		p.getChildren().add(super.getAddress().getZip().vLabel("Zip"));
		p.getChildren().add(memberId.vLabel("Member ID"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(memberButton);
		p.getChildren().add(box);
		return p;
	}

	public FTextField[] getRequiredFields() {
		FTextField[] arr = { memberId, super.getFirstName(), super.getLastName() };
		return arr; 
	}
	
	public FButton getMemberButton() {
		return memberButton;
	}

	public void setMemberButton(FButton memberButton) {
		this.memberButton = memberButton;
	}

	public FTextField getMemberId() {
		return memberId;
	}	
	
	
}
