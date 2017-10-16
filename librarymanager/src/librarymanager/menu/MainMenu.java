package librarymanager.menu;

import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import librarymanager.controllers.Controller;
import librarymanager.user.UserRole;

public class MainMenu {
	
	private static String[] admin = {"Add member info", 
				"Add New Book", 
				"Books List",
				//"Edit Member Info",
				"Logout"
			};
	
	private static String[] librarian = {
				"Check Out Books", 
				"Return Book Copies", 
				"Books List", 
				"Book Loan Status", 
				"Logout"
			};
	
	private static String[] allMenu = {
				"Add member info", 
				"Edit Member Info", 
				"Add New Book", 
				"Check Out Books", 
				"Return Book Copies",
				"Book Loan Status", 
				"Books List", "Logout"
			};
	
	private static String[] initialMenu = {"Login", "Technical Support"};
	
	public MainMenu() {
		
	}
	
	public static VBox loadMenuFor(UserRole role) {
		switch (role) {
		case Admin:
			return loadAdminMenu();
		case Librarian:
			return loadLibrarianMenu();
		default:
			return loadAllMenus();
		}
	}
	@SuppressWarnings("unchecked")
	public static VBox loadInitialMenu() {
		Hyperlink[] hyperlinks = new Hyperlink[initialMenu.length];
		
		for( int i = 0; i < initialMenu.length; i++ ) {
		final Hyperlink hpl = hyperlinks[i] = new Hyperlink(initialMenu[i]);
		hpl.setOnAction( new Controller() );
		}
		return new VBox(10, hyperlinks);
	}
	
	@SuppressWarnings("unchecked")
	public static VBox loadAdminMenu() {
		Hyperlink[] hyperlinks = new Hyperlink[admin.length];
		
		for( int i = 0; i < admin.length; i++ ) {
		final Hyperlink hpl = hyperlinks[i] = new Hyperlink(admin[i]);
		hpl.setOnAction( new Controller() );
		}
		VBox vbox = new VBox(10, hyperlinks);
		vbox.setMinWidth(300);
		return vbox ;
	}
	
	@SuppressWarnings("unchecked")
	public static VBox loadLibrarianMenu() {
		Hyperlink[] hyperlinks = new Hyperlink[librarian.length];
		
		for( int i = 0; i < librarian.length; i++ ) {
		final Hyperlink hpl = hyperlinks[i] = new Hyperlink(librarian[i]);
		hpl.setOnAction( new Controller() );
		}
		
		return new VBox(10, hyperlinks);
	}
	
	@SuppressWarnings("unchecked")
	public static VBox loadAllMenus() {
		Hyperlink[] hyperlinks = new Hyperlink[allMenu.length];
		
		for( int i = 0; i < allMenu.length; i++ ) {
		final Hyperlink hpl = hyperlinks[i] = new Hyperlink(allMenu[i]);
		hpl.setOnAction( new Controller() );
		}
		
		return new VBox(10, hyperlinks);
	}
}
