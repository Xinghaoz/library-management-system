package borrow_management;

import java.util.Date;

public class Expire extends Borrow {
	int borrowDays;
	
	public Expire(String bookId, String readerId, Date borrowDate, int days) {
		super(bookId, readerId, borrowDate);
		borrowDays = days;
	}
	public int getBorrowDays() {
		return this.borrowDays;
	}
}
