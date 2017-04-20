package yanghongquan.library_management_system;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import book_management.Book;
import book_management.BookManager;

public class BookTable extends JPanel {
	JTable table;
	
	public BookTable(List<Book> books) {
		Object[][] data = new Object[books.size()][7];
		
		System.out.println("Create table");
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

	public static void main(String[] args) {
		JFrame frame = new JFrame("图书管理系统");
        frame.setSize(666, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("图书信息管理系统");
        
        BookTable panel = new BookTable(BookManager.getAll());
        frame.add(panel);
//        for (Book book : books) {
//        	System.out.println(book);
//        }
        
        frame.setVisible(true);
	}

}
