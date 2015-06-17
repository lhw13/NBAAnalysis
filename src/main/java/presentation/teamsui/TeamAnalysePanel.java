package presentation.teamsui;

import hotui.HotRankingPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.util.Rotation;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerSelectionPanel;
import presentation.playerui.Rotator;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class TeamAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel panelOfAnalyse = new JPanel();
	JPanel panelOfPredict = new JPanel();
	JPanel pie;
	JPanel box;
	JPanel barchart1;
	JPanel barchart2;
	
	public static JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	
	JTable table_1;
	
	DefaultTableModel model_1 = new DefaultTableModel();
	
	Vector columnName_1 = new Vector();
	
	JButton button;
	
	String abbreviation;
	
	JLabel label ;
	JLabel label_analyse;
	JLabel label_predict;
	JLabel label_pk1;
	JLabel label_pk2;

	JLabel label_score;
	JLabel label_score1;
	JLabel label_score2;
	JLabel label_winrate;
	JLabel label_winrate1;
	JLabel label_winrate2;
	JLabel label_red1;
	JLabel label_blue2;
	JLabel label_home;
	JLabel label_nohome;
	
	JComboBox combox1;
	JComboBox combox2;
	
	
	double[] co1;
	double[] co2;
	
	public TeamAnalysePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		scrollPane_1 = new JScrollPane();
		
		String[] cname_1 = new String[] {"得分","投篮%","三分%",
				"罚球%","前篮板","后篮板","总篮板","助攻","抢断","盖帽","失误","犯规"};
		for(int i=0;i<cname_1.length;i++) {
			columnName_1.add(cname_1[i]);
		}	
//panel===========================================================
		panelOfBottom.setLayout(null);
		panelOfBottom.setPreferredSize(new Dimension(1000, 1300));
		
		panelOfAnalyse.setLayout(null);
		panelOfAnalyse.setBounds(0, 50, 1000, 1200);
		panelOfBottom.add(panelOfAnalyse);
		panelOfAnalyse.setVisible(true);
		
		panelOfPredict.setLayout(null);
		panelOfPredict.setBounds(0, 50, 1000, 1200);
		panelOfBottom.add(panelOfPredict);
		panelOfPredict.setVisible(false);
		
		panelOfAnalyse.add(scrollPane_1);
		scrollPane_1.setBounds(100,430, 600,60);
		
	//button===========================================================
		
	    //button===========================================================
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				TeamsInfoFrame.scrollPane.setVisible(true);
				TeamAnalysePanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA球队信息");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}
		});
		panelOfBottom.add(button);

	//table======================================================
		table_1 = new JTable(model_1);
		table_1.setShowGrid(false);
		scrollPane_1.setViewportView(table_1);
		
	//label========================================================

		
		label = new JLabel("New label");
		label.setBounds(30, 91, 200, 200);
		panelOfAnalyse.add(label);
		
		label_analyse = new JLabel("球队分析");
		label_analyse.setFont(new Font("宋体", Font.PLAIN, 14));
		label_analyse.setBounds(216, 27, 67, 20);
		panelOfBottom.add(label_analyse);
		label_analyse.addMouseListener(new Listener1());
		
		label_predict = new JLabel("预测PK");
		label_predict.setFont(new Font("宋体", Font.PLAIN, 14));
		label_predict.setBounds(293, 27, 67, 20);
		label_predict.addMouseListener(new Listener1());
		panelOfBottom.add(label_predict);
		
		label_pk1  = new JLabel("p1");
		label_pk1.setBounds(30, 91, 200, 200);
		panelOfPredict.add(label_pk1);
		
		label_pk2 = new JLabel("p2");
		label_pk2.setBounds(720, 91, 200, 200);
		panelOfPredict.add(label_pk2);
		
		label_score = new JLabel("预测比分");
		label_score.setFont(new Font("黑体", Font.PLAIN, 40));
		label_score.setBounds(400, 150, 180, 40);
		panelOfPredict.add(label_score);
		
		label_score1 = new JLabel();
		label_score1.setFont(new Font("黑体", Font.PLAIN, 40));
		label_score1.setBounds(300, 150, 100, 40);
		panelOfPredict.add(label_score1);
		
		label_score2 = new JLabel();
		label_score2.setFont(new Font("黑体", Font.PLAIN, 40));
		label_score2.setBounds(600, 150, 100, 40);
		panelOfPredict.add(label_score2);
		
		label_winrate = new JLabel("预测胜率");
		label_winrate.setFont(new Font("黑体", Font.PLAIN, 40));
		label_winrate.setBounds(400, 200, 180, 40);
		panelOfPredict.add(label_winrate);
		
		label_winrate1 = new JLabel();
		label_winrate1.setFont(new Font("黑体", Font.PLAIN, 40));
		label_winrate1.setBounds(270, 200, 100, 40);
		panelOfPredict.add(label_winrate1);
		
		label_winrate2 = new JLabel();
		label_winrate2.setFont(new Font("黑体", Font.PLAIN, 40));
		label_winrate2.setBounds(600, 200, 100, 40);
		panelOfPredict.add(label_winrate2);
		
		label_nohome = new JLabel("客场");
		label_nohome.setFont(new Font("黑体", Font.PLAIN, 40));
		label_nohome.setBounds(200, 10, 100, 40);
		panelOfPredict.add(label_nohome);
		
		label_home = new JLabel("主场");
		label_home.setFont(new Font("黑体", Font.PLAIN, 40));
		label_home.setBounds(650, 10, 100, 40);
		panelOfPredict.add(label_home);
		 
		label_red1 = new JLabel();
		label_red1.setFont(new Font("黑体", Font.PLAIN, 40));
		label_red1.setBounds(340, 260, 150, 30);
		panelOfPredict.add(label_red1);
		 
		label_blue2 = new JLabel();
		label_blue2.setFont(new Font("黑体", Font.PLAIN, 40));
		label_blue2.setBounds(340, 260, 300, 30);
		panelOfPredict.add(label_blue2);
		
		ImageIcon picture = ImageHandle.loadTeam("blue");
		label_blue2.setIcon(picture);
		picture= ImageHandle.loadTeam("red");
		label_red1.setIcon(picture);
//combobox=============================================================
		
		combox1 = new JComboBox();
		combox1.setBounds(50, 50, 100, 25);
		panelOfPredict.add(combox1);
		combox1.addItem("选择球队");
		combox1.addItemListener(new TeamItemListener('l'));
		
		combox2 = new JComboBox();
		combox2.setBounds(770, 50, 100, 25);
		panelOfPredict.add(combox2);
		combox2.addItem("选择球队");
		combox2.addItemListener(new TeamItemListener('r'));
		
		
	}

	public void update(String abb) {
		TeamWithPlayersVO tpvo = blservice.getTeamAnalysis(abb);
		TeamVO tvo = tpvo.getTeam();
		this.abbreviation = abb;
		ImageIcon picture = ImageHandle.loadTeam(abb);
		picture.setImage(picture.getImage().getScaledInstance(200, 200,
				Image.SCALE_DEFAULT));
		label.setIcon(picture);
		if(pie!=null){
			pie.setVisible(false);
			pie = null;
		}
		pie = createDemoPanel();
		pie.setBounds(350, 0, 500, 410);

		pie.setVisible(true);		
		pie.updateUI();
		
		if(box!=null){
			box.setVisible(false);
			box = null;
		}
		box = createDemoPanel_1();
		box.setBounds(50, 600, 900, 400);
		box.setVisible(true);		
		box.updateUI();
		panelOfAnalyse.add(pie);
		panelOfAnalyse.add(box);
		panelOfAnalyse.repaint();
		
		Vector rowDatas_1 = new Vector();
		Vector rowData_1 = new Vector();	
		int appearance = tvo.getAppearance();
		rowData_1.add(String.format("%.1f", (double)tvo.getScore()/appearance));
		rowData_1.add(String.format("%.1f", tvo.getHitRate()*100)+"%");
		rowData_1.add(String.format("%.1f", tvo.getThirdHitRate()*100)+"%");
		rowData_1.add(String.format("%.1f", tvo.getFreeHitRate()*100)+"%");
		rowData_1.add(String.format("%.1f", (double)tvo.getOffensiveRebound()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getDefensiveRebound()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getTotalRebound()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getAssist()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getSteal()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getBlock()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getMiss()/appearance));
		rowData_1.add(String.format("%.1f", (double)tvo.getFoul()/appearance));
		rowDatas_1.add(rowData_1);
		model_1.setDataVector(rowDatas_1, columnName_1);		
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas_1.size());
		table_1.setModel(model_1);
		//int[] width_1={50,60,5,3,3,3,3,3,3,3,3,3,3,3};
		//table_1.setColumnModel(getColumn(table_1, width_1));
		table_1.updateUI();
		
		calculate();//计算好回归需要的系数
		
	}
	
	public void update2(String abb1, String abb2) {
		
		ImageIcon picture = ImageHandle.loadTeam(abb1);
		picture.setImage(picture.getImage().getScaledInstance(200, 200,
				Image.SCALE_DEFAULT));
		label_pk1.setIcon(picture);
		
		picture = ImageHandle.loadTeam(abb2);
		picture.setImage(picture.getImage().getScaledInstance(200, 200,
				Image.SCALE_DEFAULT));
		label_pk2.setIcon(picture);
		
		if(barchart1!=null) {
			barchart1.setVisible(false);
			barchart1 = null;
		}
		
		if(abb1!="NBA"&&abb2!="NBA"){
			String[] teams = {abb1,abb2};
			barchart1 = createPanel_3(teams);
			barchart1.setBounds(0, 300, 690, 280);
			barchart1.setVisible(true);		
			barchart1.updateUI();
			panelOfPredict.add(barchart1);
			panelOfPredict.repaint();
			
			double[][] x1 = blservice.getVariables(abb1, abb2);
			double score1=0,score2=0;
			
			for(int j=1;j<x1[0].length;j++) {
				score1+=co1[j]*x1[0][j];
				score2+=co1[j]*x1[1][j];
			}
			score1+=co1[0]; score2+=co1[0];
			
			double[][] x2 = blservice.getDataForStrengthVariables(abb1, abb2);
			double differential=0;//净胜分
			
			for(int j=1;j<x2[0].length;j++) {
				differential+=co2[j]*x2[0][j];
				
			}
			differential+=co2[0];
			int[] s = blservice.adjustPredictResult(score1, score2, differential);
			label_score1.setText(Integer.toString(s[0]));
			label_score2.setText(Integer.toString(s[1]));
			
			double[][] datas2 = blservice.getDataForStrengthRegression(2000);
			double[][] vdata = new double[datas2.length][2];//计算方差,参数
			
			for(int i=0;i<datas2.length;i++) {
				vdata[i][0] = datas2[i][0];
				vdata[i][1] = co2[0];
				for(int j=1;j<datas2[0].length;j++) {
					vdata[i][1]+= co2[j]*datas2[i][j];
				}
			}
			double var = blservice.getVariance(vdata, vdata.length);
			double[] check = blservice.varifyByCentralMoment(vdata, vdata.length);
			System.out.println(check[0]+":"+check[1]);
			File fx = Opendoc("forWinRate.txt");
			
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fx));
				writer.write(Double.toString(differential)+",");
				writer.write(Double.toString(var));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Process proc;
			try {
				proc = Runtime.getRuntime().exec("python norm.py");
				
				StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");  
				StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");  
				errorGobbler.start();  
				outputGobbler.start(); 
				proc.waitFor(); 
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(Opendoc("winrate.txt")));
				String line = reader.readLine();
				double winrate = Double.parseDouble(line);
				label_winrate1.setText(String.format("%.1f", 100-winrate*100)+"%");
				label_winrate2.setText(String.format("%.1f", winrate*100)+"%");
				label_red1.setSize(300-(int)(300*winrate),30);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void update3() {
		combox1.removeAllItems();
		combox2.removeAllItems();
		combox1.addItem("选择球队");
		combox2.addItem("选择球队");
		ArrayList<TeamVO> teams = blservice.getTeamAnalysis();
		for(TeamVO temp : teams) {
			combox1.addItem(PlayerSelectionPanel.translate(temp.getAbbreviation()));
			combox2.addItem(PlayerSelectionPanel.translate(temp.getAbbreviation()));
		}
		
	}
	
	
	public void calculate() {
		double[][] datas1 = blservice.getDataForRegression(2000);
		write("datax.txt","datay.txt",datas1);
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python 123.py");
			
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");  
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");  
			errorGobbler.start();  
			outputGobbler.start(); 
			proc.waitFor(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		
		co1 = read("b.txt");//第一个接口需要的系数
		
		double[][] datas2 = blservice.getDataForStrengthRegression(2000);
		write("datax.txt","datay.txt",datas2);
		
		try {
			proc = Runtime.getRuntime().exec("python 123.py");
			
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");  
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");  
			errorGobbler.start();  
			outputGobbler.start(); 
			proc.waitFor(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		co2 = read("b.txt");//第一个接口需要的系数
		for(int i=0;i<co1.length;i++) {
			
		}
	}
	public class StreamGobbler extends Thread {  
		  
	    InputStream is;  
	    String type;  
	  
	    public StreamGobbler(InputStream is, String type) {  
	        this.is = is;  
	        this.type = type;  
	    }  
	  
	    public void run() {  
	        try {  
	            InputStreamReader isr = new InputStreamReader(is);  
	            BufferedReader br = new BufferedReader(isr);  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	                if (type.equals("Error")) {  
	                    System.out.println("Error   :" + line);  
	                } else {  
	                    System.out.println("Debug:" + line);  
	                }  
	            }  
	        } catch (IOException ioe) {  
	            ioe.printStackTrace();  
	        }  
	    }  
	}  
	public double[] read(String filename) {
		File f = Opendoc(filename);
		String[] co = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			co = line.split(",");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double[] cod = new double[co.length];
		for(int i=0;i<co.length;i++) {
			cod[i] = Double.parseDouble(co[i]);
		}
		return cod;
	}
	
	public void write(String filenamex,String filenamey,double[][] datas) {
		File fx = Opendoc(filenamex);
		File fy = Opendoc(filenamey);
		int scale = datas.length;
		int len = datas[0].length;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fx));
			for(int i=0;i<scale;i++) {
				writer.write("1,");
				for(int j=1;j<len-1;j++) {
					writer.write(Double.toString(datas[i][j])+",");
				}
				writer.write(Double.toString(datas[i][len-1])+"\n");
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
					
		try {
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(fy));
			for(int i=0;i<scale-1;i++) {	
				writer2.write(Double.toString(datas[i][0])+",");
			}
			writer2.write(Double.toString(datas[scale-1][0]));
			writer2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public File Opendoc(String s)
	{//参数为需要打开的文件路径
	    File f = new File(s);
	    if(!f.exists())//若文件不存在，自动创建
	    try
	    {
	    	if(f.getParentFile() != null)
	    		f.getParentFile().mkdirs();
	        f.createNewFile();
	    }
	    catch(IOException e)
	    {
	        System.out.println(e);
	    }
		return f;
	}
	private static JFreeChart createChart(PieDataset piedataset)
	{
		//创建主题样式  
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
        //设置标题字体  
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
        //设置图例的字体  
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
        //设置轴向的字体  
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
        //应用主题样式  
        ChartFactory.setChartTheme(standardChartTheme);

		JFreeChart jfreechart = ChartFactory.createPieChart3D("球员贡献前十占比", piedataset, true, false, false);
		PiePlot3D pieplot3d = (PiePlot3D)jfreechart.getPlot();
		
		TextTitle textTitle = jfreechart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		textTitle.setPaint(Color.white);
		
		pieplot3d.setStartAngle(270D);
		pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} )"));
		pieplot3d.setDirection(Rotation.ANTICLOCKWISE);
		pieplot3d.setForegroundAlpha(0.6F);
		jfreechart.setBackgroundPaint(new Color(0.0F, 0.0F, 0.0F, 0.0F));
		return jfreechart;
	}

	private  PieDataset createDataset()
	{
		TeamWithPlayersVO vo = blservice.getTeamAnalysis(abbreviation);
		ArrayList<PlayerVO> players= vo.getPlayers();
		Collections.sort(players, compareEfficientDesc);  
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		for(int i=0;i<10;i++) {
			PlayerVO temp = players.get(i);
			defaultpiedataset.setValue(temp.getName(), temp.getEfficient());
		}
		double e=0;
		for(int i=10;i<players.size();i++) {
			PlayerVO temp = players.get(i);
			e+=temp.getEfficient();
		}
		defaultpiedataset.setValue("其他", e);
		return defaultpiedataset;
	}

	
	public JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		Rotator rotator = new Rotator((PiePlot3D)jfreechart.getPlot());
		rotator.start();
		return new ChartPanel(jfreechart);
	}
	
	private  BoxAndWhiskerCategoryDataset createDataset_1()
	{
		TeamWithPlayersVO team = blservice.getTeamAnalysis(abbreviation);
		ArrayList<PlayerVO> players = team.getPlayers();
		Collections.sort(players, compareEfficientDesc);
		DefaultBoxAndWhiskerCategoryDataset defaultboxandwhiskercategorydataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		for (int j = 0; j<10&&j< players.size(); j++)
		{
			PlayerVO playerTemp = players.get(j);
			java.util.List list = createValueList_1(playerTemp.getName());
			defaultboxandwhiskercategorydataset.add(list, "得分 " , players.get(j).getName().split(" ")[0] );
		}
		return defaultboxandwhiskercategorydataset;
	}

	private java.util.List createValueList_1(String pname)
	{
		ArrayList arraylist = new ArrayList();
		PlayerVO vo = blservice.getPlayerAnalysis(pname);
		ArrayList<MatchPO> matches = vo.getMatches();
		
		for(int i=0;i<matches.size();i++){
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
			
			arraylist.add(playerTemp.getScore());
			
		}

		return arraylist;
	}

	private static JFreeChart createChart_1(BoxAndWhiskerCategoryDataset boxandwhiskercategorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBoxAndWhiskerChart("各个球员得分箱线图", "Category", "Value", boxandwhiskercategorydataset, true);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangePannable(true);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	public JPanel createDemoPanel_1()
	{
		JFreeChart jfreechart = createChart_1(createDataset_1());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(false);
		return chartpanel;
	}
	public class Listener1 extends MouseAdapter{

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			if(label1.getText().startsWith("球队")){
				
				label_analyse.setForeground(Color.WHITE);
				label_predict.setForeground(Color.gray);
				
				panelOfAnalyse.setVisible(true);
				panelOfPredict.setVisible(false);
			} else if(label1.getText().startsWith("预测")) {
				label_predict.setForeground(Color.WHITE);
				label_analyse.setForeground(Color.gray);
				panelOfAnalyse.setVisible(false);
				panelOfPredict.setVisible(true);
			} 
		}		
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());
		}
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
					
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						
					} else {

						//TeamWithPlayersVO teamvo = blservice.getTeamAnalysis();
						ImageIcon picture = ImageHandle.loadTeam(HotRankingPanel.translate(teamSelected));
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk1.setIcon(picture);
						String t2 = (String)combox2.getSelectedItem();
						if(!t2.equals("选择球队"))
							update2(HotRankingPanel.translate((String)combox1.getSelectedItem()),
								HotRankingPanel.translate((String)combox2.getSelectedItem()));
					}
				} else if (c == 'r') {
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						
					} else {
						ImageIcon picture = ImageHandle.loadTeam(HotRankingPanel.translate(teamSelected));
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk2.setIcon(picture);
						String t1 = (String)combox1.getSelectedItem();
						if(!t1.equals("选择球队")){
							update2(HotRankingPanel.translate((String)combox1.getSelectedItem()),
								HotRankingPanel.translate((String)combox2.getSelectedItem()));
						} else {
							update2(abbreviation,HotRankingPanel.translate((String)combox2.getSelectedItem()));
						}
						
					}
				}
				
				
			}
		}

	}
	
	private static JFreeChart createChart_3(CategoryDataset dataset){//用数据集创建一个图表
		JFreeChart chart = ChartFactory.createBarChart3D( 
				"球队赛季数据对比1", // 图表标题
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

	private  CategoryDataset createDataset_3(String[] teams){//创建柱状图数据集

		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		TeamWithPlayersVO vo1 = blservice.getTeamAnalysis(teams[0]);
		TeamWithPlayersVO vo2 = blservice.getTeamAnalysis(teams[1]);
		
		TeamVO team1 = vo1.getTeam();
		TeamVO team2 = vo2.getTeam();
		double[] scores = {handle((double)team1.getScore()/team1.getAppearance()), 
				handle((double)team2.getScore()/team2.getAppearance())};
		double[] assists = {handle((double)team1.getAssist()/team1.getAppearance()), 
				handle((double)team2.getAssist()/team2.getAppearance())};
		double[] rebounds = {handle((double)team1.getTotalRebound()/team1.getAppearance()), 
				handle((double)team2.getTotalRebound()/team2.getAppearance())};
		
		dataset.addValue(scores[0], teams[0], "得分");
		dataset.addValue(scores[1], teams[1], "得分");
		
		dataset.addValue(assists[0], teams[0], "助攻");
		dataset.addValue(assists[1], teams[1], "助攻");
		
		dataset.addValue(rebounds[0], teams[0], "篮板");
		dataset.addValue(rebounds[1], teams[1], "篮板");
	
		return dataset; 
	}

	private JPanel createPanel_3(String[] players){
		JFreeChart chart =createChart_3(createDataset_3(players));
		return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}
	
	public static double handle(double a) {
		double result = a/1.0;
		Double r = new Double(result);
		if(result!=0&&!r.isNaN()&&!r.isInfinite()) {
			BigDecimal bg = new BigDecimal(result);
			result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return result;
	}
	public final Comparator<PlayerVO> compareEfficientDesc = new Comparator<PlayerVO>() {  
		  
	    @Override  
	    public int compare(PlayerVO o1, PlayerVO o2) {  
	    	double d2 = o2.getEfficient();
	    	double d1 = o1.getEfficient();
	        if(d2>d1)return 1;else if(d2<d1)return -1;else return 0;
	    	//return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
	    }
	};
	
}
