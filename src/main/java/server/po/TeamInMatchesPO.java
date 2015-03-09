package server.po;

import java.util.ArrayList;

public class TeamInMatchesPO {
	public TeamInMatchesPO() {}
	public TeamInMatchesPO(String abbreviation,
			ArrayList<PlayerInMatchesPO> players) {
		super();
		this.abbreviation = abbreviation;
		this.players = players;
	}
	String abbreviation;
	ArrayList<PlayerInMatchesPO> players = new ArrayList<PlayerInMatchesPO>();
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
