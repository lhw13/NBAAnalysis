package presentation.playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import server.businesslogic.BLController;
import blservice.BLService;

public class PlayerAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();

	JButton button;
	public static JScrollPane scrollPane;
	
	public PlayerAnalysePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
//panel===========================================================
		panelOfBottom.setLayout(null);
		
//button===========================================================
		button = new JButton("返回");
		button.setBounds(30, 21, 111, 26);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);

				PlayerInfoPanel.scrollPane.setVisible(true);
				PlayerAnalysePanel.scrollPane.setVisible(false);
				MainFrame.frame.setTitle("NBA球员信息");
				MainFrame.currentPanel = Panels.PlayerInfoPanel;
			}
		});
		panelOfBottom.add(button);
	}

}
