package server.businesslogic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.batik.swing.JSVGCanvas;

import console.Console;
import dataservice.DataService;
import server.data.DataController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.PlayerPO;
import server.po.ScorePO;
import server.po.TeamPO;
import vo.HeightVO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;
import statistic.*;

public final class BLController implements BLService {
	private BLController() {
		data.startWatchMatches();
	}
	static String season=null;
	static Calendar day = null;
	static boolean isDEL = true;
	static boolean isBegin = true;
	HashMap<String, Player> playersHash = new HashMap<String, Player>(606);
	HashMap<String, Team> teamsHash = new HashMap<String, Team>(41);
	ArrayList<Player> players = new ArrayList<Player>(460);// buffer for
															// analysed player
															// datas
	ArrayList<Player> todayPlayers = new ArrayList<Player>(40);
	ArrayList<Team> teams = new ArrayList<Team>(31);// see above
	DataService data = new DataController();
	ArrayList<MatchPO> matches = data.getAllMatch();
	private static BLController instance = null;

	public static BLController getInstance() {// 单体模式
		if (instance == null)
			instance = new BLController();
		return instance;
	}
	
	//iteration 2
	public void startWatchMatches() {
		data.startWatchMatches();
	}
	
	public void setSeason(String season){
		return;
	}
	
	
	//iteration 3
	public double[][] getDataForRegression(int scale)
	{//参数在1000～5000之间的偶数，为double数组的行数
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		double result[][] = new double [scale][7];
		try
		{
			/*label = new Label(0, 0, "球队1");
			sheet.addCell(label);
			label = new Label(1, 0, "球队2");
			sheet.addCell(label);
			label = new Label(2, 0, "本场比赛得分");
			sheet.addCell(label);
			label = new Label(3, 0, "最近20场本队平均进球");
			sheet.addCell(label);
			label = new Label(4, 0, "最近10场对手平均失球");
			sheet.addCell(label);
			label = new Label(5, 0, "主场");
			sheet.addCell(label);
			label = new Label(6, 0, "最近5场两队比赛进球");
			sheet.addCell(label);
			label = new Label(7, 0, "进步指数");
			sheet.addCell(label);
			label = new Label(8, 0, "最近5场对手平均进攻回合");
			sheet.addCell(label);*/
			int row=0;
			for (int i = h.size()-scale/2; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				result[row][0] = po.getFinalScore().getTeam1();
				result[row][1] = GenerateXML.computeScore(h,po.getTeam1().getAbbreviation(),20,i);
				result[row][2] = GenerateXML.computeScore2(h,po.getTeam2().getAbbreviation(),10,i);
				result[row][3] = 1;
				result[row][4] = GenerateXML.computeScore(h,po.getTeam1().getAbbreviation(),po.getTeam2().getAbbreviation(),4,i);
				result[row][5] = GenerateXML.computeScore(h,po.getTeam1().getAbbreviation(),5,i);
				result[row][6] = GenerateXML.computeRound(h,po.getTeam2().getAbbreviation(),5,i);
			}
			
			for (int i = h.size()-scale/2; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				result[row][0] = po.getFinalScore().getTeam2();
				result[row][1] = GenerateXML.computeScore(h,po.getTeam2().getAbbreviation(),20,i);
				result[row][2] = GenerateXML.computeScore2(h,po.getTeam1().getAbbreviation(),10,i);
				result[row][3] = 1;
				result[row][4] = GenerateXML.computeScore(h,po.getTeam2().getAbbreviation(),po.getTeam1().getAbbreviation(),4,i);
				result[row][5] = GenerateXML.computeScore(h,po.getTeam2().getAbbreviation(),5,i);
				result[row][6] = GenerateXML.computeRound(h,po.getTeam1().getAbbreviation(),5,i);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public double[][] getVariables(String teamabr1, String teamabr2)
	{//返回值第一行为球队1，第二行为球队2
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		double result[][] = new double [2][7];
			int i = h.size();
			result[0][1] = GenerateXML.computeScore(h,teamabr1,20,i);
			result[0][2] = GenerateXML.computeScore2(h,teamabr2,10,i);
			result[0][3] = 1;
			result[0][4] = GenerateXML.computeScore(h,teamabr1,teamabr2,4,i);
			result[0][5] = GenerateXML.computeScore(h,teamabr1,5,i);
			result[0][6] = GenerateXML.computeRound(h,teamabr2,5,i);
			
				
			result[1][1] = GenerateXML.computeScore(h,teamabr2,20,i);
			result[1][2] = GenerateXML.computeScore2(h,teamabr1,10,i);
			result[1][3] = 1;
			result[1][4] = GenerateXML.computeScore(h,teamabr2,teamabr1,4,i);
			result[1][5] = GenerateXML.computeScore(h,teamabr2,5,i);
			result[1][6] = GenerateXML.computeRound(h,teamabr1,5,i);
		return result;
	}
	
	public double getStrengthDiff(String teamabr1, String teamabr2,int n, int now,double theta,double k)
	{
		Team team1 = teamsHash.get(teamabr1);
		Team team2 = teamsHash.get(teamabr2);
		return team1.getStrength(n, now,theta,k)-team2.getStrength(n, now,theta,k);
	}
	
	public ArrayList<TeamVO> getHotTeamVO(String sortCon) {
		return getHotTeamVO(sortCon,5);
	}
	public ArrayList<TeamVO> getHotTeamVO(String sortCon,int n) {
		getHotTeam(sortCon);
		ArrayList<TeamVO> result = new ArrayList<TeamVO>(n+1);
		for(int i=0;i<n && i<teams.size();i++)
			result.add(teams.get(i).toVO());
		return result;
	}
	
	public ArrayList<Team> getHotTeam(String sortCon) {
		analyse();
		sortTeams(sortCon);
		return teams;
	}
	
	public ArrayList<PlayerVO> getHotPlayerVO(String sortCon, int n) {
		analyse();
		Comparator<Player> comp = Comparators.getPlayerAvgComparator(sortCon);
		if(comp==null)
			comp = Comparators.getPlayerComparator(sortCon);
		Collections.sort(players, comp);
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>(n+1);
		for(int i=0;i<n && i<players.size();i++)
			if(players.get(i).active)
				result.add(players.get(i).toVO());
			else
				n++;
		return result;
	}
	
	public ArrayList<PlayerVO> getDailyHotPlayerVO(String sortCon, int n) {
		getDailyHotPlayer(sortCon);
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>(n+1);
		for(int i=0;i<n && i<todayPlayers.size();i++)
			result.add(todayPlayers.get(i).toVO());
		return result;
	}
	
	public ArrayList<Player> getDailyHotPlayer(String sortCon) {
		analyse();
		Comparator<Player> comp = Comparators.getPlayerAvgComparator(sortCon);
		if(comp==null)
			comp = Comparators.getPlayerComparator(sortCon);
		List<Comparator<Player>> sortConsList = new ArrayList<Comparator<Player>>();
		sortConsList.add(comp);
		sortConsList.add(Console.comparePlayerNameAsc);
		sort(todayPlayers, sortConsList);
		return todayPlayers;
	}
	
	public ArrayList<PlayerVO> getBestPromotion(String sortCon, int n) {
		analyse();
		return toPVOs(getBestPromotionForConsole(sortCon,n));
	}
        
        public ArrayList<Player> getBestPromotionForConsole(String sortCon, int n) {
		analyse();
		switch(sortCon) {
		case "point": Collections.sort(players, Comparators.compareScorePromotionDesc);break;
		case "score": Collections.sort(players, Comparators.compareScorePromotionDesc);break;
		case "assist": Collections.sort(players, Comparators.compareAssistPromotionDesc);break;
		case "rebound": Collections.sort(players, Comparators.compareReboundPromotionDesc);break;
		}
		ArrayList<Player> result = new ArrayList<Player>(n+1);
		for(int i=0;i<n && i<players.size();i++)
			if(players.get(i).active)
				result.add(players.get(i));
		return result;
	}
	
	//public ArrayList<Player> getDailyPlayer()
	//{
		
	//}

	// transform image of player or team
	public ImageIcon getPlayerAction(String name) {
		return data.getPlayerAction(name);
	}

	public ImageIcon getPlayerPortrait(String name) {
		return data.getPlayerPortrait(name);
	}

	public ImageIcon getTeamPic(String abbreviation) {
		return data.getTeamPic(abbreviation);
	}

	public JSVGCanvas getSwing(String abbreviation) {
		return data.getSwing(abbreviation);
	}
	
	public ArrayList<MatchPO> getAllMatch() {
		isBegin=true;//get all matches(), to improve reliabilty of the program
		//because we don't care about the efficiency of this method;
		analyse();
		for(int i=0;i<matches.size();i++)
			matches.get(i).sortPlayersByScore();
		return matches;
	}

	// 4 key methods to implement the interface
	public ArrayList<PlayerVO> getPlayerAnalysis() {
		analyse();
		return toPVOs(players);
	}

	public ArrayList<TeamVO> getTeamAnalysis() {
		analyse();
		return toTVOs(teams);
	}

	public PlayerVO getPlayerAnalysis(String name) {// 暂时认为名字唯一确定一名球员
		analyse();
		Player player = playersHash.get(name);
		if (player == null)
			return null;
		return player.toVO();
	}

	public TeamWithPlayersVO getTeamAnalysis(String name) {// 暂时认为球队全称唯一确定一支球队
		analyse();
		Team team = teamsHash.get(name);
		if (team == null)
			return null;
		return new TeamWithPlayersVO(team.toVO(),
				getPlayersInTeam(team.teamPO.getAbbreviation()));
	}

	public ArrayList<TeamWithPlayersVO> getTeamsWithPlayers() {
		analyse();
		ArrayList<TeamWithPlayersVO> result = new ArrayList<TeamWithPlayersVO>(35);
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			result.add(new TeamWithPlayersVO(team.toVO(),
					getPlayersInTeam(team.teamPO.getAbbreviation())));
		}
		return result;
	}
	
	//iteration III
	public PlayerVO getPlayerSeasonAvg() {
		analyse();
		ArrayList<PlayerVO> h =  getPlayerAnalysis();
		PlayerVO result = new PlayerVO
				(" ", " ",
						' ', " ", " ", -1,
						" ", null, -1, null,
						0, 0, " ", 0, 0,
						0, 0, 0, 0, 0,
						0, 0, 0,
						0,  0,  0,  0,
						 0,  0,  0,  0,  0,
						 0,  0,  0,
						 0,  0,  0,
						 0,  0,
						 0,  0,  0,
						 0,  0,  0,  0, null, 
						 0,  0,  0,  0);
		for(int i=0; i<h.size(); i++)
			result.add(h.get(i));
		result.divide(h.size());
		return result;
	}
	
	public boolean analyse() {// the operation of core algorithm
		//if (players.size() > 0)// already have content in buffer
			//return true;
		//else {// when just start the program, we will first compute the data
		isDEL=data.isDEL() || isBegin;
		isBegin=false;
		linkDatas();
		if(matches.size()==0)
			return true;
		Iterator<Entry<String, Team>> iter = teamsHash.entrySet()
				.iterator();
		while (iter.hasNext())// transfer data from hash to list to give to
								// ui, meanwhile analyse each data
		{
			Team team = iter.next().getValue();
			if(team.newData)
				team.anaylse();
			if(!team.added)
			{//we should check if it has been added to result list in iteration 2 because of dynamic data
				team.added=true;
				teams.add(team);
			}
		}
		Iterator<Entry<String, Player>> iter2 = playersHash.entrySet()
				.iterator();
		while (iter2.hasNext())// see above
		{
			Player player = iter2.next().getValue();
			if(player.newData)
				player.anaylse();
			if(!player.added)
			{
				player.added=true;
				players.add(player);
			}
		}
		//Collections.sort(players, new SortPlayersByTeam());// this sort
																// improve the
																// efficiency of
																// getTeamWithPlayer
		return true;
	}

	private void clear()
	{
		season=null;
		day = null;
		players.clear();
		todayPlayers.clear();
		teams.clear();
		teamsHash.clear();
		playersHash.clear();
	}
	
	private boolean linkDatas() {// some prepared procedure before analyse,
									// construct some hashmap, to accelarate the
									// speed of it
		if(isDEL)
		{
			clear();
			matches = data.getAllMatch();
		}
		else
			matches = data.getNewMatch();
		if(matches.size()==0)
		{
			return true;
		}
		Collections.sort(matches, new SortMatchesByCalendar());
		int matchesSize = matches.size();
		String thisSeason = matches.get(matchesSize-1).getSeason();
		Calendar thisDay = matches.get(matchesSize-1).getDate();
		if(season==null || season.compareTo(thisSeason)<0)
			season=thisSeason;
		if(day==null || !(day.get(Calendar.YEAR)==thisDay.get(Calendar.YEAR) && day.get(Calendar.MONTH)==thisDay.get(Calendar.MONTH) && day.get(Calendar.DAY_OF_MONTH)==thisDay.get(Calendar.DAY_OF_MONTH)))
		{
			day=thisDay;
			todayPlayers.clear();
		}
		HashMap<String, TeamPO> teamPOHash = data.getAllTeams();
		HashMap<String, PlayerPO> playerPOHash = data.getAllPlayers();
		for (int i = 0; i < matches.size(); i++) {
			MatchPO mttemp = matches.get(i);
			boolean theSeason = mttemp.getSeason().compareTo(season)==0;
			Calendar tempc = mttemp.getDate();
			boolean theDay = (day.get(Calendar.YEAR)==tempc.get(Calendar.YEAR) && day.get(Calendar.MONTH)==tempc.get(Calendar.MONTH) && day.get(Calendar.DAY_OF_MONTH)==tempc.get(Calendar.DAY_OF_MONTH));
			ScorePO finalTemp = mttemp.getFinalScore();
			ArrayList<ScorePO> scoresTemp = mttemp.getScores();
                        int size = scoresTemp.size();
			ArrayList<Integer> scores1 = new ArrayList<Integer>(size+1);
			ArrayList<Integer> scores2 = new ArrayList<Integer>(size+1);
			for (int j = 0; j < size; j++) {
				ScorePO scoreTemp = scoresTemp.get(j);
				scores1.add(scoreTemp.getTeam1());
				scores2.add(scoreTemp.getTeam2());
			}
			// encapsulate po in object in bl
			TeamInMatches timtemp1 = new TeamInMatches(mttemp.getTeam1(),
					finalTemp.getTeam1(), scores1, finalTemp.getTeam1()
							- finalTemp.getTeam2());
			TeamInMatches timtemp2 = new TeamInMatches(mttemp.getTeam2(),
					finalTemp.getTeam2(), scores2, finalTemp.getTeam2()
							- finalTemp.getTeam1());

			timtemp1.setTeamInMatches2(timtemp2);
			timtemp2.setTeamInMatches2(timtemp1);
			// to handle dirty data
			timtemp1.clean();
			timtemp2.clean();
			timtemp1.computeTotal();
			timtemp2.computeTotal();
			timtemp1.computeRound();
			timtemp2.computeRound();

			// put some data in team 1
			String ab = timtemp1.getAbbreviation();
			/*if(ab.equals("NOH") && season.compareTo("13-14")>=0)
			{
				Team foundTeam = teamsHash.get(ab);
				ab = "NOP";
				if(foundTeam!=null)
				{
					teamsHash.put(ab, foundTeam);
					teamsHash.remove("NOH");
				}
			}
			else if(ab.equals("CHA") && season.compareTo("14-15")>=0)
			{
				Team foundTeam = teamsHash.get(ab);
				ab = "NO";
				if(foundTeam!=null)
				{
					teamsHash.put(ab, foundTeam);
					teamsHash.remove("CHA");
				}
			}*/
			Team foundTeam = teamsHash.get(ab);
			if (foundTeam == null) {
				Team tTeam = new Team(teamPOHash.get(ab));
				if(theSeason)
				{//if the match belongs to this season
					tTeam.addThisTeam(timtemp1);
					tTeam.addOpponentTeam(timtemp2);
				}
				tTeam.addMatch(mttemp);
				teamsHash.put(ab, tTeam);
			} else {
				if(theSeason)
				{
					foundTeam.addThisTeam(timtemp1);
					foundTeam.addOpponentTeam(timtemp2);
				}
				foundTeam.addMatch(mttemp);
			}

			// put some data in team 2
			ab = timtemp2.getAbbreviation();
			/*if(ab.equals("NOH") && season.compareTo("13-14")>=0)
			{
				foundTeam = teamsHash.get(ab);
				ab = "NOP";
				if(foundTeam!=null)
				{
					teamsHash.put(ab, foundTeam);
					teamsHash.remove("NOH");
				}
			}
			else if(ab.equals("CHA") && season.compareTo("14-15")>=0)
			{
				foundTeam = teamsHash.get(ab);
				ab = "NO";
				if(foundTeam!=null)
				{
					teamsHash.put(ab, foundTeam);
					teamsHash.remove("CHA");
				}
			}*/
			foundTeam = teamsHash.get(ab);
			if (foundTeam == null) {
				Team tTeam = new Team(teamPOHash.get(ab));
				if(theSeason)
				{
					tTeam.addThisTeam(timtemp2);
					tTeam.addOpponentTeam(timtemp1);
				}
				tTeam.addMatch(mttemp);
				teamsHash.put(ab, tTeam);
			} else {
				if(theSeason)
				{
					foundTeam.addThisTeam(timtemp2);
					foundTeam.addOpponentTeam(timtemp1);
				}
				foundTeam.addMatch(mttemp);
			}

			// put some data in team 1's players
			ArrayList<PlayerInMatchesPO> playersTemp = timtemp1.getPlayers();
			for (int j = 0; j < playersTemp.size(); j++) {
				PlayerInMatchesPO playerTemp = playersTemp.get(j);
				String name = playerTemp.getName();
				Player foundPlayer = playersHash.get(name);
				if (foundPlayer == null) {
					ab = timtemp1.getAbbreviation();
					PlayerPO playerPO = playerPOHash.get(name);
					if (playerPO == null) {// if we can't find player's info, we
											// creat an object without info
						playerPO = new PlayerPO(name);
					}
					/*if(teamsHash.get(ab)==null)
						System.out.print(ab);*/
					Player tPlayer = new Player(teamsHash.get(ab).getTeamPO(),
							playerPO);
					if(theSeason)
					{
						tPlayer.addThisTeam(timtemp1, j);
						tPlayer.addOpponentTeam(timtemp2);
						if(theDay)
						{
							//Player pc = tPlayer.clone();
							Player pc = new Player(tPlayer.team,
									playerPO);
							pc.addThisTeam(timtemp1, j);
							pc.addOpponentTeam(timtemp2);
							pc.anaylse();
							todayPlayers.add(pc);
						}
					}
					tPlayer.addMatch(mttemp);
					playersHash.put(name, tPlayer);
				} else {
					if(theSeason)
					{
						foundPlayer.addThisTeam(timtemp1, j);
						foundPlayer.addOpponentTeam(timtemp2);
						if(theDay)
						{
							Player pc = new Player(foundPlayer.team,
									foundPlayer.player);
							pc.addThisTeam(timtemp1, j);
							pc.addOpponentTeam(timtemp2);
							pc.anaylse();
							todayPlayers.add(pc);
						}
					}
					foundPlayer.addMatch(mttemp);
				}
			}

			// put some data in team 2's players
			playersTemp = timtemp2.getPlayers();
			for (int j = 0; j < playersTemp.size(); j++) {
				PlayerInMatchesPO playerTemp = playersTemp.get(j);
				String name = playerTemp.getName();
				Player foundPlayer = playersHash.get(name);
				if (foundPlayer == null) {
					ab = timtemp2.getAbbreviation();
					PlayerPO playerPO = playerPOHash.get(name);
					if (playerPO == null) {// see above
						playerPO = new PlayerPO(name);
					}
					/*if(teamsHash.get(ab)==null)
						System.out.println("123"+ab);*/
					Player tPlayer = new Player(teamsHash.get(ab).getTeamPO(),
							playerPO);
					if(theSeason)
					{
						tPlayer.addThisTeam(timtemp2, j);
						tPlayer.addOpponentTeam(timtemp1);
						if(theDay)
						{
							Player pc = new Player(tPlayer.team,
									playerPO);
							pc.addThisTeam(timtemp2, j);
							pc.addOpponentTeam(timtemp1);
							pc.anaylse();
							todayPlayers.add(pc);
						}
					}
					tPlayer.addMatch(mttemp);
					playersHash.put(name, tPlayer);
				} else {
					if(theSeason)
					{
						foundPlayer.addThisTeam(timtemp2, j);
						foundPlayer.addOpponentTeam(timtemp1);
						if(theDay)
						{
							Player pc = new Player(foundPlayer.team,
									foundPlayer.player);
							pc.addThisTeam(timtemp2, j);
							pc.addOpponentTeam(timtemp1);
							pc.anaylse();
							todayPlayers.add(pc);
						}
					}
					foundPlayer.addMatch(mttemp);
				}
			}
		}
		return true;
	}

	private ArrayList<PlayerVO> getPlayersInTeam(String abbreviation) {// 参数是简称
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>(20);
		int i = 5;
		/*for (i = 0; i < players.size(); i ++)
			// here just use some small trick to improve the efficiency
			if (players.get(i).team.getAbbreviation().equals(abbreviation)) {
				result.add(players.get(i).toVO());
				break;
			}
		if (i < players.size())
			for (int j = i - 1; j >= 0; j--)
				if (players.get(j).team.getAbbreviation().equals(abbreviation))
					result.add(players.get(j).toVO());
				else
					break;
		for (i = i + 1; i < players.size(); i++)
			if (players.get(i).team.getAbbreviation().equals(abbreviation))
				result.add(players.get(i).toVO());
			else
				break;*/
		for (i = 0; i < players.size(); i ++)
			if (players.get(i).team.getAbbreviation().equals(abbreviation)) {
				if(players.get(i).active)
					result.add(players.get(i).toVO());
			}
		return result;
	}

	private ArrayList<PlayerVO> toPVOs(ArrayList<Player> h) {// transfor to list
																// of vo
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for (int i = 0; i < h.size(); i++)
			result.add(h.get(i).toVO());
		return result;
	}

	private ArrayList<TeamVO> toTVOs(ArrayList<Team> h) {// see above
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		for (int i = 0; i < h.size(); i++)
			result.add(h.get(i).toVO());
		return result;
	}

	public ArrayList<Player> getPlayers() {
		analyse();
		return players;
	}

	public ArrayList<Team> getTeams() {
		analyse();
		return teams;
	}

	public boolean sortTeams(String sort) {
		Comparator<Team> ct = Comparators.getTeamComparatorAvg(sort);
		if(ct==null)
			return false;
		Collections.sort(teams, ct);
		return true;
	}
	
	//用于多重排序
		public final void sort(List<Player> list, final List<Comparator<Player>> comList) {  
	        if (comList == null)  
	            return;  
	        Comparator<Player> cmp = new Comparator<Player>() {  
	            @Override  
	            public final int compare(Player o1, Player o2) {  
	                for (Comparator<Player> comparator : comList) {
	                    int x = comparator.compare(o1, o2);
	                    if(x!=0)
	                        return x;
	                }  
	                return 0;  
	            }  
	        };  
	        Collections.sort(list, cmp);  
	    }

		public static String getSeason() {
			return season;
		}

		public HashMap<String, Player> getPlayersHash() {
			return playersHash;
		}

		public HashMap<String, Team> getTeamsHash() {
			return teamsHash;
		}

		public ArrayList<MatchPO> getMatches() {
			return matches;
		}  
}
