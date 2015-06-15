package blservice;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import server.po.MatchPO;
import vo.*;

public interface BLService {
	ArrayList<PlayerVO> getPlayerAnalysis();

	ArrayList<TeamVO> getTeamAnalysis();

	PlayerVO getPlayerAnalysis(String name);// 暂时认为名字唯一确定一名球员

	TeamWithPlayersVO getTeamAnalysis(String abbreviation);// 暂时认为球队全称唯一确定一支球队

	ArrayList<TeamWithPlayersVO> getTeamsWithPlayers();// 展示层要求按队伍显示球员的接口
	
	public ArrayList<MatchPO> getAllMatch();//先暂时这么用吧，还能避免些缺陷，等我些差不多了我就把类型一改，其他代码都不用动

	public ArrayList<TeamVO> getHotTeamVO(String sortCon);//赛季热点球队，参数为排序依据，默认前5名
	
	public ArrayList<TeamVO> getHotTeamVO(String sortCon, int n);//获得前n名
	
	public ArrayList<PlayerVO> getHotPlayerVO(String sortCon, int n);//获得前n名
	
	public ArrayList<PlayerVO> getDailyHotPlayerVO(String sortCon, int n);
	
	public ArrayList<PlayerVO> getBestPromotion(String sortCon, int n);//进步最快
	
	public ImageIcon getPlayerAction(String name);

	public ImageIcon getPlayerPortrait(String name);

	public ImageIcon getTeamPic(String abbreviation);

	public JSVGCanvas getSwing(String abbreviation);
	
	public void startWatchMatches();//to watch the file changes, start the watch thread
	
	
	
	//iteration III
	public PlayerVO getPlayerSeasonAvg();
	public void setSeason(String season);
	//for prediction
	public double[][] getDataForRegression(int scale);//参数在1000～5000之间的偶数，为double数组的行数
	public double[][] getVariables(String teamabr1, String teamabr2);//返回值第一行为球队1，第二行为球队2
	//functions below are based on Stanford's paper
	public double[][] getDataForStrengthRegression(int scale);
	public double[][] getDataForStrengthVariables(String teamabr1, String teamabr2);
	
	public int[] adjustPredictResult(double score1, double score2, double diff);//调整预测结果，前两个参数为第一个预测的结果，最后一个参数为第二个预测的结果
	
	public PlayOffListVO getPlayOff();
}
