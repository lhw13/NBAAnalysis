package server.data;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import server.po.HeightPO;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.PlayerPO;
import server.po.ScorePO;
import server.po.TeamInMatchesPO;
import server.po.TeamPO;

public class DatabaseController implements Runnable {
	static int startY=2012;
	private static HashMap<String, DataClass> wholeData = new HashMap<>(50);
	static {
		for (int y = startY; y < 2016; y++) {
			String y1 = (y + "").substring(2, 4);
			String y2 = ((y + 1) + "").substring(2, 4);
			String season = y1 + "-" + y2;
			wholeData.put(season, new DataClass());
		}
	}
	static Statement stat = null;
	static String[][] list = new String[4145][2];
	private static String season = "15-16";
	int n;

	public static void main(String[] args) throws Exception {
		 Class.forName("org.sqlite.JDBC");
		 Connection conn = DriverManager
		 .getConnection("jdbc:sqlite:"+Path.path);
		 stat = conn.createStatement();
		//
		//
		// writeMatch(Ant.analyseteaminfo("http://www.nba.com/games/20140502/TORBKN/gameinfo.html"));
		// DataTransformation.MatchPOToText(getMatchPOListBySeason("13-14").get(0),"E:\\dataText\\");
		// System.out.println(getMatchPOListBySeason("13-14").size());

//		 File f = new
//		 File("C:\\Users\\wjc\\Desktop\\CSEIII NBA Tool\\liveData");
//		 File[] matchesFile = f.listFiles();
//		 for (File i : matchesFile) {
//		 MatchPO newMatchPO=MatchesDataAnalyse.MatchPOMade(DataReader
//		 .dataReader(i));
//		 if(newMatchPO!=null){
//		 writeMatch(newMatchPO);
//		 }
//		 }
		 for(int y=2015;y<2016;y++){
		 String y1=(y+"").substring(2, 4);
		 String y2=((y+1)+"").substring(2, 4);
		 String season=y1+"-"+y2;
		 System.out.println(season);
//		 ArrayList<MatchPO> list1=new ArrayList<MatchPO>(2000);
//		 File f = new File("D:\\dataBasketball\\"+season+"regular");
//		 File[] matchesFile = f.listFiles();
//		 System.out.println(matchesFile.length);
//		 for (File i : matchesFile) {
//		 MatchPO newMatchPO=MatchesDataAnalyse.MatchPOMade(DataReader
//		 .dataReader(i));
//		 if(newMatchPO!=null){
//		 newMatchPO.setType("regular");
//		 list1.add(newMatchPO);
//		 }
//		 }
//		 for(MatchPO mp:list1){
//		 writeMatch(mp);
//		 }
		 
		 ArrayList<MatchPO> list2=new ArrayList<MatchPO>(200);
		 File f1 = new File("D:\\dataBasketball\\"+season+"playOff");
		 File[] matchesFile1 = f1.listFiles();
		 System.out.println(matchesFile1.length);
		 for (File i : matchesFile1) {
		 MatchPO newMatchPO=MatchesDataAnalyse.MatchPOMade(DataReader
		 .dataReader(i));
		 if(newMatchPO!=null){
		 newMatchPO.setType("playOff");
		 list2.add(newMatchPO);
		
		 }
		 }
		 for(MatchPO mp:list2){
		 writeMatch(mp);
		 }
		 }
		 conn.close();
		// createTableOfPlayer();
		// File f = new File("C:\\Users\\wjc\\Desktop\\info");
		// File[] PlayersFile = f.listFiles();
		// for (File i : PlayersFile) {
		// writePlayer(PlayersDataAnalyse.PlayerPOMade(DataReader
		// .dataReader(i)));
		// }

		// createTableOfTeam();
		// File teamsFile = new File(Console.path+"/teams/teams");
		// ArrayList<TeamPO>teamPOList =
		// TeamsDataAnalyse.teamPOListMade(DataReader
		// .dataReader(teamsFile));
		// for(TeamPO tp:teamPOList){
		// writeTeam(tp);
		// }

		//
		// ResultSet rs = stat.executeQuery("select * from 'PlayerPO';");
		// int n = 0;
		// while (rs.next()) {
		// list[n][0] = rs.getString("name");
		// list[n][1] = rs.getString("position");
		// n++;
		// }
		// for (int y = 1986; y < 2015; y++) {
		// String y1 = (y + "").substring(2, 4);
		// String y2 = ((y + 1) + "").substring(2, 4);
		// String tableName = "playerInMatchesPO" + y1 + "" + y2;
		// for (String[] player : list) {
		// stat.execute("update " + tableName + " set position='"
		// + player[1] + "' where name='"
		// + player[0].replaceAll("'", "''") + "';");
		// }
		// }
		// conn.close();

		// DatabaseController a=new DatabaseController();
		// DatabaseController b=new DatabaseController();
		// DatabaseController c=new DatabaseController();
		// a.setN(0);
		// b.setN(1);
		// c.setN(2);
		// Thread ta=new Thread(a);
		// Thread tb=new Thread(b);
		// Thread tc=new Thread(c);
		// ta.start();
		// tb.start();
		// tc.start();
		// while(true){
		// for(int y=1985;y<2015;y++){
		// String y1 = (y + "").substring(2, 4);
		// String y2 = ((y + 1) + "").substring(2, 4);
		// String season = y1 + "-" + y2;
		// System.out.print(wholeData.get(season).isOK()+" ");
		// }
		// System.out.println("");
		// Thread.sleep(5000);
		// }
	}

	private static void writeMatch(MatchPO mp) throws Exception {
		String season = mp.getSeason().substring(0, 2)
				+ mp.getSeason().substring(3, 5);
		String matchTableName = "MatchPO" + season;
		String playerTableName = "playerInMatchesPO" + season;
		String sql = "insert into " + matchTableName + " values(";
		sql = sql + "'" + mp.getFileName() + "','" + mp.getSeason() + "',";
		int year = mp.getDate().get(Calendar.YEAR);
		int month = mp.getDate().get(Calendar.MONTH);
		int day = mp.getDate().get(Calendar.DAY_OF_MONTH);
		sql = sql + year + "," + month + "," + day + ",";
		ArrayList<PlayerInMatchesPO> team1 = mp.getTeam1().getPlayers();
		ArrayList<PlayerInMatchesPO> team2 = mp.getTeam2().getPlayers();
		ArrayList<ScorePO> scores = mp.getScores();
		String abbreviation1 = mp.getTeam1().getAbbreviation();
		String abbreviation2 = mp.getTeam2().getAbbreviation();
		sql = sql + "'" + abbreviation1 + "','" + abbreviation2 + "',";
		sql = sql + scores.get(0).getTeam1() + "," + scores.get(0).getTeam2()
				+ "," + scores.get(1).getTeam1() + ","
				+ scores.get(1).getTeam2() + "," + scores.get(2).getTeam1()
				+ "," + scores.get(2).getTeam2() + ","
				+ scores.get(3).getTeam1() + "," + scores.get(3).getTeam2()
				+ ",";
		sql = sql + mp.getFinalScore().getTeam1() + ","
				+ mp.getFinalScore().getTeam2() + ",'" + mp.getType() + "');";
		try {
			stat.execute(sql);
		} catch (SQLException e) {
			// e.printStackTrace();
			try {
				createTableOfMatch(season);
				stat.execute(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		for (PlayerInMatchesPO playerScore : team1) {
			String sql1 = "insert into " + playerTableName + " values(";
			sql1 = sql1 + "'" + mp.getFileName() + "','"
					+ mp.getTeam1().getAbbreviation() + "',";
			sql1 = sql1 + "'" + playerScore.getName().replaceAll("'", "''")
					+ "','" + playerScore.getPosition() + "',"
					+ playerScore.getPlayTime() + "," + playerScore.getHit()
					+ "," + playerScore.getShot() + ","
					+ playerScore.getThirdHit() + ","
					+ playerScore.getThirdshot() + ","
					+ playerScore.getFreeHit() + ","
					+ playerScore.getFreeshot() + ","
					+ playerScore.getOffensiveRebound() + ","
					+ playerScore.getDefensiveRebound() + ","
					+ playerScore.getTotalRebound() + ","
					+ playerScore.getAssist() + "," + playerScore.getSteal()
					+ "," + playerScore.getBlock() + ","
					+ playerScore.getMiss() + "," + playerScore.getFoul() + ","
					+ playerScore.getScore();
			sql1 = sql1 + ");";
			try {
				stat.execute(sql1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(mp.getFileName());
				System.out.println(sql1);
				e.printStackTrace();
			}
		}
		for (PlayerInMatchesPO playerScore : team2) {

			String sql2 = "insert into " + playerTableName + " values(";
			sql2 = sql2 + "'" + mp.getFileName() + "','"
					+ mp.getTeam2().getAbbreviation() + "',";
			sql2 = sql2 + "'" + playerScore.getName().replaceAll("'", "''")
					+ "','" + playerScore.getPosition() + "',"
					+ playerScore.getPlayTime() + "," + playerScore.getHit()
					+ "," + playerScore.getShot() + ","
					+ playerScore.getThirdHit() + ","
					+ playerScore.getThirdshot() + ","
					+ playerScore.getFreeHit() + ","
					+ playerScore.getFreeshot() + ","
					+ playerScore.getOffensiveRebound() + ","
					+ playerScore.getDefensiveRebound() + ","
					+ playerScore.getTotalRebound() + ","
					+ playerScore.getAssist() + "," + playerScore.getSteal()
					+ "," + playerScore.getBlock() + ","
					+ playerScore.getMiss() + "," + playerScore.getFoul() + ","
					+ playerScore.getScore();
			sql2 = sql2 + ");";
			try {
				stat.execute(sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void createTableOfMatch(String season) throws Exception {
		stat.execute("create table if not exists MatchPO"
				+ season
				+ "(key varchar(20), season varchar(10), year int, month int, day int,team1 varchar(5),team2 varchar(5), score1 int, score2 int, score3 int, score4 int, score5 int, score6 int, score7 int, score8 int, score9 int, score10 int ,type varchar(10),primary key(key));");
		stat.execute("create table if not exists playerInMatchesPO"
				+ season
				+ "(key varchar(20), team varchar(5), name varchar(30), position varchar(5), playTime int, hit int, shot int, thirdHit int, thirdshot int, freeHit int, freeshot int, offensiveRebound int, defensiveRebound int, totalRebound int, assist int, steal int, block int, miss int, foul int, score int,primary key(key,name,team));");
	}

	public static ArrayList<MatchPO> getMatchPOListBySeason() throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		Statement stat1 = conn.createStatement();
		Statement stat2 = conn.createStatement();
		String table1 = "MatchPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		String table2 = "playerInMatchesPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		ArrayList<MatchPO> matchPOlist = new ArrayList<MatchPO>(2000);
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from '" + table1 + "';");
		} catch (SQLException e) {
			return null;
		}
		while (rs.next()) {
			String key = rs.getString("key");
			String season = rs.getString("season");
			Calendar date = Calendar.getInstance();
			date.set(rs.getInt("year"), rs.getInt("month"), rs.getInt("day"),
					0, 0, 0);
			ScorePO finalScore = new ScorePO(rs.getInt("score9"),
					rs.getInt("score10"));
			ArrayList<ScorePO> scores = new ArrayList<ScorePO>(4);
			scores.add(new ScorePO(rs.getInt("score1"), rs.getInt("score2")));
			scores.add(new ScorePO(rs.getInt("score3"), rs.getInt("score4")));
			scores.add(new ScorePO(rs.getInt("score5"), rs.getInt("score6")));
			scores.add(new ScorePO(rs.getInt("score7"), rs.getInt("score8")));
			String abbreviation1 = rs.getString("team1");
			String abbreviation2 = rs.getString("team2");
			ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>(
					15);
			ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>(
					15);
			ResultSet rs1 = stat1.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation1
					+ "';");
			while (rs1.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs1.getString("name"), rs1.getString("position"),
						rs1.getInt("playTime"), rs1.getInt("hit"),
						rs1.getInt("shot"), rs1.getInt("thirdHit"),
						rs1.getInt("thirdShot"), rs1.getInt("freeHit"),
						rs1.getInt("freeshot"), rs1.getInt("offensiveRebound"),
						rs1.getInt("defensiveRebound"),
						rs1.getInt("totalRebound"), rs1.getInt("assist"),
						rs1.getInt("steal"), rs1.getInt("block"),
						rs1.getInt("miss"), rs1.getInt("foul"),
						rs1.getInt("score"));
				players1.add(player);
			}
			ResultSet rs2 = stat2.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation2
					+ "';");
			while (rs2.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs2.getString("name"), rs2.getString("position"),
						rs2.getInt("playTime"), rs2.getInt("hit"),
						rs2.getInt("shot"), rs2.getInt("thirdHit"),
						rs2.getInt("thirdShot"), rs2.getInt("freeHit"),
						rs2.getInt("freeshot"), rs2.getInt("offensiveRebound"),
						rs2.getInt("defensiveRebound"),
						rs2.getInt("totalRebound"), rs2.getInt("assist"),
						rs2.getInt("steal"), rs2.getInt("block"),
						rs2.getInt("miss"), rs2.getInt("foul"),
						rs2.getInt("score"));
				players2.add(player);
			}
			TeamInMatchesPO team1 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation1), players1);
			TeamInMatchesPO team2 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation2), players2);
			MatchPO matchPO = new MatchPO(season, date, finalScore, scores,
					team1, team2);
			matchPO.setFileName(key);
			matchPO.setType(rs.getString("type"));
			matchPOlist.add(matchPO);
		}

		conn.close();
		return matchPOlist;
	}

	private static void createTableOfPlayer() throws Exception {
		stat.execute("create table if not exists PlayerPO(name varchar(30), number int,  position varchar(5), feet int,inch int,weight int, year int, month int, day int ,age int, exp int, school varchar(50),primary key(name));");
	}

	private static void writePlayer(PlayerPO pp) throws Exception {
		String sql = "insert into PlayerPO values(";
		int year = pp.getBirth().get(Calendar.YEAR);
		int month = pp.getBirth().get(Calendar.MONTH);
		int day = pp.getBirth().get(Calendar.DAY_OF_MONTH);
		sql = sql + "'" + pp.getName().replaceAll("'", "''") + "',"
				+ pp.getNumber() + ",'" + pp.getPosition() + "',"
				+ pp.getHeight().getFeet() + "," + pp.getHeight().getInch()
				+ "," + pp.getWeight() + "," + year + "," + month + "," + day
				+ "," + pp.getAge() + "," + pp.getExp() + ",'"
				+ pp.getSchool().replaceAll("'", "''") + "');";
		stat.execute(sql);
	}

	public static ArrayList<PlayerPO> getPlayerList() throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		ArrayList<PlayerPO> ppList = new ArrayList<PlayerPO>(5000);
		ResultSet rs = stat.executeQuery("select * from 'PlayerPO';");
		while (rs.next()) {
			String name = rs.getString("name");
			int number = rs.getInt("number");
			String position = rs.getString("position");
			HeightPO height = new HeightPO(rs.getInt("feet"), rs.getInt("inch"));
			int weight = rs.getInt("weight");
			Calendar birth = Calendar.getInstance();
			birth.set(rs.getInt("year"), rs.getInt("month"), rs.getInt("day"),
					0, 0, 0);
			int age = rs.getInt("age");
			int exp = rs.getInt("exp");
			String school = rs.getString("school");
			PlayerPO pp = new PlayerPO(name, number, position, height, weight,
					birth, age, exp, school);
			ppList.add(pp);
		}
		conn.close();

		return ppList;
	}

	private static void createTableOfTeam() throws Exception {
		stat.execute("create table if not exists TeamPO( fullName varchar(30), abbreviation varchar(5),location varchar(30),division varchar(2),  zone varchar(30), home varchar(50), setupTime int,primary key(fullName));");
	}

	private static void writeTeam(TeamPO tp) throws Exception {
		String sql = "insert into TeamPO values(";
		String fullName = tp.getFullName();
		String abbreviation = tp.getAbbreviation();
		String location = tp.getLocation();
		char division = tp.getDivision();
		String zone = tp.getZone();
		String home = tp.getHome();
		int setupTime = tp.getSetupTime();
		sql = sql + "'" + fullName + "','" + abbreviation + "','" + location
				+ "','" + division + "','" + zone + "','" + home + "',"
				+ setupTime + ");";
		stat.execute(sql);
	}

	public static ArrayList<TeamPO> getTeamList() throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		ArrayList<TeamPO> teamList = new ArrayList<TeamPO>(40);
		ResultSet rs = stat.executeQuery("select * from 'TeamPO';");
		while (rs.next()) {
			TeamPO tp = new TeamPO(rs.getString("fullName"),
					rs.getString("abbreviation"), rs.getString("location"), rs
							.getString("division").charAt(0),
					rs.getString("zone"), rs.getString("home"),
					rs.getInt("setupTime"));
			teamList.add(tp);
		}

		conn.close();
		return teamList;
	}

	public static ArrayList<MatchPO> getMatchByDate(Calendar date)
			throws Exception {

		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		Statement stat1 = conn.createStatement();
		Statement stat2 = conn.createStatement();
		String table1 = "MatchPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		String table2 = "playerInMatchesPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		ArrayList<MatchPO> matchPOlist = new ArrayList<MatchPO>(2000);
		ResultSet rs = null;
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);
		try {
			rs = stat.executeQuery("select * from '" + table1
					+ "' where year= " + year + "and month=" + month
					+ " and day=" + day + ";");
		} catch (SQLException e) {
			return null;
		}
		while (rs.next()) {
			String key = rs.getString("key");
			String season = rs.getString("season");
			ScorePO finalScore = new ScorePO(rs.getInt("score9"),
					rs.getInt("score10"));
			ArrayList<ScorePO> scores = new ArrayList<ScorePO>(4);
			scores.add(new ScorePO(rs.getInt("score1"), rs.getInt("score2")));
			scores.add(new ScorePO(rs.getInt("score3"), rs.getInt("score4")));
			scores.add(new ScorePO(rs.getInt("score5"), rs.getInt("score6")));
			scores.add(new ScorePO(rs.getInt("score7"), rs.getInt("score8")));
			String abbreviation1 = rs.getString("team1");
			String abbreviation2 = rs.getString("team2");
			ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>(
					15);
			ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>(
					15);
			ResultSet rs1 = stat1.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation1
					+ "';");
			while (rs1.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs1.getString("name"), rs1.getString("position"),
						rs1.getInt("playTime"), rs1.getInt("hit"),
						rs1.getInt("shot"), rs1.getInt("thirdHit"),
						rs1.getInt("thirdShot"), rs1.getInt("freeHit"),
						rs1.getInt("freeshot"), rs1.getInt("offensiveRebound"),
						rs1.getInt("defensiveRebound"),
						rs1.getInt("totalRebound"), rs1.getInt("assist"),
						rs1.getInt("steal"), rs1.getInt("block"),
						rs1.getInt("miss"), rs1.getInt("foul"),
						rs1.getInt("score"));
				players1.add(player);
			}
			ResultSet rs2 = stat2.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation2
					+ "';");
			while (rs2.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs2.getString("name"), rs2.getString("position"),
						rs2.getInt("playTime"), rs2.getInt("hit"),
						rs2.getInt("shot"), rs2.getInt("thirdHit"),
						rs2.getInt("thirdShot"), rs2.getInt("freeHit"),
						rs2.getInt("freeshot"), rs2.getInt("offensiveRebound"),
						rs2.getInt("defensiveRebound"),
						rs2.getInt("totalRebound"), rs2.getInt("assist"),
						rs2.getInt("steal"), rs2.getInt("block"),
						rs2.getInt("miss"), rs2.getInt("foul"),
						rs2.getInt("score"));
				players2.add(player);
			}
			TeamInMatchesPO team1 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation1), players1);
			TeamInMatchesPO team2 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation2), players2);
			MatchPO matchPO = new MatchPO(season, date, finalScore, scores,
					team1, team2);
			matchPO.setFileName(key);
			matchPO.setType(rs.getString("type"));
			matchPOlist.add(matchPO);
		}

		conn.close();
		return matchPOlist;
	}

	public static ArrayList<PlayerInMatchesPO> getPlayerInMatchesPOByName(
			String name) throws Exception {
		ArrayList<PlayerInMatchesPO> result = new ArrayList<PlayerInMatchesPO>(
				500);
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		String table = "playerInMatchesPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		ResultSet rs = stat.executeQuery("select * from '" + table
				+ "' where name='" + name + "';");
		while (rs.next()) {

			PlayerInMatchesPO player = new PlayerInMatchesPO(
					rs.getString("name"), rs.getString("position"),
					rs.getInt("playTime"), rs.getInt("hit"), rs.getInt("shot"),
					rs.getInt("thirdHit"), rs.getInt("thirdShot"),
					rs.getInt("freeHit"), rs.getInt("freeshot"),
					rs.getInt("offensiveRebound"),
					rs.getInt("defensiveRebound"), rs.getInt("totalRebound"),
					rs.getInt("assist"), rs.getInt("steal"),
					rs.getInt("block"), rs.getInt("miss"), rs.getInt("foul"),
					rs.getInt("score"));
			result.add(player);

		}

		conn.close();
		return result;
	}

	public static ArrayList<MatchPO> getMatchPObyTeam(String team)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		Statement stat1 = conn.createStatement();
		Statement stat2 = conn.createStatement();
		String table1 = "MatchPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		String table2 = "playerInMatchesPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		ArrayList<MatchPO> matchPOlist = new ArrayList<MatchPO>(2000);
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from '" + table1
					+ "' where team1='" + team + "' or team2='" + team + "' ;");
		} catch (SQLException e) {
			return null;
		}
		while (rs.next()) {
			String key = rs.getString("key");
			String season = rs.getString("season");
			Calendar date = Calendar.getInstance();
			date.set(rs.getInt("year"), rs.getInt("month"), rs.getInt("day"),
					0, 0, 0);
			ScorePO finalScore = new ScorePO(rs.getInt("score9"),
					rs.getInt("score10"));
			ArrayList<ScorePO> scores = new ArrayList<ScorePO>(4);
			scores.add(new ScorePO(rs.getInt("score1"), rs.getInt("score2")));
			scores.add(new ScorePO(rs.getInt("score3"), rs.getInt("score4")));
			scores.add(new ScorePO(rs.getInt("score5"), rs.getInt("score6")));
			scores.add(new ScorePO(rs.getInt("score7"), rs.getInt("score8")));
			String abbreviation1 = rs.getString("team1");
			String abbreviation2 = rs.getString("team2");
			ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>(
					15);
			ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>(
					15);
			ResultSet rs1 = stat1.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation1
					+ "';");
			while (rs1.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs1.getString("name"), rs1.getString("position"),
						rs1.getInt("playTime"), rs1.getInt("hit"),
						rs1.getInt("shot"), rs1.getInt("thirdHit"),
						rs1.getInt("thirdShot"), rs1.getInt("freeHit"),
						rs1.getInt("freeshot"), rs1.getInt("offensiveRebound"),
						rs1.getInt("defensiveRebound"),
						rs1.getInt("totalRebound"), rs1.getInt("assist"),
						rs1.getInt("steal"), rs1.getInt("block"),
						rs1.getInt("miss"), rs1.getInt("foul"),
						rs1.getInt("score"));
				players1.add(player);
			}
			ResultSet rs2 = stat2.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation2
					+ "';");
			while (rs2.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs2.getString("name"), rs2.getString("position"),
						rs2.getInt("playTime"), rs2.getInt("hit"),
						rs2.getInt("shot"), rs2.getInt("thirdHit"),
						rs2.getInt("thirdShot"), rs2.getInt("freeHit"),
						rs2.getInt("freeshot"), rs2.getInt("offensiveRebound"),
						rs2.getInt("defensiveRebound"),
						rs2.getInt("totalRebound"), rs2.getInt("assist"),
						rs2.getInt("steal"), rs2.getInt("block"),
						rs2.getInt("miss"), rs2.getInt("foul"),
						rs2.getInt("score"));
				players2.add(player);
			}
			TeamInMatchesPO team1 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation1), players1);
			TeamInMatchesPO team2 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation2), players2);
			MatchPO matchPO = new MatchPO(season, date, finalScore, scores,
					team1, team2);
			matchPO.setFileName(key);
			matchPO.setType(rs.getString("type"));
			matchPOlist.add(matchPO);
		}

		conn.close();
		return matchPOlist;
	}

	public static ArrayList<ScorePO> getPK(String ab1, String ab2)
			throws Exception {
		ArrayList<ScorePO> result = new ArrayList<ScorePO>(150);
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		for (int y = startY; y < 2016; y++) {
			String y1 = (y + "").substring(2, 4);
			String y2 = ((y + 1) + "").substring(2, 4);
			String season = y1 + "-" + y2;
			String table = "MatchPO" + season.substring(0, 2)
					+ season.substring(3, 5);
			ResultSet rs = null;
			String team1=TransToStandard.getReal(ab1,season);
			String team2=TransToStandard.getReal(ab2,season);
			try {
				rs = stat
						.executeQuery("select * from '" + table
								+ "' where (team1='" + team1 + "' and team2='"
								+ team2 + "') or(team1='" + team2 + "' and team2='"
								+ team1 + "');");
			} catch (SQLException e) {
				return null;
			}
			while (rs.next()) {
				if (rs.getString("team1").equals(team1)) {
					ScorePO finalScore = new ScorePO(rs.getInt("score9"),
							rs.getInt("score10"));
					result.add(finalScore);
				} else {
					ScorePO finalScore = new ScorePO(rs.getInt("score10"),
							rs.getInt("score9"));
					result.add(finalScore);
				}
			}
		}
		conn.close();
		return result;
	}

	public static void setSeason(String newSeason) {
		season = newSeason;
	}

	public static String getSeason() {
		return season;
	}

	public void run() {
		for (int y = 2015; y > startY-1; y--) {
			if (y % 3 == n) {
				String y1 = (y + "").substring(2, 4);
				String y2 = ((y + 1) + "").substring(2, 4);
				String season = y1 + "-" + y2;
				DataClass dc = wholeData.get(season);
				try {
					dc.setList(getMatchPOListBySeason(season));
					dc.setIsOK(true);
				} catch (Exception e) {
					dc.setList(null);
					e.printStackTrace();
				}
			}
		}
	}

	public void setN(int n) {
		this.n = n;
	}

	public static ArrayList<MatchPO> getMatchPOListBySeason(String season)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:nba.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"
				+ Path.path);
		Statement stat = conn.createStatement();
		Statement stat1 = conn.createStatement();
		Statement stat2 = conn.createStatement();
		String table1 = "MatchPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		String table2 = "playerInMatchesPO" + season.substring(0, 2)
				+ season.substring(3, 5);
		ArrayList<MatchPO> matchPOlist = new ArrayList<MatchPO>(2000);
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from '" + table1 + "';");
		} catch (SQLException e) {
			return null;
		}
		while (rs.next()) {
			String key = rs.getString("key");
			Calendar date = Calendar.getInstance();
			date.set(rs.getInt("year"), rs.getInt("month"), rs.getInt("day"),
					0, 0, 0);
			ScorePO finalScore = new ScorePO(rs.getInt("score9"),
					rs.getInt("score10"));
			ArrayList<ScorePO> scores = new ArrayList<ScorePO>(4);
			scores.add(new ScorePO(rs.getInt("score1"), rs.getInt("score2")));
			scores.add(new ScorePO(rs.getInt("score3"), rs.getInt("score4")));
			scores.add(new ScorePO(rs.getInt("score5"), rs.getInt("score6")));
			scores.add(new ScorePO(rs.getInt("score7"), rs.getInt("score8")));
			String abbreviation1 = rs.getString("team1");
			String abbreviation2 = rs.getString("team2");
			ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>(
					15);
			ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>(
					15);
			ResultSet rs1 = stat1.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation1
					+ "';");
			while (rs1.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs1.getString("name"), rs1.getString("position"),
						rs1.getInt("playTime"), rs1.getInt("hit"),
						rs1.getInt("shot"), rs1.getInt("thirdHit"),
						rs1.getInt("thirdShot"), rs1.getInt("freeHit"),
						rs1.getInt("freeshot"), rs1.getInt("offensiveRebound"),
						rs1.getInt("defensiveRebound"),
						rs1.getInt("totalRebound"), rs1.getInt("assist"),
						rs1.getInt("steal"), rs1.getInt("block"),
						rs1.getInt("miss"), rs1.getInt("foul"),
						rs1.getInt("score"));
				players1.add(player);
			}
			ResultSet rs2 = stat2.executeQuery("select * from '" + table2
					+ "' where key='" + key + "' and team='" + abbreviation2
					+ "';");
			while (rs2.next()) {
				PlayerInMatchesPO player = new PlayerInMatchesPO(
						rs2.getString("name"), rs2.getString("position"),
						rs2.getInt("playTime"), rs2.getInt("hit"),
						rs2.getInt("shot"), rs2.getInt("thirdHit"),
						rs2.getInt("thirdShot"), rs2.getInt("freeHit"),
						rs2.getInt("freeshot"), rs2.getInt("offensiveRebound"),
						rs2.getInt("defensiveRebound"),
						rs2.getInt("totalRebound"), rs2.getInt("assist"),
						rs2.getInt("steal"), rs2.getInt("block"),
						rs2.getInt("miss"), rs2.getInt("foul"),
						rs2.getInt("score"));
				players2.add(player);
			}
			TeamInMatchesPO team1 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation1, season),
					players1);
			TeamInMatchesPO team2 = new TeamInMatchesPO(
					TransToStandard.getStandard(abbreviation2, season),
					players2);
			MatchPO matchPO = new MatchPO(season, date, finalScore, scores,
					team1, team2);
			matchPO.setFileName(key);
			matchPO.setType(rs.getString("type"));
			matchPOlist.add(matchPO);
		}

		conn.close();
		return matchPOlist;
	}

	public static HashMap<String, DataClass> getWholeData() {
		return wholeData;
	}
}
