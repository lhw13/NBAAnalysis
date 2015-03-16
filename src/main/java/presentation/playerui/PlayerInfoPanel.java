package presentation.playerui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsRankingFrame;
import server.businesslogic.Compute;
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
	
	BLService blservice = Compute.getInstance();
	
	public PlayerInfoPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		panelOfBottom.setPreferredSize(new Dimension(1300, 600));
		panelOfBottom.setLayout(null);
		scrollPane= new JScrollPane(panelOfBottom);
		
		labelOfPhoto = new JLabel("photo");
		labelOfPhoto.setBounds(700, 23, 230, 185);
		panelOfBottom.add(labelOfPhoto);
		
		labelOfAct = new JLabel("act");
		labelOfAct.setBounds(710, 218, 220, 350);
		panelOfBottom.add(labelOfAct);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 43, 622, 81);
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
		tabbedPane.setBounds(5, 280, 671, 106);
		panelOfBottom.add(tabbedPane);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("总计", null, scrollPane_3, null);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null,null,null,
				null, null,null},
			},
			new String[] {
				"赛季", "参赛", "先发", "时间","篮板数", "助攻数", "进攻数", 
				"防守数", "抢断数", "盖帽数", "失误数", "犯规数","得分"
			}
		));
		scrollPane_3.setViewportView(table_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("场均", null, scrollPane_2, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
					{null, null, null, null, null, null, null, null,null,null,
						null, null,null},
			},
			new String[] {
					"赛季", "参赛场数", "先发场数", "在场时间","篮板数", "助攻数", "进攻数", 
					"防守数", "抢断数", "盖帽数", "失误数", "犯规数","得分"
			}
		));
		scrollPane_2.setViewportView(table_1);
		
		button = new JButton("返回");
		button.setBounds(326, 507, 93, 23);
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
		
		JScrollPane scrollPane_4 = new JScrollPane();

		
		scrollPane_4.setBounds(5, 124, 622, 73);
		panelOfBottom.add(scrollPane_4);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"效率", "GmSc效率", "真实命中率", "投篮效率", "篮板率", "进攻篮板率", 
				"防守篮板率"
			}
		));
		scrollPane_4.setViewportView(table_3);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(5, 198, 406, 81);
		panelOfBottom.add(scrollPane_5);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null,null,null},
			},
			new String[] {
				"盖帽率", "失误率", "使用率" ,"助攻率","抢断率"
			}
		));
		scrollPane_5.setViewportView(table_4);
		scrollPane.setBounds(0, 0, 990, 600);
		add(scrollPane);
		
	}
	
	public  void update(String name) {
		picture = ImageHandle.loadPlayer(name);
		pictureOfAct = ImageHandle.loadPlayerAct(name);
		labelOfPhoto.setIcon(picture);
		pictureOfAct.setImage(pictureOfAct.getImage().getScaledInstance(labelOfAct.getWidth()
				,labelOfAct.getHeight(),Image.SCALE_DEFAULT));
		labelOfAct.setIcon(pictureOfAct);
		PlayerVO vo = blservice.getPlayerAnalysis(name);
		Vector rowData1 = new Vector();
		rowData1.add(vo.getName());  rowData1.add(vo.getTeamFullName());
		rowData1.add(vo.getNumber());
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
