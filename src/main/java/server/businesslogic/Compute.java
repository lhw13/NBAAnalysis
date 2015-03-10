package server.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;

import vo.PlayerVO;
import vo.TeamVO;
import blservice.BLService;

public class Compute implements BLService{
	private Compute(){}
	HashMap<String, ArrayList<Player>> playerInTeam;
	HashMap<String, ArrayList<Integer>> teamSchedule;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Team> teams = new ArrayList<Team>();
	private static Compute instance = null;
	public static Compute getInstance()
	{
		if(instance == null)
			instance = new Compute();
		return instance;
	}
	public ArrayList<PlayerVO> getPlayerAnalysis()
	{
		if(players.size()>0)
			return toPVOs(players);
		else
		{
			return null;
		}
	}
	public ArrayList<TeamVO> getTeamAnalysis()
	{
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
