package presentation.playerui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;

import blservice.BLService;
import presentation.mainui.MainFrame;
import server.businesslogic.Compute;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
 
public class PlayerSelectionPanel extends JPanel {
	
	BLService blservice = Compute.getInstance();
	ArrayList<TeamWithPlayersVO> teamWithPlayer = blservice.getTeamsWithPlayers();
	DefaultTableModel modelOf1=new DefaultTableModel(new Object[][] {
					{"Alex Len", "Chris Bosh", "Eric Gordon"},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {"篮网","",""}
			);
	DefaultTableModel modelOf2=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"黄蜂","",""}
	);
	DefaultTableModel modelOf3=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"公牛","",""}
	);
	DefaultTableModel modelOf4=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"魔术","",""}
	);
	DefaultTableModel modelOf5=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"雄鹿","",""}
	);
	DefaultTableModel modelOf6=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"火箭","",""}
	);
	DefaultTableModel modelOf7=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"老鹰","",""}
	);
	DefaultTableModel modelOf8=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"森林狼","",""}
	);
	DefaultTableModel modelOf9=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"奇才","",""}
	);
	DefaultTableModel modelOf10=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"凯尔特人","",""}
	);
	DefaultTableModel modelOf11=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"小牛","",""}
	);
	DefaultTableModel modelOf12=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"猛龙","",""}
	);
	DefaultTableModel modelOf13=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"马刺","",""}
	);
	DefaultTableModel modelOf14=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"灰熊","",""}
	);
	DefaultTableModel modelOf15=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"雷霆","",""}
	);
	DefaultTableModel modelOf16=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"湖人","",""}
	);
	DefaultTableModel modelOf17=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"热火","",""}
	);
	DefaultTableModel modelOf18=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"活塞","",""}
	);
	DefaultTableModel modelOf19=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"步行者","",""}
	);
	DefaultTableModel modelOf20=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"鹈鹕","",""}
	);
	DefaultTableModel modelOf21=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"骑士","",""}
	);
	DefaultTableModel modelOf22=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"国王","",""}
	);
	DefaultTableModel modelOf23=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"开拓者","",""}
	);
	DefaultTableModel modelOf24=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"太阳","",""}
	);
	DefaultTableModel modelOf25=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"76人","",""}
	);
	DefaultTableModel modelOf26=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"掘金","",""}
	);
	DefaultTableModel modelOf27=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"快船","",""}
	);
	DefaultTableModel modelOf28=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"勇士","",""}
	);
	DefaultTableModel modelOf29=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"尼克斯","",""}
	);
	DefaultTableModel modelOf30=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"爵士","",""}
	);
	private JTable tableOf1;
	private JTable tableOf2;
	private JTable tableOf3;
	private JTable tableOf4;
	private JTable tableOf5;
	private JTable tableOf6;
	private JTable tableOf7;
	private JTable tableOf8;
	private JTable tableOf9;
	private JTable tableOf10;
	private JTable tableOf11;
	private JTable tableOf12;
	private JTable tableOf13;
	private JTable tableOf14;
	private JTable tableOf15;
	private JTable tableOf16;
	private JTable tableOf17;
	private JTable tableOf18;
	private JTable tableOf19;
	private JTable tableOf20;
	private JTable tableOf21;
	private JTable tableOf22;
	private JTable tableOf23;
	private JTable tableOf24;
	private JTable tableOf25;
	private JTable tableOf26;
	private JTable tableOf27;
	private JTable tableOf28;
	private JTable tableOf29;
	private JTable tableOf30;
	
	JPanel panelOfBottom = new JPanel();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	MouseListen listener = new MouseListen();
	
	public final static int HIGHT = 100;
	public PlayerSelectionPanel() {
		this.setBounds(0, 0, 1000,600);
		//设置面板透明
		this.setOpaque(false);
		setLayout(null);
		
		tableOf1 = new JTable(modelOf1);
		tableOf1.setBounds(0, 20, 900, HIGHT);
		tableOf1.addMouseListener(listener);
		
		tableOf2 = new JTable(modelOf2);
		tableOf2.setBounds(0, 140, 900, HIGHT);
		tableOf2.addMouseListener(listener);
		
		tableOf3 = new JTable(modelOf3);
		tableOf3.setBounds(0, 260, 900, HIGHT);
		tableOf3.addMouseListener(listener);
		
		tableOf4 = new JTable(modelOf4 );
		tableOf4.setBounds(0, 380, 900, HIGHT);
		tableOf4.addMouseListener(listener);
		
		tableOf5 = new JTable(modelOf5);
		tableOf5.setBounds(0, 500, 900, HIGHT);
		tableOf5.addMouseListener(listener);
		
		tableOf6 = new JTable(modelOf6);
		tableOf6.setBounds(0, 620, 900, HIGHT);
		tableOf6.addMouseListener(listener);
		
		tableOf7 = new JTable(modelOf7);
		tableOf7.setBounds(0, 740, 900, HIGHT);
		tableOf7.addMouseListener(listener);
		
		tableOf8 = new JTable(modelOf8);
		tableOf8.setBounds(0, 860, 900, HIGHT);
		tableOf8.addMouseListener(listener);
		
		tableOf9 = new JTable(modelOf9);
		tableOf9.setBounds(0, 980, 900, HIGHT);
		tableOf9.addMouseListener(listener);
		
		tableOf10 = new JTable(modelOf10);
		tableOf10.setBounds(0, 1100, 900, HIGHT);
		tableOf10.addMouseListener(listener);
		
		tableOf11 = new JTable(modelOf11);
		tableOf11.setBounds(0, 1220, 900, HIGHT);
		tableOf11.addMouseListener(listener);
		
		tableOf12 = new JTable(modelOf12);
		tableOf12.setBounds(0, 1340, 900, HIGHT);
		tableOf12.addMouseListener(listener);
		
		tableOf13 = new JTable(modelOf13);
		tableOf13.setBounds(0, 1460, 900, HIGHT);
		tableOf13.addMouseListener(listener);
		
		tableOf14 = new JTable(modelOf14);
		tableOf14.setBounds(0, 1580, 900, HIGHT);
		tableOf14.addMouseListener(listener);
		
		tableOf15 = new JTable(modelOf15);
		tableOf15.setBounds(0, 1700, 900, HIGHT);
		tableOf15.addMouseListener(listener);
		
		tableOf16 = new JTable(modelOf16);
		tableOf16.setBounds(0, 1820, 900, HIGHT);
		tableOf16.addMouseListener(listener);
		
		tableOf17 = new JTable(modelOf17);
		tableOf17.setBounds(0, 1940, 900, HIGHT);
		tableOf17.addMouseListener(listener);
		
		tableOf18 = new JTable(modelOf18);
		tableOf18.setBounds(0, 2060, 900, HIGHT);
		tableOf18.addMouseListener(listener);
		
		tableOf19 = new JTable(modelOf19);
		tableOf19.setBounds(0, 2180, 900, HIGHT);
		tableOf19.addMouseListener(listener);
		
		tableOf20 = new JTable(modelOf20);
		tableOf20.setBounds(0, 2300, 900, HIGHT);
		tableOf20.addMouseListener(listener);
		
		tableOf21 = new JTable(modelOf21);
		tableOf21.setBounds(0, 2420, 900, HIGHT);
		tableOf21.addMouseListener(listener);
		
		tableOf22 = new JTable(modelOf22);
		tableOf22.setBounds(0, 2540, 900, HIGHT);
		tableOf22.addMouseListener(listener);
		
		tableOf23 = new JTable(modelOf23);
		tableOf23.setBounds(0, 2660, 900, HIGHT);
		tableOf23.addMouseListener(listener);
		
		tableOf24 = new JTable(modelOf24);
		tableOf24.setBounds(0, 2780, 900, HIGHT);
		tableOf24.addMouseListener(listener);
		
		tableOf25 = new JTable(modelOf25);
		tableOf25.setBounds(0, 2900, 900, HIGHT);
		tableOf25.addMouseListener(listener);
		
		tableOf26 = new JTable(modelOf26);
		tableOf26.setBounds(0, 3020, 900, HIGHT);
		tableOf26.addMouseListener(listener);
		
		tableOf27 = new JTable(modelOf27);
		tableOf27.setBounds(0, 3140, 900, HIGHT);
		tableOf27.addMouseListener(listener);
		
		tableOf28 = new JTable(modelOf28);
		tableOf28.setBounds(0, 3260, 900, HIGHT);
		tableOf28.addMouseListener(listener);
		
		tableOf29 = new JTable(modelOf29);
		tableOf29.setBounds(0, 3380, 900, HIGHT);
		tableOf29.addMouseListener(listener);
		
		tableOf30 = new JTable(modelOf30);
		tableOf30.setBounds(0, 3500, 900, HIGHT);
		tableOf30.addMouseListener(listener);
		
		
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		add(scrollPane);
		panelOfBottom.setPreferredSize(new Dimension(1000, 3700));
		panelOfBottom.setLayout(null);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(400, 0, 120, 30);
		panelOfBottom.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				PlayerSelectionPanel.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
		});
			
		panelOfBottom.add(tableOf1);
		panelOfBottom.add(tableOf2);
		panelOfBottom.add(tableOf3);
		panelOfBottom.add(tableOf4);
		panelOfBottom.add(tableOf5);
		panelOfBottom.add(tableOf6);
		panelOfBottom.add(tableOf7);
		panelOfBottom.add(tableOf8);
		panelOfBottom.add(tableOf9);
		panelOfBottom.add(tableOf10);
		panelOfBottom.add(tableOf11);	
		panelOfBottom.add(tableOf12);
		panelOfBottom.add(tableOf13);
		panelOfBottom.add(tableOf14);
		panelOfBottom.add(tableOf15);		
		panelOfBottom.add(tableOf16);
		panelOfBottom.add(tableOf17);
		panelOfBottom.add(tableOf18);
		panelOfBottom.add(tableOf19);
		panelOfBottom.add(tableOf20);
		panelOfBottom.add(tableOf21);
		panelOfBottom.add(tableOf22);
		panelOfBottom.add(tableOf23);
		panelOfBottom.add(tableOf24);
		panelOfBottom.add(tableOf25);
		panelOfBottom.add(tableOf26);
		panelOfBottom.add(tableOf27);
		panelOfBottom.add(tableOf28);
		panelOfBottom.add(tableOf29);
		panelOfBottom.add(tableOf30);				
		
		if(teamWithPlayer!=null&&teamWithPlayer.size()!=0) {
			int index=0;
			TeamWithPlayersVO vo = teamWithPlayer.get(index);
			index++;
			JLabel label1 = new JLabel(vo.getTeam().getFullName());
			label1.setBounds(5, 0, 54, 20);
			panelOfBottom.add(label1);
			initialName(modelOf1, tableOf1, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable2 = new JLabel(vo.getTeam().getFullName());
			lable2.setBounds(5, 120, 54, 20);
			panelOfBottom.add(lable2);
			initialName(modelOf2, tableOf2, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel label3 = new JLabel(vo.getTeam().getFullName());
			label3.setBounds(5, 240, 54, 20);
			panelOfBottom.add(label3);
			initialName(modelOf3, tableOf3, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel label4 = new JLabel(vo.getTeam().getFullName());
			label4.setBounds(5, 360, 54, 20);
			panelOfBottom.add(label4);
			initialName(modelOf4, tableOf4, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel label5 = new JLabel(vo.getTeam().getFullName());
			label5.setBounds(5, 480, 54, 20);
			panelOfBottom.add(label5);
			initialName(modelOf5, tableOf5, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable6 = new JLabel(vo.getTeam().getFullName());
			lable6.setBounds(5, 600, 54, 20);
			panelOfBottom.add(lable6);
			initialName(modelOf6, tableOf6, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable7 = new JLabel(vo.getTeam().getFullName());
			lable7.setBounds(5, 720, 54, 20);
			panelOfBottom.add(lable7);
			initialName(modelOf7, tableOf7, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable8 = new JLabel(vo.getTeam().getFullName());
			lable8.setBounds(5, 840, 54, 20);
			panelOfBottom.add(lable8);
			initialName(modelOf8, tableOf8, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable9 = new JLabel(vo.getTeam().getFullName());
			lable9.setBounds(5, 960, 54, 20);
			panelOfBottom.add(lable9);
			initialName(modelOf9, tableOf9, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable10 = new JLabel(vo.getTeam().getFullName());
			lable10.setBounds(5, 1080, 54, 20);
			panelOfBottom.add(lable10);
			initialName(modelOf10, tableOf10, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable11 = new JLabel(vo.getTeam().getFullName());
			lable11.setBounds(5, 1200, 54, 20);
			panelOfBottom.add(lable11);
			initialName(modelOf11, tableOf11, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable12 = new JLabel(vo.getTeam().getFullName());
			lable12.setBounds(5, 1320, 54, 20);
			panelOfBottom.add(lable12);
			initialName(modelOf12, tableOf12, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable13 = new JLabel(vo.getTeam().getFullName());
			lable13.setBounds(5, 1440, 54, 20);
			panelOfBottom.add(lable13);
			initialName(modelOf13, tableOf13, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable14 = new JLabel(vo.getTeam().getFullName());
			lable14.setBounds(5, 1560, 54, 20);
			panelOfBottom.add(lable14);
			initialName(modelOf14, tableOf14, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable15 = new JLabel(vo.getTeam().getFullName());
			lable15.setBounds(5, 1680, 54, 20);
			panelOfBottom.add(lable15);
			initialName(modelOf15, tableOf15, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable16 = new JLabel(vo.getTeam().getFullName());
			lable16.setBounds(5, 1800, 54, 20);
			panelOfBottom.add(lable16);
			initialName(modelOf16, tableOf16, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable17 = new JLabel(vo.getTeam().getFullName());
			lable17.setBounds(5, 1920, 54, 20);
			panelOfBottom.add(lable17);
			initialName(modelOf17, tableOf17, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable18 = new JLabel(vo.getTeam().getFullName());
			lable18.setBounds(5, 2040, 54, 20);
			panelOfBottom.add(lable18);
			initialName(modelOf18, tableOf18, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable19 = new JLabel(vo.getTeam().getFullName());
			lable19.setBounds(5, 2160, 54, 20);
			panelOfBottom.add(lable19);
			initialName(modelOf19, tableOf19, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable20 = new JLabel(vo.getTeam().getFullName());
			lable20.setBounds(5, 2280, 54, 20);
			panelOfBottom.add(lable20);
			initialName(modelOf20, tableOf20, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable21 = new JLabel(vo.getTeam().getFullName());
			lable21.setBounds(5, 2400, 54, 20);
			panelOfBottom.add(lable21);
			initialName(modelOf21, tableOf21, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable22 = new JLabel(vo.getTeam().getFullName());
			lable22.setBounds(5, 2520, 54, 20);
			panelOfBottom.add(lable22);
			initialName(modelOf22, tableOf22, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable23 = new JLabel(vo.getTeam().getFullName());
			lable23.setBounds(5, 2640, 54, 20);
			panelOfBottom.add(lable23);
			initialName(modelOf23, tableOf23, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable24 = new JLabel(vo.getTeam().getFullName());
			lable24.setBounds(5, 2760, 54, 20);
			panelOfBottom.add(lable24);
			initialName(modelOf24, tableOf24, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable25 = new JLabel(vo.getTeam().getFullName());
			lable25.setBounds(5, 2880, 54, 20);
			panelOfBottom.add(lable25);
			initialName(modelOf25, tableOf25, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable26 = new JLabel(vo.getTeam().getFullName());
			lable26.setBounds(5, 3000, 54, 20);
			panelOfBottom.add(lable26);
			initialName(modelOf26, tableOf26, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable27 = new JLabel(vo.getTeam().getFullName());
			lable27.setBounds(5, 3120, 54, 20);
			panelOfBottom.add(lable27);
			initialName(modelOf27, tableOf27, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable28 = new JLabel(vo.getTeam().getFullName());
			lable28.setBounds(5, 3240, 54, 20);
			panelOfBottom.add(lable28);
			initialName(modelOf28, tableOf28, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable29 = new JLabel(vo.getTeam().getFullName());
			lable29.setBounds(5, 3360, 54, 20);
			panelOfBottom.add(lable29);
			initialName(modelOf29, tableOf29, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			JLabel lable30 = new JLabel(vo.getTeam().getFullName());
			lable30.setBounds(5, 3480, 54, 20);
			panelOfBottom.add(lable30);
			initialName(modelOf30, tableOf30, vo);
			
		}
		
	}
	
	
	public void initialName(DefaultTableModel m, JTable t, TeamWithPlayersVO vo) {
		Vector rowData1 = new Vector();
		Vector rowData2 = new Vector();
		Vector rowData3 = new Vector();
		Vector rowData4 = new Vector();
		Vector rowData5 = new Vector();
		Vector rowDatas = new Vector();
		ArrayList<PlayerVO> players = vo.getPlayers();
		for(int i=0;i<3;i++) {
			rowData1.add(players.get(i).getName());
		}
		for(int i=3;i<6;i++) {
			rowData2.add(players.get(i).getName());	
		}
		for(int i=6;i<9;i++) {
			rowData3.add(players.get(i).getName());
		}
		for(int i=9;i<12;i++) {
			rowData4.add(players.get(i).getName());
		}
		for(int i=12;i<players.size();i++) {//动态修改为球员list size数量
			rowData5.add(players.get(i).getName());
		}
		rowDatas.add(rowData1); rowDatas.add(rowData2);rowDatas.add(rowData3);
		rowDatas.add(rowData4);rowDatas.add(rowData5);
		int k=3,size = players.size();
		for(int i=0;i<rowDatas.size();i++){
			
			if(size<3) k=2;
			for(int j=0;j<k;j++) {
				m.setValueAt(((Vector) rowDatas.get(i)).get(j), i, j);
			}
			size-=3;
		}
		m.setRowCount(5); m.setColumnCount(3);
		t.setModel(m);
		t.updateUI();
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
					MainFrame.pip.update(name);
					PlayerInfoPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员信息");

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
