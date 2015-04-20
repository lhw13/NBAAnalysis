package hotui;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import blservice.BLService;
import presentation.ImageHandle;
import presentation.mainui.MainFrame;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import server.businesslogic.BLController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import vo.PlayerVO;
import vo.TeamVO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class HotRankingPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	Vector columnName1;
	DefaultTableModel model_1 = new DefaultTableModel();
	BLService blservice = BLController.getInstance();
	JLabel labelsocre;
	JLabel labelrebound;
	private JLabel labelassist;
	private JLabel labelsteal;
	private JLabel labelblock;
	private JTable table_1;
	String root="每日";
	String leaf="得分榜";
	public HotRankingPanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		scrollPane = new JScrollPane(panelOfBottom);
		
		JButton button = new JButton("返回");
		button.setBounds(5, 10, 93, 23);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					HotRankingPanel.scrollPane.setVisible(false);
					MainFrame.panel.setVisible(true);
					MainFrame.frame.setTitle("NBA");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		panelOfBottom.add(button);
		JLabel label = new JLabel("每日统计");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(545, 40, 65, 31);
		label.addMouseListener(new Listener1());
		panelOfBottom.add(label);
		
		JLabel label_1 = new JLabel("进步最快");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setBounds(695, 40, 65, 31);
		label_1.addMouseListener(new Listener1());
		panelOfBottom.add(label_1);
		
		JLabel lblNewLabel = new JLabel("赛季热点球队");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 286, 101, 31);
		panelOfBottom.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("赛季统计");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(620, 40, 65, 31);
		lblNewLabel_1.addMouseListener(new Listener1());
		panelOfBottom.add(lblNewLabel_1);
		
		labelsocre = new JLabel("得分榜");
		labelsocre.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelsocre.setBounds(545, 81, 54, 15);
		labelsocre.addMouseListener(new Listener2());
		panelOfBottom.add(labelsocre);
		
		labelrebound = new JLabel("篮板榜");
		labelrebound.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelrebound.setBounds(607, 81, 54, 15);
		labelrebound.addMouseListener(new Listener2());
		panelOfBottom.add(labelrebound);
		
		labelassist = new JLabel("助攻榜");
		labelassist.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelassist.setBounds(671, 81, 54, 15);
		labelassist.addMouseListener(new Listener2());
		panelOfBottom.add(labelassist);
		
		labelsteal = new JLabel("抢断榜");
		labelsteal.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelsteal.setBounds(735, 81, 54, 15);
		labelsteal.addMouseListener(new Listener2());
		panelOfBottom.add(labelsteal);
		
		labelblock = new JLabel("盖帽榜");
		labelblock.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelblock.setBounds(799, 81, 54, 15);
		labelblock.addMouseListener(new Listener2());
		panelOfBottom.add(labelblock);
		
		table_1 = new JTable(model_1);
		table_1.setBounds(545, 116, 385, 121);
		panelOfBottom.add(table_1);
		
		
		
		scrollPane.setBounds(0, 0, 1000, 600);
		add(scrollPane);
		
		String[] names1 = {"","","",""};
		columnName1 = new Vector();
		for(int i=0;i<names1.length;i++) {
			columnName1.add(names1[i]);
		}		
	}
	
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	
	// 保留小数点
	public String handleDecimal(double f) {
		Double result = new Double(f);
		if(!result.isInfinite()&&!result.isNaN())
		return String.format("%.2f", f);
		return result.toString();
	}
	
	public void update1() {
		Vector rowDatas1 = new Vector();
		switch(root) {
		case "每日":			
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getDailyHotPlayerVO("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					rowData1.add(ImageHandle.loadPlayer(playerTemp.getName()));
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getScore());			
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getDailyHotPlayerVO("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					rowData1.add(ImageHandle.loadPlayer(playerTemp.getName()));
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getTotalRebound());					
					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getDailyHotPlayerVO("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					rowData1.add(ImageHandle.loadPlayer(playerTemp.getName()));
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getAssist());									
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<PlayerVO> players4 = blservice.getDailyHotPlayerVO("steal", 5);

				for(int i=0;i<players4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players4.get(i);
					rowData1.add(i+1);
					rowData1.add(ImageHandle.loadPlayer(playerTemp.getName()));
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getSteal());									
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<PlayerVO> players5 = blservice.getDailyHotPlayerVO("blockShot", 5);

				for(int i=0;i<players5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players5.get(i);
					rowData1.add(i+1);
					rowData1.add(ImageHandle.loadPlayer(playerTemp.getName()));
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getBlock());									
					rowDatas1.add(rowData1);
				}
				break;
			}	
				
			break;
		case "赛季":
			
			break;
		case "进步":
			
			break;
		
		}
			model_1.setDataVector(rowDatas1, columnName1);		
			model_1.setColumnCount(table_1.getColumnCount());
			model_1.setRowCount(rowDatas1.size());
			table_1.setModel(model_1);
			int[] width_1={10,120,170,80};
			table_1.setColumnModel(getColumn(table_1, width_1));
			table_1.updateUI();
	}

	public class Listener1 extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			if(label1.getText().startsWith("每日")){
				root = "每日";
			} else if(label1.getText().startsWith("赛季")) {
				root = "赛季";
			} else if(label1.getText().startsWith("进步")) {
				root = "进步";
			}
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			update1();
		}		
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());

		}
	}
	public class Listener2 extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {			
			JLabel label1 =(JLabel) e.getSource();
			switch(label1.getText()) {
			case "得分榜":leaf = "得分榜";break;
			case "篮板榜":leaf = "篮板榜";break;
			case "助攻榜":leaf = "助攻榜";break;
			case "抢断榜":leaf = "抢断榜";break;
			case "盖帽榜":leaf = "盖帽榜";break;
			}
			label1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			update1();
		}
		public void mouseExited(MouseEvent e) {
			JLabel label1 =(JLabel) e.getSource();
			label1.setCursor(Cursor.getDefaultCursor());

		}
	}
}
