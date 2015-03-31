package server.businesslogic;

import java.util.ArrayList;

import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import server.po.TeamPO;
import test.data.*;
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

	// this team's data
	int appearance = 0;// 比赛场数
	int hit = 0;// 命中
	int shot = 0;// 出手
	int thirdHit = 0;// 三分
	int thirdshot = 0;
	int freeHit = 0;// 罚球
	int freeshot = 0;
	int offendRebound = 0;// 篮板
	int defendRebound = 0;
	int rebound = 0;
	int assist = 0;// 助攻
	int steal = 0;// 抢断
	int blockShot = 0;// 盖帽
	int fault = 0;// 失误
	int foul = 0;
	int score = 0;
	int win = 0;// 胜出场数
	int playTime = 0;// 比赛时间

	// opponent's data
	int hit2 = 0;// 命中
	int shot2 = 0;// 出手
	int thirdHit2 = 0;
	int thirdshot2 = 0;
	int freeHit2 = 0;
	int freeshot2 = 0;
	int offensiveRebound2 = 0;
	int defensiveRebound2 = 0;
	int totalRebound2 = 0;
	int assist2 = 0;
	int steal2 = 0;
	int block2 = 0;
	int miss2 = 0;
	int foul2 = 0;
	int score2 = 0;
	int win2 = 0;// 胜出场数

	public TeamVO toVO() {
		return new TeamVO(teamPO.getFullName(), teamPO.getAbbreviation(),
				teamPO.getLocation(), teamPO.getDivision(), teamPO.getZone(),
				teamPO.getHome(), teamPO.getSetupTime(), appearance, hit, shot,
				thirdHit, thirdshot, freeHit, freeshot, offendRebound,
				defendRebound, rebound, assist, steal, blockShot, fault,
				foul, score, getHitRate(), getThirdHitRate(), getFreeHitRate(),
				getWinRate(), getOffensiveRound(), getOffensiveEfficiency(),
				getDefensiveRound(), getDefensiveEfficiency(),
				getOffensiveReboundEfficiency(),
				getDefensiveReboundEfficiency(), getStealEfficiency(),
				getAssistEfficiency());
	}
	
	public TeamHighInfo toHighInfo() {
		TeamHighInfo thi = new TeamHighInfo();
		thi.setAssistEfficient(getAssistEfficiency());
		thi.setDefendEfficient(getDefensiveEfficiency());
		thi.setDefendReboundEfficient(getDefensiveReboundEfficiency());
		thi.setOffendEfficient(getOffensiveEfficiency());
		thi.setOffendReboundEfficient(getOffensiveReboundEfficiency());
		thi.setOffendRound(getOffensiveRound());
		thi.setStealEfficient(getStealEfficiency());
		thi.setTeamName(teamPO.getAbbreviation());
		thi.setWinRate(getWinRate());
		return thi;
	}
	
	public TeamNormalInfo toNormalInfo() {
		TeamNormalInfo tni = new TeamNormalInfo();
		tni.setAssist(assist);
		tni.setBlockShot(blockShot);
		tni.setDefendRebound(defendRebound);
		tni.setFault(fault);
		tni.setFoul(foul);
		tni.setNumOfGame(appearance);
		tni.setOffendRebound(offendRebound);
		tni.setPenalty(getFreeHitRate());
		tni.setPoint(score);
		tni.setRebound(rebound);
		tni.setShot(getHitRate());
		tni.setSteal(steal);
		tni.setTeamName(teamPO.getAbbreviation());
		tni.setThree(getThirdHitRate());
		return tni;
	}
	
	public TeamNormalInfo toNormalInfoAvg() {
		TeamNormalInfo tni = new TeamNormalInfo();
		tni.setAssist(assist/appearance);
		tni.setBlockShot(blockShot/appearance);
		tni.setDefendRebound(defendRebound/appearance);
		tni.setFault(fault/appearance);
		tni.setFoul(foul/appearance);
		tni.setNumOfGame(appearance);
		tni.setOffendRebound(offendRebound/appearance);
		tni.setPenalty(getFreeHitRate());
		tni.setPoint(score/appearance);
		tni.setRebound(rebound/appearance);
		tni.setShot(getHitRate());
		tni.setSteal(steal/appearance);
		tni.setTeamName(teamPO.getAbbreviation());
		tni.setThree(getThirdHitRate());
		return tni;
	}

	public void addThisTeam(TeamInMatches tim) {
		thisTeam.add(tim);
	}

	public void addOpponentTeam(TeamInMatches tim) {
		opponentTeam.add(tim);
	}

	public String getFullName() {
		return teamPO.getFullName();
	}

	public TeamPO getTeamPO() {
		return teamPO;
	}

	public boolean anaylse() {// the core of team analysis
		appearance = thisTeam.size();
		for (int i = 0; i < appearance; i++) {// for more info, see Player.java
			TeamInMatches tim = thisTeam.get(i);
			add(tim);
			score += tim.getFinalScore();
			if (tim.getWin() > 0)
				win++;
		}
		for (int i = 0; i < appearance; i++) {
			TeamInMatches tim = opponentTeam.get(i);
			addOpponent(tim);
			score2 += tim.getFinalScore();
			if (tim.getWin() > 0)
				win2++;
		}
		return true;
	}

	private void add(TeamInMatches tim) {// simple addition to each
											// corresponding domain
		tim.computeTotal();
		hit += tim.getHit();
		shot += tim.getShot();
		thirdHit += tim.getThirdHit();
		thirdshot += tim.getThirdshot();
		freeHit += tim.getFreeHit();
		freeshot += tim.getFreeshot();
		offendRebound += tim.getOffensiveRebound();
		defendRebound += tim.getDefensiveRebound();
		rebound += tim.getTotalRebound();
		assist += tim.getAssist();
		steal += tim.getSteal();
		blockShot += tim.getBlock();
		fault += tim.getMiss();
		foul += tim.getFoul();
		// score will be dealt in other place
	}

	private void addOpponent(TeamInMatches tim) {
		ArrayList<PlayerInMatchesPO> players = tim.getTeamInMatchespo()
				.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			PlayerInMatchesPO player = players.get(i);
			hit2 += player.getHit();
			shot2 += player.getShot();
			thirdHit2 += player.getThirdHit();
			thirdshot2 += player.getThirdshot();
			freeHit2 += player.getFreeHit();
			freeshot2 += player.getFreeshot();
			offensiveRebound2 += player.getOffensiveRebound();
			defensiveRebound2 += player.getDefensiveRebound();
			totalRebound2 += player.getTotalRebound();
			assist2 += player.getAssist();
			steal2 += player.getSteal();
			block2 += player.getBlock();
			miss2 += player.getMiss();
			foul2 += player.getFoul();
			// score will be dealt in other place
		}
	}

	private double getHitRate() {
		return (double) hit / shot;
	}

	private double getThirdHitRate() {
		return (double) thirdHit / thirdshot;
	}

	private double getFreeHitRate() {
		return (double) freeHit / freeshot;
	}

	private double getWinRate() {
		return (double) win / appearance;
	}

	private double getOffensiveRound() {// 进攻回合
		return shot
				+ 0.4
				* freeshot
				- 1.07
				* ((double) offendRebound
						/ (offendRebound + defensiveRebound2) * getLost())
				+ 1.07 * fault;
	}

	private double getOffensiveEfficiency() {
		return (double) score / getOffensiveRound() * 100;
	}

	private double getDefensiveRound() {// whether it's right?
		return shot2
				+ 0.4
				* freeshot2
				- 1.07
				* ((double) offensiveRebound2
						/ (offensiveRebound2 + defendRebound) * getLost2())
				+ 1.07 * miss2;
	}

	private double getDefensiveEfficiency() {
		return (double) score2 / getDefensiveRound() * 100;
	}

	private double getOffensiveReboundEfficiency() {// 进攻篮板效率
		return (double) offendRebound
				/ (offendRebound + defensiveRebound2);
	}

	private double getDefensiveReboundEfficiency() {
		return (double) defendRebound
				/ (defendRebound + offensiveRebound2);
	}

	private double getStealEfficiency() {
		return (double) steal / getDefensiveRound() * 100;
	}

	private double getAssistEfficiency() {
		return (double) assist / getOffensiveRound() * 100;
	}

	private int getLost() {
		return shot - hit;
	}

	private int getLost2() {
		return shot2 - hit2;
	}
}
