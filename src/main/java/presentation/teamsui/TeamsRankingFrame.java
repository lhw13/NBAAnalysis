package presentation.teamsui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 13, 1169, 332);
		panel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 377, 930, 269);
		panel.add(scrollPane_2);
		
		
		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(36, 687, 113, 27);
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
