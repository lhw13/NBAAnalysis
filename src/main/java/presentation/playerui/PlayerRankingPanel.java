package presentation.playerui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.RowFilter;

import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.matchui.MatchSelectionPanel.MouseListen;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import vo.PlayerVO;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class PlayerRankingPanel extends JPanel {
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	private JComboBox<String> comboBox;
	public static JTable table;
	
	private static JLabel kingOfScore;
	private static JLabel kingOfRebound;
	private static JLabel kingOfAssist;
	private static JLabel kingOfSteal;
	private static JLabel kingOfBlock;
	
	private static JLabel crownOfScore;
	private static JLabel crownOfRebound;
	private static JLabel crownOfAssist;
	private static JLabel crownOfSteal;
	private static JLabel crownOfBlock;

	public static JCheckBox chckbxNewCheckBox;
	public static JCheckBox chckbxNewCheckBox_1;
	public static JCheckBox chckbxNewCheckBox_2;
	public static JCheckBox chckbxNewCheckBox_3;
	public static JCheckBox chckbxNewCheckBox_4;
	public static JCheckBox chckbxNewCheckBox_5;
	public static JCheckBox chckbxNewCheckBox_6;
	public static JCheckBox chckbxNewCheckBox_7;
	public static JCheckBox chckbxNewCheckBox_8;
	public static JCheckBox chckbxNewCheckBox_9;
	public static JCheckBox chckbxNewCheckBox_10;
	
	MouseListen listener = new MouseListen();
	
	Vector columnName1;
	DefaultTableModel model_1=new DefaultTableModel() {
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	
	private BLController compute;

	public PlayerRankingPanel() {
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 10, 100, 40);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlayerRankingPanel.scrollPane.setVisible(false);
				MainFrame.frame.getContentPane().remove(PlayerRankingPanel.scrollPane);
				PlayerRankingPanel.scrollPane=null;
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
				MainFrame.currentPanel = Panels.MainFrame;
			}

		});
		
		kingOfScore = new JLabel("得分榜");
		kingOfScore.setBounds(200, 120, 100, 50);
		panel.add(kingOfScore);
		kingOfScore.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				updatePlayerWithCondition("point");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				crownOfScore.setVisible(true);
				kingOfScore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crownOfScore.setVisible(false);
				kingOfScore.setCursor(Cursor.getDefaultCursor());
			}});
		
		
		kingOfRebound = new JLabel("篮板榜");
		kingOfRebound.setBounds(300, 120, 100, 50);
		panel.add(kingOfRebound);
		kingOfRebound.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				updatePlayerWithCondition("rebound");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				crownOfRebound.setVisible(true);
				kingOfRebound.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crownOfRebound.setVisible(false);
				kingOfRebound.setCursor(Cursor.getDefaultCursor());
			}});
		
		kingOfAssist = new JLabel("助攻榜");
		kingOfAssist.setBounds(400, 120, 100, 50);
		panel.add(kingOfAssist);
		kingOfAssist.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				updatePlayerWithCondition("assist");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				crownOfAssist.setVisible(true);
				kingOfAssist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crownOfAssist.setVisible(false);
				kingOfAssist.setCursor(Cursor.getDefaultCursor());
			}});
		
		kingOfSteal = new JLabel("抢断榜");
		kingOfSteal.setBounds(500, 120, 100, 50);
		panel.add(kingOfSteal);
		kingOfSteal.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				updatePlayerWithCondition("steal");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				crownOfSteal.setVisible(true);
				kingOfSteal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crownOfSteal.setVisible(false);
				kingOfSteal.setCursor(Cursor.getDefaultCursor());
			}});
		
		kingOfBlock = new JLabel("盖帽榜");
		kingOfBlock.setBounds(600, 120, 100, 50);
		panel.add(kingOfBlock);
		kingOfBlock.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				updatePlayerWithCondition("blockShot");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				crownOfBlock.setVisible(true);
				kingOfBlock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crownOfBlock.setVisible(false);
				kingOfBlock.setCursor(Cursor.getDefaultCursor());
			}});
		
		crownOfScore = new JLabel();
		crownOfScore.setBounds(175, 100, 100, 30);
		panel.add(crownOfScore);
		crownOfScore.setIcon(new ImageIcon("conf\\pictures\\crown.png"));
		crownOfScore.setVisible(false);
		
		crownOfRebound = new JLabel();
		crownOfRebound.setBounds(275, 100, 100, 30);
		panel.add(crownOfRebound);
		crownOfRebound.setIcon(new ImageIcon("conf\\pictures\\crown.png"));
		crownOfRebound.setVisible(false);
		
		crownOfAssist = new JLabel();
		crownOfAssist.setBounds(375, 100, 100, 30);
		panel.add(crownOfAssist);
		crownOfAssist.setIcon(new ImageIcon("conf\\pictures\\crown.png"));
		crownOfAssist.setVisible(false);
		
		crownOfSteal = new JLabel();
		crownOfSteal.setBounds(475, 100, 100, 30);
		panel.add(crownOfSteal);
		crownOfSteal.setIcon(new ImageIcon("conf\\pictures\\crown.png"));
		crownOfSteal.setVisible(false);
		
		crownOfBlock = new JLabel();
		crownOfBlock.setBounds(575, 100, 100, 30);
		panel.add(crownOfBlock);
		crownOfBlock.setIcon(new ImageIcon("conf\\pictures\\crown.png"));
		crownOfBlock.setVisible(false);
		
		String[] names1 = new String[]{"", "球员", "球队", "位置", "赛区", "分区", "得分(场均)", "得分(总计)"};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}
		
		table = new JTable();
		table.setModel(model_1);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model_1);
		table.setRowSorter(sorter);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(56);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		table.addMouseListener(listener);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 150, 800, 400);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		tabbedPane.addTab("球员排名", null, scrollPane_1, null);

		panel.add(btnNewButton);
		panel.add(tabbedPane);
		scrollPane.setViewportView(panel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		chckbxNewCheckBox = new JCheckBox("前锋");
		chckbxNewCheckBox.setBounds(200, 10, 150, 30);
		panel.add(chckbxNewCheckBox);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("得分");
		comboBox.addItem("篮板");
		comboBox.addItem("助攻");
		comboBox.addItem("得分/篮板/助攻");
		comboBox.addItem("盖帽");
		comboBox.addItem("抢断");
		comboBox.addItem("犯规");
		comboBox.addItem("失误");
		comboBox.addItem("分钟");
		comboBox.addItem("效率");
		comboBox.addItem("投篮");
		comboBox.addItem("三分");
		comboBox.addItem("罚球");
		comboBox.addItem("两双");
		comboBox.setSelectedItem(MainFrame.selection1);
		comboBox.setBounds(700, 130, 150, 30);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0: 
					MainFrame.selection1="得分";
					columnName1.setElementAt("得分(场均)", 6);
					columnName1.setElementAt("得分(总计)", 7);
					updatePlayerRanking();
					break;
				case 1: 
					MainFrame.selection1="篮板";
					columnName1.setElementAt("篮板(场均)", 6);
					columnName1.setElementAt("篮板(总计)", 7);
					updatePlayerRanking();
					break;
				case 2: 
					MainFrame.selection1="助攻";
					columnName1.setElementAt("助攻(场均)", 6);
					columnName1.setElementAt("助攻(总计)", 7);
					updatePlayerRanking();
					break;
				case 3: 
					MainFrame.selection1="得分/篮板/助攻";
					columnName1.setElementAt("得分/篮板/助攻(场均)", 6);
					columnName1.setElementAt("得分/篮板/助攻(总计)", 7);
					updatePlayerRanking();
					break;
				case 4: 
					MainFrame.selection1="盖帽";
					columnName1.setElementAt("盖帽(场均)", 6);
					columnName1.setElementAt("盖帽(总计)", 7);
					updatePlayerRanking();
					break;
				case 5: 
					MainFrame.selection1="抢断";
					columnName1.setElementAt("抢断(场均)", 6);
					columnName1.setElementAt("抢断(总计)", 7);
					updatePlayerRanking();
					break;
				case 6: 
					MainFrame.selection1="犯规";
					columnName1.setElementAt("犯规(场均)", 6);
					columnName1.setElementAt("犯规(总计)", 7);
					updatePlayerRanking();
					break;
				case 7: 
					MainFrame.selection1="失误";
					columnName1.setElementAt("失误(场均)", 6);
					columnName1.setElementAt("失误(总计)", 7);
					updatePlayerRanking();
					break;
				case 8: 
					MainFrame.selection1="分钟";
					columnName1.setElementAt("分钟(场均)", 6);
					columnName1.setElementAt("分钟(总计)", 7);
					updatePlayerRanking();
					break;
				case 9: 
					MainFrame.selection1="效率";
					columnName1.setElementAt("效率(场均)", 6);
					columnName1.setElementAt("效率(总计)", 7);
					updatePlayerRanking();
					break;
				case 10: 
					MainFrame.selection1="投篮";
					columnName1.setElementAt("投篮(场均)", 6);
					columnName1.setElementAt("投篮(总计)", 7);
					updatePlayerRanking();
					break;
				case 11: 
					MainFrame.selection1="三分";
					columnName1.setElementAt("三分(场均)", 6);
					columnName1.setElementAt("三分(总计)", 7);
					updatePlayerRanking();
					break;
				case 12: 
					MainFrame.selection1="罚球";
					columnName1.setElementAt("罚球(场均)", 6);
					columnName1.setElementAt("罚球(总计)", 7);
					updatePlayerRanking();
					break;
				case 13: 
					MainFrame.selection1="两双";
					columnName1.setElementAt("两双(场均)", 6);
					columnName1.setElementAt("两双(总计)", 7);
					updatePlayerRanking();
					break;
				}
			}
			
		});

		chckbxNewCheckBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "前锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "前锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "前锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "前锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "前锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "前锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "前锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "前锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()) {
					String text = "前锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox.isSelected()) {
					chckbxNewCheckBox_1.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_1 = new JCheckBox("中锋");
		chckbxNewCheckBox_1.setBounds(200, 40, 150, 30);
		panel.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "中锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "中锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "中锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "中锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "中锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "中锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "中锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "中锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()) {
					String text = "中锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox_1.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_2 = new JCheckBox("后卫");
		chckbxNewCheckBox_2.setBounds(200, 70, 150, 30);
		panel.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox_2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "后卫";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "后卫";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "后卫";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "后卫";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "后卫";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "后卫";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "后卫";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "后卫";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()) {
					String text = "后卫";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (!chckbxNewCheckBox_2.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_1.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_3 = new JCheckBox("东部");
		chckbxNewCheckBox_3.setBounds(380, 10, 150, 30);
		panel.add(chckbxNewCheckBox_3);

		chckbxNewCheckBox_3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "东部";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "东部";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_3.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "东部";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "前锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "中锋";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					String text = "后卫";
					String text1 = "东部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_3.isSelected()) {
					String text = "东部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_4 = new JCheckBox("西部");
		chckbxNewCheckBox_4.setBounds(380, 40, 150, 30);
		panel.add(chckbxNewCheckBox_4);

		chckbxNewCheckBox_4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "西部";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "西部";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox_4.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "西部";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
				} else if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "前锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "中锋";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					String text = "后卫";
					String text1 = "西部";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_4.isSelected()) {
					String text = "西部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_5 = new JCheckBox("中区");
		chckbxNewCheckBox_5.setBounds(540, 10, 150, 30);
		panel.add(chckbxNewCheckBox_5);

		chckbxNewCheckBox_5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "前锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "中锋";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_5.isSelected()) {
					String text = "后卫";
					String text1 = "中区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_5.isSelected()) {
					String text = "中区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_5.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_5.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_6 = new JCheckBox("大西洋区");
		chckbxNewCheckBox_6.setBounds(540, 40, 150, 30);
		panel.add(chckbxNewCheckBox_6);

		chckbxNewCheckBox_6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "前锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "中锋";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_6.isSelected()) {
					String text = "后卫";
					String text1 = "大西洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_6.isSelected()) {
					String text = "大西洋区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_6.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_6.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_7 = new JCheckBox("东南区");
		chckbxNewCheckBox_7.setBounds(540, 70, 150, 30);
		panel.add(chckbxNewCheckBox_7);

		chckbxNewCheckBox_7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "前锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "中锋";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_7.isSelected()) {
					String text = "后卫";
					String text1 = "东南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_7.isSelected()) {
					String text = "东南区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_7.isSelected()
						&& chckbxNewCheckBox_3.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (!chckbxNewCheckBox_7.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_8 = new JCheckBox("西北区");
		chckbxNewCheckBox_8.setBounds(690, 10, 150, 30);
		panel.add(chckbxNewCheckBox_8);

		chckbxNewCheckBox_8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "前锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "中锋";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_8.isSelected()) {
					String text = "后卫";
					String text1 = "西北区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_8.isSelected()) {
					String text = "西北区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_8.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_8.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_9 = new JCheckBox("太平洋区");
		chckbxNewCheckBox_9.setBounds(690, 40, 150, 30);
		panel.add(chckbxNewCheckBox_9);

		chckbxNewCheckBox_9.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "前锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "中锋";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_9.isSelected()) {
					String text = "后卫";
					String text1 = "太平洋区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_9.isSelected()) {
					String text = "太平洋区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_9.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_9.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_10.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_10 = new JCheckBox("西南区");
		chckbxNewCheckBox_10.setBounds(690, 70, 150, 30);
		panel.add(chckbxNewCheckBox_10);

		chckbxNewCheckBox_10.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "前锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "中锋";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()
						&& chckbxNewCheckBox_10.isSelected()) {
					String text = "后卫";
					String text1 = "西南区";
					List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
							2);
					filters.add(RowFilter.regexFilter(text));
					filters.add(RowFilter.regexFilter(text1));
					RowFilter<Object, Object> fooBarFilter = RowFilter
							.andFilter(filters);
					sorter.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
				} else if (chckbxNewCheckBox_10.isSelected()) {
					String text = "西南区";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_10.isSelected()
						&& chckbxNewCheckBox_4.isSelected()) {
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (!chckbxNewCheckBox_10.isSelected()) {
					chckbxNewCheckBox_3.setEnabled(true);
					chckbxNewCheckBox_4.setEnabled(true);
					chckbxNewCheckBox_8.setEnabled(true);
					chckbxNewCheckBox_9.setEnabled(true);
					chckbxNewCheckBox_5.setEnabled(true);
					chckbxNewCheckBox_6.setEnabled(true);
					chckbxNewCheckBox_7.setEnabled(true);
				}

			}

		});

		JButton btnNewButton_1 = new JButton("显示全部");
		btnNewButton_1.setBounds(50, 60, 100, 30);
		panel.add(btnNewButton_1);
		
		add(scrollPane);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String text = "";
				sorter.setRowFilter(RowFilter.regexFilter(text));
				chckbxNewCheckBox.setSelected(false);
				chckbxNewCheckBox_1.setSelected(false);
				chckbxNewCheckBox_2.setSelected(false);
				chckbxNewCheckBox_3.setSelected(false);
				chckbxNewCheckBox_4.setSelected(false);
				chckbxNewCheckBox_5.setSelected(false);
				chckbxNewCheckBox_6.setSelected(false);
				chckbxNewCheckBox_7.setSelected(false);
				chckbxNewCheckBox_8.setSelected(false);
				chckbxNewCheckBox_9.setSelected(false);
				chckbxNewCheckBox_10.setSelected(false);
			}

		});

	}
	
	public void updatePlayerWithCondition(String condition){
		ArrayList<Player> players = compute.getPlayers();
		List<Player> playerList = players;
		Collections.sort(playerList, Comparators.getPlayerComparator(condition));
		PlayerVO pvo = new PlayerVO();
		String[] s = new String[3];
		Vector rowDatas1 = new Vector();
		ImageIcon picture;
		for (int i = 0; i < playerList.size(); i++) {
			Vector rowData1 = new Vector();
			if (playerList.get(i) != null) {
				pvo = playerList.get(i).toVO();
				int appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				if (pvo.getPosition() != null) {
					s = JudeTheFilter(pvo.getPosition(), pvo.getDivision(),
							pvo.getZone());
				}
				rowData1.add(pvo.getTeamFullName());
				rowData1.add(s[0]);
				rowData1.add(s[1]);
				rowData1.add(s[2]);
				switch(condition){
				case "point":
					MainFrame.selection1="得分";
					columnName1.setElementAt("得分(场均)", 5);
					columnName1.setElementAt("得分(总计)", 6);
					rowData1.add(handle((double) pvo.getScore(), appearance));
					rowData1.add(pvo.getScore());
					break;
				case "rebound":
					MainFrame.selection1="篮板";
					columnName1.setElementAt("篮板(场均)", 5);
					columnName1.setElementAt("篮板(总计)", 6);
					rowData1.add(handle((double) pvo.getTotalRebound(),appearance));
					rowData1.add(pvo.getTotalRebound());
					break;
				case "assist":
					MainFrame.selection1="助攻";
					columnName1.setElementAt("助攻(场均)", 5);
					columnName1.setElementAt("助攻(总计)", 6);
					rowData1.add(handle((double) pvo.getAssist(), appearance));
					rowData1.add(pvo.getAssist());
					break;
				case "blockShot":
					MainFrame.selection1="盖帽";
					columnName1.setElementAt("盖帽(场均)", 5);
					columnName1.setElementAt("盖帽(总计)", 6);
					rowData1.add(handle((double) pvo.getBlock(), appearance));
					rowData1.add(pvo.getBlock());
					break;
				case "steal":
					MainFrame.selection1="抢断";
					columnName1.setElementAt("抢断(场均)", 5);
					columnName1.setElementAt("抢断(总计)", 6);
					rowData1.add(handle((double) pvo.getSteal(), appearance));
					rowData1.add(pvo.getSteal());
					break;
				}
				rowDatas1.add(rowData1);
			}
		}
		 
		comboBox.setSelectedItem(MainFrame.selection1);
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		int[] width={80,150,80,80,80,80,80,80};
		table.setColumnModel(getColumn(table, width));
		table.setModel(model_1);
		table.updateUI();
	}
	
	public void updatePlayerRanking(){
		
		 
		compute = BLController.getInstance();
		ArrayList<PlayerVO> pvoList = compute.getPlayerAnalysis();
		PlayerVO pvo = new PlayerVO();
		String[] s = new String[3];
		Vector rowDatas1 = new Vector();
		ImageIcon picture;
		for (int i = 0; i < pvoList.size(); i++) {
			Vector rowData1 = new Vector();
			if (pvoList.get(i) != null) {
				pvo = pvoList.get(i);
				int appearance = pvo.getAppearance();
				picture = ImageHandle.loadPlayer(pvo.getName());
				picture.setImage(picture.getImage().getScaledInstance(70, 56,
						Image.SCALE_DEFAULT));
				rowData1.add(picture);
				rowData1.add(pvo.getName());
				rowData1.add(pvo.getTeamFullName());
				if (pvo.getPosition() != null) {
					s = JudeTheFilter(pvo.getPosition(), pvo.getDivision(),
							pvo.getZone());
				}
				rowData1.add(s[0]);
				rowData1.add(s[1]);
				rowData1.add(s[2]);
				switch(MainFrame.selection1){
				case "得分":
					rowData1.add(handle((double) pvo.getScore(), appearance));
					rowData1.add(pvo.getScore());
					break;
				case "篮板":
					rowData1.add(handle((double) pvo.getTotalRebound(),appearance));
					rowData1.add(pvo.getTotalRebound());
					break;
				case "助攻":
					rowData1.add(handle((double) pvo.getAssist(), appearance));
					rowData1.add(pvo.getAssist());
					break;
				case "得分/篮板/助攻":
					rowData1.add(handle((double) (pvo.getScore()+pvo.getAssist()+pvo.getTotalRebound())*(0.33333), appearance));
					rowData1.add((pvo.getScore()+pvo.getAssist()+pvo.getTotalRebound())*(0.33333));
					break;
				case "盖帽":
					rowData1.add(handle((double) pvo.getBlock(), appearance));
					rowData1.add(pvo.getBlock());
					break;
				case "抢断":
					rowData1.add(handle((double) pvo.getSteal(), appearance));
					rowData1.add(pvo.getSteal());
					break;
				case "犯规":
					rowData1.add(handle((double) pvo.getFoul(), appearance));
					rowData1.add(pvo.getFoul());
					break;
				case "失误":
					rowData1.add(handle((double) pvo.getMiss(), appearance));
					rowData1.add(pvo.getMiss());
					break;
				case "分钟":
					rowData1.add(handle((double) pvo.getPlayTime(),appearance));
					rowData1.add(pvo.getPlayTime());
					break;
				case "效率":
					rowData1.add(pvo.getEfficiency());
					rowData1.add(pvo.getEfficiency());
					break;
				case "投篮":
					rowData1.add(pvo.getHitRate());
					rowData1.add(pvo.getHitRate());
					break;
				case "三分":
					rowData1.add(pvo.getThirdHitRate());
					rowData1.add(pvo.getThirdHitRate());
					break;
				case "罚球":
					rowData1.add(pvo.getFreeHitRate());
					rowData1.add(pvo.getFreeHitRate());
					break;
				case "两双":
					rowData1.add(handle((double) pvo.getTowPairs(),appearance));
					rowData1.add(pvo.getTowPairs());
					break;
				}
				rowDatas1.add(rowData1);
			}
		}
		model_1.setDataVector(rowDatas1, columnName1);
		model_1.setColumnCount(table.getColumnCount());
		model_1.setRowCount(rowDatas1.size());
		int[] width={80,150,80,80,80,80,80,80};
		table.setColumnModel(getColumn(table, width));
		table.setModel(model_1);
		table.updateUI();
		
	}
	
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				PlayerRankingPanel.scrollPane.setVisible(false);
				MainFrame.frame.getContentPane().remove(PlayerRankingPanel.scrollPane);
				PlayerRankingPanel.scrollPane=null;
				MainFrame.pip = new PlayerInfoPanel();
				MainFrame.pip.update(table.getValueAt(r,1).toString());
				MainFrame.frame.getContentPane().add(PlayerInfoPanel.scrollPane);
				PlayerInfoPanel.scrollPane.setVisible(true);
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
				MainFrame.frame.setTitle("NBA球员信息");
				MainFrame.frame.repaint();//刷新重画 
				MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		public void mouseEntered(MouseEvent e) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			table.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public static String[] JudeTheFilter(String position, char division, String zone) {
		String s[] = new String[3];
		if (position.equals("F")) {
			s[0] = "前锋";
		} else if (position.equals("C")) {
			s[0] = "中锋";
		} else if (position.equals("G")) {
			s[0] = "后卫";
		} else if (position.equals("F-C")) {
			s[0] = "前锋-中锋";
		} else if (position.equals("F-G")) {
			s[0] = "前锋-后卫";
		} else if (position.equals("C-F")) {
			s[0] = "中锋-前锋";
		} else if (position.equals("C-G")) {
			s[0] = "中锋-后卫";
		} else if (position.equals("G-F")) {
			s[0] = "后卫-前锋";
		} else if (position.equals("G-C")) {
			s[0] = "后卫-中锋";
		}
		switch (division) {
		case 'E':
			s[1] = "东部";
			break;
		case 'W':
			s[1] = "西部";
			break;
		}
		if (zone.equals("Southeast")) {
			s[2] = "东南区";
		} else if (zone.equals("Southwest")) {
			s[2] = "西南区";
		} else if (zone.equals("Northwest")) {
			s[2] = "西北区";
		} else if (zone.equals("Atlantic")) {
			s[2] = "大西洋区";
		} else if (zone.equals("Central")) {
			s[2] = "中区";
		} else if (zone.equals("Pacific")) {
			s[2] = "太平洋区";
		} else if (zone.equals("Pacific")) {
			s[2] = "太平洋区";
		}
		return s;
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
	
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}
	
}
