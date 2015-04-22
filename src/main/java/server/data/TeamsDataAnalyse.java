package server.data;

import java.util.ArrayList;

import server.po.TeamPO;

public  final class TeamsDataAnalyse {
	public static ArrayList<TeamPO> teamPOListMade(ArrayList<String> teamsList) {
		ArrayList<TeamPO> teamPOList = new ArrayList<TeamPO>(35);
		String fullName;
		String abbreviation;
		String location;
		char division;
		String zone;
		String home;
		int setupTime;
		for (int i = 2; i < teamsList.size() - 1; i++) {
			String[] oneLine = teamsList.get(i).replaceAll("║", "").split("\\│");
			fullName = oneLine[0].trim();
			abbreviation = oneLine[1].trim();
			location = oneLine[2].trim();
			division = oneLine[3].trim().charAt(0);
			zone = oneLine[4].trim();
			home = oneLine[5].trim();
			setupTime = stringToInt(oneLine[6].trim());
			teamPOList.add(new TeamPO(fullName, abbreviation, location,
					division, zone, home, setupTime));
		}
		return teamPOList;
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
}
