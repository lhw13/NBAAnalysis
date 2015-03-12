package presentation.playerui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

import presentation.mainui.MainFrame;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PlayerRankingPanel extends JPanel {

	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static JComboBox<String> comboBox_3;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JTable table;
	
	public PlayerRankingPanel() {
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 990, 560);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("前锋");
		comboBox.addItem("中锋");
		comboBox.addItem("后卫");
		comboBox.setBounds(50, 100, 200, 30);
		comboBox.setVisible(true);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("东部");
		comboBox_1.addItem("西部");
		comboBox_1.setBounds(250, 100, 200, 30);
		comboBox_1.setVisible(true);
		
		comboBox_1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				switch(index){
				case 0:
					comboBox_2.setVisible(true);
					comboBox_3.setVisible(false);
					break;
				case 1:
					comboBox_2.setVisible(false);
					comboBox_3.setVisible(true);
					break;
				}
				
			}
			
		});
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addItem("中区");
		comboBox_2.addItem("大西洋区");
		comboBox_2.addItem("东南区");
		comboBox_2.setBounds(450, 100, 200, 30);
		comboBox_2.setVisible(true);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.addItem("西北区");
		comboBox_3.addItem("太平洋区");
		comboBox_3.addItem("西南区");
		comboBox_3.setBounds(450, 100, 200, 30);
		comboBox_3.setVisible(false);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 28, 100, 40);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				PlayerRankingPanel.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
			
		});
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 180, 800, 400);
		
		panel.setPreferredSize(new Dimension(1300,1000));
		panel.add(comboBox);
		panel.add(comboBox_1);
		panel.add(comboBox_2);
		panel.add(comboBox_3);
		panel.add(btnNewButton);
		panel.add(scrollPane_1);
		
		table = new JTable();
		Object table_rows[][] ={
				{"姚明", 999, 888, 777, 666, 555, 444, 333, 500, 400, 0, 100, 200, 300},
				
			};
		String table_columns[] ={
				"球员", "得分", "篮板", "助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双"
			};
		table.setModel(new DefaultTableModel(table_rows, table_columns));
		DefaultTableModel model=new DefaultTableModel(table_rows, table_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		table.setModel(model);
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		scrollPane_1.setViewportView(table);
		
		scrollPane.setViewportView(panel);
		add(scrollPane);
	}
}
