package book_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookSortPanel extends JPanel implements ActionListener  {
	JButton backButton, sortButton, commitButton;
	JPanel tablePanel;
	JComboBox comboBox;
	BookMenu previousLevel;
	String sortField = "id";
	BookTable table;

	public BookSortPanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
//		table = new BookTable(BookManager.getAll());
//		JLabel label = new JLabel("请输入需要修改的书号：");
		String[] options = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
		 
        backButton = new JButton("返回");
        sortButton = new JButton("排序");
        tablePanel = new JPanel();
        comboBox = new JComboBox(options);
        
        comboBox.setBorder(BorderFactory.createTitledBorder("请选择需要排序的字段")); 
        comboBox.setPreferredSize(new Dimension(166, 66));
        
        backButton.addActionListener(this);
        sortButton.addActionListener(this);
        comboBox.addActionListener(this);
        
        add(comboBox);
        add(sortButton);        
		add(backButton);
		add(tablePanel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == sortButton) {
			List<Book> books = new ArrayList<>();
			boolean valid = false;
			books = BookManager.sortBy(sortField);
			table = new BookTable(books);
			
			tablePanel.removeAll();
			tablePanel.add(table);
			validate();
			previousLevel.repaint();
		} else if (event.getSource() == comboBox) {
			int index = comboBox.getSelectedIndex();
			switch (index) {
				case 0: sortField = "id"; break;
				case 1: sortField = "name"; break;
				case 2: sortField = "author"; break;
				case 3: sortField = "publisher"; break;
				case 4: sortField = "date"; break;
				case 5: sortField = "price"; break;
				case 6: sortField = "category"; break;				
			}
		}	
	}

}
