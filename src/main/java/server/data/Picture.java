package server.data;

import   java.awt.*; 
import   javax.swing.*; 
public class Picture {

	public Image getPlayerAction(String name){
			ImageIcon image = new ImageIcon("nba/players/action/"+name+".png");
			Image i = image.getImage();
			return i;
	}
	public Image getPlayerPortrait(String name){
		ImageIcon image = new ImageIcon("nba/players/portrait/"+name+".png");
		Image i = image.getImage();
		return i;
}
}
