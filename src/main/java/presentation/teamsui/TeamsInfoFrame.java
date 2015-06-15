package presentation.teamsui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
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

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.matchui.MatchDetailInfoPanel;
import presentation.playerui.PlayerAnalysePanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerSelectionPanel;
import server.businesslogic.BLController;
import server.po.MatchPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import blservice.BLService;

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
	private static int date=0;
	private static Vector columnName3;
	private static DefaultTableModel model_3=new DefaultTableModel();
	private static ArrayList<MatchPO> mpoList;
	private MouseListen_1 listener_1 = new MouseListen_1();
	private MouseListen_2 listener_2 = new MouseListen_2();
	
	private static BLController compute;
	
	private static Vector columnName1;
	private static DefaultTableModel model_1=new DefaultTableModel() {
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	
	
	Vector columnName;
	DefaultTableModel model = new DefaultTableModel();

	String abb;
	public TeamsInfoFrame(final TeamWithPlayersVO twpvo) {// 构造函数
		compute = BLController.getInstance();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 120, 100);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(0, 0, 50, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("分析");
		btnNewButton_1.setBounds(60, 0, 50, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TeamsInfoFrame.scrollPane.setVisible(false);
				TeamAnalysePanel.scrollPane.setVisible(true);
				MainFrame.frame.setTitle("NBA球队分析");
				MainFrame.tap.update(abb);
				MainFrame.tap.update2(abb,"NBA");
				MainFrame.tap.update3();
				MainFrame.backPanels.add(MainFrame.currentPanel);
				MainFrame.currentPanel = Panels.TeamAnalysePanel;
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 160, 800, 300);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(400, 5, 350, 175);

		table = new JTable(model);
		
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
			abb = abbreviation;
			location = tvo.getLocation();
			division = tvo.getDivision();
			zone = tvo.getZone();
			home = tvo.getHome();
			setupTime = tvo.getSetupTime();
		}
		int winMatchNum;
		int loseMatchNum;
		Vector rowDatas = new Vector();
		Vector rowData = new Vector();			
		rowData.add("球队");
		TeamVO t= compute.getTeamAnalysis(abbreviation).getTeam();
		winMatchNum = (int)(t.getWinRate()*t.getAppearance());
		loseMatchNum = t.getAppearance()-winMatchNum;
		rowData.add(PlayerSelectionPanel.translate(abbreviation)+" 胜"+winMatchNum+" 负"+loseMatchNum);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("球队缩写");
		rowData.add(abbreviation);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("所在地");
		rowData.add(location);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("赛区");
		rowData.add(division);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("分区");
		rowData.add(zone);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("主场");
		rowData.add(home);
		rowDatas.add(rowData);
		rowData = new Vector();
		rowData.add("建立时间");
		rowData.add(setupTime);
		rowDatas.add(rowData);
		
		String[] cname = new String[] {
				"",""
			};
		columnName = new Vector();
		for(int i=0;i<cname.length;i++) {
			columnName.add(cname[i]);
		}
		
		model.setDataVector(rowDatas, columnName);		
		model.setColumnCount(table.getColumnCount());
		model.setRowCount(rowDatas.size());
		table.setModel(model);
		table.setFont(new Font("微软雅黑", Font.BOLD, 14));
		int[] width={100,200};
		table.setColumnModel(getColumn(table, width));
		table.updateUI();
		table.setShowGrid(false);
		
		
		scrollPane_5.setViewportView(table);
		
		ImageIcon picture = ImageHandle.loadTeam(twpvo.getTeam().getAbbreviation());
		picture.setImage(picture.getImage().getScaledInstance(250, 150,
				Image.SCALE_DEFAULT));
		teamPicture = new JLabel("");
		teamPicture.setBounds(110, 10, 250, 150);
		teamPicture.setIcon(picture);
		
		scrollPane_search = new JScrollPane();
		scrollPane_search.setBounds(100, 510, 800, 250);
		
		String[] names3 = new String[]{"赛季", "日期", "主队", "比分", "客队", "主队最高分", "客队最高分", "详情"};
		columnName3 = new Vector();
		for(int i=0;i<names3.length;i++) {
			columnName3.add(names3[i]);
		}
		
		JLabel lblNewLabel = new JLabel("球队过往比赛查询");
		lblNewLabel.setBounds(350, 475, 150, 30);
		
		JLabel lblNewLabel_1 = new JLabel("球队近期比赛");
		lblNewLabel_1.setBounds(100, 475, 150, 30);
		
        table_2 = new JTable(model_3);
		scrollPane_search.setViewportView(table_2);
		
		table_2.addMouseListener(listener_1);
		table_2.setShowGrid(false);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(500, 475, 150, 30);
		comboBox_1.addItem("选择赛季");
		comboBox_1.addItem("11-12");
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
					compute = BLController.getInstance();
					compute.setSeason("11-12");
					MainFrame.season = "11-12";
					MainFrame.seasonChange = true;
					searchTheMatch(teamName);
					break;
				case 2:
					compute = BLController.getInstance();
					compute.setSeason("12-13");
					MainFrame.season = "12-13";
					MainFrame.seasonChange = true;
					searchTheMatch(teamName);
					break;
				case 3:
					compute = BLController.getInstance();
					compute.setSeason("13-14");
					MainFrame.season = "13-14";
					MainFrame.seasonChange = true;
					searchTheMatch(teamName);
					break;
				case 4:
					compute = BLController.getInstance();
					compute.setSeason("14-15");
					MainFrame.season = "14-15";
					MainFrame.seasonChange = true;
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
		comboBox_2.addItem(5);
		comboBox_2.addItem(6);
		comboBox_2.addItem(10);
		comboBox_2.addItem(11);
		comboBox_2.addItem(12);
		comboBox_2.setSelectedItem("选择月份");
		comboBox_2.setBounds(670, 475, 150, 30);
		
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
					date=4;
					searchTheMatch(teamName);
					break;
				case 6:
					date=5;
					searchTheMatch(teamName);
					break;
				case 7:
					date=9;
					searchTheMatch(teamName);
					break;
				case 8:
					date=10;
					searchTheMatch(teamName);
					break;
				case 9:
					date=11;
					searchTheMatch(teamName);
					break;
				}
			}
			
		});

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(900, 800));
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
		
		String[] names1 = new String[]{"","球员", "场数", "在场时间", "得分(场均)", "得分(总计)"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("得分");
		comboBox.addItem("胜率");
		comboBox.addItem("抢断");
		comboBox.addItem("盖帽");
		comboBox.addItem("助攻");
		comboBox.addItem("总篮板");
		comboBox.addItem("进攻篮板");
		comboBox.addItem("防守篮板");
		comboBox.addItem("投篮命中率");
		comboBox.addItem("三分命中率");
		comboBox.addItem("罚球命中率");
		comboBox.addItem("失误");
		comboBox.addItem("犯规");
		comboBox.addItem("进攻回合");
		comboBox.addItem("进攻效率");
		comboBox.addItem("防守效率");
		comboBox.addItem("进攻篮板效率");
		comboBox.addItem("防守篮板效率");
		comboBox.addItem("抢断效率");
		comboBox.addItem("助攻率");
		comboBox.addItem("投篮命中数");
		comboBox.addItem("投篮出手数");
		comboBox.addItem("三分命中数");
		comboBox.addItem("三分出手数");
		comboBox.addItem("罚球命中数");
		comboBox.addItem("罚球出手数");
		comboBox.setBounds(750, 140, 150, 30);
		comboBox.setSelectedItem("得分");
		panel_1.add(comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(60, 231, 795, 471);
		
		table_1 = new JTable(model_1);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model_1);
		table_1.setRowSorter(sorter);
		table_1.setRowHeight(56);
		table_1.addMouseListener(listener_2);
		table_1.setShowGrid(false);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr);
		
		scrollPane_1.setViewportView(table_1);
		tabbedPane.addTab("球队信息", null, scrollPane_1, null);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String index =(String) comboBox.getSelectedItem();
				switch(index){
				case "胜率": 
					MainFrame.selection="胜率";
					columnName1.setElementAt("胜率(场均)", 4);
					columnName1.setElementAt("胜率(总计)", 5);
					updateTeam(twpvo, "胜率");
					break;
				case "投篮出手数":
					MainFrame.selection="投篮出手数";
					columnName1.setElementAt("投篮出手数(场均)", 4);
					columnName1.setElementAt("投篮出手数(总计)", 5);
					updateTeam(twpvo, "投篮出手数");
					break;
				case "三分命中数": 
					MainFrame.selection="三分命中数";
					columnName1.setElementAt("三分命中数(场均)", 4);
					columnName1.setElementAt("三分命中数(总计)", 5);
					updateTeam(twpvo, "三分命中数");
					break;
				case "三分出手数": 
					MainFrame.selection="三分出手数";
					columnName1.setElementAt("三分出手数(场均)", 4);
					columnName1.setElementAt("三分出手数(总计)", 5);
					updateTeam(twpvo, "三分出手数");
					break;
				case "罚球命中数": 
					MainFrame.selection="罚球命中数";
					columnName1.setElementAt("罚球命中数(场均)", 4);
					columnName1.setElementAt("罚球命中数(总计)", 5);
					updateTeam(twpvo, "罚球命中数");
					break;
				case "罚球出手数": 
					MainFrame.selection="罚球出手数";
					columnName1.setElementAt("罚球出手数(场均)", 4);
					columnName1.setElementAt("罚球出手数(总计)", 5);
					updateTeam(twpvo, "罚球出手数");
					break;
				case "进攻篮板": 
					MainFrame.selection="进攻篮板";
					columnName1.setElementAt("进攻篮板(场均)", 4);
					columnName1.setElementAt("进攻篮板(总计)", 5);
					updateTeam(twpvo, "进攻篮板");
					break;
				case "防守篮板": 
					MainFrame.selection="防守篮板";
					columnName1.setElementAt("防守篮板(场均)", 4);
					columnName1.setElementAt("防守篮板(总计)", 5);
					updateTeam(twpvo, "防守篮板");
					break;
				case "总篮板": 
					MainFrame.selection="总篮板";
					columnName1.setElementAt("总篮板(场均)", 4);
					columnName1.setElementAt("总篮板(总计)", 5);
					updateTeam(twpvo, "总篮板");
					break;
				case "助攻": 
					MainFrame.selection="助攻";
					columnName1.setElementAt("助攻(场均)", 4);
					columnName1.setElementAt("助攻(总计)", 5);
					updateTeam(twpvo, "助攻");
					break;
				case "抢断": 
					MainFrame.selection="抢断";
					columnName1.setElementAt("抢断(场均)", 4);
					columnName1.setElementAt("抢断(总计)", 5);
					updateTeam(twpvo, "抢断");
					break;
				case "盖帽": 
					MainFrame.selection="盖帽";
					columnName1.setElementAt("盖帽(场均)", 4);
					columnName1.setElementAt("盖帽(总计)", 5);
					updateTeam(twpvo, "盖帽");
					break;
				case "失误": 
					MainFrame.selection="失误";
					columnName1.setElementAt("失误(场均)", 4);
					columnName1.setElementAt("失误(总计)", 5);
					updateTeam(twpvo, "失误");
					break;
				case "犯规": 
					MainFrame.selection="犯规";
					columnName1.setElementAt("犯规(场均)", 4);
					columnName1.setElementAt("犯规(总计)", 5);
					updateTeam(twpvo, "犯规");
					break;
				case "得分": 
					MainFrame.selection="得分";
					columnName1.setElementAt("得分(场均)", 4);
					columnName1.setElementAt("得分(总计)", 5);
					updateTeam(twpvo, "得分");
					break;
				case "投篮命中率": 
					MainFrame.selection="投篮命中率";
					columnName1.setElementAt("投篮命中率(场均)", 4);
					columnName1.setElementAt("投篮命中率(总计)", 5);
					updateTeam(twpvo, "投篮命中率");
					break;
				case "三分命中率": 
					MainFrame.selection="三分命中率";
					columnName1.setElementAt("三分命中率(场均)", 4);
					columnName1.setElementAt("三分命中率(总计)", 5);
					updateTeam(twpvo, "三分命中率");
					break;
				case "罚球命中率": 
					MainFrame.selection="罚球命中率";
					columnName1.setElementAt("罚球命中率(场均)", 4);
					columnName1.setElementAt("罚球命中率(总计)", 5);
					updateTeam(twpvo, "罚球命中率");
					break;
				case "投篮命中数": 
					MainFrame.selection="投篮命中数";
					columnName1.setElementAt("投篮命中数(场均)", 4);
					columnName1.setElementAt("投篮命中数(总计)", 5);
					updateTeam(twpvo, "投篮命中数");
					break;
				case "进攻回合": 
					MainFrame.selection="进攻回合";
					columnName1.setElementAt("进攻回合(场均)", 4);
					columnName1.setElementAt("进攻回合(总计)", 5);
					updateTeam(twpvo, "进攻回合");
					break;
				case "进攻效率": 
					MainFrame.selection="进攻效率";
					columnName1.setElementAt("进攻效率(场均)", 4);
					columnName1.setElementAt("进攻效率(总计)", 5);
					updateTeam(twpvo, "进攻效率");
					break;
				case "防守效率": 
					MainFrame.selection="防守效率";
					columnName1.setElementAt("防守效率(场均)", 4);
					columnName1.setElementAt("防守效率(总计)", 5);
					updateTeam(twpvo, "防守效率");
					break;
				case "进攻篮板效率": 
					MainFrame.selection="进攻篮板效率";
					columnName1.setElementAt("进攻篮板效率(场均)", 4);
					columnName1.setElementAt("进攻篮板效率(总计)", 5);
					updateTeam(twpvo, "进攻篮板效率");
					break;
				case "防守篮板效率": 
					MainFrame.selection="防守篮板效率";
					columnName1.setElementAt("防守篮板效率(场均)", 4);
					columnName1.setElementAt("防守篮板效率(总计)", 5);
					updateTeam(twpvo, "防守篮板效率");
					break;
				case "抢断效率": 
					MainFrame.selection="抢断效率";
					columnName1.setElementAt("抢断效率(场均)", 4);
					columnName1.setElementAt("抢断效率(总计)", 5);
					updateTeam(twpvo, "抢断效率");
					break;
				case "助攻率": 
					MainFrame.selection="助攻率";
					columnName1.setElementAt("助攻率(场均)", 4);
					columnName1.setElementAt("助攻率(总计)", 5);
					updateTeam(twpvo, "助攻率");
					break;
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int size = MainFrame.backPanels.size();
					Panels temp = MainFrame.backPanels.get(size-1);
					MainFrame.backPanels.remove(size-1);
					switch(temp) {
					case TeamsSelectionFrame:
						TeamsSelectionFrame.scrollPane.setVisible(true);
						TeamsSelectionFrame.flag = true;
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("NBA球队选择");
						MainFrame.currentPanel = Panels.TeamsSelectionFrame;
						break;
					case TeamsRankingFrame:
						TeamsRankingFrame.scrollPane.setVisible(true);
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("NBA球队排名");
						MainFrame.currentPanel = Panels.TeamsRankingFrame;
						break;
					case PlayerRankingPanel:
						PlayerRankingPanel.scrollPane.setVisible(true);
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("NBA球员排名");
						MainFrame.currentPanel = Panels.PlayerRankingPanel;
						break;
					case PlayerInfoPanel:
						PlayerInfoPanel.scrollPane.setVisible(true);
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("NBA球员信息");
						MainFrame.currentPanel = Panels.PlayerInfoPanel;
						break;
					case HotRankingPanel:
						HotRankingPanel.scrollPane.setVisible(true);
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("今日快讯");
						MainFrame.currentPanel = Panels.HotRankingPanel;
						break;
					case MatchDetailInfoPanel:
						MatchDetailInfoPanel.scrollPane.setVisible(true);
						TeamsInfoFrame.scrollPane.setVisible(false);
						TeamsInfoFrame.scrollPane = null;
						MainFrame.frame.setTitle("NBA球队信息");
						MainFrame.currentPanel = Panels.MatchDetailInfoPanel;
						break;
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		
	}
	
	private static ArrayList<PlayerVO> sortThePlayerOnScore(ArrayList<PlayerVO> players){
		for(int i=0;i<players.size();i++) {
			for(int j=i+1;j<players.size();j++){
				if(players.get(i).getScore()<players.get(j).getScore()){
					PlayerVO temp = players.get(i);
					players.set(i, players.get(j));
					players.set(j, temp);
				}
			}
		}
		return players;
	}
	
	public static void updateTeam(TeamWithPlayersVO t, String con) {
		compute = BLController.getInstance();
		TeamWithPlayersVO twpvo = compute.getTeamAnalysis(t.getTeam().getAbbreviation());
		MainFrame.TWPVO = twpvo;
		ArrayList<PlayerVO> players_NoSort = twpvo.getPlayers();
		TeamVO tvo = twpvo.getTeam();
		//球员按得分从大到小排序
		ArrayList<PlayerVO> players = sortThePlayerOnScore(players_NoSort);
		Vector rowDatas1 = new Vector();
		Vector rowData2;
		ImageIcon picture;
		int appearance;
		switch(con) {
		case "投篮命中数":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				if(players.get(i)!=null){
					PlayerVO pvo = players.get(i);
					appearance = pvo.getAppearance();
					picture = ImageHandle.loadPlayer(pvo.getName());
					picture.setImage(picture.getImage().getScaledInstance(70, 56,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(pvo.getName());
					rowData1.add(pvo.getAppearance());			
					rowData1.add(pvo.getPlayTime());
					rowData1.add(handle((double) pvo.getHit(), appearance));
					rowData1.add(pvo.getHit());
					rowDatas1.add(rowData1);
				}
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getShot(), appearance));
				rowData1.add(pvo.getShot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getThirdHit(), appearance));
				rowData1.add(pvo.getThirdHit());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getThirdshot(), appearance));
				rowData1.add(pvo.getThirdshot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFreeHit(), appearance));
				rowData1.add(pvo.getFreeHit());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFreeshot(), appearance));
				rowData1.add(pvo.getFreeshot());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getOffensiveRebound(), appearance));
				rowData1.add(pvo.getOffensiveRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getDefensiveRebound(), appearance));
				rowData1.add(pvo.getDefensiveRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getTotalRebound(), appearance));
				rowData1.add(pvo.getTotalRebound());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getAssist(), appearance));
				rowData1.add(pvo.getAssist());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getSteal(), appearance));
				rowData1.add(pvo.getSteal());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getBlock(), appearance));
				rowData1.add(pvo.getBlock());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getMiss(), appearance));
				rowData1.add(pvo.getMiss());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getFoul(), appearance));
				rowData1.add(pvo.getFoul());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handle((double) pvo.getScore(), appearance));
				rowData1.add(pvo.getScore());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handleDecimal(pvo.getHitRate()*100)+"%");
				rowData1.add(handleDecimal(pvo.getHitRate()*100)+"%");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getHitRate()*100)+"%");
			rowData2.add(handleDecimal(tvo.getHitRate()*100)+"%");
			rowDatas1.add(rowData2);
			break;
		case "三分命中率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handleDecimal(pvo.getThirdHitRate()*100)+"%");
				rowData1.add(handleDecimal(pvo.getThirdHitRate()*100)+"%");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getThirdHitRate()*100)+"%");
			rowData2.add(handleDecimal(tvo.getThirdHitRate()*100)+"%");
			rowDatas1.add(rowData2);
			break;
		case "罚球命中率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(handleDecimal(pvo.getFreeHitRate()*100)+"%");
				rowData1.add(handleDecimal(pvo.getFreeHitRate()*100)+"%");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getFreeHitRate()*100)+"%");
			rowData2.add(handleDecimal(tvo.getFreeHitRate()*100)+"%");
			rowDatas1.add(rowData2);
			break;
		case "胜率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add("0");
				rowData1.add("0");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getWinRate()*100)+"%");
			rowData2.add(handleDecimal(tvo.getWinRate()*100)+"%");
			rowDatas1.add(rowData2);
			break;
		case "进攻回合":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add("0");
				rowData1.add("0");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getOffensiveRound()));
			rowData2.add(handleDecimal(tvo.getOffensiveRound()));
			rowDatas1.add(rowData2);
			break;
		case "进攻效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add("0");
				rowData1.add("0");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getOffensiveEfficiency()));
			rowData2.add(handleDecimal(tvo.getOffensiveEfficiency()));
			rowDatas1.add(rowData2);
			break;
		case "防守效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add("0");
				rowData1.add("0");
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(handleDecimal(tvo.getDefensiveEfficiency()));
			rowData2.add(handleDecimal(tvo.getDefensiveEfficiency()));
			rowDatas1.add(rowData2);
			break;
		case "进攻篮板效率":
			for(int i=0;i<players.size();i++) {
				Vector rowData1 = new Vector();
				PlayerVO pvo = players.get(i);	
				appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getOffensiveReboundRate());
				rowData1.add(pvo.getOffensiveReboundRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getDefensiveReboundRate());
				rowData1.add(pvo.getDefensiveReboundRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getStealRate());
				rowData1.add(pvo.getStealRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getAppearance());			
				rowData1.add(pvo.getPlayTime());
				rowData1.add(pvo.getAssistRate());
				rowData1.add(pvo.getAssistRate());
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData2.add(picture);
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
//		int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//		table_3.setColumnModel(getColumn(table_3, width));
		table_1.updateUI();
	}
	
	//最新比赛数据
	public static void latestMatchs(String teamName){
		
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		int monthMax1 = 9;
		int monthMax2 = 0;
		boolean jude = false;
		int compareNum = 0;
		
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(MainFrame.season) && 
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
			if(matchList.get(i).getSeason().equals(MainFrame.season) && 
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
			rowData1.add(PlayerSelectionPanel.translate(selectedMatchs.get(i).getTeam1().getAbbreviation()));
			rowData1.add(selectedMatchs.get(i).getFinalScore().getTeam1()+"-"+
					selectedMatchs.get(i).getFinalScore().getTeam2());
			rowData1.add(PlayerSelectionPanel.translate(selectedMatchs.get(i).getTeam2().getAbbreviation()));
			rowData1.add(selectedMatchs.get(i).getTeam1().getHighestScore().getName()+" "+
					selectedMatchs.get(i).getTeam1().getHighestScore().getScore());
			rowData1.add(selectedMatchs.get(i).getTeam2().getHighestScore().getName()+" "+
					selectedMatchs.get(i).getTeam2().getHighestScore().getScore());
			rowData1.add("详情");
			rowDatas1.add(rowData1);
		}
		
		
		model_3.setDataVector(rowDatas1, columnName3);
		model_3.setColumnCount(table_2.getColumnCount());
		model_3.setRowCount(rowDatas1.size());
		int[] width={50,50,50,60,50,150,150,50};
		table_2.setColumnModel(getColumn(table_2, width));
		table_2.setModel(model_3);
		table_2.updateUI();
		
	}
	
	//球队过往查询
	public void searchTheMatch(String teamName){
		
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(MainFrame.season) && 
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
			rowData1.add(PlayerSelectionPanel.translate(selectedMatchs.get(i).getTeam1().getAbbreviation()));
			rowData1.add(selectedMatchs.get(i).getFinalScore().getTeam1()+"-"+
					selectedMatchs.get(i).getFinalScore().getTeam2());
			rowData1.add(PlayerSelectionPanel.translate(selectedMatchs.get(i).getTeam2().getAbbreviation()));
			rowData1.add(selectedMatchs.get(i).getTeam1().getHighestScore().getName()+" "+
					selectedMatchs.get(i).getTeam1().getHighestScore().getScore());
			rowData1.add(selectedMatchs.get(i).getTeam2().getHighestScore().getName()+" "+
					selectedMatchs.get(i).getTeam2().getHighestScore().getScore());
			rowData1.add("详情");
			rowDatas1.add(rowData1);
		}
		
		model_3.setDataVector(rowDatas1, columnName3);
		model_3.setColumnCount(table_2.getColumnCount());
		model_3.setRowCount(rowDatas1.size());
		int[] width={50,50,50,60,50,150,150,50};
		table_2.setColumnModel(getColumn(table_2, width));
		table_2.setModel(model_3);
		table_2.updateUI();
		
	}
	
	public class MouseListen_1 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				setMatchInfo(r);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		public void mouseEntered(MouseEvent e) {
			table_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			table_2.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public class MouseListen_2 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			int k = 0;
			try {
				if(!table.getValueAt(r,3).toString().equals("0")){
					TeamsInfoFrame.scrollPane.setVisible(false);
					MainFrame.pip = new PlayerInfoPanel();
					MainFrame.pip.update(table.getValueAt(r,1).toString());
					MainFrame.frame.getContentPane().add(PlayerInfoPanel.scrollPane);
					PlayerInfoPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员信息");
					MainFrame.backPanels.add(MainFrame.currentPanel);
					MainFrame.currentPanel = Panels.PlayerInfoPanel;
					MainFrame.frame.repaint();//刷新重画 
					MainFrame.frame.validate();//保证重画后的窗口能正常立即显示
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		public void mouseEntered(MouseEvent e) {
			table_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			table_1.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	private void setMatchInfo(int rowNum){
		MatchPO mpo = mpoList.get(rowNum);
		String[] cname1 = new String[] {
				"球员", "位置", "在场时间", "投篮", "三分", "罚球",
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
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getPlayTime()/60+" min");
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getHit()+"/"+
			               mpo.getTeam1().getPlayers().get(i).getShot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdHit()+"/"+
					       mpo.getTeam1().getPlayers().get(i).getThirdshot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getFreeHit()+"/"+
					       mpo.getTeam1().getPlayers().get(i).getFreeshot());
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
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getPlayTime()/60+" min");
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getHit()+"/"+
	                       mpo.getTeam2().getPlayers().get(i).getShot());
	        rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdHit()+"/"+
			               mpo.getTeam2().getPlayers().get(i).getThirdshot());
	        rowData2.add(mpo.getTeam2().getPlayers().get(i).getFreeHit()+"/"+
			               mpo.getTeam2().getPlayers().get(i).getFreeshot());
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
		model2.setColumnCount(columnName1.size());
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
		
		String[] cname3 = new String[] {
				"","","" };
		DefaultTableModel model4 = new DefaultTableModel();
		Vector<String> columnName4 = new Vector<String>();
		for(int i=0;i<cname3.length;i++) {
			columnName4.add(cname3[i]);
		}
		
		int winMatchNum;
		int loseMatchNum;
		Vector rowDatas4 = new Vector();
		Vector rowData4 = new Vector();
		TeamVO tvo1= compute.getTeamAnalysis(mpo.getTeam1().getAbbreviation()).getTeam();
		winMatchNum = (int)(tvo1.getWinRate()*tvo1.getAppearance());
		loseMatchNum = tvo1.getAppearance()-winMatchNum;
		rowData4.add(PlayerSelectionPanel.translate(mpo.getTeam1().getAbbreviation())+" 胜"+winMatchNum+
				" 负"+loseMatchNum);
		rowData4.add("各项最高");
		TeamVO tvo2= compute.getTeamAnalysis(mpo.getTeam2().getAbbreviation()).getTeam();
		winMatchNum = (int)(tvo2.getWinRate()*tvo2.getAppearance());
		loseMatchNum = tvo2.getAppearance()-winMatchNum;
		rowData4.add(PlayerSelectionPanel.translate(mpo.getTeam2().getAbbreviation())+" 胜"+winMatchNum+
				" 负"+loseMatchNum);
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestScore().getName()+" "+
		               mpo.getTeam1().getHighestScore().getScore());
		rowData4.add("得分");
		rowData4.add(mpo.getTeam2().getHighestScore().getName()+" "+
	               mpo.getTeam2().getHighestScore().getScore());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestRebound().getName()+" "+
                mpo.getTeam1().getHighestRebound().getTotalRebound());
	    rowData4.add("篮板");
	    rowData4.add(mpo.getTeam2().getHighestRebound().getName()+" "+
                       mpo.getTeam2().getHighestRebound().getTotalRebound());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestAssist().getName()+" "+
                       mpo.getTeam1().getHighestAssist().getAssist());
        rowData4.add("助攻");
        rowData4.add(mpo.getTeam2().getHighestAssist().getName()+" "+
                       mpo.getTeam2().getHighestAssist().getAssist());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestSteal().getName()+" "+
                       mpo.getTeam1().getHighestSteal().getSteal());
        rowData4.add("抢断");
        rowData4.add(mpo.getTeam2().getHighestSteal().getName()+" "+
                       mpo.getTeam2().getHighestSteal().getSteal());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestBlock().getName()+" "+
                       mpo.getTeam1().getHighestBlock().getBlock());
        rowData4.add("盖帽");
        rowData4.add(mpo.getTeam1().getHighestBlock().getName()+" "+
                       mpo.getTeam1().getHighestBlock().getBlock());
		rowDatas4.add(rowData4);
		
		model4.setDataVector(rowDatas4, columnName4);		
		model4.setColumnCount(columnName4.size());
		model4.setRowCount(rowDatas4.size());
		
		int width = 200;
		int height = 150;
		ImageIcon[] ii=new ImageIcon[2];
		ii[0] = ImageHandle.loadTeam(mpo.getTeam1().getAbbreviation());
		ii[0].setImage(ii[0].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		ii[1] = ImageHandle.loadTeam(mpo.getTeam2().getAbbreviation());
		ii[1].setImage(ii[1].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		
		TeamsInfoFrame.scrollPane.setVisible(false);
		MatchDetailInfoPanel mdip = new MatchDetailInfoPanel(ii,model1,model2,model3,model4);
		MainFrame.frame.getContentPane().add(mdip.scrollPane);
		MainFrame.frame.repaint();//刷新重画 
		MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		MainFrame.frame.setTitle("NBA比赛信息");
		MainFrame.backPanels.add(MainFrame.currentPanel);
		MainFrame.currentPanel = Panels.MatchDetailInfoPanel;
		
		
		
	}
	
	public static TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}
	
	public static String handleDecimal(double f) {
		return String.format("%.1f", f);
	}
	
	public static double handle(double a, int b) {
		double result = a / (double) b;
		Double r = new Double(result);
		if(result!=0&&!r.isNaN()&&!r.isInfinite()) {
			BigDecimal bg = new BigDecimal(result);
			result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return result;
	}
}
