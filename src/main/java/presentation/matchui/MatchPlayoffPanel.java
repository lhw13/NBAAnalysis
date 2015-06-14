package presentation.matchui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import server.businesslogic.BLController;
import vo.PlayOffListVO;
import vo.PlayoffVO;
import blservice.BLService;

import javax.swing.JLabel;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerComparePanel;
import presentation.playerui.PlayerInfoPanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MatchPlayoffPanel extends JPanel implements MouseListener{
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();

	public static JScrollPane scrollPane;
	
	JLabel label_background;
	JLabel big_l;
	JLabel big_r;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JLabel l8;
	JLabel r1;
	JLabel r2;
	JLabel r3;
	JLabel r4;
	JLabel r5;
	JLabel r6;
	JLabel r7;
	JLabel r8;
	
	JLabel ll1;
	JLabel ll2;
	JLabel ll3;
	JLabel ll4;
	JLabel rr1;
	JLabel rr2;
	JLabel rr3;
	JLabel rr4;
	
	JLabel lll1;
	JLabel lll2;
	JLabel rrr1;
	JLabel rrr2;
	
	JLabel c1;
	JLabel c2;
	JButton button;
	
	private JTable table;
	
	DefaultTableModel model = new DefaultTableModel();
	
	Vector columnName = new Vector();
	
	public final static int HIGHT = 80;
	public final static int WIDTH = 100;
	
	public MatchPlayoffPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
//panel===========================================================
		panelOfBottom.setLayout(null);
		panelOfBottom.addMouseListener(this);
//label==================================================================
		 l1 = new JLabel("l1");
		 l1.setBounds(172, 55, WIDTH, HIGHT);
		 
		 l2 = new JLabel("l2");
		 l2.setLocation(172, 108);
		 l2.setSize(WIDTH, HIGHT);
		 l3 = new JLabel("l3");
		 l3.setSize(WIDTH, HIGHT);
		 l3.setLocation(172, 180);
		 l4 = new JLabel("l4");
		 l4.setSize(WIDTH, HIGHT);
		 l4.setLocation(172, 233);
		 l5 = new JLabel("l5");
		 l5.setSize(WIDTH, HIGHT);
		 l5.setLocation(172, 307);
		 l6 = new JLabel("l6");
		 l6.setSize(WIDTH, HIGHT);
		 l6.setLocation(172, 351);
		 l7 = new JLabel("l7");
		 l7.setSize(WIDTH, HIGHT);
		 l7.setLocation(172, 434);
		 l8 = new JLabel("l8");
		 l8.setSize(WIDTH, HIGHT);
		 l8.setLocation(172, 480);
		 r1 = new JLabel("r1");
		 r1.setSize(WIDTH, HIGHT);
		 r1.setLocation(732, 55);
		 r2 = new JLabel("r2");
		 r2.setLocation(732, 108);
		 r2.setSize(WIDTH, HIGHT);
		 r3 = new JLabel("r3");
		 r3.setLocation(732, 180);
		 r3.setSize(WIDTH, HIGHT);
		 r4 = new JLabel("r4");
		 r4.setLocation(732, 233);
		 r4.setSize(WIDTH, HIGHT);
		 r5 = new JLabel("r5");
		 r5.setLocation(732, 307);
		 r5.setSize(WIDTH, HIGHT);
		 r6 = new JLabel("r6");
		 r6.setLocation(732, 351);
		 r6.setSize(WIDTH, HIGHT);
		 r7 = new JLabel("r7");
		 r7.setLocation(732, 434);
		 r7.setSize(WIDTH, HIGHT);
		 r8 = new JLabel("r8");
		 r8.setLocation(732, 480);
		 r8.setSize(WIDTH, HIGHT);
		
		 ll1 = new JLabel("ll1");
		 ll1.setSize(WIDTH, HIGHT);
		 ll1.setLocation(302, 130);
		 ll2 = new JLabel("ll2");
		 ll2.setSize(WIDTH, HIGHT);
		 ll2.setLocation(302, 180);
		 ll3 = new JLabel("ll3");
		 ll3.setSize(WIDTH, HIGHT);
		 ll3.setLocation(302, 380);
		 ll4 = new JLabel("ll4");
		 ll4.setSize(WIDTH, HIGHT);
		 ll4.setLocation(302, 433);
		 rr1 = new JLabel("rr1");
		 rr1.setSize(WIDTH, HIGHT);
		 rr1.setLocation(618, 130);
		 rr2 = new JLabel("rr2");
		 rr2.setSize(WIDTH, HIGHT);
		 rr2.setLocation(618, 180);
		 rr3 = new JLabel("rr3");
		 rr3.setSize(WIDTH, HIGHT);
		 rr3.setLocation(618, 380);
		 rr4 = new JLabel("rr4");
		 rr4.setSize(WIDTH, HIGHT);
		 rr4.setLocation(618, 433);
		
		 lll1 = new JLabel("lll1");
		 lll1.setSize(WIDTH, HIGHT);
		 lll1.setLocation(355, 253);
		 lll2 = new JLabel("lll2");
		 lll2.setSize(WIDTH, HIGHT);
		 lll2.setLocation(355, 313);
		 rrr1 = new JLabel("rrr1");
		 rrr1.setSize(WIDTH, HIGHT);
		 rrr1.setLocation(570, 253);
		 rrr2 = new JLabel("rrr2");
		 rrr2.setSize(WIDTH, HIGHT);
		 rrr2.setLocation(570, 313);
		
		 c1 = new JLabel("c1");
		 c1.setSize(WIDTH, HIGHT);
		 c1.setLocation(460, 188);
		 c2 = new JLabel("c2");
		 c2.setSize(WIDTH, HIGHT);
		 c2.setLocation(460, 250);
		
		label_background = new JLabel();
		label_background.setBounds(159, 74, 690, 493);
		panelOfBottom.add(label_background);
		panelOfBottom.add(l1);
		panelOfBottom.add(l2);
		panelOfBottom.add(l3);
		panelOfBottom.add(l4);
		panelOfBottom.add(l5);
		panelOfBottom.add(l6);
		panelOfBottom.add(l7);
		panelOfBottom.add(l8);
		panelOfBottom.add(r1);
		panelOfBottom.add(r2);
		panelOfBottom.add(r3);
		panelOfBottom.add(r4);
		panelOfBottom.add(r5);
		panelOfBottom.add(r6);
		panelOfBottom.add(r7);
		panelOfBottom.add(r8);
		panelOfBottom.add(ll1);
		panelOfBottom.add(ll2);
		panelOfBottom.add(ll3);
		panelOfBottom.add(ll4);
		panelOfBottom.add(rr1);
		panelOfBottom.add(rr2);
		panelOfBottom.add(rr3);
		panelOfBottom.add(rr4);
		panelOfBottom.add(lll1);
		panelOfBottom.add(lll2);
		panelOfBottom.add(rrr1);
		panelOfBottom.add(rrr2);
		panelOfBottom.add(c1);
		panelOfBottom.add(c2);
		
//ImageIcon===========================================================
		ImageIcon backgroundIcon = new ImageIcon("conf/pictures/playoffs.png");
		backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(690, 493,
				Image.SCALE_DEFAULT));
		label_background.setIcon(backgroundIcon);		
//button========================================================	
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);

				MatchSelectionPanel.scrollPane.setVisible(true);
				MatchPlayoffPanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA比赛查询");
				MainFrame.currentPanel = Panels.MatchSelectionPanel;
			}
		});
		panelOfBottom.add(button);
		

		
//Vector column===========================================================
		
		String[] cname = new String[] {"","","",""};
		for(int i=0;i<cname.length;i++) {
			columnName.add(cname[i]);
		}	
		
//table=============================================
		table = new JTable(model);
		table.setBounds(369, 87, 262, 55);
		table.setShowGrid(false);
		panelOfBottom.add(table);
		
		big_l = new JLabel("p1");
		big_l.setBounds(349, 10, 71, 67);
		panelOfBottom.add(big_l);
		
		big_r = new JLabel("p2");
		big_r.setBounds(563, 10, 79, 67);
		panelOfBottom.add(big_r);
		updateTable();
	}
	
	public void updateTable() {
		Vector rowDatas = new Vector();
		Vector rowData = new Vector();
		rowData.add("100-89");
		rowData.add("108-98");
		rowData.add("108-98");
		rowData.add("108-98");
		rowDatas.add(rowData);
		
		rowData = new Vector();
		rowData.add("100-89");
		rowData.add("108-98");
		
		rowDatas.add(rowData);

		model.setDataVector(rowDatas, columnName);		
		model.setColumnCount(table.getColumnCount());
		model.setRowCount(rowDatas.size());
		table.setFont(new Font("微软雅黑", Font.BOLD, 14));
		table.setModel(model);
		
		table.updateUI();
		
		update();
	}

	public void update() {
		ImageIcon lp1 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l1.setIcon(lp1);	
		
		ImageIcon lp2 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l2.setIcon(lp2);
		
		ImageIcon lp3 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l3.setIcon(lp3);
		
		ImageIcon lp4 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l4.setIcon(lp4);
		
		ImageIcon lp5 = new ImageIcon("conf/smallTeam/ATL.png");
	
		l5.setIcon(lp5);
		
		ImageIcon lp6 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l6.setIcon(lp6);
		
		ImageIcon lp7 = new ImageIcon("conf/smallTeam/ATL.png");
		
		l7.setIcon(lp7);
		
		ImageIcon lp8 = new ImageIcon("conf/smallTeam/ATL.png");
	
		l8.setIcon(lp8);
		
		ImageIcon llp1 = new ImageIcon("conf/smallTeam/ATL.png");
		
		ll1.setIcon(llp1);	
		
		ImageIcon llp2 = new ImageIcon("conf/smallTeam/ATL.png");
		
		ll2.setIcon(llp2);
		
		ImageIcon llp3 = new ImageIcon("conf/smallTeam/ATL.png");
		
		ll3.setIcon(llp3);
		
		ImageIcon llp4 = new ImageIcon("conf/smallTeam/ATL.png");
		llp4.setImage(llp4.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		ll4.setIcon(llp4);
		
		ImageIcon lllp1 = new ImageIcon("conf/smallTeam/ATL.png");
		lllp1.setImage(lllp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		lll1.setIcon(lllp1);
		
		ImageIcon lllp2 = new ImageIcon("conf/smallTeam/ATL.png");
		lllp2.setImage(lllp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		lll2.setIcon(lllp2);
		
		ImageIcon cp1 = new ImageIcon("conf/smallTeam/ATL.png");
		cp1.setImage(cp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		c1.setIcon(cp1);
		
		ImageIcon cp2 = new ImageIcon("conf/smallTeam/ATL.png");
		cp2.setImage(cp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		c2.setIcon(cp2);
		
		ImageIcon rp1 = new ImageIcon("conf/smallTeam/ATL.png");
		rp1.setImage(rp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r1.setIcon(rp1);	
		
		ImageIcon rp2 = new ImageIcon("conf/smallTeam/ATL.png");
		rp2.setImage(rp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r2.setIcon(rp2);
		
		ImageIcon rp3 = new ImageIcon("conf/smallTeam/ATL.png");
		rp3.setImage(rp3.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r3.setIcon(rp3);
		
		ImageIcon rp4 = new ImageIcon("conf/smallTeam/ATL.png");
		rp4.setImage(rp4.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r4.setIcon(rp4);
		
		ImageIcon rp5 = new ImageIcon("conf/smallTeam/ATL.png");
		rp5.setImage(rp5.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r5.setIcon(rp5);
		
		ImageIcon rp6 = new ImageIcon("conf/smallTeam/ATL.png");
		rp6.setImage(rp6.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r6.setIcon(rp6);
		
		ImageIcon rp7 = new ImageIcon("conf/smallTeam/ATL.png");
		rp7.setImage(rp7.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r7.setIcon(rp7);
		
		ImageIcon rp8 = new ImageIcon("conf/smallTeam/ATL.png");
		rp8.setImage(rp8.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		r8.setIcon(rp8);
		
		ImageIcon rrp1 = new ImageIcon("conf/smallTeam/ATL.png");
		rrp1.setImage(rrp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rr1.setIcon(rrp1);
		
		ImageIcon rrp2 = new ImageIcon("conf/smallTeam/ATL.png");
		rrp2.setImage(rrp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rr2.setIcon(rrp2);
		
		ImageIcon rrp3 = new ImageIcon("conf/smallTeam/ATL.png");
		rrp3.setImage(rrp3.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rr3.setIcon(rrp3);
		
		ImageIcon rrp4 = new ImageIcon("conf/smallTeam/ATL.png");
		rrp4.setImage(rrp4.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rr4.setIcon(rrp4);
		
		ImageIcon rrrp1 = new ImageIcon("conf/smallTeam/ATL.png");
		rrrp1.setImage(rrrp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rrr1.setIcon(rrrp1);
		
		ImageIcon rrrp2 = new ImageIcon("conf/smallTeam/ATL.png");
		rrrp2.setImage(rrrp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		rrr2.setIcon(rrrp2);
		PlayOffListVO list = blservice.getPlayOff();
		ArrayList<PlayoffVO> playoffE = list.getPlayOffE();
		ArrayList<PlayoffVO> playoffW = list.getPlayOffW();
		ArrayList<PlayoffVO> playoffFinal = list.getFinals();
		System.out.println("E===================");
		for(int i=0;i<playoffE.size();i++) {
			PlayoffVO vo = playoffE.get(i);
			System.out.println(vo.getAbr1()+":"+vo.getAbr2()+"  "+vo.getWin1()+":"+vo.getWin2());
		}
		System.out.println("w===================");
		for(int i=0;i<playoffW.size();i++) {
			PlayoffVO vo = playoffW.get(i);
			System.out.println(vo.getAbr1()+":"+vo.getAbr2()+"  "+vo.getWin1()+":"+vo.getWin2());
		}
		System.out.println("f===================");
		
			PlayoffVO vo = playoffW.get(0);
			System.out.println(vo.getAbr1()+":"+vo.getAbr2()+"  "+vo.getWin1()+":"+vo.getWin2());
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+","+e.getY());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
