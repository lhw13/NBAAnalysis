package presentation.mainui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.MagmaSkin;

import blservice.BLService;
import presentation.matchui.MatchSelectionPanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.teamsui.TeamsSelectionFrame;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.BLController;
import server.po.MatchPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

public class MainFrame {

	public static JFrame frame;
	public static Panels currentPanel;
	public static JPanel panel;

	public static PlayerInfoPanel pip;
	public static PlayerSelectionPanel psp;

	public static boolean flag = false;
	public static boolean flag0 = false;
	public static boolean flag1 = false;
	public static boolean flag2 = false;
	public static boolean flag3 = false;
	public static boolean flag4 = false;
	private Timer timer;
	private final int INITIAL_DELAY = 100;
	private final int PERIOD_INTERVAL = 1;

	public int x1 = 0;
	public int x2 = -500;

	public int x3 = 0;
	public int x4 = -500;

	public int x5 = 500;
	public int x6 = 1000;

	public int x7 = 600;
	public int x8 = 1000;
	
	public int y9 = 0;
	public int y10 = -300;
	
	public int y11 = 300;
	public int y12 = 600;

	public JLabel lblNewLabel;
	public JLabel lblNewLabelBack;

	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_1Back;

	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_2Back;

	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_3Back;

	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_4Back;
	
	public JLabel lblNewLabel_5;
	public JLabel lblNewLabel_5Back;
	
	private static BLController compute;

	public static String table_1_columns[] = { "球队", "场数", "投篮命中数(场均)", "投篮命中数(总计)" };

	public static String table_2_columns[] = { "球员", "位置", "赛区", "分区", "得分(场均)", "得分(总计)" };
	
	private static String table_5_columns[] = { "赛季", "日期", "球队", "总比分", "第一节", "第二节", "第三节", "第四节", "详情" };
	
	public static String selection1="得分";
	public static String selection2="投篮命中数";
	
	public static String season="13-14";
	public static int date=3;

	BLService blservice = BLController.getInstance();
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			if (System.getProperty("substancelaf.useDecorations") == null) {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				/**
				 * setDefaultLookAndFeelDecorated设置为true或者false
				 * JDialog也可以使用setDefaultLookAndFeelDecorated
				 */
			}

			// 设置当前的主题风格
			SubstanceLookAndFeel.setSkin(new MagmaSkin());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		initPanels();
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY,
				PERIOD_INTERVAL);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initPanels() {

		//初始化球员选择面板
		currentPanel = Panels.MainFrame;
		psp = new PlayerSelectionPanel();
		frame.getContentPane().add(PlayerSelectionPanel.scrollPane);
		PlayerSelectionPanel.scrollPane.setVisible(false);

		//初始化球员信息面板
		pip = new PlayerInfoPanel();
		frame.getContentPane().add(PlayerInfoPanel.scrollPane);
		PlayerInfoPanel.scrollPane.setVisible(false);
	}

	private class ScheduleTask extends TimerTask {
		public void run() {
			if (flag) {
				if (x1 > -400) {
					x1--;
					lblNewLabel.setLocation(x1, 0);
				}
				if (x2 < 0) {
					x2++;
					lblNewLabelBack.setLocation(x2, 0);

				}
			} else {
				if (x1 < 0) {
					x1++;
					lblNewLabel.setLocation(x1, 0);
				}
				if (x2 > -400) {
					x2--;
					lblNewLabelBack.setLocation(x2, 0);

				}
			}
 
			if (flag0) {
				if (x3 > -500) {
					x3--;
					lblNewLabel_1.setLocation(x3, 300);
				}
				if (x4 < 0) {
					x4++;
					lblNewLabel_1Back.setLocation(x4, 300);
				}
			} else {
				if (x3 < 0) {
					x3++;
					lblNewLabel_1.setLocation(x3, 300);
				}
				if (x4 > -500) {
					x4--;
					lblNewLabel_1Back.setLocation(x4, 300);

				}
			}

			if (flag1) {
				if (x5 < 1000) {
					x5++;
					lblNewLabel_2.setLocation(x5, 0);
				}
				if (x6 > 600) {
					x6--;
					lblNewLabel_2Back.setLocation(x6, 0);
				}
			} else {
				if (x5 > 600) {
					x5--;
					lblNewLabel_2.setLocation(x5, 0);
				}
				if (x6 < 1000) {
					x6++;
					lblNewLabel_2Back.setLocation(x6, 0);

				}
			}

			if (flag2) {
				if (x7 < 1000) {
					x7++;
					lblNewLabel_3.setLocation(x7, 300);
				}
				if (x8 > 600) {
					x8--;
					lblNewLabel_3Back.setLocation(x8, 300);
				}
			} else {
				if (x7 > 600) {
					x7--;
					lblNewLabel_3.setLocation(x7, 300);
				}
				if (x8 < 1000) {
					x8++;
					lblNewLabel_3Back.setLocation(x8, 300);

				}
			}
			
			if (flag3) {
				if (y9 > -300) {
					y9--;
					lblNewLabel_4.setLocation(400, y9);
				}
				if (y10 < 0) {
					y10++;
					lblNewLabel_4Back.setLocation(400, y10);

				}
			} else {
				if (y9 < 0) {
					y9++;
					lblNewLabel_4.setLocation(400, y9);
				}
				if (y10 > -300) {
					y10--;
					lblNewLabel_4Back.setLocation(400, y10);

				}
			}
			
			if (flag4) {
				if (y11 < 600) {
					y11++;
					lblNewLabel_5.setLocation(400, y11);
				}
				if (y12 > 300) {
					y12--;
					lblNewLabel_5Back.setLocation(400, y12);

				}
			} else {
				if (y11 > 300) {
					y11--;
					lblNewLabel_5.setLocation(400, y11);
				}
				if (y12 < 600) {
					y12++;
					lblNewLabel_5Back.setLocation(400, y12);

				}
			}

		}
	}

	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"pictures\\NBA.jpg"));
		frame.setTitle("NBA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 600);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setSize(400, 300);

		ImageIcon ii = new ImageIcon("pictures\\P1.png");
		lblNewLabel.setIcon(ii);
		panel.add(lblNewLabel);

		lblNewLabel.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				flag = true;
				flag0 = false;
				flag1 = false;
				flag2 = false;
				flag3 = false;
				flag4 = false;
			}

			public void mouseExited(java.awt.event.MouseEvent e) {

			}

		});

		lblNewLabelBack = new JLabel("");
		lblNewLabelBack.setLocation(-400, 0);
		lblNewLabelBack.setSize(400, 300);
		ImageIcon ii0 = new ImageIcon("pictures\\P1_1.png");
		lblNewLabelBack.setIcon(ii0);
		panel.add(lblNewLabelBack);

		lblNewLabelBack.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//初始化球队选择面板
					MainFrame.panel.setVisible(false);
					TeamsSelectionFrame tsp = new TeamsSelectionFrame();
					frame.getContentPane().add(tsp.scrollPane);
					tsp.scrollPane.setVisible(true);
					tsp.flag = true;
					MainFrame.frame.setTitle("NBA球队选择");
					currentPanel = Panels.TeamsSelectionFrame;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabelBack.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabelBack.setCursor(Cursor.getDefaultCursor());

			}
		});

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("pictures\\P2.PNG"));
		lblNewLabel_1.setBounds(0, 300, 400, 300);
		panel.add(lblNewLabel_1);

		lblNewLabel_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				flag0 = true;
				flag = false;
				flag1 = false;
				flag2 = false;
				flag3 = false;
				flag4 = false;
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		lblNewLabel_1Back = new JLabel("");
		lblNewLabel_1Back.setLocation(-400, 300);
		lblNewLabel_1Back.setSize(400, 300);
		ImageIcon ii1 = new ImageIcon("pictures\\P2_1.png");
		lblNewLabel_1Back.setIcon(ii1);
		panel.add(lblNewLabel_1Back);

		lblNewLabel_1Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setTeamsRanking();
					currentPanel = Panels.TeamsRankingFrame;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1Back.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1Back.setCursor(Cursor.getDefaultCursor());

			}
		});

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setLocation(600, 0);
		lblNewLabel_2.setSize(400, 300);
		ImageIcon ii2 = new ImageIcon("pictures\\P3.png");
		lblNewLabel_2.setIcon(ii2);
		panel.add(lblNewLabel_2);

		lblNewLabel_2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				flag1 = true;
				flag0 = false;
				flag = false;
				flag2 = false;
				flag3 = false;
				flag4 = false;

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		lblNewLabel_2Back = new JLabel("");
		lblNewLabel_2Back.setLocation(1000, 0);
		lblNewLabel_2Back.setSize(400, 300);
		ImageIcon ii3 = new ImageIcon("pictures\\P3_1.png");
		lblNewLabel_2Back.setIcon(ii3);
		panel.add(lblNewLabel_2Back);

		lblNewLabel_2Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					psp.update();
					PlayerSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员选择");
					currentPanel = Panels.PlayerSelectionPanel;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2Back.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2Back.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setLocation(600, 300);
		lblNewLabel_3.setSize(400, 300);
		ImageIcon ii4 = new ImageIcon("pictures\\P4.png");
		lblNewLabel_3.setIcon(ii4);
		panel.add(lblNewLabel_3);

		lblNewLabel_3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				flag2 = true;
				flag1 = false;
				flag0 = false;
				flag = false;
				flag3 = false;
				flag4 = false;

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		lblNewLabel_3Back = new JLabel("");
		lblNewLabel_3Back.setLocation(1000, 500);
		lblNewLabel_3Back.setSize(400, 300);
		ImageIcon ii5 = new ImageIcon("pictures\\P4_1.png");
		lblNewLabel_3Back.setIcon(ii5);
		panel.add(lblNewLabel_3Back);

		lblNewLabel_3Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setPlayersRanking();
					currentPanel = Panels.PlayerRankingPanel;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3Back.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3Back.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setLocation(400, 0);
		lblNewLabel_4.setSize(200, 300);
		ImageIcon ii6 = new ImageIcon("pictures\\P5.png");
		lblNewLabel_4.setIcon(ii6);
		panel.add(lblNewLabel_4);
		 
		lblNewLabel_4.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				flag3 = true;
				flag1 = false;
				flag0 = false;
				flag = false;
				flag2 = false;
				flag4 = false;

			}

			public void mouseExited(java.awt.event.MouseEvent e) {

			}

		});
		
		lblNewLabel_4Back = new JLabel("");
		lblNewLabel_4Back.setLocation(400, -300);
		lblNewLabel_4Back.setSize(200, 300);
		ImageIcon ii7 = new ImageIcon("pictures\\P5_1.png");
		lblNewLabel_4Back.setIcon(ii7);
		panel.add(lblNewLabel_4Back);
		
		lblNewLabel_4Back.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					searchTheMatch();
					currentPanel = Panels.MatchSelectionInfoPanel;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4Back.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				lblNewLabel_4Back.setCursor(Cursor.getDefaultCursor());
			}

		});
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setLocation(400, 300);
		lblNewLabel_5.setSize(200, 300);
		ImageIcon ii8 = new ImageIcon("pictures\\P6.png");
		lblNewLabel_5.setIcon(ii8);
		panel.add(lblNewLabel_5);
		 
		lblNewLabel_5.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				flag4=true;
				flag3 = false;
				flag1 = false;
				flag0 = false;
				flag = false;
				flag2 = false;

			}

			public void mouseExited(java.awt.event.MouseEvent e) {

			}

		});
		
		lblNewLabel_5Back = new JLabel("");
		lblNewLabel_5Back.setLocation(400, 600);
		lblNewLabel_5Back.setSize(200, 300);
		ImageIcon ii9 = new ImageIcon("pictures\\P6_1.png");
		lblNewLabel_5Back.setIcon(ii9);
		panel.add(lblNewLabel_5Back);
		
		lblNewLabel_5Back.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					HotRankingPanel hrp = new HotRankingPanel();
					MainFrame.panel.setVisible(false);
					frame.getContentPane().add(hrp.scrollPane);
					hrp.update1(blservice.getDailyHotPlayerVO("point", 5), "point");
					hrp.update2(blservice.getBestPromotion("point", 5), "point");
					hrp.updateTeam(blservice.getHotTeamVO("point", 5), "point");
					hrp.update4(blservice.getHotPlayerVO("point", 5), "point");					
					frame.repaint();//刷新重画 
					frame.validate();//保证重画后的窗口能正常立即显示 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				lblNewLabel_5Back.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				lblNewLabel_5Back.setCursor(Cursor.getDefaultCursor());
			}

		});
		

		frame.getContentPane().add(panel);

		frame.repaint();

	}

	// 设置查询比赛面板信息
	public static void searchTheMatch(){
		MainFrame.panel.setVisible(false);
		MainFrame.frame.setTitle("NBA比赛查询");
		
		compute = BLController.getInstance();
		ArrayList<MatchPO> matchList = compute.getAllMatch();
		
		ArrayList<MatchPO> selectedMatchs = new ArrayList<MatchPO>();
		for(int i=0;i<matchList.size();i++){
			if(matchList.get(i).getSeason().equals(season) && 
					matchList.get(i).getDate().get(Calendar.MONTH)==date){
				selectedMatchs.add(matchList.get(i));
			}
		}
		Object table_rows[][] = new Object[selectedMatchs.size()][9];
		for(int i=0;i<selectedMatchs.size();i++){
			table_rows[i][0] = selectedMatchs.get(i).getSeason();
			table_rows[i][1] = (selectedMatchs.get(i).getDate().get(Calendar.MONTH)+1)+"-"+
					selectedMatchs.get(i).getDate().get(Calendar.DAY_OF_MONTH);
			table_rows[i][2] = selectedMatchs.get(i).getTeam1().getAbbreviation()+"-"+
					selectedMatchs.get(i).getTeam2().getAbbreviation();
			table_rows[i][3] = selectedMatchs.get(i).getFinalScore().getTeam1()+"-"+
					selectedMatchs.get(i).getFinalScore().getTeam2();
			table_rows[i][4] = selectedMatchs.get(i).getScores().get(0).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(0).getTeam2();
			table_rows[i][5] = matchList.get(i).getScores().get(1).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(1).getTeam2();
			table_rows[i][6] = selectedMatchs.get(i).getScores().get(2).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(2).getTeam2();
			table_rows[i][7] = selectedMatchs.get(i).getScores().get(3).getTeam1()+"-"+
					selectedMatchs.get(i).getScores().get(3).getTeam2();
			table_rows[i][8] = "详情";
		}
		
		DefaultTableModel model = new DefaultTableModel(table_rows,table_5_columns);
		
		if(MatchSelectionPanel.scrollPane!=null){
			frame.getContentPane().remove(MatchSelectionPanel.scrollPane);
			MatchSelectionPanel.scrollPane=null;
			MatchSelectionPanel msp = new MatchSelectionPanel(model, selectedMatchs);
			frame.getContentPane().add(msp.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
		}else{
			MatchSelectionPanel msp = new MatchSelectionPanel(model, selectedMatchs);
			frame.getContentPane().add(msp.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
		}
		
	}
	
	// 设置球队排名面板信息
	public static void setTeamsRanking() {
		compute = BLController.getInstance();
		ArrayList<TeamVO> tvoList = compute.getTeamAnalysis();

		Object table_rows[][] = new Object[tvoList.size()][4];
		for (int i = 0; i < tvoList.size(); i++) {
			TeamVO tvo = tvoList.get(i);
			int appearance = tvo.getAppearance();
			
			table_rows[i][0] = tvo.getFullName();
			table_rows[i][1] = tvo.getAppearance();
			switch(selection2){
			case "投篮命中数":
				table_rows[i][3] = tvo.getHit();
				table_rows[i][2] = handle((double) tvo.getHit(), appearance);
				break;
			case "投篮出手数":
				table_rows[i][3] = tvo.getShot();
				table_rows[i][2] = handle((double) tvo.getShot(), appearance);
				break;
			case "三分命中数":
				table_rows[i][3] = tvo.getThirdHit();
				table_rows[i][2] = handle((double) tvo.getThirdHit(), appearance);
				break;
			case "三分出手数":
				table_rows[i][3] = tvo.getThirdshot();
				table_rows[i][2] = handle((double) tvo.getThirdshot(), appearance);
				break;
			case "罚球命中数":
				table_rows[i][3] = tvo.getFreeHit();
				table_rows[i][2] = handle((double) tvo.getFreeHit(), appearance);
				break;
			case "罚球出手数":
				table_rows[i][3] = tvo.getFreeshot();
				table_rows[i][2] = handle((double) tvo.getFreeshot(), appearance);
				break;
			case "进攻篮板":
				table_rows[i][3] = tvo.getOffensiveRebound();
				table_rows[i][2] = handle((double) tvo.getOffensiveRebound(),appearance);
				break;
			case "防守篮板":
				table_rows[i][3] = tvo.getDefensiveRebound();
				table_rows[i][2] = handle((double) tvo.getDefensiveRebound(),appearance);
				break;
			case "总篮板":
				table_rows[i][3] = tvo.getTotalRebound();
				table_rows[i][2] = handle((double) tvo.getTotalRebound(),appearance);
				break;
			case "助攻":
				table_rows[i][3] = tvo.getAssist();
				table_rows[i][2] = handle((double) tvo.getAssist(), appearance);
				break;
			case "抢断":
				table_rows[i][3] = tvo.getSteal();
				table_rows[i][2] = handle((double) tvo.getSteal(), appearance);
				break;
			case "盖帽":
				table_rows[i][3] = tvo.getBlock();
				table_rows[i][2] = handle((double) tvo.getBlock(), appearance);
				break;
			case "失误":
				table_rows[i][3] = tvo.getMiss();
				table_rows[i][2] = handle((double) tvo.getMiss(), appearance);
				break;
			case "犯规":
				table_rows[i][3] = tvo.getFoul();
				table_rows[i][2] = handle((double) tvo.getFoul(), appearance);
				break;
			case "得分":
				table_rows[i][3] = tvo.getScore();
				table_rows[i][2] = handle((double) tvo.getScore(), appearance);
				break;
			case "投篮命中率":
				table_rows[i][3] = tvo.getHitRate();
				table_rows[i][2] = tvo.getHitRate();
				break;
			case "三分命中率":
				table_rows[i][3] = tvo.getThirdHitRate();
				table_rows[i][2] = tvo.getThirdHitRate();
				break;
			case "罚球命中率":
				table_rows[i][3] = tvo.getFreeHitRate();
				table_rows[i][2] = tvo.getFreeHitRate();
				break;
			case "胜率":
				table_rows[i][3] = tvo.getWinRate();
				table_rows[i][2] = tvo.getWinRate();
				break;
			case "进攻回合":
				table_rows[i][3] = tvo.getOffensiveRound();
				table_rows[i][2] = tvo.getOffensiveRound();
				break;
			case "进攻效率":
				table_rows[i][3] = tvo.getOffensiveEfficiency();
				table_rows[i][2] = tvo.getOffensiveEfficiency();
				break;
			case "防守效率":
				table_rows[i][3] = tvo.getDefensiveEfficiency();
				table_rows[i][2] = tvo.getDefensiveEfficiency();
				break;
			case "进攻篮板效率":
				table_rows[i][3] = tvo.getOffensiveReboundEfficiency();
				table_rows[i][2] = tvo.getOffensiveReboundEfficiency();
				break;
			case "防守篮板效率":
				table_rows[i][3] = tvo.getDefensiveReboundEfficiency();
				table_rows[i][2] = tvo.getDefensiveReboundEfficiency();
				break;
			case "抢断效率":
				table_rows[i][3] = tvo.getStealEfficiency();
				table_rows[i][2] = tvo.getStealEfficiency();
				break;
			case "助攻率":
				table_rows[i][3] = tvo.getAssistEfficiency();
				table_rows[i][2] = tvo.getAssistEfficiency();
				break;
			}
		}

		DefaultTableModel model = new DefaultTableModel(table_rows,
				table_1_columns) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		
		
		if(TeamsRankingFrame.scrollPane!=null){
			TeamsRankingFrame.scrollPane.setVisible(false);
			MainFrame.frame.getContentPane().remove(TeamsRankingFrame.scrollPane);
			TeamsSelectionFrame.scrollPane=null;
			TeamsRankingFrame trp = new TeamsRankingFrame(model);
			frame.getContentPane().add(trp.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
		}else{
			MainFrame.panel.setVisible(false);
			MainFrame.frame.setTitle("NBA球队排名");
			TeamsRankingFrame trp = new TeamsRankingFrame(model);
			frame.getContentPane().add(trp.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
		}
		
	}
	
	// 设置球员排名面板信息
	public static void setPlayersRanking() {
		MainFrame.panel.setVisible(false);
		MainFrame.frame.setTitle("NBA球员排名");

		compute = BLController.getInstance();
		ArrayList<PlayerVO> pvoList = compute.getPlayerAnalysis();
		PlayerVO pvo = new PlayerVO();
		String[] s = new String[3];
		Object table_rows[][] = new Object[pvoList.size()][6];
		for (int i = 0; i < pvoList.size(); i++) {
			if (pvoList.get(i) != null) {
				pvo = pvoList.get(i);
				int appearance = pvo.getAppearance();
				table_rows[i][0] = pvo.getName();
				if (pvo.getPosition() != null) {
					s = JudeTheFilter(pvo.getPosition(), pvo.getDivision(),
							pvo.getZone());
				}
				table_rows[i][1] = s[0];
				table_rows[i][2] = s[1];
				table_rows[i][3] = s[2];
				switch(selection1){
				case "得分":
					table_rows[i][5] = pvo.getScore();
					table_rows[i][4] = handle((double) pvo.getScore(), appearance);
					break;
				case "篮板":
					table_rows[i][5] = pvo.getTotalRebound();
					table_rows[i][4] = handle((double) pvo.getTotalRebound(),appearance);
					break;
				case "助攻":
					table_rows[i][5] = pvo.getAssist();
					table_rows[i][4] = handle((double) pvo.getAssist(), appearance);
					break;
				case "得分/篮板/助攻":
					table_rows[i][5] = (pvo.getScore()+pvo.getAssist()+pvo.getTotalRebound())*(0.33333);
					table_rows[i][4] = handle((double) (pvo.getScore()+pvo.getAssist()+pvo.getTotalRebound())*(0.33333), appearance);
					break;
				case "盖帽":
					table_rows[i][5] = pvo.getBlock();
					table_rows[i][4] = handle((double) pvo.getBlock(), appearance);
					break;
				case "抢断":
					table_rows[i][5] = pvo.getSteal();
					table_rows[i][4] = handle((double) pvo.getSteal(), appearance);
					break;
				case "犯规":
					table_rows[i][5] = pvo.getFoul();
					table_rows[i][4] = handle((double) pvo.getFoul(), appearance);
					break;
				case "失误":
					table_rows[i][5] = pvo.getMiss();
					table_rows[i][4] = handle((double) pvo.getMiss(), appearance);
					break;
				case "分钟":
					table_rows[i][5] = pvo.getPlayTime();
					table_rows[i][4] = handle((double) pvo.getPlayTime(),appearance);
					break;
				case "效率":
					table_rows[i][5] = pvo.getEfficiency();
					table_rows[i][4] = pvo.getEfficiency();
					break;
				case "投篮":
					table_rows[i][5] = pvo.getHitRate();
					table_rows[i][4] = pvo.getHitRate();
					break;
				case "三分":
					table_rows[i][5] = pvo.getThirdHitRate();
					table_rows[i][4] = pvo.getThirdHitRate();
					break;
				case "罚球":
					table_rows[i][5] = pvo.getFreeHitRate();
					table_rows[i][4] = pvo.getFreeHitRate();
					break;
				case "两双":
					table_rows[i][5] = pvo.getTowPairs();
					table_rows[i][4] = handle((double) pvo.getTowPairs(),appearance);
					break;
				}
				
			}
		}

		DefaultTableModel model = new DefaultTableModel(table_rows,
				table_2_columns) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};

		if(PlayerRankingPanel.scrollPane!=null){
			PlayerRankingPanel.scrollPane.setVisible(false);
			MainFrame.frame.getContentPane().remove(PlayerRankingPanel.scrollPane);
			PlayerRankingPanel.scrollPane=null;
			PlayerRankingPanel prp = new PlayerRankingPanel(model);
			frame.getContentPane().add(prp.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
		}else{
			MainFrame.panel.setVisible(false);
			MainFrame.frame.setTitle("NBA球员排名");
			PlayerRankingPanel prp = new PlayerRankingPanel(model);
			frame.getContentPane().add(PlayerRankingPanel.scrollPane);
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
		}
		
		
	}

	public static String[] JudeTheFilter(String position, char division, String zone) {
		String s[] = new String[3];
		if (position.equals("F")) {
			s[0] = "前锋";
		} else if (position.equals("C")) {
			s[0] = "中锋";
		} else if (position.equals("G")) {
			s[0] = "后卫";
		} else if (position.equals("F-C")) {
			s[0] = "前锋-中锋";
		} else if (position.equals("F-G")) {
			s[0] = "前锋-后卫";
		} else if (position.equals("C-F")) {
			s[0] = "中锋-前锋";
		} else if (position.equals("C-G")) {
			s[0] = "中锋-后卫";
		} else if (position.equals("G-F")) {
			s[0] = "后卫-前锋";
		} else if (position.equals("G-C")) {
			s[0] = "后卫-中锋";
		}
		switch (division) {
		case 'E':
			s[1] = "东部";
			break;
		case 'W':
			s[1] = "西部";
			break;
		}
		if (zone.equals("Southeast")) {
			s[2] = "东南区";
		} else if (zone.equals("Southwest")) {
			s[2] = "西南区";
		} else if (zone.equals("Northwest")) {
			s[2] = "西北区";
		} else if (zone.equals("Atlantic")) {
			s[2] = "大西洋区";
		} else if (zone.equals("Central")) {
			s[2] = "中区";
		} else if (zone.equals("Pacific")) {
			s[2] = "太平洋区";
		} else if (zone.equals("Pacific")) {
			s[2] = "太平洋区";
		}
		return s;
	}


	public static double handle(double a, int b) {
		double result = a / (double) b;
		Double r = new Double(result);
		if(result!=0&&!r.isNaN()&&!r.isInfinite()) {
			BigDecimal bg = new BigDecimal(result);
			result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return result;
	}
}
