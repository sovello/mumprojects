package librarymanager.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import librarymanager.controllers.*;
import space.fugit.jfx.*;

public class LoginForm implements Form {
	
	private FTextField username;
	private FPasswordField password;
	private FButton loginButton;
	private FText txt;
	//define fields
	public LoginForm() {
		this.username = new FTextField();
		this.password = new FPasswordField();
		this.loginButton = new FButton("Login");
		this.loginButton.setOnAction((event)->Controller.setFormData(this));
		this.txt = new FText();
		this.txt.setStyle("-fx-fill:#cc0000; -fx-font-size:16px;");
	}
	
	public FVBox getForm() {
		FVBox p = new FVBox();
		p.getChildren().add(username.vLabel("Username"));
		p.getChildren().add(password.vLabel("Password"));
		HBox box = new HBox();
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(loginButton);
		p.getChildren().add(box);
		p.getChildren().add(txt);
		return p;
	}
	
	public FTextField[] getRequiredFields() {
		FTextField[] arr = { username };
		return arr; 
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	
	public String getPassword() {
		return this.password.getText();
	}
	
	public FText getMessageDisplay() {
		return this.txt;
	}
}
