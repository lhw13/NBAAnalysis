package presentation.playerui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsRankingFrame;

public class PlayerInfoPanel extends JPanel {
	JPanel panelOfBottom = new JPanel();
	public static JScrollPane scrollPane;
	JLabel labelOfPhoto;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	JButton button;
	MouseListen listener = new MouseListen();
	public PlayerInfoPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 1000));
		panelOfBottom.setLayout(null);
		scrollPane= new JScrollPane(panelOfBottom);
		
		labelOfPhoto = new JLabel("photo");
		labelOfPhoto.setBounds(62, 43, 108, 118);
		panelOfBottom.add(labelOfPhoto);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(192, 57, 622, 81);
		panelOfBottom.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null,null, null, null, null, null, null,null,null},
			},
			new String[] {
				"姓名", "球队","号码", "位置", "身高", "体重", "生日", "年龄","球龄","毕业学校"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(197, 247, 665, 106);
		panelOfBottom.add(tabbedPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_2, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_2.setViewportView(table_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_3, null);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_3.setViewportView(table_2);
		
		button = new JButton("返回");
		button.setBounds(77, 396, 93, 23);
		button.addActionListener(new ActionListener(){

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
		scrollPane.setBounds(0, 0, 990, 600);
		add(scrollPane);
		
	}
	
	public static void update(String name) {
		
	}
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			Object temp = table.getValueAt(r, c);
			String name=null;
			if(temp!=null)
				name = temp.toString();
			
			try {
				if(name!=null) {
					PlayerSelectionPanel.scrollPane.setVisible(false);
					PlayerInfoPanel.scrollPane.setVisible(true);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
