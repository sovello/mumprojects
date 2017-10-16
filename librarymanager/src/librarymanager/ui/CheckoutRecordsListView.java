package librarymanager.ui;

import java.time.LocalDate;
import java.util.*;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import librarymanager.models.CheckoutRecord;
import librarymanager.models.CheckoutRecordEntry;
import librarymanager.storage.StorageAccess;

public class CheckoutRecordsListView {
	
	//this retrieves all entries with due date after today
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> loanedBooks = 
			(HashMap<String, CheckoutRecord>)StorageAccess.read("CheckoutRecords");
	private final TableView<CheckoutRecordEntry> table = new TableView<>();
	@SuppressWarnings("unchecked")
	public CheckoutRecordsListView() {
		table.setEditable(false);
		table.setMinWidth(700);
		TableColumn<CheckoutRecordEntry, String> isbn = new TableColumn<>("ISBN");
		TableColumn<CheckoutRecordEntry, String> id = new TableColumn<>("ID");
		TableColumn<CheckoutRecordEntry, String> member = new TableColumn<>("Member ID");
		TableColumn<CheckoutRecordEntry, LocalDate> checkoutdate = new TableColumn<>("Checked Out");
		TableColumn<CheckoutRecordEntry, LocalDate> duedate = new TableColumn<>("Due date");
		TableColumn<CheckoutRecordEntry, LocalDate> returndate = new TableColumn<>("Return date");
		isbn.setMinWidth(200);
		id.setMinWidth(100);
		member.setMinWidth(200);
		checkoutdate.setMinWidth(200);
		duedate.setMinWidth(200);
		returndate.setMinWidth(200);
		
		if( loanedBooks == null || loanedBooks.size() == 0 ) {
			return;
		}
		Iterator<CheckoutRecord> it = loanedBooks.values().iterator();
		List<CheckoutRecordEntry> data = new ArrayList<CheckoutRecordEntry>();
		while(it.hasNext()) {
			CheckoutRecord ch = (CheckoutRecord)it.next();
			data.add(ch.getEntry());
		}
		
		table.getColumns().addAll(isbn, id, member, checkoutdate, duedate, returndate);
		
		ObservableList<CheckoutRecordEntry> tableData = FXCollections.observableArrayList(data);
		
		isbn.setCellValueFactory(
			    new PropertyValueFactory<CheckoutRecordEntry, String>("isbn")
			);
		id.setCellValueFactory(
				new PropertyValueFactory<CheckoutRecordEntry, String>("id")
			);
		member.setCellValueFactory(
				new PropertyValueFactory<CheckoutRecordEntry, String>("memberid")
			);
		checkoutdate.setCellValueFactory(
				new PropertyValueFactory<CheckoutRecordEntry, LocalDate>("checkoutDate")
			);
		duedate.setCellValueFactory(
				new PropertyValueFactory<CheckoutRecordEntry, LocalDate>("dueDate")
			);
		returndate.setCellValueFactory(
				new PropertyValueFactory<CheckoutRecordEntry, LocalDate>("returnDate")
			);
		
		table.setItems(tableData);
	}
	
	public VBox createViewTable() {
		VBox hBox = new VBox(10);
		hBox.getChildren().add(new Text("List of all books loaned and returned"));
		hBox.getChildren().add(this.table);
		return hBox;
	}
}
