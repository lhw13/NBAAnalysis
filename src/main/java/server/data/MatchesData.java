package server.data;

import java.io.File;
import java.util.ArrayList;

import server.po.MatchPO;

public class MatchesData {
	private static File[] matchesFile;
	private static ArrayList<MatchPO> matchesList=new ArrayList<MatchPO>();
	static {
		File f = new File("nba/matches");
		matchesFile = f.listFiles();
		for (File i : matchesFile) {
			matchesList.add(MatchesDataAnalyse.MatchPOMade(DataReader
					.dataReader(i)));
		}
	}
	public static int getMatchesList(){
		return matchesList.get(matchesList.size()-1).getFinalScore().getTeam1();
	}
	public ArrayList<MatchPO> getMatchPOList(){
		return matchesList;
	}
}
