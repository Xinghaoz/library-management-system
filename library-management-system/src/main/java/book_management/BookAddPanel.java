package book_management;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import yanghongquan.library_management_system.BookTable;

public class BookAddPanel extends JPanel implements ActionListener {
//	JButton addButton, showButton, modifyButton, deleteButton, searchButton, sortButton, backButton;
	JButton commitButton, backButton;
	JLabel[] labels;
	JLabel title;
	JTextField[] texts;
	JPanel panel;
	BookPanel previousLevel;
	CardLayout card;
	
	public BookAddPanel(BookPanel previousLevel) {
		this.previousLevel = previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
        title = new JLabel("图书管理:");
        panel.add(title);
        
        String[] strs = {"书号", "书名", "作者", "出版", "日期", "定价", "类别"};
        
        labels = new JLabel[7];
        for (int i = 0; i < labels.length; i++) {
        	labels[i] = new JLabel(strs[i]);
        	panel.add(labels[i]);
        }
        
        texts = new JTextField[7];
        for (int i = 0; i < labels.length; i++) {
        	texts[i] = new JTextField();
        	panel.add(texts[i]);
        	
        }
		
        commitButton = new JButton("确认");	
        backButton = new JButton("返回");
        commitButton.addActionListener(this);
        backButton.addActionListener(this);
		
		// BorderLayout.CENTER
		panel.add(commitButton);
		panel.add(backButton);
//		panel.add(modifyButton);

	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
//			card.show(this, "previousLevel");
		} else if (event.getSource() == commitButton) {
			String id = texts[0].getText();
			String name = texts[1].getText();
			String author = texts[2].getText();
			String publisher = texts[3].getText();
			Date date = new Date();
			float price = texts[5].getText().isEmpty() ? (float) 0.0 : Float.valueOf(texts[5].getText());
			String category = texts[6].getText();
			
//			String name = texts[1].getText().isEmpty() ? "" : texts[1].getText();
//			String author = texts[2].getText().isEmpty() ? "" : texts[2].getText();
//			String publisher = texts[3].getText().isEmpty() ? "" : texts[3].getText();
//			Date date = new Date();
//			float price = texts[5].getText().isEmpty() ? (float) 0.0 : Float.valueOf(texts[5].getText());
//			String category = texts[6].getText().isEmpty() ? "" : texts[6].getText();
			
			if (id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "书号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {			
				Book book = new Book(id, name, author, publisher, new java.sql.Date(0), price, category);
				if (BookManager.add(book)) {
					JOptionPane.showMessageDialog(null, "添加成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
					for (JTextField t : texts) {
						
					}
				} else {
					JOptionPane.showMessageDialog(null, "添加失败！——　数据已存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
		} 
	}
}
