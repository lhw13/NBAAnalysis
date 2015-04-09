package presentation.teamsui;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsInfoFrame;
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
	private Timer timer;
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

	private static String columns[] = { "球队", "球队缩写", "所在地", "赛区", "分区", "主场", "建立时间" };

	public static String table_1_columns[] = { "球员", "场数", "在场时间", "投篮命中数(场均)", "投篮命中数(总计)" };

	public static String selection="投篮命中数";
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
		midLabel.setIcon(new ImageIcon("pictures\\middle.jpg"));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 200, 1000, 600);
		panel_2.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI.jpg"));
		lblNewLabel_1.setBounds(10, 10, 150, 100);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {

					setTeamsInfo("CHI");

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI.jpg"));
			}
		});

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE.jpg"));
		lblNewLabel_2.setBounds(10, 120, 150, 100);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {

					setTeamsInfo("CLE");

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE.jpg"));
			}
		});

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET.jpg"));
		lblNewLabel_3.setBounds(10, 230, 150, 100);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					setTeamsInfo("DET");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET.jpg"));
			}
		});

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND.jpg"));
		lblNewLabel_4.setBounds(10, 340, 150, 100);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("IND");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND.jpg"));
			}
		});

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL.jpg"));
		lblNewLabel_5.setBounds(10, 450, 150, 100);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIL");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL.jpg"));
			}
		});

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(175, 10, 150, 100);
		lblNewLabel_6.setIcon(new ImageIcon("pictures\\BKN.jpg"));
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("BKN");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon("pictures\\BKN2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon("pictures\\BKN.jpg"));
			}
		});

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(175, 120, 150, 100);
		lblNewLabel_7.setIcon(new ImageIcon("pictures\\BOS.jpg"));
		panel_2.add(lblNewLabel_7);
		lblNewLabel_7.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("BOS");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_7.setIcon(new ImageIcon("pictures\\BOS2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_7.setIcon(new ImageIcon("pictures\\BOS.jpg"));
			}
		});

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(175, 230, 150, 100);
		lblNewLabel_8.setIcon(new ImageIcon("pictures\\NYK.jpg"));
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("NYK");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_8.setIcon(new ImageIcon("pictures\\NYK2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_8.setIcon(new ImageIcon("pictures\\NYK.jpg"));
			}
		});

		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(175, 340, 150, 100);
		lblNewLabel_9.setIcon(new ImageIcon("pictures\\PHI.jpg"));
		panel_2.add(lblNewLabel_9);
		lblNewLabel_9.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("PHI");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_9.setIcon(new ImageIcon("pictures\\PHI2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_9.setIcon(new ImageIcon("pictures\\PHI.jpg"));
			}
		});

		lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(175, 450, 150, 100);
		lblNewLabel_10.setIcon(new ImageIcon("pictures\\TOR.jpg"));
		panel_2.add(lblNewLabel_10);
		lblNewLabel_10.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("TOR");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_10.setIcon(new ImageIcon("pictures\\TOR2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_10.setIcon(new ImageIcon("pictures\\TOR.jpg"));
			}
		});

		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("pictures\\ATL.jpg"));
		lblNewLabel_11.setBounds(340, 10, 150, 100);
		panel_2.add(lblNewLabel_11);
		lblNewLabel_11.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("ATL");
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_11.setIcon(new ImageIcon("pictures\\ATL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_11.setIcon(new ImageIcon("pictures\\ATL.jpg"));
			}
		});

		lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon("pictures\\CHA.jpg"));
		lblNewLabel_12.setBounds(340, 120, 150, 100);
		panel_2.add(lblNewLabel_12);
		lblNewLabel_12.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("CHA");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_12.setIcon(new ImageIcon("pictures\\CHA2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_12.setIcon(new ImageIcon("pictures\\CHA.jpg"));
			}
		});

		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("pictures\\MIA.jpg"));
		lblNewLabel_13.setBounds(340, 230, 150, 100);
		panel_2.add(lblNewLabel_13);
		lblNewLabel_13.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIA");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_13.setIcon(new ImageIcon("pictures\\MIA2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_13.setIcon(new ImageIcon("pictures\\MIA.jpg"));
			}
		});

		lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon("pictures\\ORL.jpg"));
		lblNewLabel_14.setBounds(340, 340, 150, 100);
		panel_2.add(lblNewLabel_14);
		lblNewLabel_14.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("ORL");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_14.setIcon(new ImageIcon("pictures\\ORL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_14.setIcon(new ImageIcon("pictures\\ORL.jpg"));
			}
		});

		lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon("pictures\\WAS.jpg"));
		lblNewLabel_15.setBounds(340, 450, 150, 100);
		panel_2.add(lblNewLabel_15);
		lblNewLabel_15.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("WAS");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_15.setIcon(new ImageIcon("pictures\\WAS2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_15.setIcon(new ImageIcon("pictures\\WAS.jpg"));
			}
		});

		lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setIcon(new ImageIcon("pictures\\DEN.jpg"));
		lblNewLabel_16.setBounds(515, 10, 150, 100);
		panel_2.add(lblNewLabel_16);
		lblNewLabel_16.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("DEN");
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_16.setIcon(new ImageIcon("pictures\\DEN2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_16.setIcon(new ImageIcon("pictures\\DEN.jpg"));
			}
		});

		lblNewLabel_17 = new JLabel("");
		lblNewLabel_17.setIcon(new ImageIcon("pictures\\MIN.jpg"));
		lblNewLabel_17.setBounds(515, 120, 150, 100);
		panel_2.add(lblNewLabel_17);
		lblNewLabel_17.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MIN");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_17.setIcon(new ImageIcon("pictures\\MIN2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_17.setIcon(new ImageIcon("pictures\\MIN.jpg"));
			}
		});

		lblNewLabel_18 = new JLabel("");
		lblNewLabel_18.setIcon(new ImageIcon("pictures\\OKC.jpg"));
		lblNewLabel_18.setBounds(515, 230, 150, 100);
		panel_2.add(lblNewLabel_18);
		lblNewLabel_18.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("OKC");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_18.setIcon(new ImageIcon("pictures\\OKC2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_18.setIcon(new ImageIcon("pictures\\OKC.jpg"));
			}
		});

		lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.setIcon(new ImageIcon("pictures\\POR.jpg"));
		lblNewLabel_19.setBounds(515, 340, 150, 100);
		panel_2.add(lblNewLabel_19);
		lblNewLabel_19.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("POR");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_19.setIcon(new ImageIcon("pictures\\POR2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_19.setIcon(new ImageIcon("pictures\\POR.jpg"));
			}
		});

		lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.setIcon(new ImageIcon("pictures\\UTA.jpg"));
		lblNewLabel_20.setBounds(515, 450, 150, 100);
		panel_2.add(lblNewLabel_20);
		lblNewLabel_20.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("UTA");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_20.setIcon(new ImageIcon("pictures\\UTA2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_20.setIcon(new ImageIcon("pictures\\UTA.jpg"));
			}
		});

		lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setIcon(new ImageIcon("pictures\\GSW.jpg"));
		lblNewLabel_21.setBounds(680, 10, 150, 100);
		panel_2.add(lblNewLabel_21);
		lblNewLabel_21.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("GSW");
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_21.setIcon(new ImageIcon("pictures\\GSW2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_21.setIcon(new ImageIcon("pictures\\GSW.jpg"));
			}
		});

		lblNewLabel_22 = new JLabel("");
		lblNewLabel_22.setIcon(new ImageIcon("pictures\\LAC.jpg"));
		lblNewLabel_22.setBounds(680, 120, 150, 100);
		panel_2.add(lblNewLabel_22);
		lblNewLabel_22.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("LAC");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_22.setIcon(new ImageIcon("pictures\\LAC2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_22.setIcon(new ImageIcon("pictures\\LAC.jpg"));
			}
		});

		lblNewLabel_23 = new JLabel("");
		lblNewLabel_23.setIcon(new ImageIcon("pictures\\LAL.jpg"));
		lblNewLabel_23.setBounds(680, 230, 150, 100);
		panel_2.add(lblNewLabel_23);
		lblNewLabel_23.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("LAL");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_23.setIcon(new ImageIcon("pictures\\LAL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_23.setIcon(new ImageIcon("pictures\\LAL.jpg"));
			}
		});

		lblNewLabel_24 = new JLabel("");
		lblNewLabel_24.setIcon(new ImageIcon("pictures\\PHX.jpg"));
		lblNewLabel_24.setBounds(680, 340, 150, 100);
		panel_2.add(lblNewLabel_24);
		lblNewLabel_24.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("PHX");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_24.setIcon(new ImageIcon("pictures\\PHX2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_24.setIcon(new ImageIcon("pictures\\PHX.jpg"));
			}
		});

		lblNewLabel_25 = new JLabel("");
		lblNewLabel_25.setIcon(new ImageIcon("pictures\\SAC.jpg"));
		lblNewLabel_25.setBounds(680, 450, 150, 100);
		panel_2.add(lblNewLabel_25);
		lblNewLabel_25.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("SAC");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_25.setIcon(new ImageIcon("pictures\\SAC2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_25.setIcon(new ImageIcon("pictures\\SAC.jpg"));
			}
		});

		lblNewLabel_26 = new JLabel("");
		lblNewLabel_26.setIcon(new ImageIcon("pictures\\DAL.jpg"));
		lblNewLabel_26.setBounds(850, 10, 150, 100);
		panel_2.add(lblNewLabel_26);
		lblNewLabel_26.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("DAL");
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_26.setIcon(new ImageIcon("pictures\\DAL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_26.setIcon(new ImageIcon("pictures\\DAL.jpg"));
			}
		});

		lblNewLabel_27 = new JLabel("");
		lblNewLabel_27.setIcon(new ImageIcon("pictures\\HOU.jpg"));
		lblNewLabel_27.setBounds(850, 120, 150, 100);
		panel_2.add(lblNewLabel_27);
		lblNewLabel_27.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("HOU");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_27.setIcon(new ImageIcon("pictures\\HOU2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_27.setIcon(new ImageIcon("pictures\\HOU.jpg"));
			}
		});

		lblNewLabel_28 = new JLabel("");
		lblNewLabel_28.setIcon(new ImageIcon("pictures\\MEM.jpg"));
		lblNewLabel_28.setBounds(850, 230, 150, 100);
		panel_2.add(lblNewLabel_28);
		lblNewLabel_28.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("MEM");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_28.setIcon(new ImageIcon("pictures\\MEM2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_28.setIcon(new ImageIcon("pictures\\MEM.jpg"));
			}
		});

		lblNewLabel_29 = new JLabel("");
		lblNewLabel_29.setIcon(new ImageIcon("pictures\\NOP.jpg"));
		lblNewLabel_29.setBounds(850, 340, 150, 100);
		panel_2.add(lblNewLabel_29);
		lblNewLabel_29.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("NOP");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_29.setIcon(new ImageIcon("pictures\\NOP2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_29.setIcon(new ImageIcon("pictures\\NOP.jpg"));
			}
		});

		lblNewLabel_30 = new JLabel("");
		lblNewLabel_30.setIcon(new ImageIcon("pictures\\SAS.jpg"));
		lblNewLabel_30.setBounds(850, 450, 150, 100);
		panel_2.add(lblNewLabel_30);
		lblNewLabel_30.addMouseListener(new MouseListener() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				setTeamsInfo("SAS");

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_30.setIcon(new ImageIcon("pictures\\SAS2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_30.setIcon(new ImageIcon("pictures\\SAS.jpg"));
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(100, 0, 750, 100);
		panel.setOpaque(false);
		panel.setLayout(null);

		changeLabel = new JLabel("");
		changeLabel.setLocation(-750, 0);
		changeLabel.setSize(250, 100);
		ImageIcon ii = new ImageIcon("pictures\\KOBE.jpg");
		changeLabel.setIcon(ii);
		panel.add(changeLabel);

		changeLabel_1 = new JLabel("");
		changeLabel_1.setLocation(-500, 0);
		changeLabel_1.setSize(250, 100);
		ImageIcon ii0 = new ImageIcon("pictures\\WADE.jpg");
		changeLabel_1.setIcon(ii0);
		panel.add(changeLabel_1);

		changeLabel_3 = new JLabel("");
		changeLabel_3.setLocation(0, 0);
		changeLabel_3.setSize(250, 100);
		ImageIcon ii2 = new ImageIcon("pictures\\ROYALTY.jpg");
		changeLabel_3.setIcon(ii2);
		panel.add(changeLabel_3);

		changeLabel_4 = new JLabel("");
		changeLabel_4.setLocation(250, 0);
		changeLabel_4.setSize(250, 100);
		ImageIcon ii3 = new ImageIcon("pictures\\GARNETT.jpg");
		changeLabel_4.setIcon(ii3);
		panel.add(changeLabel_4);

		changeLabel_5 = new JLabel("");
		changeLabel_5.setLocation(500, 0);
		changeLabel_5.setSize(250, 100);
		ImageIcon ii4 = new ImageIcon("pictures\\DURANT.jpg");
		changeLabel_5.setIcon(ii4);
		panel.add(changeLabel_5);

		changeLabel_2 = new JLabel("");
		changeLabel_2.setLocation(-250, 0);
		changeLabel_2.setSize(250, 100);
		ImageIcon ii1 = new ImageIcon("pictures\\JORDAN.jpg");
		changeLabel_2.setIcon(ii1);
		panel.add(changeLabel_2);

		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 1000, 600);
		mainpanel.setPreferredSize(new Dimension(1000, 1000));
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
					TeamsSelectionFrame.scrollPane.setVisible(false);
					TeamsSelectionFrame.flag = false;
					MainFrame.panel.setVisible(true);
					MainFrame.frame.setTitle("NBA");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});

		scrollPane = new JScrollPane(mainpanel);
		scrollPane.setBounds(0, 100, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

	}

	//传递制定球队信息
	public static void setTeamsInfo(String teamName) {
		TeamsSelectionFrame.scrollPane.setVisible(false);
		TeamsSelectionFrame.flag = false;
		MainFrame.frame.setTitle(teamName);

		compute = BLController.getInstance();
		TeamWithPlayersVO twpvo = compute.getTeamAnalysis(teamName);
		TeamVO tvo = new TeamVO();
		String fullName = "teamName";
		String abbreviation = "null";
		String location = "null";
		char division = ' ';
		String zone = "null";
		String home = "null";
		int setupTime = 0;
		if (twpvo.getTeam() != null) {
			tvo = twpvo.getTeam();
			fullName = tvo.getFullName();
			abbreviation = tvo.getAbbreviation();
			location = tvo.getLocation();
			division = tvo.getDivision();
			zone = tvo.getZone();
			home = tvo.getHome();
			setupTime = tvo.getSetupTime();
		}
		ArrayList<PlayerVO> players = twpvo.getPlayers();

		Object rows[][] = new Object[1][7];
		rows[0][0] = fullName;
		rows[0][1] = abbreviation;
		rows[0][2] = location;
		rows[0][3] = division;
		rows[0][4] = zone;
		rows[0][5] = home;
		rows[0][6] = setupTime;

		int playersNum=players.size();
		Object table_1_rows[][] = new Object[playersNum+1][5];

		for (int i = 0; i < players.size(); i++) {
			PlayerVO pvo = players.get(i);
			int appearance = pvo.getAppearance();
			table_1_rows[i][0] = pvo.getName();
			table_1_rows[i][1] = pvo.getAppearance();
			table_1_rows[i][2] = pvo.getPlayTime();
			switch(selection){
			case "投篮命中数":
				table_1_rows[i][4] = pvo.getHit();
				table_1_rows[i][3] = handle((double) pvo.getHit(), appearance);
				break;
			case "投篮出手数":
				table_1_rows[i][4] = pvo.getShot();
				table_1_rows[i][3] = handle((double) pvo.getShot(), appearance);
				break;
			case "三分命中数":
				table_1_rows[i][4] = pvo.getThirdHit();
				table_1_rows[i][3] = handle((double) pvo.getThirdHit(), appearance);
				break;
			case "三分出手数":
				table_1_rows[i][4] = pvo.getThirdshot();
				table_1_rows[i][3] = handle((double) pvo.getThirdshot(), appearance);
				break;
			case "罚球命中数":
				table_1_rows[i][4] = pvo.getFreeHit();
				table_1_rows[i][3] = handle((double) pvo.getFreeHit(), appearance);
				break;
			case "罚球出手数":
				table_1_rows[i][4] = pvo.getFreeshot();
				table_1_rows[i][3] = handle((double) pvo.getFreeshot(), appearance);
				break;
			case "进攻篮板":
				table_1_rows[i][4] = pvo.getOffensiveRebound();
				table_1_rows[i][3] = handle((double) pvo.getOffensiveRebound(),appearance);
				break;
			case "防守篮板":
				table_1_rows[i][4] = pvo.getDefensiveRebound();
				table_1_rows[i][3] = handle((double) pvo.getDefensiveRebound(),appearance);
				break;
			case "总篮板":
				table_1_rows[i][4] = pvo.getTotalRebound();
				table_1_rows[i][3] = handle((double) pvo.getTotalRebound(),appearance);
				break;
			case "助攻":
				table_1_rows[i][4] = pvo.getAssist();
				table_1_rows[i][3] = handle((double) pvo.getAssist(), appearance);
				break;
			case "抢断":
				table_1_rows[i][4] = pvo.getSteal();
				table_1_rows[i][3] = handle((double) pvo.getSteal(), appearance);
				break;
			case "盖帽":
				table_1_rows[i][4] = pvo.getBlock();
				table_1_rows[i][3] = handle((double) pvo.getBlock(), appearance);
				break;
			case "失误":
				table_1_rows[i][4] = pvo.getMiss();
				table_1_rows[i][3] = handle((double) pvo.getMiss(), appearance);
				break;
			case "犯规":
				table_1_rows[i][4] = pvo.getFoul();
				table_1_rows[i][3] = handle((double) pvo.getFoul(), appearance);
				break;
			case "得分":
				table_1_rows[i][4] = pvo.getScore();
				table_1_rows[i][3] = handle((double) pvo.getScore(), appearance);
				break;
			case "投篮命中率":
				table_1_rows[i][4] = pvo.getHitRate();
				table_1_rows[i][3] = pvo.getHitRate();
				break;
			case "三分命中率":
				table_1_rows[i][4] = pvo.getThirdHitRate();
				table_1_rows[i][3] = pvo.getThirdHitRate();
				break;
			case "罚球命中率":
				table_1_rows[i][4] = pvo.getFreeHitRate();
				table_1_rows[i][3] = pvo.getFreeHitRate();
				break;
			case "胜率":
				table_1_rows[i][4] = 0;
				table_1_rows[i][3] = 0;
				break;
			case "进攻回合":
				table_1_rows[i][4] = 0;
				table_1_rows[i][3] = 0;
				break;
			case "进攻效率":
				table_1_rows[i][4] = 0;
				table_1_rows[i][3] = 0;
				break;
			case "防守效率":
				table_1_rows[i][4] = 0;
				table_1_rows[i][3] = 0;
				break;
			case "进攻篮板效率":
				table_1_rows[i][4] = pvo.getOffensiveReboundRate();
				table_1_rows[i][3] = pvo.getOffensiveReboundRate();
				break;
			case "防守篮板效率":
				table_1_rows[i][4] = pvo.getDefensiveReboundRate();
				table_1_rows[i][3] = pvo.getDefensiveReboundRate();
				break;
			case "抢断效率":
				table_1_rows[i][4] = pvo.getStealRate();
				table_1_rows[i][3] = pvo.getStealRate();
				break;
			case "助攻率":
				table_1_rows[i][4] = pvo.getAssistRate();
				table_1_rows[i][3] = pvo.getAssistRate();
				break;
			}
		}
		int k = players.size();
		int appearance = tvo.getAppearance();
		table_1_rows[k][0] = tvo.getFullName();
		table_1_rows[k][1] = tvo.getAppearance();
		table_1_rows[k][2] = 0;
		switch(selection){
		case "投篮命中数":
			table_1_rows[k][4] = tvo.getHit();
			table_1_rows[k][3] = handle((double) tvo.getHit(), appearance);
			break;
		case "投篮出手数":
			table_1_rows[k][4] = tvo.getShot();
			table_1_rows[k][3] = handle((double) tvo.getShot(), appearance);
			break;
		case "三分命中数":
			table_1_rows[k][4] = tvo.getThirdHit();
			table_1_rows[k][3] = handle((double) tvo.getThirdHit(), appearance);
			break;
		case "三分出手数":
			table_1_rows[k][4] = tvo.getThirdshot();
			table_1_rows[k][3] = handle((double) tvo.getThirdshot(), appearance);
			break;
		case "罚球命中数":
			table_1_rows[k][4] = tvo.getFreeHit();
			table_1_rows[k][3] = handle((double) tvo.getFreeHit(), appearance);
			break;
		case "罚球出手数":
			table_1_rows[k][4] = tvo.getFreeshot();
			table_1_rows[k][3] = handle((double) tvo.getFreeshot(), appearance);
			break;
		case "进攻篮板":
			table_1_rows[k][4] = tvo.getOffensiveRebound();
			table_1_rows[k][3] = handle((double) tvo.getOffensiveRebound(),appearance);
			break;
		case "防守篮板":
			table_1_rows[k][4] = tvo.getDefensiveRebound();
			table_1_rows[k][3] = handle((double) tvo.getDefensiveRebound(),appearance);
			break;
		case "总篮板":
			table_1_rows[k][4] = tvo.getTotalRebound();
			table_1_rows[k][3] = handle((double) tvo.getTotalRebound(),appearance);
			break;
		case "助攻":
			table_1_rows[k][4] = tvo.getAssist();
			table_1_rows[k][3] = handle((double) tvo.getAssist(), appearance);
			break;
		case "抢断":
			table_1_rows[k][4] = tvo.getSteal();
			table_1_rows[k][3] = handle((double) tvo.getSteal(), appearance);
			break;
		case "盖帽":
			table_1_rows[k][4] = tvo.getBlock();
			table_1_rows[k][3] = handle((double) tvo.getBlock(), appearance);
			break;
		case "失误":
			table_1_rows[k][4] = tvo.getMiss();
			table_1_rows[k][3] = handle((double) tvo.getMiss(), appearance);
			break;
		case "犯规":
			table_1_rows[k][4] = tvo.getFoul();
			table_1_rows[k][3] = handle((double) tvo.getFoul(), appearance);
			break;
		case "得分":
			table_1_rows[k][4] = tvo.getScore();
			table_1_rows[k][3] = handle((double) tvo.getScore(), appearance);
			break;
		case "投篮命中率":
			table_1_rows[k][4] = tvo.getHitRate();
			table_1_rows[k][3] = tvo.getHitRate();
			break;
		case "三分命中率":
			table_1_rows[k][4] = tvo.getThirdHitRate();
			table_1_rows[k][3] = tvo.getThirdHitRate();
			break;
		case "罚球命中率":
			table_1_rows[k][4] = tvo.getFreeHitRate();
			table_1_rows[k][3] = tvo.getFreeHitRate();
			break;
		case "胜率":
			table_1_rows[k][4] = tvo.getWinRate();
			table_1_rows[k][3] = tvo.getWinRate();
			break;
		case "进攻回合":
			table_1_rows[k][4] = tvo.getOffensiveRound();
			table_1_rows[k][3] = tvo.getOffensiveRound();
			break;
		case "进攻效率":
			table_1_rows[k][4] = tvo.getOffensiveEfficiency();
			table_1_rows[k][3] = tvo.getOffensiveEfficiency();
			break;
		case "防守效率":
			table_1_rows[k][4] = tvo.getDefensiveEfficiency();
			table_1_rows[k][3] = tvo.getDefensiveEfficiency();
			break;
		case "进攻篮板效率":
			table_1_rows[k][4] = tvo.getOffensiveReboundEfficiency();
			table_1_rows[k][3] = tvo.getOffensiveReboundEfficiency();
			break;
		case "防守篮板效率":
			table_1_rows[k][4] = tvo.getDefensiveReboundEfficiency();
			table_1_rows[k][3] = tvo.getDefensiveReboundEfficiency();
			break;
		case "抢断效率":
			table_1_rows[k][4] = tvo.getStealEfficiency();
			table_1_rows[k][3] = tvo.getStealEfficiency();
			break;
		case "助攻率":
			table_1_rows[k][4] = tvo.getAssistEfficiency();
			table_1_rows[k][3] = tvo.getAssistEfficiency();
			break;
		}

		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		
		DefaultTableModel model1 = new DefaultTableModel(table_1_rows,
				table_1_columns) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};

		int width = 250;
		int height = 150;
		ImageIcon ii = compute.getTeamPic(teamName);
		ii.setImage(ii.getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		
		if(TeamsInfoFrame.scrollPane!=null){
			MainFrame.frame.getContentPane().remove(TeamsInfoFrame.scrollPane);
			TeamsInfoFrame.scrollPane=null;
			TeamsInfoFrame tip = new TeamsInfoFrame(teamName,ii,model,model1);
			MainFrame.frame.getContentPane().add(tip.scrollPane);
			MainFrame.frame.repaint();//刷新重画 
			MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		}else{
			MainFrame.frame.getContentPane().add(TeamsInfoFrame.scrollPane);
			TeamsInfoFrame.scrollPane.setVisible(true);
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
		BigDecimal c = new BigDecimal(result);
		double f1 = c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

}
