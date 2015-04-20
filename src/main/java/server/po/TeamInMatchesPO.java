package server.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import server.businesslogic.Player;

public final  class TeamInMatchesPO {
	public TeamInMatchesPO() {
	}

	public TeamInMatchesPO(String abbreviation,
			ArrayList<PlayerInMatchesPO> players) {
		super();
		this.abbreviation = abbreviation;
		this.players = players;
	}

	String abbreviation;
	ArrayList<Integer> scores;// 每节得分
	ArrayList<PlayerInMatchesPO> players = new ArrayList<PlayerInMatchesPO>();

	public static Comparator<PlayerInMatchesPO> comparePointDesc = new Comparator<PlayerInMatchesPO>() {  
		  
	    @Override  
	    public int compare(PlayerInMatchesPO o1, PlayerInMatchesPO o2) {  
	    	Integer i2=o2.getScore();
	    	Integer i1=o1.getScore();
	        return i2.compareTo(i1);
	    	//return o2.getPoint() > o1.getPoint() ? 1 : -1;
	    }
	};
	
	void sortPlayersByScore() {
		Collections.sort(players, comparePointDesc);
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public ArrayList<PlayerInMatchesPO> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerInMatchesPO> players) {
		this.players = players;
	}
}
