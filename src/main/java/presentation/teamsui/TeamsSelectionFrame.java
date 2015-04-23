package presentation.teamsui;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.teamsui.TeamsInfoFrame;
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import hotui.HotRankingPanel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * 球队选择面板
 */
public class TeamsSelectionFrame {
	public static Timer timer;
	private final int INITIAL_DELAY = 100;
	private final int PERIOD_INTERVAL = 10;
	private static JLabel changeLabel;
	private static JLabel changeLabel_1;
	private static JLabel changeLabel_2;
	private static JLabel changeLabel_3;
	private static JLabel changeLabel_4;
	private static JLabel changeLabel_5;

	public static JScrollPane scrollPane;

	private static JLabel lblNewLabel_1;
	private static JLabel lblNewLabel_2;
	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_4;
	private static JLabel lblNewLabel_5;
	private static JLabel lblNewLabel_6;
	private static JLabel lblNewLabel_7;
	private static JLabel lblNewLabel_8;
	private static JLabel lblNewLabel_9;
	private static JLabel lblNewLabel_10;
	private static JLabel lblNewLabel_11;
	private static JLabel lblNewLabel_12;
	private static JLabel lblNewLabel_13;
	private static JLabel lblNewLabel_14;
	private static JLabel lblNewLabel_15;
	private static JLabel lblNewLabel_16;
	private static JLabel lblNewLabel_17;
	private static JLabel lblNewLabel_18;
	private static JLabel lblNewLabel_19;
	private static JLabel lblNewLabel_20;
	private static JLabel lblNewLabel_21;
	private static JLabel lblNewLabel_22;
	private static JLabel lblNewLabel_23;
	private static JLabel lblNewLabel_24;
	private static JLabel lblNewLabel_25;
	private static JLabel lblNewLabel_26;
	private static JLabel lblNewLabel_27;
	private static JLabel lblNewLabel_28;
	private static JLabel lblNewLabel_29;
	private static JLabel lblNewLabel_30;

	public static boolean flag = true;
	private JPanel panel_3;
	private JButton btnNewButton;

	private int x1 = -750;
	private int x2 = -500;
	private int x3 = -250;
	private int x4 = 0;
	private int x5 = 250;
	private int x6 = 500;

	private static BLController compute;

	/**
	 * Create the application.
	 */
	public TeamsSelectionFrame() {// 构造函数
		initialize();
		timer = new Timer(); // 初始化图片循环切换线程
		timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY,
				PERIOD_INTERVAL);
	}
 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 100, 1000, 100);
		panel_1.setLayout(null);

		JLabel midLabel = new JLabel("");
		midLabel.setBounds(0, 0, 1000, 100);
		panel_1.add(midLabel);
		midLabel.setIcon(new ImageIcon("conf\\pictures\\middle.jpg"));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 200, 1000, 600);
		panel_2.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("conf\\pictures\\CHI.jpg"));
		lblNewLabel_1.setBounds(10, 10, 150, 100);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {

					setTeamsInfo("CHI");
					MainFrame.currentPanel = Panels.TeamsInfoFrame;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("conf\\pictures\\CHI2.jpg"));
				lblNewLabel_1.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("conf\\pictures\\CHI.jpg"));
				lblNewLabel_1.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("conf\\pictures\\CLE.jpg"));
		lblNewLabel_2.setBounds(10, 120, 150, 100);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {

					setTeamsInfo("CLE");
					MainFrame.currentPanel = Panels.TeamsInfoFrame;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("conf\\pictures\\CLE2.jpg"));
				lblNewLabel_2.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("conf\\pictures\\CLE.jpg"));
				lblNewLabel_2.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("conf\\pictures\\DET.jpg"));
		lblNewLabel_3.setBounds(10, 230, 150, 100);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					setTeamsInfo("DET");
					MainFrame.currentPanel = Panels.TeamsInfoFrame;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("conf\\pictures\\DET2.jpg"));
				lblNewLabel_3.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("conf\\pictures\\DET.jpg"));
				lblNewLabel_3.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("conf\\pictures\\IND.jpg"));
		lblNewLabel_4.setBounds(10, 340, 150, 100);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("IND");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("conf\\pictures\\IND2.jpg"));
				lblNewLabel_4.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("conf\\pictures\\IND.jpg"));
				lblNewLabel_4.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("conf\\pictures\\MIL.jpg"));
		lblNewLabel_5.setBounds(10, 450, 150, 100);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIL");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("conf\\pictures\\MIL2.jpg"));
				lblNewLabel_5.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("conf\\pictures\\MIL.jpg"));
				lblNewLabel_5.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(175, 10, 150, 100);
		lblNewLabel_6.setIcon(new ImageIcon("conf\\pictures\\BKN.jpg"));
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("BKN");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon("conf\\pictures\\BKN2.jpg"));
				lblNewLabel_6.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon("conf\\pictures\\BKN.jpg"));
				lblNewLabel_6.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(175, 120, 150, 100);
		lblNewLabel_7.setIcon(new ImageIcon("conf\\pictures\\BOS.jpg"));
		panel_2.add(lblNewLabel_7);
		lblNewLabel_7.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("BOS");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_7.setIcon(new ImageIcon("conf\\pictures\\BOS2.jpg"));
				lblNewLabel_7.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_7.setIcon(new ImageIcon("conf\\pictures\\BOS.jpg"));
				lblNewLabel_7.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(175, 230, 150, 100);
		lblNewLabel_8.setIcon(new ImageIcon("conf\\pictures\\NYK.jpg"));
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("NYK");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_8.setIcon(new ImageIcon("conf\\pictures\\NYK2.jpg"));
				lblNewLabel_8.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_8.setIcon(new ImageIcon("conf\\pictures\\NYK.jpg"));
				lblNewLabel_8.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(175, 340, 150, 100);
		lblNewLabel_9.setIcon(new ImageIcon("conf\\pictures\\PHI.jpg"));
		panel_2.add(lblNewLabel_9);
		lblNewLabel_9.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("PHI");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_9.setIcon(new ImageIcon("conf\\pictures\\PHI2.jpg"));
				lblNewLabel_9.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_9.setIcon(new ImageIcon("conf\\pictures\\PHI.jpg"));
				lblNewLabel_9.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(175, 450, 150, 100);
		lblNewLabel_10.setIcon(new ImageIcon("conf\\pictures\\TOR.jpg"));
		panel_2.add(lblNewLabel_10);
		lblNewLabel_10.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("TOR");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_10.setIcon(new ImageIcon("conf\\pictures\\TOR2.jpg"));
				lblNewLabel_10.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_10.setIcon(new ImageIcon("conf\\pictures\\TOR.jpg"));
				lblNewLabel_10.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("conf\\pictures\\ATL.jpg"));
		lblNewLabel_11.setBounds(340, 10, 150, 100);
		panel_2.add(lblNewLabel_11);
		lblNewLabel_11.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("ATL");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_11.setIcon(new ImageIcon("conf\\pictures\\ATL2.jpg"));
				lblNewLabel_11.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_11.setIcon(new ImageIcon("conf\\pictures\\ATL.jpg"));
				lblNewLabel_11.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon("conf\\pictures\\CHA.jpg"));
		lblNewLabel_12.setBounds(340, 120, 150, 100);
		panel_2.add(lblNewLabel_12);
		lblNewLabel_12.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("CHA");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_12.setIcon(new ImageIcon("conf\\pictures\\CHA2.jpg"));
				lblNewLabel_12.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_12.setIcon(new ImageIcon("conf\\pictures\\CHA.jpg"));
				lblNewLabel_12.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("conf\\pictures\\MIA.jpg"));
		lblNewLabel_13.setBounds(340, 230, 150, 100);
		panel_2.add(lblNewLabel_13);
		lblNewLabel_13.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIA");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_13.setIcon(new ImageIcon("conf\\pictures\\MIA2.jpg"));
				lblNewLabel_13.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_13.setIcon(new ImageIcon("conf\\pictures\\MIA.jpg"));
				lblNewLabel_13.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon("conf\\pictures\\ORL.jpg"));
		lblNewLabel_14.setBounds(340, 340, 150, 100);
		panel_2.add(lblNewLabel_14);
		lblNewLabel_14.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("ORL");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_14.setIcon(new ImageIcon("conf\\pictures\\ORL2.jpg"));
				lblNewLabel_14.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_14.setIcon(new ImageIcon("conf\\pictures\\ORL.jpg"));
				lblNewLabel_14.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon("conf\\pictures\\WAS.jpg"));
		lblNewLabel_15.setBounds(340, 450, 150, 100);
		panel_2.add(lblNewLabel_15);
		lblNewLabel_15.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("WAS");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_15.setIcon(new ImageIcon("conf\\pictures\\WAS2.jpg"));
				lblNewLabel_15.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_15.setIcon(new ImageIcon("conf\\pictures\\WAS.jpg"));
				lblNewLabel_15.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setIcon(new ImageIcon("conf\\pictures\\DEN.jpg"));
		lblNewLabel_16.setBounds(505, 10, 150, 100);
		panel_2.add(lblNewLabel_16);
		lblNewLabel_16.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("DEN");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_16.setIcon(new ImageIcon("conf\\pictures\\DEN2.jpg"));
				lblNewLabel_16.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_16.setIcon(new ImageIcon("conf\\pictures\\DEN.jpg"));
				lblNewLabel_16.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_17 = new JLabel("");
		lblNewLabel_17.setIcon(new ImageIcon("conf\\pictures\\MIN.jpg"));
		lblNewLabel_17.setBounds(505, 120, 150, 100);
		panel_2.add(lblNewLabel_17);
		lblNewLabel_17.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIN");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_17.setIcon(new ImageIcon("conf\\pictures\\MIN2.jpg"));
				lblNewLabel_17.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_17.setIcon(new ImageIcon("conf\\pictures\\MIN.jpg"));
				lblNewLabel_17.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_18 = new JLabel("");
		lblNewLabel_18.setIcon(new ImageIcon("conf\\pictures\\OKC.jpg"));
		lblNewLabel_18.setBounds(505, 230, 150, 100);
		panel_2.add(lblNewLabel_18);
		lblNewLabel_18.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("OKC");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_18.setIcon(new ImageIcon("conf\\pictures\\OKC2.jpg"));
				lblNewLabel_18.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_18.setIcon(new ImageIcon("conf\\pictures\\OKC.jpg"));
				lblNewLabel_18.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.setIcon(new ImageIcon("conf\\pictures\\POR.jpg"));
		lblNewLabel_19.setBounds(505, 340, 150, 100);
		panel_2.add(lblNewLabel_19);
		lblNewLabel_19.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("POR");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_19.setIcon(new ImageIcon("conf\\pictures\\POR2.jpg"));
				lblNewLabel_19.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_19.setIcon(new ImageIcon("conf\\pictures\\POR.jpg"));
				lblNewLabel_19.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.setIcon(new ImageIcon("conf\\pictures\\UTA.jpg"));
		lblNewLabel_20.setBounds(505, 450, 150, 100);
		panel_2.add(lblNewLabel_20);
		lblNewLabel_20.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("UTA");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_20.setIcon(new ImageIcon("conf\\pictures\\UTA2.jpg"));
				lblNewLabel_20.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_20.setIcon(new ImageIcon("conf\\pictures\\UTA.jpg"));
				lblNewLabel_20.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setIcon(new ImageIcon("conf\\pictures\\GSW.jpg"));
		lblNewLabel_21.setBounds(670, 10, 150, 100);
		panel_2.add(lblNewLabel_21);
		lblNewLabel_21.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("GSW");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_21.setIcon(new ImageIcon("conf\\pictures\\GSW2.jpg"));
				lblNewLabel_21.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_21.setIcon(new ImageIcon("conf\\pictures\\GSW.jpg"));
				lblNewLabel_21.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_22 = new JLabel("");
		lblNewLabel_22.setIcon(new ImageIcon("conf\\pictures\\LAC.jpg"));
		lblNewLabel_22.setBounds(670, 120, 150, 100);
		panel_2.add(lblNewLabel_22);
		lblNewLabel_22.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("LAC");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_22.setIcon(new ImageIcon("conf\\pictures\\LAC2.jpg"));
				lblNewLabel_22.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_22.setIcon(new ImageIcon("conf\\pictures\\LAC.jpg"));
				lblNewLabel_22.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_23 = new JLabel("");
		lblNewLabel_23.setIcon(new ImageIcon("conf\\pictures\\LAL.jpg"));
		lblNewLabel_23.setBounds(670, 230, 150, 100);
		panel_2.add(lblNewLabel_23);
		lblNewLabel_23.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("LAL");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_23.setIcon(new ImageIcon("conf\\pictures\\LAL2.jpg"));
				lblNewLabel_23.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_23.setIcon(new ImageIcon("conf\\pictures\\LAL.jpg"));
				lblNewLabel_23.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_24 = new JLabel("");
		lblNewLabel_24.setIcon(new ImageIcon("conf\\pictures\\PHX.jpg"));
		lblNewLabel_24.setBounds(670, 340, 150, 100);
		panel_2.add(lblNewLabel_24);
		lblNewLabel_24.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("PHX");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_24.setIcon(new ImageIcon("conf\\pictures\\PHX2.jpg"));
				lblNewLabel_24.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_24.setIcon(new ImageIcon("conf\\pictures\\PHX.jpg"));
				lblNewLabel_24.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_25 = new JLabel("");
		lblNewLabel_25.setIcon(new ImageIcon("conf\\pictures\\SAC.jpg"));
		lblNewLabel_25.setBounds(670, 450, 150, 100);
		panel_2.add(lblNewLabel_25);
		lblNewLabel_25.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("SAC");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_25.setIcon(new ImageIcon("conf\\pictures\\SAC2.jpg"));
				lblNewLabel_25.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_25.setIcon(new ImageIcon("conf\\pictures\\SAC.jpg"));
				lblNewLabel_25.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_26 = new JLabel("");
		lblNewLabel_26.setIcon(new ImageIcon("conf\\pictures\\DAL.jpg"));
		lblNewLabel_26.setBounds(830, 10, 150, 100);
		panel_2.add(lblNewLabel_26);
		lblNewLabel_26.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("DAL");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_26.setIcon(new ImageIcon("conf\\pictures\\DAL2.jpg"));
				lblNewLabel_26.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_26.setIcon(new ImageIcon("conf\\pictures\\DAL.jpg"));
				lblNewLabel_26.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_27 = new JLabel("");
		lblNewLabel_27.setIcon(new ImageIcon("conf\\pictures\\HOU.jpg"));
		lblNewLabel_27.setBounds(830, 120, 150, 100);
		panel_2.add(lblNewLabel_27);
		lblNewLabel_27.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("HOU");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_27.setIcon(new ImageIcon("conf\\pictures\\HOU2.jpg"));
				lblNewLabel_27.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_27.setIcon(new ImageIcon("conf\\pictures\\HOU.jpg"));
				lblNewLabel_27.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_28 = new JLabel("");
		lblNewLabel_28.setIcon(new ImageIcon("conf\\pictures\\MEM.jpg"));
		lblNewLabel_28.setBounds(830, 230, 150, 100);
		panel_2.add(lblNewLabel_28);
		lblNewLabel_28.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MEM");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_28.setIcon(new ImageIcon("conf\\pictures\\MEM2.jpg"));
				lblNewLabel_28.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_28.setIcon(new ImageIcon("conf\\pictures\\MEM.jpg"));
				lblNewLabel_28.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_29 = new JLabel("");
		lblNewLabel_29.setIcon(new ImageIcon("conf\\pictures\\NOP.jpg"));
		lblNewLabel_29.setBounds(830, 340, 150, 100);
		panel_2.add(lblNewLabel_29);
		lblNewLabel_29.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("NOP");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_29.setIcon(new ImageIcon("conf\\pictures\\NOP2.jpg"));
				lblNewLabel_29.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_29.setIcon(new ImageIcon("conf\\pictures\\NOP.jpg"));
				lblNewLabel_29.setCursor(Cursor.getDefaultCursor());
			}
		});

		lblNewLabel_30 = new JLabel("");
		lblNewLabel_30.setIcon(new ImageIcon("conf\\pictures\\SAS.jpg"));
		lblNewLabel_30.setBounds(830, 450, 150, 100);
		panel_2.add(lblNewLabel_30);
		lblNewLabel_30.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("SAS");
				MainFrame.currentPanel = Panels.TeamsInfoFrame;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_30.setIcon(new ImageIcon("conf\\pictures\\SAS2.jpg"));
				lblNewLabel_30.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_30.setIcon(new ImageIcon("conf\\pictures\\SAS.jpg"));
				lblNewLabel_30.setCursor(Cursor.getDefaultCursor());
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(100, 0, 750, 100);
		panel.setOpaque(false);
		panel.setLayout(null);

		changeLabel = new JLabel("");
		changeLabel.setLocation(-750, 0);
		changeLabel.setSize(250, 100);
		ImageIcon ii = new ImageIcon("conf\\pictures\\KOBE.jpg");
		changeLabel.setIcon(ii);
		panel.add(changeLabel);

		changeLabel_1 = new JLabel("");
		changeLabel_1.setLocation(-500, 0);
		changeLabel_1.setSize(250, 100);
		ImageIcon ii0 = new ImageIcon("conf\\pictures\\WADE.jpg");
		changeLabel_1.setIcon(ii0);
		panel.add(changeLabel_1);

		changeLabel_3 = new JLabel("");
		changeLabel_3.setLocation(0, 0);
		changeLabel_3.setSize(250, 100);
		ImageIcon ii2 = new ImageIcon("conf\\pictures\\ROYALTY.jpg");
		changeLabel_3.setIcon(ii2);
		panel.add(changeLabel_3);

		changeLabel_4 = new JLabel("");
		changeLabel_4.setLocation(250, 0);
		changeLabel_4.setSize(250, 100);
		ImageIcon ii3 = new ImageIcon("conf\\pictures\\GARNETT.jpg");
		changeLabel_4.setIcon(ii3);
		panel.add(changeLabel_4);

		changeLabel_5 = new JLabel("");
		changeLabel_5.setLocation(500, 0);
		changeLabel_5.setSize(250, 100);
		ImageIcon ii4 = new ImageIcon("conf\\pictures\\DURANT.jpg");
		changeLabel_5.setIcon(ii4);
		panel.add(changeLabel_5);

		changeLabel_2 = new JLabel("");
		changeLabel_2.setLocation(-250, 0);
		changeLabel_2.setSize(250, 100);
		ImageIcon ii1 = new ImageIcon("conf\\pictures\\JORDAN.jpg");
		changeLabel_2.setIcon(ii1);
		panel.add(changeLabel_2);

		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 1000, 600);
		mainpanel.setPreferredSize(new Dimension(900, 800));
		mainpanel.setLayout(null);
		mainpanel.add(panel);
		mainpanel.add(panel_1);
		mainpanel.add(panel_2);

		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 100, 100);
		mainpanel.add(panel_3);
		panel_3.setLayout(null);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(0, 20, 100, 50);
		panel_3.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int size = MainFrame.backPanels.size();
					Panels temp = MainFrame.backPanels.get(size-1);
					MainFrame.backPanels.remove(size-1);
					switch(temp) {
					case MainFrame:
						MainFrame.panel.setVisible(true);
						TeamsSelectionFrame.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("NBA");
						MainFrame.currentPanel = Panels.MainFrame;
						break;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});

		scrollPane = new JScrollPane(mainpanel);
		scrollPane.setBounds(0, 0, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

	}

	public static void goToTeam(String teamName){
		compute = BLController.getInstance();
		TeamWithPlayersVO twpvo = compute.getTeamAnalysis(teamName);
		MainFrame.teamNAME = teamName;
		
		if(twpvo!=null&&twpvo.getTeam()!=null) {
			TeamsInfoFrame tip = new TeamsInfoFrame(twpvo);
			MainFrame.frame.getContentPane().add(tip.scrollPane);
			tip.updateTeam(twpvo, "胜率");
			tip.latestMatchs(teamName);
			MainFrame.selection = "胜率";
			MainFrame.frame.setTitle("NBA球队信息");
			MainFrame.backPanels.add(MainFrame.currentPanel);
			MainFrame.currentPanel = Panels.TeamsInfoFrame;
			MainFrame.frame.repaint();//刷新重画 
			MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		}
	}
	
	//传递制定球队信息
	public static void setTeamsInfo(String teamName) {
		TeamsSelectionFrame.scrollPane.setVisible(false);
		TeamsSelectionFrame.flag = false;
		MainFrame.teamNAME = teamName;
        
		compute = BLController.getInstance();
		TeamWithPlayersVO twpvo = compute.getTeamAnalysis(teamName);
		
		if(twpvo!=null&&twpvo.getTeam()!=null) {
			TeamsInfoFrame tip = new TeamsInfoFrame(twpvo);
			MainFrame.frame.getContentPane().add(tip.scrollPane);
			MainFrame.TWPVO = twpvo;
			tip.updateTeam(twpvo, "胜率");
			tip.latestMatchs(teamName);
			MainFrame.selection = "胜率";
			MainFrame.frame.setTitle("NBA球队信息");
			MainFrame.backPanels.add(MainFrame.currentPanel);
			MainFrame.currentPanel = Panels.TeamsInfoFrame;
			MainFrame.frame.repaint();//刷新重画 
			MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		}
	}

	// 图片循环切换线程
	private class ScheduleTask extends TimerTask {

		public void run() {
			if (flag) {
				if (x1 < 750) {
					x1++;
					changeLabel.setLocation(x1, 0);
				} else {
					x1 = -750;
					changeLabel.setLocation(x1, 0);
				}
				if (x2 < 750) {
					x2++;
					changeLabel_1.setLocation(x2, 0);
				} else {
					x2 = -750;
					changeLabel.setLocation(x2, 0);
				}
				if (x3 < 750) {
					x3++;
					changeLabel_2.setLocation(x3, 0);
				} else {
					x3 = -750;
					changeLabel_2.setLocation(x3, 0);
				}

				if (x4 < 750) {
					x4++;
					changeLabel_3.setLocation(x4, 0);
				} else {
					x4 = -750;
					changeLabel_3.setLocation(x4, 0);
				}
				if (x5 < 750) {
					x5++;
					changeLabel_4.setLocation(x5, 0);
				} else {
					x5 = -750;
					changeLabel_4.setLocation(x5, 0);
				}
				if (x6 < 750) {
					x6++;
					changeLabel_5.setLocation(x6, 0);
				} else {
					x6 = -750;
					changeLabel_5.setLocation(x6, 0);
				}

			}

		}
	}

	public static double handle(double a, int b) {
		double result = a / (double) b;
		BigDecimal bg = new BigDecimal(result);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

}
