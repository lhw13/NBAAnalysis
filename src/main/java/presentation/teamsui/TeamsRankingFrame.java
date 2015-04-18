package presentation.teamsui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

import presentation.mainui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * 所有球队排名面板
 */
public class TeamsRankingFrame {

	public static JTable table;

	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton refreshButton;
	private JComboBox<String> comboBox;

	/**
	 * Create the application.
	 */
	public TeamsRankingFrame(DefaultTableModel model) {// 构造函数
		initialize(model);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DefaultTableModel model) {

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(900, 500));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		String table_0_columns[] = {"行号"};
		Object table_0_rows[][]=
			{
				{"1"}, {"2"}, {"3"}, {"4"}, {"5"},
				{"6"}, {"7"}, {"8"}, {"9"}, {"10"},
				{"11"}, {"12"}, {"13"}, {"14"}, {"15"},
				{"16"}, {"17"}, {"18"}, {"19"}, {"20"},
				{"21"}, {"22"}, {"23"}, {"24"}, {"25"},
				{"26"}, {"27"}, {"28"}, {"29"}, {"30"},
			};
		
		JTable table0 = new JTable();
		DefaultTableModel model0 = new DefaultTableModel(table_0_rows,table_0_columns);
		table0.setModel(model0);
		table0.setPreferredScrollableViewportSize(new Dimension(50, 500));
		
		table = new JTable();
		table.setModel(model);
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		scrollPane_1.setRowHeaderView(table0);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 50, 800, 500);
		tabbedPane.addTab("球队排名", null, scrollPane_1, null);

		panel.add(tabbedPane);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(200, 10, 120, 30);
		panel.add(btnNewButton);
		
		refreshButton = new JButton("最新");
		refreshButton.setBounds(350, 10, 120, 30);
		panel.add(refreshButton);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("投篮命中数");
		comboBox.addItem("投篮出手数");
		comboBox.addItem("三分命中");
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
		comboBox.setSelectedItem(MainFrame.selection2);
		comboBox.setBounds(500, 15, 150, 30);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					MainFrame.selection2="投篮命中数";
					MainFrame.table_1_columns[2]="投篮命中数(场均)";
					MainFrame.table_1_columns[3]="投篮命中数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 1: 
					MainFrame.selection2="投篮出手数";
					MainFrame.table_1_columns[2]="投篮出手数(场均)";
					MainFrame.table_1_columns[3]="投篮出手数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 2: 
					MainFrame.selection2="三分命中数";
					MainFrame.table_1_columns[2]="三分命中数(场均)";
					MainFrame.table_1_columns[3]="三分命中数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 3: 
					MainFrame.selection2="三分出手数";
					MainFrame.table_1_columns[2]="三分出手数(场均)";
					MainFrame.table_1_columns[3]="三分出手数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 4: 
					MainFrame.selection2="罚球命中数";
					MainFrame.table_1_columns[2]="罚球命中数(场均)";
					MainFrame.table_1_columns[3]="罚球命中数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 5: 
					MainFrame.selection2="罚球出手数";
					MainFrame.table_1_columns[2]="罚球出手数(场均)";
					MainFrame.table_1_columns[3]="罚球出手数(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 6: 
					MainFrame.selection2="进攻篮板";
					MainFrame.table_1_columns[2]="进攻篮板(场均)";
					MainFrame.table_1_columns[3]="进攻篮板(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 7: 
					MainFrame.selection2="防守篮板";
					MainFrame.table_1_columns[2]="防守篮板(场均)";
					MainFrame.table_1_columns[3]="防守篮板(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 8: 
					MainFrame.selection2="总篮板";
					MainFrame.table_1_columns[2]="总篮板(场均)";
					MainFrame.table_1_columns[3]="总篮板(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 9: 
					MainFrame.selection2="助攻";
					MainFrame.table_1_columns[2]="助攻(场均)";
					MainFrame.table_1_columns[3]="助攻(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 10: 
					MainFrame.selection2="抢断";
					MainFrame.table_1_columns[2]="抢断(场均)";
					MainFrame.table_1_columns[3]="抢断(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 11: 
					MainFrame.selection2="盖帽";
					MainFrame.table_1_columns[2]="盖帽(场均)";
					MainFrame.table_1_columns[3]="盖帽(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 12: 
					MainFrame.selection2="失误";
					MainFrame.table_1_columns[2]="失误(场均)";
					MainFrame.table_1_columns[3]="失误(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 13: 
					MainFrame.selection2="犯规";
					MainFrame.table_1_columns[2]="犯规(场均)";
					MainFrame.table_1_columns[3]="犯规(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 14: 
					MainFrame.selection2="得分";
					MainFrame.table_1_columns[2]="得分(场均)";
					MainFrame.table_1_columns[3]="得分(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 15: 
					MainFrame.selection2="投篮命中率";
					MainFrame.table_1_columns[2]="投篮命中率(场均)";
					MainFrame.table_1_columns[3]="投篮命中率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 16: 
					MainFrame.selection2="三分命中率";
					MainFrame.table_1_columns[2]="三分命中率(场均)";
					MainFrame.table_1_columns[3]="三分命中率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 17: 
					MainFrame.selection2="罚球命中率";
					MainFrame.table_1_columns[2]="罚球命中率(场均)";
					MainFrame.table_1_columns[3]="罚球命中率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 18: 
					MainFrame.selection2="胜率";
					MainFrame.table_1_columns[2]="胜率(场均)";
					MainFrame.table_1_columns[3]="胜率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 19: 
					MainFrame.selection2="进攻回合";
					MainFrame.table_1_columns[2]="进攻回合(场均)";
					MainFrame.table_1_columns[3]="进攻回合(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 20: 
					MainFrame.selection2="进攻效率";
					MainFrame.table_1_columns[2]="进攻效率(场均)";
					MainFrame.table_1_columns[3]="进攻效率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 21: 
					MainFrame.selection2="防守效率";
					MainFrame.table_1_columns[2]="防守效率(场均)";
					MainFrame.table_1_columns[3]="防守效率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 22: 
					MainFrame.selection2="进攻篮板效率";
					MainFrame.table_1_columns[2]="进攻篮板效率(场均)";
					MainFrame.table_1_columns[3]="进攻篮板效率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 23: 
					MainFrame.selection2="防守篮板效率";
					MainFrame.table_1_columns[2]="防守篮板效率(场均)";
					MainFrame.table_1_columns[3]="防守篮板效率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 24: 
					MainFrame.selection2="抢断效率";
					MainFrame.table_1_columns[2]="抢断效率(场均)";
					MainFrame.table_1_columns[3]="抢断效率(总计)";
					MainFrame.setTeamsRanking();
					break;
				case 25: 
					MainFrame.selection2="助攻率";
					MainFrame.table_1_columns[2]="助攻率(场均)";
					MainFrame.table_1_columns[3]="助攻率(总计)";
					MainFrame.setTeamsRanking();
					break;
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TeamsRankingFrame.scrollPane.setVisible(false);
				MainFrame.frame.getContentPane().remove(TeamsRankingFrame.scrollPane);
				TeamsRankingFrame.scrollPane=null;
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MainFrame.setTeamsRanking();
			}
		});
	}
}
