package vo;

import java.util.Calendar;

import server.po.HeightPO;

public class PlayerVO {
	public PlayerVO(String teamFullName, String teamAbbreviation,
			char division, String zone, String name, int number, String position,
			HeightVO height, int weight, Calendar birth, int age, int exp,
			String school, int appearance, int starting, int playTime, int hit,
			int shot, int thirdHit, int thirdshot, int freeHit, int freeshot,
			int offensiveRebound, int defensiveRebound, int totalRebound,
			int assist, int steal, int block, int miss, int foul, int score,
			double hitRate, double thirdHitRate, double freeHitRate,
			double efficiency, double gmScEfficiency, double realHitRate,
			double shotEfficiency, double reboundRate,
			double offensiveReboundRate, double defensiveReboundRate,
			double assistRate, double stealRate, double blockRate,
			double missRate, double useRate) {
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
	}
	public PlayerVO()
	{
	}
	//Detailed Info
	String teamFullName;
	String teamAbbreviation;//所属球队简称
	char division;//赛区
	String zone;//分区
	String name;//球员姓名
	int number;//球衣号码
	String position;//位置
	HeightVO height;
	int weight;
	Calendar birth;
	int age;
	int exp;//球龄
	String school;
	
	//data
	int appearance;//比赛场数
	int starting;//先发场数
	int playTime;//在场时间
	int hit;//命中
	int shot;//出手
	int thirdHit;//三分命中
	int thirdshot;//三分出手
	int freeHit;//罚球命中
	int freeshot;//罚球出手
	int offensiveRebound;//进攻篮板
	int defensiveRebound;//防守篮板
	int totalRebound;//总篮板
	int assist;//助攻
	int steal;//抢断
	int block;//盖帽
	int miss;//失误
	int foul;//犯规
	int score;
	
	//analysis
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
}
