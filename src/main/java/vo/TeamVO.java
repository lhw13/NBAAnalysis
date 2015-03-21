package vo;

public class TeamVO {
	public TeamVO(String fullName, String abbreviation, String location,
			char division, String zone, String home, int setupTime,
			int appearance, int hit, int shot, int thirdHit, int thirdshot,
			int freeHit, int freeshot, int offensiveRebound,
			int defensiveRebound, int totalRebound, int assist, int steal,
			int block, int miss, int foul, int score, double hitRate,
			double thirdHitRate, double freeHitRate, double winRate,
			double offensiveRound, double offensiveEfficiency,
			double defensiveRound, double defensiveEfficiency,
			double offensiveReboundEfficiency,
			double defensiveReboundEfficiency, double stealEfficiency,
			double assistEfficiency) {
		super();
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.location = location;
		this.division = division;
		this.zone = zone;
		this.home = home;
		this.setupTime = setupTime;
		this.appearance = appearance;
		this.hit = hit;
		this.shot = shot;
		this.thirdHit = thirdHit;
		this.thirdshot = thirdshot;
		this.freeHit = freeHit;
		this.freeshot = freeshot;
		this.offensiveRebound = offensiveRebound;
		this.defensiveRebound = defensiveRebound;
		this.totalRebound = totalRebound;
		this.assist = assist;
		this.steal = steal;
		this.block = block;
		this.miss = miss;
		this.foul = foul;
		this.score = score;
		this.hitRate = hitRate;
		this.thirdHitRate = thirdHitRate;
		this.freeHitRate = freeHitRate;
		this.winRate = winRate;
		this.offensiveRound = offensiveRound;
		this.offensiveEfficiency = offensiveEfficiency;
		this.defensiveRound = defensiveRound;
		this.defensiveEfficiency = defensiveEfficiency;
		this.offensiveReboundEfficiency = offensiveReboundEfficiency;
		this.defensiveReboundEfficiency = defensiveReboundEfficiency;
		this.stealEfficiency = stealEfficiency;
		this.assistEfficiency = assistEfficiency;
	}

	public TeamVO() {
	}

	String fullName;// 球队全称
	String abbreviation;// 简称
	String location;// 所在地
	char division;// 赛区
	String zone;// 分区
	String home;// 主场
	int setupTime;// 建立时间
	int appearance;// 比赛场数
	int hit;// 命中
	int shot;// 出手
	int thirdHit;// 三分命中
	int thirdshot;// 三分出手
	int freeHit;// 罚球命中
	int freeshot;// 罚球出手
	int offensiveRebound;// 进攻篮板
	int defensiveRebound;// 防守篮板
	int totalRebound;// 总篮板
	int assist;// 助攻
	int steal;// 抢断
	int block;// 盖帽
	int miss;// 失误
	int foul;// 失误
	int score;

	double hitRate;// 命中率
	double thirdHitRate;// 三分命中率
	double freeHitRate;// 罚球命中率
	double winRate;// 胜率
	double offensiveRound;// 进攻回合
	double offensiveEfficiency;// 进攻效率
	double defensiveRound;// 防守回合
	double defensiveEfficiency;// 防守效率
	double offensiveReboundEfficiency;// 进攻篮板效率
	double defensiveReboundEfficiency;// 防守篮板效率
	double stealEfficiency;// 抢断效率
	double assistEfficiency;// 助攻效率

	public String getFullName() {
		return fullName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public int getAppearance() {
		return appearance;
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

	public double getHitRate() {
		return hitRate;
	}

	public double getThirdHitRate() {
		return thirdHitRate;
	}

	public double getFreeHitRate() {
		return freeHitRate;
	}

	public double getWinRate() {
		return winRate;
	}

	public double getOffensiveRound() {
		return offensiveRound;
	}

	public double getOffensiveEfficiency() {
		return offensiveEfficiency;
	}

	public double getDefensiveRound() {
		return defensiveRound;
	}

	public double getDefensiveEfficiency() {
		return defensiveEfficiency;
	}

	public double getOffensiveReboundEfficiency() {
		return offensiveReboundEfficiency;
	}

	public double getDefensiveReboundEfficiency() {
		return defensiveReboundEfficiency;
	}

	public double getStealEfficiency() {
		return stealEfficiency;
	}

	public double getAssistEfficiency() {
		return assistEfficiency;
	}

	public String getLocation() {
		return location;
	}

	public char getDivision() {
		return division;
	}

	public String getZone() {
		return zone;
	}

	public String getHome() {
		return home;
	}

	public int getSetupTime() {
		return setupTime;
	}

	@Override
	public String toString() {
		return "TeamVO [fullName=" + fullName + ", abbreviation="
				+ abbreviation + ", location=" + location + ", division="
				+ division + ", zone=" + zone + ", home=" + home
				+ ", setupTime=" + setupTime + ", appearance=" + appearance
				+ ", hit=" + hit + ", shot=" + shot + ", thirdHit=" + thirdHit
				+ ", thirdshot=" + thirdshot + ", freeHit=" + freeHit
				+ ", freeshot=" + freeshot + ", offensiveRebound="
				+ offensiveRebound + ", defensiveRebound=" + defensiveRebound
				+ ", totalRebound=" + totalRebound + ", assist=" + assist
				+ ", steal=" + steal + ", block=" + block + ", miss=" + miss
				+ ", foul=" + foul + ", score=" + score + ", hitRate="
				+ hitRate + ", thirdHitRate=" + thirdHitRate + ", freeHitRate="
				+ freeHitRate + ", winRate=" + winRate + ", offensiveRound="
				+ offensiveRound + ", offensiveEfficiency="
				+ offensiveEfficiency + ", defensiveRound=" + defensiveRound
				+ ", defensiveEfficiency=" + defensiveEfficiency
				+ ", offensiveReboundEfficiency=" + offensiveReboundEfficiency
				+ ", defensiveReboundEfficiency=" + defensiveReboundEfficiency
				+ ", stealEfficiency=" + stealEfficiency
				+ ", assistEfficiency=" + assistEfficiency + "]";
	}
}
