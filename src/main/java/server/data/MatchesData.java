package server.data;

import java.io.File;
import java.util.ArrayList;

import console.Console;
import server.po.MatchPO;

public  final class MatchesData {
	private static File[] matchesFile;
	private static ArrayList<MatchPO> matchesList = new ArrayList<MatchPO>(1300);
	private static ArrayList<MatchPO> matchesAddList = new ArrayList<MatchPO>(300);
	private static boolean isDEL=false;
	static {
		File f = new File(Console.path+"/matches");
		matchesFile = f.listFiles();
		for (File i : matchesFile) {
			MatchPO newMatchPO=MatchesDataAnalyse.MatchPOMade(DataReader
					.dataReader(i));
			if(newMatchPO!=null){
			matchesList.add(newMatchPO);
			}
		}
	}

	public static int getMatchesList() {
		return matchesList.get(matchesList.size() - 1).getFinalScore()
				.getTeam1();
	}

	public static synchronized ArrayList<MatchPO> getMatchPOList() {
		return matchesList;
	}
	public static synchronized void add(File f){
		MatchPO newMatchPO=MatchesDataAnalyse.MatchPOMade(DataReader
				.dataReader(f));
		if(newMatchPO!=null){
		matchesList.add(newMatchPO);
		matchesAddList.add(newMatchPO);
		}
	}
	public  static synchronized void remove(String name){
		isDEL=true;
		for(int i=0;i<matchesList.size();i++){
			if(matchesList.get(i).getFileName().equals(name)){
				matchesList.remove(i);
				matchesAddList.clear();
				break;
			}
		}
	}
	public  static synchronized ArrayList<MatchPO> getNewMatch(){
		ArrayList<MatchPO> result=(ArrayList<MatchPO>) matchesAddList.clone();
		matchesAddList.clear();
		return result;
	}
	public  static synchronized boolean isDEL(){
		if(isDEL==true){
			isDEL=false;
			return true;
		}
		else{
			return false;
		}
	}
}
