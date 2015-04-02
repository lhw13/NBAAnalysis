package presentation.playerui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.RowFilter;

import presentation.mainui.MainFrame;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class PlayerRankingPanel extends JPanel {
	public DefaultTableModel dtmodel;//by major
	public DefaultTableModel dtmodel1;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	public static JTable table;
	public static JTable table_1;

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

	public PlayerRankingPanel(DefaultTableModel model, DefaultTableModel model1) {
		setLayout(null);

		dtmodel = model;
		dtmodel1 = model1;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 28, 100, 40);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlayerRankingPanel.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}

		});

		table = new JTable();		
		table.setModel(model);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		table.setRowSorter(sorter);

		table_1 = new JTable();
		table_1.setModel(model1);
		final TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(
				model1);
		table_1.setRowSorter(sorter1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 150, 1400, 600);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		tabbedPane.addTab("场均", null, scrollPane_1, null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportView(table_1);
		tabbedPane.addTab("总计", null, scrollPane_2, null);

		panel.setPreferredSize(new Dimension(1500, 1000));
		panel.add(btnNewButton);
		panel.add(tabbedPane);
		scrollPane.setViewportView(panel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		chckbxNewCheckBox = new JCheckBox("前锋");
		chckbxNewCheckBox.setBounds(200, 30, 150, 30);
		panel.add(chckbxNewCheckBox);

		chckbxNewCheckBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox.isSelected()) {
					String text = "前锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					sorter1.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox.isSelected()) {
					chckbxNewCheckBox_1.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_1 = new JCheckBox("中锋");
		chckbxNewCheckBox_1.setBounds(200, 70, 150, 30);
		panel.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (chckbxNewCheckBox_1.isSelected()) {
					String text = "中锋";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					sorter1.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_2.setEnabled(false);
				} else if (!chckbxNewCheckBox_1.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_2.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_2 = new JCheckBox("后卫");
		chckbxNewCheckBox_2.setBounds(200, 110, 150, 30);
		panel.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox_2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (chckbxNewCheckBox_2.isSelected()) {
					String text = "后卫";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					sorter1.setRowFilter(RowFilter.regexFilter(text));
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				} else if (!chckbxNewCheckBox_2.isSelected()) {
					chckbxNewCheckBox.setEnabled(true);
					chckbxNewCheckBox_1.setEnabled(true);
				}

			}

		});

		chckbxNewCheckBox_3 = new JCheckBox("东部");
		chckbxNewCheckBox_3.setBounds(380, 30, 150, 30);
		panel.add(chckbxNewCheckBox_3);

		chckbxNewCheckBox_3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_4.setEnabled(false);
					chckbxNewCheckBox_8.setEnabled(false);
					chckbxNewCheckBox_9.setEnabled(false);
					chckbxNewCheckBox_10.setEnabled(false);
				} else if (chckbxNewCheckBox_3.isSelected()) {
					String text = "东部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_4.setBounds(380, 70, 150, 30);
		panel.add(chckbxNewCheckBox_4);

		chckbxNewCheckBox_4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
					chckbxNewCheckBox.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxNewCheckBox_3.setEnabled(false);
					chckbxNewCheckBox_5.setEnabled(false);
					chckbxNewCheckBox_6.setEnabled(false);
					chckbxNewCheckBox_7.setEnabled(false);
				} else if (chckbxNewCheckBox_4.isSelected()) {
					String text = "西部";
					sorter.setRowFilter(RowFilter.regexFilter(text));
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_5.setBounds(540, 30, 150, 30);
		panel.add(chckbxNewCheckBox_5);

		chckbxNewCheckBox_5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_6.setBounds(540, 70, 150, 30);
		panel.add(chckbxNewCheckBox_6);

		chckbxNewCheckBox_6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_7.setBounds(540, 110, 150, 30);
		panel.add(chckbxNewCheckBox_7);

		chckbxNewCheckBox_7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_8.setBounds(690, 30, 150, 30);
		panel.add(chckbxNewCheckBox_8);

		chckbxNewCheckBox_8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_9.setBounds(690, 70, 150, 30);
		panel.add(chckbxNewCheckBox_9);

		chckbxNewCheckBox_9.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		chckbxNewCheckBox_10.setBounds(690, 110, 150, 30);
		panel.add(chckbxNewCheckBox_10);

		chckbxNewCheckBox_10.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dtmodel.setRowCount(50);
				dtmodel1.setRowCount(50);
				table.setModel(dtmodel);
				table_1.setModel(dtmodel1);
				table.updateUI();
				table_1.updateUI();
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(fooBarFilter);
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
					sorter1.setRowFilter(RowFilter.regexFilter(text));
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
		btnNewButton_1.setBounds(50, 90, 100, 30);
		panel.add(btnNewButton_1);
		add(scrollPane);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String text = "";
				sorter.setRowFilter(RowFilter.regexFilter(text));
				sorter1.setRowFilter(RowFilter.regexFilter(text));
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
}
