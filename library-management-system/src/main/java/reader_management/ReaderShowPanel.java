package reader_management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ReaderShowPanel extends JPanel implements ActionListener {
	JButton backButton;
	ReaderMenu previousLevel;
	ReaderTable table;
	
	public ReaderShowPanel(ReaderMenu previousLevel) {
		this.previousLevel = previousLevel;
				
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		table = new ReaderTable(ReaderManager.getAll());
        backButton = new JButton("返回");
        backButton.addActionListener(this);
		
		add(backButton);
		add(table);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} 
	}
}
