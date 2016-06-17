package hotui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.matchui.MatchDetailInfoPanel;
import presentation.matchui.MatchSelectionPanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.teamsui.TeamsSelectionFrame;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Team;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Vector;

public class HotRankingPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	Vector columnName1;
	Vector columnName2;
	Vector columnName3;
	
	
	DefaultTableModel model_1 = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass(); 
		}
	};
	DefaultTableModel model_2 = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	DefaultTableModel model_3 = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	BLService blservice = BLController.getInstance();
	JLabel label;
	JLabel label_1;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel labelscore;
	JLabel labelrebound;
	
	JLabel labelwest;
	JLabel labeleast;
	
	private JLabel labelassist;
	private JLabel labelsteal;
	private JLabel labelblock;
	private JTable table_1;
	String root="每日";
	String leaf="得分榜";
	String root3 = "西";
	private JTable table_2;
	private JScrollPane scrollPane_3;
	private JTable table_3;
	ArrayList<Team> teamWest ;
	ArrayList<Team> teamEast ;
	public HotRankingPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		//panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		scrollPane = new JScrollPane(panelOfBottom);
		
		JButton button = new JButton("返回");
		button.setBounds(5, 10, 93, 23);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				try {
					HotRankingPanel.scrollPane.setVisible(false);
					MainFrame.panel.setVisible(true);
					MainFrame.currentPanel = Panels.MainFrame;
					MainFrame.frame.setTitle("NBA");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		panelOfBottom.add(button);
		label = new JLabel("每日统计");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(545, 35, 65, 31);
		label.addMouseListener(new Listener1());
		panelOfBottom.add(label);
		
		lblNewLabel_1 = new JLabel("赛季统计");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.gray);
		lblNewLabel_1.setBounds(620, 35, 65, 31);
		lblNewLabel_1.addMouseListener(new Listener1());
		panelOfBottom.add(lblNewLabel_1);
		
		label_1 = new JLabel("进步最快");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setForeground(Color.gray);
		label_1.setBounds(695, 35, 65, 31);
		label_1.addMouseListener(new Listener1());
		panelOfBottom.add(label_1);
		
		 lblNewLabel = new JLabel("赛季热点球队");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.gray);
		lblNewLabel.setBounds(770, 35, 101, 31);
		lblNewLabel.addMouseListener(new Listener1());
		panelOfBottom.add(lblNewLabel);
		
		labelscore = new JLabel("得分榜");
		labelscore.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelscore.setBounds(545, 73, 54, 15);
		labelscore.addMouseListener(new Listener2());
		panelOfBottom.add(labelscore);
		
		labelrebound = new JLabel("篮板榜");
		labelrebound.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelrebound.setForeground(Color.gray);
		labelrebound.setBounds(607, 73, 54, 15);
		labelrebound.addMouseListener(new Listener2());
		panelOfBottom.add(labelrebound);
		
		labelassist = new JLabel("助攻榜");
		labelassist.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelassist.setForeground(Color.gray);
		labelassist.setBounds(671, 73, 54, 15);
		labelassist.addMouseListener(new Listener2());
		panelOfBottom.add(labelassist);
		
		labelsteal = new JLabel("抢断榜");
		labelsteal.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelsteal.setForeground(Color.gray);
		labelsteal.setBounds(735, 73, 54, 15);
		labelsteal.addMouseListener(new Listener2());
		panelOfBottom.add(labelsteal);
		
		labelblock = new JLabel("盖帽榜");
		labelblock.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelblock.setForeground(Color.gray);
		labelblock.setBounds(799, 73, 54, 15);
		labelblock.addMouseListener(new Listener2());
		panelOfBottom.add(labelblock);
		
		labelwest = new JLabel("西部排名");
		labelwest.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labelwest.setForeground(Color.white);
		labelwest.setBounds(54, 49, 79, 23);
		labelwest.addMouseListener(new Listener3());
		panelOfBottom.add(labelwest);
		
		labeleast = new JLabel("东部排名");
		labeleast.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labeleast.setForeground(Color.gray);
		labeleast.setBounds(143, 49, 79, 23);
		labeleast.addMouseListener(new Listener3());
		panelOfBottom.add(labeleast);
		
		table_1 = new JTable(model_1);
		table_1.setBounds(545, 100, 385, 216);
		table_1.addMouseListener(new tableListener1());
		table_1.setShowGrid(false);
		
		DefaultTableCellRenderer tcr_1 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_1.setHorizontalAlignment(JLabel.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr_1);
		
		panelOfBottom.add(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(35, 341, 925, 223);
		panelOfBottom.add(scrollPane_2);
		
		table_2 = new JTable(model_2);
		table_2.addMouseListener(new tableListener2());
		table_2.setShowGrid(false);
		
		DefaultTableCellRenderer tcr_2 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_2.setHorizontalAlignment(JLabel.CENTER);
		table_2.setDefaultRenderer(Object.class, tcr_2);
		
		scrollPane_2.setViewportView(table_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(35, 85, 420, 231);
		panelOfBottom.add(scrollPane_3);
		
		table_3 = new JTable(model_3);
		table_3.addMouseListener(new tableListener3());
		table_3.setShowGrid(false);
		
		DefaultTableCellRenderer tcr_3 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_3.setHorizontalAlignment(JLabel.CENTER);
		table_3.setDefaultRenderer(Object.class, tcr_3);
		
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblNewLabel_2 = new JLabel("今日比赛");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(35, 316, 63, 23);
		panelOfBottom.add(lblNewLabel_2);
		
		scrollPane.setBounds(0, 0, 1000, 600);
		add(scrollPane);
		
		String[] names1 = {"","","",""};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}		
		
		String[] names2 = {"主队","胜","负","得分","篮板","助攻","比分","助攻","篮板","得分","负","胜","客队"};
		columnName2 = new Vector();
		for(int i=0;i<names2.length;i++) {
			columnName2.add(names2[i]);
		}		
		String[] names3 = {"","","西部球队","胜场","负场","胜率","胜差"};
		columnName3 = new Vector();
		for(int i=0;i<names3.length;i++) {
			columnName3.add(names3[i]);
		}		
	}
	
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	
	// 保留小数点
	public String handleDecimal(double f) {
		Double result = new Double(f);
		if(!result.isInfinite()&&!result.isNaN())
		return String.format("%.1f", f);
		return result.toString();
	}
	
	public void update1() {
		Vector rowDatas1 = new Vector();
		ImageIcon picture;
		switch(root) {
		case "每日":		
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getDailyHotPlayerVO("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(playerTemp.getScore());			
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getDailyHotPlayerVO("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(playerTemp.getTotalRebound());					
					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getDailyHotPlayerVO("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(playerTemp.getAssist());									
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<PlayerVO> players4 = blservice.getDailyHotPlayerVO("steal", 5);

				for(int i=0;i<players4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players4.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(playerTemp.getSteal());									
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<PlayerVO> players5 = blservice.getDailyHotPlayerVO("blockShot", 5);

				for(int i=0;i<players5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players5.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(playerTemp.getBlock());									
					rowDatas1.add(rowData1);
				}
				break;
			}	
				
			break;
		case "赛季":
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getHotPlayerVO("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getScore()
							/playerTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getHotPlayerVO("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));

					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getHotPlayerVO("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getAssist()
							/playerTemp.getAppearance()));	
					
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<PlayerVO> players4 = blservice.getHotPlayerVO("steal", 5);

				for(int i=0;i<players4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players4.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getSteal()
							/playerTemp.getAppearance()));	
					
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<PlayerVO> players5 = blservice.getHotPlayerVO("blockShot", 5);

				for(int i=0;i<players5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players5.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getBlock()/playerTemp.getAppearance()));									
					rowDatas1.add(rowData1);
				}
				break;
			}	
			break;
		case "进步":
			if(columnName1.size()==4)
				columnName1.add("");
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getBestPromotion("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getScore()
							/playerTemp.getAppearance()));		
					rowData1.add(" "+handleDecimal(playerTemp.getScorePromotion())+" 提升率");
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getBestPromotion("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));	
					rowData1.add(" "+handleDecimal(playerTemp.getReboundPromotion())+" 提升率");						

					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getBestPromotion("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getNameWithoutNum());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));	
					rowData1.add(" "+handleDecimal(playerTemp.getAssistPromotion())+" 提升率");						
					rowDatas1.add(rowData1);
				}
				break;
			}
			break;
			
		case "赛季热点球队":
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<TeamVO> teams1 = blservice.getHotTeamVO("point", 5);
				for(int i=0;i<teams1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(40, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getScore()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<TeamVO> teams2 = blservice.getHotTeamVO("rebound", 5);
				for(int i=0;i<teams2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams2.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(40, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getTotalRebound()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<TeamVO> teams3 = blservice.getHotTeamVO("assist", 5);
				for(int i=0;i<teams3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams3.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(40, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getAssist()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<TeamVO> teams4 = blservice.getHotTeamVO("steal", 5);
				for(int i=0;i<teams4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams4.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(40, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getSteal()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<TeamVO> teams5 = blservice.getHotTeamVO("blockShot", 5);
				for(int i=0;i<teams5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams5.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(40, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getBlock()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			}	
			break;
		}
			model_1.setDataVector(rowDatas1, columnName1);		
			model_1.setColumnCount(table_1.getColumnCount());
			model_1.setRowCount(rowDatas1.size());
			table_1.setModel(model_1);
			table_1.setRowHeight(40);
			int[] width_1={10,120,170,80};
			int[] width_11={10,100,130,40,100};
			if(root.equals("进步")) 
				table_1.setColumnModel(getColumn(table_1, width_11));
			else
				table_1.setColumnModel(getColumn(table_1, width_1));
			table_1.updateUI();
	}

	public void update2() {
		Vector rowDatas2 = new Vector();
		
		ArrayList<MatchPO> matches = blservice.getAllMatch();
		int size = matches.size();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		String dateLatest = sdf.format(matches.get(size-1).getDate().getTime());
		for(int i=size-1;i>=0;i--) {
			MatchPO matchTemp = matches.get(i);
			String dateTemp = sdf.format(matchTemp.getDate().getTime());
			if(!dateTemp.equals(dateLatest)  )  break;
			Vector rowData2 = new Vector();
			TeamInMatchesPO team1= matchTemp.getTeam1();//update2()
			TeamInMatchesPO team2= matchTemp.getTeam2();			
			TeamWithPlayersVO team11= blservice.getTeamAnalysis(team1.getAbbreviation());
			TeamWithPlayersVO team22= blservice.getTeamAnalysis(team2.getAbbreviation());
			
			rowData2.add(MainFrame.psp.translate(team1.getAbbreviation()));
			rowData2.add((int)(team11.getTeam().getWinRate()*team11.getTeam().getAppearance()));
			rowData2.add(team11.getTeam().getAppearance()-
					(int)(team11.getTeam().getWinRate()*team11.getTeam().getAppearance()));
			rowData2.add(team1.getHighestScore().getName().split(" ")[0]+
					" "+team1.getHighestScore().getScore());//得分
			rowData2.add(team1.getHighestRebound().getName().split(" ")[0]+
					" "+team1.getHighestRebound().getTotalRebound());//篮板
			rowData2.add(team1.getHighestAssist().getName().split(" ")[0]+
					" "+team1.getHighestAssist().getAssist());//助攻
			
			rowData2.add(matchTemp.getFinalScore());//比分
			
			rowData2.add(team2.getHighestAssist().getName().split(" ")[0]+
					" "+team2.getHighestAssist().getAssist());//助攻
			rowData2.add(team2.getHighestRebound().getName().split(" ")[0]+
					" "+team2.getHighestRebound().getTotalRebound());//篮板
			rowData2.add(team2.getHighestScore().getName().split(" ")[0]+//得分
					" "+team2.getHighestScore().getScore());
			rowData2.add(team22.getTeam().getAppearance()-
					(int)(team22.getTeam().getWinRate()*team22.getTeam().getAppearance()));
			rowData2.add((int)(team22.getTeam().getWinRate()*team22.getTeam().getAppearance()));
			rowData2.add(MainFrame.psp.translate(team2.getAbbreviation()));
			rowDatas2.add(rowData2);
		}
		model_2.setDataVector(rowDatas2, columnName2);		
		model_2.setColumnCount(table_2.getColumnCount());
		model_2.setRowCount(rowDatas2.size());
		table_2.setModel(model_2);
		table_2.setRowHeight(40);
		int[] width_2={60,20,20,110,110,110,60,110,110,110,20,20,60};
		table_2.setColumnModel(getColumn(table_2, width_2));
		table_2.updateUI();
	}
	
	public void update3() {
		ImageIcon picture;
		Vector rowDatas3 = new Vector();
		ArrayList<Team> teams = BLController.getInstance().getTeams();		
		Collections.sort(teams,Comparators.getTeamComparator("winRate"));
		teamWest = new ArrayList<Team>();
		teamEast = new ArrayList<Team>();
		for(int i=0;i<teams.size();i++) {
			Team temp = teams.get(i);
			if(temp.getTeamPO().getDivision() == 'E') teamEast.add(temp);
			else teamWest.add(temp);
		}
		switch(root3) {
		case "西":
			columnName3.setElementAt("西部球队", 2);
			int size =teamWest.size();
			int winFirst = teamWest.get(0).getWin();
			for(int i=0;i<size;i++) {
				Team tempWest = teamWest.get(i);
				Vector rowData3 = new Vector();
				rowData3.add(i+1);
				picture = ImageHandle.loadTeam(tempWest.getTeamPO().
						getAbbreviation());
				picture.setImage(picture.getImage().getScaledInstance(50, 50,
						Image.SCALE_DEFAULT));
				rowData3.add(picture);
				rowData3.add(MainFrame.psp.translate(tempWest.getTeamPO().
						getAbbreviation()));
				
				rowData3.add(tempWest.getWin());
				rowData3.add(tempWest.getAppearance()-tempWest.getWin());
				rowData3.add(handleDecimal(tempWest.getWinRate()*100)+"%");
				rowData3.add(winFirst-tempWest.getWin());
				
				rowDatas3.add(rowData3);
			}
			break;
		
		case "东":
			columnName3.setElementAt("东部球队", 2);
			int sizeE =teamEast.size();
			int winFirstE = teamEast.get(0).getWin();
			for(int i=0;i<sizeE;i++) {
				Team tempEast = teamEast.get(i);
				Vector rowData3 = new Vector();
				rowData3.add(i+1);
				picture = ImageHandle.loadTeam(tempEast.getTeamPO().
						getAbbreviation());
				picture.setImage(picture.getImage().getScaledInstance(50, 50,
						Image.SCALE_DEFAULT));
				
				rowData3.add(picture);
				rowData3.add(MainFrame.psp.translate(tempEast.getTeamPO().
						getAbbreviation()));
				
				rowData3.add(tempEast.getWin());
				rowData3.add(tempEast.getAppearance()-tempEast.getWin());
				rowData3.add(handleDecimal(tempEast.getWinRate()*100)+"%");
				rowData3.add(winFirstE-tempEast.getWin());
				
				rowDatas3.add(rowData3);
			}
			break;			
		}
		
		model_3.setDataVector(rowDatas3, columnName3);		
		model_3.setColumnCount(table_3.getColumnCount());
		model_3.setRowCount(rowDatas3.size());
		table_3.setModel(model_3);
		table_3.setRowHeight(50);
		int[] width_3={30,70,80,60,60,60,60};
		table_3.setColumnModel(getColumn(table_3, width_3));
		table_3.updateUI();
	}
	public class Listener1 extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			HotRankingPanel.scrollPane.setVisible(false);
			MainFrame.setPlayersRanking();
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			if(label1.getText().startsWith("每日")){
				root = "每日";
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.WHITE);
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.gray);
				
			} else if(label1.getText().startsWith("赛季统计")) {
				root = "赛季";
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.gray);				
				lblNewLabel_1.setForeground(Color.WHITE);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.gray);
				
			} else if(label1.getText().startsWith("进步")) {
				root = "进步";
				labelsteal.setVisible(false);
				labelblock.setVisible(false);
				
				label.setForeground(Color.gray);
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.WHITE);
				lblNewLabel.setForeground(Color.gray);								
				
			} else if (label1.getText().equals("赛季热点球队")) {
				root = "赛季热点球队";
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.gray);				
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.WHITE);
			}
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			update1();
		}		
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());

		}
	}
	
	public class tableListener1 extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			int r = table.getSelectedRow();
			if(!root.equals("赛季热点球队")){
				String name = (String)table.getValueAt(r, 2);
				MainFrame.pip.update(name);
				MainFrame.pip.scrollPane.setVisible(true);
				MainFrame.hrp.scrollPane.setVisible(false);
				
				MainFrame.backPanels.add(MainFrame.currentPanel);
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
				MainFrame.frame.setTitle("NBA球员信息");
			} else {
				String name = (String)table.getValueAt(r, 2);
				TeamsSelectionFrame.goToTeam(translate(name));
				MainFrame.hrp.scrollPane.setVisible(false);
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JTable table =(JTable) e.getSource();
			
			table.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			
		}
		public void mouseExited(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			table.setCursor(Cursor.getDefaultCursor());

		}
	}

	public class Listener2 extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			HotRankingPanel.scrollPane.setVisible(false);
			MainFrame.setPlayersRanking();
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			switch(label1.getText()) {
			case "得分榜":
				leaf = "得分榜";
				labelscore.setForeground(Color.white);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray);
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			case "篮板榜":
				leaf = "篮板榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.white);
				labelassist.setForeground(Color.gray);
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			case "助攻榜":
				leaf = "助攻榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.white );
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			
			case "抢断榜":
				leaf = "抢断榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray );
				labelsteal.setForeground(Color.white);
				labelblock.setForeground(Color.gray);
				break;
			case "盖帽榜":
				leaf = "盖帽榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray );
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.white);
				break;
			}
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			update1();
		}
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());

		}
	}
	
	public class tableListener2 extends MouseAdapter{

		
		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			int r = table.getSelectedRow();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			ArrayList<MatchPO> matches = blservice.getAllMatch();
			int size = matches.size();
			MatchPO matchTemp = matches.get(size-1-r);
			String dateTemp = sdf.format(matchTemp.getDate().getTime());
			
			MatchSelectionPanel.goToMatchFromPlayer(dateTemp,matchTemp.getTeam1().getAbbreviation());
			MainFrame.hrp.scrollPane.setVisible(false);
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JTable table =(JTable) e.getSource();
			
			table.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			
		}
		public void mouseExited(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			table.setCursor(Cursor.getDefaultCursor());

		}
	}

	public class Listener3 extends MouseAdapter{

		
		@Override
		public void mouseClicked(MouseEvent e) {
			HotRankingPanel.scrollPane.setVisible(false);
			MainFrame.setTeamsRanking();
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			switch(label1.getText()) {
			case "西部排名":
				root3 = "西";
				labelwest.setForeground(Color.white);
				labeleast.setForeground(Color.gray);
				break;
			case "东部排名":
				root3 = "东";
				labelwest.setForeground(Color.gray);
				labeleast.setForeground(Color.white);
				break;
			}
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			update3();
		}
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());

		}
	}
	
	public class tableListener3 extends MouseAdapter{

		
		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			int r = table.getSelectedRow();
			
			if(root3.equals("西"))
				TeamsSelectionFrame.goToTeam(teamWest.get(r).getAbbreviation());				
			 else 
				TeamsSelectionFrame.goToTeam(teamWest.get(r).getAbbreviation());
			MainFrame.hrp.scrollPane.setVisible(false);
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JTable table =(JTable) e.getSource();
			
			table.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			
		}
		public void mouseExited(MouseEvent e) {
			JTable table =(JTable) e.getSource();
			table.setCursor(Cursor.getDefaultCursor());

		}
	}
	public static String translate(String team){
		String result = "GSW";
        if(team.equals("马刺"))
        	result = "SAS";
        else if(team.equals("灰熊"))
        	result = "MEM";
        else if(team.equals("小牛"))
        	result = "DAL";
        else if(team.equals("火箭"))
        	result = "HOU";
        else if(team.equals("鹈鹕"))
        	result = "NOP";
        else if(team.equals("森林狼"))
        	result = "MIN";
        else if(team.equals("掘金"))
        	result = "DEN";
        else if(team.equals("爵士"))
        	result = "UTA";
        else if(team.equals("开拓者"))
        	result = "POR";
        else if(team.equals("雷霆"))
        	result = "OKC";
        else if(team.equals("国王"))
        	result = "SAC";
        else if(team.equals("太阳"))
        	result = "PHX";
        else if(team.equals("湖人"))
        	result = "LAL";
        else if(team.equals("快船"))
        	result = "LAC";
        else if(team.equals("勇士"))
        	result = "GSW";
        else if(team.equals("热火"))
        	result = "MIA";
        else if(team.equals("魔术"))
        	result = "ORL";
        else if(team.equals("老鹰"))
        	result = "ATL";
        else if(team.equals("奇才"))
        	result = "WAS";
        else if(team.equals("黄蜂"))
        	result = "CHA";
        else if(team.equals("活塞"))
        	result = "DET";
        else if(team.equals("步行者"))
        	result = "IND";
        else if(team.equals("骑士"))
        	result = "CLE";
        else if(team.equals("公牛"))
        	result = "CHI";
        else if(team.equals("雄鹿"))
        	result = "MIL";
        else if(team.equals("凯尔特人"))
        	result = "BOS";
        else if(team.equals("76人"))
        	result = "PHI";
        else if(team.equals("尼克斯"))
        	result = "NYK";
        else if(team.equals("篮网"))
        	result = "BKN";
        else if(team.equals("猛龙"))
        	result = "TOR";
        
        return result;
	}

}
