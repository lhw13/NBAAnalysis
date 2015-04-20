package hotui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
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
	DefaultTableModel model_1 = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;

		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	BLService blservice = BLController.getInstance();
	JLabel label;
	JLabel label_1;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel labelscore;
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
		label = new JLabel("每日统计");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(545, 40, 65, 31);
		label.addMouseListener(new Listener1());
		panelOfBottom.add(label);
		
		lblNewLabel_1 = new JLabel("赛季统计");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.gray);
		lblNewLabel_1.setBounds(620, 40, 65, 31);
		lblNewLabel_1.addMouseListener(new Listener1());
		panelOfBottom.add(lblNewLabel_1);
		
		label_1 = new JLabel("进步最快");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setForeground(Color.gray);
		label_1.setBounds(695, 40, 65, 31);
		label_1.addMouseListener(new Listener1());
		panelOfBottom.add(label_1);
		
		 lblNewLabel = new JLabel("赛季热点球队");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.gray);
		lblNewLabel.setBounds(770, 40, 101, 31);
		lblNewLabel.addMouseListener(new Listener1());
		panelOfBottom.add(lblNewLabel);
		
		
		
		labelscore = new JLabel("得分榜");
		labelscore.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelscore.setBounds(545, 81, 54, 15);
		labelscore.addMouseListener(new Listener2());
		panelOfBottom.add(labelscore);
		
		labelrebound = new JLabel("篮板榜");
		labelrebound.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelrebound.setForeground(Color.gray);
		labelrebound.setBounds(607, 81, 54, 15);
		labelrebound.addMouseListener(new Listener2());
		panelOfBottom.add(labelrebound);
		
		labelassist = new JLabel("助攻榜");
		labelassist.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelassist.setForeground(Color.gray);
		labelassist.setBounds(671, 81, 54, 15);
		labelassist.addMouseListener(new Listener2());
		panelOfBottom.add(labelassist);
		
		labelsteal = new JLabel("抢断榜");
		labelsteal.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelsteal.setForeground(Color.gray);
		labelsteal.setBounds(735, 81, 54, 15);
		labelsteal.addMouseListener(new Listener2());
		panelOfBottom.add(labelsteal);
		
		labelblock = new JLabel("盖帽榜");
		labelblock.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labelblock.setForeground(Color.gray);
		labelblock.setBounds(799, 81, 54, 15);
		labelblock.addMouseListener(new Listener2());
		panelOfBottom.add(labelblock);
		
		table_1 = new JTable(model_1);
		table_1.setBounds(545, 116, 385, 200);
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
		return String.format("%.1f", f);
		return result.toString();
	}
	
	public void update1() {
		Vector rowDatas1 = new Vector();
		ImageIcon picture;
		switch(root) {
		case "每日":		
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getDailyHotPlayerVO("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
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
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
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
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
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
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
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
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(playerTemp.getBlock());									
					rowDatas1.add(rowData1);
				}
				break;
			}	
				
			break;
		case "赛季":
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getHotPlayerVO("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getScore()
							/playerTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getHotPlayerVO("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));

					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getHotPlayerVO("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getAssist()
							/playerTemp.getAppearance()));	
					
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<PlayerVO> players4 = blservice.getHotPlayerVO("steal", 5);

				for(int i=0;i<players4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players4.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getSteal()
							/playerTemp.getAppearance()));	
					
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<PlayerVO> players5 = blservice.getHotPlayerVO("blockShot", 5);

				for(int i=0;i<players5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players5.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getBlock()/playerTemp.getAppearance()));									
					rowDatas1.add(rowData1);
				}
				break;
			}	
			break;
		case "进步":
			if(columnName1.size()==4)
				columnName1.add("");
			switch (leaf) {
			case "得分榜":
				ArrayList<PlayerVO> players1 = blservice.getBestPromotion("point", 5);
				for(int i=0;i<players1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getScore()
							/playerTemp.getAppearance()));		
					rowData1.add(" "+handleDecimal(playerTemp.getScorePromotion())+" 提升率");
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<PlayerVO> players2 = blservice.getBestPromotion("rebound", 5);
				for(int i=0;i<players2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players2.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));	
					rowData1.add(" "+handleDecimal(playerTemp.getReboundPromotion())+" 提升率");						

					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<PlayerVO> players3 = blservice.getBestPromotion("assist", 5);

				for(int i=0;i<players3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					PlayerVO playerTemp = players3.get(i);
					rowData1.add(i+1);
					picture = ImageHandle.loadPlayer(playerTemp.getName());
					picture.setImage(picture.getImage().getScaledInstance(50, 40,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(playerTemp.getName());
					rowData1.add(handleDecimal((double)playerTemp.getTotalRebound()
							/playerTemp.getAppearance()));	
					rowData1.add(" "+handleDecimal(playerTemp.getAssistPromotion())+" 提升率");						
					rowDatas1.add(rowData1);
				}
				break;
			}
			break;
			
		case "赛季热点球队":
			if(columnName1.size()>4)
				columnName1.remove(4);
			switch (leaf) {
			case "得分榜":
				ArrayList<TeamVO> teams1 = blservice.getHotTeamVO("point", 5);
				for(int i=0;i<teams1.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams1.get(i);			
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(50, 50,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getScore()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "篮板榜":
				ArrayList<TeamVO> teams2 = blservice.getHotTeamVO("rebound", 5);
				for(int i=0;i<teams2.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams2.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(50, 50,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getTotalRebound()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "助攻榜":
				ArrayList<TeamVO> teams3 = blservice.getHotTeamVO("assist", 5);
				for(int i=0;i<teams3.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams3.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(50, 50,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getAssist()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "抢断榜":
				ArrayList<TeamVO> teams4 = blservice.getHotTeamVO("steal", 5);
				for(int i=0;i<teams4.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams4.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(50, 50,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getSteal()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			case "盖帽榜":
				ArrayList<TeamVO> teams5 = blservice.getHotTeamVO("blockShot", 5);
				for(int i=0;i<teams5.size()&&i<5;i++) {
					Vector rowData1 = new Vector();
					TeamVO teamTemp = teams5.get(i);	
					rowData1.add(i+1);
					picture = ImageHandle.loadTeam(teamTemp.getAbbreviation());
					picture.setImage(picture.getImage().getScaledInstance(50, 50,
							Image.SCALE_DEFAULT));
					rowData1.add(picture);
					
					rowData1.add(MainFrame.psp.translate(teamTemp.getAbbreviation()));
					rowData1.add(handleDecimal((double)teamTemp.getBlock()
							/teamTemp.getAppearance()));
					rowDatas1.add(rowData1);
				}
				break;
			}	
			break;
		}
			model_1.setDataVector(rowDatas1, columnName1);		
			model_1.setColumnCount(table_1.getColumnCount());
			model_1.setRowCount(rowDatas1.size());
			table_1.setModel(model_1);
			table_1.setRowHeight(40);
			int[] width_1={10,120,170,80};
			int[] width_11={10,100,130,40,100};
			if(root.equals("进步")) 
				table_1.setColumnModel(getColumn(table_1, width_11));
			else
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
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.WHITE);
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.gray);
				
			} else if(label1.getText().startsWith("赛季统计")) {
				root = "赛季";
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.gray);				
				lblNewLabel_1.setForeground(Color.WHITE);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.gray);
				
			} else if(label1.getText().startsWith("进步")) {
				root = "进步";
				labelsteal.setVisible(false);
				labelblock.setVisible(false);
				
				label.setForeground(Color.gray);
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.WHITE);
				lblNewLabel.setForeground(Color.gray);								
				
			} else if (label1.getText().equals("赛季热点球队")) {
				root = "赛季热点球队";
				labelsteal.setVisible(true);
				labelblock.setVisible(true);
				
				label.setForeground(Color.gray);				
				lblNewLabel_1.setForeground(Color.gray);
				label_1.setForeground(Color.gray);
				lblNewLabel.setForeground(Color.WHITE);
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
			case "得分榜":
				leaf = "得分榜";
				labelscore.setForeground(Color.white);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray);
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			case "篮板榜":
				leaf = "篮板榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.white);
				labelassist.setForeground(Color.gray);
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			case "助攻榜":
				leaf = "助攻榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.white );
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.gray);
				break;
			
			case "抢断榜":
				leaf = "抢断榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray );
				labelsteal.setForeground(Color.white);
				labelblock.setForeground(Color.gray);
				break;
			case "盖帽榜":
				leaf = "盖帽榜";
				labelscore.setForeground(Color.gray);
				labelrebound.setForeground(Color.gray);
				labelassist.setForeground(Color.gray );
				labelsteal.setForeground(Color.gray);
				labelblock.setForeground(Color.white);
				break;
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
