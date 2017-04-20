package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import auth.Auth;
import book_management.BookManager;
import yanghongquan.library_management_system.BookTable;
import yanghongquan.library_management_system.MainMenu;

public class Test1 extends JFrame implements ActionListener {
//	JFrame frame;
	JPanel panel;
	MainMenu mainMenu;
	JButton loginButton;
    WindowChange change;  

	public Test1() {
//        frame = new JFrame("图书管理系统");
//        frame.setSize(1000, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("图书信息管理系统");
		
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("图书信息管理系统");

        panel = new JPanel();    
        mainMenu = new MainMenu();
        mainMenu.setLayout(null);
//        frame.add(panel, BorderLayout.CENTER);
        add(panel);
        
        panel.setLayout(null);
        
        loginButton = new JButton("登录");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(this);
        
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		// 用户登录
		if (event.getSource() == loginButton) {
			
		}
	}
	
	public static void main(String[] args) {
		Test1 test = new Test1();
		
		JFrame frame = new JFrame("图书管理系统");
        frame.setSize(666, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("图书信息管理系统");
        
        BookTable panel = new BookTable(BookManager.getAll());
        frame.add(panel);
        frame.setVisible(true);
	}
}
