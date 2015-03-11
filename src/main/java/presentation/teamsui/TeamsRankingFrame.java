package presentation.teamsui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import presentation.mainui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamsRankingFrame {

	private JTable table;
	private JTable table_1;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;

	/**
	 * Create the application.
	 */
	public TeamsRankingFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1500,1000));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 13, 1169, 332);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"球队", "场数", "投篮命中数", "出手数", "三分命中数", "出手数", "罚球命中数", "出手数", 
					"进攻篮板", "防守篮板", "总篮板数","助攻", "抢断", "盖帽", "失误", "犯规", "得分"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(85);
		table.getColumnModel().getColumn(7).setPreferredWidth(55);
		table.getColumnModel().getColumn(8).setPreferredWidth(70);
		table.getColumnModel().getColumn(9).setPreferredWidth(70);
		table.getColumnModel().getColumn(10).setPreferredWidth(70);
		table.getColumnModel().getColumn(11).setPreferredWidth(55);
		table.getColumnModel().getColumn(12).setPreferredWidth(55);
		table.getColumnModel().getColumn(13).setPreferredWidth(50);
		table.getColumnModel().getColumn(14).setPreferredWidth(50);
		table.getColumnModel().getColumn(15).setPreferredWidth(50);
		table.getColumnModel().getColumn(16).setPreferredWidth(50);
		
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 377, 930, 269);
		panel.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"球队", "投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "篮板效率", "抢断效率", "助攻率"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane_2.setViewportView(table_1);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(36, 687, 113, 27);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				TeamsRankingFrame.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
			}
		});
	}
}
