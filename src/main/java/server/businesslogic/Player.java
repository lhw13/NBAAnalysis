package server.businesslogic;

import java.util.ArrayList;
import java.util.Calendar;

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
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>();
	ArrayList<Integer> orders = new ArrayList<Integer>();// this array just to
															// promote
															// efficiency
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>();

	int appearance = 0;// 比赛场数
	int starting = 0;// 先发场数
	int playTime = 0;// 在场时间
	int hit = 0;// 命中
	int shot = 0;// 出手
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
	int point = 0;
	double twoPairs = 0;

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
		shot += player.getShot();// 出手
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
		point += scoretemp;
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
			twoPairs++;
	}

	public PlayerVO toVO() {
		return new PlayerVO(team.getFullName(), team.getAbbreviation(),
				team.getDivision(), team.getZone(), player.getName(),
				player.getNumber(), player.getPosition(), new HeightVO(player
						.getHeight().getFeet(), player.getHeight().getInch()),
				player.getWeight(), player.getBirth(), player.getAge(),
				player.getExp(), player.getSchool(), appearance, starting,
				playTime, hit, shot, thirdHit, thirdshot, freeHit, freeshot,
				offensiveRebound, defensiveRebound, rebound, assist,
				steal, blockShot, fault, foul, point, getHitRate(),
				getThirdHitRate(), getFreeHitRate(), getEfficiency(),
				getGmScEfficiency(), getRealHitRate(), getShotEfficiency(),
				getReboundRate(), getOffensiveReboundRate(),
				getDefensiveReboundRate(), getAssistRate(), getStealRate(),
				getBlockRate(), getMissRate(), getUseRate(), twoPairs);
	}
	
	public PlayerHighInfo toHighInfo(){
		PlayerHighInfo phi = new PlayerHighInfo();
		phi.setAssistEfficient(getAssistRate());
		phi.setBlockShotEfficient(getBlockRate());
		phi.setDefendReboundEfficient(getDefensiveReboundRate());
		phi.setFaultEfficient(getMissRate());
		phi.setFrequency(getUseRate());
		phi.setGmSc(getGmScEfficiency());
		//phi.setLeague(team.);
		phi.setName(player.getName());
		phi.setOffendReboundEfficient(getOffensiveReboundRate());
		phi.setPosition(player.getPosition());
		phi.setRealShot(getRealHitRate());
		phi.setReboundEfficient(getReboundRate());
		phi.setShotEfficient(getShotEfficiency());
		phi.setStealEfficient(getStealRate());
		phi.setTeamName(team.getAbbreviation());
		return phi;
	}
	
	public PlayerNormalInfo toNormalInfo(){
		PlayerNormalInfo pni = new PlayerNormalInfo();
		pni.setAge(player.getAge());
		pni.setAssist(assist);
		pni.setBlockShot(blockShot);
		pni.setDefend(defensiveRebound);
		pni.setEfficiency(getEfficiency());
		pni.setFault(fault);
		pni.setFoul(foul);
		pni.setMinute(playTime/60);
		pni.setName(player.getName());
		pni.setNumOfGame(appearance);
		pni.setOffend(offensiveRebound);
		pni.setPenalty(getFreeHitRate());
		pni.setPoint(point);
		pni.setRebound(rebound);
		pni.setShot(getHitRate());
		pni.setStart(starting);
		pni.setSteal(steal);
		pni.setTeamName(team.getAbbreviation());
		pni.setThree(getThirdHitRate());
		return pni;
	}

	public void addThisTeam(TeamInMatches tim, int order) {
		thisTeam.add(tim);
		orders.add(order);
	}

	public void addOpponentTeam(TeamInMatches tim) {
		opponentTeam.add(tim);
	}

	// below are corresponding to our homework paper
	private double getHitRate() {
		if (shot == 0)
			return 0;
		return (double) hit / shot;
	}

	private double getThirdHitRate() {
		if (thirdshot == 0)
			return 0;
		return (double) thirdHit / thirdshot;
	}

	private double getFreeHitRate() {
		if (freeshot == 0)
			return 0;
		return (double) freeHit / freeshot;
	}

	private double getEfficiency() {
		return (point + rebound + assist + steal + blockShot) - (shot - hit)
				- (freeshot - freeHit) - fault;
	}

	private double getGmScEfficiency() {
		return point + 0.4 * (double) hit - 0.7 * (double) shot - 0.4
				* (double) (freeshot - freeHit) + 0.7
				* (double) offensiveRebound + 0.3 * (double) defensiveRebound
				+ steal + 0.7 * (double) assist + 0.7 * (double) blockShot - 0.4
				* (double) foul - fault;
	}

	private double getRealHitRate() {
		return (double) point / (2 * (shot + 0.44 * (double) freeshot));
	}

	private double getShotEfficiency() {
		return (double) (hit + 0.5 * (double) thirdHit) / shot;
	}

	private double getReboundRate() {
		return (double) rebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamTotalRebound + teamTotalRebound2);
	}

	private double getOffensiveReboundRate() {
		return (double) offensiveRebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamOffensiveRebound + teamOffensiveRebound2);
	}

	private double getDefensiveReboundRate() {
		return (double) defensiveRebound * ((double) teamPlayTime / 5)
				/ (double) playTime
				/ (double) (teamDefensiveRebound + teamDefensiveRebound2);
	}

	private double getAssistRate() {
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

	private double getStealRate() {
		return (double) steal * ((double) teamPlayTime / 5) / (double) playTime
				/ getOffensiveRound2();
	}

	private double getBlockRate() {
		return (double) blockShot * ((double) teamPlayTime / 5) / (double) playTime
				/ (double) (teamshot2 - teamThirdshot2);
	}

	private double getMissRate() {
		return (double) fault
				/ (double) (shot - thirdshot + 0.44 * (double) freeshot + fault);
	}

	private double getUseRate() {
		return (double) (shot + 0.44 * freeshot + fault)
				* ((double) teamPlayTime / 5) / playTime
				/ (teamshot + 0.44 * teamFreeshot + teamMiss);
	}
}
