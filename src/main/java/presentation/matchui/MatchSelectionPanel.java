package presentation.matchui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.mainui.MainFrame;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.playerui.PlayerInfoPanel.MouseListen;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.BLController;
import server.po.MatchPO;

import javax.swing.JButton;

public class MatchSelectionPanel extends JPanel {
	public static JScrollPane scrollPane;
	public static JTable table;
	
	private JPanel panelOfBottom = new JPanel();
	private JComboBox<String> comboBox;
	private JComboBox comboBox_1;
	private JScrollPane scrollPane_1;
	
	MouseListen listener = new MouseListen();
	
	ArrayList<MatchPO> mpoList;
	
	BLController compute;
	
	public MatchSelectionPanel(DefaultTableModel model,ArrayList<MatchPO> selectedMatchs) {
		this.setBounds(0, 100, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(200, 50, 100, 30);
		comboBox.addItem("选择赛季");
		comboBox.addItem("12-13");
		comboBox.addItem("13-14");
		comboBox.addItem("14-15");
		comboBox.setSelectedItem("选择赛季");
		panelOfBottom.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 1:
					MainFrame.season="12-13";
					MainFrame.searchTheMatch();
					break;
				case 2:
					MainFrame.season="13-14";
					MainFrame.searchTheMatch();
					break;
				case 3:
					MainFrame.season="14-15";
					MainFrame.searchTheMatch();
					break;
				}
			}
			
		});
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItem("选择月份");
		comboBox_1.addItem(1);
		comboBox_1.addItem(2);
		comboBox_1.addItem(3);
		comboBox_1.addItem(4);
		comboBox_1.addItem(10);
		comboBox_1.addItem(11);
		comboBox_1.addItem(12);
		comboBox_1.setSelectedItem("选择月份");
		comboBox_1.setBounds(350, 50, 100, 30);
		panelOfBottom.add(comboBox_1);
		
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				switch(index){
				case 1:
					MainFrame.date=0;
					MainFrame.searchTheMatch();
					break;
				case 2:
					MainFrame.date=1;
					MainFrame.searchTheMatch();
					break;
				case 3:
					MainFrame.date=2;
					MainFrame.searchTheMatch();
					break;
				case 4:
					MainFrame.date=3;
					MainFrame.searchTheMatch();
					break;
				case 5:
					MainFrame.date=9;
					MainFrame.searchTheMatch();
					break;
				case 6:
					MainFrame.date=10;
					MainFrame.searchTheMatch();
					break;
				case 7:
					MainFrame.date=11;
					MainFrame.searchTheMatch();
					break;
				}
			}
			
		});
		
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(listener);
		
		mpoList=selectedMatchs;
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 100, 700, 400);
		panelOfBottom.add(scrollPane_1);
		
		scrollPane_1.setViewportView(table);
		
		JButton refreshButton = new JButton("最新");
		refreshButton.setBounds(500, 50, 100, 30);
		panelOfBottom.add(refreshButton);
		
		refreshButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.searchTheMatch();
			}
			
		});
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 50, 100, 30);
		panelOfBottom.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.frame.getContentPane().remove(MatchSelectionPanel.scrollPane);
				MatchSelectionPanel.scrollPane=null;
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
			
		});
	}
	
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				if(c==8){
					setMatchInfo(r);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void setMatchInfo(int rowNum){
		MatchPO mpo = mpoList.get(rowNum);
		String[] cname1 = new String[] {
				"球员", "位置", "在场时间", "投篮命中", "出手", "三分命中", "出手", "罚球命中", "出手",
				"前篮板","后篮板","篮板","助攻","抢断","盖帽","失误","犯规", "得分" };
		
		DefaultTableModel model1 = new DefaultTableModel();
		Vector<String> columnName1 = new Vector<String>();
		Vector rowDatas1 = new Vector();
		for(int i=0;i<cname1.length;i++) {
			columnName1.add(cname1[i]);
		}
		for(int i=0;i<mpo.getTeam1().getPlayers().size();i++){
			Vector rowData1 = new Vector();
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getName());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getPosition()+"");
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getPlayTime());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getShot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdshot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getFreeHit());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getThirdshot());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getOffensiveRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getDefensiveRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getTotalRebound());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getAssist());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getSteal());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getBlock());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getMiss());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getFoul());
			rowData1.add(mpo.getTeam1().getPlayers().get(i).getScore());
			rowDatas1.add(rowData1);
		}
		model1.setDataVector(rowDatas1, columnName1);		
		model1.setColumnCount(columnName1.size());
		model1.setRowCount(rowDatas1.size());
		
		DefaultTableModel model2 = new DefaultTableModel();
		Vector rowDatas2 = new Vector();
		for(int i=0;i<mpo.getTeam2().getPlayers().size();i++){
			Vector rowData2 = new Vector();
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getName());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getPosition()+"");
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getPlayTime());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getShot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdshot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getFreeHit());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getThirdshot());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getOffensiveRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getDefensiveRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getTotalRebound());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getAssist());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getSteal());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getBlock());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getMiss());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getFoul());
			rowData2.add(mpo.getTeam2().getPlayers().get(i).getScore());
			rowDatas2.add(rowData2);
		}
		model2.setDataVector(rowDatas2, columnName1);		
		model2.setColumnCount(mpo.getTeam2().getPlayers().size());
		model2.setRowCount(rowDatas2.size());
		
		String[] cname2 = new String[] {
				"赛季", "日期", "球队", "总比分", "第一节", "第二节", "第三节", "第四节"};
		DefaultTableModel model3 = new DefaultTableModel();
		Vector<String> columnName3 = new Vector<String>();
		for(int i=0;i<cname2.length;i++) {
			columnName3.add(cname2[i]);
		}
		Vector rowDatas3 = new Vector();
		Vector rowData3 = new Vector();
		rowData3.add(mpo.getSeason());
		rowData3.add((mpo.getDate().get(Calendar.MONTH)+1)+"-"+
	                 mpo.getDate().get(Calendar.DAY_OF_MONTH));
		rowData3.add(mpo.getTeam1().getAbbreviation()+"-"+mpo.getTeam2().getAbbreviation());
		rowData3.add(mpo.getFinalScore().getTeam1()+"-"+mpo.getFinalScore().getTeam2());
		rowData3.add(mpo.getScores().get(0).getTeam1()+"-"+mpo.getScores().get(0).getTeam2());
		rowData3.add(mpo.getScores().get(1).getTeam1()+"-"+mpo.getScores().get(1).getTeam2());
		rowData3.add(mpo.getScores().get(2).getTeam1()+"-"+mpo.getScores().get(2).getTeam2());
		rowData3.add(mpo.getScores().get(3).getTeam1()+"-"+mpo.getScores().get(3).getTeam2());
		rowDatas3.add(rowData3);
		model3.setDataVector(rowDatas3, columnName3);		
		model3.setColumnCount(columnName3.size());
		model3.setRowCount(rowDatas3.size());
		
		int width = 200;
		int height = 150;
		compute = BLController.getInstance();
		ImageIcon[] ii=new ImageIcon[2];
		ii[0] = compute.getTeamPic(mpo.getTeam1().getAbbreviation());
		ii[0].setImage(ii[0].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		ii[1] = compute.getTeamPic(mpo.getTeam2().getAbbreviation());
		ii[1].setImage(ii[1].getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		
		if(MatchSelectionPanel.scrollPane!=null){
			MatchSelectionPanel.scrollPane.setVisible(false);
			MatchDetailInfoPanel mdip = new MatchDetailInfoPanel(ii,model1,model2,model3);
			MainFrame.frame.getContentPane().add(mdip.scrollPane);
			MainFrame.frame.repaint();//刷新重画 
			MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
		}
		
		
		
	}
}
