package presentation.playerui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.matchui.MatchDetailInfoPanel;
import presentation.matchui.MatchSelectionPanel;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsRankingFrame;
import presentation.teamsui.TeamsSelectionFrame;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PlayerInfoPanel extends JPanel {
	JPanel panelOfBottom = new JPanel();
	public static JScrollPane scrollPane;
	JLabel labelOfPhoto;	
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	JButton button;
	ImageIcon picture;
	ImageIcon pictureOfAct;
	MouseListen listener = new MouseListen();
	private JTable table_3;
	private JScrollPane scrollPane_5;
	private JTable table_4;

	DefaultTableModel model_1 = new DefaultTableModel();

	DefaultTableModel model_2 = new DefaultTableModel();

	DefaultTableModel model_3 = new DefaultTableModel();

	DefaultTableModel model_8 = new DefaultTableModel();
	
	DefaultTableModel model_avgScore = new DefaultTableModel();
	DefaultTableModel model_avgRebound = new DefaultTableModel();
	DefaultTableModel model_avgAssist = new DefaultTableModel();
	Vector columnName1 = new Vector();
	Vector columnName2 = new Vector();
	Vector columnName3 = new Vector();
	
	Vector columnName8 = new Vector();
	Vector columnName9 = new Vector();
	
	Vector columnName_avgScore = new Vector();
	Vector columnName_avgRebound = new Vector();
	Vector columnName_avgAssist = new Vector();
	DefaultTableModel model_9 = new DefaultTableModel();
	BLService blservice = BLController.getInstance();
	PlayerVO vo;
	public String playerName;
	private JTable table_5;
	private JTable table_6;
	private JTable table_8;
	private JComboBox comboBox_1;
	JComboBox comboBox = new JComboBox();
	private JButton btnNewButton;
	private JTable table_9;
	
	private JTable table_avgScore;
	private JTable table_avgRebound;
	private JTable table_avgAssist;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	
	public PlayerInfoPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);

		//panelOfBottom.setPreferredSize(new Dimension(1000, 590));
		panelOfBottom.setLayout(null);
		scrollPane = new JScrollPane(panelOfBottom);

		labelOfPhoto = new JLabel("photo");
		labelOfPhoto.setBounds(126, 37, 230, 185);
		panelOfBottom.add(labelOfPhoto);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 226, 770, 88);
		panelOfBottom.add(tabbedPane);

		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_2, null);

		table_1 = new JTable(model_1);
		table_1.addMouseListener(new MouseListenTeam());
		table_1.setShowGrid(false);
		table_1.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e) {  
	        int row=table_1.rowAtPoint(e.getPoint());  
	        int col=table_1.columnAtPoint(e.getPoint());  
	        if(col==1){  
	        	table_1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				table_1.setCursor(Cursor.getDefaultCursor());
			}
	    }  }); 
		scrollPane_2.setViewportView(table_1);

		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_3, null);

		table_2 = new JTable(model_2);
		table_2.setShowGrid(false);
		table_2.addMouseListener(new MouseListenTeam());
		table_2.addMouseMotionListener(new MouseAdapter(){public void mouseMoved(MouseEvent e) {  
	        int row=table_2.rowAtPoint(e.getPoint());  
	        int col=table_2.columnAtPoint(e.getPoint());  
	        if(col==1){  
	        	table_2.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				table_2.setCursor(Cursor.getDefaultCursor());
			}
	    }  }); 
		scrollPane_3.setViewportView(table_2);

		button = new JButton("返回");
		button.setBounds(5, 6, 93, 23);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				switch(temp) {
				
				case PlayerSelectionPanel:
					PlayerInfoPanel.scrollPane.setVisible(false);
					PlayerSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员选择");
					MainFrame.currentPanel = Panels.PlayerSelectionPanel;
					break;
				case MatchDetailInfoPanel:
					PlayerInfoPanel.scrollPane.setVisible(false);
					MatchDetailInfoPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA比赛详情");
					MainFrame.currentPanel = Panels.MatchDetailInfoPanel;
					break;
				case MatchSelectionPanel:
					PlayerInfoPanel.scrollPane.setVisible(false);
					MatchSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA比赛查询");
					MainFrame.currentPanel = Panels.MatchSelectionPanel;
					break;
				case PlayerRankingPanel:
					PlayerInfoPanel.scrollPane.setVisible(false);
					PlayerRankingPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员排名");
					MainFrame.currentPanel = Panels.PlayerRankingPanel;
					break;
				case TeamsInfoFrame:
					PlayerInfoPanel.scrollPane.setVisible(false);
					TeamsInfoFrame.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球队信息");
					MainFrame.currentPanel = Panels.TeamsInfoFrame;
					break;
				case HotRankingPanel:
					PlayerInfoPanel.scrollPane.setVisible(false);
					HotRankingPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("今日快讯");
					MainFrame.currentPanel = Panels.HotRankingPanel;
					break;
					
				}
			}

		});
		panelOfBottom.add(button);

		JScrollPane scrollPane_4 = new JScrollPane();

		scrollPane_4.setBounds(5, 350, 770, 58);
		panelOfBottom.add(scrollPane_4);

		table_3 = new JTable(model_3);
		table_3.setShowGrid(false);
		scrollPane_4.setViewportView(table_3);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(5, 448, 703, 129);
		panelOfBottom.add(scrollPane_9);
		String[] cname1 = new String[] {"赛季","球队","出场","首发","时间","投篮","三分",
				"罚球","前篮板","后篮板","总篮板","助攻","抢断","盖帽","失误","犯规","得分"};
		for(int i=0;i<cname1.length;i++) {
			columnName1.add(cname1[i]);
		}	
		String[] cname2 = new String[] {"赛季","球队","出场","首发","时间","投篮","三分",
				"罚球","前篮板","后篮板","总篮板","助攻","抢断","盖帽","失误","犯规","得分","最高分"};
		for(int i=0;i<cname2.length;i++) {
			columnName2.add(cname2[i]);
		}	
		String[] cname3 = new String[] {"赛季","真实命中率", "效率", "GmSc效率", "投篮效率","篮板率",
				"进攻篮板率", "防守篮板率", "盖帽率", "失误率", "使用率", "助攻率", "抢断率"};
		for(int i=0;i<cname3.length;i++) {
			columnName3.add(cname3[i]);
		}	
		String[] cname8 = new String[] {
				"日期", "比赛", "时间", "投篮", "三分", "罚球","前篮板","后篮板","篮板",
				"助攻","抢断","盖帽","失误","犯规"
			};
		for(int i=0;i<cname8.length;i++) {
			columnName8.add(cname8[i]);
		}	
		String[] cname9 = new String[] {"",""};
		for(int i=0;i<cname9.length;i++) {
			columnName9.add(cname9[i]);
		}	
		String[] cname_avgScore = new String[] {"",""};
		for(int i=0;i<cname_avgScore.length;i++) {
			columnName_avgScore.add(cname_avgScore[i]);
		}	
		
		String[] cname_avgRebound = new String[] {"",""};
		for(int i=0;i<cname_avgRebound.length;i++) {
			columnName_avgRebound.add(cname_avgRebound[i]);
		}	
		
		String[] cname_avgAssist = new String[] {"",""};
		for(int i=0;i<cname_avgAssist.length;i++) {
			columnName_avgAssist.add(cname_avgAssist[i]);
		}	
		table_8 = new JTable(model_8);
		table_8.setShowGrid(false);
		table_8.addMouseListener(new MouseListen());
		table_8.setRowHeight(20);
		scrollPane_9.setViewportView(table_8);
		
		JLabel label = new JLabel("最近五场统计");
		label.setBounds(5, 417, 110, 29);
		panelOfBottom.add(label);	
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择赛季","14-15", "13-14","12-13"}));
		comboBox.setBounds(246, 417, 110, 29);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass();
			}
		});
		panelOfBottom.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"选择月份", "01", "02", "03", "04", "10", "11", "12"}));
		comboBox_1.setBounds(366, 417, 110, 29);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass();
			}
		});
		panelOfBottom.add(comboBox_1);
		
		btnNewButton = new JButton("最新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToLatest();
			}
		});
		btnNewButton.setBounds(592, 416, 93, 30);
		panelOfBottom.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("过往查询");
		lblNewLabel.setBounds(174, 417, 64, 29);
		panelOfBottom.add(lblNewLabel);
		
		table_9 = new JTable(model_9);
		table_9.setShowGrid(false);
		table_9.setBounds(387, 10, 285, 228);
		table_9.addMouseListener(new MouseListenTeam9());
		table_9.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e) {  
	        int row=table_9.rowAtPoint(e.getPoint());  
	        int col=table_9.columnAtPoint(e.getPoint());  
	        if(row==1&&col==1){  
	        	table_9.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				table_9.setCursor(Cursor.getDefaultCursor());
			}
	    }  }); 
		panelOfBottom.add(table_9);
		
		JLabel label_1 = new JLabel("高阶数据查询");
		label_1.setBounds(5, 324, 83, 15);
		panelOfBottom.add(label_1);
		
		label_2 = new JLabel("场均得分排名");
		label_2.setBounds(848, 23, 83, 15);
		label_2.addMouseListener(new RankListener());
		panelOfBottom.add(label_2);
		
		table_avgScore = new JTable(model_avgScore);
		table_avgScore.setBounds(787, 48, 190, 130);
		table_avgScore.setShowGrid(false);
		panelOfBottom.add(table_avgScore);
		
		table_avgRebound = new JTable(model_avgRebound);
		table_avgRebound.setBounds(787, 213, 190, 130);
		table_avgRebound.setShowGrid(false);
		panelOfBottom.add(table_avgRebound);
		
		table_avgAssist = new JTable(model_avgAssist);
		table_avgAssist.setBounds(787, 385, 190, 130);
		table_avgAssist.setShowGrid(false);
		panelOfBottom.add(table_avgAssist);
		
		 label_3 = new JLabel("场均篮板排名");
		label_3.setBounds(848, 188, 83, 15);
		label_3.addMouseListener(new RankListener());
		panelOfBottom.add(label_3);
		
		label_4 = new JLabel("场均助攻排名");
		label_4.setBounds(848, 357, 83, 15);
		label_4.addMouseListener(new RankListener());
		panelOfBottom.add(label_4);
		
		//scrollPane.setBounds(0, 0, 990, 600);

		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

	}
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	public void update(String name) {
		playerName = name;
		picture = ImageHandle.loadPlayer(name);
		pictureOfAct = ImageHandle.loadPlayerAct(name);
		labelOfPhoto.setIcon(picture);
		
		vo = blservice.getPlayerAnalysis(name);
		if (vo == null)
			return;

		Vector rowData_1 = new Vector();//场均
		Vector rowDatas_1 = new Vector();
		double num = vo.getAppearance();
		rowData_1.add(vo.getMatches().get(0).getSeason());
		rowData_1.add(MainFrame.psp.translate(vo.getTeamAbbreviation()));		
		rowData_1.add(vo.getAppearance());
		rowData_1.add(vo.getStarting());
		rowData_1.add(handleDecimal((vo.getPlayTime())/num));
		rowData_1.add(handleDecimal(vo.getHitRate()*100)+"%");//投篮命中率
		rowData_1.add(handleDecimal(vo.getThirdHitRate()*100)+"%");//三分命中率
		rowData_1.add(handleDecimal(vo.getFreeHitRate()*100)+"%");//罚球命中率
		rowData_1.add(handleDecimal(vo.getOffensiveRebound() / num));//前篮板
		rowData_1.add(handleDecimal(vo.getDefensiveRebound() / num));//后篮板
		rowData_1.add(handleDecimal(vo.getTotalRebound() / num));
		rowData_1.add(handleDecimal(vo.getAssist() / num));		
		rowData_1.add(handleDecimal(vo.getSteal() / num));
		rowData_1.add(handleDecimal(vo.getBlock() / num));
		rowData_1.add(handleDecimal(vo.getMiss() / num));
		rowData_1.add(handleDecimal(vo.getFoul() / num));
		rowData_1.add(handleDecimal(vo.getScore() / num));		
		rowDatas_1.add(rowData_1);

		model_1.setDataVector(rowDatas_1, columnName1);
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas_1.size());
		table_1.setModel(model_1);
		int[] width_1={10,8,5,5,3,15,18,15,3,3,3,3,3,3,3,3,3};
		table_1.setColumnModel(getColumn(table_1, width_1));
		table_1.updateUI();

		Vector rowData_2 = new Vector();//总计
		Vector rowDatas_2 = new Vector();
		rowData_2.add(vo.getMatches().get(0).getSeason());
		rowData_2.add(MainFrame.psp.translate(vo.getTeamAbbreviation()));		
		rowData_2.add(vo.getAppearance());
		rowData_2.add(vo.getStarting());
		rowData_2.add(vo.getPlayTime());
		rowData_2.add(vo.getHit()+"-"+vo.getShot());//投篮
		rowData_2.add(vo.getThirdHit()+"-"+vo.getThirdshot());//三分
		rowData_2.add(vo.getFreeHit()+"-"+vo.getFreeshot());//罚球
		rowData_2.add(vo.getOffensiveRebound());//前篮板
		rowData_2.add(vo.getDefensiveRebound());//后篮板
		rowData_2.add(vo.getTotalRebound());
		rowData_2.add(vo.getAssist());		
		rowData_2.add(vo.getSteal());
		rowData_2.add(vo.getBlock());
		rowData_2.add(vo.getMiss());
		rowData_2.add(vo.getFoul());
		rowData_2.add(vo.getScore());		
		rowData_2.add(vo.getHighestScore());
		rowDatas_2.add(rowData_2);

		model_2.setDataVector(rowDatas_2, columnName2);
		model_2.setColumnCount(table_2.getColumnCount());
		model_2.setRowCount(rowDatas_2.size());
		table_2.setModel(model_2);
		int[] width_2={40,30,30,30,40,58,55,55,40,40,40,40,40,40,40,40,40,40};
		table_2.setColumnModel(getColumn(table_2, width_2));
		table_2.updateUI();

		Vector rowData_3 = new Vector();
		Vector rowDatas_3 = new Vector();
		rowData_3.add(vo.getMatches().get(0).getSeason());
		rowData_3.add(handleDecimal(vo.getRealHitRate()*100)+"%");
		rowData_3.add(vo.getEfficiency());
		rowData_3.add(handleDecimal(vo.getGmScEfficiency()));
		rowData_3.add(handleDecimal(vo.getShotEfficiency()));
		rowData_3.add(handleDecimal(vo.getReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getOffensiveReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getDefensiveReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getBlockRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getMissRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getUseRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getAssistRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getStealRate()*100)+"%");		
		
		
		
		
		rowDatas_3.add(rowData_3);

		model_3.setDataVector(rowDatas_3, columnName3);
		model_3.setColumnCount(table_3.getColumnCount());
		model_3.setRowCount(rowDatas_3.size());
		table_3.setModel(model_3);
     	int[] width_3={50,70,50,70,60,70,70,70,50,50,50,50};
		table_3.setColumnModel(getColumn(table_3, width_3));
		table_3.updateUI();

		
		Vector rowDatas8 = new Vector();
		ArrayList<MatchPO> matches = vo.getMatches();
		for(int i=matches.size()-1;i>=0&&i>matches.size()-6;i--) {
			Vector rowData8 = new Vector();
			MatchPO matchTemp = matches.get(i);
			TeamInMatchesPO team = null;
			if(matchTemp.getTeam1().getAbbreviation().equals
					(vo.getTeamAbbreviation())){
				team = matchTemp.getTeam1();
			} else {
				team = matchTemp.getTeam2();
			}
			ArrayList<PlayerInMatchesPO> playersInMatch = team.getPlayers();
			PlayerInMatchesPO playerTemp = null; 
			for(int j=0;j<playersInMatch.size();j++) {
				playerTemp = playersInMatch.get(j);
				if(playerTemp.getName().equals(vo.getName())) break; 
			}
			
			SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr8 = sdf8.format(matchTemp.getDate().getTime());
			rowData8.add(dateStr8);//日期
			if(vo.getTeamAbbreviation().equals(matchTemp.getTeam1().getAbbreviation()))
				rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam2().getAbbreviation());//得分
			else {
				String[] score=matchTemp.getFinalScore().toString().split("-");
				rowData8.add(score[1]+"-"+score[0]+" "+					
					matchTemp.getTeam1().getAbbreviation());//得分
			}
			rowData8.add(playerTemp.getPlayTime());
			rowData8.add(playerTemp.getHit()+"-"+playerTemp.getShot());
			rowData8.add(playerTemp.getThirdHit()+"-"+playerTemp.getThirdshot());
			rowData8.add(playerTemp.getFreeHit()+"-"+playerTemp.getFreeshot());
			rowData8.add(playerTemp.getOffensiveRebound());
			rowData8.add(playerTemp.getDefensiveRebound());
			rowData8.add(playerTemp.getTotalRebound());
			rowData8.add(playerTemp.getAssist());
			rowData8.add(playerTemp.getSteal());
			rowData8.add(playerTemp.getBlock());
			rowData8.add(playerTemp.getMiss());
			rowData8.add(playerTemp.getFoul());
			rowData8.add(playerTemp.getScore());
			
			rowDatas8.add(rowData8);
		}
		
		model_8.setDataVector(rowDatas8, columnName8);		
		model_8.setColumnCount(table_8.getColumnCount());
		model_8.setRowCount(rowDatas8.size());
		table_8.setModel(model_8);
		int[] width_8={50,60,5,3,3,3,3,3,3,3,3,3,3,3};
		table_8.setColumnModel(getColumn(table_8, width_8));
		table_8.updateUI();
		
		Vector rowDatas9 = new Vector();
		
			Vector rowData9 = new Vector();			
			rowData9.add("姓名");rowData9.add(vo.getName());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("球队");rowData9.add(MainFrame.psp.translate(vo.getTeamAbbreviation()));
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("号码");rowData9.add(vo.getNumber());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("位置");rowData9.add(vo.getPosition());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("身高");rowData9.add(vo.getHeight());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("体重");rowData9.add(vo.getWeight());
			rowDatas9.add(rowData9);
				
			rowData9 = new Vector();
			SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
			rowData9.add("生日");rowData9.add(sdf9.format(vo.getBirth().getTime()));
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("年龄");rowData9.add(vo.getAge());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("球龄"); rowData9.add(vo.getExp());
			rowDatas9.add(rowData9);
			
			rowData9 = new Vector();	
			rowData9.add("毕业学校");rowData9.add(vo.getSchool());
			rowDatas9.add(rowData9);
			
			
		
	//notice	
		model_9.setDataVector(rowDatas9, columnName9);		
		model_9.setColumnCount(table_9.getColumnCount());
		model_9.setRowCount(rowDatas9.size());
		table_9.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_9.setModel(model_9);
		int[] width_9={30,60};
		table_9.setColumnModel(getColumn(table_9, width_9));
		table_9.updateUI();
		
		Vector rowDatas_avgScore = new Vector();
		ArrayList<Player> players =  BLController.getInstance().getPlayers();
		Collections.sort(players,Comparators.getPlayerAvgComparator("point"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgScore = new Vector();
				if(i-2>=0){	
					temp = players.get(i-2);
					rowData_avgScore.add((i+1-2)+"."+temp.getName());
					rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
					rowDatas_avgScore.add(rowData_avgScore);
				}
				if(i-1>=0){
					rowData_avgScore = new Vector();
					temp = players.get(i-1);
					rowData_avgScore.add((i+1-1)+"."+temp.getName());
					rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
					rowDatas_avgScore.add(rowData_avgScore);
				}
				rowData_avgScore = new Vector();
				temp = players.get(i);
				rowData_avgScore.add((i+1)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i+1);
				rowData_avgScore.add((i+2)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i+2);
				rowData_avgScore.add((i+3)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				break;
			}
			
			
		}
		
		model_avgScore.setDataVector(rowDatas_avgScore, columnName_avgScore);		
		model_avgScore.setColumnCount(table_avgScore.getColumnCount());
		model_avgScore.setRowCount(rowDatas_avgScore.size());
		table_avgScore.setModel(model_avgScore);
		table_avgScore.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgScore={150,40};
		table_avgScore.setColumnModel(getColumn(table_avgScore, width_avgScore));
		table_avgScore.updateUI();
		
		Vector rowDatas_avgRebound = new Vector();
		
		Collections.sort(players,Comparators.getPlayerAvgComparator("rebound"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgRebound = new Vector();
				if(i-2>=0){	
					temp = players.get(i-2);
					rowData_avgRebound.add((i+1-2)+"."+temp.getName());
					rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
					rowDatas_avgRebound.add(rowData_avgRebound);
				}
				if(i-1>=0){
					rowData_avgRebound = new Vector();
					temp = players.get(i-1);
					rowData_avgRebound.add((i+1-1)+"."+temp.getName());
					rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
					rowDatas_avgRebound.add(rowData_avgRebound);
				}
				rowData_avgRebound = new Vector();
				temp = players.get(i);
				rowData_avgRebound.add((i+1)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				rowData_avgRebound = new Vector();
				temp = players.get(i+1);
				rowData_avgRebound.add((i+2)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				rowData_avgRebound = new Vector();
				temp = players.get(i+2);
				rowData_avgRebound.add((i+3)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				break;
			}
			
			
		}
		
		model_avgRebound.setDataVector(rowDatas_avgRebound, columnName_avgRebound);		
		model_avgRebound.setColumnCount(table_avgRebound.getColumnCount());
		model_avgRebound.setRowCount(rowDatas_avgRebound.size());
		table_avgRebound.setModel(model_avgRebound);
		table_avgRebound.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgRebound={150,40};
		table_avgRebound.setColumnModel(getColumn(table_avgRebound, width_avgRebound));
		table_avgRebound.updateUI();
		
		Vector rowDatas_avgAssist = new Vector();
		Collections.sort(players,Comparators.getPlayerAvgComparator("assist"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgAssist = new Vector();
				if(i-2>=0){	
					temp = players.get(i-2);
					rowData_avgAssist.add((i+1-2)+"."+temp.getName());
					rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
					rowDatas_avgAssist.add(rowData_avgAssist);
				}
				if(i-1>=0){
					rowData_avgAssist = new Vector();
					temp = players.get(i-1);
					rowData_avgAssist.add((i+1-1)+"."+temp.getName());
					rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
					rowDatas_avgAssist.add(rowData_avgAssist);
				}
				rowData_avgAssist = new Vector();
				temp = players.get(i);
				rowData_avgAssist.add((i+1)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				rowData_avgAssist = new Vector();
				temp = players.get(i+1);
				rowData_avgAssist.add((i+2)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				rowData_avgAssist = new Vector();
				temp = players.get(i+2);
				rowData_avgAssist.add((i+3)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				break;
			}
		}
		
		model_avgAssist.setDataVector(rowDatas_avgAssist, columnName_avgAssist);		
		model_avgAssist.setColumnCount(table_avgAssist.getColumnCount());
		model_avgAssist.setRowCount(rowDatas_avgAssist.size());
		table_avgAssist.setModel(model_avgAssist);
		table_avgAssist.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgAssist={150,40};
		table_avgAssist.setColumnModel(getColumn(table_avgAssist, width_avgAssist));
		table_avgAssist.updateUI();
	}
	public void backToLatest(){
		Vector rowDatas8 = new Vector();
		ArrayList<MatchPO> matches = vo.getMatches();
		for(int i=matches.size()-1;i>=0&&i>matches.size()-6;i--) {
			Vector rowData8 = new Vector();
			MatchPO matchTemp = matches.get(i);
			TeamInMatchesPO team = null;
			if(matchTemp.getTeam1().getAbbreviation().equals
					(vo.getTeamAbbreviation())){
				team = matchTemp.getTeam1();
			} else {
				team = matchTemp.getTeam2();
			}
			ArrayList<PlayerInMatchesPO> playersInMatch = team.getPlayers();
			PlayerInMatchesPO playerTemp = null; 
			for(int j=0;j<playersInMatch.size();j++) {
				playerTemp = playersInMatch.get(j);
				if(playerTemp.getName().equals(vo.getName())) break; 
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr8 = sdf.format(matchTemp.getDate().getTime());
			rowData8.add(dateStr8);//日期
			if(vo.getTeamAbbreviation().equals(matchTemp.getTeam1().getAbbreviation()))
				rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam2().getAbbreviation());//得分
			else rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam1().getAbbreviation());//得分
			rowData8.add(playerTemp.getPlayTime());
			rowData8.add(playerTemp.getHit()+"-"+playerTemp.getShot());
			rowData8.add(playerTemp.getThirdHit()+"-"+playerTemp.getThirdshot());
			rowData8.add(playerTemp.getFreeHit()+"-"+playerTemp.getFreeshot());
			rowData8.add(playerTemp.getOffensiveRebound());
			rowData8.add(playerTemp.getDefensiveRebound());
			rowData8.add(playerTemp.getTotalRebound());
			rowData8.add(playerTemp.getAssist());
			rowData8.add(playerTemp.getSteal());
			rowData8.add(playerTemp.getBlock());
			rowData8.add(playerTemp.getMiss());
			rowData8.add(playerTemp.getFoul());
			rowData8.add(playerTemp.getScore());
			
			rowDatas8.add(rowData8);
		}
		
		model_8.setDataVector(rowDatas8, columnName8);		
		model_8.setColumnCount(table_8.getColumnCount());
		model_8.setRowCount(rowDatas8.size());
		table_8.setModel(model_8);
		int[] width={50,60,5,3,3,3,3,3,3,3,3,3,3,3};
		table_8.setColumnModel(getColumn(table_8, width));
		table_8.updateUI();
	}
	public void refresh(String name) {					
		vo = blservice.getPlayerAnalysis(name);
		if (vo == null)
			return;

		Vector rowData_1 = new Vector();//场均
		Vector rowDatas_1 = new Vector();
		double num = vo.getAppearance();
		rowData_1.add(vo.getMatches().get(0).getSeason());
		rowData_1.add(MainFrame.psp.translate(vo.getTeamAbbreviation()));		
		rowData_1.add(vo.getAppearance());
		rowData_1.add(vo.getStarting());
		rowData_1.add(handleDecimal((vo.getPlayTime())/num));
		rowData_1.add(handleDecimal(vo.getHitRate()*100)+"%");//投篮命中率
		rowData_1.add(handleDecimal(vo.getThirdHitRate()*100)+"%");//三分命中率
		rowData_1.add(handleDecimal(vo.getFreeHitRate()*100)+"%");//罚球命中率
		rowData_1.add(handleDecimal(vo.getOffensiveRebound() / num));//前篮板
		rowData_1.add(handleDecimal(vo.getDefensiveRebound() / num));//后篮板
		rowData_1.add(handleDecimal(vo.getTotalRebound() / num));
		rowData_1.add(handleDecimal(vo.getAssist() / num));		
		rowData_1.add(handleDecimal(vo.getSteal() / num));
		rowData_1.add(handleDecimal(vo.getBlock() / num));
		rowData_1.add(handleDecimal(vo.getMiss() / num));
		rowData_1.add(handleDecimal(vo.getFoul() / num));
		rowData_1.add(handleDecimal(vo.getScore() / num));		
		rowDatas_1.add(rowData_1);

		model_1.setDataVector(rowDatas_1, columnName1);
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas_1.size());
		table_1.setModel(model_1);
		int[] width_1={10,8,5,5,3,15,18,15,3,3,3,3,3,3,3,3,3};
		table_1.setColumnModel(getColumn(table_1, width_1));
		table_1.updateUI();

		Vector rowData_2 = new Vector();//总计
		Vector rowDatas_2 = new Vector();
		rowData_2.add(vo.getMatches().get(0).getSeason());
		rowData_2.add(MainFrame.psp.translate(vo.getTeamAbbreviation()));		
		rowData_2.add(vo.getAppearance());
		rowData_2.add(vo.getStarting());
		rowData_2.add(vo.getPlayTime());
		rowData_2.add(vo.getHit()+"-"+vo.getShot());//投篮
		rowData_2.add(vo.getThirdHit()+"-"+vo.getThirdshot());//三分
		rowData_2.add(vo.getFreeHit()+"-"+vo.getFreeshot());//罚球
		rowData_2.add(vo.getOffensiveRebound());//前篮板
		rowData_2.add(vo.getDefensiveRebound());//后篮板
		rowData_2.add(vo.getTotalRebound());
		rowData_2.add(vo.getAssist());
		rowData_2.add(vo.getSteal());
		rowData_2.add(vo.getBlock());
		rowData_2.add(vo.getMiss());
		rowData_2.add(vo.getFoul());
		rowData_2.add(vo.getScore());
		rowData_2.add(vo.getHighestScore());
		rowDatas_2.add(rowData_2);

		model_2.setDataVector(rowDatas_2, columnName2);
		model_2.setColumnCount(table_2.getColumnCount());
		model_2.setRowCount(rowDatas_2.size());
		table_2.setModel(model_2);
		int[] width_2={40,30,30,30,40,58,55,55,40,40,40,40,40,40,40,40,40,40};
		table_2.setColumnModel(getColumn(table_2, width_2));
		table_2.updateUI();

		Vector rowData_3 = new Vector();
		Vector rowDatas_3 = new Vector();
		rowData_3.add(vo.getMatches().get(0).getSeason());
		rowData_3.add(handleDecimal(vo.getRealHitRate()*100)+"%");
		rowData_3.add(vo.getEfficiency());
		rowData_3.add(handleDecimal(vo.getGmScEfficiency()));
		rowData_3.add(handleDecimal(vo.getShotEfficiency()));
		rowData_3.add(handleDecimal(vo.getReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getOffensiveReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getDefensiveReboundRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getBlockRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getMissRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getUseRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getAssistRate()*100)+"%");
		rowData_3.add(handleDecimal(vo.getStealRate()*100)+"%");		
		rowDatas_3.add(rowData_3);

		model_3.setDataVector(rowDatas_3, columnName3);
		model_3.setColumnCount(table_3.getColumnCount());
		model_3.setRowCount(rowDatas_3.size());
		table_3.setModel(model_3);
     	int[] width_3={50,70,50,70,60,70,70,70,50,50,50,50};
		table_3.setColumnModel(getColumn(table_3, width_3));
		table_3.updateUI();
		Vector rowDatas_avgScore = new Vector();
		ArrayList<Player> players =  BLController.getInstance().getPlayers();
		Collections.sort(players,Comparators.getPlayerAvgComparator("point"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgScore = new Vector();
				temp = players.get(i-2);
				rowData_avgScore.add((i+1-2)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i-1);
				rowData_avgScore.add((i+1-1)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i);
				rowData_avgScore.add((i+1)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i+1);
				rowData_avgScore.add((i+2)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				rowData_avgScore = new Vector();
				temp = players.get(i+2);
				rowData_avgScore.add((i+3)+"."+temp.getName());
				rowData_avgScore.add(handleDecimal((double)temp.getScore()/temp.getAppearance()));
				rowDatas_avgScore.add(rowData_avgScore);
				
				break;
			}
			
		}
		
		model_avgScore.setDataVector(rowDatas_avgScore, columnName_avgScore);		
		model_avgScore.setColumnCount(table_avgScore.getColumnCount());
		model_avgScore.setRowCount(rowDatas_avgScore.size());
		table_avgScore.setModel(model_avgScore);
		table_avgScore.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgScore={150,40};
		table_avgScore.setColumnModel(getColumn(table_avgScore, width_avgScore));
		table_avgScore.updateUI();
		
		Vector rowDatas_avgRebound = new Vector();
		Collections.sort(players,Comparators.getPlayerAvgComparator("rebound"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgRebound = new Vector();
				if(i-2>=0){	
					temp = players.get(i-2);
					rowData_avgRebound.add((i+1-2)+"."+temp.getName());
					rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
					rowDatas_avgRebound.add(rowData_avgRebound);
				}
				if(i-1>=0){
					rowData_avgRebound = new Vector();
					temp = players.get(i-1);
					rowData_avgRebound.add((i+1-1)+"."+temp.getName());
					rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
					rowDatas_avgRebound.add(rowData_avgRebound);
				}
				rowData_avgRebound = new Vector();
				temp = players.get(i);
				rowData_avgRebound.add((i+1)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				rowData_avgRebound = new Vector();
				temp = players.get(i+1);
				rowData_avgRebound.add((i+2)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				rowData_avgRebound = new Vector();
				temp = players.get(i+2);
				rowData_avgRebound.add((i+3)+"."+temp.getName());
				rowData_avgRebound.add(handleDecimal((double)temp.getRebound()/temp.getAppearance()));
				rowDatas_avgRebound.add(rowData_avgRebound);
				
				break;
			}
		}
		
		model_avgRebound.setDataVector(rowDatas_avgRebound, columnName_avgRebound);		
		model_avgRebound.setColumnCount(table_avgRebound.getColumnCount());
		model_avgRebound.setRowCount(rowDatas_avgRebound.size());
		table_avgRebound.setModel(model_avgRebound);
		table_avgRebound.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgRebound={150,40};
		table_avgRebound.setColumnModel(getColumn(table_avgRebound, width_avgRebound));
		table_avgRebound.updateUI();
		
		Vector rowDatas_avgAssist = new Vector();
		Collections.sort(players,Comparators.getPlayerAvgComparator("assist"));
		for(int i=0;i<players.size();i++) {
			
			Player temp = players.get(i);
			if(vo.getName().equals(temp.getName())) {
				Vector rowData_avgAssist = new Vector();
				if(i-2>=0){	
					temp = players.get(i-2);
					rowData_avgAssist.add((i+1-2)+"."+temp.getName());
					rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
					rowDatas_avgAssist.add(rowData_avgAssist);
				}
				if(i-1>=0){
					rowData_avgAssist = new Vector();
					temp = players.get(i-1);
					rowData_avgAssist.add((i+1-1)+"."+temp.getName());
					rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
					rowDatas_avgAssist.add(rowData_avgAssist);
				}
				rowData_avgAssist = new Vector();
				temp = players.get(i);
				rowData_avgAssist.add((i+1)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				rowData_avgAssist = new Vector();
				temp = players.get(i+1);
				rowData_avgAssist.add((i+2)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				rowData_avgAssist = new Vector();
				temp = players.get(i+2);
				rowData_avgAssist.add((i+3)+"."+temp.getName());
				rowData_avgAssist.add(handleDecimal((double)temp.getAssist()/temp.getAppearance()));
				rowDatas_avgAssist.add(rowData_avgAssist);
				
				break;
			}
		}
		
		model_avgAssist.setDataVector(rowDatas_avgAssist, columnName_avgAssist);		
		model_avgAssist.setColumnCount(table_avgAssist.getColumnCount());
		model_avgAssist.setRowCount(rowDatas_avgAssist.size());
		table_avgAssist.setModel(model_avgAssist);
		table_avgAssist.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		int[] width_avgAssist={150,40};
		table_avgAssist.setColumnModel(getColumn(table_avgAssist, width_avgAssist));
		table_avgAssist.updateUI();
	}
	public void showPass() {
		Vector rowDatas8 = new Vector();
		ArrayList<MatchPO> matches = vo.getMatches();
		
		for(int i=0;i<matches.size();i++) {
			Vector rowData8 = new Vector();
			MatchPO matchTemp = matches.get(i);
			SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr8 = sdf8.format(matchTemp.getDate().getTime());
			String[] time =  dateStr8.split("-");
			String season = (String) comboBox.getSelectedItem();
			String month = (String) comboBox_1.getSelectedItem();
			if((!matchTemp.getSeason().equals(season))||(!month.equals(time[1]))) continue;
			TeamInMatchesPO team = null;
			
			if(matchTemp.getTeam1().getAbbreviation().equals
					(vo.getTeamAbbreviation())){
				team = matchTemp.getTeam1();
			} else {
				team = matchTemp.getTeam2();
			}
			ArrayList<PlayerInMatchesPO> playersInMatch = team.getPlayers();
			PlayerInMatchesPO playerTemp = null; 
			for(int j=0;j<playersInMatch.size();j++) {
				playerTemp = playersInMatch.get(j);
				if(playerTemp.getName().equals(vo.getName())) break; 
			}			
			
			rowData8.add(dateStr8);//日期
			if(vo.getTeamAbbreviation().equals(matchTemp.getTeam1().getAbbreviation()))
				rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam2().getAbbreviation());//得分
			else rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam1().getAbbreviation());//得分
			rowData8.add(playerTemp.getPlayTime());
			rowData8.add(playerTemp.getHit()+"-"+playerTemp.getShot());
			rowData8.add(playerTemp.getThirdHit()+"-"+playerTemp.getThirdshot());
			rowData8.add(playerTemp.getFreeHit()+"-"+playerTemp.getFreeshot());
			rowData8.add(playerTemp.getOffensiveRebound());
			rowData8.add(playerTemp.getDefensiveRebound());
			rowData8.add(playerTemp.getTotalRebound());
			rowData8.add(playerTemp.getAssist());
			rowData8.add(playerTemp.getSteal());
			rowData8.add(playerTemp.getBlock());
			rowData8.add(playerTemp.getMiss());
			rowData8.add(playerTemp.getFoul());
			rowData8.add(playerTemp.getScore());
			
			rowDatas8.add(rowData8);
		}
		
		model_8.setDataVector(rowDatas8, columnName8);		
		model_8.setColumnCount(table_8.getColumnCount());
		model_8.setRowCount(rowDatas8.size());
		table_8.setModel(model_8);
		int[] width={50,60,5,3,3,3,3,3,3,3,3,3,3,3};
		table_8.setColumnModel(getColumn(table_8, width));
		table_8.updateUI();
		
		
	}
	// 保留小数点
	public String handleDecimal(double f) {
		return String.format("%.1f", f);
	}

	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
 
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			String date = (String) table.getValueAt(r, 0);
			String teamName = (String) table.getValueAt(r, 1);
			try {
				
				MainFrame.frame.setTitle("NBA比赛信息");
					MatchSelectionPanel.goToMatchFromPlayer(date,
							teamName.split(" ")[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			table.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));

		}

		@Override
		public void mouseExited(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			table.setCursor(Cursor.getDefaultCursor());

		}
	}
	public class MouseListenTeam extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			String teamName = (String) table.getValueAt(r, 1);
			if(c==1){	
				try {				
						PlayerInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("NBA球队信息");
						TeamsSelectionFrame.goToTeam(vo.getTeamAbbreviation());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public class MouseListenTeam9 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			String teamName = (String) table.getValueAt(r, 1);
			if(c==1&&r==1){	
				try {				
						PlayerInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("NBA球队信息");
						TeamsSelectionFrame.goToTeam(vo.getTeamAbbreviation());
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public class RankListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JLabel label = (JLabel) e.getSource();
			PlayerInfoPanel.scrollPane.setVisible(false);
			MainFrame.setPlayersRanking();			
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setCursor(Cursor.getDefaultCursor());
		}
	}
}
