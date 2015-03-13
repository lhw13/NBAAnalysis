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

import javax.swing.JLabel;
import javax.swing.JButton;

import presentation.mainui.MainFrame;

public class PlayerSelectionPanel extends JPanel {
	
	
	DefaultTableModel modelOfLanWang=new DefaultTableModel(new Object[][] {
					{"科比", "詹姆斯", "梅杰"},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {"篮网","",""}
			);
	DefaultTableModel modelOfHuangFeng=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"黄蜂","",""}
	);
	DefaultTableModel modelOfGongNiu=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"公牛","",""}
	);
	DefaultTableModel modelOfMoShu=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"魔术","",""}
	);
	DefaultTableModel modelOfXiongLu=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"雄鹿","",""}
	);
	DefaultTableModel modelOfHuoJian=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"火箭","",""}
	);
	DefaultTableModel modelOfLaoYing=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"老鹰","",""}
	);
	DefaultTableModel modelOfSenLinLang=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"森林狼","",""}
	);
	DefaultTableModel modelOfQiCai=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"奇才","",""}
	);
	DefaultTableModel modelOfKaiErTeRen=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"凯尔特人","",""}
	);
	DefaultTableModel modelOfXiaoNiu=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"小牛","",""}
	);
	DefaultTableModel modelOfMengLong=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"猛龙","",""}
	);
	DefaultTableModel modelOfMaCi=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"马刺","",""}
	);
	DefaultTableModel modelOfHuiXiong=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"灰熊","",""}
	);
	DefaultTableModel modelOfLeiTing=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"雷霆","",""}
	);
	DefaultTableModel modelOfHuRen=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"湖人","",""}
	);
	DefaultTableModel modelOfReHuo=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"热火","",""}
	);
	DefaultTableModel modelOfHuoSai=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"活塞","",""}
	);
	DefaultTableModel modelOfBuXingZhe=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"步行者","",""}
	);
	DefaultTableModel modelOfTiHu=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"鹈鹕","",""}
	);
	DefaultTableModel modelOfQiShi=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"骑士","",""}
	);
	DefaultTableModel modelOfGuoWang=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"国王","",""}
	);
	DefaultTableModel modelOfKaiTuoZhe=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"开拓者","",""}
	);
	DefaultTableModel modelOfTaiYang=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"太阳","",""}
	);
	DefaultTableModel modelOf76Ren=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"76人","",""}
	);
	DefaultTableModel modelOfJueJin=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"掘金","",""}
	);
	DefaultTableModel modelOfKuaiChuan=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"快船","",""}
	);
	DefaultTableModel modelOfYongShi=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"勇士","",""}
	);
	DefaultTableModel modelOfNiKeSi=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"尼克斯","",""}
	);
	DefaultTableModel modelOfJueShi=new DefaultTableModel(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
			{null, null, null},
		},
		new String[] {"爵士","",""}
	);
	private JTable tableOfLanWang;
	private JTable tableOfHuangFeng;
	private JTable tableOfGongNiu;
	private JTable tableOfMoShu;
	private JTable tableOfXiongLu;
	private JTable tableOfHuoJian;
	private JTable tableOfLaoYing;
	private JTable tableOfSenLinLang;
	private JTable tableOfQiCai;
	private JTable tableOfKaiErTeRen;
	private JTable tableOfXiaoNiu;
	private JTable tableOfMengLong;
	private JTable tableOfMaCi;
	private JTable tableOfHuiXiong;
	private JTable tableOfLeiTing;
	private JTable tableOfHuRen;
	private JTable tableOfReHuo;
	private JTable tableOfHuoSai;
	private JTable tableOfBuXingZhe;
	private JTable tableOfTiHu;
	private JTable tableOfQiShi;
	private JTable tableOfGuoWang;
	private JTable tableOfKaiTuoZhe;
	private JTable tableOfTaiYang;
	private JTable tableOf76Ren;
	private JTable tableOfJueJin;
	private JTable tableOfKuaiChuan;
	private JTable tableOfYongShi;
	private JTable tableOfNiKeSi;
	private JTable tableOfJueShi;
	
	JPanel panelOfBottom = new JPanel();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	
	public static JScrollPane scrollPane;
	private JButton btnNewButton;
	MouseListen listener = new MouseListen();
	public PlayerSelectionPanel() {
		this.setBounds(0, 0, 1000,600);
		//设置面板透明
		this.setOpaque(false);
		setLayout(null);
		
		tableOfLanWang = new JTable(modelOfLanWang);
		tableOfLanWang.setBounds(0, 20, 900, 80);
		tableOfLanWang.addMouseListener(listener);
		
		tableOfHuangFeng = new JTable(modelOfHuangFeng);
		tableOfHuangFeng.setBounds(0, 120, 900, 80);
		
		tableOfGongNiu = new JTable(modelOfGongNiu);
		tableOfGongNiu.setBounds(0, 220, 900, 80);
		tableOfMoShu = new JTable(modelOfMoShu );
		tableOfMoShu.setBounds(0, 320, 900, 80);
		tableOfXiongLu = new JTable(modelOfXiongLu);
		tableOfXiongLu.setBounds(0, 420, 900, 80);
		tableOfHuoJian = new JTable(modelOfHuoJian);
		tableOfHuoJian.setBounds(0, 520, 900, 80);
		tableOfLaoYing = new JTable(modelOfLaoYing);
		tableOfLaoYing.setBounds(0, 620, 900, 80);
		tableOfSenLinLang = new JTable(modelOfSenLinLang);
		tableOfSenLinLang.setBounds(0, 720, 900, 80);
		tableOfQiCai = new JTable(modelOfQiCai);
		tableOfQiCai.setBounds(0, 820, 900, 80);
		tableOfKaiErTeRen = new JTable(modelOfKaiErTeRen);
		tableOfKaiErTeRen.setBounds(0, 920, 900, 80);
		tableOfXiaoNiu = new JTable(modelOfXiaoNiu);
		tableOfXiaoNiu.setBounds(0, 1020, 900, 80);
		tableOfMengLong = new JTable(modelOfMengLong);
		tableOfMengLong.setBounds(0, 1120, 900, 80);
		tableOfMaCi = new JTable(modelOfMaCi);
		tableOfMaCi.setBounds(0, 1220, 900, 80);
		tableOfHuiXiong = new JTable(modelOfHuiXiong);
		tableOfHuiXiong.setBounds(0, 1320, 900, 80);
		tableOfLeiTing = new JTable(modelOfLeiTing);
		tableOfLeiTing.setBounds(0, 1420, 900, 80);
		tableOfHuRen = new JTable(modelOfHuRen);
		tableOfHuRen.setBounds(0, 1520, 900, 80);
		tableOfReHuo = new JTable(modelOfReHuo);
		tableOfReHuo.setBounds(0, 1620, 900, 80);
		tableOfHuoSai = new JTable(modelOfHuoSai);
		tableOfHuoSai.setBounds(0, 1720, 900, 80);
		tableOfBuXingZhe = new JTable(modelOfBuXingZhe);
		tableOfBuXingZhe.setBounds(0, 1820, 900, 80);
		tableOfTiHu = new JTable(modelOfTiHu);
		tableOfTiHu.setBounds(0, 1920, 900, 80);
		tableOfQiShi = new JTable(modelOfQiShi);
		tableOfQiShi.setBounds(0, 2020, 900, 80);
		tableOfGuoWang = new JTable(modelOfGuoWang);
		tableOfGuoWang.setBounds(0, 2120, 900, 80);
		tableOfKaiTuoZhe = new JTable(modelOfKaiTuoZhe);
		tableOfKaiTuoZhe.setBounds(0, 2220, 900, 80);
		tableOfTaiYang = new JTable(modelOfTaiYang);
		tableOfTaiYang.setBounds(0, 2320, 900, 80);
		tableOf76Ren = new JTable(modelOf76Ren);
		tableOf76Ren.setBounds(0, 2420, 900, 80);
		tableOfJueJin = new JTable(modelOfJueJin);
		tableOfJueJin.setBounds(0, 2520, 900, 80);
		tableOfKuaiChuan = new JTable(modelOfKuaiChuan);
		tableOfKuaiChuan.setBounds(0, 2620, 900, 80);
		tableOfYongShi = new JTable(modelOfYongShi);
		tableOfYongShi.setBounds(0, 2720, 900, 80);
		tableOfNiKeSi = new JTable(modelOfNiKeSi);
		tableOfNiKeSi.setBounds(0, 2820, 900, 80);
		tableOfJueShi = new JTable(modelOfJueShi);
		tableOfJueShi.setBounds(0, 2920, 900, 80);
		
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		add(scrollPane);
		panelOfBottom.setPreferredSize(new Dimension(1000, 3100));
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
			
		panelOfBottom.add(tableOfLanWang);
		panelOfBottom.add(tableOfHuangFeng);
		panelOfBottom.add(tableOfGongNiu);
		panelOfBottom.add(tableOfMoShu);
		panelOfBottom.add(tableOfXiongLu);
		panelOfBottom.add(tableOfHuoJian);
		panelOfBottom.add(tableOfLaoYing);
		panelOfBottom.add(tableOfSenLinLang);
		panelOfBottom.add(tableOfQiCai);
		panelOfBottom.add(tableOfKaiErTeRen);
		panelOfBottom.add(tableOfXiaoNiu);
		
		panelOfBottom.add(tableOfMengLong);
		panelOfBottom.add(tableOfMaCi);
		panelOfBottom.add(tableOfHuiXiong);
		panelOfBottom.add(tableOfLeiTing);
		
		panelOfBottom.add(tableOfHuRen);
		panelOfBottom.add(tableOfReHuo);
		panelOfBottom.add(tableOfHuoSai);
		panelOfBottom.add(tableOfBuXingZhe);
		panelOfBottom.add(tableOfTiHu);
		panelOfBottom.add(tableOfQiShi);
		panelOfBottom.add(tableOfGuoWang);
		panelOfBottom.add(tableOfKaiTuoZhe);
		panelOfBottom.add(tableOfTaiYang);
		panelOfBottom.add(tableOf76Ren);
		panelOfBottom.add(tableOfJueJin);
		panelOfBottom.add(tableOfKuaiChuan);
		panelOfBottom.add(tableOfYongShi);
		panelOfBottom.add(tableOfNiKeSi);
		panelOfBottom.add(tableOfJueShi);
		
		label = new JLabel("");
		label.setBounds(0, 1120, 500, 80);
		panelOfBottom.add(label);
		panelOfBottom.add(tableOfJueShi);
		
		label_1 = new JLabel("");
		label_1.setBounds(0, 1200, 500, 80);
		panelOfBottom.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setBounds(500, 1200, 500, 80);
		panelOfBottom.add(label_2);
		
		JLabel label1 = new JLabel("篮网");
		label1.setBounds(5, 0, 54, 20);
		panelOfBottom.add(label1);
		
		JLabel lable2 = new JLabel("黄蜂");
		lable2.setBounds(5, 100, 54, 20);
		panelOfBottom.add(lable2);
		
		JLabel label3 = new JLabel("公牛");
		label3.setBounds(5, 200, 54, 20);
		panelOfBottom.add(label3);
		
		JLabel label4 = new JLabel("魔术");
		label4.setBounds(5, 300, 54, 20);
		panelOfBottom.add(label4);
		
		JLabel label5 = new JLabel("雄鹿");
		label5.setBounds(5, 400, 54, 20);
		panelOfBottom.add(label5);
		
		JLabel lable6 = new JLabel("火箭");
		lable6.setBounds(5, 500, 54, 20);
		panelOfBottom.add(lable6);
		
		JLabel lable7 = new JLabel("老鹰");
		lable7.setBounds(5, 600, 54, 20);
		panelOfBottom.add(lable7);
		
		JLabel lable8 = new JLabel("森林狼");
		lable8.setBounds(5, 700, 54, 20);
		panelOfBottom.add(lable8);
		
		JLabel lable9 = new JLabel("奇才");
		lable9.setBounds(5, 800, 54, 20);
		panelOfBottom.add(lable9);
		
		JLabel lable10 = new JLabel("凯尔特人");
		lable10.setBounds(5, 900, 54, 20);
		panelOfBottom.add(lable10);
		
		JLabel lable11 = new JLabel("小牛");
		lable11.setBounds(5, 1000, 54, 20);
		panelOfBottom.add(lable11);
		
		JLabel lable12 = new JLabel("猛龙");
		lable12.setBounds(5, 1100, 54, 20);
		panelOfBottom.add(lable12);
		
		JLabel lable13 = new JLabel("马刺");
		lable13.setBounds(5, 1200, 54, 20);
		panelOfBottom.add(lable13);
		
		JLabel lable14 = new JLabel("灰熊");
		lable14.setBounds(5, 1300, 54, 20);
		panelOfBottom.add(lable14);
		
		JLabel lable15 = new JLabel("雷霆");
		lable15.setBounds(5, 1400, 54, 20);
		panelOfBottom.add(lable15);
		
		JLabel lable16 = new JLabel("湖人");
		lable16.setBounds(5, 1500, 54, 20);
		panelOfBottom.add(lable16);
		
		JLabel lable17 = new JLabel("热火");
		lable17.setBounds(5, 1600, 54, 20);
		panelOfBottom.add(lable17);
		
		JLabel lable18 = new JLabel("活塞");
		lable18.setBounds(5, 1700, 54, 20);
		panelOfBottom.add(lable18);
		
		JLabel lable19 = new JLabel("步行者");
		lable19.setBounds(5, 1800, 54, 20);
		panelOfBottom.add(lable19);
		
		JLabel lable20 = new JLabel("鹈鹕");
		lable20.setBounds(5, 1900, 54, 20);
		panelOfBottom.add(lable20);
		
		JLabel lable21 = new JLabel("骑士");
		lable21.setBounds(5, 2000, 54, 20);
		panelOfBottom.add(lable21);
		
		JLabel lable22 = new JLabel("国王");
		lable22.setBounds(5, 2100, 54, 20);
		panelOfBottom.add(lable22);
		
		JLabel lable23 = new JLabel("开拓者");
		lable23.setBounds(5, 2200, 54, 20);
		panelOfBottom.add(lable23);
		
		JLabel lable24 = new JLabel("太阳");
		lable24.setBounds(5, 2300, 54, 20);
		panelOfBottom.add(lable24);
		
		JLabel lable25 = new JLabel("76人");
		lable25.setBounds(5, 2400, 54, 20);
		panelOfBottom.add(lable25);
		
		JLabel lable26 = new JLabel("掘金");
		lable26.setBounds(5, 2500, 54, 20);
		panelOfBottom.add(lable26);
		
		JLabel lable27 = new JLabel("快船");
		lable27.setBounds(5, 2600, 54, 20);
		panelOfBottom.add(lable27);
		
		JLabel lable28 = new JLabel("勇士");
		lable28.setBounds(5, 2700, 54, 20);
		panelOfBottom.add(lable28);
		
		JLabel lable29 = new JLabel("尼克斯");
		lable29.setBounds(5, 2800, 54, 20);
		panelOfBottom.add(lable29);
		
		JLabel lable30 = new JLabel("爵士");
		lable30.setBounds(5, 2900, 54, 20);
		panelOfBottom.add(lable30);
		
		
	}
	public class MouseListen extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			String name = table.getValueAt(r, c).toString();
			try {
				
				PlayerSelectionPanel.scrollPane.setVisible(false);
				PlayerInfoPanel.scrollPane.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
