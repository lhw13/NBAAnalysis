package presentation.teamsui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import presentation.mainui.MainFrame;
import server.businesslogic.BLController;

import javax.swing.JLabel;
/*
 * 单个球队比赛信息面板
 */
public class TeamsInfoFrame {
 
	public static JTable table;
	public static JTable table_1;
	public static JTable table_2;
	public static JTable table_3;
	
	public static JLabel teamPicture;
	
	public static JScrollPane scrollPane;
	
	BLController controller;
	
	/**
	 * Create the application.
	 */
	public TeamsInfoFrame() {//构造函数
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 100, 50);
		
		JButton btnNewButton = new JButton("返回");
		panel.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 250, 1400, 300);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_1, null);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_2, null);
		table_2 = new JTable();
		
		
		scrollPane_2.setViewportView(table_2);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(60, 600, 1300, 300);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane_1.addTab("总计", null, scrollPane_3, null);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(350, 150, 800, 60);
		
		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 20));
		scrollPane_5.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(60, 60, 250, 150);
		panel_2.setLayout(null);
		 
		teamPicture = new JLabel("");
		teamPicture.setBounds(0, 0, 250, 150);
		panel_2.add(teamPicture);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1500,1000));
		panel_1.setLayout(null);
		panel_1.add(scrollPane_5);
		panel_1.add(panel);
		panel_1.add(panel_2);
		panel_1.add(tabbedPane);
		panel_1.add(tabbedPane_1);
		
		scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(0, 0, 990, 560);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					TeamsInfoFrame.scrollPane.setVisible(false);
					TeamsSelectionFrame.scrollPane.setVisible(true);
					TeamsSelectionFrame.flag=true;
					MainFrame.frame.setTitle("NBA球队选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
	}
}
