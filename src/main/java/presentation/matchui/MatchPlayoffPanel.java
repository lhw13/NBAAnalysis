package presentation.matchui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import server.businesslogic.BLController;
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
		 r2 = new JLabel("r2");
		 r3 = new JLabel("r3");
		 r4 = new JLabel("r4");
		 r5 = new JLabel("r5");
		 r6 = new JLabel("r6");
		 r7 = new JLabel("r7");
		 r8 = new JLabel("r8");
		
		 ll1 = new JLabel("ll1");
		 ll2 = new JLabel("ll2");
		 ll3 = new JLabel("ll3");
		 ll4 = new JLabel("ll4");
		 rr1 = new JLabel("rr1");
		 rr2 = new JLabel("rr2");
		 rr3 = new JLabel("rr3");
		 rr4 = new JLabel("rr4");
		
		 lll1 = new JLabel("lll1");
		 lll2 = new JLabel("lll2");
		 rrr1 = new JLabel("rrr1");
		 rrr2 = new JLabel("rrr2");
		
		 c1 = new JLabel("c1");
		 c2 = new JLabel("c2");
		
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
		ImageIcon lp1 = new ImageIcon("conf/pictures/ATL.png");
		lp1.setImage(lp1.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l1.setIcon(lp1);	
		
		ImageIcon lp2 = new ImageIcon("conf/pictures/ATL.png");
		lp2.setImage(lp2.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l2.setIcon(lp2);
		
		ImageIcon lp3 = new ImageIcon("conf/pictures/ATL.png");
		lp3.setImage(lp3.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l3.setIcon(lp3);
		
		ImageIcon lp4 = new ImageIcon("conf/pictures/ATL.png");
		lp4.setImage(lp4.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l4.setIcon(lp4);
		
		ImageIcon lp5 = new ImageIcon("conf/pictures/ATL.png");
		lp5.setImage(lp5.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l5.setIcon(lp5);
		
		ImageIcon lp6 = new ImageIcon("conf/pictures/ATL.png");
		lp6.setImage(lp6.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l6.setIcon(lp6);
		
		ImageIcon lp7 = new ImageIcon("conf/pictures/ATL.png");
		lp7.setImage(lp7.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l7.setIcon(lp7);
		
		ImageIcon lp8 = new ImageIcon("conf/pictures/ATL.png");
		lp8.setImage(lp8.getImage().getScaledInstance(HIGHT, HIGHT,
				Image.SCALE_DEFAULT));
		l8.setIcon(lp8);
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
