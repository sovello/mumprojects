package librarymanager.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import librarymanager.models.Author;
import librarymanager.models.Book;
import librarymanager.storage.StorageAccess;

public class BooksListView {
	//this retrieves all entries with due date after today
	public class MyBook {
		String title, isbn, authors, copies, checkouttime;
		public MyBook(){
			//nothing
		}
		
		public String getTitle() { return title;}
		public String getIsbn() { return isbn;}
		public String getAuthors() { return authors; }
		public String getCopies() { return copies; }
		public String getCheckouttime() { return checkouttime; }
	}
		public HashMap<String, Book> bookslist;
		public HashMap<String, MyBook> mybookslist;
		private final TableView<MyBook> table = new TableView<>();
		@SuppressWarnings("unchecked")
		public BooksListView() {
			table.setEditable(false);
			table.setMinWidth(700);
			TableColumn<MyBook, String> isbn = new TableColumn<>("ISBN");
			TableColumn<MyBook, String> title = new TableColumn<>("Title");
			TableColumn<MyBook, String> authors = new TableColumn<>("Authors");
			TableColumn<MyBook, String> copies = new TableColumn<>("Copies");
			TableColumn<MyBook, String> checkouttime = new TableColumn<>("Checkout Time (days)");
			isbn.setMinWidth(100);
			title.setMinWidth(100);
			authors.setMinWidth(100);
			copies.setMinWidth(100);
			checkouttime.setMinWidth(100);
			HashMap<String, Book> bookslist = (HashMap<String, Book>)StorageAccess.read("Books");
			
			Iterator<Book> it = bookslist.values().iterator();
			List<MyBook> data = new ArrayList<MyBook>();
			
			while(it.hasNext()) {
				Book ch = (Book)it.next();
				MyBook newbook = new MyBook();
				StringBuffer auth = new StringBuffer();
				for(Author author: ch.getAuthors()) {
					auth.append(author.getLastName().toUpperCase()+" "+author.getFirstName()+", ");
				}
				newbook.authors = auth.substring(0, auth.length());
				newbook.isbn = ch.getIsbn();
				newbook.title = ch.getTitle();
				newbook.copies = String.format("%d", ch.getCopies().length);
				newbook.checkouttime = String.format("%d", ch.getMaxCheckoutLength());
				data.add(newbook);
			}
			
			table.getColumns().addAll(title, isbn, authors, copies, checkouttime);
			
			ObservableList<MyBook> tableData = FXCollections.observableArrayList(data);
			
			isbn.setCellValueFactory(
				    new PropertyValueFactory<MyBook, String>("isbn")
				);
			title.setCellValueFactory(
					new PropertyValueFactory<MyBook, String>("title")
				);
			authors.setCellValueFactory(
					new PropertyValueFactory<MyBook, String>("authors")
				);
			copies.setCellValueFactory(
					new PropertyValueFactory<MyBook, String>("copies")
				);
			checkouttime.setCellValueFactory(
					new PropertyValueFactory<MyBook, String>("checkouttime")
				);
			
			table.setItems(tableData);
		}
		
		public TableView<MyBook> createViewTable() {
			return this.table;
		}
}
