package book_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class BookModifyPanel extends JPanel implements ActionListener, TableModelListener {
	JButton backButton, searchButton, commitButton;
	JTextField text;
	BookMenu previousLevel;
	BookTable table;
	JPanel tablePanel, wrapperPanel;
	TableModelListener listener;
	AbstractTableModel a;
	
	public BookModifyPanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
				

		JLabel label = new JLabel("请输入需要修改的书号：");
		
        backButton = new JButton("返回");
        searchButton = new JButton("查找");
        text = new JTextField();
        tablePanel = new JPanel();
        wrapperPanel = new JPanel();
        wrapperPanel.setPreferredSize(new Dimension(250, 100));
        
        text.setPreferredSize(new Dimension(250, 25));
        wrapperPanel.add(label);
        wrapperPanel.add(text);
        wrapperPanel.add(searchButton);
        wrapperPanel.add(backButton);
        
        backButton.addActionListener(this);
        searchButton.addActionListener(this);
		
        add(wrapperPanel);
		add(tablePanel);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == searchButton) {
			String queryId = text.getText();
			if (queryId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "书号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else if (!queryId.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "书号必须为纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {
				Book book = BookManager.get(queryId);
				if (book == null) {
					JOptionPane.showMessageDialog(null, "查询的书号不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else {
					table = new BookTable(book, true);
					commitButton = new JButton("修改");
					commitButton.addActionListener(this);

					tablePanel.removeAll();
					tablePanel.add(table);
					tablePanel.add(commitButton);
					validate();
					previousLevel.repaint();
				}
			}
		} else if (event.getSource() == commitButton) {
//			table.getTable().repaint();
			/*
			 * 	private String id;
				private String name;
				private String author;
				private String publisher;
				private Date date;
				private float price;
				private String category;
			 */

			JTable temp = table.getTable();
			temp.getParent().requestFocus();
			temp.getParent().requestFocusInWindow();
			String id = (String) temp.getValueAt(0, 0);
			String name = (String) temp.getValueAt(0, 1);
			String author = (String) temp.getValueAt(0, 2);
			String publisher = (String) temp.getValueAt(0, 3);
			String dateStr = temp.getValueAt(0, 4).toString();
			Float price = Float.valueOf(temp.getValueAt(0, 5).toString());
			String category = (String) temp.getValueAt(0, 6);
						
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
}
