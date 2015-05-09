package consoletest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import console.Console;

public class KingTest {

	Console c = new Console();
	@Test
	public void testDailyKing() {
		String ss[] = {"-player","-king","score","-daily"};
		c.execute(System.out, ss);
	}
}
