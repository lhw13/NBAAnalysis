package presentation.mainui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.FlatInnerBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.Glass3DTitlePainter;
import org.jvnet.substance.watermark.SubstanceWoodWatermark;

import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.teamsui.TeamsSelectionFrame;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.Compute;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

public class MainFrame {

	public static JFrame frame;

	public static JPanel panel;
	
	public static PlayerInfoPanel pip;
	
	Compute compute;
	
	String table_1_columns[] ={
			"球队", "投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "进攻篮板效率", "防守篮板效率", "抢断效率", "助攻率"
		};
	
	String table_columns[] ={
			"球员", "位置", "赛区", "分区", "得分", "篮板", "助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双"
		};
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());      
            if (System.getProperty("substancelaf.useDecorations") == null) {      
                JFrame.setDefaultLookAndFeelDecorated(true);  
                JDialog.setDefaultLookAndFeelDecorated(true);
                /**  
                 * setDefaultLookAndFeelDecorated设置为true或者false     
                 * JDialog也可以使用setDefaultLookAndFeelDecorated    
                 */   
            }
                
            // 设置当前的主题风格，同样我 们还可以设置当前的按钮形状，水印风格等等      
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceWoodWatermark());
            SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
            SubstanceLookAndFeel.setCurrentTitlePainter(new Glass3DTitlePainter());
            SubstanceLookAndFeel.setCurrentBorderPainter(new FlatInnerBorderPainter());
			} catch (Exception e){
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initPanels(){
		TeamsSelectionFrame window = new TeamsSelectionFrame();
		frame.getContentPane().add(TeamsSelectionFrame.scrollPane);
		TeamsSelectionFrame.scrollPane.setVisible(false);
		
		TeamsInfoFrame window1 = new TeamsInfoFrame();
		frame.getContentPane().add(TeamsInfoFrame.scrollPane);
		TeamsInfoFrame.scrollPane.setVisible(false);
		
		TeamsRankingFrame window2 = new TeamsRankingFrame();
		frame.getContentPane().add(TeamsRankingFrame.scrollPane);
		TeamsRankingFrame.scrollPane.setVisible(false);
		
		new PlayerSelectionPanel();
		frame.getContentPane().add(PlayerSelectionPanel.scrollPane);
		PlayerSelectionPanel.scrollPane.setVisible(false);
		
		Object table_rows[][] ={
				{"X0", "中锋", "西部西南区", 999, 888, 777, 666, 555, 444, 333, 500, 400, 0, 100, 200, 300},
		};
		String table_columns[] ={
				"球员", "位置", "赛区", "得分", "篮板", "助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双"
			};
		DefaultTableModel model=new DefaultTableModel(table_rows, table_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		new PlayerRankingPanel(model);
		frame.getContentPane().add(PlayerRankingPanel.scrollPane);
		PlayerRankingPanel.scrollPane.setVisible(false);
		
		pip = new PlayerInfoPanel();
		frame.getContentPane().add(PlayerInfoPanel.scrollPane);
		PlayerInfoPanel.scrollPane.setVisible(false);
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\NBA.jpg"));
		frame.setTitle("NBA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("NBA球队选择界面");
		btnNewButton.setBounds(65, 34, 159, 55);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					TeamsSelectionFrame.scrollPane.setVisible(true);
					TeamsSelectionFrame.flag=true;
					MainFrame.frame.setTitle("NBA球队选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("NBA球队排名界面");
		btnNewButton_1.setBounds(65, 121, 159, 55);
		panel.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					
					setTeamsRanking();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnNewButton_2 = new JButton("NBA球员选择界面");
		btnNewButton_2.setBounds(65, 211, 159, 55);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					PlayerSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnNewButton_3 = new JButton("NBA球员排名界面");
		btnNewButton_3.setBounds(65, 294, 159, 55);
		panel.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					
					setPlayersRanking();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}
	
	public void setTeamsRanking(){//设置球队排名面板信息
		MainFrame.panel.setVisible(false);
		TeamsRankingFrame.scrollPane.setVisible(true);
		MainFrame.frame.setTitle("NBA球队排名");
		
		compute=new Compute();
		ArrayList<TeamVO> tvoList=compute.getTeamAnalysis();
		
		Object table_rows[][] = new Object[30][17];
		for(int i=0;i<tvoList.size();i++){
			TeamVO tvo=tvoList.get(i);
			table_rows[i][0]=tvo.getFullName();
			table_rows[i][1]=tvo.getAppearance();table_rows[i][2]=tvo.getHit();table_rows[i][3]=tvo.getShot();
			table_rows[i][4]=tvo.getThirdHit();table_rows[i][5]=tvo.getThirdshot();table_rows[i][6]=tvo.getFreeHit();
			table_rows[i][7]=tvo.getFreeshot();table_rows[i][8]=tvo.getOffensiveRebound();table_rows[i][9]=tvo.getDefensiveRebound();
			table_rows[i][10]=tvo.getTotalRebound();table_rows[i][11]=tvo.getAssist();table_rows[i][12]=tvo.getSteal();
			table_rows[i][13]=tvo.getBlock();table_rows[i][14]=tvo.getMiss();table_rows[i][15]=tvo.getFoul();
			table_rows[i][16]=tvo.getScore();
		}
		
		Object table_1_rows[][] = new Object[30][12];
		for(int i=0;i<tvoList.size();i++){
			TeamVO tvo=tvoList.get(i);
			table_1_rows[i][0]=tvo.getFullName();
			table_1_rows[i][1]=tvo.getHitRate();
			table_1_rows[i][2]=tvo.getThirdHitRate();
			table_1_rows[i][3]=tvo.getFreeHitRate();
			table_1_rows[i][4]=tvo.getWinRate();
			table_1_rows[i][5]=tvo.getOffensiveRound();
			table_1_rows[i][6]=tvo.getOffensiveEfficiency();
			table_1_rows[i][7]=tvo.getDefensiveEfficiency();
			table_1_rows[i][8]=tvo.getOffensiveReboundEfficiency();
			table_1_rows[i][9]=tvo.getDefensiveReboundEfficiency();
			table_1_rows[i][10]=tvo.getStealEfficiency();
			table_1_rows[i][11]=tvo.getAssistEfficiency();
		}
		
		DefaultTableModel model=new DefaultTableModel(table_rows, table_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        TeamsRankingFrame.table.setModel(model);
        TeamsRankingFrame.table.setRowSorter(new TableRowSorter<TableModel>(model));
        
        DefaultTableModel model1=new DefaultTableModel(table_1_rows, table_1_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        TeamsRankingFrame.table_1.setModel(model1);
        TeamsRankingFrame.table_1.setRowSorter(new TableRowSorter<TableModel>(model1));
	}
	
	public void setPlayersRanking(){//设置球员排名面板信息
		MainFrame.panel.setVisible(false);
		MainFrame.frame.setTitle("NBA球员排名");
		
		compute=new Compute();
		ArrayList<PlayerVO> pvoList=compute.getPlayerAnalysis();
		
		Object table_rows[][] = new Object[100][17];
		for(int i=0;i<pvoList.size();i++){
			PlayerVO pvo=pvoList.get(i);
			table_rows[i][0]=pvo.getName();
			table_rows[i][1]=pvo.getPosition();table_rows[i][2]=pvo.getDivision();table_rows[i][3]=pvo.getZone();
			table_rows[i][4]=pvo.getScore();table_rows[i][5]=pvo.getTotalRebound();table_rows[i][6]=pvo.getAssist();
			table_rows[i][7]=pvo.getBlock();table_rows[i][8]=pvo.getSteal();table_rows[i][9]=pvo.getFoul();
			table_rows[i][10]=pvo.getMiss();table_rows[i][11]=pvo.getPlayTime();table_rows[i][12]=pvo.getEfficiency();
			table_rows[i][13]=pvo.getHitRate();table_rows[i][14]=pvo.getThirdHitRate();table_rows[i][15]=pvo.getFreeHitRate();
			table_rows[i][16]="两双";//两双
		}
		
		DefaultTableModel model=new DefaultTableModel(table_rows, table_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
        new PlayerRankingPanel(model);
		frame.getContentPane().add(PlayerRankingPanel.scrollPane);
		PlayerRankingPanel.scrollPane.setVisible(true);
	}
	
}

