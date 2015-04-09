package presentation.matchui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsSelectionFrame;

public class MatchDetailInfoPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	private JTable table;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable table_1;
	private JTable table_2;
	private JButton btnNewButton;
	
	public MatchDetailInfoPanel(DefaultTableModel model1, DefaultTableModel model2, DefaultTableModel model3) {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 1000));
		panelOfBottom.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 300, 800, 300);
		panelOfBottom.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(model1);
		scrollPane_1.setViewportView(table);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 650, 800, 300);
		panelOfBottom.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(model2);
		scrollPane_2.setViewportView(table_1);
		
		table_2 = new JTable();
		table_2.setModel(model3);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(200, 100, 600, 100);
		scrollPane_3.setViewportView(table_2);
		panelOfBottom.add(scrollPane_3);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(450, 250, 120, 25);
		panelOfBottom.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MatchDetailInfoPanel.scrollPane.setVisible(false);
					MatchDetailInfoPanel.scrollPane=null;
					MatchSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA比赛查询");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
}