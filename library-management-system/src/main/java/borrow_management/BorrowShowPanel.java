package borrow_management;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BorrowShowPanel extends JPanel implements ActionListener {
	JButton backButton;
	BorrowMenu previousLevel;
	BorrowTable table;
	
	public BorrowShowPanel(BorrowMenu previousLevel) {
		this.previousLevel = previousLevel;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		table = new BorrowTable(BorrowManager.getAll());
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
