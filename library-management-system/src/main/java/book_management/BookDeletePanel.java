package book_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookDeletePanel extends JPanel implements ActionListener {
	JButton backButton, commitButton;
	BookMenu previousLevel;
	BookTable table;
	JTextField idText;
	
	public BookDeletePanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
				
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
//        title = new JLabel("图书管理:");
//        panel.add(title);
		JLabel idLabel = new JLabel("请输入需要删除的书号");
		idText = new JTextField();
		idText.setPreferredSize(new Dimension(100, 25));
        backButton = new JButton("返回");
        commitButton = new JButton("删除");
		
        backButton.addActionListener(this);
        commitButton.addActionListener(this);
        
        add(idLabel);
        add(idText);
		add(commitButton);
		add(backButton);
//		add(table);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == commitButton) {
			String id = idText.getText();
//			System.out.println("id = " + id);
			if (BookManager.delete(id)) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				idText.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "此书号不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
