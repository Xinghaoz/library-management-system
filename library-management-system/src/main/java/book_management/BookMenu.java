package book_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import yanghongquan.library_management_system.Main;
import yanghongquan.library_management_system.MainMenu;

public class BookMenu extends JPanel implements ActionListener {
	JButton addButton, showButton, modifyButton, deleteButton, searchButton, sortButton, backButton;
	JLabel userLabel;
	JPanel panel;
	MainMenu previousLevel;
	BookTable table;
	CardLayout card;
	BookAddPanel bookAddPanel;
	BookShowPanel bookShowPanel;
	BookDeletePanel bookDeletePanel;
	BookModifyPanel bookModifyPanel;
	BookSearchPanel bookSearchPanel;
	
	public BookMenu(JPanel previousLevel) {
		this.previousLevel = (MainMenu) previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
        userLabel = new JLabel("图书管理:");
        panel.add(userLabel);

        showButton = new JButton("显示记录");
        modifyButton = new JButton("编辑记录");
		addButton = new JButton("增加记录");
		deleteButton = new JButton("删除记录");
		searchButton = new JButton("查询记录");
		sortButton = new JButton("排序记录");
		backButton = new JButton("返回");
		
		showButton.addActionListener(this);
		modifyButton.addActionListener(this);
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		
		// BorderLayout.CENTER
		panel.add(showButton);
		panel.add(modifyButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(searchButton);
		panel.add(backButton);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainMenu");
		} else if (event.getSource() == showButton) {
			bookShowPanel = new BookShowPanel(this);
			add("bookShowPanel", bookShowPanel);
			card.show(this, "bookShowPanel");
		} else if (event.getSource() == modifyButton) {
			bookModifyPanel = new BookModifyPanel(this);
			add("bookModifyPanel", bookModifyPanel);
			card.show(this, "bookModifyPanel");
		} else if (event.getSource() == addButton) {
			bookAddPanel = new BookAddPanel(this);
			add("bookAddPanel", bookAddPanel);
			card.show(this, "bookAddPanel");
		} else if (event.getSource() == deleteButton) {
			bookDeletePanel = new BookDeletePanel(this);
			add("bookDeletePanel", bookDeletePanel);
			card.show(this, "bookDeletePanel");
		} else if (event.getSource() == searchButton) {
			bookSearchPanel = new BookSearchPanel(this);
			add("bookSearchPanel", bookSearchPanel);
			card.show(this, "bookSearchPanel");
		}
	}
	
	public CardLayout getCard() {
		return this.card;
	}
}
