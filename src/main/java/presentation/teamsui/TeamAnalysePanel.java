package presentation.teamsui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
import presentation.playerui.Rotator;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

import javax.swing.JLabel;

public class TeamAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel pie;
	JPanel box;
	
	public static JScrollPane scrollPane;
	
	JButton button;
	
	String abbreviation;
	
	JLabel label ;
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
		panelOfBottom.setPreferredSize(new Dimension(1000, 2000));
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
		
		label = new JLabel("New label");
		label.setBounds(30, 91, 200, 200);
		panelOfBottom.add(label);
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
		pie.setBounds(310, 680, 500, 410);
		pie.setVisible(true);		
		pie.updateUI();
		
		if(box!=null){
			box.setVisible(false);
			box = null;
		}
		box = createDemoPanel_1();
		box.setBounds(50, 1200, 900, 400);
		box.setVisible(true);		
		box.updateUI();
		panelOfBottom.add(pie);
		panelOfBottom.add(box);
		panelOfBottom.repaint();
		
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
