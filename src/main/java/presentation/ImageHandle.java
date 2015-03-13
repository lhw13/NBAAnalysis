package presentation;

import javax.swing.ImageIcon;

public class ImageHandle {
	public ImageHandle () {
		
	}
	
	
	public static ImageIcon loadPlayer(String name){
		ImageIcon PLAYER = new ImageIcon("nba/player/portrait"+name+".png");
		return PLAYER;
	}

}
