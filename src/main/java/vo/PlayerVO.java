package vo;

import java.util.ArrayList;
import java.util.Calendar;

import server.po.HeightPO;
import server.po.MatchPO;

public class PlayerVO {
	public PlayerVO(String teamFullName, String teamAbbreviation,
			char division, String zone, String name, int number,
			String position, HeightVO height, int weight, Calendar birth,
			int age, int exp, String school, int appearance, int starting,
			int playTime, int hit, int shot, int thirdHit, int thirdshot,
			int freeHit, int freeshot, int offensiveRebound,
			int defensiveRebound, int totalRebound, int assist, int steal,
			int block, int miss, int foul, int score, double hitRate,
			double thirdHitRate, double freeHitRate, double efficiency,
			double gmScEfficiency, double realHitRate, double shotEfficiency,
			double reboundRate, double offensiveReboundRate,
			double defensiveReboundRate, double assistRate, double stealRate,
			double blockRate, double missRate, double useRate, double towPairs, ArrayList<MatchPO> matches, 
			double scorePromotion, double reboundPromotion, double assistPromotion, int highestScore) {
		super();
		this.teamFullName = teamFullName;
		this.teamAbbreviation = teamAbbreviation;
		this.division = division;
		this.zone = zone;
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
		this.appearance = appearance;
		this.starting = starting;
		this.playTime = playTime;
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
		this.efficiency = efficiency;
		this.gmScEfficiency = gmScEfficiency;
		this.realHitRate = realHitRate;
		this.shotEfficiency = shotEfficiency;
		this.reboundRate = reboundRate;
		this.offensiveReboundRate = offensiveReboundRate;
		this.defensiveReboundRate = defensiveReboundRate;
		this.assistRate = assistRate;
		this.stealRate = stealRate;
		this.blockRate = blockRate;
		this.missRate = missRate;
		this.useRate = useRate;
		this.towPairs = towPairs;
		this.matches = matches;
		this.scorePromotion = scorePromotion;
		this.reboundPromotion = reboundPromotion;
		this.assistPromotion = assistPromotion;
		this.highestScore = highestScore;
	}

	public PlayerVO() {
	}

	public void add(PlayerVO adder) {
		this.weight += adder. weight;
		this.age += adder. age;
		this.exp += adder. exp;
		this.appearance += adder. appearance;
		this.starting += adder. starting;
		this.playTime += adder. playTime;
		this.hit += adder. hit;
		this.shot += adder. shot;
		this.thirdHit += adder. thirdHit;
		this.thirdshot += adder. thirdshot;
		this.freeHit += adder. freeHit;
		this.freeshot += adder. freeshot;
		this.offensiveRebound += adder. offensiveRebound;
		this.defensiveRebound += adder. defensiveRebound;
		this.totalRebound += adder. totalRebound;
		this.assist += adder. assist;
		this.steal += adder. steal;
		this.block += adder. block;
		this.miss += adder. miss;
		this.foul += adder. foul;
		this.score += adder. score;
		this.hitRate += adder. hitRate;
		this.thirdHitRate += adder. thirdHitRate;
		this.freeHitRate += adder. freeHitRate;
		this.efficiency += adder. efficiency;
		this.gmScEfficiency += adder. gmScEfficiency;
		this.realHitRate += adder. realHitRate;
		this.shotEfficiency += adder. shotEfficiency;
		this.reboundRate += adder. reboundRate;
		this.offensiveReboundRate += adder. offensiveReboundRate;
		this.defensiveReboundRate += adder. defensiveReboundRate;
		this.assistRate += adder. assistRate;
		this.stealRate += adder. stealRate;
		this.blockRate += adder. blockRate;
		this.missRate += adder. missRate;
		this.useRate += adder. useRate;
		this.towPairs += adder. towPairs;
		this.scorePromotion += adder. scorePromotion;
		this.reboundPromotion += adder. reboundPromotion;
		this.assistPromotion += adder. assistPromotion;
		this.highestScore += adder. highestScore;
	}
	
	public void divide(int divisor) {
		this.weight /= divisor ;
		this.age /= divisor ;
		this.exp /= divisor ;
		this.appearance /= divisor ;
		this.starting /= divisor ;
		this.playTime /= divisor ;
		this.hit /= divisor ;
		this.shot /= divisor ;
		this.thirdHit /= divisor ;
		this.thirdshot /= divisor ;
		this.freeHit /= divisor ;
		this.freeshot /= divisor ;
		this.offensiveRebound /= divisor ;
		this.defensiveRebound /= divisor ;
		this.totalRebound /= divisor ;
		this.assist /= divisor ;
		this.steal /= divisor ;
		this.block /= divisor ;
		this.miss /= divisor ;
		this.foul /= divisor ;
		this.score /= divisor ;
		this.hitRate /= divisor ;
		this.thirdHitRate /= divisor ;
		this.freeHitRate /= divisor ;
		this.efficiency /= divisor ;
		this.gmScEfficiency /= divisor ;
		this.realHitRate /= divisor ;
		this.shotEfficiency /= divisor ;
		this.reboundRate /= divisor ;
		this.offensiveReboundRate /= divisor ;
		this.defensiveReboundRate /= divisor ;
		this.assistRate /= divisor ;
		this.stealRate /= divisor ;
		this.blockRate /= divisor ;
		this.missRate /= divisor ;
		this.useRate /= divisor ;
		this.towPairs /= divisor ;
		this.scorePromotion /= divisor ;
		this.reboundPromotion /= divisor ;
		this.assistPromotion /= divisor ;
		this.highestScore /= divisor ;
	}
	// Detailed Info
	String teamFullName;
	String teamAbbreviation;// 所属球队简称
	char division;// 赛区
	String zone;// 分区
	String name;// 球员姓名
	int number;// 球衣号码
	String position;// 位置
	HeightVO height;
	int weight;
	Calendar birth;
	int age;
	int exp;// 球龄
	String school;

	// data
	int appearance;// 比赛场数
	int starting;// 先发场数
	int playTime;// 在场时间
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
	int foul;// 犯规
	int score;

	// analysis
	double hitRate;
	double thirdHitRate;
	double freeHitRate;
	double efficiency;
	double gmScEfficiency;
	double realHitRate;
	double shotEfficiency;
	double reboundRate;
	double offensiveReboundRate;
	double defensiveReboundRate;
	double assistRate;
	double stealRate;
	double blockRate;
	double missRate;
	double useRate;
	double towPairs;
	
	//iteration 2
	ArrayList<MatchPO> matches;
	double scorePromotion;
	double reboundPromotion;
	double assistPromotion;
	
	int highestScore;

	public String getTeamFullName() {
		return teamFullName;
	}

	public String getTeamAbbreviation() {
		return teamAbbreviation;
	}

	public char getDivision() {
		return division;
	}

	public String getZone() {
		return zone;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public HeightVO getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public Calendar getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public int getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
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

	public double getEfficiency() {
		return efficiency;
	}

	public double getGmScEfficiency() {
		return gmScEfficiency;
	}

	public double getRealHitRate() {
		return realHitRate;
	}

	public double getShotEfficiency() {
		return shotEfficiency;
	}

	public double getReboundRate() {
		return reboundRate;
	}

	public double getOffensiveReboundRate() {
		return offensiveReboundRate;
	}

	public double getDefensiveReboundRate() {
		return defensiveReboundRate;
	}

	public double getAssistRate() {
		return assistRate;
	}

	public double getStealRate() {
		return stealRate;
	}

	public double getBlockRate() {
		return blockRate;
	}

	public double getMissRate() {
		return missRate;
	}

	public double getUseRate() {
		return useRate;
	}

	public double getTowPairs() {
		return towPairs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlayerVO [teamFullName=" + teamFullName + ", teamAbbreviation="
				+ teamAbbreviation + ", division=" + division + ", zone="
				+ zone + ", name=" + name + ", number=" + number
				+ ", position=" + position + ", height=" + height + ", weight="
				+ weight + ", birth=" + birth + ", age=" + age + ", exp=" + exp
				+ ", school=" + school + ", appearance=" + appearance
				+ ", starting=" + starting + ", playTime=" + playTime
				+ ", hit=" + hit + ", shot=" + shot + ", thirdHit=" + thirdHit
				+ ", thirdshot=" + thirdshot + ", freeHit=" + freeHit
				+ ", freeshot=" + freeshot + ", offensiveRebound="
				+ offensiveRebound + ", defensiveRebound=" + defensiveRebound
				+ ", totalRebound=" + totalRebound + ", assist=" + assist
				+ ", steal=" + steal + ", block=" + block + ", miss=" + miss
				+ ", foul=" + foul + ", score=" + score + ", hitRate="
				+ hitRate + ", thirdHitRate=" + thirdHitRate + ", freeHitRate="
				+ freeHitRate + ", efficiency=" + efficiency
				+ ", gmScEfficiency=" + gmScEfficiency + ", realHitRate="
				+ realHitRate + ", shotEfficiency=" + shotEfficiency
				+ ", reboundRate=" + reboundRate + ", offensiveReboundRate="
				+ offensiveReboundRate + ", defensiveReboundRate="
				+ defensiveReboundRate + ", assistRate=" + assistRate
				+ ", stealRate=" + stealRate + ", blockRate=" + blockRate
				+ ", missRate=" + missRate + ", useRate=" + useRate
				+ ", towPairs=" + towPairs + "]";
	}

	public ArrayList<MatchPO> getMatches() {
		return matches;
	}

	public double getScorePromotion() {
		return scorePromotion;
	}

	public double getReboundPromotion() {
		return reboundPromotion;
	}

	public double getAssistPromotion() {
		return assistPromotion;
	}
	public double getValue(String value) {
		switch(value) {
		case "point": return (double)score/(double)appearance;
		case "score": return (double)score/(double)appearance;
		case "rebound": return (double)totalRebound/(double)appearance;		
		case "assist": return (double)assist/(double)appearance;
		
		default: return 0;
		}
	}
	public double getUpgradeRate(String value) {
		switch(value) {
		case "point": return scorePromotion;
		case "score": return scorePromotion;
		case "rebound": return reboundPromotion;	
		case "assist": return assistPromotion;
		default: return 0;
		}
	}

	public int getHighestScore() {
		return highestScore;
	}
}
