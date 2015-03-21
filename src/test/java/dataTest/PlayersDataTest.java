package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import server.data.PlayersData;

public class PlayersDataTest {

	@Test
	public void test() {
		assertTrue(PlayersData.getData() > 20);
	}

}
