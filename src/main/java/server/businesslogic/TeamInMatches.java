package server.businesslogic;

import java.util.ArrayList;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;

public class TeamInMatches {
	//team data in matches
	public TeamInMatches(TeamInMatchesPO teamInMatchespo, int finalScore, ArrayList<Integer> scores,int win) {
		//we need use po and other infos to creat an object
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
	int thirdHit=0;//三分
	int thirdshot=0;
	int freeHit=0;//罚球
	int freeshot=0;
	int offensiveRebound=0;//篮板
	int defensiveRebound=0;
	int totalRebound=0;
	int assist=0;
	int steal=0;
	int block=0;//盖帽
	int miss=0;//失误
	int foul=0;
	int score=0;
	int playTime=0;
	public void computeTotal()
	{//sum all the players' data as the team data
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
	public void clean()
	{//deal with dirty or null data
		ArrayList<PlayerInMatchesPO> players= getPlayers();
		int pt=0;
		ArrayList<PlayerInMatchesPO> playersWithNullPlayTime = new ArrayList<PlayerInMatchesPO>();
		for(int i=0;i<players.size();i++)
		{//deal with dirty score
			PlayerInMatchesPO player=players.get(i);
			if(player.getScore()<0)
				player.setScore((player.getHit()-player.getThirdHit())*2+player.getThirdHit()*3+player.getFreeHit());
			if(player.getPlayTime()>=0)
				pt+=player.getPlayTime();
			else
				playersWithNullPlayTime.add(player);
		}
		int size = playersWithNullPlayTime.size();
		int eplayTime=48*60*5;
		for(int j=4;j<getScores().size();j++)
			eplayTime+=1500;
		for(int i=0;i<size;i++)
		{//deal with dirty play time
			playersWithNullPlayTime.get(i).setPlayTime((eplayTime-pt)/size);
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
	public int getPlayTime() {
		return playTime;
	}
}
