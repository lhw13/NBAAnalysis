package bltest;

import static org.junit.Assert.*;

import org.junit.Test;

import server.businesslogic.BLController;
import blservice.BLService;

public class BLTest {
	@Test
	public void testTeamAnalyse()
	{
		BLService bl = BLController.getInstance();
		bl.getTeamAnalysis();
		assertTrue(true);
	}
	@Test
	public void testTeamAnalyseByName()
	{
		BLService bl = BLController.getInstance();
		bl.getTeamAnalysis("AB");
		assertTrue(true);
	}
	@Test
	public void testPlayerAnalyse()
	{
		BLService bl = BLController.getInstance();
		bl.getPlayerAnalysis();
		assertTrue(true);
	}
	@Test
	public void testPlayerAnalyseByName()
	{
		BLService bl = BLController.getInstance();
		bl.getPlayerAnalysis("AB");
		assertTrue(true);
	}
	@Test
	public void testPlayerByTeamAnalyse()
	{
		BLService bl = BLController.getInstance();
		bl.getTeamsWithPlayers();
		assertTrue(true);
	}
}
