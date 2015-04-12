package consoletest;

import org.junit.Ignore;
import org.junit.Test;

import console.Console;

public class ConsoleTest {
	@Test
	public void executableTest()
	{
		Console c = new Console();
		String ss[] = {"-player","-hot","assist","-n","5"};
		c.execute(System.out, ss);
	}
}
