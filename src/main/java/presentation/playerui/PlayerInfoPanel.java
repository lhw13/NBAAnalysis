package presentation.playerui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;

public class PlayerInfoPanel extends JPanel {
	JPanel panelOfBottom = new JPanel();
	public static JScrollPane scrollPane;
	JLabel labelOfPhoto;
	JLabel labelOfAct;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	JButton button;
	ImageIcon picture;
	ImageIcon pictureOfAct;
	MouseListen listener = new MouseListen();
	private JTable table_3;
	private JScrollPane scrollPane_5;
	private JTable table_4;
	DefaultTableModel model = new DefaultTableModel(
			new Object[][] { { null, null, null, null, null, null, null, null,
					null, null, null, null }, }, new String[] { "姓名", "联盟",
					"分区", "球队", "号码", "位置"  });

	DefaultTableModel model_1 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null, null, null, null }, },
			new String[] { "参赛场数", "先发场数", "篮板数", "助攻数", "进攻数", "防守数", "抢断数",
					"盖帽数", "失误数", "犯规数" });

	DefaultTableModel model_2 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null, null, null, null }, },
			new String[] { "参赛场数", "先发场数", "篮板数", "助攻数", "进攻数", "防守数", "抢断数",
					"盖帽数", "失误数", "犯规数" });

	DefaultTableModel model_3 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null }, }, new String[] { "投篮命中率",
			"三分命中率", "罚球命中率", "真实命中率", "效率", "GmSc效率", "投篮效率", });

	DefaultTableModel model_4 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null }, }, new String[] { "篮板率",
			"进攻篮板率", "防守篮板率", "盖帽率", "失误率", "使用率", "助攻率", "抢断率" });
	DefaultTableModel model_5 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null, null, null }, }, new String[] {
			"得分", "在场时间", "命中", "出手", "三分命中", "三分出手", "罚球命中", "罚球出手", "两双" });

	DefaultTableModel model_6 = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null, null, null, null, null }, }, new String[] {
			"得分", "在场时间", "命中", "出手", "三分命中", "三分出手", "罚球命中", "罚球出手", "两双" });
	
	DefaultTableModel model_7 =new DefaultTableModel(new Object[][] { {null, 
		null, null, null, null,null},},new String[] {"身高", "体重", "生日", "年龄", "球龄",
			"毕业学校"	});
	
	DefaultTableModel model_8 = new DefaultTableModel();
	Vector columnName8 = new Vector();
	
	BLService blservice = BLController.getInstance();
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;

	public PlayerInfoPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);

		panelOfBottom.setPreferredSize(new Dimension(1300, 600));
		panelOfBottom.setLayout(null);
		scrollPane = new JScrollPane(panelOfBottom);

		labelOfPhoto = new JLabel("photo");
		labelOfPhoto.setBounds(726, 23, 230, 185);
		panelOfBottom.add(labelOfPhoto);

		labelOfAct = new JLabel("act");
		labelOfAct.setBounds(736, 218, 220, 350);
		panelOfBottom.add(labelOfAct);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 43, 686, 43);
		panelOfBottom.add(scrollPane_1);

		table = new JTable(model);

		scrollPane_1.setViewportView(table);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 257, 686, 77);
		panelOfBottom.add(tabbedPane);

		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_2, null);

		table_1 = new JTable(model_1);
		scrollPane_2.setViewportView(table_1);

		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_3, null);

		table_2 = new JTable(model_2);
		scrollPane_3.setViewportView(table_2);

		button = new JButton("返回");
		button.setBounds(5, 10, 93, 23);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					PlayerInfoPanel.scrollPane.setVisible(false);
					PlayerSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		panelOfBottom.add(button);

		JScrollPane scrollPane_4 = new JScrollPane();

		scrollPane_4.setBounds(5, 149, 686, 43);
		panelOfBottom.add(scrollPane_4);

		table_3 = new JTable(model_3);
		scrollPane_4.setViewportView(table_3);

		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(5, 202, 686, 43);
		panelOfBottom.add(scrollPane_5);

		table_4 = new JTable(model_4);

		scrollPane_5.setViewportView(table_4);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(5, 343, 686, 77);
		panelOfBottom.add(tabbedPane_1);

		JScrollPane scrollPane_6 = new JScrollPane();
		tabbedPane_1.addTab("场均", null, scrollPane_6, null);

		table_5 = new JTable(model_5);
		scrollPane_6.setViewportView(table_5);

		JScrollPane scrollPane_7 = new JScrollPane();
		tabbedPane_1.addTab("总计", null, scrollPane_7, null);

		table_6 = new JTable(model_6);

		scrollPane_7.setViewportView(table_6);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(5, 96, 686, 43);
		panelOfBottom.add(scrollPane_8);
		
		table_7 = new JTable();
		table_7.setModel(model_7);
		scrollPane_8.setViewportView(table_7);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(5, 456, 686, 123);
		panelOfBottom.add(scrollPane_9);
		
		String[] cname8 = new String[] {
				"日期", "比赛", "时间", "投篮", "三分", "罚球","前篮板","后篮板","篮板",
				"助攻","抢断","盖帽","失误","犯规"
			};
		for(int i=0;i<cname8.length;i++) {
			columnName8.add(cname8[i]);
		}
		
		
		table_8 = new JTable(model_8);
		
		table_8.setRowHeight(20);
		scrollPane_9.setViewportView(table_8);
		
		JLabel label = new JLabel("最近五场统计");
		label.setBounds(5, 425, 110, 29);
		panelOfBottom.add(label);
		scrollPane.setBounds(0, 100, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

	}
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	public void update(String name) {
		picture = ImageHandle.loadPlayer(name);
		pictureOfAct = ImageHandle.loadPlayerAct(name);
		labelOfPhoto.setIcon(picture);
		pictureOfAct.setImage(pictureOfAct.getImage().getScaledInstance(
				labelOfAct.getWidth(), labelOfAct.getHeight(),
				Image.SCALE_DEFAULT));
		labelOfAct.setIcon(pictureOfAct);
		PlayerVO vo = blservice.getPlayerAnalysis(name);
		if (vo == null)
			return;
		Vector rowData = new Vector();
		Vector rowDatas = new Vector();
		rowData.add(vo.getName());
		rowData.add(vo.getDivision());
		rowData.add(vo.getZone());
		rowData.add(vo.getTeamFullName());
		rowData.add(vo.getNumber());
		rowData.add(vo.getPosition());
		rowData.add(vo.getHeight().toString());
		rowData.add(vo.getWeight());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(vo.getBirth().getTime());
		rowData.add(dateStr);
		rowData.add(vo.getAge());
		if (vo.getExp() != -1)
			rowData.add(vo.getExp());
		else
			rowData.add("未知");
		rowData.add(vo.getSchool());
		rowDatas.add(rowData);

		for (int i = 0; i < rowDatas.size(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				model.setValueAt(((Vector) rowDatas.get(i)).get(j), i, j);
			}
		}
		model.setColumnCount(table.getColumnCount());
		model.setRowCount(rowDatas.size());
		table.setModel(model);
		table.updateUI();

		Vector rowData_1 = new Vector();
		Vector rowDatas_1 = new Vector();
		double num = vo.getAppearance();
		rowData_1.add(vo.getAppearance());
		rowData_1.add(vo.getStarting());
		rowData_1.add(handleDecimal(vo.getTotalRebound() / num));
		rowData_1.add(handleDecimal(vo.getAssist() / num));
		rowData_1.add(handleDecimal(vo.getOffensiveRebound() / num));
		rowData_1.add(handleDecimal(vo.getDefensiveRebound() / num));
		rowData_1.add(handleDecimal(vo.getSteal() / num));
		rowData_1.add(handleDecimal(vo.getBlock() / num));
		rowData_1.add(handleDecimal(vo.getMiss() / num));
		rowData_1.add(handleDecimal(vo.getFoul() / num));
		rowDatas_1.add(rowData_1);

		for (int i = 0; i < rowDatas_1.size(); i++) {
			for (int j = 0; j < table_1.getColumnCount(); j++) {
				model_1.setValueAt(((Vector) rowDatas_1.get(i)).get(j), i, j);
			}
		}
		model_1.setColumnCount(table_1.getColumnCount());
		model_1.setRowCount(rowDatas_1.size());
		table_1.setModel(model_1);
		table_1.updateUI();

		Vector rowData_2 = new Vector();
		Vector rowDatas_2 = new Vector();
		rowData_2.add(vo.getAppearance());
		rowData_2.add(vo.getStarting());
		rowData_2.add(vo.getTotalRebound());
		rowData_2.add(vo.getAssist());
		rowData_2.add(vo.getOffensiveRebound());
		rowData_2.add(vo.getDefensiveRebound());
		rowData_2.add(vo.getSteal());
		rowData_2.add(vo.getBlock());
		rowData_2.add(vo.getMiss());
		rowData_2.add(vo.getFoul());
		rowDatas_2.add(rowData_2);

		for (int i = 0; i < rowDatas_2.size(); i++) {
			for (int j = 0; j < table_2.getColumnCount(); j++) {
				model_2.setValueAt(((Vector) rowDatas_2.get(i)).get(j), i, j);
			}
		}
		model_2.setColumnCount(table_2.getColumnCount());
		model_2.setRowCount(rowDatas_2.size());
		table_2.setModel(model_2);
		table_2.updateUI();

		Vector rowData_3 = new Vector();
		Vector rowDatas_3 = new Vector();
		rowData_3.add(handleDecimal(vo.getHitRate()));
		rowData_3.add(handleDecimal(vo.getThirdHitRate()));
		rowData_3.add(handleDecimal(vo.getFreeHitRate()));
		rowData_3.add(handleDecimal(vo.getRealHitRate()));
		rowData_3.add(handleDecimal(vo.getEfficiency()));
		rowData_3.add(handleDecimal(vo.getGmScEfficiency()));
		rowData_3.add(handleDecimal(vo.getShotEfficiency()));
		rowDatas_3.add(rowData_3);

		for (int i = 0; i < rowDatas_3.size(); i++) {
			for (int j = 0; j < table_3.getColumnCount(); j++) {
				model_3.setValueAt(((Vector) rowDatas_3.get(i)).get(j), i, j);
			}
		}
		model_3.setColumnCount(table_3.getColumnCount());
		model_3.setRowCount(rowDatas_3.size());
		table_3.setModel(model_3);
		table_3.updateUI();

		Vector rowData_4 = new Vector();
		Vector rowDatas_4 = new Vector();
		rowData_4.add(handleDecimal(vo.getReboundRate()));
		rowData_4.add(handleDecimal(vo.getOffensiveReboundRate()));
		rowData_4.add(handleDecimal(vo.getDefensiveReboundRate()));
		rowData_4.add(handleDecimal(vo.getBlockRate()));
		rowData_4.add(handleDecimal(vo.getMissRate()));
		rowData_4.add(handleDecimal(vo.getUseRate()));
		rowData_4.add(handleDecimal(vo.getAssistRate()));
		rowData_4.add(handleDecimal(vo.getStealRate()));
		rowDatas_4.add(rowData_4);

		for (int i = 0; i < rowDatas_4.size(); i++) {
			for (int j = 0; j < table_4.getColumnCount(); j++) {
				model_4.setValueAt(((Vector) rowDatas_4.get(i)).get(j), i, j);
			}
		}
		model_4.setColumnCount(table_4.getColumnCount());
		model_4.setRowCount(rowDatas_4.size());
		table_4.setModel(model_4);
		table_4.updateUI();

		Vector rowData_5 = new Vector();
		Vector rowDatas_5 = new Vector();
		rowData_5.add(handleDecimal(vo.getScore() / num));
		rowData_5.add(handleDecimal(vo.getPlayTime() / num));
		rowData_5.add(handleDecimal(vo.getHit() / num));
		rowData_5.add(handleDecimal(vo.getShot() / num));
		rowData_5.add(handleDecimal(vo.getThirdHit() / num));
		rowData_5.add(handleDecimal(vo.getThirdshot() / num));
		rowData_5.add(handleDecimal(vo.getFreeHit() / num));
		rowData_5.add(handleDecimal(vo.getFreeshot() / num));
		rowData_5.add(handleDecimal(vo.getTowPairs() / num));
		rowDatas_5.add(rowData_5);
		model_5.setColumnCount(table_5.getColumnCount());
		model_5.setRowCount(rowDatas_5.size());
		for (int i = 0; i < rowDatas_5.size(); i++) {
			for (int j = 0; j < table_5.getColumnCount(); j++) {
				model_5.setValueAt(((Vector) rowDatas_5.get(i)).get(j), i, j);
			}
		}
		
		table_5.setModel(model_5);
		table_5.updateUI();

		Vector rowData_6 = new Vector();
		Vector rowDatas_6 = new Vector();
		rowData_6.add(vo.getScore());
		rowData_6.add(vo.getPlayTime());
		rowData_6.add(vo.getHit());
		rowData_6.add(vo.getShot());
		rowData_6.add(vo.getThirdHit());
		rowData_6.add(vo.getThirdshot());
		rowData_6.add(vo.getFreeHit());
		rowData_6.add(vo.getFreeshot());
		rowData_6.add(vo.getTowPairs());
		rowDatas_6.add(rowData_6);

		for (int i = 0; i < rowDatas_6.size(); i++) {
			for (int j = 0; j < table_6.getColumnCount(); j++) {
				model_6.setValueAt(((Vector) rowDatas_6.get(i)).get(j), i, j);
			}
		}
		model_6.setColumnCount(table_6.getColumnCount());
		model_6.setRowCount(rowDatas_6.size());
		table_6.setModel(model_6);
		table_6.updateUI();
		
		Vector rowData7 = new Vector();
		Vector rowDatas7 = new Vector();
		
		rowData7.add(vo.getHeight().toString());
		rowData7.add(vo.getWeight());
		SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr7 = sdf.format(vo.getBirth().getTime());
		rowData7.add(dateStr7);
		rowData7.add(vo.getAge());
		if (vo.getExp() != -1)
			rowData7.add(vo.getExp());
		else
			rowData7.add("未知");
		rowData7.add(vo.getSchool());
		rowDatas7.add(rowData7);

		for (int i = 0; i < rowDatas7.size(); i++) {
			for (int j = 0; j < table_7.getColumnCount(); j++) {
				model_7.setValueAt(((Vector) rowDatas7.get(i)).get(j), i, j);
			}
		}
		model_7.setColumnCount(table_7.getColumnCount());
		model_7.setRowCount(rowDatas7.size());
		table_7.setModel(model_7);
		table_7.updateUI();
		
		
		Vector rowDatas8 = new Vector();
		ArrayList<MatchPO> matches = vo.getMatches();
		for(int i=0;i<matches.size()&&i<5;i++) {
			Vector rowData8 = new Vector();
			MatchPO matchTemp = matches.get(i);
			TeamInMatchesPO team = null;
			if(matchTemp.getTeam1().getAbbreviation().equals
					(vo.getTeamAbbreviation())){
				team = matchTemp.getTeam1();
			} else {
				team = matchTemp.getTeam2();
			}
			ArrayList<PlayerInMatchesPO> playersInMatch = team.getPlayers();
			PlayerInMatchesPO playerTemp = null; 
			for(int j=0;j<playersInMatch.size();j++) {
				playerTemp = playersInMatch.get(j);
				if(playerTemp.getName().equals(vo.getName())) break; 
			}
			
			SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr8 = sdf.format(matchTemp.getDate().getTime());
			rowData8.add(dateStr8);//日期
			rowData8.add(matchTemp.getFinalScore().toString()+" "+					
					matchTemp.getTeam2().getAbbreviation());//得分
			rowData8.add(playerTemp.getPlayTime());
			rowData8.add(playerTemp.getHit()+"-"+playerTemp.getShot());
			rowData8.add(playerTemp.getThirdHit()+"-"+playerTemp.getThirdshot());
			rowData8.add(playerTemp.getFreeHit()+"-"+playerTemp.getFreeshot());
			rowData8.add(playerTemp.getOffensiveRebound());
			rowData8.add(playerTemp.getDefensiveRebound());
			rowData8.add(playerTemp.getTotalRebound());
			rowData8.add(playerTemp.getAssist());
			rowData8.add(playerTemp.getSteal());
			rowData8.add(playerTemp.getBlock());
			rowData8.add(playerTemp.getMiss());
			rowData8.add(playerTemp.getFoul());
			rowData8.add(playerTemp.getScore());
			
			rowDatas8.add(rowData8);
		}
		
		

//		for (int i = 0; i < rowDatas8.size(); i++) {
//			for (int j = 0; j < table_8.getColumnCount(); j++) {
//				model_8.setValueAt(((Vector) rowDatas8.get(i)).get(j), i, j);
//			}
//		}
		
		model_8.setDataVector(rowDatas8, columnName8);		
		model_8.setColumnCount(table_8.getColumnCount());
		model_8.setRowCount(rowDatas8.size());
		table_8.setModel(model_8);
		int[] width={50,55,5,3,3,3,3,3,3,3,3,3,3,3};
		table_8.setColumnModel(getColumn(table_8, width));
		table_8.updateUI();
	}

	// 保留小数点
	public String handleDecimal(double f) {
		return String.format("%.2f", f);
	}

	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			Object temp = table.getValueAt(r, c);
			String name = null;
			if (temp != null)
				name = temp.toString();

			try {
				if (name != null) {
					PlayerSelectionPanel.scrollPane.setVisible(false);
					PlayerInfoPanel.scrollPane.setVisible(true);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
