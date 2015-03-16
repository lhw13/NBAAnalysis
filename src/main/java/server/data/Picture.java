package server.data;
import   javax.swing.*; 
public class Picture {

	public ImageIcon getPlayerAction(String name){
			ImageIcon image = new ImageIcon("nba/players/action/"+name+".png");
			return image;
	}
	public ImageIcon getPlayerPortrait(String name){
		ImageIcon image = new ImageIcon("nba/players/portrait/"+name+".png");
		return image;
	}
}
