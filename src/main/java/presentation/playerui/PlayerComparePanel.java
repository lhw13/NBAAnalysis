package presentation.playerui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.ImageHandle;

public class PlayerComparePanel extends JPanel {
	JPanel panelOfBottom = new JPanel();
	
	public static JScrollPane scrollPane;
	
	JLabel labelOfPhoto1;
	JLabel labelOfPhoto2;
	JLabel labelOfPhoto3;
	JLabel labelOfPhoto4;
	
	ImageIcon picture1;
	ImageIcon picture2;
	ImageIcon picture3;
	ImageIcon picture4;
	
	String pname1="NBA";
	String tname1;
	String pname2="NBA";
	String tname2="";
	
	public PlayerComparePanel() {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		//scrollPane===================================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		//panel===========================================================
		panelOfBottom.setLayout(null);
		
		//label =======================================================
		labelOfPhoto1 = new JLabel("photo1");
		labelOfPhoto1.setBounds(209, 31, 180, 145);
		panelOfBottom.add(labelOfPhoto1);
		
		labelOfPhoto2 = new JLabel("photo2");
		labelOfPhoto2.setBounds(53, 79, 100, 100);
		panelOfBottom.add(labelOfPhoto2);
		
		labelOfPhoto3 = new JLabel("photo3");
		labelOfPhoto3.setBounds(514, 31, 180, 145);
		panelOfBottom.add(labelOfPhoto3);
		
		labelOfPhoto4 = new JLabel("p4");
		labelOfPhoto4.setBounds(754, 76, 100, 100);
		panelOfBottom.add(labelOfPhoto4);
		
		//Icon========================================================
		picture3 = ImageHandle.loadTeam(pname2);
		picture3.setImage(picture3.getImage().getScaledInstance(180, 180,
				Image.SCALE_DEFAULT));
		labelOfPhoto3.setIcon(picture3);
	}
	
	//update left player info
	public void update1(String playerName1,String teamName1) {
		pname1 = playerName1;
		tname1 = teamName1;
		
		picture1 = ImageHandle.loadPlayer(playerName1);
		picture2 = ImageHandle.loadTeam(teamName1);
		
		picture1.setImage(picture1.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture2.setImage(picture2.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));
		
		labelOfPhoto1.setIcon(picture1);
		labelOfPhoto2.setIcon(picture2);
	}
	
	//update right player info
	public void update2(String playerName2,String teamName2) {
		pname2 = playerName2;
		tname2 = teamName2;
		
		picture3 = ImageHandle.loadPlayer(pname2);
		picture4 = ImageHandle.loadTeam(tname2);
		
		picture3.setImage(picture3.getImage().getScaledInstance(180, 145,
				Image.SCALE_DEFAULT));
		picture4.setImage(picture4.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));
		
		labelOfPhoto3.setIcon(picture3);
		labelOfPhoto4.setIcon(picture4);
	}
	
	
}
