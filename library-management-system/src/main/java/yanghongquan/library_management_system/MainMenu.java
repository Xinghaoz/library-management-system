package yanghongquan.library_management_system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import book_management.BookPanel;

public class MainMenu extends JPanel implements ActionListener {
	JButton bookManageButton, readerManageButton, borrowManageButton, exitButton;
	JLabel userLabel;
	JPanel panel;
	BookPanel bookPanel;
	CardLayout card;
	
	public MainMenu() {
		bookPanel = new BookPanel(this);
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainMenu", panel);
		add("bookPanel", bookPanel);
		
        userLabel = new JLabel("主菜单:");        
        bookManageButton = new JButton("图书管理");
		readerManageButton = new JButton("读者管理");
		borrowManageButton = new JButton("借还管理");
		exitButton = new JButton("退出系统");
		exitButton.addActionListener(this);
		
		// BorderLayout.CENTER
		panel.add(userLabel);
		panel.add(bookManageButton);
		panel.add(readerManageButton);
		panel.add(borrowManageButton);
		panel.add(exitButton);
		
		bookManageButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.out.println("Exit!");
			System.exit(0);
		} else if (event.getSource() == bookManageButton) {
			card.show(this, "bookPanel");
		}
	}
	
	public CardLayout getCard() {
		return this.card;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setSize(1000, 1000);
		JPanel menu = new MainMenu();
//		menu.setLayout(null);
//		frame.add(menu);
		frame.add(menu);
		frame.setVisible(true);
	}

}
