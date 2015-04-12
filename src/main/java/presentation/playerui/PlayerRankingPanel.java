package presentation.playerui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.RowFilter;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsRankingFrame;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class PlayerRankingPanel extends JPanel {
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton refreshButton;
	private JComboBox<String> comboBox;
	public static JTable table;

	public static JCheckBox chckbxNewCheckBox;
	public static JCheckBox chckbxNewCheckBox_1;
	public static JCheckBox chckbxNewCheckBox_2;
	public static JCheckBox chckbxNewCheckBox_3;
	public static JCheckBox chckbxNewCheckBox_4;
	public static JCheckBox chckbxNewCheckBox_5;
	public static JCheckBox chckbxNewCheckBox_6;
	public static JCheckBox chckbxNewCheckBox_7;
	public static JCheckBox chckbxNewCheckBox_8;
	public static JCheckBox chckbxNewCheckBox_9;
	public static JCheckBox chckbxNewCheckBox_10;
	

	public PlayerRankingPanel(DefaultTableModel model) {
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 28, 100, 40);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlayerRankingPanel.scrollPane.setVisible(false);
				MainFrame.frame.getContentPane().remove(PlayerRankingPanel.scrollPane);
				PlayerRankingPanel.scrollPane=null;
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}

		});
		
		refreshButton = new JButton("最新");
		refreshButton.setBounds(50, 130, 100, 30);
		panel.add(refreshButton);

		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MainFrame.setPlayersRanking();
			}

		});

		String table_0_columns[] = {"行号"};
		Object table_0_rows[][]=
			{
				{"1"}, {"2"}, {"3"}, {"4"}, {"5"},
				{"6"}, {"7"}, {"8"}, {"9"}, {"10"},
				{"11"}, {"12"}, {"13"}, {"14"}, {"15"},
				{"16"}, {"17"}, {"18"}, {"19"}, {"20"},
				{"21"}, {"22"}, {"23"}, {"24"}, {"25"},
				{"26"}, {"27"}, {"28"}, {"29"}, {"30"},
				{"31"}, {"32"}, {"33"}, {"34"}, {"35"},
				{"36"}, {"37"}, {"38"}, {"39"}, {"40"},
				{"41"}, {"42"}, {"43"}, {"44"}, {"45"},
				{"46"}, {"47"}, {"48"}, {"49"}, {"50"},
			};
		
		JTable table0 = new JTable();
		DefaultTableModel model0 = new DefaultTableModel(table_0_rows,table_0_columns);
		table0.setModel(model0);
		table0.setPreferredScrollableViewportSize(new Dimension(50, 1000));
		
		table = new JTable();
		table.setModel(model);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		table.setRowSorter(sorter);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 175, 800, 1167);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		scrollPane_1.setRowHeaderView(table0);
		tabbedPane.addTab("球员排名", null, scrollPane_1, null);

		panel.setPreferredSize(new Dimension(1000, 1400));
		panel.add(btnNewButton);
		panel.add(tabbedPane);
		scrollPane.setViewportView(panel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		chckbxNewCheckBox = new JCheckBox("前锋");
		chckbxNewCheckBox.setBounds(200, 30, 150, 30);
		panel.add(chckbxNewCheckBox);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("得分");
		comboBox.addItem("篮板");
		comboBox.addItem("助攻");
		comboBox.addItem("得分/篮板/助攻");
		comboBox.addItem("盖帽");
		comboBox.addItem("抢断");
		comboBox.addItem("犯规");
		comboBox.addItem("失误");
		comboBox.addItem("分钟");
		comboBox.addItem("效率");
		comboBox.addItem("投篮");
		comboBox.addItem("三分");
		comboBox.addItem("罚球");
		comboBox.addItem("两双");
		comboBox.setSelectedItem(MainFrame.selection1);
		comboBox.setBounds(700, 160, 150, 30);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					MainFrame.selection1="得分";
					MainFrame.table_2_columns[4]="得分(场均)";
					MainFrame.table_2_columns[5]="得分(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 1: 
					MainFrame.selection1="篮板";
					MainFrame.table_2_columns[4]="篮板(场均)";
					MainFrame.table_2_columns[5]="篮板(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 2: 
					MainFrame.selection1="助攻";
					MainFrame.table_2_columns[4]="助攻(场均)";
					MainFrame.table_2_columns[5]="助攻(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 3: 
					MainFrame.selection1="得分/篮板/助攻";
					MainFrame.table_2_columns[4]="得分/篮板/助攻(场均)";
					MainFrame.table_2_columns[5]="得分/篮板/助攻(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 4: 
					MainFrame.selection1="盖帽";
					MainFrame.table_2_columns[4]="盖帽(场均)";
					MainFrame.table_2_columns[5]="盖帽(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 5: 
					MainFrame.selection1="抢断";
					MainFrame.table_2_columns[4]="抢断(场均)";
					MainFrame.table_2_columns[5]="抢断(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 6: 
					MainFrame.selection1="犯规";
					MainFrame.table_2_columns[4]="犯规(场均)";
					MainFrame.table_2_columns[5]="犯规(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 7: 
					MainFrame.selection1="失误";
					MainFrame.table_2_columns[4]="失误(场均)";
					MainFrame.table_2_columns[5]="失误(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 8: 
					MainFrame.selection1="分钟";
					MainFrame.table_2_columns[4]="分钟(场均)";
					MainFrame.table_2_columns[5]="分钟(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 9: 
					MainFrame.selection1="效率";
					MainFrame.table_2_columns[4]="效率(场均)";
					MainFrame.table_2_columns[5]="效率(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 10: 
					MainFrame.selection1="投篮";
					MainFrame.table_2_columns[4]="投篮(场均)";
					MainFrame.table_2_columns[5]="投篮(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 11: 
					MainFrame.selection1="三分";
					MainFrame.table_2_columns[4]="三分(场均)";
					MainFrame.table_2_columns[5]="三分(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 12: 
					MainFrame.selection1="罚球";
					MainFrame.table_2_columns[4]="罚球(场均)";
					MainFrame.table_2_columns[5]="罚球(总计)";
					MainFrame.setPlayersRanking();
					break;
				case 13: 
					MainFrame.selection1="两双";
					MainFrame.table_2_columns[4]="两双(场均)";
					MainFrame.table_2_columns[5]="两双(总计)";
					MainFrame.setPlayersRanking();
					break;
				}
			}
			
		});

		chckbxNewCheckBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "前锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "前锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "前锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "前锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "前锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "前锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "前锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "前锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()) {
					String text = "前锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox.isSelected()) {
					chckbxNewCheckBox_1.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_1 = new JCheckBox("中锋");
		chckbxNewCheckBox_1.setBounds(200, 70, 150, 30);
		panel.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "中锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "中锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "中锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "中锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "中锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "中锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "中锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "中锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()) {
					String text = "中锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox_1.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_2 = new JCheckBox("后卫");
		chckbxNewCheckBox_2.setBounds(200, 110, 150, 30);
		panel.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox_2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "后卫";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "后卫";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "后卫";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "后卫";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "后卫";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "后卫";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "后卫";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "后卫";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()) {
					String text = "后卫";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (!chckbxNewCheckBox_2.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_1.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_3 = new JCheckBox("东部");
		chckbxNewCheckBox_3.setBounds(380, 30, 150, 30);
		panel.add(chckbxNewCheckBox_3);

		chckbxNewCheckBox_3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "东部";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "东部";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "东部";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "前锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "中锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "后卫";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_3.isSelected()) {
					String text = "东部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_4 = new JCheckBox("西部");
		chckbxNewCheckBox_4.setBounds(380, 70, 150, 30);
		panel.add(chckbxNewCheckBox_4);

		chckbxNewCheckBox_4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "西部";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "西部";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "西部";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "前锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "中锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "后卫";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_4.isSelected()) {
					String text = "西部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_5 = new JCheckBox("中区");
		chckbxNewCheckBox_5.setBounds(540, 30, 150, 30);
		panel.add(chckbxNewCheckBox_5);

		chckbxNewCheckBox_5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "前锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "中锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "后卫";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_5.isSelected()) {
					String text = "中区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_5.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_5.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_6 = new JCheckBox("大西洋区");
		chckbxNewCheckBox_6.setBounds(540, 70, 150, 30);
		panel.add(chckbxNewCheckBox_6);

		chckbxNewCheckBox_6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "前锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "中锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "后卫";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_6.isSelected()) {
					String text = "大西洋区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_6.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_6.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_7 = new JCheckBox("东南区");
		chckbxNewCheckBox_7.setBounds(540, 110, 150, 30);
		panel.add(chckbxNewCheckBox_7);

		chckbxNewCheckBox_7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "前锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "中锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "后卫";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_7.isSelected()) {
					String text = "东南区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_7.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_7.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_8 = new JCheckBox("西北区");
		chckbxNewCheckBox_8.setBounds(690, 30, 150, 30);
		panel.add(chckbxNewCheckBox_8);

		chckbxNewCheckBox_8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "前锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "中锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "后卫";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_8.isSelected()) {
					String text = "西北区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_8.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_8.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_9 = new JCheckBox("太平洋区");
		chckbxNewCheckBox_9.setBounds(690, 70, 150, 30);
		panel.add(chckbxNewCheckBox_9);

		chckbxNewCheckBox_9.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "前锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "中锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "后卫";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_9.isSelected()) {
					String text = "太平洋区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_9.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_9.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_10 = new JCheckBox("西南区");
		chckbxNewCheckBox_10.setBounds(690, 116, 150, 30);
		panel.add(chckbxNewCheckBox_10);

		chckbxNewCheckBox_10.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "前锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "中锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "后卫";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_10.isSelected()) {
					String text = "西南区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_10.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_10.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		JButton btnNewButton_1 = new JButton("显示全部");
		btnNewButton_1.setBounds(50, 90, 100, 30);
		panel.add(btnNewButton_1);
		
		add(scrollPane);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String text = "";
				sorter.setRowFilter(RowFilter.regexFilter(text));
				chckbxNewCheckBox.setSelected(false);
				chckbxNewCheckBox_1.setSelected(false);
				chckbxNewCheckBox_2.setSelected(false);
				chckbxNewCheckBox_3.setSelected(false);
				chckbxNewCheckBox_4.setSelected(false);
				chckbxNewCheckBox_5.setSelected(false);
				chckbxNewCheckBox_6.setSelected(false);
				chckbxNewCheckBox_7.setSelected(false);
				chckbxNewCheckBox_8.setSelected(false);
				chckbxNewCheckBox_9.setSelected(false);
				chckbxNewCheckBox_10.setSelected(false);
			}

		});

	}
}
