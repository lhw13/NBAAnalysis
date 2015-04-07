package consoletest;

import org.junit.Test;

import console.Console;

public class ConsoleTest {
	@Test
	public void executableTest()
	{
		Console c = new Console();
		String ss[] = {"-player","-total","-all","-sort","point.desc"};
		c.execute(System.out, ss);
	}
}
