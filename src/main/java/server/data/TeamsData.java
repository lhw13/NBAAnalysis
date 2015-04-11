package server.data;

import java.io.File;
import java.util.ArrayList;

import console.Console;
import server.po.TeamPO;

public class TeamsData {
	private static File teamsFile;
	private static ArrayList<TeamPO> teamPOList;
	static {
		teamsFile = new File(Console.path+"/teams/teams");
		teamPOList = TeamsDataAnalyse.teamPOListMade(DataReader
				.dataReader(teamsFile));
	}

	public static String get() {
		return teamPOList.get(0).getAbbreviation();
	}

	public static ArrayList<TeamPO> getTeamPOList() {
		return teamPOList;
	}

}
