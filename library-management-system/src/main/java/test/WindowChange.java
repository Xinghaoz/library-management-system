package test;

import java.awt.*;  
import javax.swing.*;  
public class WindowChange extends JPanel  
{  
    JTextField text;  
	JButton loginButton, bookManageButton, readerManageButton, borrowManageButton, exitButton;

    WindowChange(){  
        text = new JTextField(10);  
		bookManageButton = new JButton("图书管理");
		readerManageButton = new JButton("读者管理");
		borrowManageButton = new JButton("借还管理");
		exitButton = new JButton("退出系统");
        text.setText("切换后的界面");  
        text.setEditable(false);  
        add(text);  
        add(bookManageButton);
    }  
}  