package server.data;

import java.util.ArrayList;
import java.util.Calendar;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.ScorePO;
import server.po.TeamInMatchesPO;

public class MatchesDataAnalyse {
	public static MatchPO MatchPOMade(ArrayList<String> matchData) {
		String[] fileName = matchData.get(0).split("_");
		String season = fileName[0];
		String monthAndDay = fileName[1];
		Calendar date = getDate(season, monthAndDay);
		String abbreviation1 = fileName[2].split("-")[0];
		String abbreviation2 = fileName[2].split("-")[1];
		if(abbreviation1.equals("NOH")){
			abbreviation1="NOP";
		}
		if(abbreviation2.equals("NOH")){
			abbreviation2="NOP";
		}
		String[] firstLine = matchData.get(1).split(";");
		ScorePO finalScore = stringToScorePO(firstLine[2]);
		String[] secondLine = matchData.get(2).split(";");
		ArrayList<ScorePO> scores = new ArrayList<ScorePO>();
		for (String str : secondLine) {
			scores.add(stringToScorePO(str));
		}

		TeamInMatchesPO team1;
		TeamInMatchesPO team2;
		ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>();
		ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>();
		int line = 4;
		while (!(matchData.get(line).equals(abbreviation2)||matchData.get(line).equals("NOH"))) {
			// 球队一的队员情况
			players1.add(stringToPlayerInMatchesPO(matchData.get(line)));
			line++;
		}
		team1 = new TeamInMatchesPO(abbreviation1, players1);

		line = line + 1;
		while (matchData.get(line).trim().length() > 0) {
			// 球队二的队员情况
			players2.add(stringToPlayerInMatchesPO(matchData.get(line)));
			line++;
			if (line >= matchData.size()) {
				break;
			}
		}
		team2 = new TeamInMatchesPO(abbreviation2, players2);

		MatchPO matchPO= new MatchPO(season, date, finalScore, scores, team1, team2);
		matchPO.setFileName(matchData.get(0));
		return matchPO;
	}

	private static int stringToInt(String str) {
		int result;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			result = -1;
		}
		return result;
	}

	private static ScorePO stringToScorePO(String score) {
		String scoreTeam1 = score.split("-")[0];
		String scoreTeam2 = score.split("-")[1];
		return new ScorePO(stringToInt(scoreTeam1), stringToInt(scoreTeam2));
	}

	private static PlayerInMatchesPO stringToPlayerInMatchesPO(
			String playerInMatches) {
		String[] player = playerInMatches.split(";");
		String name = player[0];
		char position;
		if (player[1].isEmpty()) {
			position = ' ';
		} else {
			position = player[1].charAt(0);
		}
		String playTime = player[2];
		int playTimeBySeconds = -1;// 脏数据取负值
		if (playTime.split(":").length == 2) {
			playTimeBySeconds = stringToInt(playTime.split(":")[0]) * 60
					+ stringToInt(playTime.split(":")[1]);
		}
		int hit = stringToInt(player[3]);// 命中
		int shot = stringToInt(player[4]);// 出手
		int thirdHit = stringToInt(player[5]);
		int thirdshot = stringToInt(player[6]);
		int freeHit = stringToInt(player[7]);
		int freeshot = stringToInt(player[8]);
		int offensiveRebound = stringToInt(player[9]);
		int defensiveRebound = stringToInt(player[10]);
		int totalRebound = stringToInt(player[11]);
		int assist = stringToInt(player[12]);
		int steal = stringToInt(player[13]);
		int block = stringToInt(player[14]);
		int miss = stringToInt(player[15]);
		int foul = stringToInt(player[16]);
		int score = stringToInt(player[17]);
		return new PlayerInMatchesPO(name, position, playTimeBySeconds, hit,
				shot, thirdHit, thirdshot, freeHit, freeshot, offensiveRebound,
				defensiveRebound, totalRebound, assist, steal, block, miss,
				foul, score);
	}

	public static Calendar getDate(String season, String monthAndDay) {
		int year1 = stringToInt(season.split("-")[0]);
		int year2 = stringToInt(season.split("-")[1]);
		int month = stringToInt(monthAndDay.split("-")[0]);
		int day = stringToInt(monthAndDay.split("-")[1]);
		int year = 2000;
		//if (month >= 11)//changed by lhw 
		if (month >= 10){
			year = year + year1;
		} else {
			year = year + year2;
		}
		Calendar result = Calendar.getInstance();
		result.set(year, month - 1, day, 0, 0, 0);
		return result;
	}
}
