package presentation.playerui;

import hotui.HotRankingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

public class PlayerComparePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();

	public static JScrollPane scrollPane;

	JLabel labelOfPhoto1;
	JLabel labelOfPhoto2;
	JLabel labelOfPhoto3;
	JLabel labelOfPhoto4;

	JComboBox comboBoxOfLTeam;
	JComboBox comboBoxOfLPlayer;
	JComboBox comboBoxOfRTeam;
	JComboBox comboBoxOfRPlayer;

	ImageIcon picture1;
	ImageIcon picture2;
	ImageIcon picture3;
	ImageIcon picture4;

	String pname1="NBA";
	String tname1;
	String pname2="NBA";
	String tname2="";

	private JTable table_l;
	private JTable table_c;
	private JTable table_r;

	DefaultTableModel model_l = new DefaultTableModel();
	DefaultTableModel model_c = new DefaultTableModel();
	DefaultTableModel model_r = new DefaultTableModel();

	Vector columnName_c = new Vector();
	Vector columnName_l = new Vector();
	Vector columnName_r = new Vector();

	private JButton button;

	private JPanel charPanel;
	public static String player_1 = "NoPlayer1";
	public static String player_2 = "NoPlayer2";

	public PlayerComparePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

		//panel===========================================================
		panelOfBottom.setLayout(null);

		//chart========================================================

		//		charPanel= new JPanel();
		//		charPanel.setBounds(50, 250, 900, 300);
		//		charPanel.setVisible(true);
		//		panelOfBottom.add(charPanel);

		//label =======================================================
		labelOfPhoto1 = new JLabel("");
		labelOfPhoto1.setBounds(140, 57, 180, 145);
		panelOfBottom.add(labelOfPhoto1);

		labelOfPhoto2 = new JLabel("");
		labelOfPhoto2.setBounds(30, 79, 100, 100);
		panelOfBottom.add(labelOfPhoto2);

		labelOfPhoto3 = new JLabel("");
		labelOfPhoto3.setBounds(663, 57, 180, 145);
		panelOfBottom.add(labelOfPhoto3);

		labelOfPhoto4 = new JLabel("");
		labelOfPhoto4.setBounds(865, 79, 100, 100);
		panelOfBottom.add(labelOfPhoto4);

		//Icon========================================================
		picture3 = ImageHandle.loadTeam(pname2);
		picture3.setImage(picture3.getImage().getScaledInstance(145, 145,
				Image.SCALE_DEFAULT));
		labelOfPhoto3.setIcon(picture3);

		//comboBox========================================================
		comboBoxOfLTeam = new JComboBox();
		comboBoxOfLTeam.setBounds(172, 21, 124, 26);
		panelOfBottom.add(comboBoxOfLTeam);
		comboBoxOfLTeam.addItem("选择球队");
		comboBoxOfLTeam.addItemListener(new TeamItemListener('l'));

		comboBoxOfLPlayer = new JComboBox();
		comboBoxOfLPlayer.setBounds(306, 21, 124, 26);
		panelOfBottom.add(comboBoxOfLPlayer);
		comboBoxOfLPlayer.addItem("选择球员");
		comboBoxOfLPlayer.addItemListener(new PlayerItemListener('l'));

		comboBoxOfRTeam = new JComboBox();
		comboBoxOfRTeam.setBounds(678, 20, 124, 26);
		panelOfBottom.add(comboBoxOfRTeam);
		comboBoxOfRTeam.addItem("选择球队");
		comboBoxOfRTeam.addItemListener(new TeamItemListener('r'));

		comboBoxOfRPlayer = new JComboBox();
		comboBoxOfRPlayer.setBounds(826, 20, 124, 26);
		panelOfBottom.add(comboBoxOfRPlayer);
		comboBoxOfRPlayer.addItem("选择球员");
		comboBoxOfRPlayer.addItemListener(new PlayerItemListener('r'));

		ArrayList<TeamVO> teams = blservice.getTeamAnalysis();
		for(int i=0;i<teams.size();i++) {
			TeamVO tempvo = teams.get(i);
			comboBoxOfLTeam.addItem(PlayerSelectionPanel.translate(tempvo.getAbbreviation()));
			comboBoxOfRTeam.addItem(PlayerSelectionPanel.translate(tempvo.getAbbreviation()));
		}

		//column==============================================================
		String[] cname_c = new String[] {""};
		for(int i=0;i<cname_c.length;i++) {
			columnName_c.add(cname_c[i]);
		}	

		String[] cname_l = new String[] {""};
		for(int i=0;i<cname_l.length;i++) {
			columnName_l.add(cname_l[i]);
		}	

		String[] cname_r = new String[] {""};
		for(int i=0;i<cname_r.length;i++) {
			columnName_r.add(cname_r[i]);
		}	

		//table=================================================================
		table_l = new JTable(model_l);
		table_l.setBounds(330, 57, 140, 190);
		panelOfBottom.add(table_l);

		table_c = new JTable(model_c);
		table_c.setBounds(470, 57, 45, 190);
		panelOfBottom.add(table_c);

		Vector rowDatas_c = new Vector();
		Vector rowData_c = new Vector();
		rowData_c.add("姓名");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("年龄");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("位置");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("生日");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("身高");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("体重");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("球龄");
		rowDatas_c.add(rowData_c);
		model_c.setDataVector(rowDatas_c, columnName_c);		
		model_c.setColumnCount(table_c.getColumnCount());
		model_c.setRowCount(rowDatas_c.size());
		table_c.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_c.setModel(model_c);
		table_c.updateUI();

		table_r = new JTable(model_r);

		table_r.setBounds(515, 57, 139, 190);
		panelOfBottom.add(table_r);

		//button=====================================================================		
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);

				PlayerInfoPanel.scrollPane.setVisible(true);
				PlayerComparePanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA球员信息");
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
			}
		});
		panelOfBottom.add(button);

	}

	//update left player info
	public void update1(String playerName1,String teamName1) {
		pname1 = playerName1;
		tname1 = teamName1;

		picture1 = ImageHandle.loadPlayer(playerName1);
		picture2 = ImageHandle.loadTeam(teamName1);

		picture1.setImage(picture1.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture2.setImage(picture2.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));

		labelOfPhoto1.setIcon(picture1);
		labelOfPhoto2.setIcon(picture2);

		PlayerVO vo = blservice.getPlayerAnalysis(playerName1);

		Vector rowDatas_l = new Vector();
		Vector rowData_l = new Vector();
		rowData_l.add(vo.getName());
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		rowData_l.add(vo.getAge());
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		rowData_l.add(vo.getPosition());
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
		rowData_l.add(sdf9.format(vo.getBirth().getTime()));
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		rowData_l.add(vo.getHeight());
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		rowData_l.add(vo.getWeight());
		rowDatas_l.add(rowData_l);

		rowData_l = new Vector();
		rowData_l.add(vo.getExp());
		rowDatas_l.add(rowData_l);

		model_l.setDataVector(rowDatas_l, columnName_l);		
		model_l.setColumnCount(table_l.getColumnCount());
		model_l.setRowCount(rowDatas_l.size());
		table_l.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_l.setModel(model_l);
		table_l.updateUI();
	}

	//update right player info
	public void update2(String playerName2,String teamName2) {
		pname2 = playerName2;
		tname2 = teamName2;

		picture3 = ImageHandle.loadPlayer(pname2);
		picture4 = ImageHandle.loadTeam(tname2);

		picture3.setImage(picture3.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture4.setImage(picture4.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));

		labelOfPhoto3.setIcon(picture3);
		labelOfPhoto4.setIcon(picture4);

		PlayerVO vo = blservice.getPlayerAnalysis(playerName2);

		Vector rowDatas_r = new Vector();
		Vector rowData_r = new Vector();
		rowData_r.add(vo.getName());
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		rowData_r.add(vo.getAge());
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		rowData_r.add(vo.getPosition());
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
		rowData_r.add(sdf9.format(vo.getBirth().getTime()));
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		rowData_r.add(vo.getHeight());
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		rowData_r.add(vo.getWeight());
		rowDatas_r.add(rowData_r);

		rowData_r = new Vector();
		rowData_r.add(vo.getExp());
		rowDatas_r.add(rowData_r);

		model_r.setDataVector(rowDatas_r, columnName_r);		
		model_r.setColumnCount(table_r.getColumnCount());
		model_r.setRowCount(rowDatas_r.size());
		table_r.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_r.setModel(model_r);
		table_r.updateUI();
	}

	public class TeamItemListener implements ItemListener{

		char c = ' ';
		public TeamItemListener() {}

		public TeamItemListener(char flag) {
			c = flag;
		}
		public void itemStateChanged(ItemEvent e) {

			if(e.getStateChange()==ItemEvent.SELECTED) {
				if(c == 'l') { 	
					comboBoxOfLPlayer.removeAllItems();
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						comboBoxOfLPlayer.addItem("选择球员");
					} else {
						TeamWithPlayersVO teamvo = blservice.getTeamAnalysis(HotRankingPanel.translate(teamSelected));
						ArrayList<PlayerVO> players = teamvo.getPlayers();
						comboBoxOfLPlayer.addItem("选择球员");
						for(int i=0;i<players.size();i++) {
							PlayerVO tempvo = players.get(i);
							comboBoxOfLPlayer.addItem(tempvo.getName());
						}
					}
				} else if (c == 'r') {
					comboBoxOfRPlayer.removeAllItems();
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						comboBoxOfLPlayer.addItem("选择球员");
					} else {
						TeamWithPlayersVO teamvo = blservice.getTeamAnalysis(HotRankingPanel.translate(teamSelected));
						ArrayList<PlayerVO> players = teamvo.getPlayers();
						comboBoxOfRPlayer.addItem("选择球员");
						for(int i=0;i<players.size();i++) {
							PlayerVO tempvo = players.get(i);
							comboBoxOfRPlayer.addItem(tempvo.getName());
						}
					}
				}
			}
		}

	}

	public class PlayerItemListener implements ItemListener{

		char c = ' ';
		public PlayerItemListener() {}

		public PlayerItemListener(char flag) {
			c = flag;
		}
		public void itemStateChanged(ItemEvent e) {

			if(e.getStateChange()==ItemEvent.SELECTED) {
				if(c == 'l') { 	
					String playerSelected =e.getItem().toString();
					if(playerSelected != "选择球员") {
						update1(e.getItem().toString(),
								HotRankingPanel.translate(comboBoxOfLTeam.getSelectedItem().toString()));
						player_1 = e.getItem().toString();
						update_chart(player_1, player_2);
					}
				} else if (c == 'r') {
					String playerSelected =e.getItem().toString();
					if(playerSelected != "选择球员") {
						update2(e.getItem().toString(),
								HotRankingPanel.translate(comboBoxOfRTeam.getSelectedItem().toString()));
						player_2 = e.getItem().toString();
						update_chart(player_1, player_2);
					}
				}
			}
		}

	}

	//update the chart info
	public void update_chart(String playerName1,String playerName2){
		if(!playerName2.equals("NoPlayer2")){
			PlayerVO vo1 = blservice.getPlayerAnalysis(playerName1);
			PlayerVO vo2 = blservice.getPlayerAnalysis(playerName2);
			int assist_1 = vo1.getAssist();
			int assist_2 = vo2.getAssist();
			int score_1 = vo1.getScore();
			int score_2 = vo2.getScore();
			int steal_1 = vo1.getSteal();
			int steal_2 = vo2.getSteal();
			int miss_1 = vo1.getMiss();
			int miss_2 = vo2.getMiss();
			int block_1 = vo1.getBlock();
			int block_2 = vo2.getBlock();
			int[] dataArray = {assist_1,assist_2,score_1,score_2,
					steal_1,steal_2,miss_1,miss_2,block_1,block_1};
			String[] players = {playerName1, playerName2};

			if(charPanel!=null){
				charPanel.setVisible(false);
				charPanel = null;
			}
			charPanel = createPanel(players, dataArray);
			charPanel.setBounds(30, 250, 900, 300);
			charPanel.setVisible(true);
			charPanel.updateUI();
			panelOfBottom.add(charPanel);
			panelOfBottom.repaint();
		}
		else{
			PlayerVO vo1 = blservice.getPlayerAnalysis(playerName1);
			int assist_1 = vo1.getAssist();
			int assist_2 = 0;
			int score_1 = vo1.getScore();
			int score_2 = 0;
			int steal_1 = vo1.getSteal();
			int steal_2 = 0;
			int miss_1 = vo1.getMiss();
			int miss_2 = 0;
			int block_1 = vo1.getBlock();
			int block_2 = 0;
			int[] dataArray = {assist_1,assist_2,score_1,score_2,
					steal_1,steal_2,miss_1,miss_2,block_1,block_1};
			String[] players = {playerName1, playerName2};

			if(charPanel!=null){
				charPanel.setVisible(false);
				charPanel = null;
			}
			charPanel = createPanel(players, dataArray);
			charPanel.setBounds(50, 250, 900, 300);
			charPanel.setVisible(true);
			charPanel.updateUI();
			panelOfBottom.add(charPanel);
			panelOfBottom.repaint();
		}


	}

	private static JFreeChart createChart(CategoryDataset dataset){//用数据集创建一个图表
		JFreeChart chart = ChartFactory.createBarChart3D( 
				"球员对比", // 图表标题
				"球员", // 目录轴的显示标签
				"数值", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true,  // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false  // 是否生成 URL 链接
				); 
		//中文乱码
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
		CategoryAxis domainAxis = categoryplot.getDomainAxis();  
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		textTitle.setPaint(Color.white);
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));  
		domainAxis.setLabelPaint(Color.white);
		domainAxis.setTickLabelPaint(Color.white);
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 15));  
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 15));
		numberaxis.setLabelPaint(Color.white);
		numberaxis.setTickLabelPaint(Color.white);
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));

		BarRenderer renderer = (BarRenderer)categoryplot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		
		chart.setBackgroundPaint(new Color(0.0F, 0.0F, 0.0F, 0.0F));

		return chart;
	}

	private static CategoryDataset createDataset(String[] players, int[] dataArray){//创建柱状图数据集

		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		dataset.addValue(dataArray[0], players[0], "助攻"); 
		dataset.addValue(dataArray[1], players[1], "助攻"); 
		dataset.addValue(dataArray[2], players[0], "得分"); 
		dataset.addValue(dataArray[3], players[1], "得分"); 
		dataset.addValue(dataArray[4], players[0], "抢断"); 
		dataset.addValue(dataArray[5], players[1], "抢断"); 
		dataset.addValue(dataArray[6], players[0], "失误"); 
		dataset.addValue(dataArray[7], players[1], "失误"); 
		dataset.addValue(dataArray[8], players[0], "盖帽"); 
		dataset.addValue(dataArray[9], players[1], "盖帽"); 
		return dataset; 
	}

	private static JPanel createPanel(String[] players, int[] dataArray){
		JFreeChart chart =createChart(createDataset(players, dataArray));
		return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}
}
