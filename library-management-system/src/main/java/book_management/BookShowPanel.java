package book_management;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookShowPanel extends JPanel implements ActionListener {
	JButton backButton;
	BookMenu previousLevel;
	BookTable table;
	
	public BookShowPanel(BookMenu previousLevel) {
		this.previousLevel = previousLevel;
				
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		table = new BookTable(BookManager.getAll());
        backButton = new JButton("返回");
        backButton.addActionListener(this);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(backButton);
		add(table);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} 
	}
}
