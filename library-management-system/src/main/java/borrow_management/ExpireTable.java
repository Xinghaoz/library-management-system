package borrow_management;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExpireTable extends JPanel {
	JTable table;
	
	public ExpireTable(List<Expire> expires) {
		Object[][] data = new Object[expires.size()][4];
		
		int i = 0;
		for (Expire expire : expires) {
			data[i][0] = expire.getBookId();
			data[i][1] = expire.getReaderId();
			data[i][2] = expire.getBorrowDate();
			data[i][3] = expire.getBorrowDays();
			i++;
		}
		
		String[] columns = {"书号", "读者卡号", "借书日期", "借书天数"};
		table = new JTable(data, columns);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
