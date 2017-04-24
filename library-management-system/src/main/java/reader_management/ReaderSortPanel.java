package reader_management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import reader_management.Reader;
import reader_management.ReaderManager;
import reader_management.ReaderMenu;
import reader_management.ReaderTable;

public class ReaderSortPanel extends JPanel implements ActionListener {
	JButton backButton, sortButton, commitButton;
	JPanel tablePanel;
	JComboBox comboBox;
	ReaderMenu previousLevel;
	String sortField = "id";
	ReaderTable table;

	public ReaderSortPanel(ReaderMenu previousLevel) {
		this.previousLevel = previousLevel;
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
//		table = new ReaderTable(ReaderManager.getAll());
//		JLabel label = new JLabel("请输入需要修改的书号：");
		String[] options = {"卡号", "姓名", "性别", "学(工)号"};
		 
        backButton = new JButton("返回");
        sortButton = new JButton("排序");
        tablePanel = new JPanel();
        comboBox = new JComboBox(options);
        
        comboBox.setBorder(BorderFactory.createTitledBorder("请选择需要排序的字段")); 
        comboBox.setPreferredSize(new Dimension(166, 66));
        
        backButton.addActionListener(this);
        sortButton.addActionListener(this);
        comboBox.addActionListener(this);
        
        add(comboBox);
        add(sortButton);        
		add(backButton);
		add(tablePanel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			previousLevel.getCard().show(previousLevel, "mainPanel");
		} else if (event.getSource() == sortButton) {
			List<Reader> readers = new ArrayList<>();
			boolean valid = false;
			readers = ReaderManager.sortBy(sortField);
			table = new ReaderTable(readers);
			
			tablePanel.removeAll();
			tablePanel.add(table);
			validate();
			previousLevel.repaint();
		} else if (event.getSource() == comboBox) {
			int index = comboBox.getSelectedIndex();
			switch (index) {
				case 0: sortField = "id"; break;
				case 1: sortField = "name"; break;
				case 2: sortField = "gender"; break;
				case 3: sortField = "student_number"; break;			
			}
		}	
	}
}
