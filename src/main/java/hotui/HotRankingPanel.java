package hotui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import blservice.BLService;
import presentation.mainui.MainFrame;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamVO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class HotRankingPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JScrollPane scrollPane_3;
	private JTable table_3;
	private JScrollPane scrollPane_4;
	Vector columnName1;
	Vector columnName2;
	Vector columnName3;
	Vector columnName4;
	DefaultTableModel model_1 = new DefaultTableModel();
	DefaultTableModel model_2 = new DefaultTableModel();
	DefaultTableModel model_3 = new DefaultTableModel();
	DefaultTableModel model_4 = new DefaultTableModel();
	
	BLService blservice = BLController.getInstance();
	private JTable table_4;
	public HotRankingPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		scrollPane = new JScrollPane(panelOfBottom);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 108, 452, 123);
		panelOfBottom.add(scrollPane_1);
		JButton button = new JButton("返回");
		button.setBounds(5, 10, 93, 23);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					HotRankingPanel.scrollPane.setVisible(false);
					MainFrame.panel.setVisible(true);
					MainFrame.frame.setTitle("NBA");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		panelOfBottom.add(button);
		table_1 = new JTable(model_1);
		table_1.setRowHeight(20);
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(500, 108, 452, 123);
		panelOfBottom.add(scrollPane_2);
		
		table_2 = new JTable(model_2);
		table_2.setRowHeight(20);
		scrollPane_2.setViewportView(table_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(38, 335, 452, 123);
		panelOfBottom.add(scrollPane_3);
		
		table_3 = new JTable(model_3);
		table_3.setRowHeight(20);
		scrollPane_3.setViewportView(table_3);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(500, 335, 452, 123);
		panelOfBottom.add(scrollPane_4);
		
		table_4 = new JTable(model_4);
		table_4.setRowHeight(20);
		scrollPane_4.setViewportView(table_4);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"个人技术",
				"得分", "篮板", "助攻", "盖帽", "抢断"}));
		comboBox_1.setBounds(389, 58, 101, 31);
		panelOfBottom.add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"个人技术",
				"场均得分", "场均篮板", "场均助攻"}));
		comboBox_2.setBounds(851, 58, 101, 31);
		panelOfBottom.add(comboBox_2);
		
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"技术项",
				"场均得分", "场均篮板", "场均助攻", "场均盖帽", "场均抢断", "三分命中率",
				"投篮命中率", "罚球命中率"}));
		comboBox_3.setBounds(389, 287, 101, 31);
		panelOfBottom.add(comboBox_3);
		
		final JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"个人技术",
				"场均得分", "场均篮板", "场均助攻", "场均盖帽", "场均抢断", "三分命中率",
				"投篮命中率", "罚球命中率"}));
		comboBox_4.setBounds(851, 287, 101, 31);
		panelOfBottom.add(comboBox_4);
		
		JLabel label = new JLabel("当天热点球员");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(38, 58, 101, 31);
		panelOfBottom.add(label);
		
		JLabel label_1 = new JLabel("进步最快球员");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setBounds(500, 58, 101, 31);
		panelOfBottom.add(label_1);
		
		JLabel lblNewLabel = new JLabel("赛季热点球队");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 286, 101, 31);
		panelOfBottom.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("赛季热点球员");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(500, 286, 101, 31);
		panelOfBottom.add(lblNewLabel_1);
		
		JButton button_1 = new JButton("最新");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update1(blservice.getDailyHotPlayerVO("point", 5), "point");
				update2(blservice.getBestPromotion("point", 5), "point");
				updateTeam(blservice.getHotTeamVO("point", 5), "point");
				update4(blservice.getHotPlayerVO("point", 5), "point");					
			}
		});
		button_1.setBounds(123, 10, 93, 23);
		panelOfBottom.add(button_1);
		
		scrollPane.setBounds(0, 0, 1000, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		String[] names1 = {"球员","球队","位置","得分"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}		
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				ArrayList<PlayerVO> players;
				switch(index){				
				case 1: 
					columnName1.setElementAt("得分", 3);
					players = blservice.getDailyHotPlayerVO("point", 5);
					update1(players,"point");
					break;
				case 2: 
					columnName1.setElementAt("篮板", 3);
					players = blservice.getDailyHotPlayerVO("rebound", 5);
					update1(players,"rebound");
					break;
				case 3: 
					columnName1.setElementAt("助攻", 3);
					players = blservice.getDailyHotPlayerVO("assist", 5);
					update1(players,"assist");
					break;
				case 4: 
					columnName1.setElementAt("盖帽", 3);
					players = blservice.getDailyHotPlayerVO("blockShot", 5);
					update1(players,"blockShot");
					break;
				case 5: 
					columnName1.setElementAt("抢断", 3);
					players = blservice.getDailyHotPlayerVO("steal", 5);
					update1(players,"steal");
					break;
				}
			}
		});
		
		String[] names2 = {"球员","球队","近五场提升率","场均得分"};
		columnName2 = new Vector();
		for(int i=0;i<names2.length;i++) {
			columnName2.add(names2[i]);
		}		
		comboBox_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_2.getSelectedIndex();
				ArrayList<PlayerVO> players;
				switch(index){				
				case 1: 
					columnName2.setElementAt("场均得分", 3);
					players = blservice.getBestPromotion("point", 5);
					update2(players,"point");
					break;
				case 2: 
					columnName2.setElementAt("场均篮板", 3);
					players = blservice.getBestPromotion("rebound", 5);
					update2(players,"rebound");
					break;
				case 3: 
					columnName2.setElementAt("场均助攻", 3);
					players = blservice.getBestPromotion("assist", 5);
					update2(players,"assist");
					break;
				
				}
			}
		});
		
		String[] names3 = {"队名","联盟","场均得分"};
		columnName3 = new Vector();
		for(int i=0;i<names3.length;i++) {
			columnName3.add(names3[i]);
		}
		comboBox_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_3.getSelectedIndex();
				ArrayList<TeamVO> teams;
				switch(index){				
				case 1: 
					columnName3.setElementAt("场均得分", 2);
					teams = blservice.getHotTeamVO("point",5);
					updateTeam(teams,"point");
					break;
				case 2: 
					columnName3.setElementAt("场均篮板", 2);
					teams = blservice.getHotTeamVO("rebound",5);
					updateTeam(teams,"rebound");
					break;
				case 3: 
					columnName3.setElementAt("场均助攻", 2);
					teams = blservice.getHotTeamVO("assist",5);
					updateTeam(teams,"assist");
					break;
				case 4: 
					columnName3.setElementAt("场均盖帽", 2);
					teams = blservice.getHotTeamVO("blockShot",5);
					updateTeam(teams,"blockShot");
					break;
				case 5: 
					columnName3.setElementAt("场均抢断", 2);
					teams = blservice.getHotTeamVO("steal",5);					
					updateTeam(teams,"steal");
					break;
				case 6: 
					columnName3.setElementAt("三分命中率", 2);
					teams = blservice.getHotTeamVO("three",5);
					updateTeam(teams,"three");
					break;
				case 7: 
					teams = blservice.getHotTeamVO("shot",5);
					columnName3.setElementAt("投篮命中率", 2);										
					updateTeam(teams,"shot");
					break;
				case 8: 
					teams = blservice.getHotTeamVO("penalty",5);
					columnName3.setElementAt("罚球命中率", 2);				
					updateTeam(teams,"penalty");
					break;
				}
			}
		});
		
		String[] names4 = {"球员","球队","位置","场均得分"};
		columnName4 = new Vector();
		for(int i=0;i<names4.length;i++) {
			columnName4.add(names4[i]);
		}
		comboBox_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_4.getSelectedIndex();
				ArrayList<PlayerVO> players;
				switch(index){				
				case 1:									
					columnName4.setElementAt("场均得分", 3);
					players = blservice.getHotPlayerVO("point", 5);	
					update4(players,"point");
					break;
				case 2: 
					columnName4.setElementAt("场均篮板", 3);
					players = blservice.getHotPlayerVO("rebound", 5);	
					update4(players,"rebound");
					break;
				case 3: 
					columnName4.setElementAt("场均助攻", 3);
					players = blservice.getHotPlayerVO("assist", 5);	
					update4(players,"assist");
					break;
				case 4: 
					columnName4.setElementAt("场均盖帽", 3);
					players = blservice.getHotPlayerVO("blockShot", 5);	
					update4(players,"blockShot");
					break;
				case 5: 
					columnName4.setElementAt("场均抢断", 3);
					players = blservice.getHotPlayerVO("steal", 5);	
					update4(players,"steal");
					break;
				case 6: 
					columnName4.setElementAt("三分命中率", 3);
					players = blservice.getHotPlayerVO("three", 5);	
					update4(players,"three");
					break;
				case 7: 
					columnName4.setElementAt("投篮命中率", 3);	
					players = blservice.getHotPlayerVO("shot", 5);	
					update4(players,"shot");
					break;
				case 8: 
					columnName4.setElementAt("罚球命中率", 3);	
					players = blservice.getHotPlayerVO("penalty", 5);	
					update4(players,"penalty");
					break;
				}
			}
		});
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
		return String.format("%.2f", f);
		return result.toString();
	}
	
	public void update1(ArrayList<PlayerVO> players, String con) {
		Vector rowDatas1 = new Vector();
		switch(con) {
		case "point":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData1 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData1.add(playerTemp.getName());
				rowData1.add(playerTemp.getTeamFullName());	
				rowData1.add(playerTemp.getPosition());					
				rowData1.add(playerTemp.getScore());			
				rowDatas1.add(rowData1);
			}
			break;
		case "rebound":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData1 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData1.add(playerTemp.getName());
				rowData1.add(playerTemp.getTeamFullName());			
				rowData1.add(playerTemp.getPosition());					
				rowData1.add(playerTemp.getTotalRebound());					
				rowDatas1.add(rowData1);
			}
			break;
		case "assist":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData1 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData1.add(playerTemp.getName());
				rowData1.add(playerTemp.getTeamFullName());	
				rowData1.add(playerTemp.getPosition());					
				rowData1.add(playerTemp.getAssist());									
				rowDatas1.add(rowData1);
			}
			break;
		case "blockShot":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData1 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData1.add(playerTemp.getName());
				rowData1.add(playerTemp.getTeamFullName());	
				rowData1.add(playerTemp.getPosition());					
				rowData1.add(playerTemp.getBlock());									
				rowDatas1.add(rowData1);
			}
			break;
		case "steal":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData1 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData1.add(playerTemp.getName());
				rowData1.add(playerTemp.getTeamFullName());	
				rowData1.add(playerTemp.getPosition());					
				rowData1.add(playerTemp.getSteal());									
				rowDatas1.add(rowData1);
			}
			break;
		}
			model_1.setDataVector(rowDatas1, columnName1);		
			model_1.setColumnCount(table_1.getColumnCount());
			model_1.setRowCount(rowDatas1.size());
			table_1.setModel(model_1);
//			int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//			table_1.setColumnModel(getColumn(table_1, width));
			table_1.updateUI();
	}

	public void update2(ArrayList<PlayerVO> players, String con) {
		Vector rowDatas2 = new Vector();
		switch(con) {
		case "point":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData2 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData2.add(playerTemp.getName());
				rowData2.add(playerTemp.getTeamFullName());			
				rowData2.add(handleDecimal(playerTemp.getScorePromotion()));
				rowData2.add(handleDecimal((double)playerTemp.getScore()/(double)playerTemp.getAppearance()));			
				rowDatas2.add(rowData2);
			}
			break;
		case "rebound":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData2 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData2.add(playerTemp.getName());
				rowData2.add(playerTemp.getTeamFullName());			
				rowData2.add(handleDecimal(playerTemp.getReboundPromotion()));
				rowData2.add(handleDecimal((double)playerTemp.getTotalRebound()/(double)playerTemp.getAppearance()));					
				rowDatas2.add(rowData2);
			}
			break;
		case "assist":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData2 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData2.add(playerTemp.getName());
				rowData2.add(playerTemp.getTeamFullName());			
				rowData2.add(handleDecimal(playerTemp.getAssistPromotion()));
				rowData2.add(handleDecimal((double)playerTemp.getAssist()/(double)playerTemp.getAppearance()));									
				rowDatas2.add(rowData2);
			}
			break;
		}
			model_2.setDataVector(rowDatas2, columnName2);		
			model_2.setColumnCount(table_2.getColumnCount());
			model_2.setRowCount(rowDatas2.size());
			table_2.setModel(model_2);
//			int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//			table_2.setColumnModel(getColumn(table_2, width));
			table_2.updateUI();
			
	}
	public void updateTeam(ArrayList<TeamVO> teams, String con) {
		Vector rowDatas3 = new Vector();
		
		switch(con) {
		case "point":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getScore()/(double)teamTemp.getAppearance()));
				rowDatas3.add(rowData3);
			}
			break;
		case "rebound":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getTotalRebound()/(double)teamTemp.getAppearance()));
				rowDatas3.add(rowData3);
			}
			break;
		case "assist":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getAssist()/(double)teamTemp.getAppearance()));
				rowDatas3.add(rowData3);
			}
			break;
		case "blockShot":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getBlock()/(double)teamTemp.getAppearance()));
				rowDatas3.add(rowData3);
			}
			break;
		case "steal":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getSteal()/(double)teamTemp.getAppearance()));
				rowDatas3.add(rowData3);
			}
			break;
		case "three":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal(teamTemp.getThirdHitRate()));//三分命中率
				rowDatas3.add(rowData3);
			}
			break;
		case "shot":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal(teamTemp.getHitRate()));//投篮命中率
				rowDatas3.add(rowData3);
			}
			break;
		case "penalty":
			for(int i=0;i<teams.size()&&i<5;i++) {
				Vector rowData3 = new Vector();
				TeamVO teamTemp = teams.get(i);			
				rowData3.add(teamTemp.getFullName());
				rowData3.add(teamTemp.getDivision()+"-"+teamTemp.getZone());			
				rowData3.add(handleDecimal((double)teamTemp.getFreeHitRate()));
				rowDatas3.add(rowData3);
			}
			break;
		}
		
		model_3.setDataVector(rowDatas3, columnName3);		
		model_3.setColumnCount(table_3.getColumnCount());
		model_3.setRowCount(rowDatas3.size());
		table_3.setModel(model_3);
//		int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//		table_3.setColumnModel(getColumn(table_3, width));
		table_3.updateUI();
	}
	
	public void update4(ArrayList<PlayerVO> players, String con) {
		Vector rowDatas4 = new Vector();
		switch(con) {
		case "point":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal((double)playerTemp.getScore()/(double)playerTemp.getAppearance()));			
				rowDatas4.add(rowData4);
			}
			break;
		case "rebound":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal((double)playerTemp.getTotalRebound()/(double)playerTemp.getAppearance()));					
				rowDatas4.add(rowData4);
			}
			break;
		case "assist":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal((double)playerTemp.getAssist()/(double)playerTemp.getAppearance()));									
				rowDatas4.add(rowData4);
			}
			break;
		case "blockShot":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal((double)playerTemp.getBlock()/(double)playerTemp.getAppearance()));									
				rowDatas4.add(rowData4);
			}
			break;
		case "steal":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal((double)playerTemp.getSteal()/(double)playerTemp.getAppearance()));									
				rowDatas4.add(rowData4);
			}
			break;
		case "three":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal(playerTemp.getThirdHitRate()));									
				rowDatas4.add(rowData4);
			}
			break;
		case "shot":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal(playerTemp.getHitRate()));									
				rowDatas4.add(rowData4);
			}
			break;
		case "penalty":
			for(int i=0;i<players.size()&&i<5;i++) {
				Vector rowData4 = new Vector();
				PlayerVO playerTemp = players.get(i);			
				rowData4.add(playerTemp.getName());
				rowData4.add(playerTemp.getTeamFullName());			
				rowData4.add(playerTemp.getPosition());
				rowData4.add(handleDecimal(playerTemp.getFreeHitRate()));		
				rowDatas4.add(rowData4);
			}
			break;
		}
			
		
		
		
			model_4.setDataVector(rowDatas4, columnName4);		
			model_4.setColumnCount(table_4.getColumnCount());
			model_4.setRowCount(rowDatas4.size());
			table_4.setModel(model_4);
//			int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
//			table_4.setColumnModel(getColumn(table_4, width));
			table_4.updateUI();
	}
}
