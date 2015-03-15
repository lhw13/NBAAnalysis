package bltest;

import static org.junit.Assert.*;

import org.junit.Test;

import server.businesslogic.Compute;
import blservice.BLService;

public class BLTest {
	@Test
	public void testTeamAnalyse()
	{
		BLService bl = Compute.getInstance();
		bl.getTeamAnalysis();
		assertTrue(true);
	}
	@Test
	public void testTeamAnalyseByName()
	{
		BLService bl = Compute.getInstance();
		bl.getTeamAnalysis("AB");
		assertTrue(true);
	}
	@Test
	public void testPlayerAnalyse()
	{
		BLService bl = Compute.getInstance();
		bl.getPlayerAnalysis();
		assertTrue(true);
	}
	@Test
	public void testPlayerAnalyseByName()
	{
		BLService bl = Compute.getInstance();
		bl.getPlayerAnalysis("AB");
		assertTrue(true);
	}
	@Test
	public void testPlayerByTeamAnalyse()
	{
		BLService bl = Compute.getInstance();
		bl.getTeamsWithPlayers();
		assertTrue(true);
	}
}
