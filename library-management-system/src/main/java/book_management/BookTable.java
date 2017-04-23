package book_management;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import yanghongquan.library_management_system.Main;

public class BookTable extends JPanel {
	JTable table;
	
	public BookTable(List<Book> books) {
		Object[][] data = new Object[books.size()][7];
		
//		System.out.println("Create table");
		int i = 0;
		for (Book book : books) {
			data[i][0] = book.getId();
			data[i][1] = book.getName();
			data[i][2] = book.getAuthor();
			data[i][3] = book.getPublisher();
			data[i][4] = book.getDate();
			data[i][5] = book.getPrice();
			data[i][6] = book.getCategory();
			i++;
		}
		
		String[] columns = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
		table = new JTable(data, columns);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(550,100));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	public BookTable(Book book) {
		setSize(new Dimension(100, 25));
		Object[][] data = new Object[1][7];		

		data[0][0] = book.getId();
		data[0][1] = book.getName();
		data[0][2] = book.getAuthor();
		data[0][3] = book.getPublisher();
		data[0][4] = book.getDate();
		data[0][5] = book.getPrice();
		data[0][6] = book.getCategory();

		
		String[] columns = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
		table = new JTable(data, columns);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(550,25));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	public BookTable(Book book, boolean canModify) {
		setSize(new Dimension(100, 25));
		Object[][] data = new Object[1][7];		

		data[0][0] = book.getId();
		data[0][1] = book.getName();
		data[0][2] = book.getAuthor();
		data[0][3] = book.getPublisher();
		data[0][4] = book.getDate();
		data[0][5] = book.getPrice();
		data[0][6] = book.getCategory();

		
		String[] columns = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
		table = new JTable(data, columns);
		table.setEnabled(canModify);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(550,25));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	public JTable getTable() {
		return table;
	}

	public static void main(String[] args) {
		Main main = new Main();
//		JFrame frame = new JFrame("图书管理系统");
//        frame.setSize(666, 250);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("图书信息管理系统");
//        
//        BookTable panel = new BookTable(BookManager.getAll());
//        frame.add(panel);
////        for (Book book : books) {
////        	System.out.println(book);
////        }
//        
//        frame.setVisible(true);
	}

}
