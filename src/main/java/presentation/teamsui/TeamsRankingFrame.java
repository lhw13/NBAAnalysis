package presentation.teamsui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

import presentation.mainui.MainFrame;
import server.businesslogic.BLController;
import vo.TeamVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * 所有球队排名面板
 */
public class TeamsRankingFrame {

	public static JTable table;

	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton refreshButton;
	private JComboBox<String> comboBox;

	Vector columnName1;
	DefaultTableModel model_1=new DefaultTableModel() {
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	
	private BLController compute;
	/**
	 * Create the application.
	 */
	public TeamsRankingFrame() {// 构造函数
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(900, 500));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(model_1);
		table.setRowSorter(new TableRowSorter<TableModel>(model_1));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 50, 800, 500);
		tabbedPane.addTab("球队排名", null, scrollPane_1, null);

		panel.add(tabbedPane);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(200, 10, 120, 30);
		panel.add(btnNewButton);
		
		refreshButton = new JButton("最新");
		refreshButton.setBounds(350, 10, 120, 30);
		panel.add(refreshButton);
		
		String[] names1 = new String[]{"球队", "场数", "投篮命中数(场均)", "投篮命中数(总计)"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("投篮命中数");
		comboBox.addItem("投篮出手数");
		comboBox.addItem("三分命中");
		comboBox.addItem("三分出手数");
		comboBox.addItem("罚球命中数");
		comboBox.addItem("罚球出手数");
		comboBox.addItem("进攻篮板");
		comboBox.addItem("防守篮板");
		comboBox.addItem("总篮板");
		comboBox.addItem("助攻");
		comboBox.addItem("抢断");
		comboBox.addItem("盖帽");
		comboBox.addItem("失误");
		comboBox.addItem("犯规");
		comboBox.addItem("得分");
		comboBox.addItem("投篮命中率");
		comboBox.addItem("三分命中率");
		comboBox.addItem("罚球命中率");
		comboBox.addItem("胜率");
		comboBox.addItem("进攻回合");
		comboBox.addItem("进攻效率");
		comboBox.addItem("防守效率");
		comboBox.addItem("进攻篮板效率");
		comboBox.addItem("防守篮板效率");
		comboBox.addItem("抢断效率");
		comboBox.addItem("助攻率");
		comboBox.setSelectedItem(MainFrame.selection2);
		comboBox.setBounds(500, 15, 150, 30);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					MainFrame.selection2="投篮命中数";
					columnName1.setElementAt("投篮命中数(场均)", 2);
					columnName1.setElementAt("投篮命中数(总计)", 3);
					updataTeamsRanking();
					break;
				case 1: 
					MainFrame.selection2="投篮出手数";
					columnName1.setElementAt("投篮出手数(场均)", 2);
					columnName1.setElementAt("投篮出手数(总计)", 3);
					updataTeamsRanking();
					break;
				case 2: 
					MainFrame.selection2="三分命中数";
					columnName1.setElementAt("三分命中数(场均)", 2);
					columnName1.setElementAt("三分命中数(总计)", 3);
					updataTeamsRanking();
					break;
				case 3: 
					MainFrame.selection2="三分出手数";
					columnName1.setElementAt("三分出手数(场均)", 2);
					columnName1.setElementAt("三分出手数(总计)", 3);
					updataTeamsRanking();
					break;
				case 4: 
					MainFrame.selection2="罚球命中数";
					columnName1.setElementAt("罚球命中数(场均)", 2);
					columnName1.setElementAt("罚球命中数(总计)", 3);
					updataTeamsRanking();
					break;
				case 5: 
					MainFrame.selection2="罚球出手数";
					columnName1.setElementAt("罚球出手数(场均)", 2);
					columnName1.setElementAt("罚球出手数(总计)", 3);
					updataTeamsRanking();
					break;
				case 6: 
					MainFrame.selection2="进攻篮板";
					columnName1.setElementAt("进攻篮板(场均)", 2);
					columnName1.setElementAt("进攻篮板(总计)", 3);
					updataTeamsRanking();
					break;
				case 7: 
					MainFrame.selection2="防守篮板";
					columnName1.setElementAt("防守篮板(场均)", 2);
					columnName1.setElementAt("防守篮板(总计)", 3);
					updataTeamsRanking();
					break;
				case 8: 
					MainFrame.selection2="总篮板";
					columnName1.setElementAt("总篮板(场均)", 2);
					columnName1.setElementAt("总篮板(总计)", 3);
					updataTeamsRanking();
					break;
				case 9: 
					MainFrame.selection2="助攻";
					columnName1.setElementAt("助攻(场均)", 2);
					columnName1.setElementAt("助攻(总计)", 3);
					updataTeamsRanking();
					break;
				case 10: 
					MainFrame.selection2="抢断";
					columnName1.setElementAt("抢断(场均)", 2);
					columnName1.setElementAt("抢断(总计)", 3);
					updataTeamsRanking();
					break;
				case 11: 
					MainFrame.selection2="盖帽";
					columnName1.setElementAt("盖帽(场均)", 2);
					columnName1.setElementAt("盖帽(总计)", 3);
					updataTeamsRanking();
					break;
				case 12: 
					MainFrame.selection2="失误";
					columnName1.setElementAt("失误(场均)", 2);
					columnName1.setElementAt("失误(总计)", 3);
					updataTeamsRanking();
					break;
				case 13: 
					MainFrame.selection2="犯规";
					columnName1.setElementAt("犯规(场均)", 2);
					columnName1.setElementAt("犯规(总计)", 3);
					updataTeamsRanking();
					break;
				case 14: 
					MainFrame.selection2="得分";
					columnName1.setElementAt("得分(场均)", 2);
					columnName1.setElementAt("得分(总计)", 3);
					updataTeamsRanking();
					break;
				case 15: 
					MainFrame.selection2="投篮命中率";
					columnName1.setElementAt("投篮命中率(场均)", 2);
					columnName1.setElementAt("投篮命中率(总计)", 3);
					updataTeamsRanking();
					break;
				case 16: 
					MainFrame.selection2="三分命中率";
					columnName1.setElementAt("三分命中率(场均)", 2);
					columnName1.setElementAt("三分命中率(总计)", 3);
					updataTeamsRanking();
					break;
				case 17: 
					MainFrame.selection2="罚球命中率";
					columnName1.setElementAt("罚球命中率(场均)", 2);
					columnName1.setElementAt("罚球命中率(总计)", 3);
					updataTeamsRanking();
					break;
				case 18: 
					MainFrame.selection2="胜率";
					columnName1.setElementAt("胜率(场均)", 2);
					columnName1.setElementAt("胜率(总计)", 3);
					updataTeamsRanking();
					break;
				case 19: 
					MainFrame.selection2="进攻回合";
					columnName1.setElementAt("进攻回合(场均)", 2);
					columnName1.setElementAt("进攻回合(总计)", 3);
					updataTeamsRanking();
					break;
				case 20: 
					MainFrame.selection2="进攻效率";
					columnName1.setElementAt("进攻效率(场均)", 2);
					columnName1.setElementAt("进攻效率(总计)", 3);
					updataTeamsRanking();
					break;
				case 21: 
					MainFrame.selection2="防守效率";
					columnName1.setElementAt("防守效率(场均)", 2);
					columnName1.setElementAt("防守效率(总计)", 3);
					updataTeamsRanking();
					break;
				case 22: 
					MainFrame.selection2="进攻篮板效率";
					columnName1.setElementAt("进攻篮板效率(场均)", 2);
					columnName1.setElementAt("进攻篮板效率(总计)", 3);
					updataTeamsRanking();
					break;
				case 23: 
					MainFrame.selection2="防守篮板效率";
					columnName1.setElementAt("防守篮板效率(场均)", 2);
					columnName1.setElementAt("防守篮板效率(总计)", 3);
					updataTeamsRanking();
					break;
				case 24: 
					MainFrame.selection2="抢断效率";
					columnName1.setElementAt("抢断效率(场均)", 2);
					columnName1.setElementAt("抢断效率(总计)", 3);
					updataTeamsRanking();
					break;
				case 25: 
					MainFrame.selection2="助攻率";
					columnName1.setElementAt("助攻率(场均)", 2);
					columnName1.setElementAt("助攻率(总计)", 3);
					updataTeamsRanking();
					break;
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TeamsRankingFrame.scrollPane.setVisible(false);
				MainFrame.frame.getContentPane().remove(TeamsRankingFrame.scrollPane);
				TeamsRankingFrame.scrollPane=null;
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				updataTeamsRanking();
			}
		});
	}
	
	public void updataTeamsRanking(){
		compute = BLController.getInstance();
		ArrayList<TeamVO> tvoList = compute.getTeamAnalysis();
		
		Vector rowDatas1 = new Vector();
		for (int i = 0; i < tvoList.size(); i++) {
			Vector rowData1 = new Vector();
			TeamVO tvo = tvoList.get(i);
			int appearance = tvo.getAppearance();
			rowData1.add(tvo.getFullName());
			rowData1.add(tvo.getAppearance());
			switch(MainFrame.selection2){
			case "投篮命中数":
				rowData1.add(handle((double) tvo.getHit(), appearance));
				rowData1.add(tvo.getHit());
				break;
			case "投篮出手数":
				rowData1.add(handle((double) tvo.getShot(), appearance));
				rowData1.add(tvo.getShot());
				break;
			case "三分命中数":
				rowData1.add(handle((double) tvo.getThirdHit(), appearance));
				rowData1.add(tvo.getThirdHit());
				break;
			case "三分出手数":
				rowData1.add(handle((double) tvo.getThirdshot(), appearance));
				rowData1.add(tvo.getThirdshot());
				break;
			case "罚球命中数":
				rowData1.add(handle((double) tvo.getFreeHit(), appearance));
				rowData1.add(tvo.getFreeHit());
				break;
			case "罚球出手数":
				rowData1.add(handle((double) tvo.getFreeshot(), appearance));
				rowData1.add(tvo.getFreeshot());
				break;
			case "进攻篮板":
				rowData1.add(handle((double) tvo.getOffensiveRebound(),appearance));
				rowData1.add(tvo.getOffensiveRebound());
				break;
			case "防守篮板":
				rowData1.add(handle((double) tvo.getDefensiveRebound(),appearance));
				rowData1.add(tvo.getDefensiveRebound());
				break;
			case "总篮板":
				rowData1.add(handle((double) tvo.getTotalRebound(),appearance));
				rowData1.add(tvo.getTotalRebound());
				break;
			case "助攻":
				rowData1.add(handle((double) tvo.getAssist(), appearance));
				rowData1.add(tvo.getAssist());
				break;
			case "抢断":
				rowData1.add(handle((double) tvo.getSteal(), appearance));
				rowData1.add(tvo.getSteal());
				break;
			case "盖帽":
				rowData1.add(handle((double) tvo.getBlock(), appearance));
				rowData1.add(tvo.getBlock());
				break;
			case "失误":
				rowData1.add(handle((double) tvo.getMiss(), appearance));
				rowData1.add(tvo.getMiss());
				break;
			case "犯规":
				rowData1.add(handle((double) tvo.getFoul(), appearance));
				rowData1.add(tvo.getFoul());
				break;
			case "得分":
				rowData1.add(handle((double) tvo.getScore(), appearance));
				rowData1.add(tvo.getScore());
				break;
			case "投篮命中率":
				rowData1.add(tvo.getHitRate());
				rowData1.add(tvo.getHitRate());
				break;
			case "三分命中率":
				rowData1.add(tvo.getThirdHitRate());
				rowData1.add(tvo.getThirdHitRate());
				break;
			case "罚球命中率":
				rowData1.add(tvo.getFreeHitRate());
				rowData1.add(tvo.getFreeHitRate());
				break;
			case "胜率":
				rowData1.add(tvo.getWinRate());
				rowData1.add(tvo.getWinRate());
				break;
			case "进攻回合":
				rowData1.add(tvo.getOffensiveRound());
				rowData1.add(tvo.getOffensiveRound());
				break;
			case "进攻效率":
				rowData1.add(tvo.getOffensiveEfficiency());
				rowData1.add(tvo.getOffensiveEfficiency());
				break;
			case "防守效率":
				rowData1.add(tvo.getDefensiveEfficiency());
				rowData1.add(tvo.getDefensiveEfficiency());
				break;
			case "进攻篮板效率":
				rowData1.add(tvo.getOffensiveReboundEfficiency());
				rowData1.add(tvo.getOffensiveReboundEfficiency());
				break;
			case "防守篮板效率":
				rowData1.add(tvo.getDefensiveReboundEfficiency());
				rowData1.add(tvo.getDefensiveReboundEfficiency());
				break;
			case "抢断效率":
				rowData1.add(tvo.getStealEfficiency());
				rowData1.add(tvo.getStealEfficiency());
				break;
			case "助攻率":
				rowData1.add(tvo.getAssistEfficiency());
				rowData1.add(tvo.getAssistEfficiency());
				break;
			}
			rowDatas1.add(rowData1);
		}
		
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		table.setModel(model_1);
		table.updateUI();
		
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
	
}
