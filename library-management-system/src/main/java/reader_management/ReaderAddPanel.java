package reader_management;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ReaderAddPanel extends JPanel implements ActionListener {
	JButton commitButton, backButton;
	JLabel[] labels;
	JLabel title;
	JTextField[] texts;
	JPanel panel;
	ReaderMenu previousLevel;
	CardLayout card;

	public ReaderAddPanel(ReaderMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		card = new CardLayout(); 
		panel = new JPanel();
		
		setLayout(card);		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		add("mainPanel", panel);
		
		title = new JLabel("添加读者:");
        panel.add(title);
        
        String[] strs = {"卡号：", "姓名：", "性别：", "学号："};
        
        labels = new JLabel[4];
        for (int i = 0; i < labels.length; i++) {
        	labels[i] = new JLabel(strs[i]);
        	labels[i].setPreferredSize(new Dimension(50, 25));
        }
        
        texts = new JTextField[4];
        for (int i = 0; i < labels.length; i++) {
        	texts[i] = new JTextField();
        	texts[i].setPreferredSize(new Dimension(150, 25));
        }
        
        for (int i = 0; i < labels.length; i++) {
        	JPanel temp = new JPanel();
        	temp.add(labels[i]);
        	temp.add(texts[i]);
        	panel.add(temp);
        }
		
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
			String id = texts[0].getText();
			String name = texts[1].getText();
			String gender = texts[2].getText();
			String studentNumber = texts[3].getText();

			if (id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "卡号不能为空！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else if (!studentNumber.matches("[0-9]{6}")) {
				JOptionPane.showMessageDialog(null, "学(工)号必须为6位纯数字！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			} else {			
				Reader reader = new Reader(id, name, gender, studentNumber);
				int result = ReaderManager.add(reader);
				if (result == 0) {
					JOptionPane.showMessageDialog(null, "添加成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
					for (JTextField t : texts) {
						
					}
				} else if (result == 1){
					JOptionPane.showMessageDialog(null, "添加失败！——　数据已存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "添加失败！——　输入数据格式错误！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
		} 		
	}
}
