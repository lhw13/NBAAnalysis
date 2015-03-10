package server.businesslogic;

import java.util.ArrayList;

import vo.PlayerVO;
import vo.TeamVO;
import blservice.BLService;

public class Compute implements BLService{
	private Compute(){}
	private static Compute instance = null;
	public static Compute getInstance()
	{
		if(instance == null)
			instance = new Compute();
		return instance;
	}
	public ArrayList<PlayerVO> getPlayerAnalysis()
	{
		return new ArrayList();
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
}
