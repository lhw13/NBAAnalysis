package presentation.teamsui;

import hotui.HotRankingPanel;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.util.Rotation;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerAnalysePanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.playerui.Rotator;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

import javax.swing.JLabel;

public class TeamAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel panelOfAnalyse = new JPanel();
	JPanel panelOfPredict = new JPanel();
	JPanel pie;
	JPanel box;
	
	public static JScrollPane scrollPane;
	
	JButton button;
	
	String abbreviation;
	
	JLabel label ;
	JLabel label_analyse;
	JLabel label_predict;
	JLabel label_pk1;
	JLabel label_pk2;
	
	JTable table;
	
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
		//panel===========================================================
		panelOfBottom.setLayout(null);
		panelOfBottom.setPreferredSize(new Dimension(1000, 1800));
		
		panelOfAnalyse.setLayout(null);
		panelOfAnalyse.setBounds(0, 50, 1000, 1700);
		panelOfBottom.add(panelOfAnalyse);
		panelOfAnalyse.setVisible(true);
		
		panelOfPredict.setLayout(null);
		panelOfPredict.setBounds(0, 50, 1000, 550);
		panelOfBottom.add(panelOfPredict);
		panelOfPredict.setVisible(false);
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
		label_pk2.setBounds(680, 91, 200, 200);
		panelOfPredict.add(label_pk2);
//table================================================================
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 16));
		table.setBounds(250, 100, 386, 200);
		panelOfPredict.add(table);
//combobox=============================================================
		
		combox1 = new JComboBox();
		combox1.setBounds(50, 50, 100, 25);
		panelOfPredict.add(combox1);
		combox1.addItem("选择球队");
		combox1.addItemListener(new TeamItemListener('l'));
		
		combox2 = new JComboBox();
		combox2.setBounds(680, 50, 100, 25);
		panelOfPredict.add(combox2);
		combox2.addItem("选择球队");
		combox2.addItemListener(new TeamItemListener('r'));
		
		calculate();//计算好回归需要的系数
	}

	public void update(String abb) {
		
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
		pie.setBounds(310, 80, 500, 410);
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
		
		
		double[][] x1 = blservice.getVariables(abb1, abb2);
		double score1=0,score2=0;
		
		for(int j=1;j<x1[0].length;j++) {
			score1+=co1[j]*x1[0][j];
			score2+=co1[j]*x1[1][j];
		}
		score1+=co1[0]; score2+=co1[0];
		System.out.println(score1+":"+score2);
		
		double[][] x2 = blservice.getDataForStrengthVariables(abb1, abb2);
		double differential=0;
		
		for(int j=1;j<x1[0].length;j++) {
			differential+=co2[j]*x1[0][j];
			
		}
		differential+=co2[0];
		System.out.println(score1+":"+score2+":"+differential);
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
		write("datax.txt","datay.txt",datas1);
		
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
		//double[][] datas = blservice.getDataForRegression(scale);
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
						ImageIcon picture = ImageHandle.loadTeam("NBA");
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk2.setIcon(picture);
					} else {
						//TeamWithPlayersVO teamvo = blservice.getTeamAnalysis();
						ImageIcon picture = ImageHandle.loadTeam(HotRankingPanel.translate(teamSelected));
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk1.setIcon(picture);
						
					}
				} else if (c == 'r') {
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						ImageIcon picture = ImageHandle.loadTeam("NBA");
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk2.setIcon(picture);
					} else {
						ImageIcon picture = ImageHandle.loadTeam(HotRankingPanel.translate(teamSelected));
						picture.setImage(picture.getImage().getScaledInstance(200, 200,
								Image.SCALE_DEFAULT));
						label_pk2.setIcon(picture);
						
					}
				}
				if(combox1.getSelectedIndex()!=0&&combox2.getSelectedIndex()!=0)
					update2(HotRankingPanel.translate((String)combox1.getSelectedItem()),
							HotRankingPanel.translate((String)combox2.getSelectedItem()));
				
			}
		}

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
