package presentation.teamsui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import presentation.mainui.MainFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class CHIFrame {

	public JFrame chiFrame;
	private JTable table;
	private JTable table_1;
	private JTable table_3;
	private JTable table_2;
	private JTable table_4;
	private JTable table_5;
	

	/**
	 * Create the application.
	 */
	public CHIFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		chiFrame = new JFrame();
		chiFrame.setBounds(100, 100, 1300, 800);
		chiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chiFrame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 38, 788, 71);
		chiFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 20));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u9A6C\u523A\u961F", null, null, null, null, null, null},
			},
			new String[] {
				"\u7403\u961F", "\u7403\u961F\u7F29\u5199", "\u6240\u5728\u5730", "\u8D5B\u533A", "\u5206\u533A", "\u4E3B\u573A", "\u5EFA\u7ACB\u65F6\u95F4"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(990, 38, 192, 84);
		chiFrame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 135, 1169, 280);
		chiFrame.getContentPane().add(tabbedPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_1, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u7403\u5458", "\u573A\u6570", "\u5728\u573A\u65F6\u95F4", "\u6295\u7BEE\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u4E09\u5206\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u7F5A\u7403\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u8FDB\u653B\u7BEE\u677F", "\u9632\u5B88\u7BEE\u677F", "\u603B\u7BEE\u677F\u6570", "\u52A9\u653B\u6570", "\u62A2\u65AD\u6570", "\u76D6\u5E3D", "\u5931\u8BEF", "\u72AF\u89C4", "\u5F97\u5206"
			}
		));
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
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u7403\u5458", "\u573A\u6570", "\u5728\u573A\u65F6\u95F4", "\u6295\u7BEE\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u4E09\u5206\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u7F5A\u7403\u547D\u4E2D\u6570", "\u51FA\u624B\u6570", "\u8FDB\u653B\u7BEE\u677F", "\u9632\u5B88\u7BEE\u677F", "\u603B\u7BEE\u677F\u6570", "\u52A9\u653B\u6570", "\u62A2\u65AD\u6570", "\u76D6\u5E3D", "\u5931\u8BEF", "\u72AF\u89C4", "\u5F97\u5206"
			}
		));
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
		chiFrame.getContentPane().add(tabbedPane_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane_1.addTab("场均", null, scrollPane_3, null);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u6295\u7BEE\u547D\u4E2D\u7387", "\u4E09\u5206\u547D\u4E2D\u7387", "\u7F5A\u7403\u547D\u4E2D\u7387", "\u80DC\u7387", "\u8FDB\u653B\u56DE\u5408", "\u8FDB\u653B\u6548\u7387", "\u9632\u5B88\u6548\u7387", "\u7BEE\u677F\u6548\u7387", "\u62A2\u65AD\u6548\u7387", "\u52A9\u653B\u7387"
			}
		));
		table_4.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane_3.setViewportView(table_4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane_1.addTab("总计", null, scrollPane_4, null);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u6295\u7BEE\u547D\u4E2D\u7387", "\u4E09\u5206\u547D\u4E2D\u7387", "\u7F5A\u7403\u547D\u4E2D\u7387", "\u80DC\u7387", "\u8FDB\u653B\u56DE\u5408", "\u8FDB\u653B\u6548\u7387", "\u9632\u5B88\u6548\u7387", "\u7BEE\u677F\u6548\u7387", "\u62A2\u65AD\u6548\u7387", "\u52A9\u653B\u7387"
			}
		));
		table_5.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_5.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane_4.setViewportView(table_5);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					chiFrame.setVisible(false);
					MainFrame window = new MainFrame();
					window.frmNba.setVisible(true);
					MainFrame.flag=true;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
	}
}
