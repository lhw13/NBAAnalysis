package bltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;

import server.businesslogic.TeamInMatches;
import server.data.DataController;
import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.PlayerPO;
import server.po.TeamInMatchesPO;
import server.po.TeamPO;

public class DirtyDataTest {
	DataController data = new DataController();
	ArrayList<MatchPO> matches = data.getAllMatch();
	HashMap<String,TeamPO> teamPOHash = data.getAllTeams();
	HashMap<String,PlayerPO> playerPOHash = data.getAllPlayers();
	@Before
	public void initial()
	{
		DataController data = new DataController();
		matches = data.getAllMatch();
		teamPOHash = data.getAllTeams();
		playerPOHash = data.getAllPlayers();
	}

	@Ignore
	public void testDirtyFinalScore()
	{
		System.out.println("testDirtyFinalScore");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			int score1=0;
			int score2=0;
			for(int j=0;j<players1.size();j++)
				score1+=players1.get(j).getScore();
			for(int j=0;j<players2.size();j++)
				score2+=players2.get(j).getScore();
			if(score1!=match.getFinalScore().getTeam1())
			{
				System.out.println("总分不符");
				System.out.println(score1);
				System.out.println(match.getFinalScore().getTeam1());
				System.out.println(match.getScores());
				for(int j=0;j<players1.size();j++)
					System.out.println(players1.get(j));
			}
			if(score2!=match.getFinalScore().getTeam2())
			{
				System.out.println("总分不符");
				System.out.println(score2);
				System.out.println(match.getFinalScore().getTeam2());
				System.out.println(match.getScores());
				for(int j=0;j<players2.size();j++)
					System.out.println(players2.get(j));
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyStarting()
	{
		System.out.println("testDirtyStarting");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			int start1=0;
			int start2=0;
			for(int j=0;j<players1.size();j++)
			{
				char p =players1.get(j).getPosition();
				if(p=='F' || p=='G' || p=='C')
					start1++;
			}
			for(int j=0;j<players2.size();j++)
			{
				char p =players2.get(j).getPosition();
				if(p=='F' || p=='G' || p=='C')
					start2++;
			}
			if(start1!=5)
			{
				System.out.println("先发错误");
				System.out.println(start1);
				for(int j=0;j<players1.size();j++)
					System.out.println(players1.get(j));
			}
			if(start2!=5)
			{
				System.out.println("先发错误");
				System.out.println(start2);
				for(int j=0;j<players2.size();j++)
					System.out.println(players2.get(j));
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyPlayTime()
	{
		System.out.println("testDirtyPlayTime");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			for(int j=0;j<players1.size();j++)
			{
				PlayerInMatchesPO player = players1.get(j);
				if(player.getPlayTime()<0)
					System.out.println(player);
			}
			for(int j=0;j<players2.size();j++)
			{
				PlayerInMatchesPO player = players2.get(j);
				if(player.getPlayTime()<0)
					System.out.println(player);
			}
		}
		assertTrue(true);
	}
	
	@Ignore
	public void testDirtyPlayTime2()
	{
		System.out.println("testDirtyPlayTime2");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			int playTime1=0;
			int playTime2=0;
			for(int j=0;j<players1.size();j++)
				playTime1+=players1.get(j).getPlayTime();
			for(int j=0;j<players2.size();j++)
				playTime2+=players2.get(j).getPlayTime();
			int eplayTime=48*60*5;
			for(int j=4;j<match.getScores().size();j++)
				eplayTime+=1500;
			if(playTime1>=eplayTime+5 || playTime1<=eplayTime-5)
			{
				System.out.println(eplayTime);
				System.out.println(playTime1);
				for(int j=0;j<players1.size();j++)
					System.out.println(players1.get(j));
			}
			if(playTime2>=eplayTime+5 || playTime2<=eplayTime-5)
			{
				System.out.println(eplayTime);
				System.out.println(playTime2);
				for(int j=0;j<players2.size();j++)
					System.out.println(players2.get(j));
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyPlayerHit()
	{
		System.out.println("testDirtyPlayerHit");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			for(int j=0;j<players1.size();j++)
			{
				PlayerInMatchesPO player = players1.get(j);
				if(player.getScore()!=(player.getHit()-player.getThirdHit())*2+player.getThirdHit()*3+player.getFreeHit())
					System.out.println(player);
			}
			for(int j=0;j<players2.size();j++)
			{
				PlayerInMatchesPO player = players2.get(j);
				if(player.getScore()!=(player.getHit()-player.getThirdHit())*2+player.getThirdHit()*3+player.getFreeHit())
					System.out.println(player);
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyPlayerShot()
	{
		System.out.println("testDirtyPlayerShot");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			for(int j=0;j<players1.size();j++)
			{
				PlayerInMatchesPO player = players1.get(j);
				if(player.getShot()<player.getHit() || player.getFreeshot()<player.getFreeHit()
						|| player.getThirdshot()<player.getThirdHit() ||
						player.getShot()-player.getThirdshot()<player.getHit()-player.getThirdHit())
					System.out.println(player);
			}
			for(int j=0;j<players2.size();j++)
			{
				PlayerInMatchesPO player = players2.get(j);
				if(player.getShot()<player.getHit() || player.getFreeshot()<player.getFreeHit()
						|| player.getThirdshot()<player.getThirdHit() ||
						player.getShot()-player.getThirdshot()<player.getHit()-player.getThirdHit())
					System.out.println(player);
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyTotalRebound()
	{
		System.out.println("testDirtyTotalRebound");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			for(int j=0;j<players1.size();j++)
			{
				PlayerInMatchesPO player = players1.get(j);
				if(player.getTotalRebound()!=player.getDefensiveRebound()+player.getOffensiveRebound())
					System.out.println(player);
			}
			for(int j=0;j<players2.size();j++)
			{
				PlayerInMatchesPO player = players2.get(j);
				if(player.getTotalRebound()!=player.getDefensiveRebound()+player.getOffensiveRebound())
					System.out.println(player);
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testDirtyOthers()
	{
		System.out.println("testDirtyOthers");
		for(int i=0; i<matches.size();i++)
		{
			MatchPO match = matches.get(i);
			TeamInMatchesPO team1 = match.getTeam1();
			TeamInMatchesPO team2 = match.getTeam2();
			ArrayList<PlayerInMatchesPO> players1 = team1.getPlayers();
			ArrayList<PlayerInMatchesPO> players2 = team2.getPlayers();
			for(int j=0;j<players1.size();j++)
			{
				PlayerInMatchesPO player = players1.get(j);
				if(player.getAssist()<0 || player.getBlock()<0 || player.getSteal()<0 ||
						player.getMiss()<0 || player.getFoul()<0)
					System.out.println(player);
			}
			for(int j=0;j<players2.size();j++)
			{
				PlayerInMatchesPO player = players2.get(j);
				if(player.getAssist()<0 || player.getBlock()<0 || player.getSteal()<0 ||
						player.getMiss()<0 || player.getFoul()<0)
					System.out.println(player);
			}
		}
		assertTrue(true);
	}
}
