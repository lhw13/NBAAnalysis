package presentation.teamsui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerAnalysePanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.Rotator;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import vo.PlayerVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

public class TeamAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();

	JPanel panelOfBottom = new JPanel();
	JPanel pie;
	
	public static JScrollPane scrollPane;
	
	JButton button;
	
	String abbreviation;
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
	}

	public void update(String abb) {
		this.abbreviation = abb;
		if(pie!=null){
			pie.setVisible(false);
			pie = null;
		}
		pie = createDemoPanel();
		pie.setBounds(110, 20, 350, 450);
		pie.setVisible(true);		
		pie.updateUI();
		
		panelOfBottom.add(pie);
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
		TeamWithPlayersVO vo = blservice.getTeamAnalysis(abbreviation);
		ArrayList<PlayerVO> players= vo.getPlayers();
		Collections.sort(players, compareEfficientDesc);  
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		for(int i=0;i<10;i++) {
			PlayerVO temp = players.get(i);
			System.out.println(temp.getName()+":"+temp.getEfficient());
			defaultpiedataset.setValue(temp.getName(), temp.getEfficient());
		}
		return defaultpiedataset;
	}

	
	public JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		Rotator rotator = new Rotator((PiePlot3D)jfreechart.getPlot());
		rotator.start();
		return new ChartPanel(jfreechart);
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
