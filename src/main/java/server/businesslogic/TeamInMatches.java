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
	
	//total of the players
	int hit=0;//命中
	int shot=0;//出手
	int thirdHit=0;
	int thirdshot=0;
	int freeHit=0;
	int freeshot=0;
	int offensiveRebound=0;
	int defensiveRebound=0;
	int totalRebound=0;
	int assist=0;
	int steal=0;
	int block=0;
	int miss=0;
	int foul=0;
	int score=0;
	int playTime=0;
	public void computeTotal()
	{
		ArrayList<PlayerInMatchesPO> players= getPlayers();
		for(int i=0;i<players.size();i++)
		{
			PlayerInMatchesPO player = players.get(i);
			hit+=player.getHit();
			shot+=player.getShot();
			thirdHit+=player.getThirdHit();
			thirdshot+=player.getThirdshot();
			freeHit+=player.getFreeHit();
			freeshot+=player.getFreeshot();
			offensiveRebound+=player.getOffensiveRebound();
			defensiveRebound+=player.getDefensiveRebound();
			totalRebound+=player.getTotalRebound();
			assist+=player.getAssist();
			steal+=player.getSteal();
			block+=player.getBlock();
			miss+=player.getMiss();
			foul+=player.getFoul();
			score+=player.getScore();
			playTime+=player.getPlayTime();
		}
	}
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
	public TeamInMatchesPO getTeamInMatchespo() {
		return teamInMatchespo;
	}
	public int getWin() {
		return win;
	}
	public int getHit() {
		return hit;
	}
	public int getShot() {
		return shot;
	}
	public int getThirdHit() {
		return thirdHit;
	}
	public int getThirdshot() {
		return thirdshot;
	}
	public int getFreeHit() {
		return freeHit;
	}
	public int getFreeshot() {
		return freeshot;
	}
	public int getOffensiveRebound() {
		return offensiveRebound;
	}
	public int getDefensiveRebound() {
		return defensiveRebound;
	}
	public int getTotalRebound() {
		return totalRebound;
	}
	public int getAssist() {
		return assist;
	}
	public int getSteal() {
		return steal;
	}
	public int getBlock() {
		return block;
	}
	public int getMiss() {
		return miss;
	}
	public int getFoul() {
		return foul;
	}
	public int getScore() {
		return score;
	}
}
