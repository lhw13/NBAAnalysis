package consoletest;

import org.junit.Test;

import console.Console;

public class ConsoleTest {
	@Test
	public void executableTest()
	{
		Console c = new Console();
		String ss[] = {"-team","-total"};
		c.execute(System.out, ss);
	}
}
