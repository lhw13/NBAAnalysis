package consoletest;

import org.junit.Ignore;
import org.junit.Test;

import console.Console;

public class ConsoleTest {
	@Ignore
	public void executableTest()
	{
		Console c = new Console();
		String ss[] = {"-player","-total","-all","-n","5","-filter","position.F"};
		c.execute(System.out, ss);
	}
}
