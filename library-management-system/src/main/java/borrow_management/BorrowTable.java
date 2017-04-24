package borrow_management;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BorrowTable extends JPanel {
	JTable table;
	
	public BorrowTable(List<Borrow> borrows) {
		Object[][] data = new Object[borrows.size()][3];
		
		int i = 0;
		for (Borrow borrow : borrows) {
			data[i][0] = borrow.getBookId();
			data[i][1] = borrow.getReaderId();
			data[i][2] = borrow.getBorrowDate();
//			data[i][3] = borrow.getReturnDate();
			i++;
		}
		
		String[] columns = {"读者卡号", "书号", "借书日期"};
		table = new JTable(data, columns);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
