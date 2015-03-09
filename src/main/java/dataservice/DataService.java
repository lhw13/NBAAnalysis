package dataservice;

import java.util.ArrayList;
import java.util.HashMap;

import server.po.*;


public interface DataService {
	HashMap<String, PlayerPO> getAllPlayers();//key 是球员名称
	HashMap<String, TeamPO> getAllTeams();//key是球队名称
	/*HashMap使用方法:
	 * 初始化： HashMap<String, PlayerPO/TeamPO> m = new HashMap<String, PlayerPO/TeamPO>(initialCapacity, float loadfactor);第一个参数填(球员or球队数量÷0.74)+1，后面填0.75
	 * 加入数据：m.put(String,PlayerPO/TeamPO);前面填球员或球队名称，后面填相应PO;
	 */
	ArrayList<MatchPO> getAllMatch();
	//Pic getPlayerPic(String name);根据姓名得到一位球员图片
	//Pic getTeamPic(String name);根据姓名得到一支球队队徽
}
