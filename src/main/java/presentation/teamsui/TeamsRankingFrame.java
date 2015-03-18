package presentation.teamsui;
 
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import presentation.mainui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * 所有球队排名面板
 */
public class TeamsRankingFrame {
 
	public static JTable table;
	public static JTable table_1;
	public static JTable table_2;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;

	/**
	 * Create the application.
	 */
	public TeamsRankingFrame() {//构造函数
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
		
		table = new JTable();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		
		table_1 = new JTable();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportView(table_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(50, 377, 930, 269);
		panel.add(scrollPane_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 20, 1200, 350);
		tabbedPane.addTab("场均", null, scrollPane_2, null);
		tabbedPane.addTab("总计", null, scrollPane_1, null);
		
		panel.add(tabbedPane);
		
		table_2 = new JTable();
		scrollPane_3.setViewportView(table_2);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(200, 10, 113, 27);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				TeamsRankingFrame.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
		});
	}
}
