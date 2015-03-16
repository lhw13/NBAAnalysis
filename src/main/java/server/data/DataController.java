package server.data;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import server.po.MatchPO;
import server.po.PlayerPO;
import server.po.TeamPO;
import dataservice.DataService;

public class DataController implements DataService{
	MatchesData matchesData=new MatchesData();
	PlayersData playersData=new PlayersData();
	TeamsData teamsData=new TeamsData();
	Picture picture=new Picture();
	public HashMap<String, PlayerPO> getAllPlayers() {
		ArrayList<PlayerPO> playerPOList=playersData.getPlayerPOList();
		int n=playerPOList.size();
		HashMap<String, PlayerPO> m = new HashMap<String, PlayerPO>((int)(n/0.74)+1,0.75f);
		for(int i=0;i<n;i++){
			m.put(playerPOList.get(i).getName(), playerPOList.get(i));
		}	
		return m;
	}
	public HashMap<String, TeamPO> getAllTeams() {
		ArrayList<TeamPO> teamPOList=teamsData.getTeamPOList();
		int n=teamPOList.size();
		HashMap<String, TeamPO> m = new HashMap<String, TeamPO>((int)(n/0.74)+1,0.75f);
		for(int i=0;i<n;i++){
			m.put(teamPOList.get(i).getAbbreviation(), teamPOList.get(i));
		}
		return m;
	}
	public ArrayList<MatchPO> getAllMatch() {
		return matchesData.getMatchPOList();
	}
	public  Image getPlayerAction(String name){
		return picture.getPlayerAction(name);
	}
	public Image getPlayerPortrait(String name){
		return picture.getPlayerPortrait(name);
	}
	
}