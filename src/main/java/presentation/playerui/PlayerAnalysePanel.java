package presentation.playerui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import blservice.BLService;

public class PlayerAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel pie =null;
	JPanel crosshair =null;
	JPanel barchart =null;
	
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
		if(barchart!=null) {
			barchart.setVisible(false);
			barchart = null;
		}
		pie = createDemoPanel();
		pie.setBounds(680, 20, 250, 200);
		pie.setVisible(true);		
		pie.updateUI();
		
		crosshair = createDemoPanel_2();
		crosshair.setBounds(220, 300, 650, 300);
		crosshair.setVisible(true);		
		crosshair.updateUI();
		
		String[] players = {playerName, "联盟平均"};
		barchart = createPanel_3(players);
		barchart.setBounds(200, 10, 500, 300);
		barchart.setVisible(true);		
		barchart.updateUI();
		panelOfBottom.add(pie);
		panelOfBottom.add(crosshair);
		panelOfBottom.add(barchart);
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
		defaultpiedataset.setValue("两分", new Double((vo.getHit()-vo.getThirdHit())*2));
		
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
	
	
	private static JFreeChart createChart_3(CategoryDataset dataset){//用数据集创建一个图表
		JFreeChart chart = ChartFactory.createBarChart3D( 
				"球员各项命中率", // 图表标题
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
		
		ValueMarker valuemarker = new ValueMarker(0.69999999999999996D, new Color(200, 200, 255), new BasicStroke(1.0F), new Color(200, 200, 255), new BasicStroke(1.0F), 1.0F);
		categoryplot.addRangeMarker(valuemarker, Layer.BACKGROUND);
		
		numberaxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
		numberaxis.setUpperMargin(0.10000000000000001D);
		ChartUtilities.applyCurrentTheme(chart);
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

	private  CategoryDataset createDataset_3(String[] players){//创建柱状图数据集

		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		PlayerVO vo1 = blservice.getPlayerAnalysis(playerName);
		PlayerVO avg_vo = blservice.getPlayerSeasonAvg();
		double[] hitRateArray = {handle((double)vo1.getHitRate()), 
				handle((double)avg_vo.getHitRate())};
		double[] thirdRateArray = {handle((double)vo1.getThirdHitRate()), 
				handle((double)avg_vo.getThirdHitRate())};
		double[] freeHitRateArray = {handle((double)vo1.getFreeHitRate()), 
				handle((double)avg_vo.getFreeHitRate())};
		
		dataset.addValue(hitRateArray[0], players[0], "命中率");
		dataset.addValue(hitRateArray[1], players[1], "命中率");
		
		dataset.addValue(thirdRateArray[0], players[0], "三分命中率");
		dataset.addValue(thirdRateArray[1], players[1], "三分命中率");
		
		dataset.addValue(freeHitRateArray[0], players[0], "罚球命中率");
		dataset.addValue(freeHitRateArray[1], players[1], "罚球命中率");
	
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
	static class CustomBarRenderer3D extends BarRenderer3D
	{

		public Paint getItemPaint(int i, int j)
		{
			CategoryDataset categorydataset = getPlot().getDataset();
			double d = categorydataset.getValue(i, j).doubleValue();
			if (d >= 0.69999999999999996D)
				return Color.green;
			else
				return Color.red;
		}

		public CustomBarRenderer3D()
		{
		}
	}
}
