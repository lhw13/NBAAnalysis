package server.data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import server.po.MatchPO;
import server.po.PlayerPO;
import server.po.TeamPO;
import dataservice.DataService;

public class DataController implements DataService {
	public HashMap<String, PlayerPO> getAllPlayers() {
		ArrayList<PlayerPO> playerPOList = PlayersData.getPlayerPOList();
		int n = playerPOList.size();
		HashMap<String, PlayerPO> m = new HashMap<String, PlayerPO>(
				(int) (n / 0.74) + 1, 0.75f);
		for (int i = 0; i < n; i++) {
			m.put(playerPOList.get(i).getName(), playerPOList.get(i));
		}
		return m;
	}

	public HashMap<String, TeamPO> getAllTeams() {
		ArrayList<TeamPO> teamPOList = TeamsData.getTeamPOList();
		int n = teamPOList.size();
		HashMap<String, TeamPO> m = new HashMap<String, TeamPO>(
				(int) (n / 0.74) + 1, 0.75f);
		for (int i = 0; i < n; i++) {
			m.put(teamPOList.get(i).getAbbreviation(), teamPOList.get(i));
		}
		return m;
	}

	public ArrayList<MatchPO> getAllMatch() {
		return MatchesData.getMatchPOList();
	}

	public ImageIcon getPlayerAction(String name) {
		return Picture.getPlayerAction(name);
	}

	public ImageIcon getPlayerPortrait(String name) {
		return Picture.getPlayerPortrait(name);
	}

	public ImageIcon getTeamPic(String abbreviation) {
		return Picture.getTeamPic(abbreviation);
	}

	public JSVGCanvas getSwing(String abbreviation) {
		return Picture.getSwing(abbreviation);
	}
	public void startWatchMatches(){
		Thread watchThread =new Thread(new WatchMatches());
		watchThread.start();
	}
	public ArrayList<MatchPO> getNewMatch(){
		return MatchesData.getNewMatch();
	}
	public boolean isDEL(){
		return MatchesData.isDEL();
	}
}
