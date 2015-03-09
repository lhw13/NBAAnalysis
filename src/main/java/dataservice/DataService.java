package dataservice;

import java.util.ArrayList;

import vo.*;

public interface DataService {
	ArrayList<PlayerVO> getPlayerAnalysis();
	ArrayList<TeamVO> getTeamAnalysis();
	PlayerVO getPlayerAnalysis(String name);//暂时认为名字唯一确定一名球员
	TeamVO getTeamAnalysis(String name);//暂时认为球队全称唯一确定一支球队
}
