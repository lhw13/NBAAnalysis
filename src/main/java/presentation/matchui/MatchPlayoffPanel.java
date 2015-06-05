package presentation.matchui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MatchPlayoffPanel extends JPanel {
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();

	public static JScrollPane scrollPane;
	
	JLabel label_background;
	
	JButton button;
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
		
		label_background = new JLabel("New label");
		label_background.setBounds(152, 42, 717, 525);
		panelOfBottom.add(label_background);
		
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
		
//ImageIcon===========================================================
		ImageIcon backgroundIcon = new ImageIcon("conf/pictures/playoffs.png");
		backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(717, 525,
				Image.SCALE_DEFAULT));
		label_background.setIcon(backgroundIcon);
	}
}
