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
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;

public class PlayerSelectionPanel extends JPanel {

	BLService blservice = BLController.getInstance();
	ArrayList<TeamWithPlayersVO> teamWithPlayer = blservice
			.getTeamsWithPlayers();
	DefaultTableModel modelOf1 = new DefaultTableModel(
			new Object[][] { { "Alex Len", "Chris Bosh", "Eric Gordon" },
					{ null, null, null }, { null, null, null },
					{ null, null, null }, { null, null, null }, },
			new String[] { "篮网", "", "" });
	DefaultTableModel modelOf2 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "黄蜂",
			"", "" });
	DefaultTableModel modelOf3 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "公牛",
			"", "" });
	DefaultTableModel modelOf4 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "魔术",
			"", "" });
	DefaultTableModel modelOf5 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "雄鹿",
			"", "" });
	DefaultTableModel modelOf6 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "火箭",
			"", "" });
	DefaultTableModel modelOf7 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "老鹰",
			"", "" });
	DefaultTableModel modelOf8 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"森林狼", "", "" });
	DefaultTableModel modelOf9 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "奇才",
			"", "" });
	DefaultTableModel modelOf10 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"凯尔特人", "", "" });
	DefaultTableModel modelOf11 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "小牛",
			"", "" });
	DefaultTableModel modelOf12 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "猛龙",
			"", "" });
	DefaultTableModel modelOf13 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "马刺",
			"", "" });
	DefaultTableModel modelOf14 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "灰熊",
			"", "" });
	DefaultTableModel modelOf15 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "雷霆",
			"", "" });
	DefaultTableModel modelOf16 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "湖人",
			"", "" });
	DefaultTableModel modelOf17 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "热火",
			"", "" });
	DefaultTableModel modelOf18 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "活塞",
			"", "" });
	DefaultTableModel modelOf19 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"步行者", "", "" });
	DefaultTableModel modelOf20 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "鹈鹕",
			"", "" });
	DefaultTableModel modelOf21 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "骑士",
			"", "" });
	DefaultTableModel modelOf22 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "国王",
			"", "" });
	DefaultTableModel modelOf23 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"开拓者", "", "" });
	DefaultTableModel modelOf24 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "太阳",
			"", "" });
	DefaultTableModel modelOf25 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"76人", "", "" });
	DefaultTableModel modelOf26 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "掘金",
			"", "" });
	DefaultTableModel modelOf27 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "快船",
			"", "" });
	DefaultTableModel modelOf28 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "勇士",
			"", "" });
	DefaultTableModel modelOf29 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] {
			"尼克斯", "", "" });
	DefaultTableModel modelOf30 = new DefaultTableModel(new Object[][] {
			{ null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, }, new String[] { "爵士",
			"", "" });
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
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;
	JLabel label12;
	JLabel label13;
	JLabel label14;
	JLabel label15;
	JLabel label16;
	JLabel label17;
	JLabel label18;
	JLabel label19;
	JLabel label20;
	JLabel label21;
	JLabel label22;
	JLabel label23;
	JLabel label24;
	JLabel label25;
	JLabel label26;
	JLabel label27;
	JLabel label28;
	JLabel label29;
	JLabel label30;
	
	JPanel panelOfBottom = new JPanel();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;

	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	MouseListen listener = new MouseListen();

	public final static int HIGHT = 100;
	public final static int START = 50;
	public final static int STARTOFLABEL = 30;
	public final static int GAP = 120;
	private JLabel labelOf1;
	private JLabel labelOf2;
	private JLabel labelOf3;
	private JLabel labelOf4;
	private JLabel labelOf5;
	private JLabel labelOf6;
	private JLabel labelOf7;
	private JButton button;
	
	public PlayerSelectionPanel() {
		this.setBounds(0, 0, 1000, 600);
		// 设置面板透明
		this.setOpaque(false);
		setLayout(null);

		tableOf1 = new JTable(modelOf1);
		tableOf1.setBounds(0, START, 900, HIGHT);
		tableOf1.addMouseListener(listener);

		tableOf2 = new JTable(modelOf2);
		tableOf2.setBounds(0, START+GAP, 900, HIGHT);
		tableOf2.addMouseListener(listener);

		tableOf3 = new JTable(modelOf3);
		tableOf3.setBounds(0, START+GAP*2, 900, HIGHT);
		tableOf3.addMouseListener(listener);

		tableOf4 = new JTable(modelOf4);
		tableOf4.setBounds(0, START+GAP*3, 900, HIGHT);
		tableOf4.addMouseListener(listener);

		tableOf5 = new JTable(modelOf5);
		tableOf5.setBounds(0, START+GAP*4, 900, HIGHT);
		tableOf5.addMouseListener(listener);

		tableOf6 = new JTable(modelOf6);
		tableOf6.setBounds(0, START+GAP*5, 900, HIGHT);
		tableOf6.addMouseListener(listener);

		tableOf7 = new JTable(modelOf7);
		tableOf7.setBounds(0, START+GAP*6, 900, HIGHT);
		tableOf7.addMouseListener(listener);

		tableOf8 = new JTable(modelOf8);
		tableOf8.setBounds(0, START+GAP*7, 900, HIGHT);
		tableOf8.addMouseListener(listener);

		tableOf9 = new JTable(modelOf9);
		tableOf9.setBounds(0, START+GAP*8, 900, HIGHT);
		tableOf9.addMouseListener(listener);

		tableOf10 = new JTable(modelOf10);
		tableOf10.setBounds(0, START+GAP*9, 900, HIGHT);
		tableOf10.addMouseListener(listener);

		tableOf11 = new JTable(modelOf11);
		tableOf11.setBounds(0, START+GAP*10, 900, HIGHT);
		tableOf11.addMouseListener(listener);

		tableOf12 = new JTable(modelOf12);
		tableOf12.setBounds(0, START+GAP*11, 900, HIGHT);
		tableOf12.addMouseListener(listener);

		tableOf13 = new JTable(modelOf13);
		tableOf13.setBounds(0, START+GAP*12, 900, HIGHT);
		tableOf13.addMouseListener(listener);

		tableOf14 = new JTable(modelOf14);
		tableOf14.setBounds(0, START+GAP*13, 900, HIGHT);
		tableOf14.addMouseListener(listener);

		tableOf15 = new JTable(modelOf15);
		tableOf15.setBounds(0, START+GAP*14, 900, HIGHT);
		tableOf15.addMouseListener(listener);

		tableOf16 = new JTable(modelOf16);
		tableOf16.setBounds(0, START+GAP*15, 900, HIGHT);
		tableOf16.addMouseListener(listener);

		tableOf17 = new JTable(modelOf17);
		tableOf17.setBounds(0, START+GAP*16, 900, HIGHT);
		tableOf17.addMouseListener(listener);

		tableOf18 = new JTable(modelOf18);
		tableOf18.setBounds(0, START+GAP*17, 900, HIGHT);
		tableOf18.addMouseListener(listener);

		tableOf19 = new JTable(modelOf19);
		tableOf19.setBounds(0, START+GAP*18, 900, HIGHT);
		tableOf19.addMouseListener(listener);

		tableOf20 = new JTable(modelOf20);
		tableOf20.setBounds(0, START+GAP*19, 900, HIGHT);
		tableOf20.addMouseListener(listener);

		tableOf21 = new JTable(modelOf21);
		tableOf21.setBounds(0, START+GAP*20, 900, HIGHT);
		tableOf21.addMouseListener(listener);

		tableOf22 = new JTable(modelOf22);
		tableOf22.setBounds(0, START+GAP*21, 900, HIGHT);
		tableOf22.addMouseListener(listener);

		tableOf23 = new JTable(modelOf23);
		tableOf23.setBounds(0, START+GAP*22, 900, HIGHT);
		tableOf23.addMouseListener(listener);

		tableOf24 = new JTable(modelOf24);
		tableOf24.setBounds(0, START+GAP*23, 900, HIGHT);
		tableOf24.addMouseListener(listener);

		tableOf25 = new JTable(modelOf25);
		tableOf25.setBounds(0, START+GAP*24, 900, HIGHT);
		tableOf25.addMouseListener(listener);

		tableOf26 = new JTable(modelOf26);
		tableOf26.setBounds(0, START+GAP*25, 900, HIGHT);
		tableOf26.addMouseListener(listener);

		tableOf27 = new JTable(modelOf27);
		tableOf27.setBounds(0, START+GAP*26, 900, HIGHT);
		tableOf27.addMouseListener(listener);

		tableOf28 = new JTable(modelOf28);
		tableOf28.setBounds(0, START+GAP*27, 900, HIGHT);
		tableOf28.addMouseListener(listener);

		tableOf29 = new JTable(modelOf29);
		tableOf29.setBounds(0, START+GAP*28, 900, HIGHT);
		tableOf29.addMouseListener(listener);

		tableOf30 = new JTable(modelOf30);
		tableOf30.setBounds(0, START+GAP*29, 900, HIGHT);
		tableOf30.addMouseListener(listener);

		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		panelOfBottom.setPreferredSize(new Dimension(1000, 3700));
		panelOfBottom.setLayout(null);

		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(10, 0, 100, 23);
		panelOfBottom.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

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
		
		ChangeMouseListen changeListener = new ChangeMouseListen();
		labelOf1 = new JLabel("主界面");
		labelOf1.setBounds(259, 2, 43, 19);
		labelOf1.addMouseListener(changeListener);
		panelOfBottom.add(labelOf1);
		
		labelOf2 = new JLabel("球队");
		labelOf2.setBounds(312, 2, 43, 19);
		labelOf2.addMouseListener(changeListener);
		panelOfBottom.add(labelOf2);
		
		labelOf3 = new JLabel("球员");
		labelOf3.setBounds(362, 2, 43, 19);
		labelOf3.addMouseListener(changeListener);
		panelOfBottom.add(labelOf3);
		
		labelOf4 = new JLabel("球队排名");
		labelOf4.setBounds(416, 2, 76, 19);
		labelOf4.addMouseListener(changeListener);
		panelOfBottom.add(labelOf4);
		
		labelOf5 = new JLabel("球员排名");
		labelOf5.setBounds(250, 4, 43, 19);
		labelOf5.addMouseListener(changeListener);
		panelOfBottom.add(labelOf3);
		
		labelOf6 = new JLabel("热点");
		labelOf6.setBounds(494, 2, 43, 19);
		labelOf6.addMouseListener(changeListener);
		panelOfBottom.add(labelOf6);
		
		labelOf7 = new JLabel("比赛");
		labelOf7.setBounds(550, 2, 43, 19);
		labelOf7.addMouseListener(changeListener);
		panelOfBottom.add(labelOf7);
		
		button = new JButton("最新");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		button.setBounds(126, 0, 93, 23);
		panelOfBottom.add(button);


		if (teamWithPlayer != null && teamWithPlayer.size() != 0) {
			int index = 0;
			TeamWithPlayersVO vo = teamWithPlayer.get(index);
			index++;
			label1 = new JLabel(vo.getTeam().getFullName());
			label1.setBounds(5, STARTOFLABEL, 54, 20);
			panelOfBottom.add(label1);
			initialName(modelOf1, tableOf1, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label2 = new JLabel(vo.getTeam().getFullName());
			label2.setBounds(5, STARTOFLABEL+GAP, 54, 20);
			panelOfBottom.add(label2);
			initialName(modelOf2, tableOf2, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label3 = new JLabel(vo.getTeam().getFullName());
			label3.setBounds(5, STARTOFLABEL+GAP*2, 54, 20);
			panelOfBottom.add(label3);
			initialName(modelOf3, tableOf3, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label4 = new JLabel(vo.getTeam().getFullName());
			label4.setBounds(5, STARTOFLABEL+GAP*3, 54, 20);
			panelOfBottom.add(label4);
			initialName(modelOf4, tableOf4, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label5 = new JLabel(vo.getTeam().getFullName());
			label5.setBounds(5, STARTOFLABEL+GAP*4, 54, 20);
			panelOfBottom.add(label5);
			initialName(modelOf5, tableOf5, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label6 = new JLabel(vo.getTeam().getFullName());
			 label6.setBounds(5, STARTOFLABEL+GAP*5, 54, 20);
			panelOfBottom.add(label6);
			initialName(modelOf6, tableOf6, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label7 = new JLabel(vo.getTeam().getFullName());
			label7.setBounds(5, STARTOFLABEL+GAP*6, 54, 20);
			panelOfBottom.add(label7);
			initialName(modelOf7, tableOf7, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label8 = new JLabel(vo.getTeam().getFullName());
			label8.setBounds(5, STARTOFLABEL+GAP*7, 54, 20);
			panelOfBottom.add(label8);
			initialName(modelOf8, tableOf8, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label9 = new JLabel(vo.getTeam().getFullName());
			label9.setBounds(5, STARTOFLABEL+GAP*8, 54, 20);
			panelOfBottom.add(label9);
			initialName(modelOf9, tableOf9, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label10 = new JLabel(vo.getTeam().getFullName());
			 label10.setBounds(5, STARTOFLABEL+GAP*9, 54, 20);
			panelOfBottom.add(label10);
			initialName(modelOf10, tableOf10, vo);

			vo = teamWithPlayer.get(index);
			index++;
			label11 = new JLabel(vo.getTeam().getFullName());
			label11.setBounds(5, STARTOFLABEL+GAP*10, 54, 20);
			panelOfBottom.add(label11);
			initialName(modelOf11, tableOf11, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label12 = new JLabel(vo.getTeam().getFullName());
			label12.setBounds(5, STARTOFLABEL+GAP*11, 54, 20);
			panelOfBottom.add(label12);
			initialName(modelOf12, tableOf12, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label13 = new JLabel(vo.getTeam().getFullName());
			label13.setBounds(5, STARTOFLABEL+GAP*12, 54, 20);
			panelOfBottom.add(label13);
			initialName(modelOf13, tableOf13, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label14 = new JLabel(vo.getTeam().getFullName());
			label14.setBounds(5, STARTOFLABEL+GAP*13, 54, 20);
			panelOfBottom.add(label14);
			initialName(modelOf14, tableOf14, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label15 = new JLabel(vo.getTeam().getFullName());
			label15.setBounds(5, STARTOFLABEL+GAP*14, 54, 20);
			panelOfBottom.add(label15);
			initialName(modelOf15, tableOf15, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label16 = new JLabel(vo.getTeam().getFullName());
			label16.setBounds(5, STARTOFLABEL+GAP*15, 54, 20);
			panelOfBottom.add(label16);
			initialName(modelOf16, tableOf16, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label17 = new JLabel(vo.getTeam().getFullName());
			label17.setBounds(5, STARTOFLABEL+GAP*16, 54, 20);
			panelOfBottom.add(label17);
			initialName(modelOf17, tableOf17, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label18 = new JLabel(vo.getTeam().getFullName());
			label18.setBounds(5, STARTOFLABEL+GAP*17, 54, 20);
			panelOfBottom.add(label18);
			initialName(modelOf18, tableOf18, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label19 = new JLabel(vo.getTeam().getFullName());
			label19.setBounds(5, STARTOFLABEL+GAP*18, 54, 20);
			panelOfBottom.add(label19);
			initialName(modelOf19, tableOf19, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label20 = new JLabel(vo.getTeam().getFullName());
			label20.setBounds(5, STARTOFLABEL+GAP*19, 54, 20);
			panelOfBottom.add(label20);
			initialName(modelOf20, tableOf20, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label21 = new JLabel(vo.getTeam().getFullName());
			label21.setBounds(5, STARTOFLABEL+GAP*20, 54, 20);
			panelOfBottom.add(label21);
			initialName(modelOf21, tableOf21, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label22 = new JLabel(vo.getTeam().getFullName());
			label22.setBounds(5, STARTOFLABEL+GAP*21, 54, 20);
			panelOfBottom.add(label22);
			initialName(modelOf22, tableOf22, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label23 = new JLabel(vo.getTeam().getFullName());
			label23.setBounds(5, STARTOFLABEL+GAP*22, 54, 20);
			panelOfBottom.add(label23);
			initialName(modelOf23, tableOf23, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label24 = new JLabel(vo.getTeam().getFullName());
			label24.setBounds(5, STARTOFLABEL+GAP*23, 54, 20);
			panelOfBottom.add(label24);
			initialName(modelOf24, tableOf24, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label25 = new JLabel(vo.getTeam().getFullName());
			label25.setBounds(5, STARTOFLABEL+GAP*24, 54, 20);
			panelOfBottom.add(label25);
			initialName(modelOf25, tableOf25, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label26 = new JLabel(vo.getTeam().getFullName());
			label26.setBounds(5, STARTOFLABEL+GAP*25, 54, 20);
			panelOfBottom.add(label26);
			initialName(modelOf26, tableOf26, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label27 = new JLabel(vo.getTeam().getFullName());
			label27.setBounds(5, STARTOFLABEL+GAP*26, 54, 20);
			panelOfBottom.add(label27);
			initialName(modelOf27, tableOf27, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label28 = new JLabel(vo.getTeam().getFullName());
			label28.setBounds(5, STARTOFLABEL+GAP*27, 54, 20);
			panelOfBottom.add(label28);
			initialName(modelOf28, tableOf28, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label29 = new JLabel(vo.getTeam().getFullName());
			label29.setBounds(5, STARTOFLABEL+GAP*28, 54, 20);
			panelOfBottom.add(label29);
			initialName(modelOf29, tableOf29, vo);

			vo = teamWithPlayer.get(index);
			index++;
			 label30 = new JLabel(vo.getTeam().getFullName());
			label30.setBounds(5, STARTOFLABEL+GAP*29, 54, 20);
			panelOfBottom.add(label30);
			initialName(modelOf30, tableOf30, vo);

		}

	}

	public void update() {
		teamWithPlayer = blservice.getTeamsWithPlayers();

		if (teamWithPlayer != null && teamWithPlayer.size() != 0) {
			int index = 0;
			TeamWithPlayersVO vo= teamWithPlayer.get(index);			
			index++;
			label1.setText(vo.getTeam().getFullName());
			initialName(modelOf1, tableOf1, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label2.setText(vo.getTeam().getFullName());
			initialName(modelOf2, tableOf2, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label3.setText(vo.getTeam().getFullName());
			initialName(modelOf3, tableOf3, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label4.setText(vo.getTeam().getFullName());
			initialName(modelOf4, tableOf4, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label5.setText(vo.getTeam().getFullName());
			initialName(modelOf5, tableOf5, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label6.setText(vo.getTeam().getFullName());
			initialName(modelOf6, tableOf6, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label7.setText(vo.getTeam().getFullName());
			initialName(modelOf7, tableOf7, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label8.setText(vo.getTeam().getFullName());
			initialName(modelOf8, tableOf8, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label9.setText(vo.getTeam().getFullName());
			initialName(modelOf9, tableOf9, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label10.setText(vo.getTeam().getFullName());
			initialName(modelOf10, tableOf10, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label11.setText(vo.getTeam().getFullName());
			initialName(modelOf11, tableOf11, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label12.setText(vo.getTeam().getFullName());
			initialName(modelOf12, tableOf12, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label13.setText(vo.getTeam().getFullName());
			initialName(modelOf13, tableOf13, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label14.setText(vo.getTeam().getFullName());
			initialName(modelOf14, tableOf14, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label15.setText(vo.getTeam().getFullName());
			initialName(modelOf15, tableOf15, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label16.setText(vo.getTeam().getFullName());
			initialName(modelOf16, tableOf16, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label17.setText(vo.getTeam().getFullName());
			initialName(modelOf17, tableOf17, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label18.setText(vo.getTeam().getFullName());
			initialName(modelOf18, tableOf18, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label19.setText(vo.getTeam().getFullName());
			initialName(modelOf19, tableOf19, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label20.setText(vo.getTeam().getFullName());
			initialName(modelOf20, tableOf20, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label21.setText(vo.getTeam().getFullName());
			initialName(modelOf21, tableOf21, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label22.setText(vo.getTeam().getFullName());
			initialName(modelOf22, tableOf22, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label23.setText(vo.getTeam().getFullName());
			initialName(modelOf23, tableOf23, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label24.setText(vo.getTeam().getFullName());
			initialName(modelOf24, tableOf24, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label25.setText(vo.getTeam().getFullName());
			initialName(modelOf25, tableOf25, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label26.setText(vo.getTeam().getFullName());
			initialName(modelOf26, tableOf26, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label27.setText(vo.getTeam().getFullName());
			initialName(modelOf27, tableOf27, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label28.setText(vo.getTeam().getFullName());
			initialName(modelOf28, tableOf28, vo);
			
			vo = teamWithPlayer.get(index);
			index++;
			label29.setText(vo.getTeam().getFullName());
			initialName(modelOf29, tableOf29, vo);
			
			vo = teamWithPlayer.get(index);
		
			label30.setText(vo.getTeam().getFullName());
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
		for (int i = 0; i < 3; i++) {
			rowData1.add(players.get(i).getName());
		}
		for (int i = 3; i < 6; i++) {
			rowData2.add(players.get(i).getName());
		}
		for (int i = 6; i < 9; i++) {
			rowData3.add(players.get(i).getName());
		}
		for (int i = 9; i < 12; i++) {
			rowData4.add(players.get(i).getName());
		}
		for (int i = 12; i < players.size(); i++) {// 动态修改为球员list size数量
			rowData5.add(players.get(i).getName());
		}
		rowDatas.add(rowData1);
		rowDatas.add(rowData2);
		rowDatas.add(rowData3);
		rowDatas.add(rowData4);
		rowDatas.add(rowData5);
		int k = 3, size = players.size();
		for (int i = 0; i < rowDatas.size(); i++) {

			if (size < 3)
				//k = 2;//change by lhw
				k=size;
			for (int j = 0; j < k; j++) {
				m.setValueAt(((Vector) rowDatas.get(i)).get(j), i, j);
			}
			size -= 3;
		}
		m.setRowCount(5);
		m.setColumnCount(3);
		t.setModel(m);
		t.updateUI();
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
					MainFrame.pip.update(name);
					PlayerInfoPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员信息");

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
public class ChangeMouseListen extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			String text = label.getText();

			
			switch(text) {
			case "主界面":
				try {
				
				PlayerSelectionPanel.scrollPane.setVisible(false);				
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");		
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "球队":
				try {
					
					PlayerSelectionPanel.scrollPane.setVisible(false);				
					MainFrame.panel.setVisible(true);
					MainFrame.frame.setTitle("NBA");		
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "球员":break;
			case "球队排名":break;
			case "球员排名":break;
			case "热点":break;
			case "比赛":break;
			
			}
		}
		
	}
}
