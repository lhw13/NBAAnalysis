package dataservice;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import server.po.*;

public interface DataService {
	HashMap<String, PlayerPO> getAllPlayers();// key 是球员名称

	HashMap<String, TeamPO> getAllTeams();// key是球队名称

	/*
	 * HashMap使用方法: 初始化： HashMap<String, PlayerPO/TeamPO> m = new
	 * HashMap<String, PlayerPO/TeamPO>(initialCapacity, float
	 * loadfactor);第一个参数填(球员or球队数量÷0.74)+1，后面填0.75
	 * 加入数据：m.put(String,PlayerPO/TeamPO);前面填球员或球队名称，后面填相应PO;
	 */
	ArrayList<MatchPO> getAllMatch();

	public ImageIcon getPlayerAction(String name);

	public ImageIcon getPlayerPortrait(String name);

	public ImageIcon getTeamPic(String abbreviation);

	public JSVGCanvas getSwing(String abbreviation);
	
	public void startWatchMatches();//开始线程
	
	public   ArrayList<MatchPO> getNewMatch();//获得新增比赛数据
	
	public boolean isDEL();//是否出现比赛数据删除
	public ArrayList<MatchPO> getAllMatchByseason();//按赛季获得数据
}
