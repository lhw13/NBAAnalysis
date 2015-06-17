package presentation.teamsui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerRankingPanel.MouseListen;
import server.businesslogic.BLController;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
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
	private JComboBox<String> comboBox;
	private static JComboBox<String> choose_season;

	private static Vector columnName1;
	private static DefaultTableModel model_1=new DefaultTableModel() {
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	
	private Vector columnName0;
	DefaultTableModel model0 = new DefaultTableModel();
	
	MouseListen listener = new MouseListen();
	
	private static BLController compute;
	/**
	 * Create the application.
	 */
	public TeamsRankingFrame() {// 构造函数
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(900, 500));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		Vector rowDatas = new Vector();
		columnName0 = new Vector();
		columnName0.add("排名");
		for(int i=0;i<30;i++){
			Vector rowData = new Vector();
			rowData.add((i+1)+"");
			rowDatas.add(rowData);
		}
		JTable table0 = new JTable();
		model0 = new DefaultTableModel(rowDatas,columnName0);
		table0.setModel(model0);
		table0.setPreferredScrollableViewportSize(new Dimension(50, 600));
		table0.setRowHeight(70);
		
		table = new JTable();
		table.setModel(model_1);
		table.setRowSorter(new TableRowSorter<TableModel>(model_1));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(70);
		table.addMouseListener(listener);
		table.setShowGrid(false);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		scrollPane_1.setRowHeaderView(table0);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 60, 800, 470);
		tabbedPane.addTab("球队排名", null, scrollPane_1, null);

		panel.add(tabbedPane);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(100, 10, 120, 30);
		panel.add(btnNewButton);
		
		choose_season = new JComboBox<String>();
		choose_season.setBounds(600, 15, 100, 30);
		choose_season.addItem("选择赛季");
		choose_season.addItem("14-15");
		choose_season.addItem("13-14");
		choose_season.addItem("12-13");
		choose_season.addItem("11-12");
		choose_season.addItem("10-11");
		choose_season.addItem("09-10");
		choose_season.addItem("08-09");
		choose_season.addItem("07-08");
		choose_season.addItem("06-07");
		choose_season.addItem("05-06");
		choose_season.addItem("04-05");
		choose_season.addItem("03-04");
		choose_season.addItem("02-03");
		choose_season.addItem("01-02");
		choose_season.addItem("00-11");
		choose_season.addItem("99-00");
		choose_season.addItem("98-99");
		choose_season.addItem("97-98");
		choose_season.addItem("96-97");
		choose_season.addItem("95-96");
		choose_season.addItem("94-95");
		choose_season.addItem("93-94");
		choose_season.addItem("92-93");
		choose_season.addItem("91-92");
		choose_season.addItem("90-91");
		choose_season.addItem("89-90");
		choose_season.addItem("88-89");
		choose_season.addItem("87-88");
		choose_season.addItem("86-87");
		choose_season.addItem("85-86");
		choose_season.setSelectedItem(MainFrame.season);
		panel.add(choose_season);
		
		choose_season.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String season = (String)choose_season.getSelectedItem();
				if(!season.equals("选择赛季")){
					compute = BLController.getInstance();
					compute.setSeason(season);
					MainFrame.season = season;
					MainFrame.seasonChange = true;
					sortTheTeamsOnWinRate();
					updataTeamsRanking();
				}
			}
		});
		
		String[] names1 = new String[]{"","球队", "场数", "胜率(场均)", "胜率(总计)"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("胜率");
		comboBox.addItem("得分");
		comboBox.addItem("助攻");
		comboBox.addItem("抢断");
		comboBox.addItem("盖帽");
		comboBox.addItem("进攻篮板");
		comboBox.addItem("防守篮板");
		comboBox.addItem("投篮命中率");
		comboBox.addItem("三分命中率");
		comboBox.addItem("罚球命中率");
		comboBox.addItem("失误");
		comboBox.addItem("犯规");
	
		comboBox.addItem("总篮板");
		comboBox.addItem("进攻回合");
		comboBox.addItem("进攻效率");
		comboBox.addItem("防守效率");
		comboBox.addItem("进攻篮板效率");
		comboBox.addItem("防守篮板效率");
		comboBox.addItem("抢断效率");
		comboBox.addItem("助攻率");
		comboBox.addItem("投篮命中数");
		comboBox.addItem("投篮出手数");
		comboBox.addItem("三分命中");
		comboBox.addItem("三分出手数");
		comboBox.addItem("罚球命中数");
		comboBox.addItem("罚球出手数");
		
		comboBox.setBounds(750, 15, 150, 30);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					MainFrame.selection2="胜率";
					columnName1.setElementAt("胜率(场均)", 3);
					columnName1.setElementAt("胜率(总计)", 4);
					updataTeamsRanking();
					break;
				case 1: 
					MainFrame.selection2="得分";
					columnName1.setElementAt("得分(场均)", 3);
					columnName1.setElementAt("得分(总计)", 4);
					updataTeamsRanking();
					break;
				case 2: 
					MainFrame.selection2="助攻";
					columnName1.setElementAt("助攻(场均)", 3);
					columnName1.setElementAt("助攻(总计)", 4);
					updataTeamsRanking();
					break;
				case 3: 
					MainFrame.selection2="抢断";
					columnName1.setElementAt("抢断(场均)", 3);
					columnName1.setElementAt("抢断(总计)", 4);
					updataTeamsRanking();
					break;
				case 4: 
					MainFrame.selection2="盖帽";
					columnName1.setElementAt("盖帽(场均)", 3);
					columnName1.setElementAt("盖帽(总计)", 4);
					updataTeamsRanking();
					break;
				case 5: 
					MainFrame.selection2="进攻篮板";
					columnName1.setElementAt("进攻篮板(场均)", 3);
					columnName1.setElementAt("进攻篮板(总计)", 4);
					updataTeamsRanking();
					break;
				case 6: 
					MainFrame.selection2="防守篮板";
					columnName1.setElementAt("防守篮板(场均)", 3);
					columnName1.setElementAt("防守篮板(总计)", 4);
					updataTeamsRanking();
					break;
				case 7: 
					MainFrame.selection2="投篮命中率";
					columnName1.setElementAt("投篮命中率(场均)", 3);
					columnName1.setElementAt("投篮命中率(总计)", 4);
					updataTeamsRanking();
					break;
				case 8: 
					MainFrame.selection2="三分命中率";
					columnName1.setElementAt("三分命中率(场均)", 3);
					columnName1.setElementAt("三分命中率(总计)", 4);
					updataTeamsRanking();
					break;
				case 9: 
					MainFrame.selection2="罚球命中率";
					columnName1.setElementAt("罚球命中率(场均)", 3);
					columnName1.setElementAt("罚球命中率(总计)", 4);
					updataTeamsRanking();
					break;
				case 10: 
					MainFrame.selection2="失误";
					columnName1.setElementAt("失误(场均)", 3);
					columnName1.setElementAt("失误(总计)", 4);
					updataTeamsRanking();
					break;
				case 11: 
					MainFrame.selection2="犯规";
					columnName1.setElementAt("犯规(场均)", 3);
					columnName1.setElementAt("犯规(总计)", 4);
					updataTeamsRanking();
					break;
				case 12: 
					MainFrame.selection2="总篮板";
					columnName1.setElementAt("总篮板(场均)", 3);
					columnName1.setElementAt("总篮板(总计)", 4);
					updataTeamsRanking();
					
					break;
				case 13: 
					MainFrame.selection2="进攻回合";
					columnName1.setElementAt("进攻回合(场均)", 3);
					columnName1.setElementAt("进攻回合(总计)", 4);
					updataTeamsRanking();
					break;
				case 14: 
					MainFrame.selection2="进攻效率";
					columnName1.setElementAt("进攻效率(场均)", 3);
					columnName1.setElementAt("进攻效率(总计)", 4);
					updataTeamsRanking();
					break;
				case 15: 
					MainFrame.selection2="防守效率";
					columnName1.setElementAt("防守效率(场均)", 3);
					columnName1.setElementAt("防守效率(总计)", 4);
					updataTeamsRanking();
					break;
				case 16: 
					MainFrame.selection2="进攻篮板效率";
					columnName1.setElementAt("进攻篮板效率(场均)", 3);
					columnName1.setElementAt("进攻篮板效率(总计)", 4);
					updataTeamsRanking();
					break;
				case 17: 
					MainFrame.selection2="防守篮板效率";
					columnName1.setElementAt("防守篮板效率(场均)", 3);
					columnName1.setElementAt("防守篮板效率(总计)", 4);
					updataTeamsRanking();
					break;
				case 18: 
					MainFrame.selection2="抢断效率";
					columnName1.setElementAt("抢断效率(场均)", 3);
					columnName1.setElementAt("抢断效率(总计)", 4);
					updataTeamsRanking();
					break;
				case 19: 
					MainFrame.selection2="助攻率";
					columnName1.setElementAt("助攻率(场均)", 3);
					columnName1.setElementAt("助攻率(总计)", 4);
					updataTeamsRanking();
					break;
				case 20: 
					MainFrame.selection2 = "投篮命中数";
					columnName1.setElementAt("投篮命中数(场均)", 3);
					columnName1.setElementAt("投篮命中数(总计)", 4);
					updataTeamsRanking();
					break;
				case 21: 
					MainFrame.selection2="投篮出手数";
					columnName1.setElementAt("投篮出手数(场均)", 3);
					columnName1.setElementAt("投篮出手数(总计)", 4);
					updataTeamsRanking();
					break;
				case 22: 
					MainFrame.selection2="三分命中数";
					columnName1.setElementAt("三分命中数(场均)", 3);
					columnName1.setElementAt("三分命中数(总计)", 4);
					updataTeamsRanking();
					break;
				case 23: 
					MainFrame.selection2="三分出手数";
					columnName1.setElementAt("三分出手数(场均)", 3);
					columnName1.setElementAt("三分出手数(总计)", 4);
					updataTeamsRanking();
					break;
				case 24: 
					MainFrame.selection2="罚球命中数";
					columnName1.setElementAt("罚球命中数(场均)", 3);
					columnName1.setElementAt("罚球命中数(总计)", 4);
					updataTeamsRanking();
					break;
				case 25: 
					MainFrame.selection2="罚球出手数";
					columnName1.setElementAt("罚球出手数(场均)", 3);
					columnName1.setElementAt("罚球出手数(总计)", 4);
					updataTeamsRanking();
					break;
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				switch(temp) {
				case HotRankingPanel:
					HotRankingPanel.scrollPane.setVisible(true);
					TeamsRankingFrame.scrollPane.setVisible(false);
					MainFrame.frame.setTitle("今日快讯");
					MainFrame.currentPanel = Panels.HotRankingPanel;
					break;
				case MainFrame:
					MainFrame.panel.setVisible(true);
					TeamsRankingFrame.scrollPane.setVisible(false);
					MainFrame.frame.setTitle("NBA");
					MainFrame.currentPanel = Panels.MainFrame;
					break;
				}
				
			}
		});
		
	}
	
	private static ArrayList<TeamVO> tvoList;
	public static void sortTheTeamsOnWinRate(){
		compute = BLController.getInstance();
		tvoList = compute.getTeamAnalysis();
		for (int i=0;i<tvoList.size();i++){
			for(int j=i+1;j<tvoList.size();j++){
				if(tvoList.get(i).getWinRate() < tvoList.get(j).getWinRate()){
					TeamVO tem = tvoList.get(i);
					tvoList.set(i, tvoList.get(j));
					tvoList.set(j, tem);
	              }
			}
		}
	}
	
	public static void updataTeamsRanking(){
		choose_season.setSelectedItem(MainFrame.season);
		ImageIcon picture;
		Vector rowDatas1 = new Vector();
		for (int i = 0; i < tvoList.size(); i++) {
			Vector rowData1 = new Vector();
			TeamVO tvo = tvoList.get(i);
			int appearance = tvo.getAppearance();
			picture = ImageHandle.loadTeam(tvo.getAbbreviation());
			picture.setImage(picture.getImage().getScaledInstance(70, 70,
					Image.SCALE_DEFAULT));
			rowData1.add(picture);
			rowData1.add(tvo.getFullName());
			rowData1.add(tvo.getAppearance()+"");
			switch(MainFrame.selection2){
			case "投篮命中数":
				rowData1.add(handle((double) tvo.getHit(), appearance));
				rowData1.add(tvo.getHit());
				break;
			case "投篮出手数":
				rowData1.add(handle((double) tvo.getShot(), appearance));
				rowData1.add(tvo.getShot());
				break;
			case "三分命中数":
				rowData1.add(handle((double) tvo.getThirdHit(), appearance));
				rowData1.add(tvo.getThirdHit());
				break;
			case "三分出手数":
				rowData1.add(handle((double) tvo.getThirdshot(), appearance));
				rowData1.add(tvo.getThirdshot());
				break;
			case "罚球命中数":
				rowData1.add(handle((double) tvo.getFreeHit(), appearance));
				rowData1.add(tvo.getFreeHit());
				break;
			case "罚球出手数":
				rowData1.add(handle((double) tvo.getFreeshot(), appearance));
				rowData1.add(tvo.getFreeshot());
				break;
			case "进攻篮板":
				rowData1.add(handle((double) tvo.getOffensiveRebound(),appearance));
				rowData1.add(tvo.getOffensiveRebound());
				break;
			case "防守篮板":
				rowData1.add(handle((double) tvo.getDefensiveRebound(),appearance));
				rowData1.add(tvo.getDefensiveRebound());
				break;
			case "总篮板":
				rowData1.add(handle((double) tvo.getTotalRebound(),appearance));
				rowData1.add(tvo.getTotalRebound());
				break;
			case "助攻":
				rowData1.add(handle((double) tvo.getAssist(), appearance));
				rowData1.add(tvo.getAssist());
				break;
			case "抢断":
				rowData1.add(handle((double) tvo.getSteal(), appearance));
				rowData1.add(tvo.getSteal());
				break;
			case "盖帽":
				rowData1.add(handle((double) tvo.getBlock(), appearance));
				rowData1.add(tvo.getBlock());
				break;
			case "失误":
				rowData1.add(handle((double) tvo.getMiss(), appearance));
				rowData1.add(tvo.getMiss());
				break;
			case "犯规":
				rowData1.add(handle((double) tvo.getFoul(), appearance));
				rowData1.add(tvo.getFoul());
				break;
			case "得分":
				rowData1.add(handle((double) tvo.getScore(), appearance));
				rowData1.add(tvo.getScore());
				break;
			case "投篮命中率":
				rowData1.add(handleDecimal(tvo.getHitRate()*100)+"%");
				rowData1.add(handleDecimal(tvo.getHitRate()*100)+"%");
				break;
			case "三分命中率":
				rowData1.add(handleDecimal(tvo.getThirdHitRate()*100)+"%");
				rowData1.add(handleDecimal(tvo.getThirdHitRate()*100)+"%");
				break;
			case "罚球命中率":
				rowData1.add(handleDecimal(tvo.getFreeHitRate()*100)+"%");
				rowData1.add(handleDecimal(tvo.getFreeHitRate()*100)+"%");
				break;
			case "胜率":
				rowData1.add(handleDecimal(tvo.getWinRate()*100)+"%");
				rowData1.add(handleDecimal(tvo.getWinRate()*100)+"%");
				break;
			case "进攻回合":
				rowData1.add(handle((double) tvo.getOffensiveRound(), appearance));
				rowData1.add(tvo.getOffensiveRound());
				break;
			case "进攻效率":
				rowData1.add(tvo.getOffensiveEfficiency());
				rowData1.add(tvo.getOffensiveEfficiency());
				break;
			case "防守效率":
				rowData1.add(tvo.getDefensiveEfficiency());
				rowData1.add(tvo.getDefensiveEfficiency());
				break;
			case "进攻篮板效率":
				rowData1.add(tvo.getOffensiveReboundEfficiency());
				rowData1.add(tvo.getOffensiveReboundEfficiency());
				break;
			case "防守篮板效率":
				rowData1.add(tvo.getDefensiveReboundEfficiency());
				rowData1.add(tvo.getDefensiveReboundEfficiency());
				break;
			case "抢断效率":
				rowData1.add(tvo.getStealEfficiency());
				rowData1.add(tvo.getStealEfficiency());
				break;
			case "助攻率":
				rowData1.add(tvo.getAssistEfficiency());
				rowData1.add(tvo.getAssistEfficiency());
				break;
			}
			rowDatas1.add(rowData1);
		}
		
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		table.setModel(model_1);
		table.updateUI();
		
	}
	
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				TeamsRankingFrame.scrollPane.setVisible(false);
				String team = getTeamName(table.getValueAt(r,1).toString());
				if(team!=null){
					TeamsSelectionFrame.goToTeam(team);
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		public void mouseEntered(MouseEvent e) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			table.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public TeamWithPlayersVO getTeam(String teamName){
		compute = BLController.getInstance();
		switch(teamName){
		case "Hawks":
			return compute.getTeamAnalysis("ATL");
		case "Nets":
			return compute.getTeamAnalysis("BKN");
		case "Celtics":
			return compute.getTeamAnalysis("BOS");
		case "Hornets":
			return compute.getTeamAnalysis("CHA");
		case "Bulls":
			return compute.getTeamAnalysis("CHI");
		case "Cavaliers":
			return compute.getTeamAnalysis("CLE");
		case "Mavericks":
			return compute.getTeamAnalysis("DAL");
		case "Nuggets":
			return compute.getTeamAnalysis("DEN");
		case "Pistons":
			return compute.getTeamAnalysis("DET");
		case "Warriors":
			return compute.getTeamAnalysis("GSW");
		case "Rockets":
			return compute.getTeamAnalysis("HOU");
		case "Pacers":
			return compute.getTeamAnalysis("IND");
		case "Clippers":
			return compute.getTeamAnalysis("LAC");
		case "Lakers":
			return compute.getTeamAnalysis("LAL");
		case "Grizzlies":
			return compute.getTeamAnalysis("MEM");
		case "Heat":
			return compute.getTeamAnalysis("MIA");
		case "Bucks":
			return compute.getTeamAnalysis("MIL");
		case "Timberwolves":
			return compute.getTeamAnalysis("MIN");
		case "Pelicans":
			return compute.getTeamAnalysis("NOP");
		case "Knicks":
			return compute.getTeamAnalysis("NYK");
		case "Thunder":
			return compute.getTeamAnalysis("OKC");
		case "Magic":
			return compute.getTeamAnalysis("ORL");
		case "76ers":
			return compute.getTeamAnalysis("PHI");
		case "Suns":
			return compute.getTeamAnalysis("PHX");
		case "Trail Blazers":
			return compute.getTeamAnalysis("POR");
		case "Kings":
			return compute.getTeamAnalysis("SAC");
		case "Spurs":
			return compute.getTeamAnalysis("SAS");
		case "Raptors":
			return compute.getTeamAnalysis("TOR");
		case "Jazz":
			return compute.getTeamAnalysis("UTA");
		case "Wizards":
			return compute.getTeamAnalysis("WAS");
		default:
			return null;
		}
		
	}
	
	// 保留小数点
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
	
	public String getTeamName(String teamName){
		switch(teamName){
		case "Hawks":
			return "ATL";
		case "Nets":
			return "BKN";
		case "Celtics":
			return "BOS";
		case "Hornets":
			return "CHA";
		case "Bulls":
			return "CHI";
		case "Cavaliers":
			return "CLE";
		case "Mavericks":
			return "DAL";
		case "Nuggets":
			return "DEN";
		case "Pistons":
			return "DET";
		case "Warriors":
			return "GSW";
		case "Rockets":
			return "HOU";
		case "Pacers":
			return "IND";
		case "Clippers":
			return "LAC";
		case "Lakers":
			return "LAL";
		case "Grizzlies":
			return "MEM";
		case "Heat":
			return "MIA";
		case "Bucks":
			return "MIL";
		case "Timberwolves":
			return "MIN";
		case "Pelicans":
			return "NOP";
		case "Knicks":
			return "NYK";
		case "Thunder":
			return "OKC";
		case "Magic":
			return "ORL";
		case "76ers":
			return "PHI";
		case "Suns":
			return "PHX";
		case "Trail Blazers":
			return "POR";
		case "Kings":
			return "SAC";
		case "Spurs":
			return "SAS";
		case "Raptors":
			return "TOR";
		case "Jazz":
			return "UTA";
		case "Wizards":
			return "WAS";
		default:
			return null;
		}
		
	}
	
}
