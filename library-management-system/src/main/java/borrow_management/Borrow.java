package borrow_management;

import java.util.Date;

public class Borrow {
	String bookId;
	String readerId;
	Date borrowDate;
	Date returnDate;
	
	public Borrow(String bookId, String readerId, Date borrowDate) {
		this.bookId = bookId;
		this.readerId = readerId;
		this.borrowDate = borrowDate;
	}
	
	public Borrow(String bookId, String readerId, Date borrowDate, Date returnDate) {
		this.bookId = bookId;
		this.readerId = readerId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
