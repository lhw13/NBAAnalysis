package bltest;

import org.junit.*;

import blservice.BLService;
import server.businesslogic.BLController;

public class PerformanceTest {
	@Ignore
	public void testPerformanceWithCycle() {
		BLService bl = BLController.getInstance();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 6400; i++)
			bl.getPlayerAnalysis();
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	@Test
	public void testPerformanceWithMatches() {
		BLService bl = BLController.getInstance();
		long begin = System.currentTimeMillis();
		bl.getPlayerAnalysis();
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}
