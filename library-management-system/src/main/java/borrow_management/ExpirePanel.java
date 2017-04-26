package borrow_management;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ExpirePanel extends JPanel implements ActionListener {
	JButton backButton;
	BorrowMenu previousLevel;
	ExpireTable table;
	
	public ExpirePanel(BorrowMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		table = new ExpireTable(BorrowManager.getExpireReaders());
        backButton = new JButton("返回");
        backButton.addActionListener(this);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(backButton);
		add(table);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} 		
	}
}
