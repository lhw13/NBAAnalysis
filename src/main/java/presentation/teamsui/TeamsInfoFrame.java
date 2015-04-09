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
import javax.swing.JComboBox;

/*
 * 单个球队比赛信息面板
 */
public class TeamsInfoFrame {

	public static JTable table;
	public static JTable table_1;

	public static JLabel teamPicture;

	public static JScrollPane scrollPane;
	
	private JComboBox<String> comboBox;

	BLController controller;
	
	public TeamsInfoFrame(String teamName ,ImageIcon ii, DefaultTableModel model, DefaultTableModel model1) {// 构造函数
		initialize(teamName, ii, model, model1);
	}

	private void initialize(final String teamName, ImageIcon ii, DefaultTableModel model, DefaultTableModel model1) {

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 100, 50);

		JButton btnNewButton = new JButton("返回");
		panel.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 250, 800, 500);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("球队信息", null, scrollPane_1, null);
		
		table_1 = new JTable();
		table_1.setModel(model1);
		table_1.setRowSorter(new TableRowSorter<TableModel>(model1));
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(60, 150, 600, 60);

		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 20));
		scrollPane_5.setViewportView(table);
		table.setModel(model);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(700, 60, 250, 150);
		panel_2.setLayout(null);

		teamPicture = new JLabel("");
		teamPicture.setIcon(ii);
		teamPicture.setBounds(0, 0, 250, 150);
		panel_2.add(teamPicture);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1100, 1000));
		panel_1.setLayout(null);
		panel_1.add(scrollPane_5);
		panel_1.add(panel);
		panel_1.add(panel_2);
		panel_1.add(tabbedPane);

		scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(0, 100, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("投篮命中数");
		comboBox.addItem("投篮出手数");
		comboBox.addItem("三分命中数");
		comboBox.addItem("三分出手数");
		comboBox.addItem("罚球命中数");
		comboBox.addItem("罚球出手数");
		comboBox.addItem("进攻篮板");
		comboBox.addItem("防守篮板");
		comboBox.addItem("总篮板");
		comboBox.addItem("助攻");
		comboBox.addItem("抢断");
		comboBox.addItem("盖帽");
		comboBox.addItem("失误");
		comboBox.addItem("犯规");
		comboBox.addItem("得分");
		comboBox.addItem("投篮命中率");
		comboBox.addItem("三分命中率");
		comboBox.addItem("罚球命中率");
		comboBox.addItem("胜率");
		comboBox.addItem("进攻回合");
		comboBox.addItem("进攻效率");
		comboBox.addItem("防守效率");
		comboBox.addItem("进攻篮板效率");
		comboBox.addItem("防守篮板效率");
		comboBox.addItem("抢断效率");
		comboBox.addItem("助攻率");
		comboBox.setSelectedItem(TeamsSelectionFrame.selection);
		comboBox.setBounds(700, 230, 150, 30);
		panel_1.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					TeamsSelectionFrame.selection="投篮命中数";
					TeamsSelectionFrame.table_1_columns[3]="投篮命中数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="投篮命中数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 1: 
					TeamsSelectionFrame.selection="投篮出手数";
					TeamsSelectionFrame.table_1_columns[3]="投篮出手数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="投篮出手数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 2: 
					TeamsSelectionFrame.selection="三分命中数";
					TeamsSelectionFrame.table_1_columns[3]="三分命中数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="三分命中数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 3: 
					TeamsSelectionFrame.selection="三分出手数";
					TeamsSelectionFrame.table_1_columns[3]="三分出手数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="三分出手数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 4: 
					TeamsSelectionFrame.selection="罚球命中数";
					TeamsSelectionFrame.table_1_columns[3]="罚球命中数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="罚球命中数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 5: 
					TeamsSelectionFrame.selection="罚球出手数";
					TeamsSelectionFrame.table_1_columns[3]="罚球出手数(场均)";
					TeamsSelectionFrame.table_1_columns[4]="罚球出手数(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 6: 
					TeamsSelectionFrame.selection="进攻篮板";
					TeamsSelectionFrame.table_1_columns[3]="进攻篮板(场均)";
					TeamsSelectionFrame.table_1_columns[4]="进攻篮板(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 7: 
					TeamsSelectionFrame.selection="防守篮板";
					TeamsSelectionFrame.table_1_columns[3]="防守篮板(场均)";
					TeamsSelectionFrame.table_1_columns[4]="防守篮板(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 8: 
					TeamsSelectionFrame.selection="总篮板";
					TeamsSelectionFrame.table_1_columns[3]="总篮板(场均)";
					TeamsSelectionFrame.table_1_columns[4]="总篮板(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 9: 
					TeamsSelectionFrame.selection="助攻";
					TeamsSelectionFrame.table_1_columns[3]="助攻(场均)";
					TeamsSelectionFrame.table_1_columns[4]="助攻(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 10: 
					TeamsSelectionFrame.selection="抢断";
					TeamsSelectionFrame.table_1_columns[3]="抢断(场均)";
					TeamsSelectionFrame.table_1_columns[4]="抢断(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 11: 
					TeamsSelectionFrame.selection="盖帽";
					TeamsSelectionFrame.table_1_columns[3]="盖帽(场均)";
					TeamsSelectionFrame.table_1_columns[4]="盖帽(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 12: 
					TeamsSelectionFrame.selection="失误";
					TeamsSelectionFrame.table_1_columns[3]="失误(场均)";
					TeamsSelectionFrame.table_1_columns[4]="失误(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 13: 
					TeamsSelectionFrame.selection="犯规";
					TeamsSelectionFrame.table_1_columns[3]="犯规(场均)";
					TeamsSelectionFrame.table_1_columns[4]="犯规(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 14: 
					TeamsSelectionFrame.selection="得分";
					TeamsSelectionFrame.table_1_columns[3]="得分(场均)";
					TeamsSelectionFrame.table_1_columns[4]="得分(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 15: 
					TeamsSelectionFrame.selection="投篮命中率";
					TeamsSelectionFrame.table_1_columns[3]="投篮命中率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="投篮命中率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 16: 
					TeamsSelectionFrame.selection="三分命中率";
					TeamsSelectionFrame.table_1_columns[3]="三分命中率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="三分命中率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 17: 
					TeamsSelectionFrame.selection="罚球命中率";
					TeamsSelectionFrame.table_1_columns[3]="罚球命中率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="罚球命中率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 18: 
					TeamsSelectionFrame.selection="胜率";
					TeamsSelectionFrame.table_1_columns[3]="胜率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="胜率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 19: 
					TeamsSelectionFrame.selection="进攻回合";
					TeamsSelectionFrame.table_1_columns[3]="进攻回合(场均)";
					TeamsSelectionFrame.table_1_columns[4]="进攻回合(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 20: 
					TeamsSelectionFrame.selection="进攻效率";
					TeamsSelectionFrame.table_1_columns[3]="进攻效率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="进攻效率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 21: 
					TeamsSelectionFrame.selection="防守效率";
					TeamsSelectionFrame.table_1_columns[3]="防守效率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="防守效率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 22: 
					TeamsSelectionFrame.selection="进攻篮板效率";
					TeamsSelectionFrame.table_1_columns[3]="进攻篮板效率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="进攻篮板效率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 23: 
					TeamsSelectionFrame.selection="防守篮板效率";
					TeamsSelectionFrame.table_1_columns[3]="防守篮板效率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="防守篮板效率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 24: 
					TeamsSelectionFrame.selection="抢断效率";
					TeamsSelectionFrame.table_1_columns[3]="抢断效率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="抢断效率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				case 25: 
					TeamsSelectionFrame.selection="助攻率";
					TeamsSelectionFrame.table_1_columns[3]="助攻率(场均)";
					TeamsSelectionFrame.table_1_columns[4]="助攻率(总计)";
					TeamsSelectionFrame.setTeamsInfo(teamName);
					break;
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					TeamsInfoFrame.scrollPane.setVisible(false);
					TeamsSelectionFrame.scrollPane.setVisible(true);
					TeamsSelectionFrame.flag = true;
					MainFrame.frame.setTitle("NBA球队选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});

	}
}
