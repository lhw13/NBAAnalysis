package server.businesslogic;

import java.util.ArrayList;
import java.util.Calendar;

import server.po.*;
import vo.*;

public class Player {
	public Player(TeamPO team,PlayerPO player) {
		super();
		//this.teamFullName = teamFullName;
		//this.teamAbbreviation = teamAbbreviation;
		this.team=team;
		this.player = player;
	}
	boolean analysed = false;
	TeamPO team;
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
	int thirdHit=0;//三分
	int thirdshot=0;
	int freeHit=0;//罚球
	int freeshot=0;
	int offensiveRebound=0;//篮板
	int defensiveRebound=0;
	int totalRebound=0;
	int assist=0;
	int steal=0;
	int block=0;
	int miss=0;
	int foul=0;
	int score=0;
	double twoPairs=0;
	
	//this team
	int teamPlayTime=0;
	int teamTotalRebound=0;
	int teamOffensiveRebound=0;
	int teamDefensiveRebound=0;
	int teamshot=0;
	int teamHit=0;
	int teamFreeshot=0;
	int teamMiss=0;
	
	//opponent team
	int teamTotalRebound2=0;
	int teamOffensiveRebound2=0;
	int teamDefensiveRebound2=0;
	int teamshot2=0;
	int teamThirdshot2=0;
	
	public boolean anaylse()
	{//the core algorithm of player analysis
		appearance = thisTeam.size();
		for(int i=0;i<appearance;i++)
		{
			TeamInMatches tim = thisTeam.get(i);
			add(tim.getPlayers().get(orders.get(i)));//add idividual data
			teamPlayTime+=tim.getPlayTime();//add this team data below
			teamTotalRebound+=tim.getTotalRebound();
			teamOffensiveRebound+=tim.getOffensiveRebound();
			teamDefensiveRebound+=tim.getDefensiveRebound();
			teamshot+=tim.getShot();
			teamHit+=tim.getHit();
			teamFreeshot+=tim.getFreeshot();
			teamMiss+=tim.getMiss();
		}
		for(int i=0;i<appearance;i++)
		{//add opponent data below
			TeamInMatches tim2 = opponentTeam.get(i);
			teamTotalRebound2+=tim2.getTotalRebound();
			teamOffensiveRebound2+=tim2.getOffensiveRebound();
			teamDefensiveRebound2+=tim2.getDefensiveRebound();
			teamshot2+=tim2.getShot();
			teamThirdshot2+=tim2.getThirdshot();
		}
		return true;
	}
	public void add(PlayerInMatchesPO player)
	{//simple add to each domain
		//record two pairs
		int scoretemp=player.getScore();
		int totalReboundtemp = player.getTotalRebound();
		int assisttemp = player.getAssist();
		int stealtemp = player.getSteal();
		int blocktemp = player.getBlock();
		
		
		playTime += player.getPlayTime();
		hit += player.getHit();//命中
		shot += player.getShot();//出手
		thirdHit += player.getThirdHit();
		thirdshot += player.getThirdshot();
		freeHit += player.getFreeHit();
		freeshot += player.getFreeshot();
		offensiveRebound += player.getOffensiveRebound();
		defensiveRebound += player.getDefensiveRebound();
		totalRebound += totalReboundtemp;
		assist += assisttemp;
		steal += stealtemp;
		block += blocktemp;
		miss += player.getMiss();
		foul += player.getFoul();
		score += scoretemp;
		char p = player.getPosition();
		if(p=='F' || p=='G' || p=='C')
			starting++;
		int tp=0;
		if(scoretemp>=10)
			tp++;
		if(totalReboundtemp>=10)
			tp++;
		if(assisttemp>=10)
			tp++;
		if(stealtemp>=10)
			tp++;
		if(blocktemp>=10)
			tp++;
		if(tp>=2)
			twoPairs++;
	}
	public PlayerVO toVO()
	{
		return new PlayerVO( team.getFullName(), team.getAbbreviation(),
				 team.getDivision(),  team.getZone(),  player.getName(),  player.getNumber(),  player.getPosition(),
				 new HeightVO(player.getHeight().getFeet(),player.getHeight().getInch()),  
				 player.getWeight(),  player.getBirth(),  player.getAge(),  player.getExp(),
				 player.getSchool(),  appearance,  starting,  playTime,  hit,
				 shot,  thirdHit,  thirdshot,  freeHit,  freeshot,
				 offensiveRebound,  defensiveRebound,  totalRebound,
				 assist,  steal,  block,  miss,  foul,  score,
				 getHitRate(),  getThirdHitRate(),  getFreeHitRate(),
				 getEfficiency(),  getGmScEfficiency(),  getRealHitRate(),
				 getShotEfficiency(),  getReboundRate(),
				 getOffensiveReboundRate(),  getDefensiveReboundRate(),
				 getAssistRate(),  getStealRate(),  getBlockRate(),
				 getMissRate(),  getUseRate(),twoPairs);
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
	
	//below are corresponding to our homework paper
	private double getHitRate()
	{
		if(shot==0)
			return 0;
		return (double)hit/shot;
	}
	private double getThirdHitRate()
	{
		if(thirdshot==0)
			return 0;
		return (double)thirdHit/thirdshot;
	}
	private double getFreeHitRate()
	{
		if(freeshot==0)
			return 0;
		return (double)freeHit/freeshot;
	}
	private double getEfficiency()
	{
		return (score+totalRebound+assist+steal+block)
				-(shot-hit)-(freeshot-freeHit)-miss;
	}
	private double getGmScEfficiency()
	{
		return score+0.4*(double)hit-0.7*(double)shot-0.4*(double)(freeshot-freeHit)
				+0.7*(double)offensiveRebound+0.3*(double)defensiveRebound
				+steal+0.7*(double)assist+0.7*(double)block-0.4*(double)foul-miss;
	}
	private double getRealHitRate()
	{
		return (double)score/(2*(shot+0.44*(double)freeshot));
	}
	private double getShotEfficiency()
	{
		return (double)(hit+0.5*(double)thirdHit)/shot;
	}
	private double getReboundRate()
	{
		return (double)totalRebound*((double)teamPlayTime/5)/(double)playTime/(double)(teamTotalRebound+teamTotalRebound2);
	}
	private double getOffensiveReboundRate()
	{
		return (double)offensiveRebound*((double)teamPlayTime/5)/(double)playTime/(double)(teamOffensiveRebound+teamOffensiveRebound2);
	}
	private double getDefensiveReboundRate()
	{
		return (double)defensiveRebound*((double)teamPlayTime/5)/(double)playTime/(double)(teamDefensiveRebound+teamDefensiveRebound2);
	}
	private double getAssistRate()
	{
		if(playTime==0)
			return 0;
		else if(assist==0)
			return 0;
		else if(((double)playTime/((double)teamPlayTime/5)*teamHit-hit)==0)
			return 0;
		return (double)assist/((double)playTime/((double)teamPlayTime/5)*teamHit-hit);
	}
	private double getStealRate()
	{
		return (double)steal*((double)teamPlayTime/5)/(double)playTime/(double)teamOffensiveRebound2;
	}
	private double getBlockRate()
	{
		return (double)block*((double)teamPlayTime/5)/(double)playTime/(double)(teamshot2-teamThirdshot2);
	}
	private double getMissRate()
	{
		return (double)miss/(double)(shot-thirdshot+0.44*(double)freeshot+miss);
	}
	private double getUseRate()
	{
		return (double)(shot+0.44*freeshot+miss)*((double)teamPlayTime/5)/playTime/(teamshot+0.44*teamFreeshot+teamMiss);
	}
}
