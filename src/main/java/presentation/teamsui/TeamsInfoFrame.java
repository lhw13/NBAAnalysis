package presentation.teamsui;

import java.awt.Dimension;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import presentation.mainui.MainFrame;
import server.businesslogic.BLController;
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

	public JLabel teamPicture;

	public static JScrollPane scrollPane;
	
	private BLController compute;
	
	Vector columnName1;
	DefaultTableModel model_1=new DefaultTableModel();
	private static String selection="投篮命中数";
	
	Vector columnName2;
	DefaultTableModel model_2;
	private String columns[] = { "球队", "球队缩写", "所在地", "赛区", "分区", "主场", "建立时间" };


	public TeamsInfoFrame(final TeamWithPlayersVO twpvo ,ImageIcon ii) {// 构造函数

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 100, 50);

		JButton btnNewButton = new JButton("返回");
		panel.add(btnNewButton);

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

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(700, 45, 250, 150);
		panel_2.setLayout(null);
		
		teamPicture = new JLabel("");
		teamPicture.setBounds(50, 20, 250, 150);
		teamPicture.setIcon(ii);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1100, 1000));
		panel_1.setLayout(null);
		panel_1.add(scrollPane_5);
		panel_1.add(panel);
		panel_1.add(panel_2);
		panel_1.add(tabbedPane);
		panel_1.add(teamPicture);
		
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
		panel_1.add(scrollPane_1);
		
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
				rowData1.add(pvo.getHit());
				rowData1.add(handle((double) pvo.getHit(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getHit());
			rowData2.add(handle((double) tvo.getHit(), appearance));
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
				rowData1.add(pvo.getShot());
				rowData1.add(handle((double) pvo.getShot(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getShot());
			rowData2.add(handle((double) tvo.getShot(), appearance));
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
				rowData1.add(pvo.getThirdHit());
				rowData1.add(handle((double) pvo.getThirdHit(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getThirdHit());
			rowData2.add(handle((double) tvo.getThirdHit(), appearance));
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
				rowData1.add(pvo.getThirdshot());
				rowData1.add(handle((double) pvo.getThirdshot(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getThirdshot());
			rowData2.add(handle((double) tvo.getThirdshot(), appearance));
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
				rowData1.add(pvo.getFreeHit());
				rowData1.add(handle((double) pvo.getFreeHit(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getFreeHit());
			rowData2.add(handle((double) tvo.getFreeHit(), appearance));
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
				rowData1.add(pvo.getFreeshot());
				rowData1.add(handle((double) pvo.getFreeshot(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getFreeshot());
			rowData2.add(handle((double) tvo.getFreeshot(), appearance));
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
				rowData1.add(pvo.getOffensiveRebound());
				rowData1.add(handle((double) pvo.getOffensiveRebound(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getOffensiveRebound());
			rowData2.add(handle((double) tvo.getOffensiveRebound(), appearance));
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
				rowData1.add(pvo.getDefensiveRebound());
				rowData1.add(handle((double) pvo.getDefensiveRebound(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getDefensiveRebound());
			rowData2.add(handle((double) tvo.getDefensiveRebound(), appearance));
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
				rowData1.add(pvo.getTotalRebound());
				rowData1.add(handle((double) pvo.getTotalRebound(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getTotalRebound());
			rowData2.add(handle((double) tvo.getTotalRebound(), appearance));
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
				rowData1.add(pvo.getAssist());
				rowData1.add(handle((double) pvo.getAssist(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getAssist());
			rowData2.add(handle((double) tvo.getAssist(), appearance));
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
				rowData1.add(pvo.getSteal());
				rowData1.add(handle((double) pvo.getSteal(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getSteal());
			rowData2.add(handle((double) tvo.getSteal(), appearance));
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
				rowData1.add(pvo.getBlock());
				rowData1.add(handle((double) pvo.getBlock(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getBlock());
			rowData2.add(handle((double) tvo.getBlock(), appearance));
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
				rowData1.add(pvo.getMiss());
				rowData1.add(handle((double) pvo.getMiss(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getMiss());
			rowData2.add(handle((double) tvo.getMiss(), appearance));
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
				rowData1.add(pvo.getFoul());
				rowData1.add(handle((double) pvo.getFoul(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getFoul());
			rowData2.add(handle((double) tvo.getFoul(), appearance));
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
				rowData1.add(pvo.getScore());
				rowData1.add(handle((double) pvo.getScore(), appearance));
				rowDatas1.add(rowData1);
			}
			appearance = tvo.getAppearance();
			rowData2 = new Vector();
			rowData2.add(tvo.getFullName());
			rowData2.add(tvo.getAppearance());			
			rowData2.add(0);
			rowData2.add(tvo.getScore());
			rowData2.add(handle((double) tvo.getScore(), appearance));
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
		
		model_1 = new DefaultTableModel(rowDatas1, columnName1) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		table_1.setModel(model_1);
		table_1.setRowSorter(new TableRowSorter<TableModel>(model_1));
//		int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//		table_3.setColumnModel(getColumn(table_3, width));
		table_1.updateUI();
	}
	
	private double handle(double a, int b) {
		double result = a / (double) b;
		BigDecimal c = new BigDecimal(result);
		double f1 = c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
}
