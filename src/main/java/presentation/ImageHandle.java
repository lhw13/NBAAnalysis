package presentation;

import javax.swing.ImageIcon;

public class ImageHandle {
	public ImageHandle() {

	}

	public static ImageIcon loadPlayer(String name) {
//		name=name.replaceAll("\\d", "");
		ImageIcon PLAYER = new ImageIcon("conf/nba/players/portrait/" + name
				+ ".png");
		return PLAYER;
	}

	public static ImageIcon loadPlayerAct(String name) {
		name=name.replaceAll("\\d", "");
		ImageIcon PLAYER = new ImageIcon("conf/nba/players/action/" + name + ".png");
		return PLAYER;
	}
	
	public static ImageIcon loadTeam(String name) {
		ImageIcon TEAM = new ImageIcon("conf/pictures/" + name + ".png");
		return TEAM;
	}
}
