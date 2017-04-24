package borrow_management;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import reader_management.ReaderAddPanel;
import reader_management.ReaderDeletePanel;
import reader_management.ReaderShowPanel;
import yanghongquan.library_management_system.MainMenu;

public class BorrowMenu extends JPanel implements ActionListener {
	JButton addButton, showButton, deleteButton, backButton;
	MainMenu previousLevel;
	CardLayout card;
	JPanel panel;
	BorrowShowPanel borrowShowPanel;
	BorrowAddPanel borrowAddPanel;
	BorrowDeletePanel borrowDeletePanel;
	
	public BorrowMenu(MainMenu previousLevel) {
		this.previousLevel = previousLevel;
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
        JLabel userLabel = new JLabel("借还管理:");
        panel.add(userLabel);
        
        showButton = new JButton("显示借书记录");
		addButton = new JButton("借书");
		deleteButton = new JButton("还书");
		backButton = new JButton("返回");
		
		showButton.addActionListener(this);
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		// BorderLayout.CENTER
		panel.add(showButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(backButton);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainMenu");
		} else if (event.getSource() == showButton) {
			borrowShowPanel = new BorrowShowPanel(this);
			add("borrowShowPanel", borrowShowPanel);
			card.show(this, "borrowShowPanel");
		} else if (event.getSource() == addButton) {
			borrowAddPanel = new BorrowAddPanel(this);
			add("borrowAddPanel", borrowAddPanel);
			card.show(this, "borrowAddPanel");
		} else if (event.getSource() == deleteButton) {
			borrowDeletePanel = new BorrowDeletePanel(this);
			add("borrowDeletePanel", borrowDeletePanel);
			card.show(this, "borrowDeletePanel");
		} 	
	}
	public CardLayout getCard() {
		return this.card;
	}
}