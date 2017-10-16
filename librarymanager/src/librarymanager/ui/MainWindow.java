package librarymanager.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import librarymanager.controllers.Controller;
import librarymanager.menu.MainMenu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainWindow {
	private static Stage stage;
	private static Scene scene;
	private static BorderPane grid;
	public MainWindow(Stage primaryStage, String title) {
		stage = primaryStage;
		stage.setTitle(title);
		grid = new BorderPane();
		grid.setPrefSize(1000, 1100);
	    grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		scene = new Scene(grid);
		String cssurl = this.getClass().getResource("main.css").toExternalForm();
		scene.getStylesheets().add(cssurl);
		Text pagetitle = new Text("FUGIT Library Manager");
		HBox hbox = new HBox();
		hbox.getChildren().add(pagetitle);
		hbox.setId("title");
		grid.setTop(hbox);
		grid.setLeft(MainMenu.loadInitialMenu());
		grid.setCenter(setInitialContent());
		grid.setId("center");
		stage.setScene(scene);
	}
	
	public static BorderPane getBorderPane() {
		return grid;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static VBox setInitialContent() {
		VBox box = new VBox();
		box.getChildren().add(new Text("This system can only do the following\n"+
		"- Check out books\n"+
		"- add new books\n"+
		"- add new library members\n"+
		"- edit library members information\n"+
		"\n To start using the system kindly login first."));
		Hyperlink link = new Hyperlink("Login");
		LoginForm form = new LoginForm();
		link.setOnAction( (event)->Controller.loadForm(form) );
		box.getChildren().add(link);
		return box;
	}
}
