package presentation.matchui;

import hotui.HotRankingPanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerRankingPanel;
import presentation.playerui.PlayerRankingPanel.MouseListen;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsRankingFrame;
import presentation.teamsui.TeamsSelectionFrame;
import vo.TeamWithPlayersVO;

import javax.swing.JLabel;

public class MatchDetailInfoPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	MouseListen_1 listener_1 = new MouseListen_1();
	
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	
	public MatchDetailInfoPanel(ImageIcon ii[], DefaultTableModel model1, DefaultTableModel model2, 
			DefaultTableModel model3, DefaultTableModel model4) {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 800));
		panelOfBottom.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 200, 900, 250);
		panelOfBottom.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(model1);
		int[] width={120,30,50,30,30,30,30,30,30,30,30,30,30,30,30};
		table.setColumnModel(getColumn(table, width));
		table.setShowGrid(false);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		scrollPane_1.setViewportView(table);
		
		table.addMouseListener(listener_1);
		table.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e) {  
	        int row=table.rowAtPoint(e.getPoint());  
	        int col=table.columnAtPoint(e.getPoint());  
	        if(col==0){  
	        	table.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				table.setCursor(Cursor.getDefaultCursor());
			}
	    }  }); 
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(50, 475, 900, 250);
		panelOfBottom.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(model2);
		int[] width1={120,30,50,30,30,30,30,30,30,30,30,30,30,30,30};
		table_1.setColumnModel(getColumn(table_1, width1));
		table_1.setShowGrid(false);
		
		DefaultTableCellRenderer tcr_1 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_1.setHorizontalAlignment(JLabel.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr_1);
		
		scrollPane_2.setViewportView(table_1);
		
		table_1.addMouseListener(listener_1);
		table_1.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e) {  
	        int row=table_1.rowAtPoint(e.getPoint());  
	        int col=table_1.columnAtPoint(e.getPoint());  
	        if(col==0){  
	        	table_1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} else {
				table_1.setCursor(Cursor.getDefaultCursor());
			}
	    }  }); 
		
		table_2 = new JTable();
		table_2.setModel(model3);
		int[] width2={40,30,80,50,30,30,30,30};
		table_2.setColumnModel(getColumn(table_2, width2));
		table_2.setShowGrid(false);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(250, 10, 500, 50);
		
		DefaultTableCellRenderer tcr_2 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_2.setHorizontalAlignment(JLabel.CENTER);
		table_2.setDefaultRenderer(Object.class, tcr_2);
		
		scrollPane_3.setViewportView(table_2);
		panelOfBottom.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(model4);
		int[] width3={150,100,150};
		table_3.setColumnModel(getColumn(table_3, width3));
		table_3.setBounds(250, 70, 500, 300);
		table_3.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table_3.setShowGrid(false);
		
		DefaultTableCellRenderer tcr_3 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_3.setHorizontalAlignment(JLabel.CENTER);
		table_3.setDefaultRenderer(Object.class, tcr_3);
		
		panelOfBottom.add(table_3);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(10, 10, 120, 25);
		panelOfBottom.add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(50, 35, 200, 150);
		lblNewLabel.setIcon(ii[0]);
		panelOfBottom.add(lblNewLabel);
		
		lblNewLabel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] strArray = table_2.getValueAt(0, 2).toString().split("-");
				String team1 = strArray[0];
				MatchDetailInfoPanel.scrollPane.setVisible(false);
				if(team1!=null){
					TeamsSelectionFrame.goToTeam(team1);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setCursor(Cursor.getDefaultCursor());
			}});
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(750, 35, 200, 150);
		lblNewLabel_1.setIcon(ii[1]);
		panelOfBottom.add(lblNewLabel_1);
		
		lblNewLabel_1.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] strArray = table_2.getValueAt(0, 2).toString().split("-");
				String team2 = strArray[1];
				MatchDetailInfoPanel.scrollPane.setVisible(false);
				if(team2!=null){
					TeamsSelectionFrame.goToTeam(team2);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setCursor(Cursor.getDefaultCursor());
			}});
		
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int size = MainFrame.backPanels.size();
					Panels temp = MainFrame.backPanels.get(size-1);
					MainFrame.backPanels.remove(size-1);
					switch(temp) {
					case MatchSelectionPanel:
						MatchSelectionPanel.scrollPane.setVisible(true);
						MatchDetailInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("查询比赛");
						MainFrame.currentPanel = Panels.MatchSelectionPanel;
						break;
					case TeamsInfoFrame:
						TeamsInfoFrame.scrollPane.setVisible(true);
						MatchDetailInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("NBA球队信息");
						MainFrame.currentPanel = Panels.TeamsInfoFrame;
						break;
					case PlayerInfoPanel:
						PlayerInfoPanel.scrollPane.setVisible(true);
						MatchDetailInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("NBA球员信息");
						MainFrame.currentPanel = Panels.PlayerInfoPanel;
						break;
					case HotRankingPanel:
						HotRankingPanel.scrollPane.setVisible(true);
						MatchDetailInfoPanel.scrollPane.setVisible(false);
						MainFrame.frame.setTitle("今日快讯");
						MainFrame.currentPanel = Panels.HotRankingPanel;
						break;
						
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	public class MouseListen_1 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			try {
				if(c==0){
					MatchDetailInfoPanel.scrollPane.setVisible(false);
					MainFrame.pip = new PlayerInfoPanel();
					MainFrame.pip.update(table.getValueAt(r,0).toString());
					MainFrame.frame.getContentPane().add(MainFrame.pip.scrollPane);
					MainFrame.pip.scrollPane.setVisible(true);
					MainFrame.backPanels.add(MainFrame.currentPanel);
					MainFrame.currentPanel = Panels.PlayerInfoPanel;
					MainFrame.frame.setTitle("NBA球员信息");
					MainFrame.frame.repaint();//刷新重画 
					MainFrame.frame.validate();//保证重画后的窗口能正常立即显示 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}