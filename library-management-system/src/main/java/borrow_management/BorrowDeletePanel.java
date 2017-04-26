package borrow_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import borrow_management.BorrowManager;
import borrow_management.BorrowMenu;
import borrow_management.BorrowTable;

public class BorrowDeletePanel extends JPanel implements ActionListener, ItemListener {
	JButton backButton, commitButton;
	BorrowMenu previousLevel;
	BorrowTable table;
	JTextField bookIdText, readerIdText;
	JTextField[] texts;
	JCheckBox checkBox;
	
	public BorrowDeletePanel(BorrowMenu previousLevel) {
		this.previousLevel = previousLevel;
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel idLabel1 = new JLabel("请输入还书的书号：");
		bookIdText = new JTextField();
		bookIdText.setPreferredSize(new Dimension(100, 25));
		
		JLabel idLabel2 = new JLabel("请输入还书读者的卡号：");
		readerIdText = new JTextField();
		readerIdText.setPreferredSize(new Dimension(100, 25));
		
        commitButton = new JButton("还书");
        backButton = new JButton("返回");
		
        commitButton.addActionListener(this);
        backButton.addActionListener(this);
        
        JPanel panel1 = new JPanel();
        panel1.add(idLabel1);
        panel1.add(bookIdText);
        
        JPanel panel2 = new JPanel();
        panel2.add(idLabel2);
        panel2.add(readerIdText);
        
        checkBox = new JCheckBox("此刻还书", true);
		checkBox.addItemListener(this);
		
		JPanel panel3 = new JPanel();
		texts = new JTextField[3];
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField();
			if (i == 0) {
				panel3.add(new JLabel("还书日期："));
				texts[i].setPreferredSize(new Dimension(66, 25));
			} else {
				panel3.add(new JLabel("-"));
				texts[i].setPreferredSize(new Dimension(36, 25));
			}
			panel3.add(texts[i]);
		}
		
        setState(true);
		add(panel1);
        add(panel2);
        add(checkBox);
        add(panel3);
		add(commitButton);
		add(backButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == commitButton) {
			String bookId = bookIdText.getText();
			String readerId = readerIdText.getText();
			if (bookId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "书号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else if (readerId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "卡号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {			
				int result = 4;
				if (checkBox.isSelected()) {
					result = BorrowManager.returnBook(bookId, readerId);
				} else {
					String year = texts[0].getText();
					if (!year.matches("[0-9]{4}")) {
						JOptionPane.showMessageDialog(null, "年份必须为4位纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					String month = texts[1].getText();
					if (!month.matches("[0-9]{2}")) {
						int m = Integer.valueOf(month);
						if (m <= 0 || m > 12) {
							JOptionPane.showMessageDialog(null, "月份必须为1～12的纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
					String day = texts[2].getText();
					if (!day.matches("[0-9]{2}")) {
						int d = Integer.valueOf(day);
						if (d <= 0 || d > 31) {
							JOptionPane.showMessageDialog(null, "日份必须为1～31的纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
					String date = year + "-" + month + "-" + day;
					int n = JOptionPane.showConfirmDialog(null, "书号为[" + bookId + "]，读者卡号为[" + readerId + "]，确认还书？", "系统信息",JOptionPane.YES_NO_OPTION);
						if (n == 0) {
						result = BorrowManager.returnBook(bookId, readerId);
					}
				}
				if (result == 0) {
					JOptionPane.showMessageDialog(null, "还书成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 1){
					JOptionPane.showMessageDialog(null, "还书失败！——　该书号不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "还书失败！——　该读者不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 3) {
					JOptionPane.showMessageDialog(null, "还书失败！——　该读书没有借过这本书！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 4) {
					JOptionPane.showMessageDialog(null, "还书失败！——　输入数据格式错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} 
			}
		}		
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == event.SELECTED) {
			setState(true);
	        validate();
		} else if (event.getStateChange() == event.DESELECTED) {
			setState(false);
	        validate();
		}
	}
	
	private void setState(boolean state) {
		for (int i = 0; i < texts.length; i++) {
			texts[i].setEnabled(!state);
			if (state) {
				texts[i].setBackground(Color.gray);
			} else {
				texts[i].setBackground(Color.white);
			}
		}
	}
}
