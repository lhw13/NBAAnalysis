package presentation.playerui;

import hotui.HotRankingPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.matchui.MatchDetailInfoPanel;
import presentation.matchui.MatchSelectionPanel;
import presentation.teamsui.TeamsInfoFrame;
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

import javax.swing.JComboBox;

import blservice.BLService;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class PlayerComparePanel extends JPanel {
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();
	
	public static JScrollPane scrollPane;
	
	JLabel labelOfPhoto1;
	JLabel labelOfPhoto2;
	JLabel labelOfPhoto3;
	JLabel labelOfPhoto4;
	
	JComboBox comboBoxOfLTeam;
	JComboBox comboBoxOfLPlayer;
	JComboBox comboBoxOfRTeam;
	JComboBox comboBoxOfRPlayer;
	
	ImageIcon picture1;
	ImageIcon picture2;
	ImageIcon picture3;
	ImageIcon picture4;
	
	String pname1="NBA";
	String tname1;
	String pname2="NBA";
	String tname2="";
	
	private JTable table_l;
	private JTable table_c;
	private JTable table_r;
	
	DefaultTableModel model_l = new DefaultTableModel();
	DefaultTableModel model_c = new DefaultTableModel();
	DefaultTableModel model_r = new DefaultTableModel();
	
	Vector columnName_c = new Vector();
	Vector columnName_l = new Vector();
	Vector columnName_r = new Vector();
	
	private JButton button;
	
	public PlayerComparePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
//panel===========================================================
		panelOfBottom.setLayout(null);
		
//label =======================================================
		labelOfPhoto1 = new JLabel("");
		labelOfPhoto1.setBounds(140, 57, 180, 145);
		panelOfBottom.add(labelOfPhoto1);
		
		labelOfPhoto2 = new JLabel("");
		labelOfPhoto2.setBounds(30, 79, 100, 100);
		panelOfBottom.add(labelOfPhoto2);
		
		labelOfPhoto3 = new JLabel("");
		labelOfPhoto3.setBounds(663, 57, 180, 145);
		panelOfBottom.add(labelOfPhoto3);
		
		labelOfPhoto4 = new JLabel("");
		labelOfPhoto4.setBounds(865, 79, 100, 100);
		panelOfBottom.add(labelOfPhoto4);
		
//Icon========================================================
		picture3 = ImageHandle.loadTeam(pname2);
		picture3.setImage(picture3.getImage().getScaledInstance(145, 145,
				Image.SCALE_DEFAULT));
		labelOfPhoto3.setIcon(picture3);
		
//comboBox========================================================
		comboBoxOfLTeam = new JComboBox();
		comboBoxOfLTeam.setBounds(172, 21, 124, 26);
		panelOfBottom.add(comboBoxOfLTeam);
		comboBoxOfLTeam.addItem("选择球队");
		comboBoxOfLTeam.addItemListener(new TeamItemListener('l'));
		
		comboBoxOfLPlayer = new JComboBox();
		comboBoxOfLPlayer.setBounds(306, 21, 124, 26);
		panelOfBottom.add(comboBoxOfLPlayer);
		comboBoxOfLPlayer.addItem("选择球员");
		comboBoxOfLPlayer.addItemListener(new PlayerItemListener('l'));
		
		comboBoxOfRTeam = new JComboBox();
		comboBoxOfRTeam.setBounds(678, 20, 124, 26);
		panelOfBottom.add(comboBoxOfRTeam);
		comboBoxOfRTeam.addItem("选择球队");
		comboBoxOfRTeam.addItemListener(new TeamItemListener('r'));
		
		comboBoxOfRPlayer = new JComboBox();
		comboBoxOfRPlayer.setBounds(826, 20, 124, 26);
		panelOfBottom.add(comboBoxOfRPlayer);
		comboBoxOfRPlayer.addItem("选择球员");
		comboBoxOfRPlayer.addItemListener(new PlayerItemListener('r'));
		
		ArrayList<TeamVO> teams = blservice.getTeamAnalysis();
		for(int i=0;i<teams.size();i++) {
			TeamVO tempvo = teams.get(i);
			comboBoxOfLTeam.addItem(PlayerSelectionPanel.translate(tempvo.getAbbreviation()));
			comboBoxOfRTeam.addItem(PlayerSelectionPanel.translate(tempvo.getAbbreviation()));
		}
		
//column==============================================================
		String[] cname_c = new String[] {""};
		for(int i=0;i<cname_c.length;i++) {
			columnName_c.add(cname_c[i]);
		}	
		
		String[] cname_l = new String[] {""};
		for(int i=0;i<cname_l.length;i++) {
			columnName_l.add(cname_l[i]);
		}	
		
		String[] cname_r = new String[] {""};
		for(int i=0;i<cname_r.length;i++) {
			columnName_r.add(cname_r[i]);
		}	
		
//table=================================================================
		table_l = new JTable(model_l);
		table_l.setBounds(330, 57, 140, 190);
		panelOfBottom.add(table_l);
		
		table_c = new JTable(model_c);
		table_c.setBounds(470, 57, 45, 190);
		panelOfBottom.add(table_c);
		
		Vector rowDatas_c = new Vector();
		Vector rowData_c = new Vector();
		rowData_c.add("姓名");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("年龄");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("位置");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("生日");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("身高");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("体重");
		rowDatas_c.add(rowData_c);
		rowData_c = new Vector();
		rowData_c.add("球龄");
		rowDatas_c.add(rowData_c);
		model_c.setDataVector(rowDatas_c, columnName_c);		
		model_c.setColumnCount(table_c.getColumnCount());
		model_c.setRowCount(rowDatas_c.size());
		table_c.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_c.setModel(model_c);
		table_c.updateUI();
		
		table_r = new JTable(model_r);
		
		table_r.setBounds(515, 57, 139, 190);
		panelOfBottom.add(table_r);
		
//button=====================================================================		
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				
				PlayerInfoPanel.scrollPane.setVisible(true);
				PlayerComparePanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA球员信息");
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
			}
		});
		panelOfBottom.add(button);
		
	}
	
//update left player info
	public void update1(String playerName1,String teamName1) {
		pname1 = playerName1;
		tname1 = teamName1;
		
		picture1 = ImageHandle.loadPlayer(playerName1);
		picture2 = ImageHandle.loadTeam(teamName1);
		
		picture1.setImage(picture1.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture2.setImage(picture2.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));
		
		labelOfPhoto1.setIcon(picture1);
		labelOfPhoto2.setIcon(picture2);
		
		PlayerVO vo = blservice.getPlayerAnalysis(playerName1);
		
		Vector rowDatas_l = new Vector();
		Vector rowData_l = new Vector();
		rowData_l.add(vo.getName());
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		rowData_l.add(vo.getAge());
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		rowData_l.add(vo.getPosition());
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
		rowData_l.add(sdf9.format(vo.getBirth().getTime()));
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		rowData_l.add(vo.getHeight());
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		rowData_l.add(vo.getWeight());
		rowDatas_l.add(rowData_l);
		
		rowData_l = new Vector();
		rowData_l.add(vo.getExp());
		rowDatas_l.add(rowData_l);
		
		model_l.setDataVector(rowDatas_l, columnName_l);		
		model_l.setColumnCount(table_l.getColumnCount());
		model_l.setRowCount(rowDatas_l.size());
		table_l.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_l.setModel(model_l);
		table_l.updateUI();
	}
	
	//update right player info
	public void update2(String playerName2,String teamName2) {
		pname2 = playerName2;
		tname2 = teamName2;
		
		picture3 = ImageHandle.loadPlayer(pname2);
		picture4 = ImageHandle.loadTeam(tname2);
		
		picture3.setImage(picture3.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture4.setImage(picture4.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));
		
		labelOfPhoto3.setIcon(picture3);
		labelOfPhoto4.setIcon(picture4);
		
	PlayerVO vo = blservice.getPlayerAnalysis(playerName2);
		
		Vector rowDatas_r = new Vector();
		Vector rowData_r = new Vector();
		rowData_r.add(vo.getName());
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		rowData_r.add(vo.getAge());
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		rowData_r.add(vo.getPosition());
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
		rowData_r.add(sdf9.format(vo.getBirth().getTime()));
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		rowData_r.add(vo.getHeight());
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		rowData_r.add(vo.getWeight());
		rowDatas_r.add(rowData_r);
		
		rowData_r = new Vector();
		rowData_r.add(vo.getExp());
		rowDatas_r.add(rowData_r);
		
		model_r.setDataVector(rowDatas_r, columnName_r);		
		model_r.setColumnCount(table_r.getColumnCount());
		model_r.setRowCount(rowDatas_r.size());
		table_r.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table_r.setModel(model_r);
		table_r.updateUI();
	}
	
	public class TeamItemListener implements ItemListener{

		char c = ' ';
		public TeamItemListener() {}
		
		public TeamItemListener(char flag) {
			c = flag;
		}
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				if(c == 'l') { 	
					comboBoxOfLPlayer.removeAllItems();
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						comboBoxOfLPlayer.addItem("选择球员");
					} else {
						TeamWithPlayersVO teamvo = blservice.getTeamAnalysis(HotRankingPanel.translate(teamSelected));
						ArrayList<PlayerVO> players = teamvo.getPlayers();
						comboBoxOfLPlayer.addItem("选择球员");
						for(int i=0;i<players.size();i++) {
							PlayerVO tempvo = players.get(i);
							comboBoxOfLPlayer.addItem(tempvo.getName());
						}
					}
				} else if (c == 'r') {
					comboBoxOfRPlayer.removeAllItems();
					String teamSelected =e.getItem().toString();
					if(teamSelected.equals("选择球队")) {
						comboBoxOfLPlayer.addItem("选择球员");
					} else {
						TeamWithPlayersVO teamvo = blservice.getTeamAnalysis(HotRankingPanel.translate(teamSelected));
						ArrayList<PlayerVO> players = teamvo.getPlayers();
						comboBoxOfRPlayer.addItem("选择球员");
						for(int i=0;i<players.size();i++) {
							PlayerVO tempvo = players.get(i);
							comboBoxOfRPlayer.addItem(tempvo.getName());
						}
					}
				}
			}
		}
		
	}
	
	public class PlayerItemListener implements ItemListener{

		char c = ' ';
		public PlayerItemListener() {}
		
		public PlayerItemListener(char flag) {
			c = flag;
		}
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				if(c == 'l') { 	
					String playerSelected =e.getItem().toString();
					if(playerSelected != "选择球员") {
						update1(e.getItem().toString(),
								HotRankingPanel.translate(comboBoxOfLTeam.getSelectedItem().toString()));
					}
				} else if (c == 'r') {
					String playerSelected =e.getItem().toString();
					if(playerSelected != "选择球员") {
						update2(e.getItem().toString(),
								HotRankingPanel.translate(comboBoxOfRTeam.getSelectedItem().toString()));
					}
				}
			}
		}
		
	}
}
