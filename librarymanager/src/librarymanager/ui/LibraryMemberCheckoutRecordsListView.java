package librarymanager.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import librarymanager.models.CheckoutRecordEntry;
import librarymanager.models.LibraryMember;
import librarymanager.storage.StorageAccess;

@SuppressWarnings("unchecked")
public class LibraryMemberCheckoutRecordsListView {
	
		public class MemberLoanedBooksList {
			String isbn, id, title;
			LocalDate checkoutdate, duedate, returndate;
			MemberLoanedBooksList(){
				
			}
			public String getIsbn() { return isbn; }
			
			public String getId() { return id; }
			
			public String getTitle() { return title; }
			
			public LocalDate getCheckoutdate() { return checkoutdate; }
			
			public LocalDate getDuedate() { return duedate; }
			
			public LocalDate getReturndate() { return returndate; }
			
		}
		//this retrieves all entries with due date after today
		private HashMap<String, LibraryMember> librarymembers = 
				(HashMap<String, LibraryMember>)StorageAccess.read("LibraryMembers");
		private LibraryMember member;
		private final TableView<MemberLoanedBooksList> table = new TableView<>();
		public LibraryMemberCheckoutRecordsListView(LibraryMember member) {
			table.setEditable(false);
			table.setMinWidth(700);
			TableColumn<MemberLoanedBooksList, String> isbn = new TableColumn<>("ISBN");
			TableColumn<MemberLoanedBooksList, String> id = new TableColumn<>("ID");
			TableColumn<MemberLoanedBooksList, String> title = new TableColumn<>("Book Title");
			TableColumn<MemberLoanedBooksList, LocalDate> checkoutdate = new TableColumn<>("Checked Out");
			TableColumn<MemberLoanedBooksList, LocalDate> duedate = new TableColumn<>("Due date");
			TableColumn<MemberLoanedBooksList, LocalDate> returndate = new TableColumn<>("Return date");
			isbn.setMinWidth(100);
			id.setMinWidth(100);
			title.setMinWidth(100);
			checkoutdate.setMinWidth(100);
			duedate.setMinWidth(100);
			returndate.setMinWidth(100);
			
			this.member = librarymembers.get(member.getKey());
			List<CheckoutRecordEntry> checkoutList= this.member.getBooksBorrowed();
			List<MemberLoanedBooksList> data = new ArrayList<>();
			Iterator<CheckoutRecordEntry> it = checkoutList.iterator();
			while(it.hasNext()) {
				CheckoutRecordEntry ch = it.next();
				MemberLoanedBooksList list = new MemberLoanedBooksList();
				list.id = ch.getId();
				list.isbn = ch.getIsbn();
				list.title = ch.getBookCopy().getBook().getTitle();
				list.checkoutdate = ch.getCheckoutDate();
				list.duedate = ch.getDueDate();
				list.returndate = ch.getReturnDate();
				data.add(list);
			}
			
			table.getColumns().addAll(title, isbn, id, checkoutdate, duedate, returndate);
			
			ObservableList<MemberLoanedBooksList> tableData = FXCollections.observableArrayList(data);
			
			isbn.setCellValueFactory(
				    new PropertyValueFactory<MemberLoanedBooksList, String>("isbn")
				);
			id.setCellValueFactory(
					new PropertyValueFactory<MemberLoanedBooksList, String>("id")
				);
			title.setCellValueFactory(
					new PropertyValueFactory<MemberLoanedBooksList, String>("title")
				);
			checkoutdate.setCellValueFactory(
					new PropertyValueFactory<MemberLoanedBooksList, LocalDate>("checkoutdate")
				);
			duedate.setCellValueFactory(
					new PropertyValueFactory<MemberLoanedBooksList, LocalDate>("duedate")
				);
			returndate.setCellValueFactory(
					new PropertyValueFactory<MemberLoanedBooksList, LocalDate>("returndate")
				);
			
			table.setItems(tableData);
		}
		
		public VBox createViewTable() {
			VBox hBox = new VBox(10);
			hBox.getChildren().add(new Text("List of all books loaned by \n"+
			this.member.getLastName().toUpperCase()+", "+this.member.getFirstName()+"\n"+
			"ID # "+this.member.getMemberId()));
			hBox.getChildren().add(this.table);
			return hBox;
		}
}
