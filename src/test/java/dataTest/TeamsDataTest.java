package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import server.data.TeamsData;

public class TeamsDataTest {

	@Test
	public void test() {
		assertEquals(TeamsData.get(),"ATL");
	}

}
