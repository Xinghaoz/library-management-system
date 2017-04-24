package yanghongquan.library_management_system;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        
        panel.add("loginPanel", loginPanel);
        panel.add("mainMenu", mainMenu);        
        frame.add(panel);
        
        JLabel userLabel = new JLabel("用户名:");
        loginPanel.add(userLabel);

        userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("　密码:");
        passwordText = new JPasswordField(20);
        loginButton = new JButton("登录");
        
        loginButton.addActionListener(this);
        JPanel panel1 = new JPanel();
        panel1.add(userLabel);
        panel1.add(userText);
        loginPanel.add(panel1);
        panel1.setMaximumSize(panel1.getPreferredSize());
        
        JPanel panel2 = new JPanel();
        panel2.add(passwordLabel);
        panel2.add(passwordText);
        loginPanel.add(panel2);
        panel2.setMaximumSize(panel2.getPreferredSize());
        
        loginPanel.add(loginButton);
        
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
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
//			card.show(panel, "mainMenu");			
			String username = userText.getText();
			String password = passwordText.getText();
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "用户名或密码不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {
				if (Auth.login(username, password)) {			
					JOptionPane.showMessageDialog(null, "登录成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);	
					card.show(panel, "mainMenu");
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);	
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
