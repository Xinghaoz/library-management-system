package yanghongquan.library_management_system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import book_management.BookMenu;
import reader_management.ReaderMenu;

public class MainMenu extends JPanel implements ActionListener {
	JButton bookManageButton, readerManageButton, borrowManageButton, exitButton;
	JLabel userLabel;
	JPanel panel;
	CardLayout card;
	BookMenu bookMenu;
	ReaderMenu readerMenu;
	
	public MainMenu() {
		card = new CardLayout(); 
		panel = new JPanel();
		bookMenu = new BookMenu(this);
		readerMenu = new ReaderMenu(this);
		
		setLayout(card);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainMenu", panel);
		add("bookMenu", bookMenu);
		add("readerMenu", readerMenu);
		
        userLabel = new JLabel("主菜单:");        
        bookManageButton = new JButton("图书管理");
		readerManageButton = new JButton("读者管理");
		borrowManageButton = new JButton("借还管理");
		exitButton = new JButton("退出系统");
		
		exitButton.addActionListener(this);
		bookManageButton.addActionListener(this);
		readerManageButton.addActionListener(this);
		
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
