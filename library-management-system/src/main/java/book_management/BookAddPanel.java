package book_management;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookAddPanel extends JPanel implements ActionListener {
//	JButton addButton, showButton, modifyButton, deleteButton, searchButton, sortButton, backButton;
	JButton commitButton, backButton;
	JLabel[] labels;
	JLabel title;
	JTextField[] texts;
	JPanel panel;
	BookMenu previousLevel;
	CardLayout card;
	
	public BookAddPanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
        title = new JLabel("添加图书:");
        panel.add(title);
        
        String[] strs = {"书号：", "书名：", "作者：", "出版：", "日期：", "定价：", "类别："};
        
        labels = new JLabel[7];
        for (int i = 0; i < labels.length; i++) {
        	labels[i] = new JLabel(strs[i]);
        	labels[i].setPreferredSize(new Dimension(50, 25));
        }
        
        texts = new JTextField[7];
        for (int i = 0; i < labels.length; i++) {
        	texts[i] = new JTextField();
        	texts[i].setPreferredSize(new Dimension(150, 25));
        }
        
        for (int i = 0; i < labels.length; i++) {
        	JPanel temp = new JPanel();
        	temp.add(labels[i]);
        	temp.add(texts[i]);
        	panel.add(temp);
        }
		
        commitButton = new JButton("确认");	
        backButton = new JButton("返回");
        commitButton.addActionListener(this);
        backButton.addActionListener(this);
		
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
				int result = BookManager.add(book);
				if (result == 0) {
					JOptionPane.showMessageDialog(null, "添加成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
					for (JTextField t : texts) {
						
					}
				} else if (result == 1) {
					JOptionPane.showMessageDialog(null, "添加失败！——　数据已存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "添加失败！——　输入数据格式错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
		} 
	}
}
