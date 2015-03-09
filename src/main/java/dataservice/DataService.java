package dataservice;

import java.util.ArrayList;
import java.util.HashMap;

import server.po.*;


public interface DataService {
	HashMap<String, PlayerPO> getAllPlayers();//key 是球员名称
	HashMap<String, TeamPO> getAllTeams();//key是球队名称
	ArrayList<MatchPO> getAllMatch();
	//Pic getPlayerPic(String name);根据姓名得到一位球员图片
	//Pic getTeamPic(String name);根据姓名得到一支球队队徽
}
