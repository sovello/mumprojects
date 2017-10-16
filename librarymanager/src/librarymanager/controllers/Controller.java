package librarymanager.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import librarymanager.menu.MainMenu;
import librarymanager.models.Address;
import librarymanager.models.Book;
import librarymanager.models.BookCopy;
import librarymanager.models.CheckoutRecord;
import librarymanager.models.LibraryMember;
import librarymanager.storage.StorageAccess;
import librarymanager.ui.BookForm;
import librarymanager.ui.BooksListView;
import librarymanager.ui.BookReturnForm;
import librarymanager.ui.CheckoutRecordsListView;
import librarymanager.ui.CheckOutForm;
import librarymanager.ui.Form;
import librarymanager.ui.LibraryMemberForm;
import librarymanager.ui.LoginForm;
import librarymanager.ui.MainWindow;
import librarymanager.ui.LibraryMemberCheckoutRecordsListView;
import librarymanager.user.Authentication;
import librarymanager.user.User;

@SuppressWarnings("rawtypes")
public class Controller implements EventHandler { 
	
	private static BorderPane grid = MainWindow.getBorderPane();
	
	@SuppressWarnings("unchecked")
	public static void setFormData(Object obj) {
		
		if(obj instanceof BookForm) {
			BookForm form = (BookForm)obj;
			//validate if there's any empty fields
			if( !Form.isValid(form)) {
				return;
			}
			if( form.getNumberOfCopies().isEmpty() || !form.getNumberOfCopies().matches("[0-9]")) {
				form.getCopiesField().setMessage("This field can only contain numbers > 0");
				return;
			}
			
			HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
			
			Book book = new Book(form.getIsbn(), form.getTitle(), Integer.parseInt(form.getNumberOfCopies()),
					Integer.parseInt(form.getMaxCheckOutLength()));
			if( !book.bookExists(book, books) ) {
				StorageAccess.save(book);
				grid.setCenter(new Text("New Book successfully added!"));
			}
			else {
				grid.setCenter(new Text("Another book has this ISBN"));
			}
		}
		
		if (obj instanceof BookReturnForm) {
			BookReturnForm form = (BookReturnForm)obj;
			//validate if there's any empty fields
			if( !Form.isValid(form)) {
				return;
			}
			String key = form.getIsbn()+"-"+form.getCopyNumber()+"-"+form.getMemberid();
			
			//get book copy from key
			//set available
			HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
			HashMap<String, CheckoutRecord> loanedBooks = 
					(HashMap<String, CheckoutRecord>)StorageAccess.read("CheckoutRecords");;
			
			if( loanedBooks.get(key) != null ) {
				//get the checkout record instance from storage
				CheckoutRecord checkoutRecord = loanedBooks.get(key);
				LibraryMember member = LibraryMember.getLibraryMember(form.getMemberid());
				int index = Integer.parseInt(checkoutRecord.getEntry().getId())-1;
				//get the copy id for the book
				Book currentBook = books.get(checkoutRecord.getEntry().getBookCopy().getBook().getKey());
				//set it available
				
				
				//NEW CODE
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Book Return");
				alert.setHeaderText("Confirm Book Return For");
				alert.setContentText(
						"To: "+member.getFirstName()+" "+member.getLastName()+"\n"+
						"ID #: "+member.getMemberId()+"\n"+
						"Book: "+currentBook.getTitle()+"\n"+
						"ISBN: "+currentBook.getIsbn()+"\n"+
						"Due Date: "+checkoutRecord.getEntry().getCheckoutDate().toString()
						);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					currentBook.getCopies()[index].setAvailable();
					//save for member and book and checkout record
					
					member.returnBook(checkoutRecord.getEntry());
					StorageAccess.save(currentBook);
					checkoutRecord.returnBook(checkoutRecord.getEntry());
					LibraryMemberCheckoutRecordsListView memberBooksList = new LibraryMemberCheckoutRecordsListView(member);
					grid.setCenter(memberBooksList.createViewTable());
				} else {
					grid.setCenter(new Text("What else can I do for you?"));
				}
				grid.setCenter(new Text("Book Return Successful!"));
			}
			else {
				grid.setCenter(new Text("No record was found!"));
			}
			
		}
		
		if( obj instanceof CheckOutForm) {
			CheckOutForm form = (CheckOutForm)obj;
			//validate if there's any empty fields
			if( !Form.isValid(form)) {
				return;
			}
			
			HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
			
			HashMap<String, LibraryMember> members = 
					(HashMap<String, LibraryMember>)StorageAccess.read("LibraryMembers");
			
			if( (books.get(form.getIsbn()) != null) && (members.get(form.getMemberid()) != null)) {
				//book isbn and memberid found in the db, proceed
				//now check if this member doesn't have similar book
				LibraryMember member = members.get(form.getMemberid());
				if(member.hasBook(form.getIsbn()) ) {
					grid.setCenter(new Text("This member has a copy of this book"));
				}
				else {
					
					Book book = books.get(form.getIsbn());
					if( book.getAvailableCopy() != null) {
						//book copy is available
						BookCopy availableCopy = book.getAvailableCopy();
						
						//checkout the book
						
						//NEW CODE
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation Checkout");
						alert.setHeaderText("Confirm checkout for");
						alert.setContentText(
								"Book: "+book.getTitle()+"\n"+
								"ISBN: "+book.getIsbn()+"\n"+
								"To: "+member.getFirstName()+" "+member.getLastName()+"\n"+
								"ID #: "+member.getMemberId()
								);

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
							book.getAvailableCopy().setNotAvailable(); 
							//set it not available so it doesn't show in future searches
							StorageAccess.save(book);
							//we are saving because the user confirmed.
							CheckoutRecord.confirmCheckout(availableCopy, member);
							LibraryMemberCheckoutRecordsListView memberBooksList = new LibraryMemberCheckoutRecordsListView(member);
							grid.setCenter(memberBooksList.createViewTable());
						} else {
							grid.setCenter(new Text("What else can I do for you?"));
						}
					}
					else {
						grid.setCenter(new Text("The book is not available for borrowing"));
					}
				}
			}
			else if(books.get(form.getIsbn()) != null) {
				grid.setCenter(new Text("Member is not found"));
			}
			else if(members.get(form.getMemberid()) != null) {
				grid.setCenter(new Text("Book is not found"));
			}
			else {
				grid.setCenter(new Text("No record was found!"));
			}
		}

		if(obj instanceof LibraryMemberForm ) {
			LibraryMemberForm form = (LibraryMemberForm)obj;
			//validate for empty fields
			if( !Form.isValid(form)) {
				return;
			}
			
			HashMap<String, LibraryMember> members = 
					(HashMap<String, LibraryMember>)StorageAccess.read("LibraryMembers");
			
			if( members.get(form.getMemberId().getText()) == null ) {
			Address addr = new Address(form.getAddress().getCity().getText(), form.getAddress().getState().getText(), 
					form.getAddress().getStreet().getText(), form.getAddress().getZip().getText());
			LibraryMember libraryMember = new LibraryMember(form.getMemberId().getText(), form.getFirstName().getText(),
					form.getLastName().getText(), form.getPhone().getText(), addr);
			//if( !libraryMember.memberExists(libraryMember, members)) {
				StorageAccess.save(libraryMember);
				grid.setCenter(new Text("New member successfully added!"));
			}
			else {
				grid.setCenter(new Text("There exists a member with this ID"));
			}
			
		}
		
		if( obj instanceof LoginForm ) {
			LoginForm form = (LoginForm)obj;
			if(!Form.isValid(form)) {
				return;
			}
			User user = new User( form.getUsername(), form.getPassword());
			if( Authentication.login(user) ) {
				grid.setLeft(MainMenu.loadMenuFor(user.getRole()));
				grid.setCenter(new Text("Welcome " + user.getUsername() +" to FUGIT Library Manager"));	
			}
			else {
					form.getMessageDisplay().setText("Invalid username or password!");;
				}
		}
	}
	
	public static void cancel(){
		
	}
	
	public static void logout() {
		grid.setLeft(MainMenu.loadInitialMenu());
		grid.setCenter(MainWindow.setInitialContent());
	}
	
	public static void loadForm(Form form) {
		grid.setCenter(form.getForm());
	}
	
	public static void login(LoginForm form) {
		User user = new User( form.getUsername(), form.getPassword());
		if( Authentication.login(user) ) {
		}
		else {
				form.getMessageDisplay().setText("Invalid username or password!");;
			}
		grid.setCenter(new Text("Welcome to FUGIT Library Manager"));	
	}
	
	
	@Override
	public void handle(Event event) {
		Object obj = event.getSource();

		if( obj instanceof Hyperlink) {
			Hyperlink hp = (Hyperlink)obj;
			BorderPane grid = MainWindow.getBorderPane();
			if(hp.getText().equals("Add member info")) {
				LibraryMemberForm member = new LibraryMemberForm();
				grid.setCenter(member.getForm());
			}

			if(hp.getText().equals("Add New Book")) {
				BookForm form = new BookForm();
				grid.setCenter(form.getForm());
			}
			if(hp.getText().equals("Login")) {
				LoginForm form = new LoginForm();
				grid.setCenter(form.getForm());
			}
			if(hp.getText().equals("Logout")) {
				logout();
			}
			if(hp.getText().equals("Check Out Books")) {
				CheckOutForm form = new CheckOutForm();
				grid.setCenter(form.getForm());
			}
			if(hp.getText().equals("Return Book Copies")) {
				BookReturnForm form = new BookReturnForm();
				grid.setCenter(form.getForm());
			}
			if(hp.getText().equals("Books List")) {
				BooksListView bklist = new BooksListView();
				grid.setCenter(bklist.createViewTable());
			}
			if(hp.getText().equals("Bookse List")) {
				@SuppressWarnings("unchecked")
				HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
				String bookTitle = "";
				Iterator<Book> it = books.values().iterator();
				while(it.hasNext()) {
					bookTitle += it.next()+"\n";
				}
					grid.setCenter(new Text(bookTitle));
			}
			if(hp.getText().equals("Book Loan Status")) {
				CheckoutRecordsListView tableview = new CheckoutRecordsListView();
				grid.setCenter(tableview.createViewTable());
			}
		}
	}
}
