package book_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class BookSearchPanel extends JPanel implements ActionListener {
	JButton backButton, searchButton, commitButton;
	JTextField text, fromText, toText;
	BookMenu previousLevel;
	BookTable table;
	JPanel tablePanel, pricePanel;
	TableModelListener listener;
	AbstractTableModel a;
	JComboBox comboBox;
	String searchField = "id";
	
	public BookSearchPanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
				
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
//		table = new BookTable(BookManager.getAll());
//		JLabel label = new JLabel("请输入需要修改的书号：");
		String[] options = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
		 
        backButton = new JButton("返回");
        searchButton = new JButton("查找");
        text = new JTextField();
        fromText = new JTextField();
        toText = new JTextField();
        tablePanel = new JPanel();
        pricePanel = new JPanel();
        comboBox = new JComboBox(options);
        JLabel fromLabel = new JLabel("From"), toLabel = new JLabel("To");
        
        
        comboBox.setBorder(BorderFactory.createTitledBorder("请选择需要查询的字段")); 
        comboBox.setPreferredSize(new Dimension(166, 66));
//        text.setSize(new Dimension(100, 25));
        text.setPreferredSize(new Dimension(100, 25));
        fromText.setPreferredSize(new Dimension(100, 25));
        toText.setPreferredSize(new Dimension(100, 25));
        pricePanel.setVisible(false);
        
        backButton.addActionListener(this);
        searchButton.addActionListener(this);
        comboBox.addActionListener(this);
		
//        add(label);
        add(comboBox);
        add(text);
        add(pricePanel);
        add(searchButton);        
		add(backButton);
		add(tablePanel);
		pricePanel.add(fromLabel);
		pricePanel.add(fromText);
		pricePanel.add(toLabel);
		pricePanel.add(toText);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == searchButton) {
			List<Book> books = new ArrayList<>();
			boolean valid = false;
			String field = (String) comboBox.getSelectedItem();
			if (!searchField.equals("price")) {
				String value = text.getText();
				if (value.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入" + field + "！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else {
					books = BookManager.search(searchField, value);
					valid = true;
				}
			} else {
				String fromStr = fromText.getText();
				String toStr = toText.getText();
				if (fromStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入查询的起始价格！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (toStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入查询的终止价格！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else {
					try {
						Float.valueOf(fromStr);
						Float.valueOf(toStr);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "输入不合法！", "系统信息", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					books = BookManager.searchPrice(fromStr, toStr);
					valid = true;
				}
			}
			
			if (valid) {
				if (books.size() == 0) {
					JOptionPane.showMessageDialog(null, "未找到查询的记录！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else {
					table = new BookTable(books);
					
					tablePanel.removeAll();
					tablePanel.add(table);
					validate();
					previousLevel.repaint();
				}
			}			
		} else if (event.getSource() == comboBox) {
			int index = comboBox.getSelectedIndex();
			text.setVisible(true);
			pricePanel.setVisible(false);
			switch (index) {
				case 0: searchField = "id"; break;
				case 1: searchField = "name"; break;
				case 2: searchField = "author"; break;
				case 3: searchField = "publisher"; break;
				case 4: searchField = "date"; break;
				case 5: {
					searchField = "price"; 
					text.setVisible(false);
					pricePanel.setVisible(true);
					break;
				}
				case 6: searchField = "category"; break;				
			}
		}	
	}
}
