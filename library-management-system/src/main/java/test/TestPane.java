package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import book_management.BookManager;
import book_management.BookTable;
import yanghongquan.library_management_system.MainMenu;

public class TestPane implements ActionListener {
	JFrame frame;
	JPanel panel;
	MainMenu mainMenu;
	JButton button ;
	
	public TestPane() {
		frame = new JFrame("图书管理系统");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("图书信息管理系统");

        panel = new JPanel();    
        
        button = new JButton("Test");
        button.addActionListener(this);
        
        panel.add(button);
        frame.add(panel);
        
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		TestPane t = new TestPane();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			BookTable bookTable = new BookTable(BookManager.getAll());
			panel.add(bookTable);
			panel.validate();
			frame.repaint();
		}
	}

}
