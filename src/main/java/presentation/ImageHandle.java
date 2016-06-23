package presentation;

import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class ImageHandle {
	public ImageHandle() {

	}

	public static ImageIcon loadPlayer(String name) {
		name=name.replaceAll("\\d", "");
		ImageIcon PLAYER = new ImageIcon("conf/nba/players/portrait/" + name
				+ ".png");
		if(PLAYER.getImageLoadStatus() == MediaTracker.ERRORED) {
			return new ImageIcon("conf/pictures/NBA.png");
		}
		
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
