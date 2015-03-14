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
	ArrayList<Integer> orders = new ArrayList<Integer>();//this array just to promote efficiency
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();
	
	int appearance=0;//比赛场数
	int starting=0;//先发场数
	int playTime=0;//在场时间
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
	
	public boolean anaylse()
	{
		appearance = thisTeam.size();
		for(int i=0;i<appearance;i++)
		{
			//TeamInMatches tim = thisTeam.get(i);
			//add(tim);
			//score+=tim.getFinalScore();
			add(thisTeam.get(i).getPlayers().get(orders.get(i)));
		}
	/*	for(int i=0;i<appearance;i++)
		{
			TeamInMatches tim = opponentTeam.get(i);
			addOpponent(tim);
			score2+=tim.getFinalScore();
			if(tim.finalScore>0)
				win2++;
		}*/
		return true;
	}
	public void add(PlayerInMatchesPO player)
	{
		playTime += player.getPlayTime();
		hit += player.getHit();//命中
		shot += player.getShot();//出手
		thirdHit += player.getThirdHit();
		thirdshot += player.getThirdshot();
		freeHit += player.getFreeHit();
		freeshot += player.getFreeshot();
		offensiveRebound += player.getOffensiveRebound();
		defensiveRebound += player.getDefensiveRebound();
		totalRebound += player.getTotalRebound();
		assist += player.getAssist();
		steal += player.getSteal();
		block += player.getBlock();
		miss += player.getMiss();
		foul += player.getFoul();
		score += player.getScore();
		char p = player.getPosition();
		if(p=='F' || p=='G' || p=='C')
			starting++;
	}
	public PlayerVO toVO()
	{
		return new PlayerVO();
	}
	public void addThisTeam(TeamInMatches tim,int order)
	{
		thisTeam.add(tim);
		orders.add(order);
	}
	public void addOpponentTeam(TeamInMatches tim)
	{
		opponentTeam.add(tim);
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
	private double getEfficiency()
	{
		return (score+totalRebound+assist+steal+block)
				-(shot-hit)-(freeshot-freeHit)-miss;
	}
	private double getGmScEfficiency()
	{
		return score+0.4*hit-0.7*shot-0.4*(freeshot-freeHit)
				+0.7*offensiveRebound+0.3*defensiveRebound
				+steal+0.7*assist+0.7*block-0.4*foul-miss;
	}
	private double getRealHitRate()
	{
		return score/(2*(shot+0.44*freeshot));
	}
	private double getShotEfficiency()
	{
		return (hit+0.5*thirdHit)/shot;
	}
}
