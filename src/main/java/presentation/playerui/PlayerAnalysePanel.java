package presentation.playerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import blservice.BLService;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

public class PlayerAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel pie =null;
	JPanel crosshair =null;
	
	JButton button;

	JLabel label_action;
	public static JScrollPane scrollPane;

	 String playerName;
	String teamName;
	public PlayerAnalysePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

		//panel===========================================================
		panelOfBottom.setLayout(null);

		//button===========================================================
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);

				PlayerInfoPanel.scrollPane.setVisible(true);
				PlayerAnalysePanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA球员信息");
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
			}
		});
		panelOfBottom.add(button);

		//label==============================================================
		label_action = new JLabel("");
		label_action.setBounds(30, 88, 230, 365);
		panelOfBottom.add(label_action);
	}

	public void update(String pname) {
		playerName = pname;
		
		ImageIcon picture = ImageHandle.loadPlayerAct(pname);
		picture.setImage(picture.getImage().getScaledInstance(230, 365,
				Image.SCALE_DEFAULT));
		label_action.setIcon(picture);
		if(pie!=null){
			pie.setVisible(false);
			pie = null;
		}
		if(crosshair!=null) {
			crosshair.setVisible(false);
			crosshair = null;
		}
		pie = createDemoPanel();
		pie.setBounds(680, 20, 250, 200);
		pie.setVisible(true);		
		pie.updateUI();
		
		crosshair = createDemoPanel_2();
		crosshair.setBounds(220, 300, 650, 300);
		crosshair.setVisible(true);		
		crosshair.updateUI();
		panelOfBottom.add(pie);
		panelOfBottom.add(crosshair);
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

		JFreeChart jfreechart = ChartFactory.createPieChart3D("球员各项得分占比", piedataset, true, false, false);
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
		PlayerVO vo = blservice.getPlayerAnalysis(playerName);
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
	
		defaultpiedataset.setValue("三分", new Double(vo.getThirdHit()*3));
		defaultpiedataset.setValue("罚球", new Double(vo.getFreeHit()));
		defaultpiedataset.setValue("两分", new Double((vo.getHit()-vo.getThirdHit()-vo.getFreeHit())*2));
		
		return defaultpiedataset;
	}

	
	public JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		Rotator rotator = new Rotator((PiePlot3D)jfreechart.getPlot());
		rotator.start();
		return new ChartPanel(jfreechart);
	}
	
	
	private XYDataset createDataset_2()
	{
		
		XYSeries xyseries = new XYSeries("得分");
		PlayerVO vo = blservice.getPlayerAnalysis(playerName);
		ArrayList<MatchPO> matches = vo.getMatches();
		int i=0,index=1;
//		if(matches.size()>=10)
//			i=matches.size()-10;
//		else i=0;
		for(;i<matches.size();i++){
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
			
			xyseries.add(index, playerTemp.getScore());
			index++;
		}
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);

		return xyseriescollection;
	}

	private static JFreeChart createChart_2(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("球员赛季每场得分", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setTickLabelPaint(Color.white);
		TextTitle textTitle = jfreechart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		textTitle.setPaint(Color.white);
		
		ValueAxis domainAxis = xyplot.getDomainAxis();  
		domainAxis.setTickLabelPaint(Color.white);
		jfreechart.setBackgroundPaint(new Color(0.0F, 0.0F, 0.0F, 0.0F));
		return jfreechart;
	}

	public JPanel createDemoPanel_2()
	{
		JFreeChart jfreechart = createChart_2(createDataset_2());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}
}
