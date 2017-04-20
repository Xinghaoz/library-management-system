package reader_management;

import java.util.Date;
import java.util.List;

import book_management.Book;

public class Reader {
	private String id;
	private String name;
	private String gender;
	private String studentNumber;
	private List<RentBook> borrowedBooks;
	
	class RentBook extends Book {
		private Date borrowDate;
		private Date returnDate;
	}
}
