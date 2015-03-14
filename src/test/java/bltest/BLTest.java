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
}
