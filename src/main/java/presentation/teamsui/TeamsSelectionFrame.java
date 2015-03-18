package presentation.teamsui;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsInfoFrame;
import server.businesslogic.Compute;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
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
    
    public static boolean flag=true;
    private JPanel panel_3;
    private JButton btnNewButton;
    
    Compute compute;
    
    String columns[] ={
			"球队", "球队缩写", "所在地", "赛区", "分区", "主场", "建立时间"
		};
    
    String table_1_columns[] ={
			"球员", "场数", "在场时间", "投篮命中数", "出手数", "三分命中数", "出手数", "罚球命中数", "出手数", 
			"进攻篮板", "防守篮板", "总篮板数","助攻", "抢断", "盖帽", "失误", "犯规", "得分"
		};

    String table_2_columns[] ={
			"球员", "场数", "在场时间", "投篮命中数", "出手数", "三分命中数", "出手数", "罚球命中数", "出手数", 
			"进攻篮板", "防守篮板", "总篮板数","助攻", "抢断", "盖帽", "失误", "犯规", "得分"
		};
    
    String table_3_columns[] ={
			"球员", "投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "进攻篮板效率", "防守篮板效率", "抢断效率", "助攻率"
		};
    
    String table_4_columns[] ={
    		"球员", "投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "进攻篮板效率", "防守篮板效率", "抢断效率", "助攻率"
		};
    
	/**
	 * Create the application.
	 */
	public TeamsSelectionFrame() {//构造函数
		initialize();
		timer = new Timer();                           //初始化图片循环切换线程
        timer.scheduleAtFixedRate(new ScheduleTask(), 
                INITIAL_DELAY, PERIOD_INTERVAL);
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
		lblNewLabel_1.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE.jpg"));
		lblNewLabel_2.setBounds(10, 120, 150, 100);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseListener(){
 
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
			}});
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET.jpg"));
		lblNewLabel_3.setBounds(10, 230, 150, 100);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND.jpg"));
		lblNewLabel_4.setBounds(10, 340, 150, 100);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL.jpg"));
		lblNewLabel_5.setBounds(10, 450, 150, 100);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(180, 10, 150, 100);
		lblNewLabel_6.setIcon(new ImageIcon("pictures\\BKN.jpg"));
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(180, 120, 150, 100);
		lblNewLabel_7.setIcon(new ImageIcon("pictures\\BOS.jpg"));
		panel_2.add(lblNewLabel_7);
		lblNewLabel_7.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(180, 230, 150, 100);
		lblNewLabel_8.setIcon(new ImageIcon("pictures\\NYK.jpg"));
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(180, 340, 150, 100);
		lblNewLabel_9.setIcon(new ImageIcon("pictures\\PHI.jpg"));
		panel_2.add(lblNewLabel_9);
		lblNewLabel_9.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(180, 450, 150, 100);
		lblNewLabel_10.setIcon(new ImageIcon("pictures\\TOR.jpg"));
		panel_2.add(lblNewLabel_10);
		lblNewLabel_10.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("pictures\\ATL.jpg"));
		lblNewLabel_11.setBounds(350, 10, 150, 100);
		panel_2.add(lblNewLabel_11);
		lblNewLabel_11.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon("pictures\\CHA.jpg"));
		lblNewLabel_12.setBounds(350, 120, 150, 100);
		panel_2.add(lblNewLabel_12);
		lblNewLabel_12.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("pictures\\MIA.jpg"));
		lblNewLabel_13.setBounds(350, 230, 150, 100);
		panel_2.add(lblNewLabel_13);
		lblNewLabel_13.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon("pictures\\ORL.jpg"));
		lblNewLabel_14.setBounds(350, 340, 150, 100);
		panel_2.add(lblNewLabel_14);
		lblNewLabel_14.addMouseListener(new MouseListener(){

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
			}});
		
		lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon("pictures\\WAS.jpg"));
		lblNewLabel_15.setBounds(350, 450, 150, 100);
		panel_2.add(lblNewLabel_15);
		lblNewLabel_15.addMouseListener(new MouseListener(){

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
			}});
		
		JPanel panel = new JPanel();
		panel.setBounds(350, 0, 250, 100);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		changeLabel = new JLabel("");
		changeLabel.setBounds(0, 0, 250, 100);
		panel.add(changeLabel);
		changeLabel.setIcon(new ImageIcon("pictures\\Kobe.jpg"));
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 1000, 600);
		mainpanel.setPreferredSize(new Dimension(1000,1000));
		mainpanel.setLayout(null);
		mainpanel.add(panel);
		mainpanel.add(panel_1);
		mainpanel.add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 150, 100);
		mainpanel.add(panel_3);
		panel_3.setLayout(null);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(14, 31, 113, 27);
		panel_3.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					TeamsSelectionFrame.scrollPane.setVisible(false);
					TeamsSelectionFrame.flag=false;
					MainFrame.panel.setVisible(true);
					MainFrame.frame.setTitle("NBA");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		scrollPane = new JScrollPane(mainpanel);
		scrollPane.setBounds(0, 0, 990, 560);
		
	}
	
	public void setTeamsInfo(String teamName){
		TeamsSelectionFrame.scrollPane.setVisible(false);
		TeamsInfoFrame.scrollPane.setVisible(true);
		TeamsSelectionFrame.flag=false;
		MainFrame.frame.setTitle(teamName);
		
		
		compute=new Compute();
		TeamWithPlayersVO twpvo=compute.getTeamAnalysis(teamName);
		TeamVO tvo=new TeamVO();
		String fullName="teamName";
		String abbreviation="null";
		String location="null";
		char division=' ';
		String zone="null";
		String home="null";
		int setupTime=0;
		if(twpvo.getTeam()!=null){
			tvo=twpvo.getTeam();
			fullName=tvo.getFullName();
			abbreviation=tvo.getAbbreviation();
			location=tvo.getLocation();
			division=tvo.getDivision();
			zone=tvo.getZone();
			home=tvo.getHome();
			setupTime=tvo.getSetupTime();
		}
		ArrayList<PlayerVO> players=twpvo.getPlayers();
		
		Object rows[][] = new Object[1][7];
		rows[0][0]=fullName;
		rows[0][1]=abbreviation;
		rows[0][2]=location;
		rows[0][3]=division;
		rows[0][4]=zone;
		rows[0][5]=home;
		rows[0][6]=setupTime;
		
		Object table_1_rows[][] = new Object[20][18];
		
		for(int i=0;i<players.size();i++){
			PlayerVO pvo=players.get(i);
			int appearance=pvo.getAppearance();
			table_1_rows[i][0]=pvo.getName();table_1_rows[i][1]=pvo.getAppearance();table_1_rows[i][2]=handle((double)pvo.getPlayTime(), appearance);
			table_1_rows[i][3]=handle((double)pvo.getHit(), appearance);table_1_rows[i][4]=handle((double)pvo.getShot(), appearance);table_1_rows[i][5]=handle((double)pvo.getThirdHit(), appearance);
			table_1_rows[i][6]=handle((double)pvo.getThirdshot(), appearance);table_1_rows[i][7]=handle((double)pvo.getFreeHit(), appearance);table_1_rows[i][8]=handle((double)pvo.getFreeshot(), appearance);
			table_1_rows[i][9]=handle((double)pvo.getOffensiveRebound(), appearance);table_1_rows[i][10]=handle((double)pvo.getDefensiveRebound(), appearance);table_1_rows[i][11]=handle((double)pvo.getTotalRebound(), appearance);
			table_1_rows[i][12]=handle((double)pvo.getAssist(), appearance);table_1_rows[i][13]=handle((double)pvo.getSteal(), appearance);table_1_rows[i][14]=handle((double)pvo.getBlock(), appearance);
			table_1_rows[i][15]=handle((double)pvo.getMiss(), appearance);table_1_rows[i][16]=handle((double)pvo.getFoul(), appearance);table_1_rows[i][17]=handle((double)pvo.getScore(), appearance);
		}
		int k=players.size();
		int appearance=tvo.getAppearance();
		table_1_rows[k][0]=tvo.getFullName();table_1_rows[k][1]=tvo.getAppearance();table_1_rows[k][2]=0.0;
		table_1_rows[k][3]=handle((double)tvo.getHit(), appearance);table_1_rows[k][4]=handle((double)tvo.getShot(), appearance);table_1_rows[k][5]=handle((double)tvo.getThirdHit(), appearance);
		table_1_rows[k][6]=handle((double)tvo.getThirdshot(), appearance);table_1_rows[k][7]=handle((double)tvo.getFreeHit(), appearance);table_1_rows[k][8]=handle((double)tvo.getFreeshot(), appearance);
		table_1_rows[k][9]=handle((double)tvo.getOffensiveRebound(), appearance);table_1_rows[k][10]=handle((double)tvo.getDefensiveRebound(), appearance);table_1_rows[k][11]=handle((double)tvo.getTotalRebound(), appearance);
		table_1_rows[k][12]=handle((double)tvo.getAssist(), appearance);table_1_rows[k][13]=handle((double)tvo.getSteal(), appearance);table_1_rows[k][14]=handle((double)tvo.getBlock(), appearance);
		table_1_rows[k][15]=handle((double)tvo.getMiss(), appearance);table_1_rows[k][16]=handle((double)tvo.getFoul(), appearance);table_1_rows[k][17]=handle((double)tvo.getScore(), appearance);

		
		Object table_2_rows[][] = new Object[20][18];
		for(int i=0;i<players.size();i++){
			PlayerVO pvo=players.get(i);
			table_2_rows[i][0]=pvo.getName();table_2_rows[i][1]=pvo.getAppearance();table_2_rows[i][2]=pvo.getPlayTime();
			table_2_rows[i][3]=pvo.getHit();table_2_rows[i][4]=pvo.getShot();table_2_rows[i][5]=pvo.getThirdHit();
			table_2_rows[i][6]=pvo.getThirdshot();table_2_rows[i][7]=pvo.getFreeHit();table_2_rows[i][8]=pvo.getFreeshot();
			table_2_rows[i][9]=pvo.getOffensiveRebound();table_2_rows[i][10]=pvo.getDefensiveRebound();table_2_rows[i][11]=pvo.getTotalRebound();
			table_2_rows[i][12]=pvo.getAssist();table_2_rows[i][13]=pvo.getSteal();table_2_rows[i][14]=pvo.getBlock();
			table_2_rows[i][15]=pvo.getMiss();table_2_rows[i][16]=pvo.getFoul();table_2_rows[i][17]=pvo.getScore();
		}
		int n=players.size();
		table_2_rows[n][0]=tvo.getFullName();table_2_rows[n][1]=tvo.getAppearance();table_2_rows[n][2]=0;
		table_2_rows[n][3]=tvo.getHit();table_2_rows[n][4]=tvo.getShot();table_2_rows[n][5]=tvo.getThirdHit();
		table_2_rows[n][6]=tvo.getThirdshot();table_2_rows[n][7]=tvo.getFreeHit();table_2_rows[n][8]=tvo.getFreeshot();
		table_2_rows[n][9]=tvo.getOffensiveRebound();table_2_rows[n][10]=tvo.getDefensiveRebound();table_2_rows[n][11]=tvo.getTotalRebound();
		table_2_rows[n][12]=tvo.getAssist();table_2_rows[n][13]=tvo.getSteal();table_2_rows[n][14]=tvo.getBlock();
		table_2_rows[n][15]=tvo.getMiss();table_2_rows[n][16]=tvo.getFoul();table_2_rows[n][17]=tvo.getScore();
		
		
		Object table_3_rows[][] = new Object[20][12];
		for(int i=0;i<players.size();i++){
			PlayerVO pvo=players.get(i);
			table_3_rows[i][0]=pvo.getName();
			table_3_rows[i][1]=pvo.getHitRate();
			table_3_rows[i][2]=pvo.getThirdHitRate();
			table_3_rows[i][3]=pvo.getFreeHitRate();
			table_3_rows[i][4]=0.0;
			table_3_rows[i][5]=0.0;
			table_3_rows[i][6]=0.0;
			table_3_rows[i][7]=0.0;
			table_3_rows[i][8]=pvo.getOffensiveReboundRate();
			table_3_rows[i][9]=pvo.getDefensiveReboundRate();
			table_3_rows[i][10]=pvo.getStealRate();
			table_3_rows[i][11]=pvo.getAssistRate();
		}
		int m=players.size();
		table_3_rows[m][0]=tvo.getFullName();
		table_3_rows[m][1]=tvo.getHitRate();
		table_3_rows[m][2]=tvo.getThirdHitRate();
		table_3_rows[m][3]=tvo.getFreeHitRate();
		table_3_rows[m][4]=tvo.getWinRate();
		table_3_rows[m][5]=tvo.getOffensiveRound();
		table_3_rows[m][6]=tvo.getOffensiveEfficiency();
		table_3_rows[m][7]=tvo.getDefensiveEfficiency();
		table_3_rows[m][8]=tvo.getOffensiveReboundEfficiency();
		table_3_rows[m][9]=tvo.getDefensiveReboundEfficiency();
		table_3_rows[m][10]=tvo.getStealEfficiency();
		table_3_rows[m][11]=tvo.getAssistEfficiency();
		
		
		DefaultTableModel model=new DefaultTableModel(rows, columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		model=new DefaultTableModel(rows, columns);
		TeamsInfoFrame.table.setModel(model);
		
		DefaultTableModel model1=new DefaultTableModel(table_1_rows, table_1_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        TeamsInfoFrame.table_1.setModel(model1);
        TeamsInfoFrame.table_1.setRowSorter(new TableRowSorter<TableModel>(model1));
        
        DefaultTableModel model2=new DefaultTableModel(table_2_rows, table_2_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        TeamsInfoFrame.table_2.setModel(model2);
        TeamsInfoFrame.table_2.setRowSorter(new TableRowSorter<TableModel>(model2));
		
        DefaultTableModel model3=new DefaultTableModel(table_3_rows, table_3_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        TeamsInfoFrame.table_3.setModel(model3);
        TeamsInfoFrame.table_3.setRowSorter(new TableRowSorter<TableModel>(model3));
        
        
	}
	
	//图片循环切换线程
	private class ScheduleTask extends TimerTask {
		
        public void run() {
        	while(flag){
        		changeLabel.setIcon(new ImageIcon("pictures\\Kobe.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
            	changeLabel.setIcon(new ImageIcon("pictures\\ROYALTY.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
            	changeLabel.setIcon(new ImageIcon("pictures\\WADE.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
    		}
        	
            	
        	
        	
        	}
        }
	
	
	public double handle(double a, int b){
		double result=a/(double)b;
		BigDecimal c=new BigDecimal(result);  
		double f1=c.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		return f1;
	}
	
	
}
