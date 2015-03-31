package server.data;

import java.io.File;
import java.util.ArrayList;

import server.po.MatchPO;
import server.po.PlayerPO;

public class PlayersData {
	private static File[] PlayersFile;
	private static ArrayList<PlayerPO> playersList = new ArrayList<PlayerPO>();
	static {
		File f = new File("./nba/players/info");
		PlayersFile = f.listFiles();
		for (File i : PlayersFile) {
			playersList.add(PlayersDataAnalyse.PlayerPOMade(DataReader
					.dataReader(i)));
		}
	}

	public static int getData() {
		System.out.println(playersList.get(0).getName());
		System.out.println(playersList.get(0).getNumber());
		System.out.println(playersList.get(0).getPosition());
		System.out.println(playersList.get(0).getWeight());
		System.out.println(playersList.get(0).getBirth().getTime());
		return playersList.get(0).getAge();
	}

	public static ArrayList<PlayerPO> getPlayerPOList() {
		return playersList;
	}
}
