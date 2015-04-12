package server.businesslogic;

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

public class BLController implements BLService {
	private BLController() {
	}
	static String season=null;
	static Calendar day = null;
	HashMap<String, Player> playersHash = new HashMap<String, Player>(606);
	HashMap<String, Team> teamsHash = new HashMap<String, Team>(41);
	ArrayList<Player> players = new ArrayList<Player>(460);// buffer for
															// analysed player
															// datas
	ArrayList<Player> todayPlayers = new ArrayList<Player>(40);
	ArrayList<Team> teams = new ArrayList<Team>(31);// see above
	DataService data = new DataController();
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
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
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
		Comparator<Player> comp = getPlayerAvgComparator(sortCon);
		if(comp==null)
			comp = getPlayerComparator(sortCon);
		Collections.sort(players, comp);
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for(int i=0;i<n && i<players.size();i++)
			result.add(players.get(i).toVO());
		return result;
	}
	
	public ArrayList<PlayerVO> getDailyHotPlayerVO(String sortCon, int n) {
		getDailyHotPlayer(sortCon);
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for(int i=0;i<n && i<todayPlayers.size();i++)
			result.add(todayPlayers.get(i).toVO());
		return result;
	}
	
	public ArrayList<Player> getDailyHotPlayer(String sortCon) {
		analyse();
		Comparator<Player> comp = getPlayerAvgComparator(sortCon);
		if(comp==null)
			comp = getPlayerComparator(sortCon);
		for(int i=0;i<todayPlayers.size();i++)
			System.out.println(todayPlayers.get(i).getBlockShot());
		Collections.sort(todayPlayers, comp);
		return todayPlayers;
	}
	
	public ArrayList<PlayerVO> getBestPromotion(String sortCon, int n) {
		analyse();
		switch(sortCon) {
		case "point": Collections.sort(players, compareScorePromotionDesc);break;
		case "score": Collections.sort(players, compareScorePromotionDesc);break;
		case "assist": Collections.sort(players, compareAssistPromotionDesc);break;
		case "rebound": Collections.sort(players, compareReboundPromotionDesc);break;
		}
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for(int i=0;i<n && i<players.size();i++)
			result.add(players.get(i).toVO());
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
		return data.getAllMatch();
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
		ArrayList<TeamWithPlayersVO> result = new ArrayList<TeamWithPlayersVO>();
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
			linkDatas();
			Iterator<Entry<String, Team>> iter = teamsHash.entrySet()
					.iterator();
			while (iter.hasNext())// transfer data from hash to list to give to
									// ui, meanwhile analyse each data
			{
				Team team = iter.next().getValue();
				team.anaylse();
				teams.add(team);
			}
			Iterator<Entry<String, Player>> iter2 = playersHash.entrySet()
					.iterator();
			while (iter2.hasNext())// see above
			{
				Player player = iter2.next().getValue();
				player.anaylse();
				players.add(player);
			}
			Collections.sort(players, new SortPlayersByTeam());// this sort
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
		clear();
		ArrayList<MatchPO> matches = data.getAllMatch();
		Collections.sort(matches, new SortMatchesByCalendar());
		int matchesSize = matches.size();
		String thisSeason = matches.get(matchesSize-1).getSeason();
		Calendar thisDay = matches.get(matchesSize-1).getDate();
		if(season==null || season.compareTo(thisSeason)<0)
			season=thisSeason;
		if(day==null || day.before(thisDay))
		{
			day=thisDay;
			todayPlayers.clear();
		}
		HashMap<String, TeamPO> teamPOHash = data.getAllTeams();
		HashMap<String, PlayerPO> playerPOHash = data.getAllPlayers();
		for (int i = matchesSize - 1; i >= 0; i--) {
			MatchPO mttemp = matches.get(i);
			boolean theSeason = mttemp.getSeason().compareTo(season)==0;
			boolean theDay = mttemp.getDate().equals(day);
			ScorePO finalTemp = mttemp.getFinalScore();
			ArrayList<ScorePO> scoresTemp = mttemp.getScores();
			ArrayList<Integer> scores1 = new ArrayList<Integer>();
			ArrayList<Integer> scores2 = new ArrayList<Integer>();
			for (int j = 0; j < scoresTemp.size(); j++) {
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
			if(ab.equals("NOH") && season.compareTo("13-14")>=0)
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
			}
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
			if(ab.equals("NOH") && season.compareTo("13-14")>=0)
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
			}
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
					Player tPlayer = new Player(teamsHash.get(ab).getTeamPO(),
							playerPO);
					if(theSeason)
					{
						tPlayer.addThisTeam(timtemp1, j);
						tPlayer.addOpponentTeam(timtemp2);
						if(theDay)
						{
							Player pc = tPlayer.clone();
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
							Player pc = foundPlayer.clone();
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
					Player tPlayer = new Player(teamsHash.get(ab).getTeamPO(),
							playerPO);
					if(theSeason)
					{
						tPlayer.addThisTeam(timtemp2, j);
						tPlayer.addOpponentTeam(timtemp1);
						if(theDay)
						{
							Player pc = tPlayer.clone();
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
							Player pc = foundPlayer.clone();
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
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		int i = 5;
		for (i = 0; i < players.size(); i ++)
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
				break;
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
	
	
	private static final Comparator<Team> TeamByPoint = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.score/t2.appearance;
			Double d1 = (double)t1.score/t1.appearance;
			return d2.compareTo(d1);
		}
	};

	private static final Comparator<Team> TeamByRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.rebound/t2.appearance;
			Double d1 = (double)t1.rebound/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByAssist = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.assist/t2.appearance;
			Double d1 = (double)t1.assist/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByBlock = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.blockShot/t2.appearance;
			Double d1 = (double)t1.blockShot/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamBySteal = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.steal/t2.appearance;
			Double d1 = (double)t1.steal/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByThree = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getThree();
			Double d1 = t1.getThree();
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByShot = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getShot();
			Double d1 = t1.getShot();
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByPenalty = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getPenalty();
			Double d1 = t1.getPenalty();
			return d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByFoul = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.foul;
			Integer i1 = t1.foul;
			return i2.compareTo(i1);
		}
	};
	private static final Comparator<Team> TeamByFault = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault;
			Integer i1 = t1.fault;
			return i2.compareTo(i1);
		}
	};
	private static final Comparator<Team> TeamByDefendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i1= t1.defendRebound;
			Integer i2 = t2.defendRebound;
			return i2.compareTo(i1);
		}
	};

	private static final Comparator<Team> TeamByWinRate = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getWinRate();
			Double d1 = t1.getWinRate();
            return d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.offendRebound;
			Integer d1 = t1.offendRebound;
            return d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendRound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound();
        	Double d1 = t1.getOffendRound();
            return d2.compareTo(d1);
			//return t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendEfficient();
        	Double d1 = t1.getOffendEfficient();
            return d2.compareTo(d1);
			//return t1.getOffendEfficient()<t2.getOffendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendEfficient();
        	Double d1 = t1.getDefendEfficient();
            return d2.compareTo(d1);
			//return t1.getDefendEfficient()<t2.getDefendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendReboundEfficient();
        	Double d1 = t1.getOffendReboundEfficient();
            return d2.compareTo(d1);
			//return t1.getOffendReboundEfficient()<t2.getOffendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendReboundEfficient();
        	Double d1 = t1.getDefendReboundEfficient();
            return d2.compareTo(d1);
			//return t1.getDefendReboundEfficient()<t2.getDefendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByStealEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getStealEfficient();
        	Double d1 = t1.getStealEfficient();
            return d2.compareTo(d1);
			//return t1.getStealEfficient()<t2.getStealEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByAssistEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getAssistEfficient();
        	Double d1 = t1.getAssistEfficient();
            return d2.compareTo(d1);
			//return t1.getAssistEfficient()<t2.getAssistEfficient()?1:-1;
		}
	};
	
	
	//升序
	private static final Comparator<Team> TeamByNameAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getAbbreviation().compareTo(t2.getAbbreviation());
		}
	};
	
	private static final Comparator<Team> TeamByPointAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.score/t2.appearance;
			Double d1 = (double)t1.score/t1.appearance;
			return -d2.compareTo(d1);
		}
	};

	private static final Comparator<Team> TeamByReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.rebound/t2.appearance;
			Double d1 = (double)t1.rebound/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByAssistAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.assist/t2.appearance;
			Double d1 = (double)t1.assist/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByBlockAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.blockShot/t2.appearance;
			Double d1 = (double)t1.blockShot/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByStealAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.steal/t2.appearance;
			Double d1 = (double)t1.steal/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByThreeAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getThree();
			Double d1 = t1.getThree();
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByShotAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getShot();
			Double d1 = t1.getShot();
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByPenaltyAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getPenalty();
			Double d1 = t1.getPenalty();
			return -d2.compareTo(d1);
		}
	};
	private static final Comparator<Team> TeamByFoulAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.foul;
			Integer i1 = t1.foul;
			return -i2.compareTo(i1);
		}
	};
	private static final Comparator<Team> TeamByFaultAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault;
			Integer i1 = t1.fault;
			return -i2.compareTo(i1);
		}
	};
	private static final Comparator<Team> TeamByDefendReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i1= t1.defendRebound;
			Integer i2 = t2.defendRebound;
			return -i2.compareTo(i1);
		}
	};

	private static final Comparator<Team> TeamByWinRateAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getWinRate();
			Double d1 = t1.getWinRate();
            return -d2.compareTo(d1);
			//return -t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.offendRebound;
			Integer d1 = t1.offendRebound;
            return -d2.compareTo(d1);
			//return -t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendRoundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound();
        	Double d1 = t1.getOffendRound();
            return -d2.compareTo(d1);
			//return -t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendEfficient();
        	Double d1 = t1.getOffendEfficient();
            return -d2.compareTo(d1);
			//return -t1.getOffendEfficient()<t2.getOffendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendEfficient();
        	Double d1 = t1.getDefendEfficient();
            return -d2.compareTo(d1);
			//return -t1.getDefendEfficient()<t2.getDefendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendReboundEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendReboundEfficient();
        	Double d1 = t1.getOffendReboundEfficient();
            return -d2.compareTo(d1);
			//return -t1.getOffendReboundEfficient()<t2.getOffendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendReboundEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendReboundEfficient();
        	Double d1 = t1.getDefendReboundEfficient();
            return -d2.compareTo(d1);
			//return -t1.getDefendReboundEfficient()<t2.getDefendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByStealEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getStealEfficient();
        	Double d1 = t1.getStealEfficient();
            return -d2.compareTo(d1);
			//return -t1.getStealEfficient()<t2.getStealEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByAssistEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getAssistEfficient();
        	Double d1 = t1.getAssistEfficient();
            return -d2.compareTo(d1);
			//return -t1.getAssistEfficient()<t2.getAssistEfficient()?1:-1;
		}
	};
	
	
	//球员姓名应该是按升序排
	public Comparator<Player> comparePlayerNameAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o1.getName().compareTo(o2.getName());
        }
    };
	
	public Comparator<Player> comparePointDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer i2=o2.getPoint();
        	Integer i1=o1.getPoint();
            return i2.compareTo(i1);
        	//return o2.getPoint() > o1.getPoint() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareReboundDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {
        	Integer i2=o2.getRebound();
        	Integer i1=o1.getRebound();
            return i2.compareTo(i1);
           // return o2.getRebound() > o1.getRebound() ? 1 : -1;
        }
    };
    public Comparator<Player> compareAssistDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer i2=o2.getAssist();
        	Integer i1=o1.getAssist();
            return i2.compareTo(i1);
        	//return o2.getAssist() > o1.getAssist() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareBlockShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer i2=o2.getBlockShot();
        	Integer i1=o1.getBlockShot();
            return i2.compareTo(i1);
        }
    };
    
    public Comparator<Player> compareStealDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer d2 = o2.getSteal();
        	Integer d1 = o1.getSteal();
            return d2.compareTo(d1);
        	//return o2.getSteal() > o1.getSteal() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFoulDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer d2 = o2.getFoul();
        	Integer d1 = o1.getFoul();
            return d2.compareTo(d1);
        	//return o2.getFoul() > o1.getFoul() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFaultDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Integer d2 = o2.getFault();
        	Integer d1 = o1.getFault();
            return d2.compareTo(d1);
        	//return o2.getFault() > o1.getFault() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareMinuteDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getMinute();
        	Double d1 = o1.getMinute();
            return d2.compareTo(d1);
        	//return o2.getMinute() > o1.getMinute() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getEfficient();
        	Double d1 = o1.getEfficient();
            return d2.compareTo(d1);
        	//return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getShot();
        	Double d1 = o1.getShot();
            return d2.compareTo(d1);
        	// return o2.getShot() > o1.getShot() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareThreeDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getThree();
        	Double d1 = o1.getThree();
            return d2.compareTo(d1);
        	//return o2.getThree() > o1.getThree() ? 1 : -1;
        }
    };
    
    public Comparator<Player> comparePenaltyDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getPenalty();
        	Double d1 = o1.getPenalty();
            return d2.compareTo(d1);
        	//return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareDoubleTwoDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getDoubleTwo();
        	Double d1 = o1.getDoubleTwo();
            return d2.compareTo(d1);
        	//return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareRealShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getRealShot();
        	Double d1 = o1.getRealShot();
            return d2.compareTo(d1);
        	//return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareGmScDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getGmSc();
        	Double d1 = o1.getGmSc();
            return d2.compareTo(d1);
        	//return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareShotEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getShotEfficient();
        	Double d1 = o1.getShotEfficient();
            return d2.compareTo(d1);
        	//return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getReboundEfficient();
        	Double d1 = o1.getReboundEfficient();
            return d2.compareTo(d1);
        	// return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareOffendReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getOffendReboundEfficient();
        	Double d1 = o1.getOffendReboundEfficient();
            return d2.compareTo(d1);
        	// return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareDefendReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getDefendReboundEfficient();
        	Double d1 = o1.getDefendReboundEfficient();
            return d2.compareTo(d1);
        	//return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareAssistEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getAssistEfficient();
        	Double d1 = o1.getAssistEfficient();
            return d2.compareTo(d1);
        	//return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareStealEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getStealEfficient();
        	Double d1 = o1.getStealEfficient();
            return d2.compareTo(d1);
        	//return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareBlockShotEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getBlockShotEfficient();
        	Double d1 = o1.getBlockShotEfficient();
            return d2.compareTo(d1);
        	//return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFaultEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getFaultEfficient();
        	Double d1 = o1.getFaultEfficient();
            return d2.compareTo(d1);
        	// return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFrequencyDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = o2.getFrequency();
        	Double d1 = o1.getFrequency();
            return d2.compareTo(d1);
        	//return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
        }
    };
    
    
    //avg player descent comparator
	public Comparator<Player> comparePointAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = (double)o2.getPoint()/(double)o2.appearance;
        	Double d1 = (double)o1.getPoint()/(double)o1.appearance;
            return d2.compareTo(d1);
        //	return (double)o2.getPoint()/(double)o2.appearance > (double)o1.getPoint()/(double)o1.appearance? 1 : -1;
        }
    };
    
    public Comparator<Player> compareReboundAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = (double)o2.getRebound()/(double)o2.appearance;
        	Double d1 = (double)o1.getRebound()/(double)o1.appearance;
            return d2.compareTo(d1);
        	//return (double)o2.getRebound()/(double)o2.appearance > (double)o1.getRebound()/(double)o1.appearance? 1 : -1;
        }
    };
    public Comparator<Player> compareAssistAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 = (double)o2.getAssist()/(double)o2.appearance;
        	Double d1 = (double)o1.getAssist()/(double)o1.appearance;
            return d2.compareTo(d1);
           // return (double)o2.getAssist()/(double)o2.appearance > (double)o1.getAssist()/(double)o1.appearance? 1 : -1;
        }
    };
    
    public Comparator<Player> compareBlockShotAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
//  	return (double)o2.getBlockShot()/(double)o2.appearance > (double)o1.getBlockShot()/(double)o1.appearance? 1 : -1;
        	Double d2 = (double)o2.getBlockShot()/(double)o2.appearance;
        	Double d1 = (double)o1.getBlockShot()/(double)o1.appearance;
            return d2.compareTo(d1);
        }
    };
    
    public Comparator<Player> compareStealAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =(double)o2.getSteal()/(double)o2.appearance ;
        	Double d1 =(double)o1.getSteal()/(double)o1.appearance ;
        	return d2.compareTo(d1);      
        }
    };
    
    public Comparator<Player> compareFoulAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =(double)o2.getFoul()/(double)o2.appearance ;
        	Double d1 =(double)o1.getFoul()/(double)o1.appearance ;
        	return d2.compareTo(d1);            	
        }
    };
    
    public Comparator<Player> compareFaultAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =(double)o2.getFault()/(double)o2.appearance ;
        	Double d1 =(double)o1.getFault()/(double)o1.appearance ;
        	return d2.compareTo(d1);  
        }
    };
    
    public Comparator<Player> compareMinuteAvgDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =(double)o2.getMinute()/(double)o2.appearance ;
        	Double d1 =(double)o1.getMinute()/(double)o1.appearance ;
        	return d2.compareTo(d1);  
        }
    };
    
    //Promotion
    public Comparator<Player> compareAssistPromotionDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =o2.assistPromotion;
        	Double d1 =o1.assistPromotion ;
        	return d2.compareTo(d1);  
        }
    };
    
    public Comparator<Player> compareScorePromotionDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =o2.scorePromotion;
        	Double d1 =o1.scorePromotion ;
        	return d2.compareTo(d1);
        	//return o2.scorePromotion > o1.scorePromotion? 1 : -1;
        }
    };
    
    public Comparator<Player> compareReboundPromotionDesc = new Comparator<Player>(){  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	Double d2 =o2.reboundPromotion;
        	Double d1 =o1.reboundPromotion ;
        	return d2.compareTo(d1);
        	//return o2.reboundPromotion > o1.reboundPromotion? 1 : -1;
        }
    };
    
    
	public Comparator<Player> getPlayerComparator(String sort) {
		switch(sort) {
		case "score": return comparePointDesc;
		case "point": return comparePointDesc;
		case "rebound": return compareReboundDesc;
		case "assist": return compareAssistDesc;					
		case "blockShot": return compareBlockShotDesc;
		case "steal": return compareStealDesc;
		case "foul": return compareFoulDesc;
		case "fault": return compareFaultDesc;
		case "minute": return compareMinuteDesc;
		case "efficient": return compareEfficientDesc;
		case "shot": return compareShotDesc;
		case "three": return compareThreeDesc;
		case "penalty": return comparePenaltyDesc;
		case "doubleTwo": return compareDoubleTwoDesc;
		case "realShot": return compareRealShotDesc;
		case "GmSc": return compareGmScDesc;
		case "shotEfficient": return compareShotEfficientDesc;
		case "reboundEfficient": return compareReboundEfficientDesc;
		case "offendReboundEfficient": return compareOffendReboundEfficientDesc;
		case "defendReboundEfficient": return compareDefendReboundEfficientDesc;
		case "assistEfficient": return compareAssistEfficientDesc;
		case "stealEfficient": return compareStealEfficientDesc;		
		case "blockShotEfficient": return compareBlockShotEfficientDesc;
		case "faultEfficient": return compareFaultEfficientDesc;
		case "frequency": return compareFrequencyDesc;
		default: return null;
		}
	}
	
	public Comparator<Player> getPlayerAvgComparator(String sort) {
		switch(sort) {
		case "score": return comparePointAvgDesc;
		case "point": return comparePointAvgDesc;
		case "rebound": return compareReboundAvgDesc;
		case "assist": return compareAssistAvgDesc;					
		case "blockShot": return compareBlockShotAvgDesc;
		case "steal": return compareStealAvgDesc;
		case "foul": return compareFoulAvgDesc;
		case "fault": return compareFaultAvgDesc;
		case "minute": return compareMinuteAvgDesc;
		default: return null;
		}
	}
	
	public Comparator<Team> getTeamComparator(String sort) {
		switch(sort) {
		case "point": return TeamByPoint;
		case "score": return TeamByPoint;
		case "rebound": return TeamByRebound;
		case "blockShot": return TeamByBlock;
		case "assist": return TeamByAssist;
		case "steal": return TeamBySteal;
		case "foul": return TeamByFoul;
		case "fault": return TeamByFault;
		case "three": return TeamByThree;
		case "shot": return TeamByShot;
		case "penalty": return TeamByPenalty;
		case "defendRebound": return TeamByDefendRebound;
		case "offendRebound": return TeamByOffendRebound;
		case "winRate": return TeamByWinRate;
		case "offendRound": return TeamByOffendRound;
		case "offendEfficient": return TeamByOffendEfficient;
		case "defendEfficient": return TeamByDefendEfficient;
		case "offendReboundEfficient": return TeamByOffendReboundEfficient;
		case "defendReboundEfficient": return TeamByDefendReboundEfficient;
		case "stealEfficient": return TeamByStealEfficient;
		case "assistEfficient": return TeamByAssistEfficient;
		default: return TeamByPoint;
		}
	}
	
	public boolean sortTeams(String sort) {
		Comparator<Team> ct = getTeamComparator(sort);
		if(ct==null)
			return false;
		Collections.sort(teams, ct);
		return true;
	}
}
