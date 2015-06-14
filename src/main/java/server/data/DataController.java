package server.data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import server.po.MatchPO;
import server.po.PlayerPO;
import server.po.TeamPO;
import dataservice.DataService;

public  final class DataController implements DataService {
	public HashMap<String, PlayerPO> getAllPlayers() {
//		ArrayList<PlayerPO> playerPOList = PlayersData.getPlayerPOList();
		ArrayList<PlayerPO> playerPOList = null;
		try {
			playerPOList = DatabaseController.getPlayerList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		int n = playerPOList.size();
		HashMap<String, PlayerPO> m = new HashMap<String, PlayerPO>(
				(int) (n / 0.74) + 1, 0.75f);
		for (int i = 0; i < n; i++) {
			m.put(playerPOList.get(i).getName(), playerPOList.get(i));
		}
		return m;
	}
	

	public HashMap<String, TeamPO> getAllTeams() {
//		ArrayList<TeamPO> teamPOList = TeamsData.getTeamPOList();
		ArrayList<TeamPO> teamPOList =null;
		try {
			teamPOList = DatabaseController.getTeamList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		int n = teamPOList.size();
		HashMap<String, TeamPO> m = new HashMap<String, TeamPO>(
				(int) (n / 0.74) + 1, 0.75f);
		for (int i = 0; i < n; i++) {
			m.put(teamPOList.get(i).getAbbreviation(), teamPOList.get(i));
		}
		return m;
	}

	public ArrayList<MatchPO> getAllMatch() {
	//	return MatchesData.getMatchPOList();
		ArrayList<MatchPO> matchPOList=null;
		try {
			matchPOList = DatabaseController.getMatchPOListBySeason();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return matchPOList;
	}
	public ArrayList<MatchPO> getAllMatchByseason( ) {
		ArrayList<MatchPO> matchPOList=null;
		try {
			matchPOList = DatabaseController.getMatchPOListBySeason();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return matchPOList;
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
//		Thread watchThread =new Thread(new WatchMatches());
//		watchThread.start();
	}
	public ArrayList<MatchPO> getNewMatch(){
//		return MatchesData.getNewMatch();
		return new ArrayList<MatchPO>();
	}
	public boolean isDEL(){
		return MatchesData.isDEL();
	}
	public void setSeason(String season){
		DatabaseController.season=season;
		MatchesData.isDEL=true;
	}
}
