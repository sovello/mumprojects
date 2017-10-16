package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import librarymanager.controllers.Controller;
import space.fugit.jfx.FButton;
import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public class BookForm implements Form {
	private FTextField isbn, title, author, maxCheckOutLength, numberOfCopies;
	private FButton createbook;
	public BookForm() {
		this.isbn = new FTextField();
		this.title = new FTextField();
		this.author = new FTextField();
		this.numberOfCopies = new FTextField();
		this.numberOfCopies.setTooltip("This value must be an integer!");
		this.maxCheckOutLength = new FTextField();
		this.maxCheckOutLength.setTooltip("This value must be an integer!");
		this.createbook = new FButton("Add Book");
		this.createbook.setOnAction((event)->Controller.setFormData(this));
	}
	
	public FTextField[] getRequiredFields() {
		FTextField[] arr = { isbn, title, maxCheckOutLength };
		return arr; 
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(title.vLabel("Title"));
		p.getChildren().add(isbn.vLabel("ISBN"));
		p.getChildren().add(author.vLabel("Author"));
		p.getChildren().add(numberOfCopies.vLabel("Number of Copies"));
		p.getChildren().add(maxCheckOutLength.vLabel("Maximum Checkout Time"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(createbook);
		p.getChildren().add(box);
		return p;
	}

	
	public String getIsbn() {
		return isbn.getText();
	}

	public String getTitle() {
		return title.getText();
	}

	public String getAuthor() {
		return author.getText();
	}

	public String getMaxCheckOutLength() {
		return maxCheckOutLength.getText();
	}

	public String getNumberOfCopies() {
		return numberOfCopies.getText();
	}
	
	public FTextField getCopiesField() {
		return this.numberOfCopies;
	}
}

