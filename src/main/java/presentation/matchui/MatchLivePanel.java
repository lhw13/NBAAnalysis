package presentation.matchui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;

public class MatchLivePanel extends JPanel{

	public static JScrollPane scrollPane;
	
	JPanel panelOfBottom = new JPanel();
	
	JButton return_bt;

	public MatchLivePanel(){
		
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		//scrollPane============================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setLayout(null);
		
		//返回===================================================
		return_bt = new JButton("返回");
		return_bt.setBounds(30, 20, 100, 30);
		return_bt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				
				MatchLivePanel.scrollPane.setVisible(false);
				MatchSelectionPanel.scrollPane.setVisible(true);
				MainFrame.frame.setTitle("NBA比赛查询");
				MainFrame.currentPanel = Panels.MatchSelectionPanel;
			}
		});
		panelOfBottom.add(return_bt);
		
		
	}

}
