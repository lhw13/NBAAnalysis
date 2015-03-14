package server.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import dataservice.DataService;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.PlayerPO;
import server.po.ScorePO;
import server.po.TeamPO;
import vo.PlayerVO;
import vo.TeamVO;
import blservice.BLService;

public class Compute implements BLService{
	private Compute()
	{
	}
	HashMap<String, Player> playersHash = new HashMap<String, Player>(606);
	HashMap<String, Team> teamsHash = new HashMap<String, Team>(41);
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Team> teams = new ArrayList<Team>();
	DataService data = new MockData();
	private static Compute instance = null;
	public static Compute getInstance()
	{
		if(instance == null)
			instance = new Compute();
		return instance;
	}
	public ArrayList<PlayerVO> getPlayerAnalysis()
	{
		analyse();
		return toPVOs(players);
	}
	public ArrayList<TeamVO> getTeamAnalysis()
	{
		analyse();
		return new ArrayList();
	}
	public PlayerVO getPlayerAnalysis(String name)
	{//暂时认为名字唯一确定一名球员
		return new PlayerVO();
	}
	public TeamVO getTeamAnalysis(String name)
	{//暂时认为球队全称唯一确定一支球队
		return new TeamVO();
	}
	private boolean analyse()
	{
		if(players.size()>0)
			return true;
		else
		{
			linkDatas();
			Iterator<Entry<String, Team>> iter = teamsHash.entrySet().iterator();
			while(iter.hasNext())
			{
				Team team = iter.next().getValue();
				team.anaylse();
				teams.add(team);
			}
			//遍历hashMap,并放入数组
		}
		return false;
	}
	
	private boolean linkDatas()
	{//some prepared procedure before analyse, construct some hashmap, to accelarate the speed of it
		ArrayList<MatchPO> matches = data.getAllMatch();
		HashMap<String,TeamPO> teamPOHash = data.getAllTeams();
		HashMap<String,PlayerPO> playerPOHash = data.getAllPlayers();
		for(int i=matches.size()-1;i>=0;i--)
		{
			MatchPO mttemp = matches.get(i);
			ScorePO finalTemp = mttemp.getFinalScore();
			ArrayList<ScorePO> scoresTemp = mttemp.getScores();
			ArrayList<Integer> scores1 = new ArrayList<Integer>();
			ArrayList<Integer> scores2 = new ArrayList<Integer>();
			for(int j=0;j<scoresTemp.size();j++)
			{
				ScorePO scoreTemp = scoresTemp.get(j);
				scores1.add(scoreTemp.getTeam1());
				scores2.add(scoreTemp.getTeam2());
			}
			TeamInMatches timtemp1 = new TeamInMatches(mttemp.getTeam1(),finalTemp.getTeam1(),scores1,finalTemp.getTeam1()-finalTemp.getTeam2());
			TeamInMatches timtemp2 = new TeamInMatches(mttemp.getTeam2(),finalTemp.getTeam2(),scores2,finalTemp.getTeam2()-finalTemp.getTeam1());
			
			//put some data in team 1
			String ab = timtemp1.getAbbreviation();
			Team foundTeam = teamsHash.get(ab);
			if(foundTeam==null)
			{
				Team tTeam = new Team(teamPOHash.get(ab));
				tTeam.addThisTeam(timtemp1);
				tTeam.addOpponentTeam(timtemp2);
				teamsHash.put(ab, tTeam);
			}
			else
			{
				foundTeam.addThisTeam(timtemp1);
				foundTeam.addOpponentTeam(timtemp2);
			}
			
			//put some data in team 2
			ab = timtemp2.getAbbreviation();
			foundTeam = teamsHash.get(ab);
			if(foundTeam==null)
			{
				Team tTeam = new Team(teamPOHash.get(ab));
				tTeam.addThisTeam(timtemp2);
				tTeam.addOpponentTeam(timtemp1);
				teamsHash.put(ab, tTeam);
			}
			else
			{
				foundTeam.addThisTeam(timtemp2);
				foundTeam.addOpponentTeam(timtemp1);
			}
			
			//put some data in team 1's players
			ArrayList<PlayerInMatchesPO> playersTemp = timtemp1.getPlayers();
			for(int j=0;j<playersTemp.size();j++)
			{
				PlayerInMatchesPO playerTemp = playersTemp.get(j);
				String name = playerTemp.getName();
				Player foundPlayer = playersHash.get(name);
				if(foundPlayer==null)
				{
					ab = timtemp1.getAbbreviation();
					String teamFullName = teamsHash.get(ab).getFullName();
					Player tPlayer = new Player(teamFullName,ab,playerPOHash.get(name));
					tPlayer.addThisTeam(timtemp1,j);
					tPlayer.addOpponentTeam(timtemp2);
					playersHash.put(name, tPlayer);
				}
				else
				{
					foundPlayer.addThisTeam(timtemp1,j);
					foundPlayer.addOpponentTeam(timtemp2);
				}
			}
			
			//put some data in team 2's players
			playersTemp = timtemp2.getPlayers();
			for(int j=0;j<playersTemp.size();j++)
			{
				PlayerInMatchesPO playerTemp = playersTemp.get(j);
				String name = playerTemp.getName();
				Player foundPlayer = playersHash.get(name);
				if(foundPlayer==null)
				{
					ab = timtemp2.getAbbreviation();
					String teamFullName = teamsHash.get(ab).getFullName();
					Player tPlayer = new Player(teamFullName,ab,playerPOHash.get(name));
					tPlayer.addThisTeam(timtemp2,j);
					tPlayer.addOpponentTeam(timtemp1);
					playersHash.put(name, tPlayer);
				}
				else
				{
					foundPlayer.addThisTeam(timtemp2,j);
					foundPlayer.addOpponentTeam(timtemp1);
				}
			}
		}
		return true;
	}
	
	private boolean compute()
	{
		//compute team analysis
		
		return true;
	}
	private ArrayList<PlayerVO> toPVOs(ArrayList<Player> h)
	{
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for(int i=0;i<h.size();i++)
			result.add(h.get(i).toVO());
		return result;
	}
	private ArrayList<TeamVO> toTVOs(ArrayList<Team> h)
	{
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		for(int i=0;i<h.size();i++)
			result.add(h.get(i).toVO());
		return result;
	}
}
