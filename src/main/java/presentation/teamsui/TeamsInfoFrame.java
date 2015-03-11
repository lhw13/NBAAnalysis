package presentation.teamsui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import presentation.mainui.MainFrame;

public class TeamsInfoFrame {

	private JTable table;
	private JTable table_1;
	private JTable table_3;
	private JTable table_2;
	private JTable table_4;
	private JTable table_5;
	
	public static JScrollPane scrollPane;
	/**
	 * Create the application.
	 */
	public TeamsInfoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 750, 200, 84);
		
		JButton btnNewButton = new JButton("返回");
		panel.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 135, 1169, 280);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_1, null);
		
		table_1 = new JTable();
		Object table_1_rows[][] ={
				{"科比", 4, 5, 8, 9, 12, 13, 16, 3, 5, 35, 3, 6, 67, 54, 7, 8, 9},
				{"詹姆斯", 3, 6, 7, 10, 11, 14, 15, 4, 40, 40, 65, 86, 7, 4, 3, 2, 0},
				{"韦德", 15, 5, 0, 9, 12, 13, 16, 3, 6, 1, 1, 2, 33, 44, 66, 7, 8},
				
			};
		String table_1_columns[] ={
				"球员", "场数", "在场时间", "投篮命中数", "出手数", "三分命中数", "出手数", "罚球命中数", "出手数", 
				"进攻篮板", "防守篮板", "总篮板数","助攻", "抢断", "盖帽", "失误", "犯规", "得分"
			};
		DefaultTableModel model=new DefaultTableModel(table_1_rows, table_1_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		table_1.setModel(model);
		table_1.setRowSorter(new TableRowSorter<TableModel>(model));
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(7).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(8).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(9).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(10).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(11).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(12).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(13).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(14).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(15).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(16).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(17).setPreferredWidth(50);
		
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_2, null);
		
		table_2 = new JTable();
		Object table_2_rows[][] ={
				{"科比", 99, 45, 10, 20, 10, 20, 10, 20, 15, 25, 40, 7, 8, 3, 6, 5, 100},
				
			};
		String table_2_columns[] ={
				"球员", "场数", "在场时间", "投篮命中数", "出手数", "三分命中数", "出手数", "罚球命中数", "出手数", 
				"进攻篮板", "防守篮板", "总篮板数","助攻", "抢断", "盖帽", "失误", "犯规", "得分"
			};
		table_2.setModel(new DefaultTableModel(table_2_rows, table_2_columns));
		
		DefaultTableModel model1=new DefaultTableModel(table_2_rows, table_2_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		table_2.setModel(model1);
		table_2.setRowSorter(new TableRowSorter<TableModel>(model1));
		
		table_2.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(70);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(85);
		table_2.getColumnModel().getColumn(6).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(7).setPreferredWidth(85);
		table_2.getColumnModel().getColumn(8).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(9).setPreferredWidth(70);
		table_2.getColumnModel().getColumn(10).setPreferredWidth(70);
		table_2.getColumnModel().getColumn(11).setPreferredWidth(70);
		table_2.getColumnModel().getColumn(12).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(13).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(14).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(15).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(16).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(17).setPreferredWidth(50);
		
		scrollPane_2.setViewportView(table_2);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(60, 463, 868, 257);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane_1.addTab("场均", null, scrollPane_3, null);
		
		table_4 = new JTable();
		Object table_4_rows[][] ={
				{"10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%"},
				{"20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%"},
				
			};
		String table_4_columns[] ={
				"投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "篮板效率", "抢断效率", "助攻率"
			};
		table_4.setModel(new DefaultTableModel(table_4_rows, table_4_columns));
		DefaultTableModel model2=new DefaultTableModel(table_4_rows, table_4_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		table_4.setModel(model2);
		table_4.setRowSorter(new TableRowSorter<TableModel>(model2));
		
		table_4.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane_3.setViewportView(table_4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane_1.addTab("总计", null, scrollPane_4, null);
		
		table_5 = new JTable();
		Object table_5_rows[][] ={
				{"10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%", "10%"},
				{"20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%", "20%"},
				
			};
		String table_5_columns[] ={
				"投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "篮板效率", "抢断效率", "助攻率"
			};
		table_5.setModel(new DefaultTableModel(table_5_rows, table_5_columns));
		DefaultTableModel model5=new DefaultTableModel(table_5_rows, table_5_columns){
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0,columnIndex).getClass();
            }
        };
		table_5.setModel(model5);
		table_5.setRowSorter(new TableRowSorter<TableModel>(model5));
		
		table_5.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane_4.setViewportView(table_5);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(60, 38, 788, 71);
		
		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 20));
		Object rows[][] ={
				{"公牛队", null, null, null, null, null, null},
			};
		String columns[] ={
				"球队", "球队缩写", "所在地", "赛区", "分区", "主场", "建立时间"
			};
		
		model=new DefaultTableModel(rows, columns);
		table.setModel(model);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		scrollPane_5.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(1500,1000));
		panel_1.setLayout(null);
		panel_1.add(scrollPane_5);
		panel_1.add(panel);
		panel_1.add(tabbedPane);
		panel_1.add(tabbedPane_1);
		
		scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(0, 0, 990, 560);
		
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					TeamsInfoFrame.scrollPane.setVisible(false);
					TeamsFrame.scrollPane.setVisible(true);
					TeamsFrame.flag=true;
					MainFrame.frame.setTitle("NBA球队选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
	}
	
	
	
}
