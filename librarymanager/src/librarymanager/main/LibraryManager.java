package librarymanager.main;

import javafx.application.Application;
import javafx.stage.Stage;
import librarymanager.menu.MainMenu;
import librarymanager.ui.*;

public class LibraryManager extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new MainWindow(primaryStage, "Home - LibraryManager");
		new MainMenu();
		primaryStage.show();	
	}
	
}