package yanghongquan.library_management_system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import book_management.BookMenu;
import borrow_management.BorrowMenu;
import reader_management.ReaderMenu;

public class MainMenu extends JPanel implements ActionListener {
	JButton bookManageButton, readerManageButton, borrowManageButton, exitButton;
	JLabel userLabel;
	JPanel panel;
	CardLayout card;
	BookMenu bookMenu;
	ReaderMenu readerMenu;
	BorrowMenu borrowMenu;
	
	public MainMenu() {
		card = new CardLayout(); 
		panel = new JPanel();
		bookMenu = new BookMenu(this);
		readerMenu = new ReaderMenu(this);
		borrowMenu = new BorrowMenu(this);
		
		setLayout(card);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainMenu", panel);
		add("bookMenu", bookMenu);
		add("readerMenu", readerMenu);
		add("borrowMenu", borrowMenu);
		
        userLabel = new JLabel("主菜单:");       
        bookManageButton = new JButton("图书管理");
		readerManageButton = new JButton("读者管理");
		borrowManageButton = new JButton("借还管理");
		exitButton = new JButton("退出系统");
		
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bookManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		readerManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		borrowManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		exitButton.addActionListener(this);
		bookManageButton.addActionListener(this);
		readerManageButton.addActionListener(this);
		borrowManageButton.addActionListener(this);
		
		panel.add(userLabel);
		panel.add(bookManageButton);
		panel.add(readerManageButton);
		panel.add(borrowManageButton);
		panel.add(exitButton);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.out.println("Exit!");
			System.exit(0);
		} else if (event.getSource() == bookManageButton) {
			card.show(this, "bookMenu");
		} else if (event.getSource() == readerManageButton) {
			card.show(this, "readerMenu");
		} else if (event.getSource() == borrowManageButton) {
			card.show(this, "borrowMenu");
		}
	}
	
	public CardLayout getCard() {
		return this.card;
	}

	public static void main(String[] args) {
		Main main = new Main();
		
//		JFrame frame = new JFrame("Test");
//		frame.setSize(1000, 1000);
//		JPanel menu = new MainMenu();
////		menu.setLayout(null);
////		frame.add(menu);
//		frame.add(menu);
//		frame.setVisible(true);
	}

}
