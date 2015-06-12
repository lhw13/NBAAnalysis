package presentation.playerui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlayerAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel crosshair =null;
	JPanel crosshair2 =null;
	JPanel barchart =null;
	
	JButton button;

	JLabel label_action;
	public static JScrollPane scrollPane;
	JScrollPane scrollPane_1;
	
	String playerName;
	String teamName;
	
	private JTable table;
	
	DefaultTableModel model = new DefaultTableModel();
	
	Vector columnName = new Vector();
	public PlayerAnalysePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(320, 1030, 250, 68);
		panelOfBottom.add(scrollPane_1);
		//panel===========================================================
		panelOfBottom.setLayout(null);
		panelOfBottom.setPreferredSize(new Dimension(1000, 1200));
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
		
		
	//table=============================================================	
		table = new JTable(model);
		scrollPane_1.setViewportView(table);
		
		String[] cname = new String[] {"","得分","篮板","助攻"};
		for(int i=0;i<cname.length;i++) {
			columnName.add(cname[i]);
		}	
	}

	public void update(String pname) {
		playerName = pname;
		
		ImageIcon picture = ImageHandle.loadPlayerAct(pname);
		picture.setImage(picture.getImage().getScaledInstance(230, 365,
				Image.SCALE_DEFAULT));
		label_action.setIcon(picture);
		if(crosshair!=null) {
			crosshair.setVisible(false);
			crosshair = null;
		}
		if(crosshair2!=null) {
			crosshair2.setVisible(false);
			crosshair2 = null;
		}
		if(barchart!=null) {
			barchart.setVisible(false);
			barchart = null;
		}
		
		
		crosshair = createDemoPanel_2();
		crosshair.setBounds(220, 300, 650, 350);
		crosshair.setVisible(true);		
		crosshair.updateUI();
		
		crosshair2 = createDemoPanel_1();
		crosshair2.setBounds(80, 660, 770, 350);
		crosshair2.setVisible(true);		
		crosshair2.updateUI();
		
		String[] players = {playerName, "联盟平均"};
		barchart = createPanel_3(players);
		barchart.setBounds(200, 1, 700, 300);
		barchart.setVisible(true);		
		barchart.updateUI();
		panelOfBottom.add(crosshair);
		panelOfBottom.add(crosshair2);
		panelOfBottom.add(barchart);
		panelOfBottom.repaint();
		
		PlayerVO vo = blservice.getPlayerAnalysis(pname);
		Vector rowData_1 = new Vector();
		Vector rowDatas_1 = new Vector();
		rowData_1.add("均值");
		double score_avg = ((double)vo.getScore())/vo.getAppearance();
		rowData_1.add(handleDecimal(score_avg));
		double rebound_avg = ((double)vo.getTotalRebound())/vo.getAppearance();
		rowData_1.add(handleDecimal(rebound_avg));
		double assist_avg = ((double)vo.getAssist())/vo.getAppearance();
		rowData_1.add(handleDecimal(assist_avg));
		rowDatas_1.add(rowData_1);
		rowData_1 = new Vector();
		rowData_1.add("标准差");
		ArrayList<MatchPO> matches = vo.getMatches();
		int i=0;
		double scores=0,rebounds=0,assists=0;
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
			scores += (playerTemp.getScore()-score_avg)*(playerTemp.getScore()-score_avg);
			rebounds += (playerTemp. getTotalRebound()-rebound_avg)*(playerTemp.getTotalRebound()-rebound_avg);
			assists +=(playerTemp.getAssist()-assist_avg)*(playerTemp.getAssist()-assist_avg);
		}
		
		rowData_1.add(handleDecimal(Math.sqrt(scores/vo.getAppearance())));
		rowData_1.add(handleDecimal(Math.sqrt(rebounds/vo.getAppearance())));
		rowData_1.add(handleDecimal(Math.sqrt(assists/vo.getAppearance())));
		rowDatas_1.add(rowData_1);
		model.setDataVector(rowDatas_1, columnName);		
		model.setColumnCount(table.getColumnCount());
		model.setRowCount(rowDatas_1.size());
		table.setModel(model);
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.updateUI();
	}
	// 保留小数点
	public String handleDecimal(double f) {
		return String.format("%.1f", f);
	}

	private XYDataset createDataset_1()
	{
		
		XYSeries xyseries1 = new XYSeries("篮板");
		XYSeries xyseries2 = new XYSeries("助攻");
		
		PlayerVO vo = blservice.getPlayerAnalysis(playerName);
		ArrayList<MatchPO> matches = vo.getMatches();
		int i=0,index=1;

		double efficient=0;
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
//			efficient = playerTemp.getScore()+playerTemp.getTotalRebound()+
//					playerTemp.getAssist()+playerTemp.getSteal()+
//					playerTemp.getBlock()-playerTemp.getShot()+playerTemp.getHit()-
//					playerTemp.getFreeshot()+playerTemp.getFreeHit()-playerTemp.getMiss();
			xyseries1.add(index, playerTemp.getTotalRebound());
			xyseries2.add(index, playerTemp.getAssist());
			index++;
		}
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries1);
		xyseriescollection.addSeries(xyseries2);
		
		return xyseriescollection;
	}

	private static JFreeChart createChart_1(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("球员赛季每场篮板和助攻", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		
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

	public JPanel createDemoPanel_1()
	{
		JFreeChart jfreechart = createChart_1(createDataset_1());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
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
		chartpanel.setMouseWheelEnabled(false);
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
		double[] realHitRateArray = {handle((double)vo1.getRealHitRate()), 
				handle((double)avg_vo.getRealHitRate())};
		
		
		dataset.addValue(hitRateArray[0], players[0], "命中率");
		dataset.addValue(hitRateArray[1], players[1], "命中率");
		
		dataset.addValue(thirdRateArray[0], players[0], "三分命中率");
		dataset.addValue(thirdRateArray[1], players[1], "三分命中率");
		
		dataset.addValue(freeHitRateArray[0], players[0], "罚球命中率");
		dataset.addValue(freeHitRateArray[1], players[1], "罚球命中率");
	
		dataset.addValue(realHitRateArray[0], players[0], "真实命中率");
		dataset.addValue(realHitRateArray[1], players[1], "真实命中率");
	
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
