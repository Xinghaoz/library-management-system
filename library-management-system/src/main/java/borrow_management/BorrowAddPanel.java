package borrow_management;

import java.awt.CardLayout;
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


public class BorrowAddPanel extends JPanel implements ActionListener, ItemListener {
	JButton commitButton, backButton;
	JLabel[] labels;
	JLabel title;
	JTextField[] texts;
	JPanel panel;
	BorrowMenu previousLevel;
	CardLayout card;
	JCheckBox checkBox;

	public BorrowAddPanel(BorrowMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
//		title = new JLabel("借书:");
//        panel.add(title);
        
        String[] strs = {"书号：", "读者卡号：", "借书日期："};
        
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
        	labels[i] = new JLabel(strs[i]);
        }
        
        texts = new JTextField[5];
        for (int i = 0; i < texts.length; i++) {
        	texts[i] = new JTextField();
        	if (i < 2) {
        		texts[i].setPreferredSize(new Dimension(150, 25));
        	} else if (i == 2) {
        		texts[i].setPreferredSize(new Dimension(66, 25));
        	} else {
        		texts[i].setPreferredSize(new Dimension(36, 25));
        	}
        }
        
        for (int i = 0; i < labels.length; i++) {
        	JPanel temp = new JPanel();
        	if (i == 2) {
        		checkBox = new JCheckBox("此刻借书", true);
        		checkBox.addItemListener(this);
        		panel.add(checkBox);
        		temp.add(labels[i]);
            	temp.add(texts[i]);
            	temp.add(new JLabel("-"));
            	temp.add(texts[i + 1]);
            	temp.add(new JLabel("-"));
            	temp.add(texts[i + 2]);
        	} else {
        		temp.add(labels[i]);
            	temp.add(texts[i]);
        	}
        	panel.add(temp);
        }
        
        setState(true);
		
        commitButton = new JButton("确认");	
        backButton = new JButton("返回");
        		
        commitButton.addActionListener(this);
        backButton.addActionListener(this);
		
		panel.add(commitButton);
		panel.add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");//			card.show(this, "previousLevel");
		} else if (event.getSource() == commitButton) {
			String bookId = texts[0].getText();
			String readerId = texts[1].getText();
			
			if (bookId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "书号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else if (readerId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "卡号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {			
				int result = 4;
				if (checkBox.isSelected()) {
					result = BorrowManager.add(bookId, readerId);
				} else {
					String year = texts[2].getText();
					if (!year.matches("[0-9]{4}")) {
						JOptionPane.showMessageDialog(null, "年份必须为4位纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					String month = texts[3].getText();
					if (!month.matches("[0-9]{2}")) {
						int m = Integer.valueOf(month);
						if (m <= 0 || m > 12) {
							JOptionPane.showMessageDialog(null, "月份必须为1～12的纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
					String day = texts[4].getText();
					if (!day.matches("[0-9]{2}")) {
						int d = Integer.valueOf(day);
						if (d <= 0 || d > 31) {
							JOptionPane.showMessageDialog(null, "日份必须为1～31的纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
					String date = year + "-" + month + "-" + day;
					result = BorrowManager.add(bookId, readerId, date);
				}
				if (result == 0) {
					JOptionPane.showMessageDialog(null, "借书成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 1){
					JOptionPane.showMessageDialog(null, "借书失败！——　该书号不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "借书失败！——　该读者不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 3) {
					JOptionPane.showMessageDialog(null, "借书失败！——　不能重复借书，该读书已借过这本书！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 4) {
					JOptionPane.showMessageDialog(null, "借书失败！——　输入数据格式错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == -1) {
					JOptionPane.showMessageDialog(null, "借书失败！——　该读者已借3本书，请先归还图书！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == -2) {
					JOptionPane.showMessageDialog(null, "借书失败！——　该读者有一本借书超过15天未还，请先归还那本书！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
			

		} 		
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == event.SELECTED) {
			setState(true);
	        panel.validate();
		} else if (event.getStateChange() == event.DESELECTED) {
			setState(false);
	        panel.validate();
		}
	}
	
	private void setState(boolean state) {
		for (int i = 2; i < 5; i++) {
			texts[i].setEnabled(!state);
			if (state) {
				texts[i].setBackground(Color.gray);
			} else {
				texts[i].setBackground(Color.white);
			}
		}
	}
}
