package server.businesslogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import dataservice.DataService;
import server.data.DataController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.PlayerPO;
import server.po.ScorePO;
import server.po.TeamPO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TeamWithPlayersVO;
import blservice.BLService;

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
		Collections.sort(todayPlayers, comp);
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
	
	private boolean analyse() {// the operation of core algorithm
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
			boolean theDay = mttemp.getDate().equals(day);
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

			// to handle dirty data
			timtemp1.clean();
			timtemp2.clean();

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
}
