package blservice;

import java.util.ArrayList;

import vo.*;

public interface BLService {
	ArrayList<PlayerVO> getPlayerAnalysis();
	ArrayList<TeamVO> getTeamAnalysis();
	PlayerVO getPlayerAnalysis(String name);//暂时认为名字唯一确定一名球员
	TeamWithPlayersVO getTeamAnalysis(String abbreviation);//暂时认为球队全称唯一确定一支球队
	ArrayList<TeamWithPlayersVO> getTeamsWithPlayers();//展示层要求按队伍显示球员的接口
}
