package server.businesslogic;

import java.util.ArrayList;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.TeamInMatchesPO;
import server.po.TeamPO;
import test.data.*;
import vo.TeamVO;

public  final class Team {
	public Team(TeamPO teamPO) {
		super();
		this.teamPO = teamPO;
	}

	boolean newData = true;
	boolean added = false;
	TeamPO teamPO;
	ArrayList<MatchPO> matches = new ArrayList<MatchPO>(85);
	ArrayList<TeamInMatches> thisTeam = new ArrayList<TeamInMatches>(85);
	ArrayList<TeamInMatches> opponentTeam = new ArrayList<TeamInMatches>(85);
	
	ArrayList<TeamInMatches> thisTeamPast = new ArrayList<TeamInMatches>();
	ArrayList<TeamInMatches> opponentTeamPast = new ArrayList<TeamInMatches>();
	
	ArrayList<TeamInMatches> thisTeamNew = new ArrayList<TeamInMatches>(10);
	ArrayList<TeamInMatches> opponentTeamNew = new ArrayList<TeamInMatches>(10);

	// this team's data
	int appearance = 0;// 比赛场数
	int hit = 0;// 命中
	int chuShou = 0;// 出手
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
	
	double offendRound = 0;
	double defendRound = 0;

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
				teamPO.getHome(), teamPO.getSetupTime(), appearance, hit, chuShou,
				thirdHit, thirdshot, freeHit, freeshot, offendRebound,
				defendRebound, rebound, assist, steal, blockShot, fault,
				foul, score, getHitRate(), getThree(), getPenalty(),
				getWinRate(), getOffendRound(), getOffendEfficient(),
				getDefendRound(), getDefendEfficient(),
				getOffendReboundEfficient(),
				getDefendReboundEfficient(), getStealEfficient(),
				getAssistEfficient(),matches);
	}
	
	public TeamHighInfo toHighInfo() {
		TeamHighInfo thi = new TeamHighInfo();
		thi.setAssistEfficient(getAssistEfficient());
		thi.setDefendEfficient(getDefendEfficient());
		thi.setDefendReboundEfficient(getDefendReboundEfficient());
		thi.setOffendEfficient(getOffendEfficient());
		thi.setOffendReboundEfficient(getOffendReboundEfficient());
		thi.setOffendRound(getOffendRound());
		thi.setStealEfficient(getStealEfficient());
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
		tni.setPenalty(getPenalty());
		tni.setPoint(score);
		tni.setRebound(rebound);
		tni.setShot(getHitRate());
		tni.setSteal(steal);
		tni.setTeamName(teamPO.getAbbreviation());
		tni.setThree(getThree());
		return tni;
	}
	
	public TeamNormalInfo toNormalInfoAvg() {
		TeamNormalInfo tni = new TeamNormalInfo();
		tni.setAssist((double)assist/appearance);
		tni.setBlockShot((double)blockShot/appearance);
		tni.setDefendRebound((double)defendRebound/appearance);
		tni.setFault((double)fault/appearance);
		tni.setFoul((double)foul/appearance);
		tni.setNumOfGame(appearance);
		tni.setOffendRebound((double)offendRebound/appearance);
		tni.setPenalty(getPenalty());
		tni.setPoint((double)score/appearance);
		tni.setRebound((double)rebound/appearance);
		tni.setShot(getHitRate());
		tni.setSteal((double)steal/appearance);
		tni.setTeamName(teamPO.getAbbreviation());
		tni.setThree(getThree());
		return tni;
	}

	public void addThisTeam(TeamInMatches tim) {
		newData=true;
		if(BLController.isDEL)
			thisTeam.add(tim);
		else
			thisTeamNew.add(tim);
	}

	public void addOpponentTeam(TeamInMatches tim) {
		if(BLController.isDEL)
			opponentTeam.add(tim);
		else
			opponentTeamNew.add(tim);
	}
	
	public void addThisTeamPast(TeamInMatches tim) {
		thisTeamPast.add(tim);
	}

	public void addOpponentTeamPast(TeamInMatches tim) {
		opponentTeamPast.add(tim);
	}
	
	public void addThisTeamNew(TeamInMatches tim) {
		thisTeamNew.add(tim);
	}

	public void addOpponentTeamNew(TeamInMatches tim) {
		opponentTeamNew.add(tim);
	}
	
	public void addMatch(MatchPO mp)
	{
		matches.add(mp);
	}

	public String getFullName() {
		return teamPO.getFullName();
	}

	public TeamPO getTeamPO() {
		return teamPO;
	}

	public boolean anaylse() {// the core of team analysis
		if(BLController.isDEL)
		{
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
		}
		//increment
		else
		{
			int size = thisTeamNew.size();
			appearance += size;
			for (int i = 0; i < size; i++) {// for more info, see Player.java
				TeamInMatches tim = thisTeamNew.get(i);
				add(tim);
				score += tim.getFinalScore();
				if (tim.getWin() > 0)
					win++;
			}
			thisTeamNew.clear();
			for (int i = 0; i < size; i++) {
				TeamInMatches tim = opponentTeamNew.get(i);
				addOpponent(tim);
				score2 += tim.getFinalScore();
				if (tim.getWin() > 0)
					win2++;
			}
			opponentTeamNew.clear();
		}
		newData=false;
		return true;
	}

	private void add(TeamInMatches tim) {// simple addition to each
											// corresponding domain
		//tim.computeTotal();
		hit += tim.getHit();
		chuShou += tim.getShot();
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
		playTime += tim.getPlayTime();
		offendRound += tim.getOffendRound();
		// score will be dealt in other place
	}

	private void addOpponent(TeamInMatches tim) {
			hit2 += tim.getHit();
			shot2 += tim.getShot();
			thirdHit2 += tim.getThirdHit();
			thirdshot2 += tim.getThirdshot();
			freeHit2 += tim.getFreeHit();
			freeshot2 += tim.getFreeshot();
			offensiveRebound2 += tim.getOffensiveRebound();
			defensiveRebound2 += tim.getDefensiveRebound();
			totalRebound2 += tim.getTotalRebound();
			assist2 += tim.getAssist();
			steal2 += tim.getSteal();
			block2 += tim.getBlock();
			miss2 += tim.getMiss();
			foul2 += tim.getFoul();
			defendRound += tim.getOffendRound();
			// score will be dealt in other place
	}

	private double getHitRate() {
		return (double) hit / chuShou;
	}
	
	public double getShot() {
		return (double) hit / chuShou;
	}

	public double getThree() {
		return (double) thirdHit / thirdshot;
	}

	public double getPenalty() {
		return (double) freeHit / freeshot;
	}

	public double getWinRate() {
		return (double) win / appearance;
	}

	public double getOffendRound() {// 进攻回合
		return offendRound;
	}

	public double getOffendEfficient() {
		return (double) score / getOffendRound() * 100;
	}

	public double getDefendRound() {// whether it's right?
		return defendRound;
	}

	public double getDefendEfficient() {
		return (double) score2 / getDefendRound() * 100;
	}

	public double getOffendReboundEfficient() {// 进攻篮板效率
		return (double) offendRebound
				/ (offendRebound + defensiveRebound2);
	}

	public double getDefendReboundEfficient() {
		return (double) defendRebound
				/ (defendRebound + offensiveRebound2);
	}

	public double getStealEfficient() {
		return (double) steal / getDefendRound() * 100;
	}

	public double getAssistEfficient() {
		return (double) assist / getOffendRound() * 100;
	}

	private int getLost() {
		return chuShou - hit;
	}

	private int getLost2() {
		return shot2 - hit2;
	}

	public boolean isAnalysed() {
		return added;
	}

	public ArrayList<TeamInMatches> getThisTeam() {
		return thisTeam;
	}

	public ArrayList<TeamInMatches> getOpponentTeam() {
		return opponentTeam;
	}

	public int getAppearance() {
		return appearance;
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

	public int getOffendRebound() {
		return offendRebound;
	}

	public int getDefendRebound() {
		return defendRebound;
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
	
	public int getPoint() {
		return score;
	}

	public int getWin() {
		return win;
	}

	public int getPlayTime() {
		return playTime;
	}

	public int getHit2() {
		return hit2;
	}

	public int getShot2() {
		return shot2;
	}

	public int getThirdHit2() {
		return thirdHit2;
	}

	public int getThirdshot2() {
		return thirdshot2;
	}

	public int getFreeHit2() {
		return freeHit2;
	}

	public int getFreeshot2() {
		return freeshot2;
	}

	public int getOffensiveRebound2() {
		return offensiveRebound2;
	}

	public int getDefensiveRebound2() {
		return defensiveRebound2;
	}

	public int getTotalRebound2() {
		return totalRebound2;
	}

	public int getAssist2() {
		return assist2;
	}

	public int getSteal2() {
		return steal2;
	}

	public int getBlock2() {
		return block2;
	}

	public int getMiss2() {
		return miss2;
	}

	public int getFoul2() {
		return foul2;
	}

	public int getScore2() {
		return score2;
	}

	public int getWin2() {
		return win2;
	}
	
	public String getAbbreviation() {
		return teamPO.getAbbreviation();
	}
	
	public String getLeague() {
		char c = teamPO.getDivision();
		if(c=='W')
			return "West";
		else
			return "East";
	}
	
	public double getValue(String value) {
		switch(value) {
		case "point": return score/appearance;
		case "score": return score/appearance;
		case "rebound": return rebound/appearance;
		case "blockShot": return blockShot/appearance;
		case "assist": return assist/appearance;
		case "steal": return steal/appearance;
		case "foul": return foul/appearance;
		case "fault": return fault/appearance;
		case "three": return getThree();
		case "shot": return getShot();
		case "penalty": return getPenalty();
		case "defendRebound": return getDefendRebound()/appearance;
		case "offendRebound": return getOffendRebound()/appearance;
		default: return 0;
		}
	}
}
