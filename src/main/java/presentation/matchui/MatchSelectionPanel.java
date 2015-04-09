package presentation.matchui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.mainui.MainFrame;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerSelectionPanel;
import presentation.playerui.PlayerInfoPanel.MouseListen;
import presentation.teamsui.TeamsRankingFrame;

import javax.swing.JButton;

public class MatchSelectionPanel extends JPanel {
	public static JScrollPane scrollPane;
	public static JTable table;
	
	private JPanel panelOfBottom = new JPanel();
	private JComboBox<String> comboBox;
	private JComboBox<Integer> comboBox_1;
	private JScrollPane scrollPane_1;
	
	MouseListen listener = new MouseListen();
	
	public MatchSelectionPanel(DefaultTableModel model) {
		this.setBounds(0, 100, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 600));
		panelOfBottom.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(200, 50, 100, 30);
		comboBox.addItem("12-13");
		comboBox.addItem("13-14");
		comboBox.addItem("14-15");
		comboBox.setSelectedItem(MainFrame.season);
		panelOfBottom.add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				switch(index){
				case 0:
					MainFrame.season="12-13";
					MainFrame.searchTheMatch();
					break;
				case 1:
					MainFrame.season="13-14";
					MainFrame.searchTheMatch();
					break;
				case 2:
					MainFrame.season="14-15";
					MainFrame.searchTheMatch();
					break;
				}
			}
			
		});
		
		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.addItem(1);
		comboBox_1.addItem(2);
		comboBox_1.addItem(3);
		comboBox_1.addItem(4);
		comboBox_1.addItem(10);
		comboBox_1.addItem(11);
		comboBox_1.addItem(12);
		comboBox_1.setSelectedItem(MainFrame.date);
		comboBox_1.setBounds(350, 50, 100, 30);
		panelOfBottom.add(comboBox_1);
		
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_1.getSelectedIndex();
				switch(index){
				case 0:
					MainFrame.date=1;
					MainFrame.searchTheMatch();
					break;
				case 1:
					MainFrame.date=2;
					MainFrame.searchTheMatch();
					break;
				case 2:
					MainFrame.date=3;
					MainFrame.searchTheMatch();
					break;
				case 3:
					MainFrame.date=4;
					MainFrame.searchTheMatch();
					break;
				case 4:
					MainFrame.date=10;
					MainFrame.searchTheMatch();
					break;
				case 5:
					MainFrame.date=11;
					MainFrame.searchTheMatch();
					break;
				case 6:
					MainFrame.date=12;
					MainFrame.searchTheMatch();
					break;
				}
			}
			
		});
		
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(listener);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 100, 700, 400);
		panelOfBottom.add(scrollPane_1);
		
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(50, 50, 100, 30);
		panelOfBottom.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MatchSelectionPanel.scrollPane.setVisible(false);
				MainFrame.panel.setVisible(true);
				MainFrame.frame.setTitle("NBA");
			}
			
		});
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
					setMatchInfo();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void setMatchInfo(){
		MatchSelectionPanel.scrollPane.setVisible(false);
		
	}
}
