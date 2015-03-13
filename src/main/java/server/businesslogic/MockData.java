package server.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;

import server.po.MatchPO;
import server.po.PlayerPO;
import server.po.TeamPO;
import dataservice.DataService;

public class MockData implements DataService{
	public HashMap<String, PlayerPO> getAllPlayers()
	{
		return new HashMap<String, PlayerPO>();
	}
	public HashMap<String, TeamPO> getAllTeams()
	{
		return new HashMap<String, TeamPO>();
	}
	public ArrayList<MatchPO> getAllMatch()
	{
		return new ArrayList<MatchPO>();
	}
}
