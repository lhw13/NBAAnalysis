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

	@Ignore
	public void testDirtyFinalScore()
	{
		DataController data = new DataController();
		ArrayList<MatchPO> matches = data.getAllMatch();
		HashMap<String,TeamPO> teamPOHash = data.getAllTeams();
		HashMap<String,PlayerPO> playerPOHash = data.getAllPlayers();
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
			if(score1!=match.getFinalScore().getTeam1())
			{
				System.out.println("总分不符");
				System.out.println(score1);
				System.out.println(match.getFinalScore().getTeam1());
				System.out.println(match.getScores());
				for(int j=0;j<players1.size();j++)
					System.out.println(players1.get(j));
			}
		}
		assertTrue(true);
	}
}
