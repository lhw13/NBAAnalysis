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
	{	
		appearance = thisTeam.size();
		for(int i=0;i<appearance;i++)
		{
			TeamInMatches tim = thisTeam.get(i);
			add(tim.getPlayers().get(orders.get(i)));
			teamPlayTime+=tim.getPlayTime();
			teamTotalRebound+=tim.getTotalRebound();
			teamOffensiveRebound+=tim.getOffensiveRebound();
			teamDefensiveRebound+=tim.getDefensiveRebound();
			teamshot+=tim.getShot();
			teamHit+=tim.getHit();
			teamFreeshot+=tim.getFreeshot();
			teamMiss+=tim.getMiss();
		}
		for(int i=0;i<appearance;i++)
		{
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
	{
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
	private double getHitRate()
	{
		if(shot==0)
			return 0;
		return hit/shot;
	}
	private double getThirdHitRate()
	{
		if(thirdshot==0)
			return 0;
		return thirdHit/thirdshot;
	}
	private double getFreeHitRate()
	{
		if(freeshot==0)
			return 0;
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
	private double getReboundRate()
	{
		return totalRebound*(teamPlayTime/5)/playTime/(teamTotalRebound+teamTotalRebound2);
	}
	private double getOffensiveReboundRate()
	{
		return offensiveRebound*(teamPlayTime/5)/playTime/(teamOffensiveRebound+teamOffensiveRebound2);
	}
	private double getDefensiveReboundRate()
	{
		return defensiveRebound*(teamPlayTime/5)/playTime/(teamDefensiveRebound+teamDefensiveRebound2);
	}
	private double getAssistRate()
	{
		if(playTime==0)
			return 0;
		else if(assist==0)
			return 0;
		else if((playTime/(teamPlayTime/5)*teamHit-hit)==0)
			return 0;
		return assist/(playTime/(teamPlayTime/5)*teamHit-hit);
	}
	private double getStealRate()
	{
		return steal*(teamPlayTime/5)/playTime/teamOffensiveRebound2;
	}
	private double getBlockRate()
	{
		return block*(teamPlayTime/5)/playTime/(teamshot2-teamThirdshot2);
	}
	private double getMissRate()
	{
		return miss/(shot-thirdshot+0.44*freeshot+miss);
	}
	private double getUseRate()
	{
		return (shot+0.44*freeshot+miss)*(teamPlayTime/5)/playTime/(teamshot+0.44*teamFreeshot+teamMiss);
	}
}
