package book_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import yanghongquan.library_management_system.BookTable;
import yanghongquan.library_management_system.Main;
import yanghongquan.library_management_system.MainMenu;

public class BookPanel extends JPanel implements ActionListener {
	JButton addButton, showButton, modifyButton, deleteButton, searchButton, sortButton, backButton;
	JLabel userLabel;
	JPanel panel;
	MainMenu previousLevel;
	BookTable table;
	CardLayout card;
	BookAddPanel bookAddPanel;
	BookShowPanel bookShowPanel;
	
	public BookPanel(JPanel previousLevel) {
		this.previousLevel = (MainMenu) previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		bookAddPanel = new BookAddPanel(this);
		bookShowPanel = new BookShowPanel(this);
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		add("bookAddPanel", bookAddPanel);
		add("bookShowPanel", bookShowPanel);
//		add("previousLevel", previousLevel);
		
        userLabel = new JLabel("图书管理:");
//        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        showButton = new JButton("显示记录");
        modifyButton = new JButton("编辑记录");
		addButton = new JButton("增加记录");
		deleteButton = new JButton("删除记录");
		searchButton = new JButton("查询记录");
		sortButton = new JButton("排序记录");
		backButton = new JButton("返回");
		
		showButton.addActionListener(this);
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		
		// BorderLayout.CENTER
		panel.add(showButton);
//		panel.add(modifyButton);
		panel.add(addButton);
//		panel.add(deleteButton);
		panel.add(backButton);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
//			System.out.println("返回");
//			previousLevel.updateUI();
			previousLevel.getCard().show(previousLevel, "mainMenu");
//			card.show(this, "previousLevel");
		} else if (event.getSource() == showButton) {
			card.show(this, "bookShowPanel");
			
//			if (table == null) {
//				table = new BookTable(BookManager.getAll());
//				panel.add(table);
//			} else {
//				table = new BookTable(BookManager.getAll());
//			}
//			validate();
//			previousLevel.repaint();
		} else if (event.getSource() == addButton) {
			System.out.println("Add");
			card.show(this, "bookAddPanel");
		}
	}
	
	public CardLayout getCard() {
		return this.card;
	}

	public static void main(String[] args) {
		Main main = new Main();

	}

}
