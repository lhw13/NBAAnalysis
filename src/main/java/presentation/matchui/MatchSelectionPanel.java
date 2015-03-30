package presentation.matchui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MatchSelectionPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	private JComboBox comboBox_1;
	private JScrollPane scrollPane_1;
	private JTable table;
	
	public MatchSelectionPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(227, 60, 82, 27);
		comboBox.addItem("选择年份");
		comboBox.addItem("2013");
		comboBox.addItem("2014");
		panelOfBottom.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"选择月份", "1", "2", "3", "4", "10", "11", "12"}));
		comboBox_1.setBounds(334, 63, 82, 24);
		panelOfBottom.add(comboBox_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 139, 711, 415);
		panelOfBottom.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table);
	}
}
