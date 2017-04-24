package reader_management;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import yanghongquan.library_management_system.MainMenu;

public class ReaderMenu extends JPanel implements ActionListener {
	JButton addButton, showButton, deleteButton, backButton, sortButton;
	MainMenu previousLevel;
	CardLayout card;
	JPanel panel;
	ReaderShowPanel readerShowPanel;
	ReaderAddPanel readerAddPanel;
	ReaderDeletePanel readerDeletePanel;
	ReaderSortPanel readerSortPanel;
	
	public ReaderMenu(MainMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
        JLabel userLabel = new JLabel("读者管理:");
        panel.add(userLabel);

        showButton = new JButton("显示读者");
		addButton = new JButton("添加读者");
		deleteButton = new JButton("删除读者");
		backButton = new JButton("返回");
		sortButton = new JButton("排序");

		showButton.addActionListener(this);
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		sortButton.addActionListener(this);

		// BorderLayout.CENTER
		panel.add(showButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(sortButton);
		panel.add(backButton);

	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainMenu");
		} else if (event.getSource() == showButton) {
			readerShowPanel = new ReaderShowPanel(this);
			add("readerShowPanel", readerShowPanel);
			card.show(this, "readerShowPanel");
		} else if (event.getSource() == addButton) {
			readerAddPanel = new ReaderAddPanel(this);
			add("readerAddPanel", readerAddPanel);
			card.show(this, "readerAddPanel");
		} else if (event.getSource() == deleteButton) {
			readerDeletePanel = new ReaderDeletePanel(this);
			add("readerDeletePanel", readerDeletePanel);
			card.show(this, "readerDeletePanel");
		} else if (event.getSource() == sortButton) {
			readerSortPanel = new ReaderSortPanel(this);
			add("readerSortPanel", readerSortPanel);
			card.show(this, "readerSortPanel");
		} 			
	}
	
	public CardLayout getCard() {
		return this.card;
	}
}
