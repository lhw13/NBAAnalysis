package server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.ScorePO;
import server.po.TeamInMatchesPO;

public class DatabaseController {
	static Statement stat;
	public static void main(String[] args) throws Exception{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:nba.db");
		 stat = conn.createStatement();
		 //writeMatch(Ant.analyseteaminfo("http://www.nba.com/games/20140502/TORBKN/gameinfo.html"));
		 //DataTransformation.MatchPOToText(getMatchPOListBySeason("13-14").get(0),"E:\\dataText\\");
		 //System.out.println(getMatchPOListBySeason("13-14").size());
		 conn.close();
	}
	private static void  writeMatch(MatchPO mp) {
		String season=mp.getSeason().substring(0, 2)+mp.getSeason().substring(3, 5);
		String matchTableName="MatchPO"+season;
		String playerTableName="playerInMatchesPO"+season;
		String sql="insert into "+matchTableName+" values(";
		sql=sql+"'"+mp.getFileName()+"','"+mp.getSeason()+"',";
		int year=mp.getDate().get(Calendar.YEAR);
		int month=mp.getDate().get(Calendar.MONTH);
		int day=mp.getDate().get(Calendar.DAY_OF_MONTH);
		sql=sql+year+","+month+","+day+",";
		ArrayList<PlayerInMatchesPO> team1=mp.getTeam1().getPlayers();
		ArrayList<PlayerInMatchesPO> team2=mp.getTeam2().getPlayers();
		ArrayList<ScorePO> scores=mp.getScores();
		String abbreviation1=mp.getTeam1().getAbbreviation();
		String abbreviation2=mp.getTeam2().getAbbreviation();
		sql=sql+"'"+abbreviation1+"','"+abbreviation2+"',";
		sql=sql+scores.get(0).getTeam1()+","+scores.get(0).getTeam2()+","+scores.get(1).getTeam1()+","+scores.get(1).getTeam2()+","+scores.get(2).getTeam1()+","+scores.get(2).getTeam2()+","+scores.get(3).getTeam1()+","+scores.get(3).getTeam2()+",";
		sql=sql+mp.getFinalScore().getTeam1()+","+mp.getFinalScore().getTeam2()+");";
		try{
		stat.execute(sql);
		}
		catch(SQLException e){
			//e.printStackTrace();
			try {
				createTableOfMatch(season);
				stat.execute(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		for(PlayerInMatchesPO playerScore:team1){
			String sql1="insert into "+playerTableName+" values(";
			sql1=sql1+"'"+mp.getFileName()+"','"+mp.getTeam1().getAbbreviation()+"',";
			sql1=sql1+"'"+playerScore.getName()+"','"+playerScore.getPosition()+"',"+playerScore.getPlayTime()+","+playerScore.getHit()+","+playerScore.getShot()+","+playerScore.getThirdHit()+","+playerScore.getThirdshot()+","+playerScore.getFreeHit()+","+playerScore.getFreeshot()+","+playerScore.getOffensiveRebound()+","+playerScore.getDefensiveRebound()+","+playerScore.getTotalRebound()+","+playerScore.getAssist()+","+playerScore.getSteal()+","+playerScore.getBlock()+","+playerScore.getMiss()+","+playerScore.getFoul()+","+playerScore.getScore();
			sql1=sql1+");";
			try {
				stat.execute(sql1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(PlayerInMatchesPO playerScore:team2){

			String sql2="insert into "+playerTableName+" values(";
			sql2=sql2+"'"+mp.getFileName()+"','"+mp.getTeam2().getAbbreviation()+"',";
			sql2=sql2+"'"+playerScore.getName()+"','"+playerScore.getPosition()+"',"+playerScore.getPlayTime()+","+playerScore.getHit()+","+playerScore.getShot()+","+playerScore.getThirdHit()+","+playerScore.getThirdshot()+","+playerScore.getFreeHit()+","+playerScore.getFreeshot()+","+playerScore.getOffensiveRebound()+","+playerScore.getDefensiveRebound()+","+playerScore.getTotalRebound()+","+playerScore.getAssist()+","+playerScore.getSteal()+","+playerScore.getBlock()+","+playerScore.getMiss()+","+playerScore.getFoul()+","+playerScore.getScore();
			sql2=sql2+");";
			try {
				stat.execute(sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void  createTableOfMatch(String season) throws Exception{
		stat.execute("create table if not exists MatchPO"+season+"(key varchar(20), season varchar(10), year int, month int, day int,team1 varchar(5),team2 varchar(5), score1 int, score2 int, score3 int, score4 int, score5 int, score6 int, score7 int, score8 int, score9 int, score10 int ,primary key(key));");
		stat.execute("create table if not exists playerInMatchesPO"+season+"(key varchar(20), team varchar(5), name varchar(30), position varchar(5), playTime int, hit int, shot int, thirdHit int, thirdshot int, freeHit int, freeshot int, offensiveRebound int, defensiveRebound int, totalRebound int, assist int, steal int, block int, miss int, foul int, score int,primary key(key,name,team));");
	}
	public static ArrayList<MatchPO> getMatchPOListBySeason(String Season) throws SQLException{
		String table1="MatchPO"+Season.substring(0, 2)+Season.substring(3, 5);
		String table2="playerInMatchesPO"+Season.substring(0, 2)+Season.substring(3, 5);
		ArrayList<MatchPO> matchPOlist =new ArrayList<MatchPO>(2000);
		ResultSet rs = stat.executeQuery("select * from '"+table1+"';");
		while(rs.next()){
		String key=rs.getString("key");
		String season=rs.getString("season");
		Calendar date= Calendar.getInstance();
		date.set(rs.getInt("year"), rs.getInt("month"), rs.getInt("day"), 0, 0,0);
		ScorePO finalScore=new ScorePO(rs.getInt("score9"),rs.getInt("score10"));
		ArrayList<ScorePO> scores=new ArrayList<ScorePO>(4);
		scores.add(new ScorePO(rs.getInt("score1"),rs.getInt("score2")));
		scores.add(new ScorePO(rs.getInt("score3"),rs.getInt("score4")));
		scores.add(new ScorePO(rs.getInt("score5"),rs.getInt("score6")));
		scores.add(new ScorePO(rs.getInt("score7"),rs.getInt("score8")));
		String abbreviation1=rs.getString("team1");
		String abbreviation2=rs.getString("team2");
		ArrayList<PlayerInMatchesPO> players1 = new ArrayList<PlayerInMatchesPO>(15);
		ArrayList<PlayerInMatchesPO> players2 = new ArrayList<PlayerInMatchesPO>(15);
		ResultSet rs1=stat.executeQuery("select * from '"+table2+"' where key='"+key+"' and team='"+abbreviation1+"';");
		 while(rs1.next()){
			 PlayerInMatchesPO player=new PlayerInMatchesPO(rs1.getString("name") , rs1.getString("position"), rs1.getInt("playTime"), rs1.getInt("hit")  ,
					 rs1.getInt("shot") , rs1.getInt("thirdHit"), rs1.getInt("thirdShot"), rs1.getInt("freeHit"), rs1.getInt("freeshot"),
					 rs1.getInt("offensiveRebound"),  rs1.getInt("defensiveRebound"),  rs1.getInt("totalRebound"),
							 rs1.getInt("assist"), rs1.getInt("steal"), rs1.getInt("block"),  rs1.getInt("miss"), rs1.getInt("foul"),rs1.getInt("score"));
			 players1.add(player);
		 }
		 ResultSet rs2=stat.executeQuery("select * from '"+table2+"' where key='"+key+"' and team='"+abbreviation2+"';");
		 while(rs2.next()){
			 PlayerInMatchesPO player=new PlayerInMatchesPO(rs2.getString("name") , rs2.getString("position"), rs2.getInt("playTime"), rs2.getInt("hit")  ,
					 rs2.getInt("shot") , rs2.getInt("thirdHit"), rs2.getInt("thirdShot"), rs2.getInt("freeHit"), rs2.getInt("freeshot"),
					 rs2.getInt("offensiveRebound"),  rs2.getInt("defensiveRebound"),  rs2.getInt("totalRebound"),
							 rs2.getInt("assist"), rs2.getInt("steal"), rs2.getInt("block"),  rs2.getInt("miss"), rs2.getInt("foul"),rs2.getInt("score"));
			 players2.add(player);
		 }
		TeamInMatchesPO team1=new TeamInMatchesPO(abbreviation1, players1);
		TeamInMatchesPO team2=new TeamInMatchesPO(abbreviation2, players2);
		MatchPO matchPO= new MatchPO(season, date, finalScore, scores, team1, team2);
		matchPO.setFileName(key);
		matchPOlist.add(matchPO);
		}
		return matchPOlist;
	}
}
