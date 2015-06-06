package presentation.playerui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import server.businesslogic.BLController;
import blservice.BLService;

public class PlayerAnalysePanel extends JPanel {
	BLService blservice = BLController.getInstance();
	
	JPanel panelOfBottom = new JPanel();

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
		
	}

}
