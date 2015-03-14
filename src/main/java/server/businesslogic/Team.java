package server.businesslogic;

import java.util.ArrayList;

import server.po.PlayerInMatchesPO;
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
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>();
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();
	
	//this team's data
	int appearance=0;//比赛场数
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
	int win=0;//胜出场数
	
	//opponent's data
	int hit2=0;//命中
	int shot2=0;//出手
	int thirdHit2=0;
	int thirdshot2=0;
	int freeHit2=0;
	int freeshot2=0;
	int offensiveRebound2=0;
	int defensiveRebound2=0;
	int totalRebound2=0;
	int assist2=0;
	int steal2=0;
	int block2=0;
	int miss2=0;
	int foul2=0;
	int score2=0;
	int win2=0;//胜出场数
	
	public TeamVO toVO()
	{
		return new TeamVO(teamPO.getFullName(),teamPO.getAbbreviation(), teamPO.getLocation(),
				 teamPO.getDivision(), teamPO.getZone(), teamPO.getHome(), teamPO.getSetupTime(),appearance,
				 hit,  shot,  thirdHit,  thirdshot,  freeHit,
				 freeshot,  offensiveRebound,  defensiveRebound,
				 totalRebound,  assist,  steal,  block,  miss,
				 foul,  score,  getHitRate(),  getThirdHitRate(),
				 getFreeHitRate(),  getWinRate(),  getOffensiveRound(),
				 getOffensiveEfficiency(),  getDefensiveRound(),
				 getDefensiveEfficiency(),  getOffensiveReboundEfficiency(),
				 getDefensiveReboundEfficiency(),  getStealEfficiency(),
				 getAssistEfficiency());
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
	public boolean compute()
	{
		appearance = thisTeam.size();
		for(int i=0;i<appearance;i++)
		{
			TeamInMatches tim = thisTeam.get(i);
			add(tim);
			score+=tim.getFinalScore();
			if(tim.finalScore>0)
				win++;
		}
		for(int i=0;i<appearance;i++)
		{
			TeamInMatches tim = opponentTeam.get(i);
			addOpponent(tim);
			score2+=tim.getFinalScore();
			if(tim.finalScore>0)
				win2++;
		}
		return true;
	}
	private void add(TeamInMatches tim)
	{
		ArrayList<PlayerInMatchesPO> players= tim.getTeamInMatchespo().getPlayers();
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
			//score will be dealt in other place
		}
	}
	private void addOpponent(TeamInMatches tim)
	{
		ArrayList<PlayerInMatchesPO> players= tim.getTeamInMatchespo().getPlayers();
		for(int i=0;i<players.size();i++)
		{
			PlayerInMatchesPO player = players.get(i);
			hit2+=player.getHit();
			shot2+=player.getShot();
			thirdHit2+=player.getThirdHit();
			thirdshot2+=player.getThirdshot();
			freeHit2+=player.getFreeHit();
			freeshot2+=player.getFreeshot();
			offensiveRebound2+=player.getOffensiveRebound();
			defensiveRebound2+=player.getDefensiveRebound();
			totalRebound2+=player.getTotalRebound();
			assist2+=player.getAssist();
			steal2+=player.getSteal();
			block2+=player.getBlock();
			miss2+=player.getMiss();
			foul2+=player.getFoul();
			//score will be dealt in other place
		}
	}
	private double getHitRate()
	{
		return hit/shot;
	}
	private double getThirdHitRate()
	{
		return thirdHit/thirdshot;
	}
	private double getFreeHitRate()
	{
		return freeHit/freeshot;
	}
	private double getWinRate()
	{
		return win/appearance;
	}
	private double getOffensiveRound()
	{//进攻回合
		return shot+0.4*freeshot-1.07*(offensiveRebound/(offensiveRebound+defensiveRebound2)*getLost())+1.07*miss;
	}
	private double getOffensiveEfficiency()
	{
		return score/getOffensiveRound()*100;
	}
	private double getDefensiveRound()
	{//whether it's right?
		return shot2+0.4*freeshot2-1.07*(offensiveRebound2/(offensiveRebound2+defensiveRebound)*getLost2())+1.07*miss2;
	}
	private double getDefensiveEfficiency()
	{
		return score2/getDefensiveRound()*100;
	}
	private double getOffensiveReboundEfficiency()
	{//进攻篮板效率
		return offensiveRebound/(offensiveRebound+defensiveRebound2);
	}
	private double getDefensiveReboundEfficiency()
	{
		return defensiveRebound/(defensiveRebound+offensiveRebound2);
	}
	private double getStealEfficiency()
	{
		return steal/getDefensiveRound()*100;
	}
	private double getAssistEfficiency()
	{
		return assist/getOffensiveRound()*100;
	}
	private int getLost()
	{
		return shot-hit;
	}
	private int getLost2()
	{
		return shot2-hit2;
	}
}
