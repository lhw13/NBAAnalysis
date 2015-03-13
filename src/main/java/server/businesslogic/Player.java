package server.businesslogic;

import java.util.ArrayList;

import server.po.*;
import vo.*;

public class Player {
	public Player(String teamFullName, String teamAbbreviation, PlayerPO player) {
		super();
		this.teamFullName = teamFullName;
		this.teamAbbreviation = teamAbbreviation;
		this.player = player;
	}
	boolean analysed = false;
	String teamFullName;
	String teamAbbreviation;
	PlayerPO player;
	PlayerInMatchesPO playerInMatches =  new PlayerInMatchesPO();
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>();
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();
	
	public PlayerVO toVO()
	{
		return new PlayerVO();
	}
	public void addThisTeam(TeamInMatches tim)
	{
		thisTeam.add(tim);
	}
	public void addOpponentTeam(TeamInMatches tim)
	{
		opponentTeam.add(tim);
	}
}
