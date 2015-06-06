package presentation.mainui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
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
import org.jvnet.substance.border.FlatInnerBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.MagmaSkin;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.Glass3DTitlePainter;
import org.jvnet.substance.watermark.SubstanceWoodWatermark;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.matchui.MatchPlayoffPanel;
import presentation.matchui.MatchSelectionPanel;
import presentation.playerui.PlayerAnalysePanel;
import presentation.playerui.PlayerComparePanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsSelectionFrame;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.BLController;
import server.po.MatchPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

public class MainFrame {

	public static JFrame frame;
	public static Panels currentPanel=Panels.MainFrame;
	public static ArrayList<Panels> backPanels;
	public static JPanel panel;
	
	public static PlayerInfoPanel pip;
	public static PlayerSelectionPanel psp;
	public static HotRankingPanel hrp;
	public static PlayerComparePanel pcp;
	public static MatchPlayoffPanel mpp;
	public static PlayerAnalysePanel pap;
	
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

	public int x5 = 600;
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
	
	public static String selection="得分";
	public static String selection1="得分";
	public static String selection2="胜率";
	
	public static String season="13-14";
	public static int date=3;
	
	public static String teamNAME = "";
	public static TeamWithPlayersVO TWPVO;

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

			 //设置当前的主题风格
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
		psp = new PlayerSelectionPanel();
		frame.getContentPane().add(PlayerSelectionPanel.scrollPane);
		PlayerSelectionPanel.scrollPane.setVisible(false);

		//初始化球员信息面板
		pip = new PlayerInfoPanel();
		frame.getContentPane().add(PlayerInfoPanel.scrollPane);
		PlayerInfoPanel.scrollPane.setVisible(false);
		
		hrp = new HotRankingPanel();
		frame.getContentPane().add(HotRankingPanel.scrollPane);
		HotRankingPanel.scrollPane.setVisible(false);
		
		pcp = new PlayerComparePanel();
		frame.getContentPane().add(PlayerComparePanel.scrollPane);
		PlayerComparePanel.scrollPane.setVisible(false);
		
		mpp = new MatchPlayoffPanel();
		frame.getContentPane().add(MatchPlayoffPanel.scrollPane);
		MatchPlayoffPanel.scrollPane.setVisible(false);
		
		pap = new PlayerAnalysePanel();
		frame.getContentPane().add(PlayerAnalysePanel.scrollPane);
		PlayerAnalysePanel.scrollPane.setVisible(false);
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
		backPanels = new ArrayList<Panels>();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"conf\\pictures\\NBA.jpg"));
		frame.setTitle("NBA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 600);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setSize(400, 300);

		ImageIcon ii = new ImageIcon("conf\\pictures\\P1.png");
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
		ImageIcon ii0 = new ImageIcon("conf\\pictures\\P1_1.png");
		lblNewLabelBack.setIcon(ii0);
		panel.add(lblNewLabelBack);

		lblNewLabelBack.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//初始化球队选择面板
					if(TeamsSelectionFrame.scrollPane!=null){
						MainFrame.panel.setVisible(false);
						TeamsSelectionFrame.scrollPane.setVisible(true);
						TeamsSelectionFrame.flag = true;
						MainFrame.frame.setTitle("NBA球队选择");
						backPanels.add(currentPanel);
						currentPanel = Panels.TeamsSelectionFrame;
					}
					else{
						MainFrame.panel.setVisible(false);
						TeamsSelectionFrame tsp = new TeamsSelectionFrame();
						frame.getContentPane().add(tsp.scrollPane);
						tsp.scrollPane.setVisible(true);
						tsp.flag = true;
						MainFrame.frame.setTitle("NBA球队选择");
						backPanels.add(currentPanel);
						currentPanel = Panels.TeamsSelectionFrame;
					}
					
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
		lblNewLabel_1.setIcon(new ImageIcon("conf\\pictures\\P2.PNG"));
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
		ImageIcon ii1 = new ImageIcon("conf\\pictures\\P2_1.png");
		lblNewLabel_1Back.setIcon(ii1);
		panel.add(lblNewLabel_1Back);

		lblNewLabel_1Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setTeamsRanking();
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
		ImageIcon ii2 = new ImageIcon("conf\\pictures\\P3.png");
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
		ImageIcon ii3 = new ImageIcon("conf\\pictures\\P3_1.png");
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
					backPanels.add(currentPanel);
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
		ImageIcon ii4 = new ImageIcon("conf\\pictures\\P4.png");
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
		ImageIcon ii5 = new ImageIcon("conf\\pictures\\P4_1.png");
		lblNewLabel_3Back.setIcon(ii5);
		panel.add(lblNewLabel_3Back);

		lblNewLabel_3Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setPlayersRanking();
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
		ImageIcon ii6 = new ImageIcon("conf\\pictures\\P5.png");
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
		ImageIcon ii7 = new ImageIcon("conf\\pictures\\P5_1.png");
		lblNewLabel_4Back.setIcon(ii7);
		panel.add(lblNewLabel_4Back);
		
		lblNewLabel_4Back.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					searchTheMatch();
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
		ImageIcon ii8 = new ImageIcon("conf\\pictures\\P6.png");
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
		ImageIcon ii9 = new ImageIcon("conf\\pictures\\P6_1.png");
		lblNewLabel_5Back.setIcon(ii9);
		panel.add(lblNewLabel_5Back);
		
		lblNewLabel_5Back.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					
					MainFrame.panel.setVisible(false);
					frame.getContentPane().add(hrp.scrollPane);
					backPanels.add(currentPanel);
					currentPanel = Panels.HotRankingPanel;
					hrp.update1();	
					hrp.update2();
					hrp.update3();
					hrp.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("今日快讯");
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
		
		if(MatchSelectionPanel.scrollPane!=null){
			MainFrame.panel.setVisible(false);
			MatchSelectionPanel.scrollPane.setVisible(true);
			MatchSelectionPanel.update();
			MainFrame.frame.setTitle("NBA比赛查询");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
			backPanels.add(currentPanel);
			currentPanel = Panels.MatchSelectionPanel;
		}else{
			MainFrame.panel.setVisible(false);
			MatchSelectionPanel msp = new MatchSelectionPanel();
			msp.update();
			frame.getContentPane().add(msp.scrollPane);
			MainFrame.frame.setTitle("NBA比赛查询");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
			backPanels.add(currentPanel);
			currentPanel = Panels.MatchSelectionPanel;
		}
		
	}
	
	// 设置球队排名面板信息
	public static void setTeamsRanking() {
		
		if(TeamsRankingFrame.scrollPane!=null){
			MainFrame.panel.setVisible(false);
			TeamsRankingFrame.scrollPane.setVisible(true);
			TeamsRankingFrame.updataTeamsRanking();
			frame.setTitle("NBA球队排名");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
			backPanels.add(currentPanel);
			currentPanel = Panels.TeamsRankingFrame;
		}else{
			MainFrame.panel.setVisible(false);
			TeamsRankingFrame trp = new TeamsRankingFrame();
			trp.updataTeamsRanking();
			frame.getContentPane().add(trp.scrollPane);
			frame.setTitle("NBA球队排名");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
			backPanels.add(currentPanel);
			currentPanel = Panels.TeamsRankingFrame;
		}
		
	}
	
	// 设置球员排名面板信息
	public static void setPlayersRanking() {

		if(PlayerRankingPanel.scrollPane!=null){
			MainFrame.panel.setVisible(false);
			PlayerRankingPanel.scrollPane.setVisible(true);
			PlayerRankingPanel.updatePlayerRanking();
			MainFrame.frame.setTitle("NBA球员排名");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示
			backPanels.add(currentPanel);
			currentPanel = Panels.PlayerRankingPanel;
		}else{
			MainFrame.panel.setVisible(false);
			PlayerRankingPanel prp = new PlayerRankingPanel();
			prp.updatePlayerRanking();
			frame.getContentPane().add(PlayerRankingPanel.scrollPane);
			MainFrame.frame.setTitle("NBA球员排名");
			frame.repaint();//刷新重画 
			frame.validate();//保证重画后的窗口能正常立即显示 
			backPanels.add(currentPanel);
			currentPanel = Panels.PlayerRankingPanel;
		}
		
		
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
	
	public static void refresh() {
		switch(currentPanel) {
		case PlayerInfoPanel: pip.refresh(pip.playerName); break;
		case PlayerSelectionPanel: psp.update();break;
		case MatchSelectionPanel: MatchSelectionPanel.update();break;
		case PlayerRankingPanel: PlayerRankingPanel.updatePlayerRanking();break;
		case TeamsInfoFrame: 
			TeamsInfoFrame.updateTeam(TWPVO, MainFrame.selection);
			TeamsInfoFrame.latestMatchs(teamNAME);
			break;
		case TeamsRankingFrame: TeamsRankingFrame.updataTeamsRanking();break;
		case HotRankingPanel: hrp.update1();hrp.update2();hrp.update3();break;
		}
	}
}
