package presentation.matchui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerComparePanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.playerui.PlayerInfoPanel.MouseListen;
import presentation.teamsui.TeamsRankingFrame;
import presentation.teamsui.TeamsSelectionFrame;
import server.businesslogic.BLController;
import server.po.MatchPO;
import vo.TeamVO;

import javax.swing.JButton;

public class MatchSelectionPanel extends JPanel {
	public static JScrollPane scrollPane;
	public static JTable table;
	
	private JPanel panelOfBottom = new JPanel();
	private static JComboBox<String> comboBox;
	private JComboBox comboBox_1;
	private JScrollPane scrollPane_1;
	
	MouseListen listener = new MouseListen();
	
	private static DefaultTableModel model_1=new DefaultTableModel();
	private static Vector columnName1;
	
	private static ArrayList<MatchPO> mpoList;
	
	private static BLController compute; 
	
	public MatchSelectionPanel() {
		this.setBounds(0, 100, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 500));
		panelOfBottom.setLayout(null);
		
		String[] names1 = new String[]{"赛季", "日期", "客队", "比分", "主队", "客队最高得分球员", "得分", "主队最高得分球员", "得分"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(250, 50, 100, 30);
		comboBox.addItem("选择赛季");
		comboBox.addItem("15-16");
		comboBox.addItem("14-15");
		comboBox.addItem("13-14");
		comboBox.addItem("12-13");
	/*	comboBox.addItem("11-12");
		comboBox.addItem("10-11");
		comboBox.addItem("09-10");
		comboBox.addItem("08-09");
		comboBox.addItem("07-08");
		comboBox.addItem("06-07");
		comboBox.addItem("05-06");
		comboBox.addItem("04-05");
		comboBox.addItem("03-04");
		comboBox.addItem("02-03");
		comboBox.addItem("01-02");
		comboBox.addItem("00-11");
		comboBox.addItem("99-00");
		comboBox.addItem("98-99");
		comboBox.addItem("97-98");
		comboBox.addItem("96-97");
		comboBox.addItem("95-96");
		comboBox.addItem("94-95");
		comboBox.addItem("93-94");
		comboBox.addItem("92-93");
		comboBox.addItem("91-92");
		comboBox.addItem("90-91");
		comboBox.addItem("89-90");
		comboBox.addItem("88-89");
		comboBox.addItem("87-88");
		comboBox.addItem("86-87");
		comboBox.addItem("85-86");*/
		comboBox.setSelectedItem(MainFrame.season);
		panelOfBottom.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String season = (String)comboBox.getSelectedItem();
				if(!season.equals("选择赛季")){
					compute = BLController.getInstance();
					compute.setSeason(season);
					MainFrame.season = season;
					MainFrame.seasonChange = true;
					update();
				}
			}
		});
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItem("选择月份");
		comboBox_1.addItem(1);
		comboBox_1.addItem(2);
		comboBox_1.addItem(3);
		comboBox_1.addItem(4);
		comboBox_1.addItem(5);
		comboBox_1.addItem(6);
		comboBox_1.addItem(10);
		comboBox_1.addItem(11);
		comboBox_1.addItem(12);
		comboBox_1.setSelectedItem(MainFrame.date+1);
		comboBox_1.setBounds(400, 50, 100, 30);
		panelOfBottom.add(comboBox_1);
		
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				switch(index){
				case 1:
					MainFrame.date=0;
					update();
					break;
				case 2:
					MainFrame.date=1;
					update();
					break;
				case 3:
					MainFrame.date=2;
					update();
					break;
				case 4:
					MainFrame.date=3;
					update();
					break;
				case 5:
					MainFrame.date=4;
					update();
					break;
				case 6:
					MainFrame.date=5;
					update();
					break;
				case 7:
					MainFrame.date=9;
					update();
					break;
				case 8:
					MainFrame.date=10;
					update();
					break;
				case 9:
					MainFrame.date=11;
					update();
					break;
				}
			}
			
		});
		
		table = new JTable();
		table.setModel(model_1);
		table.addMouseListener(listener);
		table.setShowGrid(false);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 100, 800, 400);
		panelOfBottom.add(scrollPane_1);
		
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(100, 50, 100, 30);
		panelOfBottom.add(btnNewButton);
		
		JButton live_bt = new JButton("直播");
		live_bt.setBounds(650, 50, 120, 30);
		panelOfBottom.add(live_bt);
		live_bt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(MatchLivePanel.scrollPane!=null){
					MainFrame.frame.setTitle("直播");
					MatchSelectionPanel.scrollPane.setVisible(false);
					MatchLivePanel.scrollPane.setVisible(true);
					
					MatchLivePanel.t = null;
					MatchLivePanel.t = new Timer();
					MatchLivePanel.t.schedule(new LiveThread(), 0, 5000);
					
					MainFrame.frame.repaint();//刷新重画 
					MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
					MainFrame.backPanels.add(MainFrame.currentPanel);
					MainFrame.currentPanel = Panels.MatchLivePanel;
					
				}
				else{
					MainFrame.frame.setTitle("直播");
					MatchSelectionPanel.scrollPane.setVisible(false);
					MatchLivePanel mlp = new MatchLivePanel();
					MainFrame.frame.getContentPane().add(mlp.scrollPane);
					
					MatchLivePanel.t = null;
					MatchLivePanel.t = new Timer();
					MatchLivePanel.t.schedule(new LiveThread(), 0, 5000);
					
					MainFrame.frame.repaint();//刷新重画 
					MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
					MainFrame.backPanels.add(MainFrame.currentPanel);
					MainFrame.currentPanel = Panels.MatchLivePanel;
				}
				
				
			}
		});
		
		JButton button = new JButton("季后赛");
		button.setBounds(800, 50, 100, 30);
		panelOfBottom.add(button);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MatchSelectionPanel.scrollPane.setVisible(false);
				MatchPlayoffPanel.scrollPane.setVisible(true);

				MainFrame.frame.setTitle("NBA季后赛");
				MainFrame.frame.repaint();//刷新重画 
				MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
				MainFrame.mpp.update();
				MainFrame.backPanels.add(MainFrame.currentPanel);
				MainFrame.currentPanel = Panels.TeamAnalysePanel;

			}
		});
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				switch(temp) {
				case MainFrame:
					MainFrame.panel.setVisible(true);
					MatchSelectionPanel.scrollPane.setVisible(false);
					MainFrame.frame.setTitle("NBA");
					MainFrame.currentPanel = Panels.MainFrame;
					break;
				}
			}
			
		});
	}
	
	public static void update(){
		comboBox.setSelectedItem(MainFrame.season);
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(MainFrame.season) && 
					matchList.get(i).getDate().get(Calendar.MONTH)==MainFrame.date){
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
			rowData1.add(selectedMatchs.get(i).getTeam1().getHighestScore().getNameWithoutNum());
			rowData1.add(selectedMatchs.get(i).getTeam1().getHighestScore().getScore());
			rowData1.add(selectedMatchs.get(i).getTeam2().getHighestScore().getNameWithoutNum());
			rowData1.add(selectedMatchs.get(i).getTeam2().getHighestScore().getScore());
			rowData1.add("详情");
			rowDatas1.add(rowData1);
		}
		
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		int[] width={50,50,50,60,50,150,50,150,50};
		table.setColumnModel(getColumn(table, width));
		table.setModel(model_1);
		table.updateUI();
		
	}
	
	public class MouseListen extends MouseAdapter {
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
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			table.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	private void setMatchInfo(int rowNum){
		compute = BLController.getInstance();
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
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getNameWithoutNum()); 
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
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getNameWithoutNum());
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
		rowData4.add(mpo.getTeam1().getHighestScore().getNameWithoutNum()+" "+
		               mpo.getTeam1().getHighestScore().getScore());
		rowData4.add("得分");
		rowData4.add(mpo.getTeam2().getHighestScore().getNameWithoutNum()+" "+
	               mpo.getTeam2().getHighestScore().getScore());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestRebound().getNameWithoutNum()+" "+
                mpo.getTeam1().getHighestRebound().getTotalRebound());
	    rowData4.add("篮板");
	    rowData4.add(mpo.getTeam2().getHighestRebound().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestRebound().getTotalRebound());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestAssist().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestAssist().getAssist());
        rowData4.add("助攻");
        rowData4.add(mpo.getTeam2().getHighestAssist().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestAssist().getAssist());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestSteal().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestSteal().getSteal());
        rowData4.add("抢断");
        rowData4.add(mpo.getTeam2().getHighestSteal().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestSteal().getSteal());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestBlock().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestBlock().getBlock());
        rowData4.add("盖帽");
        rowData4.add(mpo.getTeam1().getHighestBlock().getNameWithoutNum()+" "+
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
		
		if(MatchSelectionPanel.scrollPane!=null){
			MatchSelectionPanel.scrollPane.setVisible(false);
			MatchDetailInfoPanel mdip = new MatchDetailInfoPanel(ii,model1,model2,model3,model4);
			MainFrame.frame.getContentPane().add(mdip.scrollPane);
			MainFrame.frame.repaint();//刷新重画 
			MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
			MainFrame.frame.setTitle("NBA比赛信息");
			MainFrame.backPanels.add(MainFrame.currentPanel);
			MainFrame.currentPanel = Panels.MatchDetailInfoPanel;
		}
	}
	
	public static void goToMatchFromPlayer(String date, String teamName){
		compute = BLController.getInstance();
		MatchPO mpo = null;
		String[] strArray = new String[3];
		strArray = date.split("-");
		int year = Integer.parseInt(strArray[0]);
		int month = Integer.parseInt(strArray[1]);
		int day = Integer.parseInt(strArray[2]);
		String season = "";
		if(1<=month && month<=6){
			int a=year%10; 
			int b=(year/10)%10;
			int c = 10*b+a;
			season = (c-1)+"-"+c;
		}
		else if(10<=month && month<=12){
			int a=year%10; 
			int b=(year/10)%10;
			int c = 10*b+a;
			season = c+"-"+(c+1);
		}
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(season) &&
				(matchList.get(i).getDate().get(Calendar.MONTH)+1)==month &&
				  (matchList.get(i).getDate().get(Calendar.DAY_OF_MONTH))==day &&
				    (matchList.get(i).getTeam1().getAbbreviation().equals(teamName) || matchList.get(i).getTeam2().getAbbreviation().equals(teamName))
				   ){
				mpo = matchList.get(i);
			}
		}
		if(mpo!=null){
			done(mpo);
		}
	}
	
	public static void done(MatchPO mpo){
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
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getNameWithoutNum());
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
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getNameWithoutNum());
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
		rowData4.add(mpo.getTeam1().getHighestScore().getNameWithoutNum()+" "+
		               mpo.getTeam1().getHighestScore().getScore());
		rowData4.add("得分");
		rowData4.add(mpo.getTeam2().getHighestScore().getNameWithoutNum()+" "+
	               mpo.getTeam2().getHighestScore().getScore());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestRebound().getNameWithoutNum()+" "+
                mpo.getTeam1().getHighestRebound().getTotalRebound());
	    rowData4.add("篮板");
	    rowData4.add(mpo.getTeam2().getHighestRebound().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestRebound().getTotalRebound());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestAssist().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestAssist().getAssist());
        rowData4.add("助攻");
        rowData4.add(mpo.getTeam2().getHighestAssist().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestAssist().getAssist());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestSteal().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestSteal().getSteal());
        rowData4.add("抢断");
        rowData4.add(mpo.getTeam2().getHighestSteal().getNameWithoutNum()+" "+
                       mpo.getTeam2().getHighestSteal().getSteal());
		rowDatas4.add(rowData4);
		rowData4 = new Vector();
		rowData4.add(mpo.getTeam1().getHighestBlock().getNameWithoutNum()+" "+
                       mpo.getTeam1().getHighestBlock().getBlock());
        rowData4.add("盖帽");
        rowData4.add(mpo.getTeam1().getHighestBlock().getNameWithoutNum()+" "+
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
		
		PlayerInfoPanel.scrollPane.setVisible(false);
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
}
