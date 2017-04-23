package yanghongquan.library_management_system;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import auth.Auth;
import book_management.BookMenu;

public class Main implements ActionListener {
	JFrame frame;
	JPanel panel, loginPanel;
	JTextField userText, passwordText;
	JButton loginButton;
//	FlowLayout layout = new FlowLayout(); 
    CardLayout card; 

	MainMenu mainMenu;
	
	public Main() {
        frame = new JFrame("图书管理系统");
        frame.setSize(666, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("图书信息管理系统");

        panel = new JPanel();    
        loginPanel = new JPanel();
        card = new CardLayout();        
        mainMenu = new MainMenu();
        
        panel.setLayout(card);
        loginPanel.setLayout(null);
        
        panel.add("loginPanel", loginPanel);
        panel.add("mainMenu", mainMenu);        
        frame.add(panel);
        
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(10,20,80,25);
        loginPanel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        loginPanel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        loginPanel.add(passwordText);

        loginButton = new JButton("登录");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        
        frame.setVisible(true);
	}
	
	public void enterMainMenu() {
		panel.removeAll();

		panel.add(mainMenu);
		
		panel.validate();
		frame.repaint();
	}
	
	public void actionPerformed(ActionEvent event) {
		// 用户登录
		if (event.getSource() == loginButton) {
			card.show(panel, "mainMenu");
			/*
			String username = userText.getText();
			String password = passwordText.getText();
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "用户名或密码不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {
				if (Auth.login(username, password)) {			
//					JOptionPane.showMessageDialog(null, "登录成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);	
//					card.show(panel, "mainMenu");
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);	
				}
			}*/
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
