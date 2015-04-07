package server.businesslogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import server.po.*;
import test.data.*;
import vo.*;

public class Player {
	public Player(TeamPO team, PlayerPO player) {
		super();
		// this.teamFullName = teamFullName;
		// this.teamAbbreviation = teamAbbreviation;
		this.team = team;
		this.player = player;
	}

	boolean analysed = false;
	TeamPO team;
	PlayerPO player;
	PlayerInMatchesPO playerInMatches = new PlayerInMatchesPO();
	ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>();
	ArrayList<Integer> orders = new ArrayList<Integer>();// this array just to
															// promote
															// efficiency
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();

	int appearance = 0;// 比赛场数
	int starting = 0;// 先发场数
	int playTime = 0;// 在场时间
	int hit = 0;// 命中
	int chuShou = 0;// 出手
	int thirdHit = 0;// 三分
	int thirdshot = 0;
	int freeHit = 0;// 罚球
	int freeshot = 0;
	int offensiveRebound = 0;// 篮板
	int defensiveRebound = 0;
	int rebound = 0;
	int assist = 0;
	int steal = 0;
	int blockShot = 0;
	int fault = 0;
	int foul = 0;
	int score = 0;
	double doubleTwo = 0;

	// this team
	int teamPlayTime = 0;
	int teamTotalRebound = 0;
	int teamOffensiveRebound = 0;
	int teamDefensiveRebound = 0;
	int teamshot = 0;
	int teamHit = 0;
	int teamFreeshot = 0;
	int teamMiss = 0;

	// opponent team
	int teamTotalRebound2 = 0;
	int teamOffensiveRebound2 = 0;
	int teamDefensiveRebound2 = 0;
	int teamshot2 = 0;
	int teamThirdshot2 = 0;
	int teamFreeshot2 = 0;
	int teamHit2 = 0;
	int teamMiss2 = 0;

	public boolean anaylse() {// the core algorithm of player analysis
		appearance = thisTeam.size();
		for (int i = 0; i < appearance; i++) {
			TeamInMatches tim = thisTeam.get(i);
			add(tim.getPlayers().get(orders.get(i)));// add idividual data
			teamPlayTime += tim.getPlayTime();// add this team data below
			teamTotalRebound += tim.getTotalRebound();
			teamOffensiveRebound += tim.getOffensiveRebound();
			teamDefensiveRebound += tim.getDefensiveRebound();
			teamshot += tim.getShot();
			teamHit += tim.getHit();
			teamFreeshot += tim.getFreeshot();
			teamMiss += tim.getMiss();
		}
		for (int i = 0; i < appearance; i++) {// add opponent data below
			TeamInMatches tim2 = opponentTeam.get(i);
			teamTotalRebound2 += tim2.getTotalRebound();
			teamOffensiveRebound2 += tim2.getOffensiveRebound();
			teamDefensiveRebound2 += tim2.getDefensiveRebound();
			teamshot2 += tim2.getShot();
			teamThirdshot2 += tim2.getThirdshot();
			teamFreeshot2 += tim2.getFreeshot();
			teamHit2 += tim2.getHit();
			teamMiss2 += tim2.getMiss();
		}
		return true;
	}

	public void add(PlayerInMatchesPO player) {// simple add to each domain
												// record two pairs
		int scoretemp = player.getScore();
		int totalReboundtemp = player.getTotalRebound();
		int assisttemp = player.getAssist();
		int stealtemp = player.getSteal();
		int blocktemp = player.getBlock();

		playTime += player.getPlayTime();
		hit += player.getHit();// 命中
		chuShou += player.getShot();// 出手
		thirdHit += player.getThirdHit();
		thirdshot += player.getThirdshot();
		freeHit += player.getFreeHit();
		freeshot += player.getFreeshot();
		offensiveRebound += player.getOffensiveRebound();
		defensiveRebound += player.getDefensiveRebound();
		rebound += totalReboundtemp;
		assist += assisttemp;
		steal += stealtemp;
		blockShot += blocktemp;
		fault += player.getMiss();
		foul += player.getFoul();
		score += scoretemp;
		char p = player.getPosition();
		if (p == 'F' || p == 'G' || p == 'C')
			starting++;
		int tp = 0;
		if (scoretemp >= 10)
			tp++;
		if (totalReboundtemp >= 10)
			tp++;
		if (assisttemp >= 10)
			tp++;
		if (stealtemp >= 10)
			tp++;
		if (blocktemp >= 10)
			tp++;
		if (tp >= 2)
			doubleTwo++;
	}

	public PlayerVO toVO() {
		computePromotion();
		return new PlayerVO(team.getFullName(), team.getAbbreviation(),
				team.getDivision(), team.getZone(), player.getName(),
				player.getNumber(), player.getPosition(), new HeightVO(player
						.getHeight().getFeet(), player.getHeight().getInch()),
				player.getWeight(), player.getBirth(), player.getAge(),
				player.getExp(), player.getSchool(), appearance, starting,
				playTime, hit, chuShou, thirdHit, thirdshot, freeHit, freeshot,
				offensiveRebound, defensiveRebound, rebound, assist,
				steal, blockShot, fault, foul, score, getHitRate(),
				getThree(), getPenalty(), getEfficient(),
				getGmSc(), getRealShot(), getShotEfficient(),
				getReboundEfficient(), getOffendReboundEfficient(),
				getDefendReboundEfficient(), getAssistEfficient(), getStealEfficient(),
				getBlockShotEfficient(), getFaultEfficient(), getFrequency(), doubleTwo, matches,
				scorePromotion, reboundPromotion, assistPromotion);
	}
	
	public PlayerHighInfo toHighInfo(){
		PlayerHighInfo phi = new PlayerHighInfo();
		phi.setAssistEfficient(getAssistEfficient());
		phi.setBlockShotEfficient(getBlockShotEfficient());
		phi.setDefendReboundEfficient(getDefendReboundEfficient());
		phi.setFaultEfficient(getFaultEfficient());
		phi.setFrequency(getFrequency());
		phi.setGmSc(getGmSc());
		//phi.setLeague(team.);
		phi.setName(player.getName());
		phi.setOffendReboundEfficient(getOffendReboundEfficient());
		phi.setPosition(player.getPosition());
		phi.setRealShot(getRealShot());
		phi.setReboundEfficient(getReboundEfficient());
		phi.setShotEfficient(getShotEfficient());
		phi.setStealEfficient(getStealEfficient());
		phi.setTeamName(team.getAbbreviation());
		return phi;
	}
	
	public PlayerNormalInfo toNormalInfo(){
		PlayerNormalInfo pni = new PlayerNormalInfo();
		pni.setAge(player.getAge());
		pni.setAssist(assist);
		pni.setBlockShot(blockShot);
		pni.setDefend(defensiveRebound);
		pni.setEfficiency(getEfficient());
		pni.setFault(fault);
		pni.setFoul(foul);
		pni.setMinute(playTime/60);
		pni.setName(player.getName());
		pni.setNumOfGame(appearance);
		pni.setOffend(offensiveRebound);
		pni.setPenalty(getPenalty());
		pni.setPoint(score);
		pni.setRebound(rebound);
		pni.setShot(getHitRate());
		pni.setStart(starting);
		pni.setSteal(steal);
		pni.setTeamName(team.getAbbreviation());
		pni.setThree(getThree());
		return pni;
	}
	
	public PlayerNormalInfo toNormalInfoAvg() {
		PlayerNormalInfo pni = new PlayerNormalInfo();
		pni.setAge(player.getAge());
		pni.setAssist(assist/appearance);
		pni.setBlockShot(blockShot/appearance);
		pni.setDefend(defensiveRebound/appearance);
		pni.setEfficiency(getEfficient());
		pni.setFault(fault/appearance);
		pni.setFoul(foul/appearance);
		pni.setMinute(playTime/60/appearance);
		pni.setName(player.getName());
		pni.setNumOfGame(appearance);
		pni.setOffend(offensiveRebound/appearance);
		pni.setPenalty(getPenalty());
		pni.setPoint(score/appearance);
		pni.setRebound(rebound/appearance);
		pni.setShot(getHitRate());
		pni.setStart(starting/appearance);
		pni.setSteal(steal/appearance);
		pni.setTeamName(team.getAbbreviation());
		pni.setThree(getThree());
		return pni;
	}

	public void addThisTeam(TeamInMatches tim, int order) {
		thisTeam.add(tim);
		orders.add(order);
	}

	public void addOpponentTeam(TeamInMatches tim) {
		opponentTeam.add(tim);
	}
	
	public void addMatch(MatchPO mp) {
		matches.add(mp);
	}

	// below are corresponding to our homework paper
	private double getHitRate() {//HitRate
		if (chuShou == 0)
			return 0;
		return (double) hit / chuShou;
	}
	
	public double getShot() {//HitRate
		if (chuShou == 0)
			return 0;
		return (double) hit / chuShou;
	}

	public double getThree() {//third hit rate
		if (thirdshot == 0)
			return 0;
		return (double) thirdHit / thirdshot;
	}

	public double getPenalty() {
		if (freeshot == 0)
			return 0;
		return (double) freeHit / freeshot;
	}

	public double getEfficient() {
		return (score + rebound + assist + steal + blockShot) - (chuShou - hit)
				- (freeshot - freeHit) - fault;
	}

	public double getGmSc() {
		return score + 0.4 * (double) hit - 0.7 * (double) chuShou - 0.4
				* (double) (freeshot - freeHit) + 0.7
				* (double) offensiveRebound + 0.3 * (double) defensiveRebound
				+ steal + 0.7 * (double) assist + 0.7 * (double) blockShot - 0.4
				* (double) foul - fault;
	}

	public double getRealShot() {
		return (double) score / (2 * (chuShou + 0.44 * (double) freeshot));
	}

	public double getShotEfficient() {
		return (double) (hit + 0.5 * (double) thirdHit) / chuShou;
	}

	public double getReboundEfficient() {
		return (double) rebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamTotalRebound + teamTotalRebound2);
	}

	public double getOffendReboundEfficient() {
		return (double) offensiveRebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamOffensiveRebound + teamOffensiveRebound2);
	}

	public double getDefendReboundEfficient() {
		return (double) defensiveRebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamDefensiveRebound + teamDefensiveRebound2);
	}

	public double getAssistEfficient() {
		if (playTime == 0)
			return 0;
		else if (assist == 0)
			return 0;
		else if (((double) playTime / ((double) teamPlayTime / 5) * teamHit - hit) == 0)
			return 0;
		return (double) assist
				/ ((double) playTime / ((double) teamPlayTime / 5) * teamHit - hit);
	}

	private double getOffensiveRound() {// 进攻回合
		return teamshot
				+ 0.4
				* teamFreeshot
				- 1.07
				* ((double) teamOffensiveRebound
						/ (teamOffensiveRebound + teamDefensiveRebound2) * (teamshot - teamHit))
				+ 1.07 * teamMiss;
	}

	private double getOffensiveRound2() {// whether it's right?
		return teamshot2
				+ 0.4
				* teamFreeshot2
				- 1.07
				* ((double) teamOffensiveRebound2
						/ (teamOffensiveRebound2 + teamDefensiveRebound) * (teamshot2 - teamHit2))
				+ 1.07 * teamMiss2;
	}

	public double getStealEfficient() {
		return (double) steal * ((double) teamPlayTime / 5) / (double) playTime
				/ getOffensiveRound2();
	}

	public double getBlockShotEfficient() {
		return (double) blockShot * ((double) teamPlayTime / 5) / (double) playTime
				/ (double) (teamshot2 - teamThirdshot2);
	}

	public double getFaultEfficient() {
		return (double) fault
				/ (double) (chuShou - thirdshot + 0.44 * (double) freeshot + fault);
	}

	public double getFrequency() {
		return (double) (chuShou + 0.44 * freeshot + fault)
				* ((double) teamPlayTime / 5) / playTime
				/ (teamshot + 0.44 * teamFreeshot + teamMiss);
	}

	public boolean isAnalysed() {
		return analysed;
	}

	public TeamPO getTeam() {
		return team;
	}

	public PlayerPO getPlayer() {
		return player;
	}

	public PlayerInMatchesPO getPlayerInMatches() {
		return playerInMatches;
	}

	public ArrayList<TeamInMatches> getThisTeam() {
		return thisTeam;
	}

	public ArrayList<Integer> getOrders() {
		return orders;
	}

	public ArrayList<TeamInMatches> getOpponentTeam() {
		return opponentTeam;
	}

	public int getAppearance() {
		return appearance;
	}

	public int getStarting() {
		return starting;
	}

	public int getPlayTime() {
		return playTime;
	}
	
	public double getMinute() {
		return playTime/60;
	}

	public int getHit() {
		return hit;
	}

	public int getChuShou() {
		return chuShou;
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

	public int getRebound() {
		return rebound;
	}

	public int getAssist() {
		return assist;
	}

	public int getSteal() {
		return steal;
	}

	public int getBlockShot() {
		return blockShot;
	}

	public int getFault() {
		return fault;
	}

	public int getFoul() {
		return foul;
	}

	public int getScore() {
		return score;
	}
	
	public int getPoint() {//deal with
		//the different commond test
		return score;
	}

	public double getDoubleTwo() {
		return doubleTwo;
	}

	public int getTeamPlayTime() {
		return teamPlayTime;
	}

	public int getTeamTotalRebound() {
		return teamTotalRebound;
	}

	public int getTeamOffensiveRebound() {
		return teamOffensiveRebound;
	}

	public int getTeamDefensiveRebound() {
		return teamDefensiveRebound;
	}

	public int getTeamshot() {
		return teamshot;
	}

	public int getTeamHit() {
		return teamHit;
	}

	public int getTeamFreeshot() {
		return teamFreeshot;
	}

	public int getTeamMiss() {
		return teamMiss;
	}

	public int getTeamTotalRebound2() {
		return teamTotalRebound2;
	}

	public int getTeamOffensiveRebound2() {
		return teamOffensiveRebound2;
	}

	public int getTeamDefensiveRebound2() {
		return teamDefensiveRebound2;
	}

	public int getTeamshot2() {
		return teamshot2;
	}

	public int getTeamThirdshot2() {
		return teamThirdshot2;
	}

	public int getTeamFreeshot2() {
		return teamFreeshot2;
	}

	public int getTeamHit2() {
		return teamHit2;
	}

	public int getTeamMiss2() {
		return teamMiss2;
	}
	
	public String getPosition() {
		return player.getPosition();
	}
	
	public String getLeague() {
		char c = team.getDivision();
		if(c=='W')
			return "West";
		else
			return "East";
	}
	
	public int getAge() {
		return player.getAge();
	}
	public String getName() {
		return player.getName();
	}
	
	int scorePromotion;
	int assistPromotion;
	int reboundPromotion;
	
	public void computePromotion() {
		int size = thisTeam.size();
		if(size<=5)
		{ //information is not enough
			scorePromotion=0;
			assistPromotion=0;
			reboundPromotion=0;
			return;
		}
		int scoreRecent=0;
		int assistRecent=0;
		int reboundRecent=0;
		for(int i=size-5; i<size; i++)
		{
			TeamInMatches tim = thisTeam.get(i);
			PlayerInMatchesPO player = tim.getPlayers().get(orders.get(i));
			scoreRecent+=player.getScore();
			assistRecent+=player.getAssist();
			reboundRecent+=player.getTotalRebound();
		}
		int appearancePast = appearance-5;
		int scorePastAvg = (score - scoreRecent)/appearancePast;
		int assistPastAvg = (assist - assistRecent)/appearancePast;
		int reboundPashAvg = (rebound - reboundRecent)/appearancePast;
		if(scorePastAvg==0)
			scorePromotion=0;
		else
			scorePromotion = (scoreRecent/5 - scorePastAvg)/scorePastAvg;
		if(assistPastAvg==0)
			assistPromotion=0;
		else
			assistPromotion = (assistRecent/5 - assistPastAvg)/assistPastAvg;
		if(reboundPashAvg==0)
			reboundPromotion=0;
		else
			reboundPromotion = (reboundRecent/5 - reboundPashAvg)/reboundPashAvg;
	}
}
