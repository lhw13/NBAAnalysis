package presentation.matchui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;

public class MatchLivePanel extends JPanel{

	public static JScrollPane scrollPane;

	JPanel panelOfBottom = new JPanel();

	private static JScrollPane sp;
	public static JTextArea jtext;

	JButton return_bt;

	public static Timer t;

	private static JLabel team_1;
	private static JLabel team_2;

	private static JLabel team_1_score;
	private static JLabel team_2_score;
	private static JLabel team_1_name;
	private static JLabel team_2_name;
	private static JLabel team_1_state;
	private static JLabel team_2_state;

	private static DefaultTableModel model=new DefaultTableModel();
	private static Vector columnName;
	private static JTable table;
	
	private JScrollPane scrollPane_1;
	private static JTable table_1;
	private static DefaultTableModel model_1=new DefaultTableModel();
	private static Vector columnName_1;
	private JScrollPane scrollPane_2;
	private static JTable table_2;
	private static DefaultTableModel model_2=new DefaultTableModel();
	private static Vector columnName_2;

	JLabel live_label;
	JLabel data_label;
	
	public static int live_or_data = 0;

	public MatchLivePanel(){

		this.setBounds(0, 0, 1000, 600);
		setLayout(null);

		//scrollPane============================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

		panelOfBottom.setPreferredSize(new Dimension(1000, 950));
		panelOfBottom.setLayout(null);

		//返回===================================================
		return_bt = new JButton("返回");
		return_bt.setBounds(10, 20, 100, 30);
		return_bt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				MainFrame.backPanels.remove(size-1);

				MatchLivePanel.scrollPane.setVisible(false);
				MatchSelectionPanel.scrollPane.setVisible(true);
				MainFrame.frame.setTitle("NBA比赛查询");
				MainFrame.currentPanel = Panels.MatchSelectionPanel;

				t.cancel();
			}
		});
		panelOfBottom.add(return_bt);

		//label========================================================
		int width = 200;
		int height = 150;
		ImageIcon[] ii=new ImageIcon[2];
		ii[0] = ImageHandle.loadTeam("GSW");
		ii[0].setImage(ii[0].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		ii[1] = ImageHandle.loadTeam("CLE");
		ii[1].setImage(ii[1].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));

		team_1 = new JLabel("");
		team_1.setBounds(50, 35, 200, 150);
		team_1.setIcon(ii[0]);
		panelOfBottom.add(team_1);

		team_2 = new JLabel("");
		team_2.setBounds(750, 35, 200, 150);
		team_2.setIcon(ii[1]);
		panelOfBottom.add(team_2);

		team_1_score = new JLabel("108");
		team_1_score.setBounds(300, 0, 150, 120);
		team_1_score.setFont(new Font("黑体",Font.PLAIN,50));
		panelOfBottom.add(team_1_score);

		team_2_score = new JLabel("100");
		team_2_score.setBounds(600, 0, 150, 120);
		team_2_score.setFont(new Font("黑体",Font.PLAIN,50));
		panelOfBottom.add(team_2_score);

		team_1_name = new JLabel("勇士(1)");
		team_1_name.setBounds(300, 60, 150, 150);
		team_1_name.setFont(new Font("黑体",Font.PLAIN,20));
		panelOfBottom.add(team_1_name);

		team_2_name = new JLabel("骑士(1)");
		team_2_name.setBounds(600, 60, 150, 150);
		team_2_name.setFont(new Font("黑体",Font.PLAIN,20));
		panelOfBottom.add(team_2_name);

		team_1_state = new JLabel("主队（67胜15负）");
		team_1_state.setBounds(300, 80, 150, 150);
		team_1_state.setFont(new Font("黑体",Font.PLAIN,15));
		panelOfBottom.add(team_1_state);

		team_2_state = new JLabel("客队（53胜29负）");
		team_2_state.setBounds(600, 80, 150, 150);
		team_2_state.setFont(new Font("黑体",Font.PLAIN,15));
		panelOfBottom.add(team_2_state);

		live_label = new JLabel("文字直播");
		live_label.setBounds(150, 230, 100, 50);
		live_label.setFont(new Font("黑体",Font.PLAIN,25));
		panelOfBottom.add(live_label);
		live_label.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				live_or_data = 1;
				updateLiveInfo();
				sp.setVisible(true);
				scrollPane_1.setVisible(false);
				scrollPane_2.setVisible(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				live_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				live_label.setCursor(Cursor.getDefaultCursor());
			}
		});

		data_label = new JLabel("数据统计");
		data_label.setBounds(750, 230, 100, 50);
		data_label.setFont(new Font("黑体",Font.PLAIN,25));
		panelOfBottom.add(data_label);
		data_label.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				live_or_data = -1;
				updateTheData();
				sp.setVisible(false);
				scrollPane_1.setVisible(true);
				scrollPane_2.setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				data_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				data_label.setCursor(Cursor.getDefaultCursor());
			}
		});

		//分节比分=================================================
		table = new JTable();
		table.setModel(model);
		table.setShowGrid(false);
		table.setBounds(300, 180, 400, 100);
		panelOfBottom.add(table);
		
		//实时数据=================================================
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 280, 800, 300);
		table_1 = new JTable();
		table_1.setModel(model_1);
		table_1.setShowGrid(false);
		scrollPane_1.setViewportView(table_1);
		panelOfBottom.add(scrollPane_1);
		scrollPane_1.setVisible(false);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 600, 800, 300);
		table_2 = new JTable();
		table_2.setModel(model_2);
		table_2.setShowGrid(false);
		scrollPane_2.setViewportView(table_2);
		panelOfBottom.add(scrollPane_2);
		scrollPane_2.setVisible(false);

		//文字直播=================================================
		jtext = new JTextArea(10, 20);
		jtext.setLineWrap(true);
		jtext.setWrapStyleWord(true);
		jtext.setBackground(Color.LIGHT_GRAY);
		jtext.setEditable(false);
		jtext.setFont(new Font("黑体",Font.PLAIN,25));
		jtext.setForeground(Color.BLACK);
		sp = new JScrollPane(jtext);
		sp.setBounds(150,280,700,650);
		panelOfBottom.add(sp);
		
		try{
			Thread.sleep(1000);
			MatchLivePanel.updateLabel();
			MatchLivePanel.updateLiveInfo();
			MatchLivePanel.updateTheData();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}
	
	public static void updateLabel(){
		
		team_1_score.setText(LiveThread.team_score.get("team_1_score"));
		team_2_score.setText(LiveThread.team_score.get("team_2_score"));
		team_1_name.setText(LiveThread.team_score.get("team_1_name")+
				LiveThread.team_score.get("team_1_zong"));
		team_2_name.setText(LiveThread.team_score.get("team_2_name")+
				LiveThread.team_score.get("team_2_zong"));
		int width = 200;
		int height = 150;
		ImageIcon[] ii=new ImageIcon[2];
		ii[0] = ImageHandle.loadTeam(CNToEN(LiveThread.team_score.get("team_1_name")));
		ii[0].setImage(ii[0].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		ii[1] = ImageHandle.loadTeam(CNToEN(LiveThread.team_score.get("team_2_name")));
		ii[1].setImage(ii[1].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		team_1.setIcon(ii[0]);
		team_2.setIcon(ii[1]);
		team_1_state.setText(LiveThread.team_score.get("team_1_state"));
		team_2_state.setText(LiveThread.team_score.get("team_2_state"));
		
		
	}

	public static void updateLiveInfo(){
		columnName = new Vector();
		String[] cname_noadd = new String[] {
				"球队", "一", "二", "三", "四","总分"};
		String[] cname_add1 = new String[] {
				"球队", "一", "二", "三", "四","加时1","总分"};
		String[] cname_add2 = new String[] {
				"球队", "一", "二", "三", "四","加时1","加时2","总分"};
		String[] cname;
		switch(LiveThread.each_part.size()){
		case 12:
			cname = cname_noadd;
			break;
		case 14:
			cname = cname_add1;
			break;
		case 16:
			cname = cname_add2;
			break;
		default:
			cname = cname_noadd;
		}
		for(int i=0;i<cname.length;i++) {
			columnName.add(cname[i]);
		}
		Vector rowDatas = new Vector();
		for(int i=0;i<3;i++){
			Vector rowData = new Vector();
			if(i==0){
				switch(LiveThread.each_part.size()){
				case 12:
					rowData.add("球队");
					rowData.add("一");
					rowData.add("二");
					rowData.add("三");
					rowData.add("四");
					rowData.add("总分");
					rowDatas.add(rowData);
					break;
				case 14:
					rowData.add("球队");
					rowData.add("一");
					rowData.add("二");
					rowData.add("三");
					rowData.add("四");
					rowData.add("加时");
					rowData.add("总分");
					rowDatas.add(rowData);
					break;
				case 16:
					rowData.add("球队");
					rowData.add("一");
					rowData.add("二");
					rowData.add("三");
					rowData.add("四");
					rowData.add("加时");
					rowData.add("加时");
					rowData.add("总分");
					rowDatas.add(rowData);
					break;
				}
			}else if(i==1){
				rowData.add(LiveThread.each_part.get("team_1_name"));
				rowData.add(LiveThread.each_part.get("team_1_one"));
				rowData.add(LiveThread.each_part.get("team_1_two"));
				rowData.add(LiveThread.each_part.get("team_1_three"));
				rowData.add(LiveThread.each_part.get("team_1_four"));
				if(LiveThread.each_part.get("team_1_add1")!=null){
					rowData.add(LiveThread.each_part.get("team_1_add1"));
				}
				if(LiveThread.each_part.get("team_1_add2")!=null){
					rowData.add(LiveThread.each_part.get("team_1_add2"));
				}
				rowData.add(LiveThread.each_part.get("team_1_all"));
				rowDatas.add(rowData);
			}
			else if(i==2){
				rowData.add(LiveThread.each_part.get("team_2_name"));
				rowData.add(LiveThread.each_part.get("team_2_one"));
				rowData.add(LiveThread.each_part.get("team_2_two"));
				rowData.add(LiveThread.each_part.get("team_2_three"));
				rowData.add(LiveThread.each_part.get("team_2_four"));
				if(LiveThread.each_part.get("team_2_add1")!=null){
					rowData.add(LiveThread.each_part.get("team_2_add1"));
				}
				if(LiveThread.each_part.get("team_2_add2")!=null){
					rowData.add(LiveThread.each_part.get("team_2_add2"));
				}
				rowData.add(LiveThread.each_part.get("team_2_all"));
				rowDatas.add(rowData);
			}
		}
		model.setDataVector(rowDatas, columnName);
		model.setColumnCount(table.getColumnCount());
		model.setRowCount(rowDatas.size());
		table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table.setModel(model);
		table.updateUI();
	}
	
	public static void updateTheData(){
		
		//team1=======================================================
		columnName_1 = new Vector();
		String[] cname = new String[] {
				"球员", "位置", "时间", "投篮", "三分", "罚球","前场", "后场", 
				"篮板", "助攻", "犯规", "抢断", "失误", "盖帽", "得分", "+/-"};
		for(int i=0;i<cname.length;i++) {
			columnName_1.add(cname[i]);
		}
		Vector rowDatas1 = new Vector();
		for(int i=0;i<LiveThread.player_data_1.size();i++){
			Vector rowData1 = new Vector();
			String[] player = LiveThread.player_data_1.get("player"+i);
			for(int j=0;j<player.length;j++){
				rowData1.add(player[j]);
			}
			rowDatas1.add(rowData1);
		}
		model_1.setDataVector(rowDatas1, columnName_1);
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		int[] width_1={140,50,50,60,50,50,50,50,50,50,50,50,50,50,50,50};
		table_1.setColumnModel(getColumn(table_1, width_1));
		table_1.setModel(model_1);
		table_1.updateUI();
		
		//team2=======================================================
		columnName_2 = new Vector();
		for(int i=0;i<cname.length;i++) {
			columnName_2.add(cname[i]);
		}
		Vector rowDatas2 = new Vector();
		for(int i=0;i<LiveThread.player_data_2.size();i++){
			Vector rowData2 = new Vector();
			String[] player = LiveThread.player_data_2.get("player"+i);
			for(int j=0;j<player.length;j++){
				rowData2.add(player[j]);
			}
			rowDatas2.add(rowData2);
		}
		model_2.setDataVector(rowDatas2, columnName_2);
		model_2.setColumnCount(table_2.getColumnCount());
		model_2.setRowCount(rowDatas2.size());
		int[] width_2={140,50,50,60,50,50,50,50,50,50,50,50,50,50,50,50};
		table_2.setColumnModel(getColumn(table_2, width_2));
		table_2.setModel(model_2);
		table_2.updateUI();
		
	}

	public static TableColumnModel getColumn(JTable table, int[] width) {  
		TableColumnModel columns = table.getColumnModel();  
		for (int i = 0; i < width.length; i++) {  
			TableColumn column = columns.getColumn(i);  
			column.setPreferredWidth(width[i]);  
		}  
		return columns;  
	}
	
	public static String CNToEN(String team){
		String result = "";
        if(team.equals("马刺"))
        	result = "SAS";
        else if(team.equals("灰熊"))
        	result = "MEM";
        else if(team.equals("小牛"))
        	result = "DAL";
        else if(team.equals("火箭"))
        	result = "HOU";
        else if(team.equals("鹈鹕"))
        	result = "NOP";
        else if(team.equals("森林狼"))
        	result = "MIN";
        else if(team.equals("掘金"))
        	result = "DEN";
        else if(team.equals("爵士"))
        	result = "UTA";
        else if(team.equals("开拓者"))
        	result = "POR";
        else if(team.equals("雷霆"))
        	result = "OKC";
        else if(team.equals("国王"))
        	result = "SAC";
        else if(team.equals("太阳"))
        	result = "PHX";
        else if(team.equals("湖人"))
        	result = "LAL";
        else if(team.equals("快船"))
        	result = "LAC";
        else if(team.equals("勇士"))
        	result = "GSW";
        else if(team.equals("热火"))
        	result = "MIA";
        else if(team.equals("魔术"))
        	result = "ORL";
        else if(team.equals("老鹰"))
        	result = "ATL";
        else if(team.equals("奇才"))
        	result = "WAS";
        else if(team.equals("黄蜂"))
        	result = "CHA";
        else if(team.equals("活塞"))
        	result = "DET";
        else if(team.equals("步行者"))
        	result = "IND";
        else if(team.equals("骑士"))
        	result = "CLE";
        else if(team.equals("公牛"))
        	result = "CHI";
        else if(team.equals("雄鹿"))
        	result = "MIL";
        else if(team.equals("凯尔特人"))
        	result = "BOS";
        else if(team.equals("76人"))
        	result = "PHI";
        else if(team.equals("尼克斯"))
        	result = "NYK";
        else if(team.equals("篮网"))
        	result = "BKN";
        else if(team.equals("猛龙"))
        	result = "TOR";
        
        return result;
	}

}
