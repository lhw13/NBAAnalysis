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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import presentation.mainui.MainFrame;
import presentation.matchui.MatchDetailInfoPanel;
import server.businesslogic.BLController;
import server.po.MatchPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import javax.swing.JLabel;
import javax.swing.JComboBox;

/*
 * 单个球队比赛信息面板
 */
public class TeamsInfoFrame extends JPanel{

	private static JTable table;
	private static JTable table_1;
	private static JTable table_2;

	public JLabel teamPicture;

	public static JScrollPane scrollPane;
	
	private JScrollPane scrollPane_search;
	private JComboBox<String> comboBox_1;
	private JComboBox comboBox_2;
	private static String season="13-14";
	private static int date=0;
	Vector columnName3;
	DefaultTableModel model_3=new DefaultTableModel();
	private ArrayList<MatchPO> mpoList;
	private MouseListen listener = new MouseListen();
	
	private static BLController compute;
	
	Vector columnName1;
	DefaultTableModel model_1=new DefaultTableModel();
	private static String selection="投篮命中数";
	
	Vector columnName2;
	DefaultTableModel model_2;
	private String columns[] = { "球队", "球队缩写", "所在地", "赛区", "分区", "主场", "建立时间" };


	public TeamsInfoFrame(final TeamWithPlayersVO twpvo ,ImageIcon ii) {// 构造函数

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 300, 50);

		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(0, 0, 100, 30);
		panel.add(btnNewButton);
		
		JButton refreshButton = new JButton("最新");
		refreshButton.setBounds(110, 0, 100, 30);
		panel.add(refreshButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 231, 800, 500);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(300, 60, 600, 60);

		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 20));
		
		TeamVO tvo = new TeamVO();
		String fullName = "teamName";
		String abbreviation = "null";
		String location = "null";
		char division = ' ';
		String zone = "null";
		String home = "null";
		int setupTime = 0;
		if (twpvo.getTeam() != null) {
			tvo = twpvo.getTeam();
			fullName = tvo.getFullName();
			abbreviation = tvo.getAbbreviation();
			location = tvo.getLocation();
			division = tvo.getDivision();
			zone = tvo.getZone();
			home = tvo.getHome();
			setupTime = tvo.getSetupTime();
		}
		
		Object rows[][] = new Object[1][7];
		rows[0][0] = fullName;
		rows[0][1] = abbreviation;
		rows[0][2] = location;
		rows[0][3] = division;
		rows[0][4] = zone;
		rows[0][5] = home;
		rows[0][6] = setupTime;
		
		DefaultTableModel model = new DefaultTableModel(rows, columns);
		table.setModel(model);
		
		scrollPane_5.setViewportView(table);
		
		teamPicture = new JLabel("");
		teamPicture.setBounds(50, 50, 250, 150);
		teamPicture.setIcon(ii);
		
		scrollPane_search = new JScrollPane();
		scrollPane_search.setBounds(60, 800, 700, 300);
		
		String[] names3 = new String[]{"赛季", "日期", "球队", "总比分", "第一节", "第二节", "第三节", "第四节", "详情"};
		columnName3 = new Vector();
		for(int i=0;i<names3.length;i++) {
			columnName3.add(names3[i]);
		}
		
		JLabel lblNewLabel = new JLabel("球队过往比赛查询");
		lblNewLabel.setBounds(300, 750, 150, 30);
		
		JLabel lblNewLabel_1 = new JLabel("球队近期比赛");
		lblNewLabel_1.setBounds(60, 750, 150, 30);
		
        table_2 = new JTable(model_3);
		scrollPane_search.setViewportView(table_2);
		
		table_2.addMouseListener(listener);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(450, 750, 150, 30);
		comboBox_1.addItem("选择赛季");
		comboBox_1.addItem("12-13");
		comboBox_1.addItem("13-14");
		comboBox_1.addItem("14-15");
		comboBox_1.setSelectedItem("选择赛季");
		
		final String teamName = twpvo.getTeam().getAbbreviation();
		
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				switch(index){
				case 1:
					season="12-13";
					searchTheMatch(teamName);
					break;
				case 2:
					season="13-14";
					searchTheMatch(teamName);
					break;
				case 3:
					season="14-15";
					searchTheMatch(teamName);
					break;
				}
			}
			
		});
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItem("选择月份");
		comboBox_2.addItem(1);
		comboBox_2.addItem(2);
		comboBox_2.addItem(3);
		comboBox_2.addItem(4);
		comboBox_2.addItem(10);
		comboBox_2.addItem(11);
		comboBox_2.addItem(12);
		comboBox_2.setSelectedItem("选择月份");
		comboBox_2.setBounds(620, 750, 150, 30);
		
		comboBox_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_2.getSelectedIndex();
				switch(index){
				case 1:
					date=0;
					searchTheMatch(teamName);
					break;
				case 2:
					date=1;
					searchTheMatch(teamName);
					break;
				case 3:
					date=2;
					searchTheMatch(teamName);
					break;
				case 4:
					date=3;
					searchTheMatch(teamName);
					break;
				case 5:
					date=9;
					searchTheMatch(teamName);
					break;
				case 6:
					date=10;
					searchTheMatch(teamName);
					break;
				case 7:
					date=11;
					searchTheMatch(teamName);
					break;
				}
			}
			
		});

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1100, 1200));
		panel_1.setLayout(null);
		panel_1.add(scrollPane_5);
		panel_1.add(panel);
		panel_1.add(tabbedPane);
		panel_1.add(teamPicture);
		panel_1.add(scrollPane_search);
		panel_1.add(comboBox_1);
		panel_1.add(comboBox_2);
		panel_1.add(lblNewLabel);
		panel_1.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(0, 0, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		String[] names1 = new String[]{"球员", "场数", "在场时间", "投篮命中数(场均)", "投篮命中数(总计)"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		final JComboBox<String> comboBox = new JComboBox<String>();
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
		comboBox.setSelectedItem(selection);
		comboBox.setBounds(700, 200, 150, 30);
		panel_1.add(comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(60, 231, 795, 471);
		
		table_1 = new JTable(model_1);
		
		scrollPane_1.setViewportView(table_1);
		tabbedPane.addTab("球队信息", null, scrollPane_1, null);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					selection="投篮命中数";
					columnName1.setElementAt("投篮命中数(场均)", 3);
					columnName1.setElementAt("投篮命中数(总计)", 4);
					updateTeam(twpvo, "投篮命中数");
					break;
				case 1:
					selection="投篮出手数";
					columnName1.setElementAt("投篮出手数(场均)", 3);
					columnName1.setElementAt("投篮出手数(总计)", 4);
					updateTeam(twpvo, "投篮出手数");
					break;
				case 2: 
					selection="三分命中数";
					columnName1.setElementAt("三分命中数(场均)", 3);
					columnName1.setElementAt("三分命中数(总计)", 4);
					updateTeam(twpvo, "三分命中数");
					break;
				case 3: 
					selection="三分出手数";
					columnName1.setElementAt("三分出手数(场均)", 3);
					columnName1.setElementAt("三分出手数(总计)", 4);
					updateTeam(twpvo, "三分出手数");
					break;
				case 4: 
					selection="罚球命中数";
					columnName1.setElementAt("罚球命中数(场均)", 3);
					columnName1.setElementAt("罚球命中数(总计)", 4);
					updateTeam(twpvo, "罚球命中数");
					break;
				case 5: 
					selection="罚球出手数";
					columnName1.setElementAt("罚球出手数(场均)", 3);
					columnName1.setElementAt("罚球出手数(总计)", 4);
					updateTeam(twpvo, "罚球出手数");
					break;
				case 6: 
					selection="进攻篮板";
					columnName1.setElementAt("进攻篮板(场均)", 3);
					columnName1.setElementAt("进攻篮板(总计)", 4);
					updateTeam(twpvo, "进攻篮板");
					break;
				case 7: 
					selection="防守篮板";
					columnName1.setElementAt("防守篮板(场均)", 3);
					columnName1.setElementAt("防守篮板(总计)", 4);
					updateTeam(twpvo, "防守篮板");
					break;
				case 8: 
					selection="总篮板";
					columnName1.setElementAt("总篮板(场均)", 3);
					columnName1.setElementAt("总篮板(总计)", 4);
					updateTeam(twpvo, "总篮板");
					break;
				case 9: 
					selection="助攻";
					columnName1.setElementAt("助攻(场均)", 3);
					columnName1.setElementAt("助攻(总计)", 4);
					updateTeam(twpvo, "助攻");
					break;
				case 10: 
					selection="抢断";
					columnName1.setElementAt("抢断(场均)", 3);
					columnName1.setElementAt("抢断(总计)", 4);
					updateTeam(twpvo, "抢断");
					break;
				case 11: 
					selection="盖帽";
					columnName1.setElementAt("盖帽(场均)", 3);
					columnName1.setElementAt("盖帽(总计)", 4);
					updateTeam(twpvo, "盖帽");
					break;
				case 12: 
					selection="失误";
					columnName1.setElementAt("失误(场均)", 3);
					columnName1.setElementAt("失误(总计)", 4);
					updateTeam(twpvo, "失误");
					break;
				case 13: 
					selection="犯规";
					columnName1.setElementAt("犯规(场均)", 3);
					columnName1.setElementAt("犯规(总计)", 4);
					updateTeam(twpvo, "犯规");
					break;
				case 14: 
					selection="得分";
					columnName1.setElementAt("得分(场均)", 3);
					columnName1.setElementAt("得分(总计)", 4);
					updateTeam(twpvo, "得分");
					break;
				case 15: 
					selection="投篮命中率";
					columnName1.setElementAt("投篮命中率(场均)", 3);
					columnName1.setElementAt("投篮命中率(总计)", 4);
					updateTeam(twpvo, "投篮命中率");
					break;
				case 16: 
					selection="三分命中率";
					columnName1.setElementAt("三分命中率(场均)", 3);
					columnName1.setElementAt("三分命中率(总计)", 4);
					updateTeam(twpvo, "三分命中率");
					break;
				case 17: 
					selection="罚球命中率";
					columnName1.setElementAt("罚球命中率(场均)", 3);
					columnName1.setElementAt("罚球命中率(总计)", 4);
					updateTeam(twpvo, "罚球命中率");
					break;
				case 18: 
					selection="胜率";
					columnName1.setElementAt("胜率(场均)", 3);
					columnName1.setElementAt("胜率(总计)", 4);
					updateTeam(twpvo, "胜率");
					break;
				case 19: 
					selection="进攻回合";
					columnName1.setElementAt("进攻回合(场均)", 3);
					columnName1.setElementAt("进攻回合(总计)", 4);
					updateTeam(twpvo, "进攻回合");
					break;
				case 20: 
					selection="进攻效率";
					columnName1.setElementAt("进攻效率(场均)", 3);
					columnName1.setElementAt("进攻效率(总计)", 4);
					updateTeam(twpvo, "进攻效率");
					break;
				case 21: 
				    selection="防守效率";
					columnName1.setElementAt("防守效率(场均)", 3);
					columnName1.setElementAt("防守效率(总计)", 4);
					updateTeam(twpvo, "防守效率");
					break;
				case 22: 
					selection="进攻篮板效率";
					columnName1.setElementAt("进攻篮板效率(场均)", 3);
					columnName1.setElementAt("进攻篮板效率(总计)", 4);
					updateTeam(twpvo, "进攻篮板效率");
					break;
				case 23: 
					selection="防守篮板效率";
					columnName1.setElementAt("防守篮板效率(场均)", 3);
					columnName1.setElementAt("防守篮板效率(总计)", 4);
					updateTeam(twpvo, "防守篮板效率");
					break;
				case 24: 
					selection="抢断效率";
					columnName1.setElementAt("抢断效率(场均)", 3);
					columnName1.setElementAt("抢断效率(总计)", 4);
					updateTeam(twpvo, "抢断效率");
					break;
				case 25: 
					selection="助攻率";
					columnName1.setElementAt("助攻率(场均)", 3);
					columnName1.setElementAt("助攻率(总计)", 4);
					updateTeam(twpvo, "助攻率");
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
		
		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selection="投篮命中数";
				columnName1.setElementAt("投篮命中数(场均)", 3);
				columnName1.setElementAt("投篮命中数(总计)", 4);
				updateTeam(twpvo, "投篮命中数");
				comboBox.setSelectedItem(selection);
				latestMatchs(teamName);
				comboBox_1.setSelectedItem("选择赛季");
				comboBox_2.setSelectedItem("选择月份");
			}

		});
	}
	
	public void updateTeam(TeamWithPlayersVO twpvo, String con) {
		
		ArrayList<PlayerVO> players = twpvo.getPlayers();
		Vector rowDatas1 = new Vector();
		Vector rowData2;
		int appearance;
		TeamVO tvo = twpvo.getTeam();
		switch(con) {
		case "投篮命中数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getHit(), appearance));
				rowData1.add(pvo.getHit());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getHit(), appearance));
			rowData2.add(tvo.getHit());
			rowDatas1.add(rowData2);
			break;
		case "投篮出手数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getShot(), appearance));
				rowData1.add(pvo.getShot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getShot(), appearance));
			rowData2.add(tvo.getShot());
			rowDatas1.add(rowData2);
			break;
		case "三分命中数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getThirdHit(), appearance));
				rowData1.add(pvo.getThirdHit());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getThirdHit(), appearance));
			rowData2.add(tvo.getThirdHit());
			rowDatas1.add(rowData2);
			break;
		case "三分出手数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getThirdshot(), appearance));
				rowData1.add(pvo.getThirdshot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getThirdshot(), appearance));
			rowData2.add(tvo.getThirdshot());
			rowDatas1.add(rowData2);
			break;
		case "罚球命中数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFreeHit(), appearance));
				rowData1.add(pvo.getFreeHit());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getFreeHit(), appearance));
			rowData2.add(tvo.getFreeHit());
			rowDatas1.add(rowData2);
			break;
		case "罚球出手数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFreeshot(), appearance));
				rowData1.add(pvo.getFreeshot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getFreeshot(), appearance));
			rowData2.add(tvo.getFreeshot());
			rowDatas1.add(rowData2);
			break;
		case "进攻篮板":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getOffensiveRebound(), appearance));
				rowData1.add(pvo.getOffensiveRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getOffensiveRebound(), appearance));
			rowData2.add(tvo.getOffensiveRebound());
			rowDatas1.add(rowData2);
			break;
		case "防守篮板":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getDefensiveRebound(), appearance));
				rowData1.add(pvo.getDefensiveRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getDefensiveRebound(), appearance));
			rowData2.add(tvo.getDefensiveRebound());
			rowDatas1.add(rowData2);
			break;
		case "总篮板":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getTotalRebound(), appearance));
				rowData1.add(pvo.getTotalRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getTotalRebound(), appearance));
			rowData2.add(tvo.getTotalRebound());
			rowDatas1.add(rowData2);
			break;
		case "助攻":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getAssist(), appearance));
				rowData1.add(pvo.getAssist());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getAssist(), appearance));
			rowData2.add(tvo.getAssist());
			rowDatas1.add(rowData2);
			break;
		case "抢断":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getSteal(), appearance));
				rowData1.add(pvo.getSteal());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getSteal(), appearance));
			rowData2.add(tvo.getSteal());
			rowDatas1.add(rowData2);
			break;
		case "盖帽":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getBlock(), appearance));
				rowData1.add(pvo.getBlock());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getBlock(), appearance));
			rowData2.add(tvo.getBlock());
			rowDatas1.add(rowData2);
			break;
		case "失误":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getMiss(), appearance));
				rowData1.add(pvo.getMiss());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getMiss(), appearance));
			rowData2.add(tvo.getMiss());
			rowDatas1.add(rowData2);
			break;
		case "犯规":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFoul(), appearance));
				rowData1.add(pvo.getFoul());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getFoul(), appearance));
			rowData2.add(tvo.getFoul());
			rowDatas1.add(rowData2);
			break;
		case "得分":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getScore(), appearance));
				rowData1.add(pvo.getScore());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handle((double) tvo.getScore(), appearance));
			rowData2.add(tvo.getScore());
			rowDatas1.add(rowData2);
			break;
		case "投篮命中率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getHitRate());
				rowData1.add(pvo.getHitRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getHitRate());
			rowData2.add(tvo.getHitRate());
			rowDatas1.add(rowData2);
			break;
		case "三分命中率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getThirdHitRate());
				rowData1.add(pvo.getThirdHitRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getThirdHitRate());
			rowData2.add(tvo.getThirdHitRate());
			rowDatas1.add(rowData2);
			break;
		case "罚球命中率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getFreeHitRate());
				rowData1.add(pvo.getFreeHitRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getFreeHitRate());
			rowData2.add(tvo.getFreeHitRate());
			rowDatas1.add(rowData2);
			break;
		case "胜率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(0);
				rowData1.add(0);
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getWinRate());
			rowData2.add(tvo.getWinRate());
			rowDatas1.add(rowData2);
			break;
		case "进攻回合":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(0);
				rowData1.add(0);
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getOffensiveRound());
			rowData2.add(tvo.getOffensiveRound());
			rowDatas1.add(rowData2);
			break;
		case "进攻效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(0);
				rowData1.add(0);
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getOffensiveEfficiency());
			rowData2.add(tvo.getOffensiveEfficiency());
			rowDatas1.add(rowData2);
			break;
		case "防守效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(0);
				rowData1.add(0);
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getDefensiveEfficiency());
			rowData2.add(tvo.getDefensiveEfficiency());
			rowDatas1.add(rowData2);
			break;
		case "进攻篮板效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getOffensiveReboundRate());
				rowData1.add(pvo.getOffensiveReboundRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getOffensiveReboundEfficiency());
			rowData2.add(tvo.getOffensiveReboundEfficiency());
			rowDatas1.add(rowData2);
			break;
		case "防守篮板效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getDefensiveReboundRate());
				rowData1.add(pvo.getDefensiveReboundRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getDefensiveReboundEfficiency());
			rowData2.add(tvo.getDefensiveReboundEfficiency());
			rowDatas1.add(rowData2);
			break;
		case "抢断效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getStealRate());
				rowData1.add(pvo.getStealRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getStealEfficiency());
			rowData2.add(tvo.getStealEfficiency());
			rowDatas1.add(rowData2);
			break;
		case "助攻率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getAssistRate());
				rowData1.add(pvo.getAssistRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getAssistEfficiency());
			rowData2.add(tvo.getAssistEfficiency());
			rowDatas1.add(rowData2);
			break;
		}
		
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		table_1.setModel(model_1);
		table_1.setRowSorter(new TableRowSorter<TableModel>(model_1));
//		int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//		table_3.setColumnModel(getColumn(table_3, width));
		table_1.updateUI();
	}
	
	//最新比赛数据
	public void latestMatchs(String teamName){
		
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		int monthMax1 = 9;
		int monthMax2 = 0;
		boolean jude = false;
		int compareNum = 0;
		
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(season) && 
					(matchList.get(i).getTeam1().getAbbreviation().equals(teamName) ||
					 matchList.get(i).getTeam2().getAbbreviation().equals(teamName))){

				compareNum=matchList.get(i).getDate().get(Calendar.MONTH)+1;
				if(compareNum >4 && compareNum > monthMax1){
					monthMax1 = compareNum;
				}
				if(compareNum < 10 && compareNum > monthMax2){
					monthMax2 = compareNum;
					jude = true;
				}
			}
			
		}
		if(jude){
			date = monthMax2-1;
		}else{
			date = monthMax1-1;
		}
		
		
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(season) && 
					matchList.get(i).getDate().get(Calendar.MONTH)==date &&
					(matchList.get(i).getTeam1().getAbbreviation().equals(teamName) ||
					 matchList.get(i).getTeam2().getAbbreviation().equals(teamName))){
				selectedMatchs.add(matchList.get(i));
			}
		}
		
		mpoList = selectedMatchs;
		
		Vector rowDatas1 = new Vector();
		
		for(int i=0;i<selectedMatchs.size();i++){
			Vector rowData1 = new Vector();
			rowData1.add(selectedMatchs.get(i).getSeason());
			rowData1.add((selectedMatchs.get(i).getDate().get(Calendar.MONTH)+1)+"-"+
					selectedMatchs.get(i).getDate().get(Calendar.DAY_OF_MONTH));
			rowData1.add(selectedMatchs.get(i).getTeam1().getAbbreviation()+"-"+
					selectedMatchs.get(i).getTeam2().getAbbreviation());
			rowData1.add(selectedMatchs.get(i).getFinalScore().getTeam1()+"-"+
					selectedMatchs.get(i).getFinalScore().getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(0).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(0).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(1).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(1).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(2).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(2).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(3).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(3).getTeam2());
			rowData1.add("详情");
			rowDatas1.add(rowData1);
		}
		
		
		model_3.setDataVector(rowDatas1, columnName3);
		model_3.setColumnCount(table_2.getColumnCount());
		model_3.setRowCount(rowDatas1.size());
		table_2.setModel(model_3);
		table_2.updateUI();
		
	}
	
	//球队过往查询
	public void searchTheMatch(String teamName){
		
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(season) && 
					matchList.get(i).getDate().get(Calendar.MONTH)==date &&
					(matchList.get(i).getTeam1().getAbbreviation().equals(teamName) ||
					 matchList.get(i).getTeam2().getAbbreviation().equals(teamName))){
				selectedMatchs.add(matchList.get(i));
			}
		}
		
		mpoList = selectedMatchs;
		
		Vector rowDatas1 = new Vector();
		
		for(int i=0;i<selectedMatchs.size();i++){
			Vector rowData1 = new Vector();
			rowData1.add(selectedMatchs.get(i).getSeason());
			rowData1.add((selectedMatchs.get(i).getDate().get(Calendar.MONTH)+1)+"-"+
					selectedMatchs.get(i).getDate().get(Calendar.DAY_OF_MONTH));
			rowData1.add(selectedMatchs.get(i).getTeam1().getAbbreviation()+"-"+
					selectedMatchs.get(i).getTeam2().getAbbreviation());
			rowData1.add(selectedMatchs.get(i).getFinalScore().getTeam1()+"-"+
					selectedMatchs.get(i).getFinalScore().getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(0).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(0).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(1).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(1).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(2).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(2).getTeam2());
			rowData1.add(selectedMatchs.get(i).getScores().get(3).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(3).getTeam2());
			rowData1.add("详情");
			rowDatas1.add(rowData1);
		}
		
		
		model_3.setDataVector(rowDatas1, columnName3);
		model_3.setColumnCount(table_2.getColumnCount());
		model_3.setRowCount(rowDatas1.size());
		table_2.setModel(model_3);
		table_2.updateUI();
		
	}
	
	
	
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				if(c==8){
					setMatchInfo(r);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void setMatchInfo(int rowNum){
		MatchPO mpo = mpoList.get(rowNum);
		String[] cname1 = new String[] {
				"球员", "位置", "在场时间", "投篮命中", "出手", "三分命中", "出手", "罚球命中", "出手",
				"前篮板","后篮板","篮板","助攻","抢断","盖帽","失误","犯规", "得分" };
		
		DefaultTableModel model1 = new DefaultTableModel();
		Vector<String> columnName1 = new Vector<String>();
		Vector rowDatas1 = new Vector();
		for(int i=0;i<cname1.length;i++) {
			columnName1.add(cname1[i]);
		}
		for(int i=0;i<mpo.getTeam1().getPlayers().size();i++){
			Vector rowData1 = new Vector();
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getName());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getPosition()+"");
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getPlayTime());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getShot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdshot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getFreeHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdshot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getOffensiveRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getDefensiveRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getTotalRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getAssist());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getSteal());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getBlock());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getMiss());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getFoul());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getScore());
			rowDatas1.add(rowData1);
		}
		model1.setDataVector(rowDatas1, columnName1);		
		model1.setColumnCount(columnName1.size());
		model1.setRowCount(rowDatas1.size());
		
		DefaultTableModel model2 = new DefaultTableModel();
		Vector rowDatas2 = new Vector();
		for(int i=0;i<mpo.getTeam2().getPlayers().size();i++){
			Vector rowData2 = new Vector();
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getName());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getPosition()+"");
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getPlayTime());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getShot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdshot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getFreeHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdshot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getOffensiveRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getDefensiveRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getTotalRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getAssist());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getSteal());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getBlock());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getMiss());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getFoul());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getScore());
			rowDatas2.add(rowData2);
		}
		model2.setDataVector(rowDatas2, columnName1);		
		model2.setColumnCount(mpo.getTeam2().getPlayers().size());
		model2.setRowCount(rowDatas2.size());
		
		String[] cname2 = new String[] {
				"赛季", "日期", "球队", "总比分", "第一节", "第二节", "第三节", "第四节"};
		DefaultTableModel model3 = new DefaultTableModel();
		Vector<String> columnName3 = new Vector<String>();
		for(int i=0;i<cname2.length;i++) {
			columnName3.add(cname2[i]);
		}
		Vector rowDatas3 = new Vector();
		Vector rowData3 = new Vector();
		rowData3.add(mpo.getSeason());
		rowData3.add((mpo.getDate().get(Calendar.MONTH)+1)+"-"+
	                 mpo.getDate().get(Calendar.DAY_OF_MONTH));
		rowData3.add(mpo.getTeam1().getAbbreviation()+"-"+mpo.getTeam2().getAbbreviation());
		rowData3.add(mpo.getFinalScore().getTeam1()+"-"+mpo.getFinalScore().getTeam2());
		rowData3.add(mpo.getScores().get(0).getTeam1()+"-"+mpo.getScores().get(0).getTeam2());
		rowData3.add(mpo.getScores().get(1).getTeam1()+"-"+mpo.getScores().get(1).getTeam2());
		rowData3.add(mpo.getScores().get(2).getTeam1()+"-"+mpo.getScores().get(2).getTeam2());
		rowData3.add(mpo.getScores().get(3).getTeam1()+"-"+mpo.getScores().get(3).getTeam2());
		rowDatas3.add(rowData3);
		model3.setDataVector(rowDatas3, columnName3);		
		model3.setColumnCount(columnName3.size());
		model3.setRowCount(rowDatas3.size());
		
		int width = 200;
		int height = 150;
		compute = BLController.getInstance();
		ImageIcon[] ii=new ImageIcon[2];
		ii[0] = compute.getTeamPic(mpo.getTeam1().getAbbreviation());
		ii[0].setImage(ii[0].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		ii[1] = compute.getTeamPic(mpo.getTeam2().getAbbreviation());
		ii[1].setImage(ii[1].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		
		TeamsInfoFrame.scrollPane.setVisible(false);
		MatchDetailInfoPanel mdip = new MatchDetailInfoPanel(ii,model1,model2,model3);
		MainFrame.frame.getContentPane().add(mdip.scrollPane);
		MainFrame.frame.repaint();//刷新重画 
		MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		
		
		
	}
	
	public static double handle(double a, int b) {
		double result = a / (double) b;
		BigDecimal bg = new BigDecimal(result);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
}
