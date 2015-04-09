package server.businesslogic;

import java.util.ArrayList;
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

	HashMap<String, Player> playersHash = new HashMap<String, Player>(606);
	HashMap<String, Team> teamsHash = new HashMap<String, Team>(41);
	ArrayList<Player> players = new ArrayList<Player>(460);// buffer for
															// analysed player
															// datas
	ArrayList<Team> teams = new ArrayList<Team>(31);// see above
	DataService data = new DataController();
	private static BLController instance = null;

	public static BLController getInstance() {// 单体模式
		if (instance == null)
			instance = new BLController();
		return instance;
	}
	
	//iteration 2
	public ArrayList<TeamVO> getHotTeamVO(String sortCon) {
		ArrayList <Team>h = getHotTeam(sortCon);
		return toTVOs(h);
	}
	
	public ArrayList<Team> getHotTeam(String sortCon) {
		analyse();
		sortTeams(sortCon);
		return teams;
	}

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
		if (players.size() > 0)// already have content in buffer
			return true;
		else {// when just start the program, we will first compute the data
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
	}

	private boolean linkDatas() {// some prepared procedure before analyse,
									// construct some hashmap, to accelarate the
									// speed of it
		ArrayList<MatchPO> matches = data.getAllMatch();
		Collections.sort(matches, new SortMatchesByCalendar());
		HashMap<String, TeamPO> teamPOHash = data.getAllTeams();
		HashMap<String, PlayerPO> playerPOHash = data.getAllPlayers();
		for (int i = matches.size() - 1; i >= 0; i--) {
			MatchPO mttemp = matches.get(i);
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
			Team foundTeam = teamsHash.get(ab);
			if (foundTeam == null) {
				Team tTeam = new Team(teamPOHash.get(ab));
				tTeam.addThisTeam(timtemp1);
				tTeam.addOpponentTeam(timtemp2);
				tTeam.addMatch(mttemp);
				teamsHash.put(ab, tTeam);
			} else {
				foundTeam.addThisTeam(timtemp1);
				foundTeam.addOpponentTeam(timtemp2);
				foundTeam.addMatch(mttemp);
			}

			// put some data in team 2
			ab = timtemp2.getAbbreviation();
			foundTeam = teamsHash.get(ab);
			if (foundTeam == null) {
				Team tTeam = new Team(teamPOHash.get(ab));
				tTeam.addThisTeam(timtemp2);
				tTeam.addOpponentTeam(timtemp1);
				tTeam.addMatch(mttemp);
				teamsHash.put(ab, tTeam);
			} else {
				foundTeam.addThisTeam(timtemp2);
				foundTeam.addOpponentTeam(timtemp1);
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
					tPlayer.addThisTeam(timtemp1, j);
					tPlayer.addOpponentTeam(timtemp2);
					tPlayer.addMatch(mttemp);
					playersHash.put(name, tPlayer);
				} else {
					foundPlayer.addThisTeam(timtemp1, j);
					foundPlayer.addOpponentTeam(timtemp2);
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
					tPlayer.addThisTeam(timtemp2, j);
					tPlayer.addOpponentTeam(timtemp1);
					tPlayer.addMatch(mttemp);
					playersHash.put(name, tPlayer);
				} else {
					foundPlayer.addThisTeam(timtemp2, j);
					foundPlayer.addOpponentTeam(timtemp1);
					foundPlayer.addMatch(mttemp);
				}
			}
		}
		return true;
	}

	private ArrayList<PlayerVO> getPlayersInTeam(String abbreviation) {// 参数是简称
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		int i = 5;
		for (i = 5; i < players.size(); i += 10)
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
			return (double)t1.score/t1.appearance>(double)t2.score/t2.appearance?1:-1;
		}
	};
	private static final Comparator<Team> TeamByRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return (double)t1.rebound/t1.appearance>(double)t2.rebound/t2.appearance?1:-1;
		}
	};
	private static final Comparator<Team> TeamByAssist = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return (double)t1.assist/t1.appearance>(double)t2.assist/t2.appearance?1:-1;
		}
	};
	private static final Comparator<Team> TeamByBlock = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return (double)t1.blockShot/t1.appearance>(double)t2.blockShot/t2.appearance?1:-1;
		}
	};
	private static final Comparator<Team> TeamBySteal = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return (double)t1.steal/t1.appearance>(double)t2.steal/t2.appearance?1:-1;
		}
	};
	private static final Comparator<Team> TeamByThree = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getThree()>t2.getThree()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByShot = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getShot()>t2.getShot()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByPenalty = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getPenalty()>t2.getPenalty()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByFoul = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.foul>t2.foul?1:-1;
		}
	};
	private static final Comparator<Team> TeamByFault = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.fault>t2.fault?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.defendRebound>t2.defendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByWinRate = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.offendRebound>t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.offendRebound>t2.offendRebound?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendRound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getOffendRound()>t2.getOffendRound()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getOffendEfficient()>t2.getOffendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getDefendEfficient()>t2.getDefendEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByOffendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getOffendReboundEfficient()>t2.getOffendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByDefendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getDefendReboundEfficient()>t2.getDefendReboundEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByStealEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getStealEfficient()>t2.getStealEfficient()?1:-1;
		}
	};
	private static final Comparator<Team> TeamByAssistEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getAssistEfficient()>t2.getAssistEfficient()?1:-1;
		}
	};
	public boolean sortTeams(String sort) {
		switch(sort) {
		case "point": Collections.sort(teams, TeamByPoint);break;
		case "score": Collections.sort(teams, TeamByPoint);break;
		case "rebound": Collections.sort(teams, TeamByRebound);break;
		case "blockShot": Collections.sort(teams, TeamByBlock);break;
		case "assist": Collections.sort(teams, TeamByAssist);break;
		case "steal": Collections.sort(teams, TeamBySteal);break;
		case "foul": Collections.sort(teams, TeamByFoul);break;
		case "fault": Collections.sort(teams, TeamByFault);break;
		case "three": Collections.sort(teams, TeamByThree);break;
		case "shot": Collections.sort(teams, TeamByShot);break;
		case "penalty": Collections.sort(teams, TeamByPenalty);break;
		case "defendRebound": Collections.sort(teams, TeamByDefendRebound);break;
		case "offendRebound": Collections.sort(teams, TeamByOffendRebound);break;
		case "winRate": Collections.sort(teams, TeamByWinRate);break;
		case "offendRound": Collections.sort(teams, TeamByOffendRound);break;
		case "offendEfficient": Collections.sort(teams, TeamByOffendEfficient);break;
		case "defendEfficient": Collections.sort(teams, TeamByDefendEfficient);break;
		case "offendReboundEfficient": Collections.sort(teams, TeamByOffendReboundEfficient);break;
		case "defendReboundEfficient": Collections.sort(teams, TeamByDefendReboundEfficient);break;
		case "stealEfficient": Collections.sort(teams, TeamByStealEfficient);break;
		case "assistEfficient": Collections.sort(teams, TeamByAssistEfficient);break;
		default: return false;
		}
		return true;
	}
}
