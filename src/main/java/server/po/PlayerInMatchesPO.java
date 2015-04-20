package server.po;

public  final class PlayerInMatchesPO {
	public PlayerInMatchesPO() {
	}

	public PlayerInMatchesPO(String name, char position, int playTime, int hit,
			int shot, int thirdHit, int thirdshot, int freeHit, int freeshot,
			int offensiveRebound, int defensiveRebound, int totalRebound,
			int assist, int steal, int block, int miss, int foul, int score) {
		super();
		this.name = name;
		this.position = position;
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
	}

	String name;
	char position;
	int playTime;
	int hit;// 命中
	int shot;// 出手
	int thirdHit;
	int thirdshot;
	int freeHit;
	int freeshot;
	int offensiveRebound;
	int defensiveRebound;
	int totalRebound;
	int assist;
	int steal;
	int block;
	int miss;
	int foul;
	int score;

	public String toString() {
		return name + ";" + position + ";" + playTime + ";" + hit + ";" + shot
				+ ";" + thirdHit + ";" + thirdshot + ";" + freeHit + ";"
				+ freeshot + ";篮板" + offensiveRebound + ";" + defensiveRebound
				+ ";" + totalRebound + ";" + assist + ";" + steal + ";" + block
				+ ";" + miss + ";" + foul + ";" + score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getPosition() {
		return position;
	}

	public void setPosition(char position) {
		this.position = position;
	}

	public int getPlayTime() {
		return playTime;
	}
	
	public int getPlayTimeMinute() {
		return playTime/60;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getThirdHit() {
		return thirdHit;
	}

	public void setThirdHit(int thirdHit) {
		this.thirdHit = thirdHit;
	}

	public int getThirdshot() {
		return thirdshot;
	}

	public void setThirdshot(int thirdshot) {
		this.thirdshot = thirdshot;
	}

	public int getFreeHit() {
		return freeHit;
	}

	public void setFreeHit(int freeHit) {
		this.freeHit = freeHit;
	}

	public int getFreeshot() {
		return freeshot;
	}

	public void setFreeshot(int freeshot) {
		this.freeshot = freeshot;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public void setOffensiveRebound(int offensiveRebound) {
		this.offensiveRebound = offensiveRebound;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public void setDefensiveRebound(int defensiveRebound) {
		this.defensiveRebound = defensiveRebound;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public void setTotalRebound(int totalRebound) {
		this.totalRebound = totalRebound;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getMiss() {
		return miss;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public int getFoul() {
		return foul;
	}

	public void setFoul(int foul) {
		this.foul = foul;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSteal() {
		return steal;
	}

	public void setSteal(int steal) {
		this.steal = steal;
	}
}
