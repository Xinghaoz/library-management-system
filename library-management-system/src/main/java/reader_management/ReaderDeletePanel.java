package reader_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book_management.BookManager;
import book_management.BookMenu;
import book_management.BookTable;

public class ReaderDeletePanel extends JPanel implements ActionListener {
	JButton backButton, commitButton;
	ReaderMenu previousLevel;
	ReaderTable table;
	JTextField idText;
	
	public ReaderDeletePanel(ReaderMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		JLabel idLabel = new JLabel("请输入需要删除的读者的卡号");
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
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == commitButton) {
			String id = idText.getText();
			int n = JOptionPane.showConfirmDialog(null, "确认删除卡号为" + id + "的读者吗?", "系统信息",JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				if (ReaderManager.delete(id)) {
					JOptionPane.showMessageDialog(null, "删除成功！", "系统信息", JOptionPane.PLAIN_MESSAGE);
					idText.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "此读者不存在！", "系统信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}		
	}
}
