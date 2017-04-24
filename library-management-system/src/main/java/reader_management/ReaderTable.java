package reader_management;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import book_management.Book;

public class ReaderTable extends JPanel {
	JTable table;
	
	public ReaderTable(List<Reader> readers) {
		Object[][] data = new Object[readers.size()][4];
		
		int i = 0;
		for (Reader reader : readers) {
			data[i][0] = reader.getId();
			data[i][1] = reader.getName();
			data[i][2] = reader.getGender();
			data[i][3] = reader.getStudentNumber();
			i++;
		}
		
		String[] columns = {"卡号", "姓名", "性别", "学(工)号"};
		table = new JTable(data, columns);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(360,250));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
