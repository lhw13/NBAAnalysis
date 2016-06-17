package presentation.matchui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

public class MatchPlayoffPanel extends JPanel{
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();

	public static JScrollPane scrollPane;
	
	JLabel label_background;
	JLabel big_l;
	JLabel big_r;
	
	JLabel label_l[];
	JLabel label_r[];
	JLabel label_c[];

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
		
//label==================================================================
		 label_l = new JLabel[14];
		 for(int i=0;i<label_l.length;i++) {
			 label_l[i] = new JLabel();
			 label_l[i].setSize(WIDTH, HIGHT);
			 label_l[i].addMouseListener(new LabelListener1(i));
			 panelOfBottom.add(label_l[i]);
			 
		 }
		 label_r = new JLabel[14];
		 for(int i=0;i<label_r.length;i++) {
			 label_r[i] = new JLabel();
			 label_r[i].setSize(WIDTH, HIGHT);
			 label_r[i].addMouseListener(new LabelListener2(i));
			 panelOfBottom.add(label_r[i]);
			 
		 }
		 label_c = new JLabel[2];
		 for(int i=0;i<label_c.length;i++) {
			 label_c[i] = new JLabel();
			 label_c[i].setSize(180, 150);
			 label_c[i].setFont(new Font("黑体", Font.PLAIN, 20));
			 label_c[i].addMouseListener(new LabelListener3());
			 panelOfBottom.add(label_c[i]);
			 
		 }
		 label_l[0].setLocation(172, 55);
		 label_l[1].setLocation(172, 108);
		 label_l[2].setLocation(172, 180);
		 label_l[3].setLocation(172, 233);
		 label_l[4].setLocation(172, 307);
		 label_l[5].setLocation(172, 351);
		 label_l[6].setLocation(172, 434);
		 label_l[7].setLocation(172, 480);
		 label_l[8].setLocation(302, 130);
		 label_l[9].setLocation(302, 180);
		 label_l[10].setLocation(302, 380);
		 label_l[11].setLocation(302, 433);
		 label_l[12].setLocation(355, 253);
		 label_l[13].setLocation(355, 313);
		 
		 label_r[0].setLocation(732, 55);
		 label_r[1].setLocation(732, 108);
		 label_r[2].setLocation(732, 180);
		 label_r[3].setLocation(732, 233);
		 label_r[4].setLocation(732, 307);
		 label_r[5].setLocation(732, 351);
		 label_r[6].setLocation(732, 434);
		 label_r[7].setLocation(732, 480);
		 label_r[8].setLocation(618, 130);
		 label_r[9].setLocation(618, 180);
		 label_r[10].setLocation(618, 380);
		 label_r[11].setLocation(618, 433);
		 label_r[12].setLocation(570, 253);
		 label_r[13].setLocation(570, 313);
		
		 label_c[0].setLocation(420, 188);
		 label_c[1].setLocation(420, 340);
		
		label_background = new JLabel();
		label_background.setBounds(159, 74, 690, 493);
		panelOfBottom.add(label_background);
		
		
		
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
		table.setBounds(369, 93, 262, 55);
		table.setShowGrid(false);
		panelOfBottom.add(table);
		
		big_l = new JLabel("p1");
		big_l.setBounds(349, 0, 140, 100);
		big_l.setFont(new Font("黑体", Font.PLAIN, 36));
		panelOfBottom.add(big_l);
		
		big_r = new JLabel("p2");
		big_r.setBounds(563, 0, 140,100);
		big_r.setFont(new Font("黑体", Font.PLAIN, 36));
		panelOfBottom.add(big_r);
	}
	
	public void updateTable(PlayoffVO vo) {
		
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
		PlayOffListVO list = blservice.getPlayOff();
		ArrayList<PlayoffVO> playoffE = list.getPlayOffE();
		ArrayList<PlayoffVO> playoffW = list.getPlayOffW();
		ArrayList<PlayoffVO> playoffFinal = list.getFinals();
		for(int i=0,j=0;i<playoffW.size();i++,j+=2) {
			PlayoffVO vo = playoffW.get(i);
			label_l[j].setText(Integer.toString(vo.getWin1()));
			label_l[j+1].setText(Integer.toString(vo.getWin2()));
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(50, 50,
					Image.SCALE_DEFAULT));
			label_l[j].setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png"); 
			icon.setImage(icon.getImage().getScaledInstance(50, 50,
					Image.SCALE_DEFAULT));
			label_l[j+1].setIcon(icon);
		}
		for(int i=0,j=0;i<playoffE.size();i++,j+=2) {
			PlayoffVO vo = playoffE.get(i);
			label_r[j].setText(Integer.toString(vo.getWin1()));
			label_r[j+1].setText(Integer.toString(vo.getWin2()));
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(50, 50,
					Image.SCALE_DEFAULT));
			label_r[j].setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png");
			icon.setImage(icon.getImage().getScaledInstance(50, 50,
					Image.SCALE_DEFAULT));
			label_r[j+1].setIcon(icon);
		}
		for(int i=0,j=0;i<playoffFinal.size();i++,j+=2) {
			PlayoffVO vo = playoffFinal.get(i);
			label_c[j].setText(Integer.toString(vo.getWin1()));
			label_c[j+1].setText(Integer.toString(vo.getWin2()));
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(150, 150,
					Image.SCALE_DEFAULT));
			label_c[j].setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(150, 150,
					Image.SCALE_DEFAULT));
			label_c[j+1].setIcon(icon);
		}
	}
	
	public class LabelListener1 extends MouseAdapter {
		int index=0;
		PlayOffListVO list = blservice.getPlayOff();
		ArrayList<PlayoffVO> playoffW = list.getPlayOffW();
		PlayoffVO vo;
		public LabelListener1() {}
		public LabelListener1(int index) {
			this.index = index;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {	
			

			int i = index/2;
			vo = playoffW.get(i);
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_l.setText(Integer.toString(vo.getWin1()));
			big_l.setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png");
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_r.setText(Integer.toString(vo.getWin2()));
			big_r.setIcon(icon);
			updateTable(vo);
		}
		
		public void mouseExited(MouseEvent e) { 
			
			

		}
	}
	
	public class LabelListener2 extends MouseAdapter {
		int index=0;
		PlayOffListVO list = blservice.getPlayOff();
		ArrayList<PlayoffVO> playoffE = list.getPlayOffE();
		PlayoffVO vo;
		public LabelListener2() {}
		public LabelListener2(int index) {
			this.index = index;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {	
			

			int i = index/2;
			vo = playoffE.get(i);
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_l.setText(Integer.toString(vo.getWin1()));
			big_l.setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png");
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_r.setText(Integer.toString(vo.getWin2()));
			big_r.setIcon(icon);
			updateTable(vo);
		}
		
		public void mouseExited(MouseEvent e) {
		}
	}
	
	public class LabelListener3 extends MouseAdapter {
		int index=0;
		PlayOffListVO list = blservice.getPlayOff();
		ArrayList<PlayoffVO> finals = list.getFinals();
		PlayoffVO vo;
		public LabelListener3() {}
		public LabelListener3(int index) {
			this.index = index;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {	
			vo = finals.get(0);
			ImageIcon icon = new ImageIcon("conf/pictures/"+vo.getAbr1()+".png");	
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_l.setText(Integer.toString(vo.getWin1()));
			big_l.setIcon(icon);
			icon = new ImageIcon("conf/pictures/"+vo.getAbr2()+".png");
			icon.setImage(icon.getImage().getScaledInstance(100, 100,
					Image.SCALE_DEFAULT));
			big_r.setText(Integer.toString(vo.getWin2()));
			big_r.setIcon(icon);
			updateTable(vo);
		}
		
		public void mouseExited(MouseEvent e) {
		}
	}
}
