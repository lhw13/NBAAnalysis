package server.data;

import java.io.File;
import java.util.ArrayList;

import console.Console;
import server.po.MatchPO;

public class MatchesData {
	private static File[] matchesFile;
	private static ArrayList<MatchPO> matchesList = new ArrayList<MatchPO>();
	static {
		File f = new File(Console.path+"/matches");
		matchesFile = f.listFiles();
		for (File i : matchesFile) {
			matchesList.add(MatchesDataAnalyse.MatchPOMade(DataReader
					.dataReader(i)));
		}
	}

	public static int getMatchesList() {
		return matchesList.get(matchesList.size() - 1).getFinalScore()
				.getTeam1();
	}

	public static  ArrayList<MatchPO> getMatchPOList() {
		return matchesList;
	}
	public static synchronized void add(File f){
		matchesList.add(MatchesDataAnalyse.MatchPOMade(DataReader
				.dataReader(f)));
	}
	public static synchronized void remove(String name){
		
	}
}
