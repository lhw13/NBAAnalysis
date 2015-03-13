package server.businesslogic;

import java.util.ArrayList;

import server.po.TeamInMatchesPO;
import server.po.TeamPO;
import vo.TeamVO;

public class Team {
	public Team(TeamPO teamPO) {
		super();
		this.teamPO = teamPO;
	}
	boolean analysed = false;
	TeamPO teamPO;
	TeamInMatchesPO teamInMatchesPO = new TeamInMatchesPO();
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>();
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();
	public TeamVO toVO()
	{
		return new TeamVO();
	}
	public void addThisTeam(TeamInMatches tim)
	{
		thisTeam.add(tim);
	}
	public void addOpponentTeam(TeamInMatches tim)
	{
		opponentTeam.add(tim);
	}
	public String getFullName()
	{
		return teamPO.getFullName();
	}
}
