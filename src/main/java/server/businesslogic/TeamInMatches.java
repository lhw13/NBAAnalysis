package server.businesslogic;

import java.util.ArrayList;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;

public class TeamInMatches {
	public TeamInMatches(TeamInMatchesPO teamInMatchespo, int finalScore, ArrayList<Integer> scores,int win) {
		super();
		this.teamInMatchespo = teamInMatchespo;
		this.finalScore = finalScore;
		this.scores = scores;
		this.win = win;
	}
	public TeamInMatches(TeamInMatchesPO teamInMatchespo, int finalScore) {
		super();
		this.teamInMatchespo = teamInMatchespo;
		this.finalScore = finalScore;
	}
	
	TeamInMatchesPO teamInMatchespo;
	int finalScore;
	ArrayList<Integer> scores;
	int win;
	public boolean containsPlayer(String name)
	{
		ArrayList<PlayerInMatchesPO> players = teamInMatchespo.getPlayers();
		for(int i=0;i<players.size();i++)
			if(name.equals(players.get(i).getName()))
				return true;
		return false;
	}
	public TeamInMatchesPO getMatchpo() {
		return teamInMatchespo;
	}
	public void setMatchpo(TeamInMatchesPO teamInMatchespo) {
		this.teamInMatchespo = teamInMatchespo;
	}
	public int getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	public ArrayList<Integer> getScores() {
		return scores;
	}
	public void setScores(ArrayList<Integer> scores) {
		this.scores = scores;
	}
	public String getAbbreviation()
	{
		return teamInMatchespo.getAbbreviation();
	}
	public ArrayList<PlayerInMatchesPO> getPlayers()
	{
		return teamInMatchespo.getPlayers();
	}
}
